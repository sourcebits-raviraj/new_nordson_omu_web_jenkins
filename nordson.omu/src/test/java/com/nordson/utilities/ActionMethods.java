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

	public void waitForAnElementPresence(By string) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated((string)));
		}
	
	public void waitForAnElementClickable(By string) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable((string)));
		}
	public void waitFortexttoBePresent(final By byObject)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, 40);

		wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return (d.findElement(byObject).getAttribute ("value").length() >= 1);
            }
        }); 
	}

	public void waitForAnElementPresence(WebElement element) {
		// TODO Auto-generated method stub
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf((element)));
	}
	public void waitForAnElementIsInVisible(By element) throws Error 
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
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
	
	public static String getConversionToCelsiusSys(String farnhittemp)
	{
		double celsiustemp=0;
		String celsius ="";
		
		   int ctemp = Integer.parseInt(farnhittemp);
		   celsiustemp = ctemp *5/9;
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
	
	public void waitForAnElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable((element)));
	}
	
	public void copyFile(String filnm) throws IOException
	{
		// creating two channels
        // one input and other output  
		String srcfilepth=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\testData\\Norfiles\\"+filnm;
		String destnationpth=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\BDGTest\\";
        File src = new File(srcfilepth);
        File dest = new File(destnationpth); 
              
        // using copy(InputStream,Path Target); method 
        try {
            FileUtils.copyFileToDirectory(src,dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public String getlatestDownloadedNorFilenm()
	{
		String flnm="";
		   String norfilpth=System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\testData\\Norfiles\\";
			File dir = new File(norfilpth);
		    File[] fileList = dir.listFiles((d,f)-> f.toLowerCase().endsWith(".nor"));
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

	public void ConversionfromNorToXML(String flnm) throws IOException
	{
		  ProcessBuilder builder =new ProcessBuilder("cmd.exe", "/c","cd \""+System.getProperty("user.dir")+"\\src\\test\\java\\com\\nordson\\BDGTest\" && BlueDatGenerator -U "+flnm);
		  builder.redirectErrorStream(true); 
		  builder.start();
		  System.out.println("Converted NOR "+flnm+"to XML file Done");

	}

}
