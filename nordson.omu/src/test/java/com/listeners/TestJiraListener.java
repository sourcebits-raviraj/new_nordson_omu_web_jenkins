package com.listeners;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.JiraPolicy;
import com.nordson.utilities.JiraServiceProvider;

public class TestJiraListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {

		JiraPolicy jpolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		boolean isTicketReady = jpolicy.logTicketReady();

		if (isTicketReady) {
			// raise a jira ticket
			System.out.println("Is ticket ready for Jira=" + isTicketReady);
			JiraServiceProvider jirasp = new JiraServiceProvider("https://sourcebits.atlassian.net/",
					"raviraj.metri@ascendum.com", "dTlpeqJYj5FiE8axqdC9E798", "NORBUG");

			// NvISwviiwuCBJcuEfflw1BBE

			// Summary of the issue
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName()
					+ "got failed due to some assertion / exception";

			String issueDescription = result.getThrowable().getMessage() + "\n";
			String issueDescription2 = ExceptionUtils.getFullStackTrace(result.getThrowable());
			ActionMethods Am = new ActionMethods();

			/*
			 * String attachment1 = ""; try { attachment1 = Am.takeSnapShot(); } catch
			 * (Exception e) {
			 * 
			 * e.printStackTrace(); }
			 */

			jirasp.CreateJiraTicket("Bug", issueSummary, issueDescription, issueDescription2);

		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
