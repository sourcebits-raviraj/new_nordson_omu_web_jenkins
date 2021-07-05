package com.nordson.utilities;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider {

	public JiraClient jira;
	public String project;

	public JiraServiceProvider(String jiraUrl, String username, String password, String project) {

		BasicCredentials creds = new BasicCredentials(username, password);
		jira = new JiraClient(jiraUrl, creds);
		this.project = project;

	}

	public void CreateJiraTicket(String issueType, String summary, String description, String issuedescription) {

		try {
			FluentCreate fluentCreate = jira.createIssue(project, issueType);

			fluentCreate.field(Field.SUMMARY, summary);
			fluentCreate.field(Field.DESCRIPTION, description);
			fluentCreate.field(Field.DESCRIPTION, issuedescription);
			// fluentCreate.field(Field.ATTACHMENT, attachment);

			// fluentCreate.field(Field.ATTACHMENT, attached);
			/*
			 * // fluentCreate.field(Field., estimate);
			 * 
			 * FluentCreate fluentCreate = jira.createIssue(project, issueType);
			 * 
			 * fluentCreate.field(Field.SUMMARY, summary); //
			 * fluentCreate.field(Field.TIME_SPENT, timetracking_originalestimate);
			 * fluentCreate.field(Field.DESCRIPTION, description);
			 */

			// newIssue.field(Field.Operation.TIME_TRACKING, priority);
			// newIssue.field(Meta.TIME_TRACKING, priority);
			// Field.TIME_TRACKING_originalestimate
			// newIssue.field(Field.COMMENT, estimate);

			Issue newIssue = fluentCreate.execute();

			// System.out.println(fluentCreate.field(Field.valueByKey("timetracking"),
			// estimate));
			// fluentCreate.field(Field.WORKLOG, oestimate);
			// fluentCreate.field(oestimate, Field.valueByName("Original estimate"));

			// fluentCreate.field(Field.TIME_TRACKING, oestimate);
			// fluentCreate.field(Field.REPORTER, reporterName);
			// Issue issueRef = fluentCreate.execute();
			System.out.println("New Issue Create in JIRA=" + newIssue);

		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.err.println(e.getMessage());

			if (e.getCause() != null)
				System.err.println(e.getCause().getMessage());
		}

	}

}
