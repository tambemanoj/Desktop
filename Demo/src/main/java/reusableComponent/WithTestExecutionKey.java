package reusableComponent;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

public class WithTestExecutionKey {
	/*
	 * A possible raw implementation for uploading test results from JUnit to Xray
	 * cloud, using the standard REST API endpoint, which provides the ability of
	 * specifying some well-known attributes/fields for the Test Execution that will
	 * be created.
	 */
	

	public static void main(String[] args) throws InterruptedException {

		// To get Issue ID*******************
		createJiraIssue_TestExecution jiraIssue = new createJiraIssue_TestExecution();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		System.out.println("Date :" + actualDate);
		String issueS = "Automation Test Execution - " + actualDate;
		String issueD = "Automated Test Execution.";
		String issueNumber = null;
		try {
			issueNumber = jiraIssue.createJiraIssue("AB", issueS, issueD);
			System.out.println(" issueNumber " + issueNumber);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//**********************************************************************
		//need to add testNG Run command to get parameterization  use main arg 
	
		// ************************************************************************
		Thread.sleep(6000L);
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
		WithTestExecutionKey_XrayResultImporter xrayImporter;
		
		try {

			//(Xray cloud): Importing a testng XML report
			xrayImporter = new WithTestExecutionKey_XrayResultImporter.CloudBuilder(clientId, clientSecret)
					.withProjectKey("AB").withTestExecKey(issueNumber).build();
			response = xrayImporter.submitStandardCloud(WithTestExecutionKey_XrayResultImporter.TESTNG_FORMAT,
					testngReport);
			System.out.println("response: " + response);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.exit(0);
	}
}
