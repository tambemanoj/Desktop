package reusableComponent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AWC_JiraCreateTestExecution {
	
	
	public String testExecution() {
		 String issueNumber = null;
		
		createJiraIssue_TestExecution jiraOps= new createJiraIssue_TestExecution();
	///////JIRA  creation part
			String automaticJIRAcreation = PropertiesOperations.getPropertyValueByKey("automatic_Issue_Creation_In_JIRA");
			if(automaticJIRAcreation.trim().equalsIgnoreCase("ON")) {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
				Date date = new Date();
				String actualDate = format.format(date);
				String issueS = "Automation Test Execution - "+actualDate;
				String issueD = "Automated Test Execution.";
				
				try {
					issueNumber = jiraOps.createJiraIssue("AB", issueS, issueD);
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				/*
				 * try { jiraOps.addAttachmentToJiraIssue(issueNumber, screenshotPath); } catch
				 * (Exception e) { e.printStackTrace(); }
				 */
			}
			return issueNumber;	}

}
