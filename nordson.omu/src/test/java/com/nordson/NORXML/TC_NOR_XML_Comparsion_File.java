package com.nordson.NORXML;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XLUtils;
import com.nordson.utilities.XMLTagConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_NOR_XML_Comparsion_File extends TC_LoginTest_DDT_001 {

	Pressure_Min_Max_Validations rsp;
	ActionMethods Am = new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	XMLClass xmlval = new XMLClass();

	@Test(priority = 0, enabled = true)
	public void sheetname() {
		XLUtils.setExcelSheetNm("Input_Minmax_KPA_manualadjust");
	}

	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value in downloaded NOR file for KPA Unit")
	@Test(priority = 1, enabled = true, dataProvider = "min_max_Presure_for_norfile_KPA_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void Min_Max_Pressure_Value_comparision_with_NorFile_KPA_Manualadjust(String Min, String Max)
			throws InterruptedException, IOException, SAXException, InvalidFormatException {
		rsp = new Pressure_Min_Max_Validations(driver);
		rsp.CreatNewNORfile();
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
		String pressminVal = rsp.getkPaMinSetPoint();
		String pressmaxVal = rsp.getKpaMaxSetPoint();
		// converting Integer value to string
		Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal);
		Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal);
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2500);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.copyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2000);
		// Getting the value from RecipeCurrent.XML

		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_KPA_Manualadjust",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparing the old tags
		if (Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal) == xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)
				&& Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal) == xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlert)) {
			Assert.assertTrue(true, "Pressure value successfully updated to nor file");
		}

		// Verfication of native tags
		Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal);
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_KPA_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));

		if (Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal) == xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative)
				&& Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal) == xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlertNative)) {
			Assert.assertTrue(true, "Pressure value successfully updated to nor file");
		}
		XLUtils.setExcelSheetNm("Input_Minmax_BAR_manualadjust");
		Thread.sleep(3000);
	}

	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value in downloaded NOR file for BAR Unit")
	@Test(priority = 2, enabled = true, dataProvider = "min_max_Presure_for_norfile_BAR_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void Min_Max_Pressure_Value_comparision_with_NorFile_BAR_Manualadjust(String Min, String Max)
			throws InterruptedException, IOException, SAXException {

		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		log.info("Clicked on system setting");
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(2000);
		rsp.clearMinSetPoint();
		rsp.clearMaxSetPoint();
		rsp.setMinValue(Min);
		// fetch the values from excel sheet
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
		// converting Integer value to string
		Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal);
		Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal);
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2000);
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.copyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2500);
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
		System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_Manualadjust",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		// old tag comparsion
		if (Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal) == xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)
				&& Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal) == xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlert)) {
			Assert.assertTrue(true, "Pressure value successfully updated to nor file for BAR Unit");
		}

		// Verfication of native tags
		Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal);
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_BAR_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));

		if (Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal) == xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative)
				&& Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal) == xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlertNative)) {
			Assert.assertTrue(true, "Pressure value successfully updated to nor file");
		}
		XLUtils.setExcelSheetNm("Input_Minmax_PSI_manualadjust");
		Thread.sleep(3000);
	}

	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert value in downloaded NOR file for PSI Unit")
	@Test(priority = 3, enabled = true, dataProvider = "min_max_Presure_for_norfile_PSI_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void Min_Max_Pressure_Value_comparision_with_NorFile_PSI_Manualadjust(String Min, String Max)
			throws InterruptedException, IOException, SAXException {

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
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Thread.sleep(1200);
		rsp.clearMinSetPoint();
		rsp.clearMaxSetPoint();
		Thread.sleep(1200);
		rsp.setMinValue(Min);
		rsp.setMaxValue(Max);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		}
		// getting the min and max value from the UI String
		String pressminVal = rsp.getPSIMinSetPoint();
		String pressmaxVal = rsp.getPSIMaxSetPoint();
		// converting Integer value to string
		Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal);
		Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal);
		// Downloading the .Nor file
		rsp.clickdownload();
		Thread.sleep(2000);
		// Storing the downloaded file to the projec tlocation and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		Thread.sleep(2000);
		Am.copyFile(flnm);
		String newfilename = Am.removeSpaces(flnm);
		Thread.sleep(2000);
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(2500);
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
		System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_Min_Max_PSI_Manualadjust",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparsion of old tags
		if (Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal) == xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert)
				&& Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal) == xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlert)) {
			Assert.assertTrue(true, "Pressure value successfully updated to nor file");
		}

		// Verfication of native tags
		Am.conversion_of_KPA_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal);
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Mnladjst_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));

		if (Am.conversion_of_BAR_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal) == xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative)
				&& Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal) == xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlertNative)) {
			Assert.assertTrue(true, "Pressure value successfully updated to nor file");
		}
		XLUtils.setExcelSheetNm("Input_KPA_electronicadjust");
		Thread.sleep(3000);
	}


}
