package com.nordson.nor;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001 ;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XMLTagConstants;

public class TC_NORFile_Download extends TC_LoginTest_DDT_001 {
	Pressure_Min_Max_Validations rsp;
	ActionMethods Am=new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	XMLClass xmlval=new XMLClass();
	
	@Test(priority = 1, enabled = true)
	public void Create_and_Download_NORFile() throws InterruptedException, IOException, SAXException {
		
		rsp = new Pressure_Min_Max_Validations(driver);
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
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
		Thread.sleep(1200);
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(1200);
		rsp.clearMinSetPoint();
		rsp.setMinValue("42");
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());}
	    driver.findElement(By.xpath("//*[@class='download']")).click();
		Thread.sleep(3000);
		String flnm=Am.getlatestDownloadedNorFilenm();
		Am.copyFile(flnm);
		Am.ConversionfromNorToXML(flnm);
		Thread.sleep(1200);
	    System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
	    System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
			
	}
}
