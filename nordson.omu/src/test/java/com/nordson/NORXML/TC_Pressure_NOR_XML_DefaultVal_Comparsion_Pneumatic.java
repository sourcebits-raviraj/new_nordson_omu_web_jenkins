package com.nordson.NORXML;

import java.io.IOException;

import org.apache.commons.math3.util.Precision;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.pageObjects.Runtime_Settings_Pressure_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XLUtils;
import com.nordson.utilities.XMLTagConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_Pressure_NOR_XML_DefaultVal_Comparsion_Pneumatic extends TC_LoginTest_DDT_001 {

	Pressure_Min_Max_Validations rsp;
	ActionMethods Am = new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	XMLClass xmlval = new XMLClass();

	@Test(priority = 0, enabled = true)
	public void TC_SetExcelNM() {
		XLUtils.setExceltNm("NorfilePressure_Pneumatic_DefaultValus");
			}
	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value in downloaded NOR file for KPA Unit")
	@Test(priority = 1, enabled = true)
	public void TC_Pneumatic_Manualadjust_Pressure_DefaultValue_comparision_with_NorFile_KPA()
			throws Exception {
		
		rsp = new Pressure_Min_Max_Validations(driver);
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectKPAUnit();
		log.info("Selected KPA Raido button");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(2000);
		
		// getting the Default value from the UI String
				String pressminVal = rsp.getkPaMinSetPoint();
				String pressmaxVal = rsp.getKpaMaxSetPoint();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2500);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(4000);
		Am.NorcopyFile(flnm);
		Thread.sleep(4000);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML

		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_KPA_Manualadjust_dfltval",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparing the old tags
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -KPA
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -KPA
		softAssert.assertEquals(pressmaxVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert)));

		// Verification of native tags
		
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_KPA_dfltval_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative)));
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -KPA
		String newcnvval=String.valueOf(Integer.parseInt(Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative)))+1).toString();
		softAssert.assertEquals(pressmaxVal,newcnvval);

		softAssert.assertAll();
	}


  @Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value default values in downloaded NOR file for BAR Unit")
  @Test(priority = 2, enabled = true) 
  public void TC_Pneumatic_Manualadjust_Pressure_DefaultValueXML_comparision_with_UI_BAR() throws Exception {
  
  rsp.clickDashboard();
  rsp=new Pressure_Min_Max_Validations(driver);
  rsp.CreatNewNORfile(); 
  rsp.ClickSystemSettingsLink();
  log.info("Clicked on system setting");
  rsp.ClickPreferencesLink();
  log.info("Clicked on Preferences Link"); 
  rsp.checkBARPressureUnitSelected();
  Thread.sleep(1500);
  rsp.clickRunTimeSettings();
  log.info("Clicked on Run Time Settings Link"); 
  rsp.clickPressure();
  log.info("Clicked on Pressure Link");
  rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
  Thread.sleep(2000); 
 
  // getting the min and max value from the UI
  String pressminVal = rsp.getBARMinSetPoint();
  String pressmaxVal =rsp.getBARMaxSetPoint(); 
  
  //Downloading the .Nor file
  rsp.clickdownload(); 
  Thread.sleep(2500); 
 
  // Storing the downloaded file to the project location and converting it to XML String
  String flnm = Am.getlatestDownloadedNorFilenm();
  Thread.sleep(2000);
  Am.NorcopyFile(flnm); 
  Thread.sleep(2000);
  String newfilename = Am.removeSpaces(flnm);
  Thread.sleep(2000); Am.ConversionfromNorToXML(newfilename);
  Thread.sleep(2500); 
  
  // writing the value to excel sheet
  XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_Dflt_Manualadjust",
  XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
  xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
  xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert)); 
  
//Comparing the old tags
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -BAR
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		
		System.out.println(Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -BAR
		softAssert.assertEquals(pressmaxVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert)));

		// Verification of native tags
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_dfltval_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative)));
		
		double val1=Precision.round(Double.parseDouble(Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative))),2);
		String newcnvval=String.valueOf(Precision.round(val1+0.01,2));
		System.out.println(newcnvval);
		softAssert.assertEquals(pressmaxVal,newcnvval);

		softAssert.assertAll();
  }	
  
  @Test(priority = 3, enabled = true) 
  public void TC_Pneumatic_Manualadjust_Pressure_DefaultValueXML_comparision_with_UI_PSI() throws Exception {
  
  rsp.clickDashboard();
  rsp.CreatNewNORfile(); 
  rsp.ClickSystemSettingsLink();
  log.info("Clicked on system setting");
  rsp.ClickPreferencesLink();
  log.info("Clicked on Preferences Link"); 
  rsp.SelectPSIUnit();
  Thread.sleep(1500);
  log.info("Selected PSI Raido button");
	rsp.saveButton();
	log.info("Save the preferences");
	softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
  rsp.clickRunTimeSettings();
  log.info("Clicked on Run Time Settings Link"); 
  rsp.clickPressure();
  log.info("Clicked on Pressure Link");
  rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
  Thread.sleep(2000); 
 
  // getting the min and max value from the UI
  String pressminVal = rsp.getPSIMinSetPoint();
  String pressmaxVal =rsp.getPSIMaxSetPoint(); 
  
  //Downloading the .Nor file
  rsp.clickdownload(); 
  Thread.sleep(2500); 
 
  // Storing the downloaded file to the project location and converting it to XML String
  String flnm = Am.getlatestDownloadedNorFilenm();
  Thread.sleep(2000);
  Am.NorcopyFile(flnm); 
  Thread.sleep(2000);
  String newfilename = Am.removeSpaces(flnm);
  Thread.sleep(2000); Am.ConversionfromNorToXML(newfilename);
  Thread.sleep(2500); 
  
  // writing the value to excel sheet
  XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_Dflt_Manualadjust",
  XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
  xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
  xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert)); 
  
//Comparing the old tags
  
	XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_dfltval_Mnladjst",
			XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
			xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
			xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -PSI
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -PSI
		softAssert.assertEquals(pressmaxVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert)));

		// Verification of native tags
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_dfltval_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative)));
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(pressmaxVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative)));

		softAssert.assertAll();
  }	
	  @Feature("Pressure Mode Electronic Adjust and pressure scaling as pneumatic")
	  @Description("Verify the All fields with Default values in downloaded NOR file for KPA Unit" )
	  @Test(priority = 4, enabled = true)
	  public void TC_Pneumatic_electronicadjust_KPA_Press_NorFile_Value_comparision_with_UI() throws
	  Exception, InvalidFormatException {
	  rsp.clickDashboard();
	  rsp.CreatNewNORfile(); 
	  rsp.ClickSystemSettingsLink();
	  log.info("Clicked on system setting");
	  rsp.ClickPreferencesLink();
	  log.info("Clicked on Preferences Link");
	  rsp.SelectKPAUnit();
	  log.info("Selected KPA Raido button");
	  rsp.saveButton();
	  log.info("Save the preferences");
	  softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
	  rsp.clickRunTimeSettings(); 
	  log.info("Clicked on Run Time Settings Link");
	  rsp.clickPressure(); 
	  log.info("Clicked on Pressure Link");
	  rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
	  Thread.sleep(2000);
	  
	  //getting Values of the electronic adjust fields from UI
	  String prssstpnt=rsp.getPressureSetPoint();
	  String lpalrtthsld=rsp.getLowPressureAlertThreshold();
	  String hpalrtthsld=rsp.getHighPressureAlertThreshold();
	  String minprspntrng=rsp.getMinimumPressureSetPointRange();
	  String maxprspntrng=rsp.getMaximumPressureSetPointRange(); 
	  Thread.sleep(2000);
	  
	  // Downloading the .Nor file 
	  rsp.clickdownload(); 
	  Thread.sleep(2500); 
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm(); 
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000);
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000); ;
	  
	  // writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_electronicadjust(
	  "OP_Elctrnicadjst_KPA_Dfltval", XMLTagConstants.PressureSetpoint,
	  xmlval.XMLParser(XMLTagConstants.PressureSetpoint),
	  XMLTagConstants.LowPressureAlertthsldelctrnic,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic),
	  XMLTagConstants.highPressureAlertthsldelctrnic,
	  xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic),
	  XMLTagConstants.MinimumPressureSetpointrange,
	  xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrange),
	  XMLTagConstants.MaximumPressureSetpointrange,
	  xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrange));
	  
	  // old tags
	  
	  //Verification of ulPressureSetPoint tag with Pressure Unit as -KPA
	  softAssert.assertEquals(prssstpnt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.PressureSetpoint))); 
	  softAssert.assertEquals(lpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic))); 
	  softAssert.assertEquals(hpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic))); 
	  softAssert.assertEquals(minprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrange))); 
	  softAssert.assertEquals(maxprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrange))); 
		 	 	  
	  // Native tags 
	  // writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_electronicadjust(
	  "OP_Elcadj_Dfltval_KPA_Ntv", XMLTagConstants.PressureSetpointNative,
	  xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
	  XMLTagConstants.LowPressureAlertthsldelctrnicNative,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
	  XMLTagConstants.highPressureAlertthsldelctrnicNative,
	  xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
	  XMLTagConstants.MinimumPressureSetpointrangeNative,
	  xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
	  XMLTagConstants.MaximumPressureSetpointrangeNative,
	  xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
	  
	  //Verification of ulPressureSetPointNative tag with Pressure Unit as -KPA
	  softAssert.assertEquals(prssstpnt, Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.PressureSetpointNative))); 
	  softAssert.assertEquals(lpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative))); 
	  softAssert.assertEquals(hpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative))); 
	  softAssert.assertEquals(minprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative))); 
	  softAssert.assertEquals(maxprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative))); 
		
	  softAssert.assertAll();
	 }
	  
	  @Feature("Pressure Mode Electronic Adjust and pressure scaling as pneumatic")
	  @Description("Verify the All fields with Default values in downloaded NOR file for KPA Unit" )
	  @Test(priority = 5, enabled = true)
	  public void TC_Pneumatic_electronicadjust_BAR_Press_NorFile_Value_comparision_with_UI() throws
	  Exception, InvalidFormatException {
	  rsp.clickDashboard();
	  rsp.CreatNewNORfile(); 
	  rsp.ClickSystemSettingsLink();
	  log.info("Clicked on system setting");
	  rsp.ClickPreferencesLink();
	  log.info("Clicked on Preferences Link");
	  rsp.checkBARPressureUnitSelected();
	  log.info("Selected BAR Raido button");
	  rsp.clickRunTimeSettings(); 
	  log.info("Clicked on Run Time Settings Link");
	  rsp.clickPressure(); 
	  log.info("Clicked on Pressure Link");
	  rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
	  Thread.sleep(2000);
	  
	  //getting Values of the electronic adjust fields from UI
	  String prssstpnt=rsp.getPressureSetPoint();
	  String lpalrtthsld=rsp.getLowPressureAlertThreshold();
	  String hpalrtthsld=rsp.getHighPressureAlertThreshold();
	  String minprspntrng=rsp.getMinimumPressureSetPointRange();
	  String maxprspntrng=rsp.getMaximumPressureSetPointRange(); 
	  Thread.sleep(2000);
	  // Downloading the .Nor file 
	  rsp.clickdownload(); 
	  Thread.sleep(2500); 
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm(); 
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000);
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000); ;
	  
	  // writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_electronicadjust(
	  "OP_Elctrnicadjst_BAR_Dfltval", XMLTagConstants.PressureSetpoint,
	  xmlval.XMLParser(XMLTagConstants.PressureSetpoint),
	  XMLTagConstants.LowPressureAlertthsldelctrnic,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic),
	  XMLTagConstants.highPressureAlertthsldelctrnic,
	  xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic),
	  XMLTagConstants.MinimumPressureSetpointrange,
	  xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrange),
	  XMLTagConstants.MaximumPressureSetpointrange,
	  xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrange));
	  
	  // old tags
	  //Verification of ulPressureSetPoint tag with Pressure Unit as -BAR
	  softAssert.assertEquals(prssstpnt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.PressureSetpoint))); 
	  softAssert.assertEquals(lpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic))); 
	  softAssert.assertEquals(hpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic))); 
	  softAssert.assertEquals(minprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrange))); 
	  softAssert.assertEquals(maxprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrange))); 
		 	 	  
	  // Native tags 
	  // writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_electronicadjust(
	  "OP_Elcadj_Dfltval_BAR_Ntv", XMLTagConstants.PressureSetpointNative,
	  xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
	  XMLTagConstants.LowPressureAlertthsldelctrnicNative,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
	  XMLTagConstants.highPressureAlertthsldelctrnicNative,
	  xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
	  XMLTagConstants.MinimumPressureSetpointrangeNative,
	  xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
	  XMLTagConstants.MaximumPressureSetpointrangeNative,
	  xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
	  
	  //Verification of ulPressureSetPointNative tag with Pressure Unit as -KPA
	  softAssert.assertEquals(prssstpnt, Am. conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.PressureSetpointNative))); 
	  softAssert.assertEquals(lpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative))); 
	  softAssert.assertEquals(hpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative))); 
	  softAssert.assertEquals(minprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative))); 
	  softAssert.assertEquals(maxprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative))); 
		
	  softAssert.assertAll();
	 }	  
	
	  @Feature("Pressure Mode Electronic Adjust and pressure scaling as pneumatic")
	  @Description("Verify the All fields with Default values in downloaded NOR file for KPA Unit" )
	  @Test(priority = 6, enabled = true)
	  public void TC_Pneumatic_electronicadjust_PSI_Press_NorFile_Value_comparision_with_UI() throws
	  Exception, InvalidFormatException {
	  rsp.clickDashboard();
	  rsp.CreatNewNORfile(); 
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
	  rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
	  Thread.sleep(2000);
	  
	  //getting Values of the electronic adjust fields from UI
	  String prssstpnt=rsp.getPressureSetPoint();
	  String lpalrtthsld=rsp.getLowPressureAlertThreshold();
	  String hpalrtthsld=rsp.getHighPressureAlertThreshold();
	  String minprspntrng=rsp.getMinimumPressureSetPointRange();
	  String maxprspntrng=rsp.getMaximumPressureSetPointRange(); 
	  Thread.sleep(2000);
	  
	  // Downloading the .Nor file 
	  rsp.clickdownload(); 
	  Thread.sleep(2500); 
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm(); 
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000);
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000); ;
	  
	  // writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_electronicadjust(
	  "OP_Elctrnicadjst_PSI_Dfltval", XMLTagConstants.PressureSetpoint,
	  xmlval.XMLParser(XMLTagConstants.PressureSetpoint),
	  XMLTagConstants.LowPressureAlertthsldelctrnic,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic),
	  XMLTagConstants.highPressureAlertthsldelctrnic,
	  xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic),
	  XMLTagConstants.MinimumPressureSetpointrange,
	  xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrange),
	  XMLTagConstants.MaximumPressureSetpointrange,
	  xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrange));
	  
	  // old tags
	  
	  //Verification of ulPressureSetPoint tag with Pressure Unit as -PSI
	  softAssert.assertEquals(prssstpnt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.PressureSetpoint))); 
	  softAssert.assertEquals(lpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic))); 
	  softAssert.assertEquals(hpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic))); 
	  softAssert.assertEquals(minprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrange))); 
	  softAssert.assertEquals(maxprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrange))); 
		 	 	  
	  // Native tags 
	  // writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_electronicadjust(
	  "OP_Elcadj_Dfltval_PSI_Ntv", XMLTagConstants.PressureSetpointNative,
	  xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
	  XMLTagConstants.LowPressureAlertthsldelctrnicNative,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
	  XMLTagConstants.highPressureAlertthsldelctrnicNative,
	  xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
	  XMLTagConstants.MinimumPressureSetpointrangeNative,
	  xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
	  XMLTagConstants.MaximumPressureSetpointrangeNative,
	  xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
	  
	  //Verification of ulPressureSetPointNative tag with Pressure Unit as -KPA
	  softAssert.assertEquals(prssstpnt, Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.PressureSetpointNative))); 
	  softAssert.assertEquals(lpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative))); 
	  softAssert.assertEquals(hpalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative))); 
	  softAssert.assertEquals(minprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative))); 
	  softAssert.assertEquals(maxprspntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative))); 
		
	  softAssert.assertAll();
	 }
	  
	  @Feature("Pressure Mode Run UP and pressure scaling as pneumatic")
	  @Description("Verify the All fields with Minimum value in downloaded NOR file for KPA Unit")
	  @Test(priority = 7, enabled = true)
	  public void TC_Pneumatic_Runup_KPA_Press_NorFile_Value_comparision_with_UI() throws Exception,
	  InvalidFormatException {
	  rsp.clickDashboard(); 
	  rsp.CreatNewNORfile(); 
	  rsp.ClickSystemSettingsLink();
	  log.info("Clicked on system setting"); 
	  rsp.ClickPreferencesLink();
	  log.info("Clicked on Preferences Link");
	  rsp.SelectKPAUnit();
	  log.info("Selected KPA Raido button"); 
	  rsp.saveButton();
	  log.info("Save the preferences");
	  softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
	  rsp.clickRunTimeSettings(); 
	  log.info("Clicked on Run Time Settings Link");
	  rsp.clickPressure();
	  log.info("Clicked on Pressure Link");
	  rsp.SelectMainPressureModeSelectionDropdown("Runup"); 
	  Thread.sleep(2000);
	  
	  // getting values from UI 

	 String lpaltthsld=rsp.getLowPressureAlertThreshold();
	 String hghaltthsld=rsp.getHighPressureAlertThreshold();
	 String lwspdpsttng=rsp.getLowSpeedPressureSetting();
	 String hghspdpsttng=rsp.getHighSpeedPressureSetting();
	 String stmxpslmt=rsp.getSetMaximumPressureLimit();
	 String stminpslmt=rsp.getSetMinimumPressureLimit();
	 Thread.sleep(2000);
	  
	  // Downloading the .Nor file 
	  rsp.clickdownload(); 
	  Thread.sleep(2500);
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm();
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000); 
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000); 
	  // Getting the value from RecipeCurrent.XML and writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_Runup("Output_KPA_Dfltvals_Runup",
	  XMLTagConstants.LowPressureAlertshldRunup,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup),
	  XMLTagConstants.HighPressureAlertshldRunup,
	  xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup),
	  XMLTagConstants.LowSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),XMLTagConstants.
	  HighSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),XMLTagConstants.
	  SetmaxPressurelimit, xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit),
	  XMLTagConstants.SetminPressurelimit,
	  xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));

	  
	  //Verification of ulLowPressureAlertDelta tag with Pressure Unit as -KPA
	  softAssert.assertEquals(lpaltthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup))); 
	  softAssert.assertEquals(hghaltthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup))); 
	  softAssert.assertEquals(lwspdpsttng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting))); 
	  softAssert.assertEquals(hghspdpsttng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting))); 
	  softAssert.assertEquals(stmxpslmt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit))); 
	  softAssert.assertEquals(stminpslmt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_KPA(xmlval.XMLParser(XMLTagConstants.SetminPressurelimit))); 
	  softAssert.assertAll();
	 }
	  
	  
	  
	  @Feature("Pressure Mode Run UP and pressure scaling as pneumatic")
	  @Description("Verify the All fields with Default values in downloaded NOR file for BAR Unit")
	  @Test(priority = 8, enabled = true)
	  public void TC_Pneumatic_Runup_BAR_Press_NorFile_Value_comparision_with_UI() throws Exception,
	  InvalidFormatException {
		  
	 // rsp=new Pressure_Min_Max_Validations(driver);
	  rsp.clickDashboard(); 
	  rsp.CreatNewNORfile(); 
	  rsp.ClickSystemSettingsLink();
	  log.info("Clicked on system setting"); 
	  rsp.ClickPreferencesLink();
	  log.info("Clicked on Preferences Link");
	  rsp.checkBARPressureUnitSelected();
	  log.info("Selected BAR Raido button"); 
	  rsp.clickRunTimeSettings(); 
	  log.info("Clicked on Run Time Settings Link");
	  rsp.clickPressure();
	  log.info("Clicked on Pressure Link");
	  rsp.SelectMainPressureModeSelectionDropdown("Runup"); 
	  Thread.sleep(2000);
	  
	  // getting values from UI 
	 String lpaltthsld=rsp.getLowPressureAlertThreshold();
	 String hghaltthsld=rsp.getHighPressureAlertThreshold();
	 String lwspdpsttng=rsp.getLowSpeedPressureSetting();
	 String hghspdpsttng=rsp.getHighSpeedPressureSetting();
	 String stmxpslmt=rsp.getSetMaximumPressureLimit();
	 String stminpslmt=rsp.getSetMinimumPressureLimit(); 
	 Thread.sleep(2000);
	  
	  // Downloading the .Nor file 
	  rsp.clickdownload(); 
	  Thread.sleep(2500);
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm();
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000); 
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000); 
	  // Getting the value from RecipeCurrent.XML and writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_Runup("Output_BAR_Dfltvals_Runup",
	  XMLTagConstants.LowPressureAlertshldRunup,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup),
	  XMLTagConstants.HighPressureAlertshldRunup,
	  xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup),
	  XMLTagConstants.LowSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),XMLTagConstants.
	  HighSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),XMLTagConstants.
	  SetmaxPressurelimit, xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit),
	  XMLTagConstants.SetminPressurelimit,
	  xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));

	  
	  //Verification of ulLowPressureAlertDelta tag with Pressure Unit as -PSI
	  softAssert.assertEquals(lpaltthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup))); 
	  softAssert.assertEquals(hghaltthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup))); 
	  softAssert.assertEquals(lwspdpsttng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting))); 
	  softAssert.assertEquals(hghspdpsttng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting))); 
	  softAssert.assertEquals(stmxpslmt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit))); 
	  if(stminpslmt.contains(Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR_Native(xmlval.XMLParser(XMLTagConstants.SetminPressurelimit))))
	  log.info("Set Minimum Pressure limit is set to default value");
	  else
		  log.info("Set Minimum Pressure limit is not set to default value");
	  softAssert.assertAll();
	}  
	  
	  @Feature("Pressure Mode Run UP and pressure scaling as pneumatic")
	  @Description("Verify the All fields with Default values in downloaded NOR file for PSI Unit")
	  @Test(priority = 9, enabled = true)
	  public void TC_Pneumatic_Runup_PSI_Press_NorFile_Value_comparision_with_UI() throws Exception,
	  InvalidFormatException {
	  rsp.clickDashboard(); 
	  rsp.CreatNewNORfile(); 
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
	  rsp.SelectMainPressureModeSelectionDropdown("Runup"); 
	  Thread.sleep(2000);
	  
	  // getting values from UI 
	 String lpaltthsld=rsp.getLowPressureAlertThreshold();
	 String hghaltthsld=rsp.getHighPressureAlertThreshold();
	 String lwspdpsttng=rsp.getLowSpeedPressureSetting();
	 String hghspdpsttng=rsp.getHighSpeedPressureSetting();
	 String stmxpslmt=rsp.getSetMaximumPressureLimit();
	 String stminpslmt=rsp.getSetMinimumPressureLimit(); 
	 Thread.sleep(2000);
	  
	  // Downloading the .Nor file 
	  rsp.clickdownload(); 
	  Thread.sleep(2500);
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm();
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000); 
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000); 
	  // Getting the value from RecipeCurrent.XML and writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_Runup("Output_PSI_Dfltvals_Runup",
	  XMLTagConstants.LowPressureAlertshldRunup,
	  xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup),
	  XMLTagConstants.HighPressureAlertshldRunup,
	  xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup),
	  XMLTagConstants.LowSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),XMLTagConstants.
	  HighSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),XMLTagConstants.
	  SetmaxPressurelimit, xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit),
	  XMLTagConstants.SetminPressurelimit,
	  xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));

	  
	  //Verification of ulLowPressureAlertDelta tag with Pressure Unit as -PSI
	  softAssert.assertEquals(lpaltthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup))); 
	  softAssert.assertEquals(hghaltthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup))); 
	  softAssert.assertEquals(lwspdpsttng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting))); 
	  softAssert.assertEquals(hghspdpsttng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting))); 
	  softAssert.assertEquals(stmxpslmt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit))); 
	  softAssert.assertEquals(stminpslmt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.SetminPressurelimit))); 
	  softAssert.assertAll();
	 } 
	  
	
	  @Description("Verify all the Line Speed fields values in downloaded NOR file for PSI Unit with Line Speed ft/min")
	  @Test(priority = 10, enabled = true)
	  public void TC_Pneumatic_Runup_Linespeedftpermin_Press_NOR_Default_Value_comparision_with_UIValue() throws
	  Exception, InvalidFormatException {
	  
	  rsp.clickDashboard();
	  rsp.CreatNewNORfile(); 
	  rsp.ClickSystemSettingsLink();
	  log.info("Clicked on system setting");
	  rsp.ClickPreferencesLink();
	  log.info("Clicked on Preferences Link"); 
	  rsp.SelectPSIUnit();
	  log.info("Selected PSI Raido button");
	  rsp.SelectLineSpeedftUnit();
	  log.info("Selected ft/min Raido button");
	  rsp.saveButton();
	  log.info("Save the preferences");
	  softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
	  rsp.clickRunTimeSettings();
	  log.info("Clicked on Run Time Settings Link");
	  rsp.clickPressure(); 
	  log.info("Clicked on Pressure Link");
	  rsp.SelectMainPressureModeSelectionDropdown("Runup"); 
	  Thread.sleep(2000);
	  
	  // Value Validation rsp.clearLowLineSpeedSetting();
	  String lowlnspsttng=rsp.getLowLineSpeedSetting();
	  String hghlnspsttng=rsp.getHighLineSpeedSetting();
	  String fullscllnsp=rsp.getFullScaleLineSpeed();
	
	// Downloading the .Nor file 
	  rsp.clickdownload();
	  Thread.sleep(2500); 
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm();
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000); 
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000);
	  
	  // Getting the value from RecipeCurrent.XML and writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_Runup_LineSpeed("Output_Dfltval_Runup_Lnspd_ftmn"
	  ,XMLTagConstants.LowLineSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.LowLineSppdPressuresetting),
	  XMLTagConstants.HighLineSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.HighLineSppdPressuresetting),
	  XMLTagConstants.FullScalelnspeed,
	  xmlval.XMLParser(XMLTagConstants.FullScalelnspeed));
	  
	  // Comparison of tags 
	  //Verification of usLinespeedCalPtMin tag with Pressure Line Speed as -ft/min
	  softAssert.assertEquals(lowlnspsttng,Am.conversion_LineSpeed_ftpermin_XML_ToUI(xmlval.XMLParser(XMLTagConstants.LowLineSppdPressuresetting))); 
	  softAssert.assertEquals(hghlnspsttng,Am.conversion_LineSpeed_ftpermin_XML_ToUI(xmlval.XMLParser(XMLTagConstants.HighLineSppdPressuresetting))); 
	  softAssert.assertEquals(fullscllnsp,Am.conversion_LineSpeed_ftpermin_XML_ToUI(xmlval.XMLParser(XMLTagConstants.FullScalelnspeed))); 
	  softAssert.assertAll();
	  }
	  
	  @Description("Verify all the Line Speed fields values in downloaded NOR file with Line Speed m/min")
	  @Test(priority = 11, enabled = true)
	  public void TC_Pneumatic_Runup_Linespeedmeterpermin_Press_NOR_Default_Value_comparision_with_UIValue() throws
	  Exception, InvalidFormatException {
	  
	  rsp.clickDashboard();
	 //rsp=new Pressure_Min_Max_Validations(driver);
	  rsp.CreatNewNORfile(); 
	  rsp.ClickSystemSettingsLink();
	  log.info("Clicked on system setting");
	  rsp.ClickPreferencesLink();
	  log.info("Clicked on Preferences Link"); 
	  rsp.checkBARPressureUnitSelected();
	  log.info("Selected BAR Raido button");
	  rsp.clickRunTimeSettings();
	  log.info("Clicked on Run Time Settings Link");
	  rsp.clickPressure();
	  log.info("Clicked on Pressure Link");
	  rsp.SelectMainPressureModeSelectionDropdown("Runup"); 
	  Thread.sleep(2000);
	  
	  // Value Validation rsp.clearLowLineSpeedSetting();
	  String lowlnspsttng=rsp.getLowLineSpeedSetting();
	  String hghlnspsttng=rsp.getHighLineSpeedSetting();
	  String fullscllnsp=rsp.getFullScaleLineSpeed();
	
	 //Downloading the .Nor file 
	  rsp.clickdownload();
	  Thread.sleep(2500); 
	  //Storing the downloaded file to the project location and converting it to XML
	  String flnm = Am.getlatestDownloadedNorFilenm();
	  Thread.sleep(2000);
	  Am.NorcopyFile(flnm); 
	  Thread.sleep(2000);
	  String newfilename = Am.removeSpaces(flnm);
	  Thread.sleep(2000); 
	  Am.ConversionfromNorToXML(newfilename);
	  Thread.sleep(2000);
	  
	  // Getting the value from RecipeCurrent.XML and writing the value to excel sheet
	  XLUtils.setNorXMLValues_Pressure_Runup_LineSpeed("Output_Dfltval_Runup_Lnspd_mmn"
	  ,XMLTagConstants.LowLineSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.LowLineSppdPressuresetting),
	  XMLTagConstants.HighLineSppdPressuresetting,
	  xmlval.XMLParser(XMLTagConstants.HighLineSppdPressuresetting),
	  XMLTagConstants.FullScalelnspeed,
	  xmlval.XMLParser(XMLTagConstants.FullScalelnspeed));
	  
	  // Comparison of tags 
	  //Verification of usLinespeedCalPtMin tag with Pressure Line Speed as -ft/min
	  softAssert.assertEquals(lowlnspsttng,Am.conversion_LineSpeed_mpermin_XML_TOUI(xmlval.XMLParser(XMLTagConstants.LowLineSppdPressuresetting))); 
	  softAssert.assertEquals(hghlnspsttng,Am.conversion_LineSpeed_mpermin_XML_TOUI(xmlval.XMLParser(XMLTagConstants.HighLineSppdPressuresetting))); 
	  softAssert.assertEquals(fullscllnsp,Am.conversion_LineSpeed_mpermin_XML_TOUI(xmlval.XMLParser(XMLTagConstants.FullScalelnspeed))); 
	  softAssert.assertAll();
	}
  
}
