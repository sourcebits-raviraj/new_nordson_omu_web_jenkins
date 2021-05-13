package com.nordson.MDS;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.ReadConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Regression Tests")
@Feature("Pressure default,Min & Max Field verification from MDS pressure file")
public class TC_RuntimeSettings_Pressure_MDS_Validations extends TC_LoginTest_DDT_001 {
	Pressure_Min_Max_Validations rsp;
	ActionMethods Am;
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Pressure mdsp = new MDSGetterandSetters_Pressure();
	RetriveMDSdata_Pressure rmdsp = new RetriveMDSdata_Pressure();
	ReadConfig rcf = new ReadConfig();

	@Test(priority = 1, enabled = true)
	@Description("Verify the MDS Values of Manual adjust mode selection for PSI Pressure Unit")
	public void Manualadjust_Pressure_Validation_PSI_MDS_values() throws Exception {
		
		rsp = new Pressure_Min_Max_Validations(driver);
		rsp.CreatNewNORfile();
		Am.sleepTime(1500);
		Am.drawBorder(rsp.SystemSettingsLink,driver);
		rsp.ClickSystemSettingsLink();
		Am.sleepTime(1500);
		Am.drawBorder(rsp.PreferencesLink,driver);
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		Am.sleepTime(1500);
		Am.drawBorder(rsp.PSIUnit,driver);
		rsp.SelectPSIUnit();
		log.info("Selected PSI Raido button");
		Am.sleepTime(1500);
		Am.drawBorder(rsp.SaveButton,driver);
		rsp.saveButton();
		log.info("Save the preferences");
		Am.drawBorder(rsp.ToastMessage,driver);
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		Am.sleepTime(1500);
		Am.drawBorder(rsp.RunTimeSettings,driver);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		Am.sleepTime(1500);
		Am.drawBorder(rsp.Pressure,driver);
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Am.sleepTime(2000);
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Am.sleepTime(2000);
		
		// Verfication of default mds value for Minimum and Maximium Pressure Alert for PSI Unit
		
		Am.sleepTime(1500);
		Am.drawBorder(rsp.MinSetPoint,driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		softAssert.assertEquals(rsp.getPSIMinSetPoint(), mdsp.getDefault1());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for PSI Unit");
		
		Am.sleepTime(1200);
		Am.drawBorder(rsp.MaxSetPoint,driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		softAssert.assertEquals(rsp.getPSIMaxSetPoint(), mdsp.getDefault1());
		log.info("Assertion to verify the maximum set point is equal to MDS default value for PSI Unit");

		// Verfication of min mds value for Minimum Pressure Alert and Maxmium Pressure
		// Alert for PSI Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		rsp.setMinValue(mdsp.getMin1());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMin1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out
					.println("Toast Message not displayed and for MDS min val for maximum pressure alert for PSI Unit");

		// Verfication of max mds value for Minimum and Maximum Pressure Alert for PSI
		// Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		rsp.setMinValue(mdsp.getMax1());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMax1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed for MDS max val for maximum pressure alert for PSI Unit");

		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true)

	@Description("Verify the MDS Values of Manual adjust mode selection for BAR Pressure Unit")
	public void Manualadjust_Pressure_Validation_BAR_MDS_values() throws InterruptedException, IOException {

		Thread.sleep(1200);
		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectBARUnit();
		log.info("Selected BAR Raido button");
		Thread.sleep(1000);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");

		// Verfication of default mds value for Minimum and Maximium Pressure Alert for
		// BAR Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		softAssert.assertEquals(rsp.getBARMinSetPoint(), mdsp.getDefault2());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for BAR Unit");
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		softAssert.assertEquals(rsp.getBARMaxSetPoint(), mdsp.getDefault2());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for BAR Unit");

		// Verfication of min mds value for Minimum Pressure Alert and Maxmium Pressure
		// Alert for BAR Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		rsp.setMinValue(mdsp.getMin2());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMin2());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out
					.println("Toast Message not displayed and for MDS min val for maximum pressure alert for BAR Unit");

		// Verfication of max mds value for Minimum and Maximum Pressure Alert for BAR
		// Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		rsp.setMinValue(mdsp.getMax2());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMax2());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed for MDS max val for minimum pressure alertfor BAR Unit");
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true)

	@Description("Verify the MDS Values of Manual adjust mode selection for KPA Pressure Unit")
	public void Manualadjust_Pressure_Validation_KPA_MDS_values() throws InterruptedException, IOException {
		Thread.sleep(1200);
		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectKPAUnit();
		log.info("Selected KPA Raido button");
		rsp.saveButton();
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");

		// Verfication of default mds value for Minimum and Maximium Pressure Alert for
		// KPA Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		softAssert.assertEquals(rsp.getkPaMinSetPoint(), mdsp.getDefault3());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for KPA Unit");
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		softAssert.assertEquals(rsp.getKpaMaxSetPoint(), mdsp.getDefault3());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for KPA Unit");

		// Verfication of min mds value for Minimum Pressure Alert and Maxmium Pressure
		// Alert for KPA Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		rsp.setMinValue(mdsp.getMin3());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMin3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out
					.println("Toast Message not displayed and for MDS min val for maximum pressure alert for KPA Unit");

		// Verfication of max mds value for Minimum and Maximum Pressure Alert for KPA
		// Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		rsp.setMinValue(mdsp.getMax3());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMax3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed for MDS max val for maximum pressure alert for KPA Unit");
		softAssert.assertAll();
	}

	@Test(priority = 4, enabled = true)

	@Description("Verify the MDS Values of Electronic Pressure adjust mode selection for PSI Pressure Unit")
	public void ElectronicPressureadjust_Validation_PSI_MDS_values() throws InterruptedException, IOException {

		Thread.sleep(1200);
		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectPSIUnit();
		log.info("Selected PSI Raido button");
		rsp.saveButton();
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Thread.sleep(1200);
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");

		// Validation of Low Pressure Alert Threshold,High Pressure
		// AlertThreshold,Minimum Pressure Set Point Range and Minimum Pressure Set
		// PointRange
		// Default Value validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		softAssert.assertEquals(rsp.getPressureSetPoint(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		softAssert.assertEquals(rsp.getLowPressureAlertThreshold(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		softAssert.assertEquals(rsp.getHighPressureAlertThreshold(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		softAssert.assertEquals(rsp.getMinimumPressureSetPointRange(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		softAssert.assertEquals(rsp.getMaximumPressureSetPointRange(), mdsp.getDefault1());

		// Min Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(mdsp.getMin1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed and for MDS min val for all pressure fields for PSI Unit"); // Max
																														// Value
																														// Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(mdsp.getMax1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(),
					"Minimum and Maximum Pressure Set Point Range difference should be minimum 5 PSI");
			softAssert.assertEquals(true, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed and for MDS max val for all pressure fields for PSI Unit");
		softAssert.assertAll();
	}

	@Test(priority = 5, enabled = true)

	@Description("Verify the MDS Values of Electronic Pressure adjust mode selection for BAR Pressure Unit")
	public void ElectronicPressureadjust_Validation_BAR_MDS_values() throws InterruptedException, IOException {
		Thread.sleep(1200);
		rsp.clickDashboard();
		rsp.clickAcceptalert();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectBARUnit();
		log.info("Selected BAR Raido button");
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");

		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");

		// Validation of Low Pressure Alert Threshold,High Pressure Alert
		// Threshold,Minimum Pressure Set Point Range and Minimum Pressure Set Point
		// Range
		// Default Value validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		softAssert.assertEquals(rsp.getPressureSetPoint(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		softAssert.assertEquals(rsp.getLowPressureAlertThreshold(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		softAssert.assertEquals(rsp.getHighPressureAlertThreshold(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		softAssert.assertEquals(rsp.getMinimumPressureSetPointRange(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		softAssert.assertEquals(rsp.getMaximumPressureSetPointRange(), mdsp.getDefault2()); // Min Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(mdsp.getMin2());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed and for MDS min val for all pressure fields for BAR Unit");

		// Max Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(mdsp.getMax2());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(),
					"Minimum and Maximum Pressure Set Point Range difference should be minimum 0.34 BAR");
			softAssert.assertEquals(true, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed and for MDS max val for all pressure fields for BAR Unit");
		softAssert.assertAll();
	}

	@Test(priority = 6, enabled = true)
	@Description("Verify the MDS Values of Electronic Pressure adjust mode selection for KPA Pressure Unit")
	public void ElectronicPressureadjust_Validation_KPA_MDS_values() throws InterruptedException, IOException {
		Thread.sleep(1200);
		rsp.clickDashboard();
		rsp.clickAcceptalert();
		rsp.CreatNewNORfile();
		rsp.ClickSystemSettingsLink();
		rsp.ClickPreferencesLink();
		log.info("Clicked on Preferences Link");
		rsp.SelectKPAUnit();
		log.info("Selected KPA Raido button");
		rsp.saveButton();
		softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Thread.sleep(1200);
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");

		// Validation of Low Pressure Alert Threshold,High Pressure
		// AlertThreshold,Minimum Pressure Set Point Range and Minimum Pressure Set
		// Point Range
		// Default Value validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		softAssert.assertEquals(rsp.getPressureSetPoint(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		softAssert.assertEquals(rsp.getLowPressureAlertThreshold(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		softAssert.assertEquals(rsp.getHighPressureAlertThreshold(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		softAssert.assertEquals(rsp.getMinimumPressureSetPointRange(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		softAssert.assertEquals(rsp.getMaximumPressureSetPointRange(), mdsp.getDefault3());
		// Min Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(mdsp.getMin3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), "Pressure updated successfully");
			softAssert.assertEquals(rsp.saveButtonEnabled(), false);
		} else
			System.out.println("Toast Message not displayed and for MDS min val for all pressure fields for KPA Unit");
		// Max Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		rsp.setPressureSetPoint(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		rsp.setLowPressureAlertThreshold(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		rsp.setHighPressureAlertThreshold(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		rsp.setMinimumPressureSetPointRange(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		rsp.setMaximumPressureSetPointRange(mdsp.getMax3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(),
					"Minimum and Maximum Pressure Set Point Range difference should be minimum 34 kPa");
			softAssert.assertEquals(true, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed and for MDS max val for all pressure fields for KPA Unit");
		softAssert.assertAll();
	}

	/*
	 * @Test(priority = 7, enabled = true)
	 * 
	 * @Description("Verify the MDS Values of Runup mode selection for PSI Pressure Unit"
	 * ) public void RunUp_Pressure_Validation_PSI_MDS_values() throws
	 * InterruptedException, IOException { Thread.sleep(1200); rsp.clickDashboard();
	 * rsp.clickAcceptalert(); rsp.CreatNewNORfile(); rsp.ClickSystemSettingsLink();
	 * rsp.ClickPreferencesLink(); log.info("Clicked on Preferences Link");
	 * rsp.SelectPSIUnit(); log.info("Selected PSI Raido button"); rsp.saveButton();
	 * softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
	 * rsp.clickRunTimeSettings(); log.info("Clicked on Run Time Settings Link");
	 * rsp.clickPressure(); log.info("Clicked on Pressure Link");
	 * Thread.sleep(1200); rsp.SelectMainPressureModeSelectionDropdown("Runup");
	 * 
	 * // Validation of Pressure mode runup mode fields // Default value validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * softAssert.assertEquals(rsp.getLowPressureAlertThreshold(),
	 * mdsp.getDefault1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * softAssert.assertEquals(rsp.getHighPressureAlertThreshold(),
	 * mdsp.getDefault1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * softAssert.assertEquals(rsp.getLowSpeedPressureSetting(),
	 * mdsp.getDefault1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * softAssert.assertEquals(rsp.getHighSpeedPressureSetting(),
	 * mdsp.getDefault1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * softAssert.assertEquals(rsp.getSetMaximumPressureLimit(),
	 * mdsp.getDefault1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * softAssert.assertEquals(rsp.getSetMinimumPressureLimit(),
	 * mdsp.getDefault1()); // Min Value Validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * rsp.clearLowPressureAlertThreshold();
	 * rsp.setLowPressureAlertThreshold(mdsp.getMin1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * rsp.clearHighPressureAlertThreshold();
	 * rsp.setHighPressureAlertThreshold(mdsp.getMin1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * rsp.clearLowSpeedPressureSetting();
	 * rsp.setLowSpeedPressureSettings(mdsp.getMin1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * rsp.clearHighSpeedPressureSetting();
	 * rsp.setHighSpeedPressureSettings(mdsp.getMin1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * rsp.clearSetMaximumPressureLimit();
	 * rsp.setMaximumPressureLimit(mdsp.getMin1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * rsp.clearSetMinimumPressureLimit();
	 * rsp.setMinimumPressureLimit(mdsp.getMin1()); rsp.saveButton(); if
	 * (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS min val for all pressure fields for PSI Unit"
	 * ); // Max Value Validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * rsp.clearLowPressureAlertThreshold();
	 * rsp.setLowPressureAlertThreshold(mdsp.getMax1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * rsp.clearHighPressureAlertThreshold();
	 * rsp.setHighPressureAlertThreshold(mdsp.getMax1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * rsp.clearLowSpeedPressureSetting();
	 * rsp.setLowSpeedPressureSettings(mdsp.getMax1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * rsp.clearHighSpeedPressureSetting();
	 * rsp.setHighSpeedPressureSettings(mdsp.getMax1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * rsp.clearSetMaximumPressureLimit();
	 * rsp.setMaximumPressureLimit(mdsp.getMax1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * rsp.clearSetMinimumPressureLimit();
	 * rsp.setMinimumPressureLimit(mdsp.getMax1()); rsp.saveButton(); if
	 * (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS max val for all pressure fields for PSI Unit"
	 * ); softAssert.assertAll(); }
	 * 
	 * @Test(priority = 8, enabled = true)
	 * 
	 * @Description("Verify the MDS Values of Runup mode selection for BAR Pressure Unit"
	 * ) public void RunUp_Pressure_Validation_BAR_MDS_values() throws
	 * InterruptedException, IOException { Thread.sleep(1200); rsp.clickDashboard();
	 * rsp.CreatNewNORfile(); rsp.ClickSystemSettingsLink();
	 * rsp.ClickPreferencesLink(); log.info("Clicked on Preferences Link");
	 * rsp.SelectBARUnit(); log.info("Selected BAR Raido button");
	 * rsp.clickRunTimeSettings(); log.info("Clicked on Run Time Settings Link");
	 * rsp.clickPressure(); log.info("Clicked on Pressure Link");
	 * Thread.sleep(2200); rsp.SelectMainPressureModeSelectionDropdown("Runup");
	 * 
	 * // Validation of Pressure mode runup mode fields // Default value validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * softAssert.assertEquals(rsp.getLowPressureAlertThreshold(),
	 * mdsp.getDefault2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * softAssert.assertEquals(rsp.getHighPressureAlertThreshold(),
	 * mdsp.getDefault2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * softAssert.assertEquals(rsp.getLowSpeedPressureSetting(),
	 * mdsp.getDefault2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * softAssert.assertEquals(rsp.getHighSpeedPressureSetting(),
	 * mdsp.getDefault2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * if(rsp.getSetMaximumPressureLimit().equals(mdsp.getDefault2())||rsp.
	 * getSetMaximumPressureLimit().equals("6.89"))
	 * log.info("SetMaximumPressureLimit is set to default value"); else
	 * System.out.println("SetMaximumPressureLimit is not set to default value");
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * softAssert.assertEquals(rsp.getSetMinimumPressureLimit(),
	 * mdsp.getDefault2()); // Min Value Validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * rsp.clearLowPressureAlertThreshold();
	 * rsp.setLowPressureAlertThreshold(mdsp.getMin2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * rsp.clearHighPressureAlertThreshold();
	 * rsp.setHighPressureAlertThreshold(mdsp.getMin2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * rsp.clearLowSpeedPressureSetting();
	 * rsp.setLowSpeedPressureSettings(mdsp.getMin2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * rsp.clearHighSpeedPressureSetting();
	 * rsp.setHighSpeedPressureSettings(mdsp.getMin2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * rsp.clearSetMaximumPressureLimit();
	 * rsp.setMaximumPressureLimit(mdsp.getMin2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * rsp.clearSetMinimumPressureLimit();
	 * rsp.setMinimumPressureLimit(mdsp.getMin2()); rsp.saveButton(); if
	 * (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS min val for all pressure fields for BAR Unit"
	 * ); // Max Value Validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * rsp.clearLowPressureAlertThreshold();
	 * rsp.setLowPressureAlertThreshold(mdsp.getMax2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * rsp.clearHighPressureAlertThreshold();
	 * rsp.setHighPressureAlertThreshold(mdsp.getMax2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * rsp.clearLowSpeedPressureSetting();
	 * rsp.setLowSpeedPressureSettings(mdsp.getMax2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * rsp.clearHighSpeedPressureSetting();
	 * rsp.setHighSpeedPressureSettings(mdsp.getMax2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * rsp.clearSetMaximumPressureLimit();
	 * rsp.setMaximumPressureLimit(mdsp.getMax2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * rsp.clearSetMinimumPressureLimit();
	 * rsp.setMinimumPressureLimit(mdsp.getMax2()); rsp.saveButton(); if
	 * (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS max val for all pressure fields for BAR Unit"
	 * ); softAssert.assertAll(); }
	 * 
	 * @Test(priority = 9, enabled = true)
	 * 
	 * @Description("Verify the MDS Values of Runup mode selection for KPA Pressure Unit"
	 * ) public void RunUp_Pressure_Validation_kPa_MDS_values() throws
	 * InterruptedException, IOException { Thread.sleep(1200); rsp.clickDashboard();
	 * rsp.CreatNewNORfile(); rsp.ClickSystemSettingsLink();
	 * rsp.ClickPreferencesLink(); log.info("Clicked on Preferences Link");
	 * Thread.sleep(2000); rsp.SelectKPAUnit();
	 * log.info("Selected kPa Raido button"); rsp.saveButton();
	 * softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
	 * rsp.clickRunTimeSettings(); log.info("Clicked on Run Time Settings Link");
	 * rsp.clickPressure(); log.info("Clicked on Pressure Link");
	 * Thread.sleep(3200); rsp.SelectMainPressureModeSelectionDropdown("Runup");
	 * 
	 * // Validation of Pressure mode runup mode fields // Default value validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * softAssert.assertEquals(rsp.getLowPressureAlertThreshold(),
	 * mdsp.getDefault3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * softAssert.assertEquals(rsp.getHighPressureAlertThreshold(),
	 * mdsp.getDefault3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * softAssert.assertEquals(rsp.getLowSpeedPressureSetting(),
	 * mdsp.getDefault3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * softAssert.assertEquals(rsp.getHighSpeedPressureSetting(),
	 * mdsp.getDefault3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * if(rsp.getSetMaximumPressureLimit().equals(mdsp.getDefault3())||rsp.
	 * getSetMaximumPressureLimit().equals("689"))
	 * log.info("SetMaximumPressureLimit is set to default value for kPa unit");
	 * else System.out.
	 * println("SetMaximumPressureLimit is not set to default value for kPa unit");
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * softAssert.assertEquals(rsp.getSetMinimumPressureLimit(),
	 * mdsp.getDefault3()); // Min Value Validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * rsp.clearLowPressureAlertThreshold();
	 * rsp.setLowPressureAlertThreshold(mdsp.getMin3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * rsp.clearHighPressureAlertThreshold();
	 * rsp.setHighPressureAlertThreshold(mdsp.getMin3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * rsp.clearLowSpeedPressureSetting();
	 * rsp.setLowSpeedPressureSettings(mdsp.getMin3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * rsp.clearHighSpeedPressureSetting();
	 * rsp.setHighSpeedPressureSettings(mdsp.getMin3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * rsp.clearSetMaximumPressureLimit();
	 * rsp.setMaximumPressureLimit(mdsp.getMin3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * rsp.clearSetMinimumPressureLimit();
	 * rsp.setMinimumPressureLimit(mdsp.getMin3()); rsp.saveButton(); if
	 * (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS min val for all pressure fields for kPa Unit"
	 * ); // Max Value Validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
	 * rsp.clearLowPressureAlertThreshold();
	 * rsp.setLowPressureAlertThreshold(mdsp.getMax3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
	 * rsp.clearHighPressureAlertThreshold();
	 * rsp.setHighPressureAlertThreshold(mdsp.getMax3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowSpeedPressureSetting());
	 * rsp.clearLowSpeedPressureSetting();
	 * rsp.setLowSpeedPressureSettings(mdsp.getMax3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighSpeedPressureSetting());
	 * rsp.clearHighSpeedPressureSetting();
	 * rsp.setHighSpeedPressureSettings(mdsp.getMax3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMaximumPressureLimit());
	 * rsp.clearSetMaximumPressureLimit();
	 * rsp.setMaximumPressureLimit(mdsp.getMax3());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_SetMinimumPressureLimit());
	 * rsp.clearSetMinimumPressureLimit();
	 * rsp.setMinimumPressureLimit(mdsp.getMax3()); rsp.saveButton(); if
	 * (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS max val for all pressure fields for kPa Unit"
	 * ); softAssert.assertAll(); }
	 * 
	 * @Test(priority = 10, enabled = true)
	 * 
	 * @Description("Verify the MDS Values of Runup mode selection for Line speed ft/min Pressure Unit"
	 * ) public void RunUp_Pressure_LineSpeedftunit_Validation_MDS_values() throws
	 * InterruptedException, IOException {
	 * 
	 * Thread.sleep(1200); //rsp=new Pressure_Min_Max_Validations(driver);
	 * rsp.clickDashboard(); rsp.CreatNewNORfile(); rsp.ClickSystemSettingsLink();
	 * rsp.ClickPreferencesLink(); log.info("Clicked on Preferences Link");
	 * rsp.SelectLineSpeedftUnit(); log.info("Selected Line Speed Raido button");
	 * rsp.saveButton(); softAssert.assertEquals(rsp.toastmessageDisplayed(), true);
	 * rsp.clickRunTimeSettings(); log.info("Clicked on Run Time Settings Link");
	 * rsp.clickPressure(); log.info("Clicked on Pressure Link");
	 * Thread.sleep(1200); rsp.SelectMainPressureModeSelectionDropdown("Runup"); //
	 * Validation of Pressure mode runup mode fields
	 * LowlineSpeedSetting,HighlineSpeedSetting and Fullscalelinespeed //Default
	 * value validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowLineSpeedSetting());
	 * softAssert.assertEquals(rsp.getLowLineSpeedSetting(), mdsp.getDefault1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighLineSpeedSetting());
	 * softAssert.assertEquals(rsp.getHighLineSpeedSetting(), mdsp.getDefault1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_FullScaleLineSpeed());
	 * softAssert.assertEquals(rsp.getFullScaleLineSpeed(), mdsp.getDefault2()); //
	 * Min val validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowLineSpeedSetting());
	 * rsp.clearLowLineSpeedSetting(); rsp.setLowLineSpeedSettings(mdsp.getMin1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighLineSpeedSetting());
	 * rsp.clearHighLineSpeedSetting();
	 * rsp.setHighLineSpeedSettings(mdsp.getMin1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_FullScaleLineSpeed());
	 * rsp.clearFullScaleLineSpeed(); rsp.setFullScaleLineSpeeds(mdsp.getMin2());
	 * rsp.saveButton(); if (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS min val for all pressure fields for Linespeed ft/min Unit"
	 * ); rsp.isDisplayedDownardSlopingRunupcurvecontent(); //Max validation
	 * Thread.sleep(1200);
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowLineSpeedSetting());
	 * rsp.clearLowLineSpeedSetting(); rsp.setLowLineSpeedSettings(mdsp.getMax1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighLineSpeedSetting());
	 * rsp.clearHighLineSpeedSetting();
	 * rsp.setHighLineSpeedSettings(mdsp.getMax1());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_FullScaleLineSpeed());
	 * rsp.clearFullScaleLineSpeed(); rsp.setFullScaleLineSpeeds(mdsp.getMax2());
	 * rsp.saveButton(); if (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS max val for all pressure fields for Linespeed ft/min Unit"
	 * );
	 * 
	 * softAssert.assertAll(); }
	 * 
	 * @Test(priority = 11, enabled = true)
	 * 
	 * @Description("Verify the MDS Values of Runup mode selection for Line speed m/min Pressure Unit"
	 * ) public void RunUp_Pressure_LineSpeedminunit_Validation_MDS_values() throws
	 * InterruptedException, IOException { rsp.clickDashboard();
	 * rsp.CreatNewNORfile(); rsp.ClickSystemSettingsLink();
	 * rsp.ClickPreferencesLink(); log.info("Clicked on Preferences Link");
	 * rsp.SelectLineSpeedminUnit(); log.info("Selected Line Speed Raido button");
	 * rsp.clickRunTimeSettings(); log.info("Clicked on Run Time Settings Link");
	 * rsp.clickPressure(); log.info("Clicked on Pressure Link");
	 * Thread.sleep(2200); rsp.SelectMainPressureModeSelectionDropdown("Runup"); //
	 * Validation of Pressure mode runup mode fields
	 * LowlineSpeedSetting,HighlineSpeedSetting and Fullscalelinespeed //Default
	 * value validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowLineSpeedSetting());
	 * softAssert.assertEquals(rsp.getLowLineSpeedSetting(), mdsp.getDefault2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighLineSpeedSetting());
	 * softAssert.assertEquals(rsp.getHighLineSpeedSetting(), mdsp.getDefault2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_FullScaleLineSpeed());
	 * if(rsp.getFullScaleLineSpeed().equals(mdsp.getDefault1())||rsp.
	 * getFullScaleLineSpeed().equals("100.0"))
	 * log.info("FullScaleLineSpeed is set to default value for m/min unit"); else
	 * System.out.
	 * println("FullScaleLineSpeed is not set to default value for m/min unit"); //
	 * Min val validations
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowLineSpeedSetting());
	 * rsp.clearLowLineSpeedSetting(); rsp.setLowLineSpeedSettings(mdsp.getMin2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighLineSpeedSetting());
	 * rsp.clearHighLineSpeedSetting();
	 * rsp.setHighLineSpeedSettings(mdsp.getMin2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_FullScaleLineSpeed());
	 * rsp.clearFullScaleLineSpeed(); rsp.setFullScaleLineSpeeds(mdsp.getMin1());
	 * rsp.saveButton(); if (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS min val for all pressure fields for Linespeed m/min Unit"
	 * ); rsp.isDisplayedDownardSlopingRunupcurvecontent(); //Max validation
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowLineSpeedSetting());
	 * rsp.clearLowLineSpeedSetting(); rsp.setLowLineSpeedSettings(mdsp.getMax2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighLineSpeedSetting());
	 * rsp.clearHighLineSpeedSetting();
	 * rsp.setHighLineSpeedSettings(mdsp.getMax2());
	 * rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_FullScaleLineSpeed());
	 * rsp.clearFullScaleLineSpeed(); rsp.setFullScaleLineSpeeds(mdsp.getMax1());
	 * rsp.saveButton(); if (rsp.toastmessageDisplayed() == true) {
	 * softAssert.assertEquals(rsp.getToastMessageText(),
	 * "Pressure updated successfully");
	 * softAssert.assertEquals(rsp.saveButtonEnabled(), false); } else System.out.
	 * println("Toast Message not displayed and for MDS max val for all pressure fields for Linespeed m/min Unit"
	 * ); softAssert.assertAll(); }
	 */
}
