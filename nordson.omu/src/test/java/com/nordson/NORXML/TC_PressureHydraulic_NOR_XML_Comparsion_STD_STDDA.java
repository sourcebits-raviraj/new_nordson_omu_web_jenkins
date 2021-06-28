package com.nordson.NORXML;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.pageObjects.Runtime_Settings_Pressure_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.XLUtils;
import com.nordson.utilities.XMLTagConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_PressureHydraulic_NOR_XML_Comparsion_STD_STDDA extends TC_LoginTest_DDT_001 {

	Pressure_Min_Max_Validations rsp;
	ActionMethods Am = new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	XMLClass xmlval = new XMLClass();

	@Test(priority = 0, enabled = true)
	public void sheetname() {
		XLUtils.setExceltNm("NorfilePressure_Hydraulic_STD_STD-DA");
		XLUtils.setExcelSheetNm("Input_KPA_manualadjust");
	}
    
	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as Manual adjust")
	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value in downloaded NOR file for KPA Unit for STD STD-DA pump ratio")
	@Test(priority = 1, enabled = true, dataProvider = "min_max_Presure_for_norfile_KPA_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Manualadjust_KPA_Pressure_Value_comparision_with_NorFile(String Min, String Max)
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
		rsp.clearMinimumPressureAlert();
		rsp.clearMaximumPressureAlert();
		// fetch the values from excel sheet
		rsp.setMinValue(Min);
		Thread.sleep(1200);
		rsp.setMaxValue(Max);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		}
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
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_KPA_Manualadjust",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparing the old tags
		Thread.sleep(2000);
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Verification of native tags
		Thread.sleep(2000);
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_KPA_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(pressminVal,Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative));
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(pressmaxVal,Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_BAR_manualadjust");
	}

	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value in downloaded NOR file for BAR Unit for STD STD-DA pump ratio")
	@Test(priority = 2, enabled = true, dataProvider = "min_max_Presure_for_norfile_BAR_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Manualadjust_BAR_Pressure_Value_comparision_with_NorFile(String Min, String Max)
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
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(1200);
		rsp.clearMinimumPressureAlert();
		rsp.clearMaximumPressureAlert();
		// fetch the values from excel sheet
		rsp.setMinValue(Min);
		Thread.sleep(1200);
		rsp.setMaxValue(Max);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		}
		// getting the min and max value from the UI String
		String pressminVal = rsp.getBARMinSetPoint();
		String pressmaxVal = rsp.getBARMaxSetPoint();
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
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
		System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_Manualadjust",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		// old tag comparison
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert));
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Verification of native tags
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(pressminVal,Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative));
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(pressmaxVal,Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
		
		softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_PSI_manualadjust");
	}

	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value in downloaded NOR file for PSI Unit for STD STD-DA pump ratio")
	@Test(priority = 3, enabled = true, dataProvider = "min_max_Presure_for_norfile_PSI_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Manualadjust_PSI_Pressure_Value_comparision_with_NorFile(String Min, String Max)
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
		Thread.sleep(1200);
		rsp.clearMinimumPressureAlert();
		rsp.clearMaximumPressureAlert();
		Thread.sleep(1200);
		rsp.setMinValue(Min);
		Thread.sleep(1200);
		rsp.setMaxValue(Max);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		}
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
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Manualadjust",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparison of old tags
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressminVal), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(pressmaxVal), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Verification of native tags
		Thread.sleep(2000);	
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));

		
		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(pressminVal,Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative));
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(pressmaxVal,Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_KPA_electronicadjust");
	}
	
	@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as Electronic adjust for STD STD-DA pump ratio")
	@Description("Verify the All fields with Minimum value in downloaded NOR file for KPA Unit")
	@Test(priority = 4, enabled = true, dataProvider = "Presure_for_norfile_KPA_electronicadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_electronicadjust_KPA_Press_Value_comparision_with_NorFile(String Pressuresetpntmin,
			String lowprssuralrtthsld, String highprssuralrtthsld, String minprssurstpntrng, String maxprssurstpntrng)
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
		Thread.sleep(2000);

		//passing Values to the electronic adjust fields from Excel and saving
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(Pressuresetpntmin);
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(lowprssuralrtthsld);
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(highprssuralrtthsld);
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(minprssurstpntrng);
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(maxprssurstpntrng);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all Electronic pressure fields for KPA Unit");

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
		XLUtils.setNorXMLValues_Pressure_electronicadjust("Output_KPA_electronicadjust",
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
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getPressureSetPoint()), xmlval
				.XMLParser(XMLTagConstants.PressureSetpoint));
		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getLowPressureAlertThreshold()),xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getHighPressureAlertThreshold()),xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic));
		//Verification of ulPressureMinSetPoint tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getMinimumPressureSetPointRange()),xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrange));
		//Verification of ulPressureMaxSetPoint tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getMaximumPressureSetPointRange()),xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrange));
		
		// Native tags
		// writing the value to excel sheet

		XLUtils.setNorXMLValues_Pressure_electronicadjust("Output_KPA_electronicadjust_Ntv",
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
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getPressureSetPoint(),Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.PressureSetpointNative));
	//Verification of ulLowPressureAlertDeltaNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getLowPressureAlertThreshold(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative));
		//Verification of ulHighPressureAlertDeltaNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getHighPressureAlertThreshold(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative));
		//Verification of ulPressureMinSetPointNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getMinimumPressureSetPointRange(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative));
		//Verification of ulPressureMaxSetPointNative tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getMaximumPressureSetPointRange(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
		
        softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_BAR_electronicadjust");
	}

	@Description("Verify the All fields with value in downloaded NOR file for BAR Unit for STD STD-DA pump ratio")
	@Test(priority = 5, enabled = true, dataProvider = "Presure_for_norfile_BAR_electronicadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_electronicadjust_BAR_Press_Value_comparision_with_NorFile(String Pressuresetpntmin,
			String lowprssuralrtthsld, String highprssuralrtthsld, String minprssurstpntrng, String maxprssurstpntrng)
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
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		Thread.sleep(2500);

		// Passing values from excel to all electronic adjust fields and verifying
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(Pressuresetpntmin);
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(lowprssuralrtthsld);
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(highprssuralrtthsld);
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(minprssurstpntrng);
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(maxprssurstpntrng);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all Electronic pressure fields for BAR Unit");

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

		XLUtils.setNorXMLValues_Pressure_electronicadjust("Output_BAR_electronicadjust",
				XMLTagConstants.PressureSetpoint, xmlval.XMLParser(XMLTagConstants.PressureSetpoint),
				XMLTagConstants.LowPressureAlertthsldelctrnic,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic),
				XMLTagConstants.highPressureAlertthsldelctrnic,
				xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic),
				XMLTagConstants.MinimumPressureSetpointrange,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrange),
				XMLTagConstants.MaximumPressureSetpointrange,
				xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrange));
		
		Thread.sleep(2000);
		//Verification of ulPressureSetPoint tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getPressureSetPoint()), xmlval
						.XMLParser(XMLTagConstants.PressureSetpoint));
				//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getLowPressureAlertThreshold()),xmlval
						.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic));
				//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getHighPressureAlertThreshold()),xmlval
						.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic));
				//Verification of ulPressureMinSetPoint tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getMinimumPressureSetPointRange()),xmlval
						.XMLParser(XMLTagConstants.MinimumPressureSetpointrange));
				//Verification of ulPressureMaxSetPoint tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getMaximumPressureSetPointRange()),xmlval
						.XMLParser(XMLTagConstants.MaximumPressureSetpointrange));
				
		
		// Native tags

		// writing the value to excel sheet
        
		XLUtils.setNorXMLValues_Pressure_electronicadjust("Output_BAR_electronicadjust_Ntv",
				XMLTagConstants.PressureSetpointNative, xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
				XMLTagConstants.LowPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
				XMLTagConstants.highPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
				XMLTagConstants.MinimumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
				XMLTagConstants.MaximumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
		
		Thread.sleep(2000);
		//Verification of ulPressureSetPointNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getPressureSetPoint(),Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.PressureSetpointNative));
	//Verification of ulLowPressureAlertDeltaNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getLowPressureAlertThreshold(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative));
		//Verification of ulHighPressureAlertDeltaNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getHighPressureAlertThreshold(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative));
		//Verification of ulPressureMinSetPointNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getMinimumPressureSetPointRange(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative));
		//Verification of ulPressureMaxSetPointNative tag with Pressure Unit as -BAR
		softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getMaximumPressureSetPointRange(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
		softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_PSI_electronicadjust");
	}

	@Description("Verify the All fields with value in downloaded NOR file for PSI Unit for STD STD-DA pump ratio")
	@Test(priority = 6, enabled = true, dataProvider = "Presure_for_norfile_PSI_electronicadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_electronicadjust_PSI_Press_Value_comparision_with_NorFile(String Pressuresetpntmin,
			String lowprssuralrtthsld, String highprssuralrtthsld, String minprssurstpntrng, String maxprssurstpntrng)
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

		// Passing values from excel to Electronic adjust fields and saving 
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(Pressuresetpntmin);
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(lowprssuralrtthsld);
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(highprssuralrtthsld);
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(minprssurstpntrng);
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(maxprssurstpntrng);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all Electronic pressure fields for PSI Unit");
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

		XLUtils.setNorXMLValues_Pressure_electronicadjust("Output_PSI_electronicadjust",
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
		Thread.sleep(2000);
		//Verification of ulPressureSetPoint tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getPressureSetPoint()), xmlval
						.XMLParser(XMLTagConstants.PressureSetpoint));
				//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getLowPressureAlertThreshold()),xmlval
						.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnic));
				//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getHighPressureAlertThreshold()),xmlval
						.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnic));
				//Verification of ulPressureMinSetPoint tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getMinimumPressureSetPointRange()),xmlval
						.XMLParser(XMLTagConstants.MinimumPressureSetpointrange));
				//Verification of ulPressureMaxSetPoint tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getMaximumPressureSetPointRange()),xmlval
						.XMLParser(XMLTagConstants.MaximumPressureSetpointrange));
				
		
		// Native tags

		// writing the value to excel sheet

		XLUtils.setNorXMLValues_Pressure_electronicadjust("Output_PSI_electronicadjust_Ntv",
				XMLTagConstants.PressureSetpointNative, xmlval.XMLParser(XMLTagConstants.PressureSetpointNative),
				XMLTagConstants.LowPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative),
				XMLTagConstants.highPressureAlertthsldelctrnicNative,
				xmlval.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative),
				XMLTagConstants.MinimumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative),
				XMLTagConstants.MaximumPressureSetpointrangeNative,
				xmlval.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
		
		Thread.sleep(2000);
		//Verification of ulPressureSetPointNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getPressureSetPoint(),Constants.STD_STD_DA), xmlval
				.XMLParser(XMLTagConstants.PressureSetpointNative));
	//Verification of ulLowPressureAlertDeltaNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getLowPressureAlertThreshold(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertthsldelctrnicNative));
		//Verification of ulHighPressureAlertDeltaNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getHighPressureAlertThreshold(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.highPressureAlertthsldelctrnicNative));
		//Verification of ulPressureMinSetPointNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getMinimumPressureSetPointRange(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.MinimumPressureSetpointrangeNative));
		//Verification of ulPressureMaxSetPointNative tag with Pressure Unit as -PSI
		softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getMaximumPressureSetPointRange(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.MaximumPressureSetpointrangeNative));
		XLUtils.setExcelSheetNm("Input_KPA_Runup");
		softAssert.assertAll();
	}
	
@Feature("Pressure Scaling as Hydraulic with pump ratio as STD STD-DA and Pressure mode as Runup for STD STD-DA pump ratio")
	@Description("Verify the All fields with Minimum value in downloaded NOR file for KPA Unit")
	@Test(priority = 7, enabled = true, dataProvider = "Presure_for_norfile_KPA_RunUp", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Runup_KPA_Press_Value_comparision_with_NorFile(String lowprssuralrtthsld,
			String highprssuralrtthsld, String lowspdprsssetting, String highspdprsssetting,
			 String setmaximumpresslmt, String setminmumpresslmt)
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
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(lowprssuralrtthsld);
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(highprssuralrtthsld);
		rsp.clearLowSpeedPressureSetting();
		rsp.setLowSpeedPressureSettings(lowspdprsssetting);
		rsp.clearHighSpeedPressureSetting();
		rsp.setHighSpeedPressureSettings(highspdprsssetting);
		rsp.clearSetMaximumPressureLimit();
		rsp.setMaximumPressureLimit(setmaximumpresslmt);
		rsp.clearSetMinimumPressureLimit();
		rsp.setMinimumPressureLimit(setminmumpresslmt);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all Runup pressure fields for PSI Unit");
		
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
		XLUtils.setNorXMLValues_Pressure_Runup("Output_KPA_Runup", XMLTagConstants.LowPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup), XMLTagConstants.HighPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup), XMLTagConstants.LowSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting),XMLTagConstants.HighSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting),XMLTagConstants.SetmaxPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit), XMLTagConstants.SetminPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));		
	
		// getting the values from the UI String and converting Integer value to string and comparing with XML

		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getLowPressureAlertThreshold()), xmlval
				.XMLParser(XMLTagConstants.LowPressureAlertshldRunup));
		//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getHighPressureAlertThreshold()),xmlval
				.XMLParser(XMLTagConstants.HighPressureAlertshldRunup));
		//Verification of usPressureCalPtMin tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getLowSpeedPressureSetting(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.LowSppdPressuresetting));
		//Verification of usPressureCalPtMax tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getHighSpeedPressureSetting(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.HighSppdPressuresetting));
		//Verification of usPressureMax tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getSetMaximumPressureLimit(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.SetmaxPressurelimit));
		//Verification of usPressureMin tag with Pressure Unit as -KPA
		softAssert.assertEquals(Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getSetMinimumPressureLimit(),Constants.STD_STD_DA),xmlval
				.XMLParser(XMLTagConstants.SetminPressurelimit));
		
		softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("Input_BAR_Runup");
	}

	@Feature("Pressure Mode Run UP and pressure scaling as pneumatic")
	@Description("Verify the All fields with Minimum value in downloaded NOR file for BAR Unit for STD STD-DA pump ratio")
	@Test(priority = 8, enabled = true, dataProvider = "Presure_for_norfile_BAR_RunUp", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Runup_BAR_Press_Value_comparision_with_NorFile_RunUpadjust(String lowprssuralrtthsld,
			String highprssuralrtthsld, String lowspdprsssetting,String highspdprsssetting,
		    String setmaximumpresslmt, String setminmumpresslmt)
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
		rsp.SelectMainPressureModeSelectionDropdown("Runup");
		Thread.sleep(2000);

		// Passing values from excel to runup pressure mode fields and saving
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(lowprssuralrtthsld);
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(highprssuralrtthsld);
		rsp.clearLowSpeedPressureSetting();
		rsp.setLowSpeedPressureSettings(lowspdprsssetting);
		rsp.clearHighSpeedPressureSetting();
		rsp.setHighSpeedPressureSettings(highspdprsssetting);
		rsp.clearSetMaximumPressureLimit();
		rsp.setMaximumPressureLimit(setmaximumpresslmt);
		rsp.clearSetMinimumPressureLimit();
		rsp.setMinimumPressureLimit(setminmumpresslmt);
		Thread.sleep(1200);

		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all Runup pressure fields for BAR Unit");

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
		XLUtils.setNorXMLValues_Pressure_Runup("Output_BAR_Runup", XMLTagConstants.LowPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup), XMLTagConstants.HighPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup), XMLTagConstants.LowSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting), XMLTagConstants.HighSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting), XMLTagConstants.SetmaxPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit), XMLTagConstants.SetminPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));
		
		
		
		
		// getting the values from the UI String and converting Integer value to string and comparing with XML
		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getLowPressureAlertThreshold()), xmlval
						.XMLParser(XMLTagConstants.LowPressureAlertshldRunup));
				//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getHighPressureAlertThreshold()),xmlval
						.XMLParser(XMLTagConstants.HighPressureAlertshldRunup));
				//Verification of usPressureCalPtMin tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getLowSpeedPressureSetting(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.LowSppdPressuresetting));
				//Verification of usPressureCalPtMax tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getHighSpeedPressureSetting(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.HighSppdPressuresetting));
				//Verification of usPressureMax tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getSetMaximumPressureLimit(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.SetmaxPressurelimit));
				//Verification of usPressureMin tag with Pressure Unit as -BAR
				softAssert.assertEquals(Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getSetMinimumPressureLimit(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.SetminPressurelimit));
		softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_PSI_Runup");
	}

	@Description("Verify all the fields values in downloaded NOR file for PSI Unit for STD STD-DA pump ratio")
	@Test(priority = 9, enabled = true, dataProvider = "Presure_for_norfile_PSI_RunUp", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Runup_PSI_Press_Value_comparision_with_NorFile_RunUpadjust(String lowprssuralrtthsld,
			String highprssuralrtthsld, String lowspdprsssetting,String highspdprsssetting,
		    String setmaximumpresslmt, String setminmumpresslmt)
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
		rsp.SelectMainPressureModeSelectionDropdown("Runup");
		Thread.sleep(2000);
		// Passing values from excel to runup pressure mode fields and saving
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(lowprssuralrtthsld);
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(highprssuralrtthsld);
		rsp.clearLowSpeedPressureSetting();
		rsp.setLowSpeedPressureSettings(lowspdprsssetting);
		rsp.clearHighSpeedPressureSetting();
		rsp.setHighSpeedPressureSettings(highspdprsssetting);
		rsp.clearSetMaximumPressureLimit();
		rsp.setMaximumPressureLimit(setmaximumpresslmt);
		rsp.clearSetMinimumPressureLimit();
		rsp.setMinimumPressureLimit(setminmumpresslmt);
	
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all RunUp fields for PSI Unit");

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
		XLUtils.setNorXMLValues_Pressure_Runup("Output_PSI_Runup", XMLTagConstants.LowPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.LowPressureAlertshldRunup), XMLTagConstants.HighPressureAlertshldRunup,
				xmlval.XMLParser(XMLTagConstants.HighPressureAlertshldRunup), XMLTagConstants.LowSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowSppdPressuresetting), XMLTagConstants.HighSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighSppdPressuresetting), XMLTagConstants.SetmaxPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetmaxPressurelimit), XMLTagConstants.SetminPressurelimit,
				xmlval.XMLParser(XMLTagConstants.SetminPressurelimit));

		// getting the values from the UI String and converting Integer value to string and comparing with XML
		//Verification of ulLowPressureAlertDelta tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getLowPressureAlertThreshold()), xmlval
						.XMLParser(XMLTagConstants.LowPressureAlertshldRunup));
				//Verification of ulHighPressureAlertDelta tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision(rsp.getHighPressureAlertThreshold()),xmlval
						.XMLParser(XMLTagConstants.HighPressureAlertshldRunup));
				//Verification of usPressureCalPtMin tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getLowSpeedPressureSetting(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.LowSppdPressuresetting));
				//Verification of usPressureCalPtMax tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getHighSpeedPressureSetting(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.HighSppdPressuresetting));
				//Verification of usPressureMax tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getSetMaximumPressureLimit(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.SetmaxPressurelimit));
				//Verification of usPressureMin tag with Pressure Unit as -PSI
				softAssert.assertEquals(Am.conversion_of_PSI_App_vlaue_for_Norfile_comparision_Hydraulic(rsp.getSetMinimumPressureLimit(),Constants.STD_STD_DA),xmlval
						.XMLParser(XMLTagConstants.SetminPressurelimit));
		softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_Runup_LineSpeedft_min");
		
	}
	
	@Description("Verify all the Line Speed fields values in downloaded NOR file for PSI Unit with Line Speed ft/min for STD STD-DA pump ratio")
	@Test(priority = 10, enabled = true, dataProvider = "Presure_for_norfile_RunUp_LineSpeed", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Runup_LineSpeedftpermin_Press_Value_comparision_with_NorFile( String lowlnspdsetting,String highlnspdsettings,String fullscalelnspd)
			throws Exception {
		
		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.checkBARPressureUnitSelected();
		log.info("Selected BAR Raido button");
		rsp.SelectLineSpeedftUnit();
		log.info("Selected ft/min Raido button");
		rsp.SelectHydaulicDropdown();
		rsp.SelectPumpDropdown("STD STD-DA");
		rsp.saveButton();
		log.info("Save the preferences");
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Runup");
		Thread.sleep(2500);

		// Value Validation 
		rsp.clearLowLineSpeedSetting();
		rsp.setLowLineSpeedSettings(lowlnspdsetting);
		rsp.clearHighLineSpeedSetting();
		rsp.setHighLineSpeedSettings(highlnspdsettings);
		rsp.clearFullScaleLineSpeed();
		rsp.setFullScaleLineSpeeds(fullscalelnspd);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all RunUp fields ft/min line speed");
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
		XLUtils.setNorXMLValues_Pressure_Runup_LineSpeed("Output_Runup_Lnspeed_ftmin",XMLTagConstants.LowLineSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowLineSppdPressuresetting), XMLTagConstants.HighLineSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighLineSppdPressuresetting), XMLTagConstants.FullScalelnspeed,
				xmlval.XMLParser(XMLTagConstants.FullScalelnspeed));

		// Comparison of tags
		//Verification of usLinespeedCalPtMin tag with Pressure Line Speed as -ft/min
		softAssert.assertEquals(Am.conversion_LineSpeed_ftpermin(rsp.getLowLineSpeedSetting()), xmlval
				.XMLParser(XMLTagConstants.LowLineSppdPressuresetting));
		//Verification of usLinespeedCalPtMax tag with Pressure Line Speed as -ft/min
		softAssert.assertEquals(Am.conversion_LineSpeed_ftpermin(rsp.getHighLineSpeedSetting()), xmlval
				.XMLParser(XMLTagConstants.HighLineSppdPressuresetting));
		//Verification of ulFullScaleLineSpeed tag with Pressure Line Speed as -ft/min
		softAssert.assertEquals(Am.conversion_LineSpeed_ftpermin(rsp.getFullScaleLineSpeed()), xmlval
				.XMLParser(XMLTagConstants.FullScalelnspeed));
	
		softAssert.assertAll();
		XLUtils.setExcelSheetNm("Input_Runup_LineSpeedm_min");
		
	}
	
	@Description("Verify all the Line Speed fields values in downloaded NOR file with Line Speed m/min for STD STD-DA pump ratio")
	@Test(priority = 11, enabled = true, dataProvider = "Presure_for_norfile_RunUp_LineSpeed", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Hydraulic_STD_STD_DA_Runup_LineSpeedmpermin_Press_Value_comparision_with_NorFile( String lowlnspdsetting,
			String highlnspdsettings,String fullscalelnspd)
			throws Exception {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectPSIUnit();
		log.info("Selected PSI Raido button");
		rsp.SelectLineSpeedminUnit();
		log.info("Selected ft/min Raido button");
		rsp.SelectHydaulicDropdown();
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

		// Value Validation 
		rsp.clearLowLineSpeedSetting();
		rsp.setLowLineSpeedSettings(lowlnspdsetting);
		rsp.clearHighLineSpeedSetting();
		rsp.setHighLineSpeedSettings(highlnspdsettings);
		rsp.clearFullScaleLineSpeed();
		rsp.setFullScaleLineSpeeds(fullscalelnspd);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for all RunUp fields m/min line speed");

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
		XLUtils.setNorXMLValues_Pressure_Runup_LineSpeed("Output_Runup_Lnspeed_mmin",XMLTagConstants.LowLineSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.LowLineSppdPressuresetting), XMLTagConstants.HighLineSppdPressuresetting,
				xmlval.XMLParser(XMLTagConstants.HighLineSppdPressuresetting), XMLTagConstants.FullScalelnspeed,
				xmlval.XMLParser(XMLTagConstants.FullScalelnspeed));

		// Comparsion of tags
		//Verification of usLinespeedCalPtMin tag with Pressure Line Speed as -m/min
				softAssert.assertEquals(Am.conversion_LineSpeed_mpermin(rsp.getLowLineSpeedSetting()), xmlval
						.XMLParser(XMLTagConstants.LowLineSppdPressuresetting));
				//Verification of usLinespeedCalPtMax tag with Pressure Line Speed as -m/min
				softAssert.assertEquals(Am.conversion_LineSpeed_mpermin(rsp.getHighLineSpeedSetting()), xmlval
						.XMLParser(XMLTagConstants.HighLineSppdPressuresetting));
				//Verification of ulFullScaleLineSpeed tag with Pressure Line Speed as -m/min
				softAssert.assertEquals(Am.conversion_LineSpeed_mpermin(rsp.getFullScaleLineSpeed()), xmlval
						.XMLParser(XMLTagConstants.FullScalelnspeed));
				
				softAssert.assertAll();
	}
	
}