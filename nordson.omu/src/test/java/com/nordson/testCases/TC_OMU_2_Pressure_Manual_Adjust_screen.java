package com.nordson.testCases;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.LoginPage;
import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.JiraPolicy;
import com.nordson.utilities.XLUtils;

public class TC_OMU_2_Pressure_Manual_Adjust_screen extends TC_LoginTest_DDT_001 {
	LoginPage lp;
	Pressure_Min_Max_Validations pvm;
	ActionMethods Am= new ActionMethods();

	private SoftAssert softAssert = new SoftAssert();

	@JiraPolicy(logTicketReady = false)
	@Test(priority = 1, enabled = true)
	// @Feature("")
	public void TC_OMU_4_OMU_359_pressureModuleVerification() throws Exception {

		pvm = new Pressure_Min_Max_Validations(driver);
		log.info("driver instantiated");

		pvm.clickSetUpToolLink();
		log.info("setup tool link clicked");

		pvm.clickCreateNewFile();
		log.info("create new file clicked");

		pvm.clickSubmit();
		log.info("submit button clicked");

		pvm.clickPressure();
		log.info("pressure clicked");

		
		if (pvm.pressureAlertTogglebuttonStatus().equals("true")) {

			log.info("By default Pressure Setting Range Alert toggle is enabled");
		} else {
			log.info("Pressure Setting Range Alert toggle is not enabled");
		}

	}

	@Test(priority = 2, enabled = true)
	public void TC_OMU_363_verificationOfTheTogglebarbyDisablingit() throws Exception {
		{
			//Thread.sleep(3000);
			
			if (pvm.pressureAlertTogglebuttonStatus().equals("true")) {

				pvm.clickPressureToggle();
				log.info("successfully Toggle button is disabled");
			}
			else {
				log.info("Toggle button is not getting disable when toggle bar is enable");
			}

			pvm.saveButton();
			log.info("Save button is clicked");

			// Thread.sleep(1000);
			if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
		}
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 3, enabled = true)
	public void TC_OMU_362_verificationOfTheTogglebarbyenablingit() throws Exception {

		Thread.sleep(3000);

		if (pvm.pressureAlertTogglebuttonStatus().equals("false")) {

			pvm.clickPressureToggle();
			log.info("successfully Toggle button is enabled when toggle bar is disabled");
		} else {
			log.info("Toggle button is not getting enabled when toggle bar is disable");
		}

		pvm.saveButton();
		log.info("Save button is clicked");

		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");

		}

		else {
			softAssert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

		softAssert.assertAll();
	}

	// Minimum Pressure LIMIT
	
	@Test(priority = 4, enabled = true)
	public void verificationOfSaveButtonOnDefaultValuePSI() throws Exception {
		//Am.sleepTime(1000);
		pvm.ClickSystemSettingsLink();
		log.info("System Settings link clicked");

		pvm.ClickPreferencesLink();
		log.info("Preferences link clicked");

		pvm.SelectPSIUnit();
		log.info("PSIUnit selected");

		pvm.saveButton();
		log.info("save Button clicked");

		pvm.clickRunTimeSettings();
		log.info("RunTime Settings link clicked");

		pvm.clickPressure();
		log.info("Pressure link clicked");
		
		if(pvm.getPSIMinSetPoint().equals(Constants.PSIMinsetpointdefaultValue)&& pvm.getPSIMaxSetPoint().equals(Constants.PSIMaxsetpointdefaultValue)) {
		    
			Assert.assertEquals(false, pvm.saveButtonEnabled());
			
			log.info("save Button Disabled for default value");
		
		}
		else
		{
			Assert.assertTrue(true);
			log.info("save Button enabled");
		}
	  }
		@Test(priority = 5, enabled = true)
		public void TC_OMU_365_verificationOfMinPressureSetPointLimitBelowRangePSI() throws Exception {
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");

		pvm.saveButton();
		log.info("save Button clicked after clearing the field");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MinpresssetptPSIerrormsg);
			log.info("Minimum Pressure Alert should be between 0 and 95 PSI is displyed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckPSIMinValue");
	}

	@Test(priority = 6, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMinValuePSI(String min) throws Exception {
		//Thread.sleep(3000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");

		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 0");

		}

		else {
			
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckPSImin+1");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 7, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMorethanMinValuePSI(String min) throws Exception {
		//Thread.sleep(3000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 1");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckPSImax");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 8, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMaxValuePSI(String min) throws Exception {
		//Thread.sleep(3000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 95 ");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckPSImax-1");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 9, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitlessthanMaxValuePSI(String min) throws Exception {
		//Thread.sleep(3000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 94");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("PSIAboveRange");
		pvm.clearMinSetPoint();

	}

	// XLUtils.setExcelSheetNm("PSIAboveRange");
	@Test(priority = 10, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_365_verificationOfMinPressureSetPointLimitAboveRangePSI(String min) throws Exception {
		//Thread.sleep(3000);
		

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MinpresssetptPSIerrormsg);
			log.info("Minimum Pressure Alert should be between 0 and 95 PSI is displyed");

		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("PSIMidValue");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 11, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_366_verificationOfMinPressureSetPointLimitMidvaluePSI(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		XLUtils.setExcelSheetNm("PSImaxbelowRange");
	}
	
	@Test(priority = 12, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_370_verificationOfMaxPressureSetPointLimitBelowRangePSI(String max) throws Exception {
		//Thread.sleep(3000);

		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after clearing the field");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MaxpresssetptPSIerrormsg);
			log.info("Maximum Pressure Alert should be between 5 and 100 PSI is displyed");

		}
		else
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("maxcheckPSIMin");
		
		pvm.clearMinSetPoint();
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 13, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMinValuePSI(String min, String max) throws Exception {
		//Thread.sleep(2000);
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 5");

		}

		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckPSIMin+1");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 14, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMorethanMinValuePSI(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 6");

		}

		else
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckPSIMax");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 15, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMaxValuePSI(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 100");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

		XLUtils.setExcelSheetNm("maxcheckPSIMax-1");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 16, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitLessthanMaxValuePSI(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit value 99");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("PSImaxAboveRange");
		pvm.clearMaxSetPoint();

	}

	@Test(priority = 17, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_370_verificationOfMaxPressureSetPointLimitAboveRangePSI(String max) throws Exception {
		//Thread.sleep(3000);

		pvm.setMaxValue(max);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MaxpresssetptPSIerrormsg);
			log.info("Maximum Pressure Alert should be between 5 and 100 PSI is displyed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("PSImaxInRange");
		pvm.clearMaxSetPoint();

	}

	@Test(priority = 18, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_371_verificationOfMaxPressureSetPointLimitWithinRangePSI(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		//XLUtils.setExcelSheetNm("KPAmaxbelowRange");
		XLUtils.setExcelSheetNm("maxlessminPSI");

	}
	@Test(priority = 19, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressurelessthanMinPressurePSI(String min, String max) throws Exception {
		//Thread.sleep(2000);
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Maxsetptbelowminsetpterrormsg);
			log.info("Minimum Pressure Alert Range should be less than Maximum Pressure Alert Range message displyed");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("MaxMinsamePSI");

	}

	@Test(priority = 20, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressureSamePSI(String min, String max) throws Exception {
		//Thread.sleep(2000);
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Maxsetptbelowminsetpterrormsg);
			log.info("Minimum Pressure Alert Range should be less than Maximum Pressure Alert Range message displyed");

		}
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxmindiffPSI");

	}

	@Test(priority = 21, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressuredifferencePSI(String min, String max) throws Exception {
		//Thread.sleep(2000);
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.PressalrtrangediffPSIerrormsg);
			log.info("Pressure Alert range should have a minimum difference of 5 PSI message displyed");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxvaluePSI");

	}

	@Test(priority = 22, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressurediffvaluePSI(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for PSI Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		XLUtils.setExcelSheetNm("DefaultvaluePSI");
	}
		@Test(priority = 23, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
		public void verificationOfDefaultvaluePSI(String min,String max) throws Exception {
			//Thread.sleep(2000);
			pvm.clearMinSetPoint();
			log.info("Min SetPoint field Cleared");
			
			pvm.setMinValue(min);
			log.info("Min SetPoint value provided");
			
			pvm.clearMaxSetPoint();
			log.info("Max SetPoint field Cleared");
			
			pvm.setMaxValue(max);
			log.info("Max SetPoint value provided");
			
			pvm.saveButton();
			log.info("save Button clicked after value provided");
			Am.sleepTime(1500);
			
			if (pvm.toastmessageDisplayed() == true) {
				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed for PSI Unit");

			}

			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
		
	}


	

	@Test(priority = 24, enabled = true)
	public void verificationOfSaveButtonforDefaultValueKPA() throws Exception {
		//Thread.sleep(3000);
		
		pvm.ClickSystemSettingsLink();
		log.info("System Settings link clicked");
		
		pvm.ClickPreferencesLink();
		log.info("Preferences link clicked");
		
		pvm.SelectKPAUnit();
		log.info("KPAUnit selected");
		
		pvm.saveButton();
		log.info("save Button clicked");
		
		pvm.clickRunTimeSettings();
		log.info("RunTime Settings link clicked");
		
		pvm.clickPressure();
		log.info("Pressure link clicked");
		
        if(pvm.getkPaMinSetPoint().equals(Constants.KPAMinsetpointdefaultValue)&& pvm.getKpaMaxSetPoint().equals(Constants.KPAMaxsetpointdefaultValue)) {
		    
			Assert.assertEquals(false, pvm.saveButtonEnabled());
			
			log.info("save Button Disabled for default value");
		
		}
		else
		{
			Assert.assertTrue(true);
			log.info("save Button enabled");
		}
	  }
	@Test(priority = 25, enabled = true)
	public void TC_OMU_365_verificationOfMinPressureSetPointLimitBelowRangeKPA() throws Exception {
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.saveButton();
		log.info("save Button clicked after clearing the field");
		Am.sleepTime(1500);
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MinpresssetptKPAerrormsg);
			log.info("Minimum Pressure Alert should be between 0 and 655 kPa is displyed");

		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("mincheckKPAMin");
		pvm.clearMinSetPoint();

	}

	// XLUtils.setExcelSheetNm("KPAAboveRange");
	@Test(priority = 26, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMinValueKPA(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			
			log.info("Pressure updated successfully message displyed for KPA Unit value 0");
		} 
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckKPAmin+1");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 27, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMorethanMinValueKPA(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit value 1");
		} 
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckKPAMax");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 28, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMaxValueKPA(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit value 655");
		}
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckKPAMax-1");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 29, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitLessthanMinValueKPA(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit value 654");
		}
		else
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("KPAAboveRange");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 30, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_365_verificationOfMinPressureSetPointLimitAboveRangeKPA(String min) throws Exception {
		//Thread.sleep(3000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MinpresssetptKPAerrormsg);
			log.info("Minimum Pressure Alert should be between 0 and 655 kPa is displyed");

		} 
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("KPAMidValue");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 31, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_368_verificationOfMinPressureSetPointLimitMidValueKPA(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit");
		}
		else
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		XLUtils.setExcelSheetNm("KPAmaxbelowRange");
	}
	@Test(priority = 32, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_370_verificationOfMaxPressureSetPointLimitBelowRangeKPA(String max) throws Exception {
		//Thread.sleep(3000);
        pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after clearing the field");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MaxpresssetptKPAerrormsg);
			log.info("Maximum Pressure Alert should be between 34 and 690 kPa is displyed");

		}
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("maxcheckKPAMin");
		
	}

	@Test(priority = 33, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMinValueKPA(String min,String max) throws Exception {
		//Thread.sleep(2000);
		pvm.clearMinSetPoint();
		log.info("Min SetPoint Field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint Field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit value 34");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckKPAMin+1");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 34, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMorethanMinValueKPA(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit value 35");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckKPAMax");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 35, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMaxValueKPA(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit value 690");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckKPAMax-1");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 36, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitLessthanMaxValueKPA(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit value 689");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("KPAmaxAboveRange");
		pvm.clearMaxSetPoint();

	}

	@Test(priority = 37, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_370_verificationOfMaxPressureSetPointLimitAboveRangeKPA(String max) throws Exception {
		//Thread.sleep(3000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MaxpresssetptKPAerrormsg);
			log.info("Maximum Pressure Alert should be between 34 and 690 kPa is displyed");

		}
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("KPAmaxInRange");
		pvm.clearMaxSetPoint();

	}

	@Test(priority = 38, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_373_verificationOfMaxPressureSetPointLimitWithinRangeKPA(String max) throws Exception {
		//Thread.sleep(3000);
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		//XLUtils.setExcelSheetNm("BARmaxbelowRange");
		XLUtils.setExcelSheetNm("maxlessminKPA");
	}
	@Test(priority = 39, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressurelessthanMinPressureKPA(String min, String max) throws Exception {
		//Thread.sleep(2000);
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Maxsetptbelowminsetpterrormsg);
			log.info("Minimum Pressure Alert Range should be less than Maximum Pressure Alert Range message displyed");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("MaxMinsameKPA");

	}

	@Test(priority = 40, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressureSameKPA(String min, String max) throws Exception {
		//Thread.sleep(2000);
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Maxsetptbelowminsetpterrormsg);
			log.info("Minimum Pressure Alert Range should be less than Maximum Pressure Alert Range message displyed");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxmindiffKPA");

	}

	@Test(priority = 41, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressuredifferenceKPA(String min, String max) throws Exception {
		//Thread.sleep(2000);
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.PressalrtrangediffKPAerrormsg);
			log.info("Pressure Alert range should have a minimum difference of 34 kPa message displyed");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxvalueKPA");

	}

	@Test(priority = 42, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressurediffvalueKPA(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("DefaultvalueKPA");

	}
	@Test(priority = 43, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressurediffvalueKPA(String min,String max) throws Exception {
		//Thread.sleep(2000);
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");

		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for KPA Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		//XLUtils.setExcelSheetNm("maxlessminBAR");

	}

	@Test(priority = 44, enabled = true)
	public void verificationOfSaveButtonforBAR() throws Exception {
		//Thread.sleep(3000);
		
		pvm.ClickSystemSettingsLink();
		log.info("System Settings link clicked");
		
		pvm.ClickPreferencesLink();
		log.info("Preferences link clicked");
		
		pvm.SelectBARUnit();
		log.info("BARUnit selected");
		
		pvm.saveButton();
		log.info("save Button clicked");
		
		pvm.clickRunTimeSettings();
		log.info("RunTime Settings link clicked");
		
		pvm.clickPressure();
		log.info("Pressure link clicked");
       if(pvm.getBARMinSetPoint().equals(Constants.BARMinsetpointdefaultValue)&& pvm.getBARMaxSetPoint().equals(Constants.BARMaxsetpointdefaultValue)) {
		    
			Assert.assertEquals(false, pvm.saveButtonEnabled());
			
			log.info("save Button Disabled for default value");
		
		}
		else
		{
			Assert.assertTrue(true);
			log.info("save Button enabled");
		}
		
	}
		
		@Test(priority = 45, enabled = true)
		public void TC_OMU_365_verificationOfMinPressureSetPointLimitBelowRangeBAR() throws Exception {	
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.saveButton();
		log.info("save Button clicked after clearing the field");
		Am.sleepTime(1500);
		// Minimum Pressure Alert should be between 0 and 6.55 BAR is displyed
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MinpresssetptBARerrormsg);
			log.info("Minimum Pressure Alert should be between 0 and 6.55 BAR is displyed");

		}
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckBARMin");
	}

	@Test(priority = 46, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMinValueBAR(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 0");
		} 
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckBARMin+1");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 47, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMorethanMinValueBAR(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 1");
		}
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckBARMax");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 48, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitMaxValueBAR(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 655");
		}
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("mincheckBarMax-1");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 49, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMinPressureSetPointLimitLessthanMinValueBAR(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 654");
		}
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("BARAboveRange");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 50, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_365_verificationOfMinPressureSetPointLimitAboveRangeBAR(String min) throws Exception {
		//Thread.sleep(3000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MinpresssetptBARerrormsg);
			log.info("Minimum Pressure Alert should be between 0 and 6.55 BAR is displyed");

		}
		else 
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("BARMidValue");
		pvm.clearMinSetPoint();

	}

	@Test(priority = 51, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_367_verificationOfMinPressureSetPointLimitMidValueBAR(String min) throws Exception {
		//Thread.sleep(2000);

		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit");
		}
		else
		{
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		//XLUtils.setExcelSheetNm("PSImaxbelowRange");
		XLUtils.setExcelSheetNm("BARmaxbelowRange");
	}
	@Test(priority = 52, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_370_verificationOfMaxPressureSetPointLimitBelowRangeBAR(String max) throws Exception {
		//Thread.sleep(3000);
	
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after clearing the field");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MaxpresssetptBARerrormsg);
			log.info("Maximum Pressure Alert should be between 0.34 and 6.9 BAR is displyed");

		}
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("maxcheckBARMin");
		
	}

	@Test(priority = 53, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMinValueBAR(String min,String max) throws Exception {
		//Thread.sleep(2000);
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 0.34");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckBARMin+1");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 54, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMorethanMinValueBAR(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 0.35");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckBARMax");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 55, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitMaxValueBAR(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 6.9");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxcheckBARMax-1");
		pvm.clearMaxSetPoint();
	}

	@Test(priority = 56, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureSetPointLimitLessthanMaxValueBAR(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit value 6.8");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("BARmaxAboveRange");
		pvm.clearMaxSetPoint();

	}

	@Test(priority = 57, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_370_verificationOfMaxPressureSetPointLimitAboveRangeBAR(String max) throws Exception {
		//Thread.sleep(3000);

		pvm.setMaxValue(max);
		log.info("Min SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);

		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.MaxpresssetptBARerrormsg);
			log.info("Maximum Pressure Alert should be between 0.34 and 6.9 BAR is displyed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("BARmaxInRange");
		pvm.clearMaxSetPoint();

	}

	@Test(priority = 58, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_OMU_372_verificationOfMaxPressureSetPointLimitWithinRangeBAR(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxlessminBAR");
	}
	@Test(priority = 59, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressurelessthanMinPressureBAR(String min, String max) throws Exception {
		//Thread.sleep(3000);
		
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Maxsetptbelowminsetpterrormsg);
			log.info("Minimum Pressure Alert Range should be less than Maximum Pressure Alert Range message displyed");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("MaxMinsameBAR");
	}

	@Test(priority = 60, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressureSameBAR(String min, String max) throws Exception {
		Thread.sleep(2000);
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Maxsetptbelowminsetpterrormsg);
			log.info("Minimum Pressure Alert Range should be less than Maximum Pressure Alert Range message displyed");

		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxmindiffBAR");

	}

	@Test(priority = 61, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressuredifferenceBAR(String min, String max) throws Exception {
		//Thread.sleep(2000);
		
		pvm.clearMinSetPoint();
		log.info("Min SetPoint field Cleared");
		
		pvm.setMinValue(min);
		log.info("Min SetPoint value provided");
		
		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.PressalrtrangediffBARerrormsg);
			log.info("Pressure Alert range should have a minimum difference of 0.34 BAR message displyed");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		//softAssert.assertAll();

		XLUtils.setExcelSheetNm("maxvalueBAR");

	}

	@Test(priority = 62, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void verificationOfMaxPressureandMinPressurediffvalueBAR(String max) throws Exception {
		//Thread.sleep(2000);

		pvm.clearMaxSetPoint();
		log.info("Max SetPoint field Cleared");
		
		pvm.setMaxValue(max);
		log.info("Max SetPoint value provided");
		
		pvm.saveButton();
		log.info("save Button clicked after value provided");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed for BAR Unit");

		}

		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		XLUtils.setExcelSheetNm("DefaultvalueBAR");
	}
		@Test(priority = 63, enabled = true, dataProvider = "Pressure boundry value data", dataProviderClass = com.nordson.utilities.XLUtils.class)
		public void verificationOfDefaultvalueBAR(String min,String max) throws Exception {
			//Thread.sleep(2000);
			pvm.clearMinSetPoint();
			log.info("Min SetPoint field Cleared");
			
			pvm.setMinValue(min);
			log.info("Min SetPoint value provided");
			pvm.clearMaxSetPoint();
			log.info("Max SetPoint field Cleared");
			
			pvm.setMaxValue(max);
			log.info("Max SetPoint value provided");
			
			pvm.saveButton();
			log.info("save Button clicked after value provided");
			Am.sleepTime(1500);
			
			if (pvm.toastmessageDisplayed() == true) {
				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed for BAR Unit");

			}

			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
		
	}
}
	/*
	 * //softAssert.assertAll();
	 * 
	 * pvm.clickZoneTemperareture(); log.info("ZoneTemperareture link clicked");
	 * pvm.clickPressure(); log.info("Pressure link clicked");
	 * 
	 * System.out.println(pvm.getBARMinSetPoint());
	 * System.out.println(pvm.getBARMaxSetPoint());
	 * 
	 * // String str1=driver.findElement(By.xpath(
	 * "//input[contains(@name,'ulPressureMaxSetPoint')]")).getAttribute("value");
	 * // String str2=driver.findElement(By.xpath(
	 * "//input[contains(@name,'ulPressureMaxSetPoint')]")).getAttribute("value");
	 * 
	 * // System.out.println(str1); // System.out.println(str2); //
	 */



	