package com.nordson.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.testCases.BaseClass;

public class ActionMethods extends BaseClass {

	ArrayList<String> tabs;
	// public WebDriver driver;

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		// System.out.println("Screenshot taken");
	}

	public String takeSnapShot() throws Exception {

		// below line is just to append the date format with the screenshot name to
		// avoid duplicate names
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// Returns the captured file path
		return destination;
	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

// Generic Method to create random email id
	public static String emailID() {
		String genEmailId = RandomStringUtils.randomAlphabetic(4) + "@yopmail.com";
		return (genEmailId);
	}

	public void waitForAnElementPresence(By string) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated((string)));
	}

	public void waitForAnElementClickable(By string) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable((string)));
	}

	public void waitFortexttoBePresent(final By byObject) {

		WebDriverWait wait = new WebDriverWait(driver, 40);

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (d.findElement(byObject).getAttribute("value").length() >= 1);
			}
		});
	}

	public void waitForAnElementPresence(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForAnElementIsInVisible(By element) throws Error {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	public static String getConversionToFahrenheit(String celsiustemp) {
		double fahrenheittemp = 0;
		String fahrheittemp = "";

		int ctemp = Integer.parseInt(celsiustemp);
		fahrenheittemp = ctemp * 1.8 + 32;
		fahrheittemp = String.valueOf((int) Math.round(fahrenheittemp));
		return fahrheittemp;

	}

	public static String getConversionToCelsius(String farnhittemp) {
		double celsiustemp = 0;
		String celsius = "";

		int ctemp = Integer.parseInt(farnhittemp);
		celsiustemp = (ctemp - 32) * 5 / 9;
		celsius = String.valueOf((int) Math.round(celsiustemp));
		return celsius;

	}

	public static String getConversionToCelsiusSys(String farnhittemp) {
		double celsiustemp = 0;
		String celsius = "";

		int ctemp = Integer.parseInt(farnhittemp);
		celsiustemp = ctemp * 5 / 9;
		celsius = String.valueOf((int) Math.round(celsiustemp));
		return celsius;

	}

	public static String getConversionToFahrenheitSys(String celsiustemp) {
		double fahrenheittemp = 0;
		String farntemp = "";
		int ctemp = Integer.parseInt(celsiustemp);
		fahrenheittemp = ctemp * 1.8;
		farntemp = String.valueOf((int) Math.round(fahrenheittemp));
		return farntemp;
	}

	public void waitForAnElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable((element)));
	}

	public int getXcoordinatetoclick(WebElement element) {
		return element.getLocation().x - 2;
	}

	public int getYcoordinatetoclick(WebElement element) {
		return element.getLocation().y - 4;
	}

	public void waitForAnElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToNexttab() {
		tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("No of tabs opened=" + tabs.size());
		driver.switchTo().window(tabs.get(1));
	}

	public void closeCurrentTab_SwitchtoPrevioustab() throws InterruptedException {
		driver.close();
		Thread.sleep(1000);
		driver.switchTo().window(tabs.get(0));
	}

	public void sleepTime(int milliseconds) throws Exception {

		try {

			TimeUnit.MILLISECONDS.sleep(milliseconds);
		}

		catch (Exception e) {
			throw new Exception("Pause between steps was interrupted", e);
		}
	}

	public void drawBorder(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid green'", element);
	}

	public void drawBorderFail(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void drawBorder(List<WebElement> element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public static void sendEmail() throws EmailException {
		ReadConfig readconfig = new ReadConfig();

		EmailAttachment attachment = new EmailAttachment();
		String omuPath = System.getProperty("user.dir") + readconfig.getNordsonOMUPath();
		attachment.setPath(omuPath);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);

		EmailAttachment attachment1 = new EmailAttachment();
		String emailpath = System.getProperty("user.dir") + readconfig.getEmailableReport();
		attachment1.setPath(emailpath);
		attachment1.setDisposition(EmailAttachment.ATTACHMENT);

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();

		// HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("Devteamascendum@gmail.com", "Welcome@2020"));
		email.setSSLOnConnect(true);
		email.addTo("raviraj.metri@ascendum.com", "Ravi Raj");
		email.addTo("Amrendra.Pathak@ascendum.com", "Amrendra");
		email.addTo("Kumar.Belur@ascendum.com", "Kumar Belur");
		email.addTo("Jayasena.Mallikarjun@ascendum.com", "Jayasena");
		email.setFrom("Devteamascendum@gmail.com", "Automation Team");
		email.setSubject("Nordson Test Automation Reports-" + new Date());
		email.setMsg("Please find the attached Nordson Test Automation Reports");

		// add the attachment
		email.attach(attachment);
		email.attach(attachment1);

		// send the email
		email.send();
	}

}
