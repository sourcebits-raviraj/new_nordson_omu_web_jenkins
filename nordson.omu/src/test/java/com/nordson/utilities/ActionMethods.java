package com.nordson.utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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

	public void drawBorder(WebElement element, WebDriver driver) throws Exception {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid green'", element);
		sleepTime(1500);
		 //  js.executeScript("arguments[0].style.border='3px solid transparent'", element);
	}

	public void drawBorderFail(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	  public void drawBorder(List<WebElement> element, WebDriver driver) {
	  JavascriptExecutor js = ((JavascriptExecutor) driver);
	  js.executeScript("arguments[0].style.border='3px solid red'", element); }
	  
	  public String conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic() {
			double newValue =14.93889 * 1000;
			return String.valueOf((int)Math.round(newValue));
		}
	  
	  public String conversion_of_App_PSI_Default_vlaue_Max_for_Norfile_comparision_Pneumatic() {
			return String.valueOf(100*1000);
		}
		public String conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(String value_To_Be_Converted) {
			
			double prValue=Double.parseDouble(value_To_Be_Converted);
			double newValue =prValue * 1000;
			return String.valueOf((int)newValue);
		}
		
	  public String conversion_of_BAR_App_vlaue_for_Norfile_comparision_Pneumatic(String value_To_Be_Converted) {
			
			double prValue=Double.parseDouble(value_To_Be_Converted);
			double newValue =((1/0.0689475728)*prValue) * 1000;
			System.out.println(newValue);
			return String.valueOf((int)Math.round(newValue));
		}
	  
	  public String conversion_of_KPA_App_vlaue_for_Norfile_comparision_Pneumatic(String value_To_Be_Converted) {
			
			double prValue=Double.parseDouble(value_To_Be_Converted);
			double newValue =((1/6.89475728)*prValue) * 1000;
			return String.valueOf((int)Math.round(newValue));
		}
	  public String conversion_LineSpeed_mpermin(String value_To_Be_Converted) {
		  double prValue=Double.parseDouble(value_To_Be_Converted); 
		  double newValue =((1/0.3048)*prValue) * 100; 
		  return String.valueOf((int)(newValue));
		  
		  }
	 public String conversion_LineSpeed_ftpermin(String value_To_Be_Converted) {

		 double prValue=Double.parseDouble(value_To_Be_Converted);
		   double newValue =prValue * 100;
		   return String.valueOf((int)newValue);
		  }
	 
	  public String conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(String value_To_Be_Converted,String Pumpratio) {
			
			double prValue=Double.parseDouble(value_To_Be_Converted);
			double newValue =((1/6.89475728)*prValue) * 1000/Double.parseDouble(Pumpratio);
			return String.valueOf((int)Math.round(newValue));
		}
	  public String conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(String value_To_Be_Converted,String Pumpratio) {
			
			double prValue=Double.parseDouble(value_To_Be_Converted);
			double newValue =((1/0.0689475728)*prValue) * 1000/ Double.parseDouble(Pumpratio);
			System.out.println(newValue);
			return String.valueOf((int)Math.round(newValue));
		}
	  
	  public String conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(String value_To_Be_Converted,String Pumpratio) {
			
		  double prValue=Double.parseDouble(value_To_Be_Converted);
			double newValue =(prValue*1000) / Double.parseDouble(Pumpratio);
			return String.valueOf((int)Math.round(newValue));
		}
	  
	  public String getlatestDownloadedNorFilenm() throws Exception
		{
			String flnm="";
			   String norfilpth=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\testData\\Norfiles\\";
				File dir = new File(norfilpth);
				FileFilter fileFilter = new WildcardFileFilter("*.nor");
				File[] fileList = dir.listFiles(fileFilter);
			  // File[] fileList = dir.listFiles((d,f)-> f.toLowerCase().endsWith(".nor"));
			   sleepTime(2500);
			    // Listing all the included files
			    File lastModifiedFile = fileList[0];
			    if(fileList.length==1)
			    {
			    	flnm=lastModifiedFile.getName();
			    }
			    	
			    else {
			    	    for (int i = 0; i < fileList.length; i++)	{
			    		System.out.println(fileList[i]);
			    	       if(lastModifiedFile.lastModified()<fileList[i].lastModified())
			    	       {
			    	           lastModifiedFile=fileList[i];
			  	               System.out.println(lastModifiedFile.getName());
			  	               flnm=lastModifiedFile.getName();
			    	        }
			    	      else
			    		System.out.println("Nor file not found");
			    	  }                                                }
			return flnm;
		}
	  
	  public void copyFile(String filnm) throws Exception
		{
			// creating two channels
	        // one input and other output  
			String srcfilepth=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\testData\\Norfiles\\"+filnm;
			String destnationpth=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\BDGTest\\";
	        File src = new File(srcfilepth);
	        sleepTime(2500);
	        File dest = new File(destnationpth); 
	        sleepTime(2500); 
	        
	        // using copy(InputStream,Path Target); method 
	      
	        FileUtils.copyFileToDirectory(src,dest);
	        
	        System.out.println("new filename"+filnm.replace(" ", ""));
		}
	
		  public String removeSpaces(String flnm) { 
			  String path =System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\BDGTest\\"+flnm; 
		  File newfile= new File(path); 
		  
		 
		  String newflnm=flnm.replace(" ", ""); 
		  String path2 = System.getProperty("user.dir")+ "\\src\\test\\java\\com\\nordson\\BDGTest\\"+newflnm; 
		  File newfile2 = new File(path2); 
		  boolean newfileStatus= newfile.renameTo(newfile2);
		  if(newfileStatus==true)
		  System.out.println("file successfuly renamed");
		  else
			  System.out.println("file not renamed");
		  return newflnm;
		  }

		public void ConversionfromNorToXML(String flnm) throws IOException
		{
			  ProcessBuilder builder =new ProcessBuilder("cmd.exe", "/c","cd \""+System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\BDGTest\" && BlueDatGenerator -U "+flnm);
			  builder.redirectErrorStream(true); 
			  builder.start();
			  System.out.println("Converted NOR "+flnm+"to XML file Done");  
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
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("Devteamascendum@gmail.com", "Welcome@2020"));
		email.setSSLOnConnect(true);
	    email.addTo("raviraj.metri@ascendum.com", "Ravi Raj");
		email.addTo("Amrendra.Pathak@ascendum.com", "Amrendra");
		email.addTo("Kumar.Belur@ascendum.com", "Kumar Belur");
		 
		email.addTo("jayasena.mallikarjun@ascendum.com", "Jayasena");
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
