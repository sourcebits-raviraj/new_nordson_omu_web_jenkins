package com.nordson.utilities;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.util.Precision;
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
ReadConfig rcf=new ReadConfig();
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname +timestamp()+ ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
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
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		wait.until(ExpectedConditions.presenceOfElementLocated((string)));
		}
	
	public void waitForAnElementClickable(By string) {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
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
		WebDriverWait wait = new WebDriverWait(driver,30);
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
	
	  public String getlatestDownloadedNorFilenm() 
		{
			String flnm="";
			   String norfilpth=System.getProperty("user.home")+rcf.getDownloadPath();
				File dir = new File(norfilpth);
				FileFilter fileFilter = new WildcardFileFilter("*.nor");
				File[] fileList = dir.listFiles(fileFilter);
			  // File[] fileList = dir.listFiles((d,f)-> f.toLowerCase().endsWith(".nor"));
			   try {
				sleepTime(2500);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			    		System.out.println("Checking for NOR file");
			    	  }                                                }
			return flnm;
		}
	  
	  public void NorcopyFile(String filnm) throws Exception
		{
			// creating two channels
	        // one input and other output  
			String srcfilepth=System.getProperty("user.home")+rcf.getDownloadPath()+filnm;
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
	
	public String conversion_of_App_vlaue_for_Norfile_comparision(String value_To_Be_Converted) {
		
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
		return String.valueOf((double)Math.round(newValue));
	}
  
  public String conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(String value_To_Be_Converted,String Pumpratio) {
		
	  double prValue=Double.parseDouble(value_To_Be_Converted);
		double newValue =(prValue*1000) / Double.parseDouble(Pumpratio);
		return String.valueOf((int)Math.round(newValue));
	}
  
  public String conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(String value_To_Be_Converted) {
		
		double prValue=Double.parseDouble(value_To_Be_Converted);
		double newValue =prValue / 1000;
		return String.valueOf((int)Math.round(newValue));
	}
  public String conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(String value_To_Be_Converted) {
		
		double prValue=Double.parseDouble(value_To_Be_Converted);
		double newValue =prValue / 1000;
		return String.valueOf((double)Precision.round(newValue,2));
	}
  
  public String conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(String value_To_Be_Converted) {
  double prValue=Double.parseDouble(value_To_Be_Converted);
	double newValue =(prValue/1000)*6.89475728;
	return String.valueOf((int)Math.round(newValue));
   }
  
  public String conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(String value_To_Be_Converted) {
	  double prValue=Double.parseDouble(value_To_Be_Converted);
		double newValue =(prValue/1000)*0.0689475728;
		 return String.valueOf((double)Precision.round(newValue,2));
	  }

  public String conversion_LineSpeed_mpermin_XML_TOUI(String value_To_Be_Converted) {
	  double prValue=Double.parseDouble(value_To_Be_Converted); 
	  double newValue =(0.3048*prValue)/100; 
	  return String.valueOf((double)Precision.round(newValue,1));
	  
	  }
 public String conversion_LineSpeed_ftpermin_XML_ToUI(String value_To_Be_Converted) {
	 double prValue=Double.parseDouble(value_To_Be_Converted);
	   double newValue =prValue/100;
	   return String.valueOf((int)newValue);
	  }
 
  public void sleepTime(int milliseconds) throws Exception {
		try {
			   TimeUnit.MILLISECONDS.sleep(milliseconds);	}
		catch (Exception e) {
			throw new Exception("Pause between steps was interrupted", e);
		}
	 } 
  public String conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic(String value_To_Be_Converted,String Pumpratio) {
		
		double prValue=Double.parseDouble(value_To_Be_Converted);
		double newValue =(prValue/1000) * 6.89475728 *Double.parseDouble(Pumpratio);
		return String.valueOf((int)Math.round(newValue));
	}
  
  public String conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic(String value_To_Be_Converted,String Pumpratio) {
		
		double prValue=Double.parseDouble(value_To_Be_Converted);
		double newValue =(prValue/1000) * 0.0689475728 *Double.parseDouble(Pumpratio);
		System.out.println(String.valueOf((double)Precision.round(newValue,2)));
		return String.valueOf((double)Precision.round(newValue,2));
	}
  
}
