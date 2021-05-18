package com.nordson.MDS;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Regression Tests")
@Feature("Pressure default,Min & Max Field verification from MDS pressure file")
public class TC_RuntimeSettings_Pressure_MDSValidiations extends TC_LoginTest_DDT_001 {
	Pressure_Min_Max_Validations rsp;
	ActionMethods Am = new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Pressure mdsp = new MDSGetterandSetters_Pressure();
	RetriveMDSdata_Pressure rmdsp = new RetriveMDSdata_Pressure();
	ReadConfig rcf = new ReadConfig();

	@Test(priority = 1, enabled = true)
	@Description("Verify the MDS Values of Manual adjust mode selection for PSI Pressure Unit")
	public void Manualadjust_Pressure_Validation_PSI_MDS_values() throws Exception {

		rsp = new Pressure_Min_Max_Validations(driver);
		rsp.CreatNewNORfile();
		Am.sleepTime(3500);
		Am.drawBorder(rsp.SystemSettingsLnk, driver);
		rsp.ClickSystemSettingsLink();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PreferencesLink, driver);
		rsp.ClickPreferencesLink();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PSIUnit, driver);
		rsp.SelectPSIUnit();
		log.info("Selected PSI Raido button");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.SaveButton, driver);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Preferencessucssmsg);
		} else
			Am.drawBorderFail(rsp.ToastMessage, driver);
		log.info("Saved the preferences");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.RunTimeSettings, driver);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.Pressure, driver);
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Am.sleepTime(2500);
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Am.sleepTime(2500);

		// Verfication of default mds value for Minimum and Maximium Pressure Alert for
		// PSI Unit

		Am.sleepTime(2500);
		Am.drawBorder(rsp.PSIMinSetPoint, driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file  for PSI unit=" + mdsp.getDefault1());
		softAssert.assertEquals(rsp.getPSIMinSetPoint(), mdsp.getDefault1());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for PSI Unit");

		Am.sleepTime(1200);
		Am.drawBorder(rsp.PSIMaxSetPoint, driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
				+ "from MDS Pressure file =" + mdsp.getDefault1());
		softAssert.assertEquals(rsp.getPSIMaxSetPoint(), mdsp.getDefault1());
		log.info("Assertion to verify the maximum set point is equal to MDS default value for PSI Unit");

		// Verfication of min mds value for Minimum Pressure Alert and Maxmium Pressure
		// Alert for PSI Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file for PSI unit =" + mdsp.getMin1());
		rsp.setMinValue(mdsp.getMin1());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
				+ "from MDS Pressure file for PSI unit =" + mdsp.getMin1());
		rsp.setMaxValue(mdsp.getMin1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Pressuresucssmsg);
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out
					.println("Toast Message not displayed and for MDS min val for maximum pressure alert for PSI Unit");

		// Verfication of max mds value for Minimum and Maximum Pressure Alert for PSI
		// Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file for PSI unit =" + mdsp.getMax1());
		rsp.setMinValue(mdsp.getMax1());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMax1());
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
		+ "from MDS Pressure file for `PSI unit =" + mdsp.getMax1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Pressuresucssmsg);
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed for MDS max val for maximum pressure alert for PSI Unit");

		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true)
	@Description("Verify the MDS Values of Manual adjust mode selection for `BAR Pressure Unit")
	public void Manualadjust_Pressure_Validation_BAR_MDS_values() throws Exception {

		Am.sleepTime(3200);
		Am.drawBorder(rsp.Dashboard, driver);
		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		Am.sleepTime(3500);
		Am.drawBorder(rsp.SystemSettingsLnk, driver);
		rsp.ClickSystemSettingsLink();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PreferencesLink, driver);
		rsp.ClickPreferencesLink();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PrefPressureunitSelected, driver);
		rsp.checkBARPressureUnitSelected();
		log.info("Selected BAR Raido button");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.RunTimeSettings, driver);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.Pressure, driver);
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Am.sleepTime(2500);
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Am.sleepTime(2500);

		// Verfication of default mds value for Minimum and Maximium Pressure Alert for
		// `BAR Unit

		Am.sleepTime(2500);
		Am.drawBorder(rsp.BARMinSetPoint, driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file  for `BAR unit=" + mdsp.getDefault2());
		softAssert.assertEquals(rsp.getBARMinSetPoint(), mdsp.getDefault2());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for `BAR Unit");

		Am.sleepTime(2200);
		Am.drawBorder(rsp.BARMaxSetPoint, driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
				+ "from MDS Pressure file =" + mdsp.getDefault2());
		softAssert.assertEquals(rsp.getBARMaxSetPoint(), mdsp.getDefault2());
		log.info("Assertion to verify the maximum set point is equal to MDS default value for `BAR Unit");

		// Verfication of min mds value for Minimum Pressure Alert and Maxmium Pressure
		// Alert for `BAR Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file for `BAR unit =" + mdsp.getMin2());
		rsp.setMinValue(mdsp.getMin2());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
				+ "from MDS Pressure file for `BAR unit =" + mdsp.getMin2());
		rsp.setMaxValue(mdsp.getMin2());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Pressuresucssmsg);
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for maximum pressure alert for `BAR Unit");

		// Verfication of max mds value for Minimum and Maximum Pressure Alert for `BAR
		// Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file for `BAR unit =" + mdsp.getMax2());
		rsp.setMinValue(mdsp.getMax2());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMax2());
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
		+ "from MDS Pressure file for BAR unit =" + mdsp.getMax2());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Pressuresucssmsg);
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed for MDS max val for maximum pressure alert for `BAR Unit");

		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true)
	@Description("Verify the MDS Values of Manual adjust mode selection for `KPA Pressure Unit")
	public void Manualadjust_Pressure_Validation_KPA_MDS_values() throws Exception {
		Am.sleepTime(3300);
		Am.drawBorder(rsp.Dashboard, driver);
		rsp.clickDashboard();
		rsp.CreatNewNORfile();
		Am.sleepTime(3500);
		Am.drawBorder(rsp.SystemSettingsLnk, driver);
		rsp.ClickSystemSettingsLink();
		Am.sleepTime(3500);
		Am.drawBorder(rsp.PreferencesLink, driver);
		rsp.ClickPreferencesLink();
		Am.sleepTime(3500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(3500);
		Am.drawBorder(rsp.KPAUnit, driver);
		rsp.SelectKPAUnit();
		log.info("Selected KPA Raido button");
		Am.sleepTime(3500);
		Am.drawBorder(rsp.SaveButton, driver);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.sleepTime(200);
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Preferencessucssmsg);
		} else
			Am.drawBorderFail(rsp.ToastMessage, driver);
		log.info("Saved the preferences");
		Am.sleepTime(3500);
		Am.drawBorder(rsp.RunTimeSettings, driver);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		Am.sleepTime(3500);
		Am.drawBorder(rsp.Pressure, driver);
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Am.sleepTime(3500);
		rsp.SelectMainPressureModeSelectionDropdown("Manual Adjust");
		Am.sleepTime(3500);

		// Verfication of default mds value for Minimum and Maximium Pressure Alert for
		// `KPA Unit

		Am.sleepTime(3500);
		Am.drawBorder(rsp.MinSetPoint, driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file  for `KPA unit=" + mdsp.getDefault3());
		softAssert.assertEquals(rsp.getkPaMinSetPoint(), mdsp.getDefault3());
		log.info("Assertion to verify the minimum set point is equal to MDS default value for `KPA Unit");

		Am.sleepTime(3300);
		Am.drawBorder(rsp.MaxSetPoint, driver);
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
				+ "from MDS Pressure file =" + mdsp.getDefault3());
		softAssert.assertEquals(rsp.getKpaMaxSetPoint(), mdsp.getDefault3());
		log.info("Assertion to verify the maximum set point is equal to MDS default value for `KPA Unit");

		// Verfication of min mds value for Minimum Pressure Alert and Maxmium Pressure
		// Alert for `KPA Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file for `KPA unit =" + mdsp.getMin3());
		rsp.setMinValue(mdsp.getMin3());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
				+ "from MDS Pressure file for `KPA unit =" + mdsp.getMin3());
		rsp.setMaxValue(mdsp.getMin3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Pressuresucssmsg);
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println(
					"Toast Message not displayed and for MDS min val for maximum pressure alert for `KPA Unit");

		// Verfication of max mds value for Minimum and Maximum Pressure Alert for `KPA
		// Unit
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureAlrt());
		rsp.clearMinSetPoint();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MinimumPressureAlrt()
				+ "from MDS Pressure file for `KPA unit =" + mdsp.getMax3());
		rsp.setMinValue(mdsp.getMax3());

		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureAlrt());
		rsp.clearMaxSetPoint();
		rsp.setMaxValue(mdsp.getMax3());
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MaximumPressureAlrt()
				+ "from MDS Pressure file for `KPA unit =" + mdsp.getMax3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Pressuresucssmsg);
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed for MDS max val for maximum pressure alert for KPA Unit");

		softAssert.assertAll();
	}
	@Test(priority = 4, enabled = true)
	@Description("Verify the MDS Values of Electronic Pressure adjust mode selection for PSI Pressure Unit")
	public void ElectronicPressureadjust_Validation_PSI_MDS_values() throws Exception {
	
		Am.sleepTime(1200);
		Am.drawBorder(rsp.Dashboard, driver);
		rsp.clickDashboard();
		Am.sleepTime(1800);
		rsp.CreatNewNORfile();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.SystemSettingsLnk, driver);
		rsp.ClickSystemSettingsLink();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PreferencesLink, driver);
		rsp.ClickPreferencesLink();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PSIUnit, driver);
		rsp.SelectPSIUnit();
		log.info("Selected PSI Raido button");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.SaveButton, driver);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.sleepTime(200);
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Preferencessucssmsg);
		} else
			Am.drawBorderFail(rsp.ToastMessage, driver);
		log.info("Saved the preferences");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.RunTimeSettings, driver);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.Pressure, driver);
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Am.sleepTime(2500);
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		Am.sleepTime(2500);

		// Validation of Low Pressure Alert Threshold,High Pressure AlertThreshold,Minimum Pressure Set Point Range and Minimum Pressure Set PointRange
		// Default Value validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getDefault1());
		Am.drawBorder(rsp.PressureSetPoint, driver);
		softAssert.assertEquals(rsp.getPressureSetPoint(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getDefault1());
		Am.drawBorder(rsp.LowPressureAlertThreshold, driver);
		softAssert.assertEquals(rsp.getLowPressureAlertThreshold(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getDefault1());
		Am.drawBorder(rsp.HighPressureAlertThreshold, driver);
		softAssert.assertEquals(rsp.getHighPressureAlertThreshold(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getDefault1());
		Am.drawBorder(rsp.MinimumPressureSetPointRange, driver);
		softAssert.assertEquals(rsp.getMinimumPressureSetPointRange(), mdsp.getDefault1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getDefault1());
		Am.drawBorder(rsp.MinimumPressureSetPointRange, driver);
		softAssert.assertEquals(rsp.getMaximumPressureSetPointRange(), mdsp.getDefault1());

		// Min Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMin1());
		rsp.setPressureSetPoint(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMin1());
		rsp.setLowPressureAlertThreshold(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMin1());
		rsp.setHighPressureAlertThreshold(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMin1());
		rsp.setMinimumPressureSetPointRange(mdsp.getMin1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMin1());
		rsp.setMaximumPressureSetPointRange(mdsp.getMin1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.sleepTime(200);
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Pressuresucssmsg);
		} else {
			Am.drawBorderFail(rsp.ToastMessage, driver);
			System.out.println("Toast Message not displayed and for MDS min val for all pressure fields for PSI Unit");}

		// Max Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMax1());
		rsp.setPressureSetPoint(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMax1());
		rsp.setLowPressureAlertThreshold(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMax1());
		rsp.setHighPressureAlertThreshold(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMax1());
		rsp.setMinimumPressureSetPointRange(mdsp.getMax1());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for PSI unit=" + mdsp.getMax1());
		rsp.setMaximumPressureSetPointRange(mdsp.getMax1());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.sleepTime(200);
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.ElctnicadjstPSImaxerrormsg);
			softAssert.assertEquals(true, rsp.saveButtonEnabled());
		} else {
			Am.drawBorderFail(rsp.ToastMessage, driver);
			System.out.println("Toast Message not displayed and for MDS max val for all pressure fields for PSI Unit");}
		softAssert.assertAll();
	}
	@Test(priority = 5, enabled = true)
	@Description("Verify the MDS Values of Electronic Pressure adjust mode selection for BAR Pressure Unit")
	public void ElectronicPressureadjust_Validation_BAR_MDS_values() throws Exception {
	
		Am.sleepTime(1200);
		Am.drawBorder(rsp.Dashboard, driver);
		rsp.clickDashboard();
		rsp.clickAcceptalert();
		Am.sleepTime(1800);
		rsp.CreatNewNORfile();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.SystemSettingsLnk, driver);
		rsp.ClickSystemSettingsLink();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PreferencesLink, driver);
		rsp.ClickPreferencesLink();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.BARUnit, driver);
		rsp.checkBARPressureUnitSelected();
		log.info("Selected BAR Raido button");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.RunTimeSettings, driver);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.Pressure, driver);
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Am.sleepTime(2500);
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		Am.sleepTime(2500);

		// Validation of Low Pressure Alert Threshold,High Pressure AlertThreshold,Minimum Pressure Set Point Range and Minimum Pressure Set PointRange
		// Default Value validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getDefault2());
		Am.drawBorder(rsp.PressureSetPoint, driver);
		softAssert.assertEquals(rsp.getPressureSetPoint(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getDefault2());
		Am.drawBorder(rsp.LowPressureAlertThreshold, driver);
		softAssert.assertEquals(rsp.getLowPressureAlertThreshold(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getDefault2());
		Am.drawBorder(rsp.HighPressureAlertThreshold, driver);
		softAssert.assertEquals(rsp.getHighPressureAlertThreshold(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getDefault2());
		Am.drawBorder(rsp.MinimumPressureSetPointRange, driver);
		softAssert.assertEquals(rsp.getMinimumPressureSetPointRange(), mdsp.getDefault2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getDefault2());
		Am.drawBorder(rsp.MinimumPressureSetPointRange, driver);
		softAssert.assertEquals(rsp.getMaximumPressureSetPointRange(), mdsp.getDefault2());

		// Min Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMin2());
		rsp.setPressureSetPoint(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMin2());
		rsp.setLowPressureAlertThreshold(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMin2());
		rsp.setHighPressureAlertThreshold(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMin2());
		rsp.setMinimumPressureSetPointRange(mdsp.getMin2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMin2());
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
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMax2());
		rsp.setPressureSetPoint(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMax2());
		rsp.setLowPressureAlertThreshold(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMax2());
		rsp.setHighPressureAlertThreshold(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMax2());
		rsp.setMinimumPressureSetPointRange(mdsp.getMax2());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for BAR unit=" + mdsp.getMax2());
		rsp.setMaximumPressureSetPointRange(mdsp.getMax2());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.sleepTime(200);
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.ElctnicadjstBARmaxerrormsg);
			softAssert.assertEquals(true, rsp.saveButtonEnabled());
		} else {
			Am.drawBorderFail(rsp.ToastMessage, driver);
			System.out.println("Toast Message not displayed and for MDS max val for all pressure fields for BAR Unit");}
		softAssert.assertAll();
	}
	
	@Test(priority = 6, enabled = true)
	@Description("Verify the MDS Values of Electronic Pressure adjust mode selection for KPA Pressure Unit")
	public void ElectronicPressureadjust_Validation_KPA_MDS_values() throws Exception {
		Am.sleepTime(3200);
		Am.drawBorder(rsp.Dashboard, driver);
		rsp.clickDashboard();
		Am.sleepTime(3800);
		rsp.clickAcceptalert();
		rsp.CreatNewNORfile();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.SystemSettingsLnk, driver);
		rsp.ClickSystemSettingsLink();
		Am.sleepTime(2500);
		Am.drawBorder(rsp.PreferencesLink, driver);
		rsp.ClickPreferencesLink();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.KPAUnit, driver);
		rsp.SelectKPAUnit();
		log.info("Selected KPA Raido button");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.SaveButton, driver);
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.sleepTime(200);
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.Preferencessucssmsg);
		} else
			Am.drawBorderFail(rsp.ToastMessage, driver);
		log.info("Saved the preferences");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.RunTimeSettings, driver);
		rsp.clickRunTimeSettings();
		log.info("Clicked on Run Time Settings Link");
		Am.sleepTime(2500);
		Am.drawBorder(rsp.Pressure, driver);
		rsp.clickPressure();
		log.info("Clicked on Pressure Link");
		Am.sleepTime(2500);
		rsp.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		Am.sleepTime(2500);

		// Validation of Low Pressure Alert Threshold,High Pressure AlertThreshold,Minimum Pressure Set Point Range and Minimum Pressure Set PointRange
		// Default Value validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getDefault3());
		Am.drawBorder(rsp.PressureSetPoint, driver);
		softAssert.assertEquals(rsp.getPressureSetPoint(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getDefault3());
		Am.drawBorder(rsp.LowPressureAlertThreshold, driver);
		softAssert.assertEquals(rsp.getLowPressureAlertThreshold(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getDefault3());
		Am.drawBorder(rsp.HighPressureAlertThreshold, driver);
		softAssert.assertEquals(rsp.getHighPressureAlertThreshold(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getDefault3());
		Am.drawBorder(rsp.MinimumPressureSetPointRange, driver);
		softAssert.assertEquals(rsp.getMinimumPressureSetPointRange(), mdsp.getDefault3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		System.out.println("Default Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getDefault3());
		Am.drawBorder(rsp.MinimumPressureSetPointRange, driver);
		softAssert.assertEquals(rsp.getMaximumPressureSetPointRange(), mdsp.getDefault3());

		// Min Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMin3());
		rsp.setPressureSetPoint(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMin3());
		rsp.setLowPressureAlertThreshold(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMin3());
		rsp.setHighPressureAlertThreshold(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMin3());
		rsp.setMinimumPressureSetPointRange(mdsp.getMin3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		System.out.println("Minimum Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMin3());
		rsp.setMaximumPressureSetPointRange(mdsp.getMin3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			softAssert.assertEquals(rsp.getToastMessageText(),Constants.Pressuresucssmsg);
			softAssert.assertEquals(false, rsp.saveButtonEnabled());
		} else
			System.out.println("Toast Message not displayed and for MDS min val for all pressure fields for KPA Unit");

		// Max Value Validation
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_PressureSetPoint());
		rsp.clearPressureSetPoint();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_PressureSetPoint()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMax3());
		rsp.setPressureSetPoint(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_LowPressureAlertThreshold());
		rsp.clearLowPressureAlertThreshold();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_LowPressureAlertThreshold()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMax3());
		rsp.setLowPressureAlertThreshold(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_HighPressureAlertThreshold());
		rsp.clearHighPressureAlertThreshold();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_HighPressureAlertThreshold()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMax3());
		rsp.setHighPressureAlertThreshold(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MinimumPressureSetPointRange());
		rsp.clearPressureMinimumSetPointRange();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MinimumPressureSetPointRange()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMax3());
		rsp.setMinimumPressureSetPointRange(mdsp.getMax3());
		rmdsp.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumPressureSetPointRange());
		rsp.clearMaximumPressureSetPointRange();
		System.out.println("Maximum Value of " + rcf.getUIfieldTobefetched_MaximumPressureSetPointRange()
		+ "from MDS Pressure file  for KPA unit=" + mdsp.getMax3());
		rsp.setMaximumPressureSetPointRange(mdsp.getMax3());
		rsp.saveButton();
		if (rsp.toastmessageDisplayed() == true) {
			Am.sleepTime(200);
			Am.drawBorder(rsp.ToastMessage, driver);
			softAssert.assertEquals(rsp.getToastMessageText(), Constants.ElctnicadjstKPAmaxerrormsg);
			softAssert.assertEquals(true, rsp.saveButtonEnabled());
		} else {
			Am.drawBorderFail(rsp.ToastMessage, driver);
			System.out.println("Toast Message not displayed and for MDS max val for all pressure fields for KPA Unit");}
		softAssert.assertAll();
	}
}
