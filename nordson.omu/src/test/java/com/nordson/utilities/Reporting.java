package com.nordson.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	ActionMethods Am;
	public static WebDriver driver;

	public void onStart(ITestContext testContext) {

		// Date Format
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Nordson OMU Test-Report-" + timeStamp + ".html";
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReports/" + repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		// htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +
		// "/test-output/ExtentReports/" + repName);
		// htmlReporter.loadXMLConfig(System.getProperty("user.dir") +
		// "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "Nordson");
		extent.setSystemInfo("Environemnt", "Pre Production");
		extent.setSystemInfo("user", "Raviraj");

		// Title of the Report
		htmlReporter.config().setDocumentTitle("Nordson Test Project");

		// Name of the Report
		htmlReporter.config().setReportName("Functional Test Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

	}

	public void onTestSuccess(ITestResult tr) {

		// create new entry in the report
		logger = extent.createTest(tr.getName());

		// send the passed information to the report with GREEN color highlighted
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult tr) {

		// create new entry in the report
		logger = extent.createTest(tr.getName());

		// send the passed information to the report with RED color highlighted
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		if (ITestResult.FAILURE == tr.getStatus()) {

			Am = new ActionMethods();
			try {

				String screenShotPath = Am.takeSnapShot();
				logger.log(Status.FAIL, tr.getThrowable());
				logger.log(Status.FAIL, "Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		/*
		 * // Attach Screenshots String screenshotPath = System.getProperty("user.dir")
		 * + "\\Screenshots\\" + tr.getName() + ".png";
		 * 
		 * File f = new File(screenshotPath);
		 * 
		 * if (f.exists()) { try { logger.fail("Screenshot is below:" +
		 * logger.addScreenCaptureFromPath(screenshotPath)); } catch (IOException e) {
		 * e.printStackTrace(); } }
		 */

	}

	public void onTestSkipped(ITestResult tr) {

		// create new entry in the report
		logger = extent.createTest(tr.getName());

		// send the passed information to the report with ORANGE color highlighted
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {

		extent.flush();
	}
}