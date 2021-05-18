package com.nordson.testCases;

import java.io.IOException;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.TemperatureRuntimeSettings;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.XLUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Regression Tests")
@Feature("Temperature Runtime settings verification of default values")
public class TC_RuntimeDefaultSettings_TemperatureTest extends TC_LoginTest_DDT_001 {

	TemperatureRuntimeSettings Trs;
	ActionMethods Am;
	TC_LoginTest_DDT_001 lg;
	private SoftAssert softAssert = new SoftAssert();

	@Test(priority = 1, enabled = true)
	@Description("Verify the Default Values for Celsius Temperature Unit")
	public void Test_Celsius_RuntimeSettingDefaultTempValidations() throws Exception {

		Trs = new TemperatureRuntimeSettings(driver);
		

		Trs.clickSetUpToolBtn();
		Trs.clickCreateNewBtn();
		Trs.clickSubmitBtn();
		
		Trs.clickSystemSettingsBtn();
		log.info("Clicked on System settings button");

		
		Trs.clickPreferencesBtn();
		log.info("Clicked on Preferences button");

		Trs.clickCelsiusTempUnit();
		log.info("Clicked on Celsius temperature button");
		
    	Trs.RuntimeSettingsBtn();
    	log.info("Clicked on Runtime settings button");

        Trs.ZoneTemperatureBtn();
        log.info("Clicked on Runtime settings button");

		// Verfication of Default Values for Celsius unit

		String globalSetPoint = Trs.getGlobalSetPoint();
		softAssert.assertTrue(globalSetPoint.isEmpty(), "Global set point is set null for celsius unit");
		log.info("Global set point is set null for celsius unit");

		String tempunt = Trs.getTemperatureunt();
		softAssert.assertEquals("oC", tempunt, "Global set point is not set to Default  celsius unit");
		log.info("Global set point is set to Default  celsius unit");

		String tanksp = Trs.getTankSetPoint();
		softAssert.assertEquals(Constants.Tank, tanksp,
				"Tank set point is not set to Default value for celsius unit : " + Constants.Tank);
		log.info("Tank set point is set to Default value for celsius unit :" + Constants.Tank);

		String manifold = Trs.getManifold();
		softAssert.assertEquals(Constants.Manifold, manifold,
				"Manifold set point is not set to Default value for celsius unit : " + Constants.Manifold);
		log.info("Manifold set point is set to Default value for celsius unit :" + Constants.Manifold);

		// To check the toggle bar status is enabled or not

		Trs.gethosesstatus();
		log.info("All the hoses are disabled by defaultlly");

		// To check all the applicators are enabled or not
		Trs.getApplicatorsstatus();
		log.info("All the applicators are disabled by defaultlly");
	
		// To check the Hose set point temperature
		 Trs.getHosesSettempStatus(Constants.HoseSetTemp);
		 log.info("All Hoses are set to default values");

		// To check the Applicator set point temperature
		Trs.getApplicatorsSettempStatus(Constants.ApplicatorSetTemp);
		log.info("All Applicators are set to default values");

		softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true)
	@Description("Verify the Default Values for Farnheit Temperature Unit")
	public void Test_Farnhenit_RuntimeSettingDefaultTempValidations() throws Exception

	{

		// To Validiate the Default Values in Runtime settings for Farnheit Unit

		Trs.clickSystemSettingsBtn();
		log.info("Clicked on System settings button to change preferences");

		
		Trs.clickPreferencesBtn();
		log.info("Clicked on Preferences to validate Farnheit Default values");

		Trs.clickFarhenitUnit();
		log.info("Clicked on Farnheit temperature button");
		
		Trs.clickSave();
        log.info("Farnheit Value saved to verfiy Default values in run time settings");
        
        
        if(Trs.getToastmsgststus()==true)
        {
        	log.info("Preferences sucessfully updated");
        }

		Trs.RuntimeSettingsBtn();
		log.info("Runtime Setting is clicked to verfiy Farnheit Unit deafult values");


		Trs.ZoneTemperatureBtn();
		log.info("Zone temperature is clicked to verify default Farnheit Values");
		

		// Validations of default values for GP,Tank,Manifold,hoses and applicators
		String globalSetPointfh = Trs.getGlobalSetPoint();
		softAssert.assertTrue(globalSetPointfh.isEmpty(), "Global set point is not set to null for Farnheit Unit");
		log.info("Global set point is set null for Farnheit Unit");

		String tempuntfh = Trs.getTemperatureunt();
		softAssert.assertEquals("oF", tempuntfh, "Global set point is not set to Default  celsius unit");
		log.info("Global set point is set to Default  celsius unit");

		String tankspfh = Trs.getTankSetPoint();
		String farhenitvalutp = ActionMethods.getConversionToFahrenheit(Constants.Tank);
		softAssert.assertEquals(farhenitvalutp, tankspfh,
				"Tank set point is not set to Default value : " + farhenitvalutp);
		log.info("Tank set point is set to Default value for Farnheit Unit :" + farhenitvalutp);

		String manifoldfh = Trs.getManifold();
		String farhenitvalump = ActionMethods.getConversionToFahrenheit(Constants.Manifold);
		softAssert.assertEquals(farhenitvalump, manifoldfh,
				"Manifold set point is not set to Default value for Farnheit Unit: " + farhenitvalump);
		log.info("Manifold set point is set to Default value for Farnheit Unit:" + farhenitvalump);

		Trs.gethosesstatus();
		log.info("Hoses are disabled by default");

		Trs.getApplicatorsstatus();
		log.info("Applicators are disabled by default");
		
		
		 Trs.getHosesSettempStatus(ActionMethods.getConversionToFahrenheit(Constants.HoseSetTemp));
		 log.info("All Hose set point temperature set to default value in Farnheit Unit");

		// To check the Applicator set point temperature
		Trs.getApplicatorsSettempStatus(ActionMethods.getConversionToFahrenheit(Constants.ApplicatorSetTemp));
		log.info("All Applicator set point temperature set to default value in Farnheit Unit");
        	
		XLUtils.setExcelSheetNm("GlobalPointCelsius");
		softAssert.assertAll();
		

	}

}
