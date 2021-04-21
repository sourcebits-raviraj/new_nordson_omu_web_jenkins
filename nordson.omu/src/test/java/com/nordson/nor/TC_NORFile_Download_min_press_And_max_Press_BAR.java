package com.nordson.nor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XLUtils;
import com.nordson.utilities.XMLTagConstants;

public class TC_NORFile_Download_min_press_And_max_Press_BAR extends TC_LoginTest_DDT_001{
	Pressure_Min_Max_Validations rsp;
	ActionMethods Am=new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	XMLClass xmlval=new XMLClass();

	 @Test(priority=0,enabled = true) public void sheetname() {
		  XLUtils.setExcelSheetNm("MinPress_BAR"); 
		  }
	 
	 
	
	@Test(priority = 1, enabled = true,dataProvider ="min_Presure_for_norfile_BAR",dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void Min_BAR_Press_Value_comparision_with_NorFile(String Min) throws InterruptedException, IOException, SAXException  {
		
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
		  
			/*
			 * rsp.SelectBARUnit(); log.info("Selected  Raido button");
			 * 
			 * rsp.saveButton(); log.info("Save the preferences");
			 * 
			 * softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
			 */
			rsp.clickRunTimeSettings(); 
			log.info("Clicked on Run Time Settings Link");
			
			rsp.clickPressure(); 
			log.info("Clicked on Pressure Link");
			
			rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
			Thread.sleep(1200); 
			
			rsp.clearMinSetPoint();
			
			rsp.setMinValue(Min);
			
			rsp.saveButton(); 
			if (rsp.toastmessageDisplayed() == true) {
		    softAssert.assertEquals(rsp.getToastMessageText(),"Pressure updated successfully"); 
		    
		    softAssert.assertEquals(false,rsp.saveButtonEnabled());
		    }
			
			 String pressminVal=rsp.getBARMinSetPoint();
			 
			 Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal);
			 
			 driver.findElement(By.xpath("//*[@class='download']")).click();
			  
			  Thread.sleep(2000);
			  
			  String flnm=Am.getlatestDownloadedNorFilenm();
			  Thread.sleep(2000);
			 // String newfilename=Am.removeSpaces(flnm);
			  Am.copyFile(flnm); 
			  String newfilename=Am.removeSpaces(flnm);
			  Thread.sleep(2000);
			  Am.ConversionfromNorToXML(newfilename);
			  Thread.sleep(2500);
			  
			  System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
			  
			  XLUtils.setminPressurevalueBAR(xmlval.XMLParser(XMLTagConstants.
					  MinimumPressureAlert));
			  
			  
			  
			  if(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal)==xmlval.
					  XMLParser(XMLTagConstants.MinimumPressureAlert)) {
					  Assert.assertTrue(true,"Pressure value successfully updated to nor file");
					  
					  } 
			  XLUtils.setExcelSheetNm("MaxPress_BAR");		  
	    }
	@Test(priority = 2, enabled = true,dataProvider ="max_Presure_for_norfile_BAR",dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void Max_BAR_Press_Value_comparision_with_NorFile(String Max) throws InterruptedException, IOException, SAXException  {
		
		
			
			
			rsp.clearMaxSetPoint();
			
			rsp.setMaxValue(Max);
			
			rsp.saveButton(); 
			if (rsp.toastmessageDisplayed() == true) {
		    softAssert.assertEquals(rsp.getToastMessageText(),"Pressure updated successfully"); 
		    
		    softAssert.assertEquals(false,rsp.saveButtonEnabled());
		    }
			
			 String pressmaxVal=rsp.getBARMaxSetPoint();
			 
			 Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal);
			 
			 driver.findElement(By.xpath("//*[@class='download']")).click();
			  
			  Thread.sleep(2000);
			  
			  String flnm=Am.getlatestDownloadedNorFilenm();
			  Thread.sleep(2000);
			 // String newfilename=Am.removeSpaces(flnm);
			  Am.copyFile(flnm); 
			  
			  String newfilename=Am.removeSpaces(flnm);
			  Thread.sleep(2000);
			  Am.ConversionfromNorToXML(newfilename);
			  Thread.sleep(2500);
			  
			  System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
			  
			  XLUtils.setmaxPressurevalueBAR(xmlval.XMLParser(XMLTagConstants.
					  MaximumPressureAlert));
			  
			  if(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal)==xmlval.
					  XMLParser(XMLTagConstants.MaximumPressureAlert)) {
					  Assert.assertTrue(true,"Pressure value successfully updated to nor file");
					  
					  } 
					  
	}
		

}
