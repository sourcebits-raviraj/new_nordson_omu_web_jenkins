package com.nordson.testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.LoginPage;
import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XLUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("N30-1 Pressure - OMU R2.5")
@Story("N30-2 Pressure Manual Adjust screen - Default values")
public class TC_Pressure_Min_Max_Data_Validations extends TC_LoginTest_DDT_001 {
	LoginPage lp;
	Pressure_Min_Max_Validations pmv;
	ActionMethods Am = new ActionMethods();

	private SoftAssert softAssert = new SoftAssert();

	@Test(priority = 1, enabled = true)
	// @Feature("Pressure Validation Clear KPA Min data and save")
	public void Pressure_Validation_Kpa_Clear_Data_Min_Save() throws Exception {

		pmv = new Pressure_Min_Max_Validations(driver);
		log.info("Clicked on Set Up Tool Link");
		pmv.clickSetUpToolLink();
		Thread.sleep(1000);

		log.info("Clicked on Create New .NOR file button");
		pmv.clickCreateNewFile();

		log.info("Added Description");
		pmv.addDescription();

		log.info("Submitted NOR file");
		pmv.clickSubmit();

		log.info("Clicked on System Setting Link");
		pmv.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		pmv.ClickPreferencesLink();

		log.info("Selected Kpa Raido button");
		pmv.SelectKPAUnit();

		log.info("Save the preferences");
		pmv.saveButton();

		log.info("Clicked on Run Time Settings Link");
		pmv.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		pmv.clickPressure();

		log.info("clear the value in minimum set point kpa");
		pmv.clearMinSetPoint();

		log.info("Save the pressure settings");
		pmv.saveButton();

		softAssert.assertEquals(true, pmv.saveButtonEnabled());

	}

	@Test(priority = 2, enabled = true)
	// @Feature("Pressure Validation Clear KPA max data and save")
	public void Pressure_Validation_Kpa_Clear_Data_Max_Save() throws Exception {

		log.info("clear the value in max set point kpa");
		pmv.clearMaxSetPoint();

		log.info("Save the pressure settings");
		pmv.saveButton();

		softAssert.assertEquals(true, pmv.saveButtonEnabled());
		XLUtils.setExcelSheetNm("kpaMinMaxSameValues");
	}

	@Test(priority = 3, enabled = true, dataProvider = "PressureValuesMinMax0_0", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit Values Min and Max 0 and 0")
	public void PressureValues_Kpa_Min_0_Max_0(String min, String max) throws Exception {

		// pmv = new Pressure_Min_Max_Validations(driver);

		log.info("Set the min value as 0");
		pmv.setMinValue(min);

		log.info("Set the max value as 0");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Maximum Pressure Alert should be between 34 and 690 kPa")) {
			System.out.println("Pressure value 0 and 0 not accepted");
			softAssert.assertEquals(true, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Pressure value 0 and 0 , Pressure values should not accept 0 and 0");

		}

		XLUtils.setExcelSheetNm("kpaMin_0_Max_691");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Test(priority = 4, enabled = true, dataProvider = "PressureValuesMinMax_0_691", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit Values Min and Max 0 and 691")
	public void PressureValues_Kpa_Min_0_Max_691(String min, String max) throws Exception {

		// pmv = new Pressure_Min_Max_Validations(driver);

		log.info("Set the min value as 0");
		pmv.setMinValue(min);

		log.info("Set the max value 691");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Maximum Pressure Alert should be between 34 and 690 kPa")) {
			System.out.println("Pressure value 0 and 691 not accepted");
			softAssert.assertEquals(true, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Pressure value 0 and 691, Pressure values should not accept 0 and 0");

		}
		XLUtils.setExcelSheetNm("kpaMin_0_Max_690");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5, enabled = true, dataProvider = "PressureValuesMinMax_0_690", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit Values Min and Max 0 and 690")
	public void PressureValues_Kpa_Min_0_Max_690(String min, String max) throws  Exception {

		// pmv = new Pressure_Min_Max_Validations(driver);

		log.info("Set the min value as 0");
		pmv.setMinValue(min);

		log.info("Set the max value 690");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Pressure updated successfully")) {
			System.out.println("Pressure value 0 and 690 accepted");
			softAssert.assertEquals(true, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Pressure value 0 and 690 not accepted");

		}
	}

	// ==================================================================

	@Test(priority = 6, enabled = true)
	// @Feature("Pressure Unit clear Data for Min field and Save")
	public void Pressure_Validation_BAR_Clear_Data_Min_Save() throws Exception{

		log.info("Clicked on System Setting Link");
		pmv.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		pmv.ClickPreferencesLink();

		log.info("Selected Kpa Raido button");
		pmv.SelectBARUnit();

		log.info("Save the preferences");
		pmv.saveButton();

		log.info("Clicked on Run Time Settings Link");
		pmv.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		pmv.clickPressure();

		log.info("clear the value in minimum set point BAR");
		pmv.clearMinSetPoint();

		log.info("Save the pressure settings");
		pmv.saveButton();

		softAssert.assertEquals(true, pmv.saveButtonEnabled());

	}

	@Test(priority = 7, enabled = true)
	// @Feature("Pressure Unit clear data for Max field")
	public void Pressure_Validation_BAR_Clear_Data_Max_Save() throws Exception {

		log.info("clear the value in max set point BAR");
		pmv.clearMaxSetPoint();

		log.info("Save the pressure settings");
		pmv.saveButton();

		softAssert.assertEquals(true, pmv.saveButtonEnabled());
		XLUtils.setExcelSheetNm("BARMinMaxSameValues");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Test(priority = 8, enabled = true, dataProvider = "PressureValues_BAR_MinMax_7_7", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit Values Min and Max 7 and 7 and save it")
	public void PressureValues_BAR_Same_Min_Max(String min, String max) throws Exception {

		// pmv = new Pressure_Min_Max_Validations(driver);

		log.info("Set the min value as 7 BAR");
		pmv.setMinValue(min);

		log.info("Set the max value as 7 BAR");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Minimum Pressure Alert should be between 0 and 6.55 BAR")) {
			System.out.println("BAR Pressure value 7 and 7 not accepted");
			softAssert.assertEquals(true, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Pressure value 7 and 7 accepted");

		}

		XLUtils.setExcelSheetNm("BAR_Min_1.9_Max_2");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Test(priority = 9, enabled = true, dataProvider = "PressureValuesMinMax_1.9_2", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit Values Min and Max 1.9 and 2")
	public void PressureValues_Min_19_Max_2(String min, String max) throws Exception {

		// pmv = new Pressure_Min_Max_Validations(driver);

		log.info("Set the min value as 1.9BAR");
		pmv.setMinValue(min);

		log.info("Set the max value 2 BAR");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		if (pmv.toastmessageDisplayed() == true) {
			System.out.println("Toast Message is displayed" + pmv.toastmessageDisplayed());
			// softAssert.assertEquals(true, pmv.saveButtonEnabled());
			String originalTitle = pmv.getToastMessageText();
			softAssert.assertEquals(originalTitle, "Pressure Alert range should have a minimum difference of 0.34 BAR");
		}

		else {

			System.out.println("Toast Message not displayed and Values 1.9 and 2 not got accepted");

		}
		XLUtils.setExcelSheetNm("BAR_Min_0_Max_6.9");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 10, enabled = true, dataProvider = "PressureValues_MinMax_0_6.9", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit Values Min and Max 0 and 6.9")
	public void PressureValuesMin_0_Max_690(String min, String max) throws Exception {

		// pmv = new Pressure_Min_Max_Validations(driver);

		log.info("Set the min value as 0 BAR");
		pmv.setMinValue(min);

		log.info("Set the max value 6.9 BAR");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Pressure updated successfully")) {
			System.out.println("Pressure value 0 and 6.9 accepted");

			softAssert.assertEquals(false, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Maximum 6.9 & Minimum Pressure 0 alert not accepted");

		}
	}

// -------------------------------------------------------------------------------

	@Test(priority = 11, enabled = true)
	// @Feature("Pressure Unit clear data in Min and save")
	public void Pressure_Validation_PSI_Clear_Data_Min_Save() throws Exception {

		log.info("Clicked on System Setting Link");
		pmv.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		pmv.ClickPreferencesLink();

		log.info("Selected PSI Raido button");
		pmv.SelectPSIUnit();

		log.info("Save the preferences");
		pmv.saveButton();

		log.info("Clicked on Run Time Settings Link");
		pmv.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		pmv.clickPressure();

		log.info("clear the value in minimum set point PSI");
		pmv.clearMinSetPoint();

		log.info("Save the pressure settings");
		pmv.saveButton();

		softAssert.assertEquals(true, pmv.saveButtonEnabled());

	}

	@Test(priority = 12, enabled = true)
	// @Feature("Pressure Unit clear data in Max and save")
	public void Pressure_Validation_PSI_Clear_Data_Max_Save() throws Exception {

		log.info("clear the value in max set point PSI");
		pmv.clearMaxSetPoint();

		log.info("Save the pressure settings");
		pmv.saveButton();

		softAssert.assertEquals(true, pmv.saveButtonEnabled());
		XLUtils.setExcelSheetNm("PSIMinMaxSameValues");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Test(priority = 13, enabled = true, dataProvider = "PressureValues_PSI_MinMax_50_51", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit values Min 50 and Max 51")
	public void PressureValues_PSI_Same_Min_Max(String min, String max) throws Exception {

		log.info("Set the min value as 50 PSI");
		pmv.setMinValue(min);

		log.info("Set the max value as 51 PSI");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Pressure Alert range should have a minimum difference of 5 PSI")) {
			System.out.println("BAR Pressure value 50 and 51 not accepted");
			softAssert.assertEquals(true, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Pressure value 50 and 51 accepted");

		}

		XLUtils.setExcelSheetNm("PSI_Min_Max_0_101");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 13, enabled = true, dataProvider = "PressureValues_PSI_MinMax_0_101", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit values Min 0 and Max 101")
	public void PressureValues_PSI_Min_Max_0_101(String min, String max) throws Exception {

		log.info("Set the min value as 0 PSI");
		pmv.setMinValue(min);

		log.info("Set the max value as 101 PSI");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Maximum Pressure Alert should be between 5 and 100 PSI")) {
			System.out.println("BAR Pressure value 0 and 101 not accepted");
			softAssert.assertEquals(true, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Pressure value 0 and 101 accepted");

		}

		XLUtils.setExcelSheetNm("PSI_Min_Max_0_100");
		pmv.clearMinSetPoint();
		pmv.clearMaxSetPoint();
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 14, enabled = true, dataProvider = "PressureValues_PSI_MinMax_0_100", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("Pressure Unit values Min 0 and Max 100")
	public void PressureValues_PSI_Min_Max_0_100(String min, String max) throws Exception {

		log.info("Set the min value as 0 PSI");
		pmv.setMinValue(min);

		log.info("Set the max value as 100 PSI");
		pmv.setMaxValue(max);

		log.info("Save the pressure settings");
		pmv.saveButton();

		// verify the login is successful
		if (driver.getPageSource().contains("Pressure updated successfully")) {
			System.out.println("BAR Pressure value 0 and 100 accepted");
			softAssert.assertEquals(false, pmv.saveButtonEnabled());
		}

		else {

			System.out.println("Pressure value 0 and 100 not accepted");

		}
		Thread.sleep(3000);

	}

}