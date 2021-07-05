package com.nordson.NORXML;


import org.apache.commons.math3.util.Precision;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.XLUtils;
import com.nordson.utilities.XMLTagConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_PressureHydraulic_NOR_XML_DefaultVal_Comparsion_STD_STDDA extends TC_LoginTest_DDT_001 {

	Pressure_Min_Max_Validations rsp;
	ActionMethods Am = new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	XMLClass xmlval = new XMLClass();

	@Test(priority = 0, enabled = true)
	public void sheetname() {
		XLUtils.setExceltNm("NorfilePressure_Hydraulic_Default_STD_STD-DA");
		
	}
    
	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as Manual adjust")
	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert Default value in downloaded NOR file for KPA Unit for STD STD-DA pump ratio")
	@Test(priority = 1, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_Manualadjust_Default_KPA_Pressure_Value_NorFileValue_comparision_with_UI()
			throws Exception {
		
		rsp = new Pressure_Min_Max_Validations(driver);
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectKPAUnit();
		log.info("Selected Bar Raido button");
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		log.info("Selected STD STD-DA Pump ratio for Hydraulic pressure scaling");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(1200);
		
		// getting the minimum and max value from the UI String
		String pressminVal = rsp.getkPaMinSetPoint();
		String pressmaxVal = rsp.getKpaMaxSetPoint();
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(3000);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2500);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2500);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2500);
		// Getting the value from RecipeCurrent.XML

		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_KPA_Mnuladjt_dflt",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparing the old tags
		Thread.sleep(2000);
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -KPA
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -KPA
		softAssert.assertEquals(pressmaxVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert)));

		// Verification of native tags
		Thread.sleep(2000);
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Op_KPA_Mnladjst_ntv_dflt",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(pressminVal,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative),Constants.STD_STD_DA));
		
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -KPA
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -KPA
		double val1=Precision.round(Double.parseDouble(Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative),Constants.STD_STD_DA)),2);
		String newcnvval=String.valueOf((int)Precision.round(val1+8,2));
		System.out.println(newcnvval);
		softAssert.assertEquals(pressmaxVal,newcnvval);
		softAssert.assertAll();
	}

	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value from downloaded NOR file to UI for BAR Unit STD STD-DA pump ratio")
	@Test(priority = 2, enabled = true)
	 public void TC_Hydraulic_STD_STD_DA_Manualadjust_Default_BAR_Pressure_Value_NorFileValue_comparision_with_UI()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.checkBARPressureUnitSelected();
		log.info("Selected Bar Raido button");
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		log.info("Selected STD STD-DA Pump ratio for Hydraulic pressure scaling");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(3000);
		
		// getting the minimum and max value from the UI String
		String pressminVal = rsp.getBARMinSetPoint();
		String pressmaxVal = rsp.getBARMaxSetPoint();
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(3000);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2500);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2500);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2500);
		// Getting the value from RecipeCurrent.XML

		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_Mnuladjt_dflt",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparing the old tags
		Thread.sleep(2000);
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -BAR
		softAssert.assertEquals(pressminVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -BAR
		softAssert.assertEquals(pressmaxVal,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert)));

		// Verification of native tags
		Thread.sleep(2000);
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Op_BAR_Mnladjst_ntv_dflt",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(pressminVal,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative),Constants.STD_STD_DA));
		
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -BAR
		double val1=Precision.round(Double.parseDouble(Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative),Constants.STD_STD_DA)),2);
		String newcnvval=String.valueOf(Precision.round(val1+0.08,2));
		System.out.println(newcnvval);
		softAssert.assertEquals(pressmaxVal,newcnvval);
		softAssert.assertAll();
	}
	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value from downloaded NOR file to UI for PSI Unit STD STD-DA pump ratio")
	@Test(priority = 3, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_Manualadjust_Default_PSI_Pressure_Value_NorFileValue_comparision_with_UI()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectPSIUnit();
		log.info("Selected PSI Raido button");
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		log.info("Selected STD STD-DA Pump ratio for Hydraulic pressure scaling");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(1500);
	
		
		// getting the minimum and max value from the UI String
		String pressminVal = rsp.getPSIMinSetPoint();
		String pressmaxVal = rsp.getPSIMaxSetPoint();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2500);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML and writing the value to excel sheet
		Thread.sleep(2000);
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Mnuladst_dflt",
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
		Thread.sleep(2000);	
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("OP_PSI_Mnladjst_ntv_dflt",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));

		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -PSI
		softAssert.assertEquals(pressminVal,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative),Constants.STD_STD_DA));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -PSI
		softAssert.assertEquals(pressmaxVal,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative),Constants.STD_STD_DA));
		softAssert.assertAll();
	}
	
	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as Electronic adjust for STD STD-DA pump ratio")
	@Description("Verify the All fields with UI value in downloaded NOR file for KPA Unit")
	@Test(priority = 4, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_electronicadjust_NorFileValue_comparision_with_UI_KPA()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectKPAUnit();
		log.info("Selected KPA Raido button");
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		Thread.sleep(2500);

		//passing Values to the electronic adjust fields from Excel and saving
		
		String prspnt=rsp.getPressureSetPoint();
		String lwprsthrshld=rsp.getLowPressureAlertThreshold();
		String hghprsthrshld=rsp.getHighPressureAlertThreshold();
		String minprsstpntrng=rsp.getMinimumPressureSetPointRange();
		String maxprsstpntrng=rsp.getMaximumPressureSetPointRange();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2800);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.PressureSetpoint));

		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_electronicadjust("OP_KPA_electronicadjust_dflt",
				XMLTagConstants.PressureSetpoint, xmlval.XMLParser(XMLTagConstants.PressureSetpoint),
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
		softAssert.assertEquals(prspnt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.PressureSetpoint)));
		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(lwprsthrshld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic)));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(hghprsthrshld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic)));
		//Verification of ulPressureMinSetPoint tag with Pressure Unit as -KPA
		softAssert.assertEquals(minprsstpntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrange)));
		//Verification of ulPressureMaxSetPoint tag with Pressure Unit as -KPA
		softAssert.assertEquals(maxprsstpntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrange)));
		
		// Native tags
		// writing the value to excel sheet

		XLUtils.setNorXMLValues_Pressure_electronicadjust("OP_KPA_elcnicadjt_Ntv_dflt",
				XMLTagConstants.PressureSetpointNative, xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
				XMLTagConstants.LowPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
				XMLTagConstants.highPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
				XMLTagConstants.MinimumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
				XMLTagConstants.MaximumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
	
	//Verification of ulPressureSetPointNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(prspnt,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.PressureSetpointNative)),Constants.STD_STD_DA));
	//Verification of ulLowPressureAlertDeltaNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(lwprsthrshld,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative)),Constants.STD_STD_DA));
		//Verification of ulHighPressureAlertDeltaNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(hghprsthrshld,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative)),Constants.STD_STD_DA));
		//Verification of ulPressureMinSetPointNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(minprsstpntrng,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative)),Constants.STD_STD_DA));
		//Verification of ulPressureMaxSetPointNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(maxprsstpntrng,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative)),Constants.STD_STD_DA));
        softAssert.assertAll();
	}

	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as Electronic adjust for STD STD-DA pump ratio")
	@Description("Verify the All fields with UI value in downloaded NOR file for BAR Unit")
	@Test(priority = 5, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_electronicadjust_NorFileValue_comparision_with_UI_BAR()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.checkBARPressureUnitSelected();
		log.info("Selected BAR Raido button");
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		Thread.sleep(2500);

		//passing Values to the electronic adjust fields from Excel and saving
		
		String prspnt=rsp.getPressureSetPoint();
		String lwprsthrshld=rsp.getLowPressureAlertThreshold();
		String hghprsthrshld=rsp.getHighPressureAlertThreshold();
		String minprsstpntrng=rsp.getMinimumPressureSetPointRange();
		String maxprsstpntrng=rsp.getMaximumPressureSetPointRange();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2800);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.PressureSetpoint));

		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_electronicadjust("OP_BAR_electronicadjust_dflt",
				XMLTagConstants.PressureSetpoint, xmlval.XMLParser(XMLTagConstants.PressureSetpoint),
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
		softAssert.assertEquals(prspnt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.PressureSetpoint)));
		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -BAR
		softAssert.assertEquals(lwprsthrshld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic)));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -BAR
		softAssert.assertEquals(hghprsthrshld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic)));
		//Verification of ulPressureMinSetPoint tag with Pressure Unit as -BAR
		softAssert.assertEquals(minprsstpntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrange)));
		//Verification of ulPressureMaxSetPoint tag with Pressure Unit as -BAR
		softAssert.assertEquals(maxprsstpntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrange)));
		
		// Native tags
		// writing the value to excel sheet

		XLUtils.setNorXMLValues_Pressure_electronicadjust("OP_BAR_elcnicadjt_Ntv_dflt",
				XMLTagConstants.PressureSetpointNative, xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
				XMLTagConstants.LowPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
				XMLTagConstants.highPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
				XMLTagConstants.MinimumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
				XMLTagConstants.MaximumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
	
	//Verification of ulPressureSetPointNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(prspnt,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.PressureSetpointNative)),Constants.STD_STD_DA));
	//Verification of ulLowPressureAlertDeltaNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(lwprsthrshld,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative)),Constants.STD_STD_DA));
		//Verification of ulHighPressureAlertDeltaNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(hghprsthrshld,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative)),Constants.STD_STD_DA));
		//Verification of ulPressureMinSetPointNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(minprsstpntrng,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative)),Constants.STD_STD_DA));
		//Verification of ulPressureMaxSetPointNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(maxprsstpntrng,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic((xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative)),Constants.STD_STD_DA));
        softAssert.assertAll();
	}

	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as Electronic adjust for STD STD-DA pump ratio")
	@Description("Verify the All fields with UI value in downloaded NOR file for PSI Unit")
	@Test(priority = 6, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_electronicadjust_NorFileValue_comparision_with_UI_PSI()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectPSIUnit();
		log.info("Selected PSI Raido button");
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		Thread.sleep(2500);

		//passing Values to the electronic adjust fields from Excel and saving
		
		String prspnt=rsp.getPressureSetPoint();
		String lwprsthrshld=rsp.getLowPressureAlertThreshold();
		String hghprsthrshld=rsp.getHighPressureAlertThreshold();
		String minprsstpntrng=rsp.getMinimumPressureSetPointRange();
		String maxprsstpntrng=rsp.getMaximumPressureSetPointRange();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2800);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.PressureSetpoint));

		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_electronicadjust("OP_PSI_electronicadjust_dflt",
				XMLTagConstants.PressureSetpoint, xmlval.XMLParser(XMLTagConstants.PressureSetpoint),
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
		softAssert.assertEquals(prspnt,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.PressureSetpoint)));
		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -PSI
		softAssert.assertEquals(lwprsthrshld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic)));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -PSI
		softAssert.assertEquals(hghprsthrshld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic)));
		//Verification of ulPressureMinSetPoint tag with Pressure Unit as -PSI
		softAssert.assertEquals(minprsstpntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrange)));
		//Verification of ulPressureMaxSetPoint tag with Pressure Unit as -PSI
		softAssert.assertEquals(maxprsstpntrng,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrange)));
		
		// Native tags
		// writing the value to excel sheet

		XLUtils.setNorXMLValues_Pressure_electronicadjust("OP_PSI_elcnicadjt_Ntv_dflt",
				XMLTagConstants.PressureSetpointNative, xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
				XMLTagConstants.LowPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
				XMLTagConstants.highPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
				XMLTagConstants.MinimumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
				XMLTagConstants.MaximumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
	
	//Verification of ulPressureSetPointNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(prspnt,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.PressureSetpointNative),Constants.STD_STD_DA));
	//Verification of ulLowPressureAlertDeltaNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(lwprsthrshld,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),Constants.STD_STD_DA));
		//Verification of ulHighPressureAlertDeltaNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(hghprsthrshld,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),Constants.STD_STD_DA));
		//Verification of ulPressureMinSetPointNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(minprsstpntrng,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),Constants.STD_STD_DA));
		//Verification of ulPressureMaxSetPointNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(maxprsstpntrng,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative),Constants.STD_STD_DA));
        softAssert.assertAll();
	}

	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as RunUP for STD STD-DA pump ratio")
	@Description("Verify the All fields with UI value in downloaded NOR file for KPA Unit")
	@Test(priority = 7, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_RunUP_NorFileValue_comparision_with_UI_KPA()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on system setting");
		rsp.SelectKPAUnit();
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Runup");
		Thread.sleep(2200);

		// Passing values from excel to Electronic adjust fields and saving 
		
		String lwprssalrtthsld=rsp.getLowPressureAlertThreshold();
		String hghprssalrtthsld=rsp.getHighPressureAlertThreshold();
		String lwspdprssttng=rsp.getLowSpeedPressureSetting();
		String hghspdprsstng=rsp.getHighSpeedPressureSetting();
		String setmaxprsslmt=rsp.getSetMaximumPressureLimit();
		String setminprsslmt=rsp.getSetMinimumPressureLimit();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2500);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML and writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Runup("OP_KPA_Runup_dfltval", XMLTagConstants.LowPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup), XMLTagConstants.HighPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup), XMLTagConstants.LowSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),XMLTagConstants.HighSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),XMLTagConstants.SetmaxPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit), XMLTagConstants.SetminPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));		
	
		// getting the values from the UI String and converting Integer value to string and comparing with XML

		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(lwprssalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup)));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(hghprssalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup)));
		//Verification of usPressureCalPtMin tag with Pressure Unit as -KPA
		softAssert.assertEquals(lwspdprssttng,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),Constants.STD_STD_DA));
		//Verification of usPressureCalPtMax tag with Pressure Unit as -KPA
		softAssert.assertEquals(hghspdprsstng,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),Constants.STD_STD_DA));
		//Verification of usPressureMax tag with Pressure Unit as -KPA
		softAssert.assertEquals(setmaxprsslmt,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit),Constants.STD_STD_DA));
		//Verification of usPressureMin tag with Pressure Unit as -KPA
		softAssert.assertEquals(setminprsslmt,Am.conversion_of_KPA_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.SetminPressurelimit),Constants.STD_STD_DA));
		softAssert.assertAll();
	}
	
	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as RunuP for STD STD-DA pump ratio")
	@Description("Verify the All fields with UI value in downloaded NOR file for BAR Unit")
	@Test(priority = 8, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_RunUP_NorFileValue_comparision_with_UI_BAR()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on system setting");
		rsp.checkBARPressureUnitSelected();
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Runup");
		Thread.sleep(2200);

		// Passing values from excel to Electronic adjust fields and saving 
		
		String lwprssalrtthsld=rsp.getLowPressureAlertThreshold();
		String hghprssalrtthsld=rsp.getHighPressureAlertThreshold();
		String lwspdprssttng=rsp.getLowSpeedPressureSetting();
		String hghspdprsstng=rsp.getHighSpeedPressureSetting();
		String setmaxprsslmt=rsp.getSetMaximumPressureLimit();
		String setminprsslmt=rsp.getSetMinimumPressureLimit();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2500);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML and writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Runup("OP_BAR_Runup_dfltval", XMLTagConstants.LowPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup), XMLTagConstants.HighPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup), XMLTagConstants.LowSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),XMLTagConstants.HighSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),XMLTagConstants.SetmaxPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit), XMLTagConstants.SetminPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));		
	
		// getting the values from the UI String and converting Integer value to string and comparing with XML

		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -BAR
		softAssert.assertEquals(lwprssalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup)));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -BAR
		softAssert.assertEquals(hghprssalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic_BAR(xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup)));
		//Verification of usPressureCalPtMin tag with Pressure Unit as -BAR
		softAssert.assertEquals(lwspdprssttng,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),Constants.STD_STD_DA));
		//Verification of usPressureCalPtMax tag with Pressure Unit as -BAR
		softAssert.assertEquals(hghspdprsstng,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),Constants.STD_STD_DA));
		//Verification of usPressureMax tag with Pressure Unit as -BAR
		softAssert.assertEquals(setmaxprsslmt,Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit),Constants.STD_STD_DA));
		//Verification of usPressureMin tag with Pressure Unit as -BAR
		 if(setminprsslmt.contains(Am.conversion_of_BAR_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.SetminPressurelimit),Constants.STD_STD_DA)))
			  log.info("Set Minimum Pressure limit is set to default value");
			  else
				  log.info("Set Minimum Pressure limit is not set to default value");
	    softAssert.assertAll();
	}

	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as RunuP for STD STD-DA pump ratio")
	@Description("Verify the All fields with UI value in downloaded NOR file for PSI Unit")
	@Test(priority = 9, enabled = true)
	public void TC_Hydraulic_STD_STD_DA_RunUP_NorFileValue_comparision_with_UI_PSI()
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on system setting");
		rsp.SelectPSIUnit();
		rsp.SelectHydaulicDropdown();
		log.info("Selected Hydraulic pressure scaling option from drop down");
		rsp.SelectPumpDropdown("STD STD-DA");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Runup");
		Thread.sleep(2200);

		// Passing values from excel to Electronic adjust fields and saving 
		
		String lwprssalrtthsld=rsp.getLowPressureAlertThreshold();
		String hghprssalrtthsld=rsp.getHighPressureAlertThreshold();
		String lwspdprssttng=rsp.getLowSpeedPressureSetting();
		String hghspdprsstng=rsp.getHighSpeedPressureSetting();
		String setmaxprsslmt=rsp.getSetMaximumPressureLimit();
		String setminprsslmt=rsp.getSetMinimumPressureLimit();
		
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2500);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.NorcopyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML and writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Runup("OP_BAR_Runup_dfltval", XMLTagConstants.LowPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup), XMLTagConstants.HighPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup), XMLTagConstants.LowSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),XMLTagConstants.HighSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),XMLTagConstants.SetmaxPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit), XMLTagConstants.SetminPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));		
	
		// getting the values from the UI String and converting Integer value to string and comparing with XML

		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -PSI
		softAssert.assertEquals(lwprssalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup)));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -PSI
		softAssert.assertEquals(hghprssalrtthsld,Am.conversion_of_Norfile_Value_To_Default_UIval_Pneuamtic(xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup)));
		//Verification of usPressureCalPtMin tag with Pressure Unit as -PSI
		softAssert.assertEquals(lwspdprssttng,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),Constants.STD_STD_DA));
		//Verification of usPressureCalPtMax tag with Pressure Unit as -PSI
		softAssert.assertEquals(hghspdprsstng,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),Constants.STD_STD_DA));
		//Verification of usPressureMax tag with Pressure Unit as -PSI
		softAssert.assertEquals(setmaxprsslmt,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit),Constants.STD_STD_DA));
		//Verification of usPressureMin tag with Pressure Unit as -PSI
		softAssert.assertEquals(setminprsslmt,Am.conversion_of_PSI_NOR_vlaue_TO_UI_Hydraulic(xmlval.XMLParser(XMLTagConstants.SetminPressurelimit),Constants.STD_STD_DA));
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
	  rsp.SelectHydaulicDropdown();
	  log.info("Selected Hydraulic pressure scaling option from drop down");
	  rsp.SelectPumpDropdown("STD STD-DA");
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
	  rsp.SelectLineSpeedminUnit();
	  log.info("Selected LineSpeed min unit Raido button");
	  rsp.SelectHydaulicDropdown();
	  log.info("Selected Hydraulic pressure scaling option from drop down");
	  rsp.SelectPumpDropdown("STD STD-DA");
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
