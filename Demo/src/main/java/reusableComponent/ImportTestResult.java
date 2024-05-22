package reusableComponent;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImportTestResult {

	/*
	 * A possible raw implementation for uploading test results from TestNG to Xray
	 * cloud, using the "multipart" REST API endpoint for that format. Multipart
	 * endpoints support the ability of providing the test report along with raw
	 * fields, as JSON, for the Test Execution and/or for the Test issues that may
	 * be created.
	 */
	private static String submitXrayCloudJunitMultipartEndpointExample(String reportFile) throws IOException {
		String clientId = System.getenv().getOrDefault("CLIENT_ID", "7FCD1B3BBB054C129DCC8135049B4567");
		String clientSecret = System.getenv().getOrDefault("CLIENT_SECRET",
				"f9aabafe556c2db17cfdbeb4c1fd20747fa224af3536f69b92ab57cf363e714d");

		final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");
		final MediaType MEDIA_TYPE_XML = MediaType.parse("application/xml");

		String xrayCloudApiBaseUrl = "https://xray.cloud.getxray.app/api/v2";
		String authenticateUrl = xrayCloudApiBaseUrl + "/authenticate";

		System.out.println(
				"Importing a JUnit XML report to a Xray Cloud instance, customizing the Test Execution fields...");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		System.out.println("Date :" + actualDate);
		JSONObject testExecInfo = new JSONObject()
				.put("fields",
						new JSONObject().put("project", new JSONObject().put("key", "AB"))
								.put("issuetype", new JSONObject().put("name", "Test Execution"))
								.put("fixVersions", new JSONObject[] { new JSONObject().put("name", "v1.0") }))
				.put("xrayFields",
						new JSONObject().put("testPlanKey", "AB-18").put("environments", new String[] { "Chrome" }));
		// System.out.println(testExecInfo.toString());

		OkHttpClient client = new OkHttpClient();
		String authenticationPayload = "{ \"client_id\": \"" + clientId + "\", \"client_secret\": \"" + clientSecret
				+ "\" }";
		RequestBody body = RequestBody.create(authenticationPayload, MEDIA_TYPE_JSON);
		Request request = new Request.Builder().url(authenticateUrl).post(body).build();
		Response response = null;
		String authToken = null;
		try {
			response = client.newCall(request).execute();
			// System.out.println("=============");
			// System.out.println(response);
			String responseBody = response.body().string();
			// System.out.println(responseBody);
			// System.out.println("=============");

			if (response.isSuccessful()) {
				authToken = responseBody.replace("\"", "");
				System.out.println("Import class AuthToken :" + authToken);
			} else {
				throw new IOException("failed to authenticate " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		String credentials = "Bearer " + authToken;

		String endpointUrl = xrayCloudApiBaseUrl + "/import/execution/testng/multipart";
		HttpUrl url = HttpUrl.get(endpointUrl);
		HttpUrl.Builder builder = url.newBuilder();
		MultipartBody requestBody = null;
		try {
			requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("results", reportFile, RequestBody.create(new File(reportFile), MEDIA_TYPE_XML))
					.addFormDataPart("info", "info.json", RequestBody.create(testExecInfo.toString(), MEDIA_TYPE_JSON))
					.build();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		request = new Request.Builder().url(builder.build()).post(requestBody).addHeader("Authorization", credentials)
				.build();

		response = null;
		try {
			response = client.newCall(request).execute();
			System.out.println("=============");
			System.out.println(response);
			String responseBody = response.body().string();
			System.out.println(responseBody);
			System.out.println("=============");

			if (response.isSuccessful()) {
				JSONObject responseObj = new JSONObject(responseBody);
				System.out.println("Test Execution: " + responseObj.get("key"));
				return responseBody;
			} else {
				throw new IOException("Unexpected HTTP code " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static String getIssueID() {
		createJiraIssue_TestExecution jiraIssue = new createJiraIssue_TestExecution();
		String issueNumber = null;
		String automaticJIRAcreation = PropertiesOperations.getPropertyValueByKey("automatic_Issue_Creation_In_JIRA");
		if (automaticJIRAcreation.trim().equalsIgnoreCase("ON")) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
			Date date = new Date();
			String actualDate = format.format(date);
			String issueS = "Automation Test Execution - " + actualDate;
			String issueD = "Automated Test Execution.";

			try {
				issueNumber = jiraIssue.createJiraIssue("AB", issueS, issueD);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return issueNumber;
	}

	public static void main(String[] args) {
		// To get Issue ID*******************
		String id =getIssueID();
		
			
			//************************************************************************
		System.out.println(System.getProperty("user.dir"));
		// credentials used for Xray cloud requests
		String clientId = System.getenv().getOrDefault("CLIENT_ID", "7FCD1B3BBB054C129DCC8135049B4567");
		String clientSecret = System.getenv().getOrDefault("CLIENT_SECRET",
				"f9aabafe556c2db17cfdbeb4c1fd20747fa224af3536f69b92ab57cf363e714d");

		// auxiliary variables
		// String xrayJsonDCReport = "./src/main/resources/xray_dc.json";
		// String xrayJsonCloudReport = "./src/main/resources/xray_cloud.json";
		// String junitReport = "./src/main/resources/junit.xml";
		String testngReport = "./target/surefire-reports/testng-results.xml";
		String response = null;
		XrayResultsImporter xrayImporter;
		JSONObject testExecInfo;
		JSONObject testInfo = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		System.out.println("Date :"+actualDate );
		try {

			// EXAMPLE (Xray cloud): Importing a JUnit XML report, customizing the Test
			// Execution fields...
			testExecInfo = new JSONObject()
					.put("fields",
							new JSONObject().put("summary", "Test execution for automated tests : " +actualDate)
									.put("project", new JSONObject().put("key", "AB"))
									.put("issuetype", new JSONObject().put("name", "Test Execution")))
					
	.put("xrayFields", new JSONObject().put("testPlanKey", "AB-18"));	
			
			xrayImporter = new XrayResultsImporter.CloudBuilder(clientId, clientSecret).build();
			response = xrayImporter.submitMultipartCloud(XrayResultsImporter.TESTNG_FORMAT, testngReport, testExecInfo,
					testInfo);
			System.out.println("response: " + response);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.exit(0);
	}
}
