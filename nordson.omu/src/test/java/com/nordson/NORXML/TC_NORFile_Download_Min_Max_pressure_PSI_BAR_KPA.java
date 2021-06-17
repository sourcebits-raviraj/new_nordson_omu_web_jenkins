package com.nordson.nor;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001 ;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XLUtils;
import com.nordson.utilities.XMLTagConstants;

public class TC_NORFile_Download_Min_Max_pressure_PSI_BAR_KPA extends TC_LoginTest_DDT_001 {
	Pressure_Min_Max_Validations rsp;
	ActionMethods Am=new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	XMLClass xmlval=new XMLClass();

	
	  @Test(priority=0,enabled = true) public void sheetname() {
	  XLUtils.setExcelSheetNm("Pressure_for_norfile_PSI"); 
	  }
 
	
	
	@Test(priority = 1, enabled = true,dataProvider ="min_max_Presure_for_norfile_PSI",dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void Pressure_Value_comparision_with_NorFile_PSI(String Min,String Max) throws InterruptedException, IOException, SAXException  {
		
		rsp = new Pressure_Min_Max_Validations(driver);
		//rsp.CreatNewNORfile();
		//creating new nor file
		rsp.clickSetUpToolLink();
		log.info("Clicked on Setuptool");
		
		rsp.clickCreateNewFile();
		log.info("Clicked on Create new file");
		
		rsp.addDescription();
		log.info("Description provided");
		
		rsp.clickSubmit();
		log.info("Clicked on submit button");
		
		rsp.ClickSystemSettingsLink(); 
		log.info("Clicked on system setting");
		
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		
		rsp.SelectPSIUnit(); 
		log.info("Selected PSI Raido button");
		
		rsp.saveButton();
		log.info("Save the preferences");
		
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		
		rsp.clickRunTimeSettings(); 
		log.info("Clicked on Run Time Settings Link");
		
		rsp.clickPressure(); 
		log.info("Clicked on Pressure Link");
		
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(1200); 
		//XLUtils.setExcelSheetNm("Pressure_for_norfile_PSI");
		rsp.clearMinSetPoint();
		rsp.clearMaxSetPoint();
		Thread.sleep(1200); 
	    
	
        rsp.setMinValue(Min);
		rsp.setMaxValue(Max);
		
		
		rsp.saveButton(); 
		if (rsp.toastmessageDisplayed() == true) {
	    softAssert.assertEquals(rsp.getToastMessageText(),"Pressure updated successfully"); 
	    
	    softAssert.assertEquals(false,rsp.saveButtonEnabled());
	    }
		
		  //getting the min and max value from the UI String
		  String pressminVal=rsp.getPSIMinSetPoint();
		  
		  String pressmaxVal=rsp.getPSIMaxSetPoint();
		  
		  
		  //converting Integer value to string
		  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal);
		  
		  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal);
		 
		//Downloading the .Nor file
		
		  driver.findElement(By.xpath("//*[@class='download']")).click();
		  
		  Thread.sleep(2000);
		  
		  //Storing the downloaded file to the projectlocation and converting it to XML
		  String flnm=Am.getlatestDownloadedNorFilenm();
		  Thread.sleep(2000);
		 // String newfilename=Am.removeSpaces(flnm);
		  Am.copyFile(flnm); 
		  String newfilename=Am.removeSpaces(flnm);
		  Thread.sleep(2000);
		  Am.ConversionfromNorToXML(newfilename);
		  Thread.sleep(2500);
		  
		  //Getting the value from RecipeCurrent.XML
		  System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
		 
		  System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		  
		  //writing the value to excel sheet
	      XLUtils.setminmaxPressurevaluePSI(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
			  
			  
			  //writing the value to excel sheet
			 // XLUtils.setminmaxPressurevaluePSI(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
			  
			 
		  if(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal)==xmlval.
		  XMLParser(XMLTagConstants.MinimumPressureAlert) &&
		  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal)==xmlval.
		  XMLParser(XMLTagConstants.MaximumPressureAlert)) {
		  Assert.assertTrue(true,"Pressure value successfully updated to nor file");
		  
		  } 
		  
		  XLUtils.setExcelSheetNm("Pressure_for_norfile_BAR");
			
		}
	@Test(priority = 2, enabled = true,dataProvider="min_max_Presure_for_norfile_BAR",dataProviderClass =
			  com.nordson.utilities.XLUtils.class) 
	  public void Pressure_Value_comparision_with_NorFile_BAR(String Min,String Max) throws
	  InterruptedException, IOException, SAXException {
		
		 rsp.ClickSystemSettingsLink(); 
		 
		  log.info("Clicked on system setting");
			/*
			 * JavascriptExecutor jse = (JavascriptExecutor)driver;
			 * jse.executeScript("arguments[0].click()", ele);
			 */
		  rsp.ClickPreferencesLink(); 
		  
		  log.info("Clicked on Preferences Link");
		  
		  rsp.SelectBARUnit(); log.info("Selected Bar Raido button");
		   rsp.saveButton(); log.info("Save the preferences");
			  
			 softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
			 
			 rsp.clickRunTimeSettings(); 
			  
			  log.info("Clicked on Run Time Settings Link");
			  
			  rsp.clickPressure(); 
			  
			  log.info("Clicked on Pressure Link");
			  
			  rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
			  Thread.sleep(1200); 
			  
			  rsp.clearMinSetPoint(); 
			  rsp.clearMaxSetPoint();
			 
			  rsp.setMinValue(Min);//fetch the values from excel sheet
			  
			  Thread.sleep(1200); 
			  
			  rsp.setMaxValue(Max);
			  
			  
			  rsp.saveButton(); if (rsp.toastmessageDisplayed() == true) {
			  softAssert.assertEquals(rsp.getToastMessageText()
			  ,"Pressure updated successfully");
			  
			  softAssert.assertEquals(false,rsp.saveButtonEnabled()); }
			  
			  //getting the min and max value from the UI String 
			  String pressminVal=rsp.getBARMinSetPoint();
			  
			  String pressmaxVal=rsp.getBARMaxSetPoint();
			  
			  
			  //converting Integer value to string
			  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal);
			  
			  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal);
			  
			  //Downloading the .Nor file
			  
			  driver.findElement(By.xpath("//*[@class='download']")).click();
			  
			  Thread.sleep(2000);
			  
			  //Storing the downloaded file to the project location and converting it to XML
			  String flnm=Am.getlatestDownloadedNorFilenm();
			  Thread.sleep(2000);
			  //String newfilename=Am.removeSpaces(flnm);
			  Am.copyFile(flnm); 
			  String newfilename=Am.removeSpaces(flnm);
			  Thread.sleep(2000);
			  Am.ConversionfromNorToXML(newfilename);
			  Thread.sleep(2500);;
			  
			  //Getting the value from RecipeCurrent.XML
				
				  System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
				  System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
				  
					
					  //writing the value to excel sheet
					  XLUtils.setminmaxPressurevalueBAR(xmlval.XMLParser(XMLTagConstants.
					  MinimumPressureAlert),xmlval.XMLParser(XMLTagConstants.
					  MaximumPressureAlert));
					 
				 
				 
				 
			  
			  if(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal)==xmlval.
			  XMLParser(XMLTagConstants.MinimumPressureAlert) &&
			  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal)==xmlval.
			  XMLParser(XMLTagConstants.MaximumPressureAlert)) {
			  Assert.assertTrue(true,"Pressure value successfully updated to nor file");
			  
			  }
			 
			  XLUtils.setExcelSheetNm("Pressure_for_norfile_KPA");
			  }
	@Test(priority = 3, enabled = true,dataProvider="min_max_Presure_for_norfile_KPA",dataProviderClass =
			  com.nordson.utilities.XLUtils.class) 
	  public void Pressure_Value_comparision_with_NorFile_KPA(String Min,String Max) throws
	  InterruptedException, IOException, SAXException, InvalidFormatException {
		rsp.ClickSystemSettingsLink(); 
		  
		  log.info("Clicked on system setting");
		  
		  rsp.ClickPreferencesLink(); 
		  
		  log.info("Clicked on Preferences Link");
		  
		  rsp.SelectKPAUnit(); 
		  log.info("Selected Bar Raido button");
		  
		  rsp.saveButton();
		  log.info("Save the preferences");
		  
		  softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		  
		  rsp.clickRunTimeSettings(); 
		  
		  log.info("Clicked on Run Time Settings Link");
		  
		  rsp.clickPressure(); 
		  
		  log.info("Clicked on Pressure Link");
		  
		  rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		  Thread.sleep(1200); 
		  
		  rsp.clearMinSetPoint(); 
		  rsp.clearMaxSetPoint();
		 
		  rsp.setMinValue(Min);//fetch the values from excel sheet
		  
		  Thread.sleep(1200); 
		  
		  rsp.setMaxValue(Max);
		  
		  
		  rsp.saveButton(); if (rsp.toastmessageDisplayed() == true) {
		  softAssert.assertEquals(rsp.getToastMessageText()
		  ,"Pressure updated successfully");
		  
		  softAssert.assertEquals(false,rsp.saveButtonEnabled()); }
		  
		  //getting the min and max value from the UI String 
		  String pressminVal=rsp.getkPaMinSetPoint();
		  
		  String pressmaxVal=rsp.getKpaMaxSetPoint();
		  
		  
		  //converting Integer value to string
		  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal);
		  
		  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal);
		  
		  //Downloading the .Nor file
		  
		  driver.findElement(By.xpath("//*[@class='download']")).click();
		  
		  Thread.sleep(5000);
		  
		  //Storing the downloaded file to the project location and converting it to XML
		  String flnm=Am.getlatestDownloadedNorFilenm();
		  Thread.sleep(2000);
		 //String newfilename=Am.removeSpaces(flnm);
		  Am.copyFile(flnm); 
		  String newfilename=Am.removeSpaces(flnm);
		  Thread.sleep(2000);
		  Am.ConversionfromNorToXML(newfilename);
		  Thread.sleep(2000);
		  //Getting the value from RecipeCurrent.XML
			
			  System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
			  System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
			  
			
			  
				
				
				  //writing the value to excel sheet
				  XLUtils.setminmaxPressurevalueKPA(xmlval.XMLParser(XMLTagConstants.
				  MinimumPressureAlert),xmlval.XMLParser(XMLTagConstants.
				  MaximumPressureAlert));
				 
			 
			 
			 
		  
		  if(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal)==xmlval.
		  XMLParser(XMLTagConstants.MinimumPressureAlert) &&
		  Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal)==xmlval.
		  XMLParser(XMLTagConstants.MaximumPressureAlert)) {
		  Assert.assertTrue(true,"Pressure value successfully updated to nor file");
		  
		  }
		  
		  
		  }	
		
	
	}

