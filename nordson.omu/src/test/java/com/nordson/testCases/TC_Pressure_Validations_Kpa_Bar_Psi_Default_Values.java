package com.nordson.testCases;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Runtime_Settings_Pressure_Validations;
import com.nordson.utilities.ActionMethods;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("N30-1 Pressure - OMU R2.5")
@Story("N30-2 Pressure Min & Max Field verification of default Values")
public class TC_Pressure_Validations_Kpa_Bar_Psi_Default_Values extends TC_LoginTest_DDT_001 {

	Runtime_Settings_Pressure_Validations rsp;
	ActionMethods Am=new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();

	@Test(priority = 1, enabled = true)
	@Description("Verify the Default Values for Kpa Pressure Unit")
	public void Pressure_Validation_Kpa_default_values() throws Exception {

		rsp = new Runtime_Settings_Pressure_Validations(driver);

		log.info("Clicked on Set Up Tool Link");
		rsp.clickSetUpToolLink();

		log.info("Clicked on Create New .NOR file button");
		rsp.clickCreateNewFile();

		log.info("Added Description");
		rsp.addDescription();
		Am.sleepTime(2000);
		
		log.info("Submitted NOR file");
		rsp.clickSubmit();

		log.info("Clicked on System Setting Link");
		rsp.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		rsp.ClickPreferencesLink();
		Am.sleepTime(4000);

		log.info("Selected Kpa Raido button");
		rsp.SelectKPAUnit();

		log.info("Save the preferences");
		rsp.saveButton();

		log.info("Clicked on Run Time Settings Link");
		rsp.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		rsp.clickPressure();

		log.info("Assertion to verify the minimum set point is equal to 103");
		softAssert.assertEquals("103", rsp.getkPaMinSetPoint());

		log.info("Assertion to verify the minimum set point is equal to 690");
		softAssert.assertEquals("690", rsp.getKpaMaxSetPoint());

		log.info("Assertion to verify Kpa text is displayed");
		softAssert.assertEquals(true, rsp.getKPaText());

	}

	@Test(priority = 2, enabled = true)
	@Description("Verify the Default Values for BAR Pressure Unit")
	public void Pressure_Validation_BAR_default_values() throws InterruptedException, IOException {

		log.info("Clicked on System Setting Link");
		rsp.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		rsp.ClickPreferencesLink();

		log.info("Selected BAR Raido button");
		rsp.SelectBARUnit();

		log.info("Save the preferences");
		rsp.saveButton();

		log.info("Clicked on Run Time Settings Link");
		rsp.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		rsp.clickPressure();

		log.info("Assertion to verify the minimum BAR set point is equal to 1.03");
		softAssert.assertEquals("1.03", rsp.getBARMinSetPoint());

		log.info("Assertion to verify the maximum BAR set point is equal to 6.9");
		softAssert.assertEquals("6.9", rsp.getBARMaxSetPoint());

		log.info("Assertion to verify BAR text is displayed");
		softAssert.assertEquals(true, rsp.getBARText(), "BAR");

	}

	@Test(priority = 3, enabled = true)
	@Description("Verify the Default Values for PSI Pressure Unit")
	public void Pressure_Validation_PSI_default_values() throws InterruptedException, IOException {

		log.info("Clicked on System Setting Link");
		rsp.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		rsp.ClickPreferencesLink();

		log.info("Selected PSI Raido button");
		rsp.SelectPSIUnit();

		log.info("Save the preferences");
		rsp.saveButton();

		log.info("Clicked on Run Time Settings Link");
		rsp.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		rsp.clickPressure();

		log.info("Assertion to verify the minimum psi set point is equal to 15");
		softAssert.assertEquals("15", rsp.getPSIMinSetPoint());

		log.info("Assertion to verify the maximum psi set point is equal to 100");
		softAssert.assertEquals("100", rsp.getPSIMaxSetPoint());

		log.info("Assertion to verify psi text is displayed");
		softAssert.assertEquals(true, rsp.getPSIText(), "PSI");

	}

	@Test(priority = 4, enabled = true)
	@Description("Verify the Default Values for KPA Pressure Unit with Hydraulic pressure scaling and Pump Ratio-STD STD-DA")
	public void Pressure_Validation_Kpa_Default_Values_with_Hydraulic_Pump_Ratio_STD_STD_DA()
			throws InterruptedException, IOException {

		log.info("Clicked on System Setting Link");
		rsp.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		rsp.ClickPreferencesLink();

		log.info("Selected Kpa Raido button");
		rsp.SelectKPAUnit();

		log.info("Selected Hydraulic from the Pressure Scaling Dropdown");
		rsp.SelectHydaulicDropdown();

		log.info("Save the preferences");
		rsp.saveButton();

		log.info("Clicked on Run Time Settings Link");
		rsp.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		rsp.clickPressure();

		log.info("Assertion to verify the minimum set point with hyrdraulic is equal to 1545");
		softAssert.assertEquals("1545", rsp.getkPaMinSetPoint());

		log.info("Assertion to verify the minimum set point with hyrdraulic is equal to 10342");
		softAssert.assertEquals("10342", rsp.getKpaMaxSetPoint());

		log.info("Assertion to verify Kpa text is displayed");
		softAssert.assertEquals(true, rsp.getKPaText());

	}

	@Test(priority = 5, enabled = true)
	@Description("Verify the Default Values for BAR Pressure Unit with Hydraulic pressure scaling and Pump Ratio-STD STD-DA")
	public void Pressure_Validation_BAR_Default_Values_with_Hydraulic_Pump_Ratio_STD_STD_DA()
			throws InterruptedException, IOException {

		log.info("Clicked on System Setting Link");
		rsp.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		rsp.ClickPreferencesLink();

		log.info("Selected BAR Raido button");
		rsp.SelectBARUnit();

		// log.info("Selected Hydraulic from the Pressure Scaling Dropdown");
		// rsp.SelectHydaulicDropdown();

		log.info("Save the preferences");
		rsp.saveButton();

		log.info("Clicked on Run Time Settings Link");
		rsp.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		rsp.clickPressure();

		log.info("Assertion to verify the minimum BAR set point with hyrdraulic is equal to 15.45");
		softAssert.assertEquals("15.45", rsp.getBARMinSetPoint());

		log.info("Assertion to verify the maximum BAR set point with hyrdraulic is equal to 103.42");
		softAssert.assertEquals("103.42", rsp.getBARMaxSetPoint());

		log.info("Assertion to verify BAR text is displayed");
		softAssert.assertEquals(true, rsp.getBARText(), "BAR");

	}

	@Test(priority = 6, enabled = true)
	@Description("Verify the Default Values for PSI Pressure Unit with Hydraulic pressure scaling and Pump Ratio-STD STD-DA")
	public void Pressure_Validation_PSI_Default_Values_with_Hydraulic_Pump_Ratio_STD_STD_DA()
			throws InterruptedException, IOException {

		log.info("Clicked on System Setting Link");
		rsp.ClickSystemSettingsLink();

		log.info("Clicked on Preferences Link");
		rsp.ClickPreferencesLink();

		log.info("Selected PSI Raido button");
		rsp.SelectPSIUnit();

		log.info("Save the preferences");
		rsp.saveButton();

		log.info("Clicked on Run Time Settings Link");
		rsp.clickRunTimeSettings();

		log.info("Clicked on Pressure Link");
		rsp.clickPressure();

		log.info("Assertion to verify the minimum psi set point with hyrdraulic is equal to 224");
		softAssert.assertEquals("224", rsp.getPSIMinSetPoint());

		log.info("Assertion to verify the maximum psi set point with hyrdraulic is equal to 1500");
		softAssert.assertEquals("1500", rsp.getPSIMaxSetPoint());

		log.info("Assertion to verify psi text is displayed");
		softAssert.assertEquals(true, rsp.getPSIText(), "PSI");

	}

}
