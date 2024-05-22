package reusableComponent;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import java.util.Arrays;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class XrayResultsImporter {
	private final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");
	private final MediaType MEDIA_TYPE_XML = MediaType.parse("text/xml");

	private final String xrayCloudApiBaseUrl = "https://xray.cloud.getxray.app/api/v2";
	private final String xrayCloudAuthenticateUrl = xrayCloudApiBaseUrl + "/authenticate";

	public static final String TESTNG_FORMAT = "testng";

	private String jiraBaseUrl;
	private String jiraUsername;
	private String jiraPassword;
	private String jiraPersonalAccessToken;

	private String clientId;
	private String clientSecret;

	private String projectKey;
	private String fixVersion;
	private String revision;
	private String testPlanKey;
	private String testExecKey;
	private String testEnvironment;

	private Boolean ignoreSslErrors = false;
	private Boolean useInternalTestProxy = false;

	private XrayResultsImporter(CloudBuilder builder) {
		this.clientId = builder.clientId;
		this.clientSecret = builder.clientSecret;
		this.projectKey = builder.projectKey;
		this.fixVersion = builder.fixVersion;
		this.revision = builder.revision;
		this.testPlanKey = builder.testPlanKey;
		this.testExecKey = builder.testExecKey;
		this.testEnvironment = builder.testEnvironment;

		this.ignoreSslErrors = builder.ignoreSslErrors;
		this.useInternalTestProxy = builder.useInternalTestProxy;
	}

	public static class CloudBuilder {

		private final String clientId;
		private final String clientSecret;

		private String projectKey;
		private String fixVersion;
		private String revision;
		private String testPlanKey;
		private String testExecKey;
		private String testEnvironment;

		private Boolean ignoreSslErrors = false;
		private Boolean useInternalTestProxy = false;

		public CloudBuilder(String clientId, String clientSecret) {
			this.clientId = clientId;
			this.clientSecret = clientSecret;
		}

		public CloudBuilder withIgnoreSslErrors(Boolean ignoreSslErrors) {
			this.ignoreSslErrors = ignoreSslErrors;
			return this;
		}

		public CloudBuilder withInternalTestProxy(Boolean useInternalTestProxy) {
			this.useInternalTestProxy = useInternalTestProxy;
			return this;
		}

		public CloudBuilder withProjectKey(String projectKey) {
			this.projectKey = projectKey;
			return this;
		}

		public CloudBuilder withVersion(String fixVersion) {
			this.fixVersion = fixVersion;
			return this;
		}

		public CloudBuilder withRevision(String revision) {
			this.revision = revision;
			return this;
		}

		public CloudBuilder withTestPlanKey(String testPlanKey) {
			this.testPlanKey = testPlanKey;
			return this;
		}

		public CloudBuilder withTestExecKey(String testExecKey) {
			this.testExecKey = testExecKey;
			return this;
		}

		public CloudBuilder withTestEnvironment(String testEnvironment) {
			this.testEnvironment = testEnvironment;
			return this;
		}

		public XrayResultsImporter build() {
			return new XrayResultsImporter(this);
		}

	}

	/*
	 * public String submit(String format, String reportFile) throws Exception { if
	 * (clientId != null) { return submitMultipartCloud(format, reportFile); } else
	 * { return submitMultipartCloud(format, reportFile, null, null); } }
	 */

	// -----------------------------------------------------------
	public String submitMultipartCloud(String format, String reportFile, JSONObject testExecInfo, JSONObject testInfo)
			throws Exception {
		OkHttpClient client = CommonUtils.getHttpClient(this.useInternalTestProxy, this.ignoreSslErrors);

		String authenticationPayload = "{ \"client_id\": \"" + clientId + "\", \"client_secret\": \"" + clientSecret
				+ "\" }";
		RequestBody body = RequestBody.create(authenticationPayload, MEDIA_TYPE_JSON);
		Request request = new Request.Builder().url(xrayCloudAuthenticateUrl).post(body).build();
		Response response = null;
		String authToken = null;
		try {
			response = client.newCall(request).execute();
			String responseBody = response.body().string();
			if (response.isSuccessful()) {
				authToken = responseBody.replace("\"", "");
				System.out.println("authToken :" + authToken);

			} else {
				throw new IOException("failed to authenticate " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		String credentials = "Bearer " + authToken;

		String supportedFormats[] = new String[] { TESTNG_FORMAT };
		if (!Arrays.asList(supportedFormats).contains(format)) {
			throw new Exception("unsupported report format: " + format);
		}

		MediaType mediaType;
		String xmlBasedFormats[] = new String[] { TESTNG_FORMAT };
		if (Arrays.asList(xmlBasedFormats).contains(format)) {
			mediaType = MEDIA_TYPE_XML;
		} else {
			mediaType = MEDIA_TYPE_JSON;
		}

		String endpointUrl;
		if ("xray".equals(format)) {
			endpointUrl = xrayCloudApiBaseUrl + "/import/execution/multipart";
			System.out.println("EndPoint : "+endpointUrl);
		} else {
			endpointUrl = xrayCloudApiBaseUrl + "/import/execution/" + format + "/multipart";
			System.out.println("EndPoint : "+endpointUrl);
		}

		HttpUrl url = HttpUrl.get(endpointUrl);
		HttpUrl.Builder builder = url.newBuilder();
		MultipartBody requestBody = null;
		try {
			requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("results", reportFile, RequestBody.create(new File(reportFile), mediaType))
					.addFormDataPart("info", "info.json", RequestBody.create(testExecInfo.toString(), MEDIA_TYPE_JSON))
					.build();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}

		request = new Request.Builder().url(builder.build()).post(requestBody).addHeader("Authorization", credentials)
				.build();
		response = null;
		try {
			response = client.newCall(request).execute();
			String responseBody = response.body().string();
			System.out.println("reportContent :"+responseBody);
			if (response.isSuccessful()) {
				JSONObject responseObj = new JSONObject(responseBody);
				 System.out.println("Test Execution: "+responseObj.get("key"));
				return responseBody;
			} else {
				// System.err.println(responseBody);
				throw new IOException("Unexpected HTTP code " + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
