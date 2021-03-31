package com.nordson.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.testCases.BaseClass;

public class ActionMethods extends BaseClass {

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	
	
   
	public void waitForAnElementIsInVisible(By element) throws Error 
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
	
	public void waitForAnElement()
	{
	   //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void waitForAnElementPresenceofElementLocated(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated((element)));
	}

	public void waitForAnElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable((element)));
	}

	public void waitForAnElementPresence(WebElement element) throws Error {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitFortexttoBePresent(final By byObject) {

		WebDriverWait wait = new WebDriverWait(driver, 40);

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (d.findElement(byObject).getAttribute("value").length() >= 1);
			}
		});
	}

	public void waitForAnElementVisible(WebElement element) throws Error {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static String getConversionToFahrenheit(String celsiustemp)
	{
		double fahrenheittemp=0;
		String fahrheittemp="";
		
		   int ctemp = Integer.parseInt(celsiustemp);
		   fahrenheittemp = ctemp *1.8 +32;
		   fahrheittemp= String.valueOf((int) Math.round(fahrenheittemp));
		   return fahrheittemp;
		
	}
	
	public static String getConversionToCelsius(String farnhittemp)
	{
		double celsiustemp=0;
		String celsius ="";
		
		   int ctemp = Integer.parseInt(farnhittemp);
		   celsiustemp = (ctemp-32) *5/9;
		   celsius= String.valueOf((int) Math.round(celsiustemp));
		   return celsius;
		
	}
	
	public static String getConversionToFahrenheitSys(String celsiustemp)
	{
		double fahrenheittemp=0;
		String farntemp="";
		   int ctemp = Integer.parseInt(celsiustemp);
		   fahrenheittemp = ctemp *1.8;
		   farntemp= String.valueOf((int) Math.round(fahrenheittemp));
		   return farntemp;
	}
	
	public static String getConversionToCelsiusSys(String farnhittemp)
	{
		double celsiustemp=0;
		String celsius ="";
		
		   int ctemp = Integer.parseInt(farnhittemp);
		   celsiustemp = ctemp *5/9;
		   celsius= String.valueOf((int) Math.round(celsiustemp));
		   return celsius;
		
	}
	
	public void waitForAnElementPresence(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated((element)));
	}

	public void waitForAnElementClickable(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForAnElementVisible(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

}
