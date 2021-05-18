package com.nordson.MDS;



import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.TemperatureRuntimeSettings;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_RuntimeSettings_Temperature_MDSValidations extends TC_LoginTest_DDT_001 {

	TemperatureRuntimeSettings trs;
	ActionMethods Am=new ActionMethods();
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Temperature mds = new MDSGetterandSetters_Temperature();
	RetriveMDSdata_Temperature rmds = new RetriveMDSdata_Temperature();
	ReadConfig rcf = new ReadConfig();

	@Feature("Verify the Runtime Settings MDS Values")
	@Description("Verify the MDS Global Set Point for Celsius Temperature Unit")
	@Test(priority = 1 ,enabled = false)
	public void Test_RuntimeSetting_GlobalSetPoint_MDS_Celsius() throws Exception {

		trs = new TemperatureRuntimeSettings(driver);
		trs.createNewNORfile();
		Am.sleepTime(3500);
		trs.clickSystemSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.Preferences, driver);
		trs.clickPreferencesBtn();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(trs.SelectedTemperatureunit, driver);
		trs.clickCelsiusTempUnit();
		Am.sleepTime(2500);
		Am.drawBorder(trs.RuntimeSettings, driver);
		trs.RuntimeSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.ZoneTemperature, driver);
		trs.ZoneTemperatureBtn();
		
		// Verfication default temp unt
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_GP());
		Am.sleepTime(2500);
		System.out.println("Primary temperature unit for Global Set Point in MDS file = "+mds.getPrmryunt());
		if (trs.getTemperatureunt().contains(mds.getPrmryunt().toUpperCase())) {
			Am.drawBorder(trs.Temperatureunt, driver);
			log.info("Global set point is set to Default Celsius unit");}
		else {
			Am.drawBorderFail(trs.Temperatureunt, driver);
			log.info("Global set point is not set to Default Celsius unit");}
		
		// Verification of min value
        //  sleep for demo 
		Am.sleepTime(2200);
		Am.drawBorder(trs.GlobalSetPoint, driver);
		Am.sleepTime(2000);
		System.out.println("Minimum temperature value for Global Set Point in MDS file = "+mds.getMin1());
		Am.sleepTime(1200);
		trs.setGlobalSetPoint(mds.getMin1());
		Am.sleepTime(1200);
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			  Am.sleepTime(200);
			    Am.drawBorder(trs.Toastmsg, driver);
			    softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Global Set point Celsius unit");
			    softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else {
			Am.drawBorderFail(trs.Toastmsg, driver);}
		log.info("Toast msg is shown for MDS Globalset point min value Celsius unit");
		
		// Verification of Tank point with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		Am.sleepTime(2000);
		System.out.println("Minimum Value of Tank Set point in MDS file ="+mds.getMin1());
		Am.sleepTime(2000);
		if(trs.getTankSetPoint().equalsIgnoreCase(mds.getMin1())) {
			Am.drawBorder(trs.Tank, driver);
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getMin1(),
				"Tank set point is not set to Minimum value : " + mds.getMin1() + "for Celsius unit");}
		else
		    Am.drawBorderFail(trs.Tank, driver);
		log.info("Tank set point for global minimum point is set to :" + mds.getMin1() + "for Celsius unit");
		
		// Verification of Manifold with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		Am.sleepTime(2000);
		System.out.println("Minimum Value of Manifold Set point in MDS file ="+mds.getMin1());
		Am.sleepTime(2000);
		if(trs.getManifold().equalsIgnoreCase(mds.getMin1())) {
			Am.drawBorder(trs.Manifold, driver);
		 softAssert.assertEquals(trs.getManifold(), mds.getMin1(),
				"Manifold  set point is not set to Minimum value : " + mds.getMin1() + "for Celsius unit");}
		else
			  Am.drawBorderFail(trs.Tank, driver);
		log.info("Manifold set point for global minimum point is set to :" + mds.getMin1() + "for Celsius unit");
		
		// Verification of Hose and Applicator with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		Am.sleepTime(2000);
		System.out.println("Minimum Value of Hoses Set point in MDS file ="+mds.getMin1());
		trs.getHosesSettempStatus(mds.getMin1());
		log.info("All the Hoses are set to min value for minimum global set point for Celsius unit");
		trs.getApplicatorsSettempStatus(mds.getMin1());
		log.info("All the Applicators are set to min value for minimum global set point for Celsius unit");

		// Verification of max value
		//Global Point
		Am.sleepTime(2200);
		Am.drawBorder(trs.GlobalSetPoint, driver);
		Am.sleepTime(2000);
		System.out.println("Maximum temperature value for Global Set Point in MDS file = "+mds.getMax1());
		Am.sleepTime(1200);
		trs.setGlobalSetPoint(mds.getMax1());
		Am.sleepTime(1200);
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			 Am.waitForAnElementPresence(trs.Toastmsg);
			    Am.drawBorder(trs.Toastmsg, driver);
			    softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Global Set point Celsius unit");
			    softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else {
			Am.drawBorderFail(trs.Toastmsg, driver);}
		log.info("Toast msg is shown for MDS Globalset point min value Celsius unit");
		
		//Tank
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		Am.sleepTime(2000);
		System.out.println("Maximum Value of Tank Set point in MDS file ="+mds.getMax1());
		Am.sleepTime(2000);
		if(trs.getTankSetPoint().equalsIgnoreCase(mds.getMax1())) {
			Am.drawBorder(trs.Tank, driver);
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getMax1(),
				"Tank set point is not set to Maximum value : " + mds.getMax1() + "for Celsius unit");}
		else
		    Am.drawBorderFail(trs.Tank, driver);
		log.info("Tank set point for global Maximum point is set to :" + mds.getMax1() + "for Celsius unit");
		
		//Manifold
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		Am.sleepTime(2000);
		System.out.println("Maximum Value of Tank Set point in MDS file ="+mds.getMax1());
		Am.sleepTime(2000);
		if(trs.getManifold().equalsIgnoreCase(mds.getMax1())) {
			Am.drawBorder(trs.Manifold, driver);
		 softAssert.assertEquals(trs.getManifold(), mds.getMax1(),
				"Manifold  set point is not set to Minimum value : " + mds.getMax1() + "for Celsius unit");}
		else
			  Am.drawBorderFail(trs.Tank, driver);
		log.info("Manifold set point for global maximum point is set to :" + mds.getMax1() + "for Celsius unit");
		
		//Hose and Applicators
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		Am.sleepTime(2000);
		System.out.println("Minimum Value of Hoses Set point in MDS file ="+mds.getMax1());
		trs.getHosesSettempStatus(mds.getMax1());
		log.info("All the Hoses are set to min value for minium global set point for Celsius unit");
		Am.sleepTime(2000);
		System.out.println("Minimum Value of Applicator Set point in MDS file ="+mds.getMax1());
		trs.getApplicatorsSettempStatus(mds.getMax1());
		log.info("All the Applicators are set to min value for maximum global set point for Celsius unit");

	}

	@Description("Verify the MDS Tank Set Point for Celsius Temperature Unit")
	@Test(priority = 2,enabled = false)
	public void Test_RuntimeSetting_TankSetPoint_MDS_Celsius() throws Exception {
		Am.sleepTime(1800);
		Am.drawBorder(trs.Dashboard, driver);
		Thread.sleep(2000);
		trs.clickDashboard();
		Am.sleepTime(1000);
		trs.createNewNORfile();
		Am.sleepTime(3500);
		Am.drawBorder(trs.SystemSettingsLnk, driver);
		trs.clickSystemSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.Preferences, driver);
		trs.clickPreferencesBtn();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(trs.SelectedTemperatureunit, driver);
		trs.clickCelsiusTempUnit();
		Am.sleepTime(2500);
		Am.drawBorder(trs.RuntimeSettings, driver);
		trs.RuntimeSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.ZoneTemperature, driver);
		trs.ZoneTemperatureBtn();
		
		// Default Value Validations
		// Sleep for demo purpose
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		Am.sleepTime(2200);
		System.out.println("Default Value of TankPoint from MDS file ="+mds.getDefault1());
		Am.sleepTime(2500);
		Am.drawBorder(trs.Tank, driver);
		Am.sleepTime(1800);
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getDefault1(),
				"Tank set point is not set to Default value for Celsius unit");
		log.info("Tank set point is set to Default value for Celsius unit");
		
		// Min value validations for Tank point in Celsius
		trs.clearTanktemperature();
		log.info("Tank temperature cleared to enter min mds val for Celsius unit");
		Am.sleepTime(1800);
		System.out.println("Minimum Value of TankPoint from MDS file ="+mds.getMin1());
		Am.sleepTime(2500);
		trs.setTankSetPoint(mds.getMin1());
		Am.sleepTime(2500);
		Am.drawBorder(trs.SAVE1, driver);
		Am.sleepTime(1800);
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			 Am.waitForAnElementPresence(trs.Toastmsg);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Tankset point Celsius unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("MDS Min val for Tank set pointsaved successfully for Celsius unit");
	
		// Max value validations for Tank point in Celsius
		trs.clearTanktemperature();
		log.info("Tank temperature cleared to enter min mds val for Celsius unit");
		Am.sleepTime(1800);
		System.out.println("Maximum Value of TankPoint from MDS file ="+mds.getMax1());
		Am.sleepTime(2500);
		trs.setTankSetPoint(mds.getMax1());
		Am.sleepTime(2500);
		Am.drawBorder(trs.SAVE1, driver);
		Am.sleepTime(1800);
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			 Am.waitForAnElementPresence(trs.Toastmsg);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Tankset point Celsius unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("MDS Min val for Tank set pointsaved successfully for Celsius unit");
		softAssert.assertAll();
	}	
		@Description("Verify the MDS Manifold Set Point for Celsius Temperature Unit")
		@Test(priority = 3,enabled = false)
		public void Test_RuntimeSetting_ManifoldSetPoint_MDS_Celsius() throws Exception {

			Am.sleepTime(1800);
			Am.drawBorder(trs.Dashboard, driver);
			Thread.sleep(2000);
			trs.clickDashboard();
			Am.sleepTime(1000);
			trs.createNewNORfile();
			Am.sleepTime(3500);
			Am.drawBorder(trs.SystemSettingsLnk, driver);
			trs.clickSystemSettingsBtn();
			Am.sleepTime(2500);
			Am.drawBorder(trs.Preferences, driver);
			trs.clickPreferencesBtn();
			Am.sleepTime(2500);
			log.info("Clicked on Preferences Link");
			Am.sleepTime(2500);
			Am.drawBorder(trs.SelectedTemperatureunit, driver);
			trs.clickCelsiusTempUnit();
			Am.sleepTime(2500);
			Am.drawBorder(trs.RuntimeSettings, driver);
			trs.RuntimeSettingsBtn();
			Am.sleepTime(2500);
			Am.drawBorder(trs.ZoneTemperature, driver);
			trs.ZoneTemperatureBtn();
			
			// Default Value Validations
			// Sleep for demo purpose
			rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
			Am.sleepTime(2200);
			System.out.println("Default Value of ManifoldPoint from MDS file ="+mds.getDefault1());
			Am.sleepTime(2500);
			Am.drawBorder(trs.Manifold, driver);
			Am.sleepTime(1800);
			softAssert.assertEquals(trs.getManifold(), mds.getDefault1(),
					"Manifold set point is not set to Default value for Celsius unit");
			log.info("Manifold set point is set to Default value for Celsius unit");
			
			// Min value validations for Manifold point in Celsius
			trs.clearManifoldtemperature();
			log.info("Manifold temperature cleared to enter min mds val for Celsius unit");
			Am.sleepTime(1800);
			System.out.println("Minimum Value of ManifoldPoint from MDS file ="+mds.getMin1());
			Am.sleepTime(2500);
			trs.setManifoldSetPoint(mds.getMin1());
			Am.sleepTime(2500);
			Am.drawBorder(trs.SAVE1, driver);
			Am.sleepTime(1800);
			trs.clickSavebtn();
			if (trs.getToastmsgststus() == true) {
				 Am.waitForAnElementPresence(trs.Toastmsg);
				Am.drawBorder(trs.Toastmsg, driver);
				softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
						"Toast msg is not shown for MDS min Manifoldset point Celsius unit");
				softAssert.assertEquals(trs.getSavebtnstatus(), false);
			} else
				Am.drawBorderFail(trs.Toastmsg, driver);
			log.info("MDS Min val for Manifold set pointsaved successfully for Celsius unit");
		
			// Max value validations for Manifold point in Celsius
			trs.clearManifoldtemperature();
			log.info("Manifold temperature cleared to enter min mds val for Celsius unit");
			Am.sleepTime(1800);
			System.out.println("Maximum Value of ManifoldPoint from MDS file ="+mds.getMax1());
			Am.sleepTime(2500);
			trs.setManifoldSetPoint(mds.getMax1());
			Am.sleepTime(2500);
			Am.drawBorder(trs.SAVE1, driver);
			Am.sleepTime(1800);
			trs.clickSavebtn();
			if (trs.getToastmsgststus() == true) {
				 Am.waitForAnElementPresence(trs.Toastmsg);
				Am.drawBorder(trs.Toastmsg, driver);
				softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
						"Toast msg is not shown for MDS min Manifoldset point Celsius unit");
				softAssert.assertEquals(trs.getSavebtnstatus(), false);
			} else
				Am.drawBorderFail(trs.Toastmsg, driver);
			log.info("MDS Min val for Manifold set pointsaved successfully for Celsius unit");
			softAssert.assertAll();
	}
	@Description("Verify the MDS Hose and Applicator Set Point for Celsius Temperature Unit")
	@Test(priority = 4,enabled = true)
	public void Test_RuntimeSetting_HoseAppSetPoint_MDS_Celsius() throws Exception {
		
		trs = new TemperatureRuntimeSettings(driver);
		Am.drawBorder(trs.Dashboard, driver); Thread.sleep(2000);
	    trs.clickDashboard(); Am.sleepTime(1000);
		trs.createNewNORfile();
		Am.sleepTime(3500);
		Am.drawBorder(trs.SystemSettingsLnk, driver);
		trs.clickSystemSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.Preferences, driver);
		trs.clickPreferencesBtn();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(trs.SelectedTemperatureunit, driver);
		trs.clickCelsiusTempUnit();
		Am.sleepTime(2500);
		Am.drawBorder(trs.RuntimeSettings, driver);
		trs.RuntimeSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.ZoneTemperature, driver);
		trs.ZoneTemperatureBtn();
		
		// Default Values verification for Hoses and Applicators
		 rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		  Am.sleepTime(2500);
		  System.out.println("Default Value of Hoses from MDS file ="+mds.getDefault1()
		  ); Am.sleepTime(2500); trs.getHosesSettempStatus(mds.getDefault1());
		  log.info("All Hoses are set to MDS default values"); Am.sleepTime(2500);
		  System.out.println("Default Value of Applicator from MDS file ="+mds.
		  getDefault1()); Am.sleepTime(2500);
		  trs.getApplicatorsSettempStatus(mds.getDefault1());
		  log.info("All Applicators are set to MDS default values");
		 
		
		 // Minium Values Verification for Hoses and Applicators
		 Am.sleepTime(2500);
         System.out.println("Minimum Value of Hoses from MDS file ="+mds.getMin1());
         Am.sleepTime(2500);
		 trs.setHosestemp(mds.getMin1());
		 trs.clickSavebtn();
		 if (trs.getToastmsgststus() == true) {
			  Am.sleepTime(200);
				Am.drawBorder(trs.Toastmsg, driver);
				softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
						"Toast msg is not shown for MDS min Manifoldset point Celsius unit");
				softAssert.assertEquals(trs.getSavebtnstatus(), false);
			} else
				Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("All Hoses are set to min MDS value and save successfully");
		
		 Am.sleepTime(2500);
         System.out.println("Minimum Value of Applicators from MDS file ="+mds.getMin1());
         Am.sleepTime(2500);
		 trs.setApplicatorstemp(mds.getMin1());
		 trs.clickSavebtn();
		 if (trs.getToastmsgststus() == true) {
			 Am.waitForAnElementPresence(trs.Toastmsg);
				Am.drawBorder(trs.Toastmsg, driver);
				softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
						"Toast msg is not shown for MDS min Manifoldset point Celsius unit");
				softAssert.assertEquals(trs.getSavebtnstatus(), false);
			} else
				Am.drawBorderFail(trs.Toastmsg, driver);
		
		// Maximum Values Verification for Hoses and Applicators
		 Am.sleepTime(2500);
         System.out.println("Maximum Value of Hoses from MDS file ="+mds.getMax1());
         Am.sleepTime(2500);
		trs.setHosestemp(mds.getMax1());
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			 Am.waitForAnElementPresence(trs.Toastmsg);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Manifoldset point Celsius unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("All Hoses are set to Max MDS value and saved successfully");
		
	    Am.sleepTime(2500);
        System.out.println("Maximum Value of Hoses from MDS file ="+mds.getMax1());
        Am.sleepTime(2500);
		trs.setApplicatorstemp(mds.getMax1());
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			 Am.waitForAnElementPresence(trs.Toastmsg);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Manifoldset point Celsius unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("All Applicators are set to max value and save successfully");
	}
@Feature("Verify the Runtime Settings MDS Values")
@Description("Verify the MDS Global Set Point for Fahrenheit Temperature Unit")
@Test(priority = 5 ,enabled = false)
public void Test_RuntimeSetting_GlobalSetPoint_MDS_Fahrenheit() throws Exception {

	trs=new TemperatureRuntimeSettings(driver);
    Am.drawBorder(trs.Dashboard, driver);
	Am.sleepTime(2000);
	trs.clickDashboard();
	Am.sleepTime(500);
    trs.createNewNORfile();
	trs.clickSystemSettingsBtn();
	Am.sleepTime(2500);
	Am.drawBorder(trs.Preferences, driver);
	trs.clickPreferencesBtn();
	Am.sleepTime(2500);
	log.info("Clicked on Preferences Link");
	Am.sleepTime(2500);
	Am.drawBorder(trs.FarhenitTemperatureunit, driver);
	trs.clickFarnheitTempUnit();
	Am.sleepTime(2500);
	Am.drawBorder(trs.SAVE, driver);
	trs.clickSave();
	if (trs.getToastmsgststus() == true) {
		Am.drawBorder(trs.Toastmsg, driver);
		softAssert.assertEquals(trs.getToastmsg(), Constants.Preferencessucssmsg);
	} else
		Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("Saved the preferences");
	Am.drawBorder(trs.RuntimeSettings, driver);
	trs.RuntimeSettingsBtn();
	Am.sleepTime(2500);
	Am.drawBorder(trs.ZoneTemperature, driver);
	trs.ZoneTemperatureBtn();
	
	// Verfication default temp unt
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_GP());
	Am.sleepTime(2500);
	System.out.println("Primary temperature unit for Global Set Point in MDS file = "+mds.getPrmryunt());
	if (trs.getTemperatureunt().contains(mds.getScndryunt().toUpperCase())) {
		Am.drawBorder(trs.Temperatureunt, driver);
		log.info("Global set point is set to Default Fahrenheit unit");}
	else {
		Am.drawBorderFail(trs.Temperatureunt, driver);
		log.info("Global set point is not set to Default Fahrenheit unit");}
	
	// Verification of min value
    //  sleep for demo 
	Am.sleepTime(2200);
	Am.drawBorder(trs.GlobalSetPoint, driver);
	Am.sleepTime(2000);
	System.out.println("Minimum temperature value for Global Set Point in MDS file = "+mds.getMin2());
	Am.sleepTime(2200);
	trs.setGlobalSetPoint(mds.getMin2());
	Am.sleepTime(2200);
	trs.clickSavebtn();
	if (trs.getToastmsgststus() == true) {
		  Am.sleepTime(200);
		    Am.drawBorder(trs.Toastmsg, driver);
		    softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Global Set point Fahrenheit unit");
		    softAssert.assertEquals(trs.getSavebtnstatus(), false);
	} else {
		Am.drawBorderFail(trs.Toastmsg, driver);}
	log.info("Toast msg is shown for MDS Globalset point min value Fahrenheit unit");
	
	// Verification of Tank point with respect to global set min point
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
	Am.sleepTime(2000);
	System.out.println("Minimum Value of Tank Set point in MDS file ="+mds.getMin2());
	Am.sleepTime(2000);
	if(trs.getTankSetPoint().equalsIgnoreCase(mds.getMin2())) {
		Am.drawBorder(trs.Tank, driver);
	softAssert.assertEquals(trs.getTankSetPoint(), mds.getMin2(),
			"Tank set point is not set to Minimum value : " + mds.getMin2() + "for Fahrenheit unit");}
	else
	    Am.drawBorderFail(trs.Tank, driver);
	log.info("Tank set point for global minimum point is set to :" + mds.getMin2() + "for Fahrenheit unit");
	
	// Verification of Manifold with respect to global set min point
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
	Am.sleepTime(2000);
	System.out.println("Minimum Value of Manifold Set point in MDS file ="+mds.getMin2());
	Am.sleepTime(2000);
	if(trs.getManifold().equalsIgnoreCase(mds.getMin2())) {
		Am.drawBorder(trs.Manifold, driver);
	 softAssert.assertEquals(trs.getManifold(), mds.getMin2(),
			"Manifold  set point is not set to Minimum value : " + mds.getMin2() + "for Fahrenheit unit");}
	else
		  Am.drawBorderFail(trs.Tank, driver);
	log.info("Manifold set point for global minimum point is set to :" + mds.getMin2() + "for Fahrenheit unit");
	
	// Verification of Hose and Applicator with respect to global set min point
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
	Am.sleepTime(2000);
	System.out.println("Minimum Value of Hoses Set point in MDS file ="+mds.getMin2());
	trs.getHosesSettempStatus(mds.getMin2());
	log.info("All the Hoses are set to min value for minimum global set point for Fahrenheit unit");
	trs.getApplicatorsSettempStatus(mds.getMin2());
	log.info("All the Applicators are set to min value for minimum global set point for Fahrenheit unit");

	// Verification of max value
	//Global Point
	Am.sleepTime(2200);
	Am.drawBorder(trs.GlobalSetPoint, driver);
	Am.sleepTime(2000);
	System.out.println("Maximum temperature value for Global Set Point in MDS file = "+mds.getMax2());
	Am.sleepTime(2200);
	trs.setGlobalSetPoint(mds.getMax2());
	Am.sleepTime(2200);
	trs.clickSavebtn();
	if (trs.getToastmsgststus() == true) {
		  Am.sleepTime(200);
		    Am.drawBorder(trs.Toastmsg, driver);
		    softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Global Set point Fahrenheit unit");
		    softAssert.assertEquals(trs.getSavebtnstatus(), false);
	} else {
		Am.drawBorderFail(trs.Toastmsg, driver);}
	log.info("Toast msg is shown for MDS Globalset point min value Fahrenheit unit");
	
	//Tank
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
	Am.sleepTime(2000);
	System.out.println("Maximum Value of Tank Set point in MDS file ="+mds.getMax2());
	Am.sleepTime(2000);
	if(trs.getTankSetPoint().equalsIgnoreCase(mds.getMax2())) {
		Am.drawBorder(trs.Tank, driver);
	softAssert.assertEquals(trs.getTankSetPoint(), mds.getMax2(),
			"Tank set point is not set to Maximum value : " + mds.getMax2() + "for Fahrenheit unit");}
	else
	    Am.drawBorderFail(trs.Tank, driver);
	log.info("Tank set point for global Maximum point is set to :" + mds.getMax2() + "for Fahrenheit unit");
	
	//Manifold
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
	Am.sleepTime(2000);
	System.out.println("Maximum Value of Tank Set point in MDS file ="+mds.getMax2());
	Am.sleepTime(2000);
	if(trs.getManifold().equalsIgnoreCase(mds.getMax2())) {
		Am.drawBorder(trs.Manifold, driver);
	 softAssert.assertEquals(trs.getManifold(), mds.getMax2(),
			"Manifold  set point is not set to Minimum value : " + mds.getMax2() + "for Fahrenheit unit");}
	else
		  Am.drawBorderFail(trs.Tank, driver);
	log.info("Manifold set point for global maximum point is set to :" + mds.getMax2() + "for Fahrenheit unit");
	
	//Hose and Applicators
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
	Am.sleepTime(2000);
	System.out.println("Minimum Value of Hoses Set point in MDS file ="+mds.getMax2());
	trs.getHosesSettempStatus(mds.getMax2());
	log.info("All the Hoses are set to min value for minium global set point for Fahrenheit unit");
	Am.sleepTime(2000);
	System.out.println("Minimum Value of Applicator Set point in MDS file ="+mds.getMax2());
	trs.getApplicatorsSettempStatus(mds.getMax2());
	log.info("All the Applicators are set to min value for maximum global set point for Fahrenheit unit");

}

@Description("Verify the MDS Tank Set Point for Fahrenheit Temperature Unit")
@Test(priority = 6,enabled = false)
public void Test_RuntimeSetting_TankSetPoint_MDS_Fahrenheit() throws Exception {
    
	Am.sleepTime(2800);
	Am.drawBorder(trs.Dashboard, driver);
	Thread.sleep(2000);
	trs.clickDashboard();
	Am.sleepTime(2000);
	trs.createNewNORfile();
	Am.sleepTime(3500);
	Am.drawBorder(trs.SystemSettingsLnk, driver);
	trs.clickSystemSettingsBtn();
	Am.sleepTime(2500);
	Am.drawBorder(trs.Preferences, driver);
	trs.clickPreferencesBtn();
	Am.sleepTime(2500);
	log.info("Clicked on Preferences Link");
	Am.sleepTime(2500);
	Am.drawBorder(trs.FarhenitTemperatureunit, driver);
	trs.clickFarnheitTempUnit();
	Am.sleepTime(2500);
	Am.drawBorder(trs.SAVE, driver);
	trs.clickSave();
	if (trs.getToastmsgststus() == true) {
		Am.drawBorder(trs.Toastmsg, driver);
		softAssert.assertEquals(trs.getToastmsg(), Constants.Preferencessucssmsg);
	} else
		Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("Saved the preferences");
	Am.sleepTime(2500);
	Am.drawBorder(trs.RuntimeSettings, driver);
	trs.RuntimeSettingsBtn();
	Am.sleepTime(2500);
	Am.drawBorder(trs.ZoneTemperature, driver);
	trs.ZoneTemperatureBtn();
	
	// Default Value Validations
	// Sleep for demo purpose
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
	Am.sleepTime(2200);
	System.out.println("Default Value of TankPoint from MDS file ="+mds.getDefault2());
	Am.sleepTime(2500);
	Am.drawBorder(trs.Tank, driver);
	Am.sleepTime(2800);
	softAssert.assertEquals(trs.getTankSetPoint(), mds.getDefault2(),
			"Tank set point is not set to Default value for Fahrenheit unit");
	log.info("Tank set point is set to Default value for Fahrenheit unit");
	
	// Min value validations for Tank point in Fahrenheit
	trs.clearTanktemperature();
	log.info("Tank temperature cleared to enter min mds val for Fahrenheit unit");
	Am.sleepTime(2800);
	System.out.println("Minimum Value of TankPoint from MDS file ="+mds.getMin2());
	Am.sleepTime(2500);
	trs.setTankSetPoint(mds.getMin2());
	Am.sleepTime(2500);
	Am.drawBorder(trs.SAVE1, driver);
	Am.sleepTime(2800);
	trs.clickSavebtn();
	if (trs.getToastmsgststus() == true) {
		Am.waitForAnElementPresence(trs.Toastmsg);
		Am.drawBorder(trs.Toastmsg, driver);
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Tankset point Fahrenheit unit");
		softAssert.assertEquals(trs.getSavebtnstatus(), false);
	} else
		Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("MDS Min val for Tank set pointsaved successfully for Fahrenheit unit");

	// Max value validations for Tank point in Fahrenheit
	trs.clearTanktemperature();
	log.info("Tank temperature cleared to enter min mds val for Fahrenheit unit");
	Am.sleepTime(2800);
	System.out.println("Maximum Value of TankPoint from MDS file ="+mds.getMax2());
	Am.sleepTime(2500);
	trs.setTankSetPoint(mds.getMax2());
	Am.sleepTime(2500);
	Am.drawBorder(trs.SAVE1, driver);
	Am.sleepTime(2800);
	trs.clickSavebtn();
	if (trs.getToastmsgststus() == true) {
		Am.waitForAnElementPresence(trs.Toastmsg);
		Am.drawBorder(trs.Toastmsg, driver);
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Tankset point Fahrenheit unit");
		softAssert.assertEquals(trs.getSavebtnstatus(), false);
	} else
		Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("MDS Min val for Tank set pointsaved successfully for Fahrenheit unit");
	softAssert.assertAll();
}	
	@Description("Verify the MDS Manifold Set Point for Fahrenheit Temperature Unit")
	@Test(priority = 7,enabled = false)
	public void Test_RuntimeSetting_ManifoldSetPoint_MDS_Fahrenheit() throws Exception {

		Am.sleepTime(2800);
		Am.drawBorder(trs.Dashboard, driver);
		Thread.sleep(2000);
		trs.clickDashboard();
		Am.sleepTime(2000);
		trs.createNewNORfile();
		Am.sleepTime(3500);
		trs.clickSystemSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.Preferences, driver);
		trs.clickPreferencesBtn();
		Am.sleepTime(2500);
		log.info("Clicked on Preferences Link");
		Am.sleepTime(2500);
		Am.drawBorder(trs.FarhenitTemperatureunit, driver);
		trs.clickFarnheitTempUnit();
		Am.sleepTime(2500);
		Am.drawBorder(trs.SAVE, driver);
		trs.clickSave();
		if (trs.getToastmsgststus() == true) {
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.Preferencessucssmsg);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("Saved the preferences");
		Am.sleepTime(2500);
		Am.drawBorder(trs.RuntimeSettings, driver);
		trs.RuntimeSettingsBtn();
		Am.sleepTime(2500);
		Am.drawBorder(trs.ZoneTemperature, driver);
		trs.ZoneTemperatureBtn();
		
		// Default Value Validations
		// Sleep for demo purpose
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		Am.sleepTime(2200);
		System.out.println("Default Value of ManifoldPoint from MDS file ="+mds.getDefault2());
		Am.sleepTime(2500);
		Am.drawBorder(trs.Manifold, driver);
		Am.sleepTime(2800);
		softAssert.assertEquals(trs.getManifold(), mds.getDefault2(),
				"Manifold set point is not set to Default value for Fahrenheit unit");
		log.info("Manifold set point is set to Default value for Fahrenheit unit");
		
		// Min value validations for Manifold point in Fahrenheit
		trs.clearManifoldtemperature();
		log.info("Manifold temperature cleared to enter min mds val for Fahrenheit unit");
		Am.sleepTime(2800);
		System.out.println("Minimum Value of ManifoldPoint from MDS file ="+mds.getMin2());
		Am.sleepTime(2500);
		trs.setManifoldSetPoint(mds.getMin2());
		Am.sleepTime(2500);
		Am.drawBorder(trs.SAVE1, driver);
		Am.sleepTime(2800);
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			Am.waitForAnElementPresence(trs.Toastmsg);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Manifoldset point Fahrenheit unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("MDS Min val for Manifold set pointsaved successfully for Fahrenheit unit");
	
		// Max value validations for Manifold point in Fahrenheit
		trs.clearManifoldtemperature();
		log.info("Manifold temperature cleared to enter min mds val for Fahrenheit unit");
		Am.sleepTime(2800);
		System.out.println("Maximum Value of ManifoldPoint from MDS file ="+mds.getMax2());
		Am.sleepTime(2500);
		trs.setManifoldSetPoint(mds.getMax2());
		Am.sleepTime(2500);
		Am.drawBorder(trs.SAVE1, driver);
		Am.sleepTime(2800);
		trs.clickSavebtn();
		if (trs.getToastmsgststus() == true) {
			Am.waitForAnElementPresence(trs.Toastmsg);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Manifoldset point Fahrenheit unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
		log.info("MDS Min val for Manifold set pointsaved successfully for Fahrenheit unit");
		softAssert.assertAll();
}
@Description("Verify the MDS Hose and Applicator Set Point for Fahrenheit Temperature Unit")
@Test(priority = 8,enabled = true)
public void Test_RuntimeSetting_HoseAppSetPoint_MDS_Fahrenheit() throws Exception {

	Am.drawBorder(trs.Dashboard, driver);
	Thread.sleep(2000);
	trs.clickDashboard();
	trs = new TemperatureRuntimeSettings(driver);
	Am.sleepTime(2000);
	trs.createNewNORfile();
	Am.sleepTime(3500);
	Am.drawBorder(trs.SystemSettingsLnk, driver);
	trs.clickSystemSettingsBtn();
	Am.sleepTime(2500);
	Am.drawBorder(trs.Preferences, driver);
	trs.clickPreferencesBtn();
	Am.sleepTime(2500);
	log.info("Clicked on Preferences Link");
	Am.sleepTime(2500);
	Am.drawBorder(trs.FarhenitTemperatureunit, driver);
	trs.clickFarnheitTempUnit();
	Am.sleepTime(2500);
	Am.drawBorder(trs.SAVE, driver);
	trs.clickSave();
	if (trs.getToastmsgststus() == true) {
		Am.drawBorder(trs.Toastmsg, driver);
		softAssert.assertEquals(trs.getToastmsg(), Constants.Preferencessucssmsg);
	} else
		Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("Saved the preferences");
	Am.sleepTime(2500);
	Am.drawBorder(trs.RuntimeSettings, driver);
	trs.RuntimeSettingsBtn();
	Am.sleepTime(2500);
	Am.drawBorder(trs.ZoneTemperature, driver);
	trs.ZoneTemperatureBtn();
	
	// Default Values verification for Hoses and Applicators
	rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		/*
		 * Am.sleepTime(2500);
		 * System.out.println("Default Value of Hoses from MDS file ="+mds.getDefault2()
		 * ); Am.sleepTime(2500); trs.getHosesSettempStatus(mds.getDefault2());
		 * log.info("All Hoses are set to MDS default values"); Am.sleepTime(2500);
		 * System.out.println("Default Value of Applicator from MDS file ="+mds.
		 * getDefault2()); Am.sleepTime(2500);
		 * trs.getApplicatorsSettempStatus(mds.getDefault2());
		 * log.info("All Applicators are set to MDS default values");
		 */
	
	 // Minium Values Verification for Hoses and Applicators
	 Am.sleepTime(2500);
     System.out.println("Minimum Value of Hoses from MDS file ="+mds.getMin2());
     Am.sleepTime(2500);
	 trs.setHosestemp(mds.getMin2());
	 trs.clickSavebtn();
	 if (trs.getToastmsgststus() == true) {
		  Am.sleepTime(200);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Manifoldset point Fahrenheit unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("All Hoses are set to min MDS value and save successfully");
	
	 Am.sleepTime(2500);
     System.out.println("Minimum Value of Applicators from MDS file ="+mds.getMin2());
     Am.sleepTime(2500);
	 trs.setApplicatorstemp(mds.getMin2());
	 trs.clickSavebtn();
	 if (trs.getToastmsgststus() == true) {
		  Am.sleepTime(200);
			Am.drawBorder(trs.Toastmsg, driver);
			softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
					"Toast msg is not shown for MDS min Manifoldset point Fahrenheit unit");
			softAssert.assertEquals(trs.getSavebtnstatus(), false);
		} else
			Am.drawBorderFail(trs.Toastmsg, driver);
	
	// Maximum Values Verification for Hoses and Applicators
	 Am.sleepTime(2500);
     System.out.println("Maximum Value of Hoses from MDS file ="+mds.getMax2());
     Am.sleepTime(2500);
	trs.setHosestemp(mds.getMax2());
	trs.clickSavebtn();
	if (trs.getToastmsgststus() == true) {
		 Am.waitForAnElementPresence(trs.Toastmsg);
		Am.drawBorder(trs.Toastmsg, driver);
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Manifoldset point Fahrenheit unit");
		softAssert.assertEquals(trs.getSavebtnstatus(), false);
	} else
		Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("All Hoses are set to Max MDS value and saved successfully");
	
    Am.sleepTime(2500);
    System.out.println("Maximum Value of Hoses from MDS file ="+mds.getMax2());
    Am.sleepTime(2500);
	trs.setApplicatorstemp(mds.getMax2());
	trs.clickSavebtn();
	if (trs.getToastmsgststus() == true) {
		 Am.waitForAnElementPresence(trs.Toastmsg);
		Am.drawBorder(trs.Toastmsg, driver);
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Manifoldset point Fahrenheit unit");
		softAssert.assertEquals(trs.getSavebtnstatus(), false);
	} else
		Am.drawBorderFail(trs.Toastmsg, driver);
	log.info("All Applicators are set to max value and save successfully");
    }
}
