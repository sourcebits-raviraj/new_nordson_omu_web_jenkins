package com.nordson.MDS;

import java.io.IOException;

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
	ActionMethods Am;
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Temperature mds = new MDSGetterandSetters_Temperature();
	RetriveMDSdata_Temperature rmds = new RetriveMDSdata_Temperature();
	ReadConfig rcf = new ReadConfig();

	@Feature("Verify the Runtime Settings MDS Values")
	@Description("Verify the MDS Global Set Point for Celsius Temperature Unit")
	@Test(priority = 1 ,enabled = true)
	public void Test_RuntimeSetting_GlobalSetPoint_MDS_Celsius() throws InterruptedException, IOException {

		trs = new TemperatureRuntimeSettings(driver);
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_GP());
		trs.createNewNORfile();
		// Verfication default temp unt
		if (trs.getTemperatureunt().contains(mds.getPrmryunt()))
			log.info("Global set point is set to Default celsius unit");
		else
			log.info("Global set point is not set to Default celsius unit");
		// Verification of min value
		trs.setGlobalSetPoint(mds.getMin1());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Global Set point celsius unit");
		log.info("Toast msg is shown for MDS Globalset point min value celsius unit");
		boolean glblpntSavebtnstutsmin1 = trs.getSavebtnstatus();
		softAssert.assertEquals(glblpntSavebtnstutsmin1, false);
		log.info("Save button is disabled for Global set point mds min val for celsius unit");
		// Verification of Tankpoint with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getMin1(),
				"Tank set point is not set to Minimum value : " + mds.getMin1() + "for celsius unit");
		log.info("Tank set point for global minimun point is set to :" + mds.getMin1() + "for celsius unit");
		// Verification of Manifold with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		softAssert.assertEquals(trs.getManifold(), mds.getMin1(),
				"Manifold  set point is not set to Minimum value : " + mds.getMin1() + "for celsius unit");
		log.info("Manifold set point for global minimun point is set to :" + mds.getMin1() + "for celsius unit");
		// Verification of Hose and Applicator with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		trs.getHosesSettempStatus(mds.getMin1());
		log.info("All the Hoses are set to min value for minium global set point for celsius unit");
		trs.getApplicatorsSettempStatus(mds.getMin1());
		log.info("All the Applicators are set to min value for minimun global set point for celsius unit");

		// Verification of max value
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_GP());
		trs.setGlobalSetPoint(mds.getMax1());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Global Set point celsius unit");
		log.info("Toast msg is shown for MDS Globalset point max value celsius unit");
		boolean glblpntSavebtnstutsmax1 = trs.getSavebtnstatus();
		softAssert.assertEquals(glblpntSavebtnstutsmax1, false);
		log.info("Save button is disabled for Global set point mds max val for celsius unit");
		// Verification of Tankpoint with respect to global set max point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getMax1(),
				"Tank set point is not set to maximum value : " + mds.getMax1() + "for celsius unit");
		log.info("Tank set point for global maximun point is set to :" + mds.getMax1() + "for celsius unit");
		// Verification of Manifold with respect to global set max point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		softAssert.assertEquals(trs.getManifold(), mds.getMax1(),
				"Manifold  set point is not set to maximum value : " + mds.getMax1() + "for celsius unit");
		log.info("Manifold set point for global maximun point is set to :" + mds.getMax1() + "for celsius unit");
		// Verification of Manifold with respect to global set max point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		trs.getHosesSettempStatus(mds.getMax1());
		log.info("All the Hoses are set to max value for maxium global set point");
		trs.getApplicatorsSettempStatus(mds.getMax1());
		log.info("All the Applicators are set to max value for maximun global set point");

	}

	@Description("Verify the MDS Tank Set Point for Celsius Temperature Unit")
	@Test(priority = 2,enabled = true)
	public void Test_RuntimeSetting_TankSetPoint_MDS_Celsius() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());	
		trs.clickDashboard();
		Thread.sleep(1000);
		trs.createNewNORfile();
		// Default Value Validations
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getDefault1(),
				"Tank set point is not set to Default value for celsius unit");
		log.info("Tank set point is set to Default value for celsius unit");
		// Min value validations for Tank point in celsius
		trs.clearTanktemperature();
		log.info("Tank temperature cleared to enter min mds val for Celsius unit");
		trs.setTankSetPoint(mds.getMin1());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Tankset point celsius unit");
		log.info("MDS Min val for Tank set pointsaved successfully for celsius unit");
		boolean TnkpntSavebtnstutsmin1 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmin1, false);
		log.info("Save button is disabled for Tank set point mds min val for celsius unit");
		// Max value validations for Tank point in celsius
		trs.clearTanktemperature();
		log.info("Tank temperature cleared to enter Max Tankset point value for Celsius unit");
		trs.setTankSetPoint(mds.getMax1());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS max Tankset point celsius unit");
		log.info("Toast msg is shown for MDS Tankset point max value celsius unit");
		boolean TnkpntSavebtnstutsmax1 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmax1, false);
		log.info("Save button is disabled for Tank set point mds max val for celsius unit");
		softAssert.assertAll();
	}

	@Description("Verify the MDS Manifold Set Point for Celsius Temperature Unit")
	@Test(priority = 3,enabled = true)
	public void Test_RuntimeSetting_ManifoldSetPoint_MDS_Celsius() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		trs.clickDashboard();
		Thread.sleep(1000);
		trs.createNewNORfile();
		// Default Value Validations
		softAssert.assertEquals(trs.getManifold(), mds.getDefault1(),
				"Manifold set point is not set to Default value for celsius unit");
		log.info("Manifold set point is set to Default value for celsius unit");
		// Min value validations for Manifold point in celsius
		trs.clearManifoldtemperature();
		log.info("Manifold temperature cleared to enter min mds val for Celsius unit");
		trs.setManifoldSetPoint(mds.getMin1());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Manifoldset point celsius unit");
		log.info("MDS Min val for Manifold set point saved successfully for celsius unit");
		boolean TnkpntSavebtnstutsmin1 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmin1, false);
		log.info("Save button is disabled for Manifold set point mds min val for celsius unit");
		// Max value validations for Manifold point in celsius
		trs.clearManifoldtemperature();
		log.info("Manifold temperature cleared to enter Max Manifoldset point value for Celsius unit");
		trs.setManifoldSetPoint(mds.getMax1());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS max Manifoldset point celsius unit");
		log.info("Toast msg is shown for MDS Manifoldset point max value celsius unit");
		boolean TnkpntSavebtnstutsmax1 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmax1, false);
		log.info("Save button is disabled for Manifold set point mds max val for celsius unit");

		softAssert.assertAll();
	}

	@Description("Verify the MDS Hose and Applicator Set Point for Celsius Temperature Unit")
	@Test(priority = 4,enabled = true)
	public void Test_RuntimeSetting_HoseAppSetPoint_MDS_Celsius() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		trs.clickDashboard();
		Thread.sleep(1000);
		trs.createNewNORfile();
		// Default Values verification for Hoses and Applicators
		trs.getHosesSettempStatus(mds.getDefault1());
		log.info("All Hoses are set to MDS default values");
		trs.getApplicatorsSettempStatus(mds.getDefault1());
		log.info("All Applicators are set to MDS default values");
		// Minium Values Verification for Hoses and Applicators
		trs.setHosestemp(mds.getMin1());
		log.info("All Hoses are set to min MDS value and save successfully");
		trs.setApplicatorstemp(mds.getMin1());
		log.info("All Applicators are set to min value and save successfully");
		// Maximum Values Verification for Hoses and Applicators
		trs.setHosestemp(mds.getMax1());
		log.info("All Hoses are set to Max MDS value and saved successfully");
		trs.setApplicatorstemp(mds.getMax1());
		log.info("All Applicators are set to max value and save successfully");
	}

	@Description("Verify the MDS Global Set Point for Fahrenheit Temperature Unit")
	@Test(priority = 5,enabled = true)
	public void Test_RuntimeSetting_GlobalSetPoint_MDS_Fahrenheit() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_GP());
		trs.clickDashboard();
		Thread.sleep(1000);
		trs.createNewNORfile();
		trs.clickPreferencesBtn();
		log.info("Clicked on Preferences button");
		trs.clickFarhenitUnit();
		log.info("Clicked on Fahrenheit temperature button");
		trs.clickSave();
		log.info("Temperature Unit Fahrenheit is saved ");
		if (trs.getToastmsgststus() == true)
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");
		trs.RuntimeSettingsBtn();
		Thread.sleep(1000);
		trs.ZoneTemperatureBtn();
		log.info("Clicked on Runtime settings button");
		// Verfication default temp unt
		if (trs.getTemperatureunt().contains(mds.getScndryunt()))
			log.info("Global set point is set to Default  Fahrenheit unit");
		else
			log.info("Global set point is not set to Default Fahrenheit unit");

		// Verification of min value
		trs.setGlobalSetPoint(mds.getMin2());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Global Set point Fahrenheit unit");
		log.info("Toast msg is shown for MDS Globalset point min value Fahrenheit unit");
		boolean glblpntSavebtnstutsMin2 = trs.getSavebtnstatus();
		softAssert.assertEquals(glblpntSavebtnstutsMin2, false);
		log.info("Save button is disabled for Global set point mds min val for Fahrenheit unit");

		// Verification of Tankpoint with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getMin2(),
				"Tank set point is not set to Minimum value : " + mds.getMin2() + "for Fahrenheit unit");
		log.info("Tank set point for global minimun point is set to :" + mds.getMin2() + "for Fahrenheit unit");
		// Verification of Manifold with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		softAssert.assertEquals(trs.getManifold(), mds.getMin2(),
				"Manifold  set point is not set to Minimum value : " + mds.getMin2() + "for Fahrenheit unit");
		log.info("Manifold set point for global minimun point is set to :" + mds.getMin2() + "for Fahrenheit unit");
		// Verification of Hose/App with respect to global set min point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		trs.getHosesSettempStatus(mds.getMin2());
		log.info("All the Hoses are set to min value for minium global set point for Fahrenheit unit");
		trs.getApplicatorsSettempStatus(mds.getMin2());
		log.info("All the Applicators are set to min value for minimun global set point for Fahrenheit unit");
		// Verification of max value
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_GP());
		trs.setGlobalSetPoint(mds.getMax2());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Global Set point Fahrenheit unit");
		log.info("Toast msg is shown for MDS Globalset point max value Fahrenheit unit");
		boolean glblpntSavebtnstutsMax2 = trs.getSavebtnstatus();
		softAssert.assertEquals(glblpntSavebtnstutsMax2, false);
		log.info("Save button is disabled for Global set point mds max val for Fahrenheit unit");
		// Verification of Tankpoint with respect to global set max point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getMax2(),
				"Tank set point is not set to maximum value : " + mds.getMax2() + "for Fahrenheit unit");
		log.info("Tank set point for global maximun point is set to :" + mds.getMax2() + "for Fahrenheit unit");
		// Verification of Manifold with respect to global set max point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		softAssert.assertEquals(trs.getManifold(), mds.getMax2(),
				"Manifold  set point is not set to maximum value : " + mds.getMax2() + "for Fahrenheit unit");
		log.info("Manifold set point for global maximun point is set to :" + mds.getMax2() + "for Fahrenheit unit");
		// Verification of Hose/Applicator with respect to global set max point
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		trs.getHosesSettempStatus(mds.getMax2());
		log.info("All the Hoses are set to max value for maxium global set point");
		trs.getApplicatorsSettempStatus(mds.getMax2());
		log.info("All the Applicators are set to max value for maximun global set point");

	}

	@Description("Verify the MDS Tank Set Point for Fahrenheit Temperature Unit")
	@Test(priority = 6,enabled = true)
	public void Test_RuntimeSetting_TankSetPoint_MDS_Fahrenheit() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TP());
		trs.clickDashboard();
		Thread.sleep(1000);
		trs.createNewNORfile();
		trs.clickPreferencesBtn();
		log.info("Clicked on Preferences button");
		Thread.sleep(2000);
		trs.clickFarhenitUnit();
		log.info("Clicked on Fahrenheit temperature button");
		trs.clickSave();
		log.info("Temperature Unit Fahrenheit is saved ");
		if (trs.getToastmsgststus() == true)
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");
		trs.RuntimeSettingsBtn();
		Thread.sleep(1000);
		trs.ZoneTemperatureBtn();
		log.info("Clicked on Runtime settings button");
		
		// Default Value Validations
		softAssert.assertEquals(trs.getTankSetPoint(), mds.getDefault2(),
				"Tank set point is not set to Default value for Fahrenheit unit");
		log.info("Tank set point is set to Default value for Fahrenheit unit");
		// Min value validations for Tank point in Fahrenheit
		trs.clearTanktemperature();
		log.info("Tank temperature cleared to enter min mds val for Fahrenheit unit");
		trs.setTankSetPoint(mds.getMin2());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Tankset point Fahrenheit unit");
		log.info("MDS Min val for Tank set pointsaved successfully for Fahrenheit unit");
		boolean TnkpntSavebtnstutsmin2 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmin2, false);
		log.info("Save button is disabled for Tank set point mds min val for Fahrenheit unit");
		// Max value validations for Tank point in Fahrenheit
		trs.clearTanktemperature();
		log.info("Tank temperature cleared to enter Max Tankset point value for Fahrenheit unit");
		trs.setTankSetPoint(mds.getMax2());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS max Tankset point Fahrenheit unit");
		log.info("Toast msg is shown for MDS Tankset point max value Fahrenheit unit");
		boolean TnkpntSavebtnstutsmax2 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmax2, false);
		log.info("Save button is disabled for Tank set point mds max val for Fahrenheit unit");
	}

	@Description("Verify the MDS Manifold Set Point for Fahrenheit Temperature Unit")
	@Test(priority = 7,enabled = true)
	public void Test_RuntimeSetting_ManifoldSetPoint_MDS_Fahrenheit() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MFP());
		trs.clickDashboard();
		Thread.sleep(1000);
		trs.createNewNORfile();
		trs.clickPreferencesBtn();
		log.info("Clicked on Preferences button");
		trs.clickFarhenitUnit();
		log.info("Clicked on Fahrenheit temperature button");
		trs.clickSave();
		log.info("Temperature Unit Fahrenheit is saved ");
		if (trs.getToastmsgststus() == true)
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");
		trs.RuntimeSettingsBtn();
		Thread.sleep(1000);
		trs.ZoneTemperatureBtn();
		log.info("Clicked on Runtime settings button");
		
		// Default Value Validations
		softAssert.assertEquals(trs.getManifold(), mds.getDefault2(),
				"Manifold set point is not set to Default value for Fahrenheit unit");
		log.info("Manifold set point is set to Default value for Fahrenheit unit");
		// Min value validations for Manifold point in Fahrenheit
		trs.clearManifoldtemperature();
		log.info("Manifold temperature cleared to enter min mds val for Fahrenheit unit");
		trs.setManifoldSetPoint(mds.getMin2());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS min Manifoldset point Fahrenheit unit");
		log.info("MDS Min val for Manifold set pointsaved successfully for Fahrenheit unit");
		boolean TnkpntSavebtnstutsmin2 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmin2, false);
		log.info("Save button is disabled for Manifold set point mds min val for Fahrenheit unit");
		// Max value validations for Manifold point in Fahrenheit
		trs.clearManifoldtemperature();
		log.info("Manifold temperature cleared to enter Max Manifoldset point value for Fahrenheit unit");
		trs.setManifoldSetPoint(mds.getMax2());
		trs.clickSavebtn();
		softAssert.assertEquals(trs.getToastmsg(), Constants.SucssmsgRuntime,
				"Toast msg is not shown for MDS max Manifoldset point Fahrenheit unit");
		log.info("Toast msg is shown for MDS Manifoldset point max value Fahrenheit unit");
		boolean TnkpntSavebtnstutsmax2 = trs.getSavebtnstatus();
		softAssert.assertEquals(TnkpntSavebtnstutsmax2, false);
		log.info("Save button is disabled for Manifold set point mds max val for Fahrenheit unit");

		softAssert.assertAll();
	}

	@Description("Verify the MDS Hose and Applicator Set Point for Fahrenheit Temperature Unit")
	@Test(priority = 8,enabled = true)
	public void Test_RuntimeSetting_HoseAppSetPoint_MDS_Fahrenheit() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HoseApp());
		trs.clickDashboard();
		Thread.sleep(1000);
		trs.createNewNORfile();
		trs.clickPreferencesBtn();
		log.info("Clicked on Preferences button");
		trs.clickFarhenitUnit();
		log.info("Clicked on Fahrenheit temperature button");
		trs.clickSave();
		log.info("Temperature Unit Fahrenheit is saved ");
		if (trs.getToastmsgststus() == true)
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");
		trs.RuntimeSettingsBtn();
		Thread.sleep(1000);
		trs.ZoneTemperatureBtn();
		log.info("Clicked on Runtime settings button");
		
		// Default Values verification for Hoses and Applicators
		trs.getHosesSettempStatus(mds.getDefault1());
		log.info("All Hoses are set to MDS default values for Fahrenheit unit");
		trs.getApplicatorsSettempStatus(mds.getDefault1());
		log.info("All Applicators are set to MDS default values for Fahrenheit unit");
		// Minium Values Verification for Hoses and Applicators
		trs.setHosestemp(mds.getMin2());
		log.info("All Hoses are set to min MDS value and save successfully for Fahrenheit unit");
		trs.setApplicatorstemp(mds.getMin2());
		log.info("All Applicators are set to min value and save successfully for Fahrenheit unit");
		// Maximum Values Verification for Hoses and Applicators
		trs.setHosestemp(mds.getMax2());
		log.info("All Hoses are set to Max MDS value and saved successfully for Fahrenheit unit");
		trs.setApplicatorstemp(mds.getMax2());
		log.info("All Applicators are set to max MDS value and save successfully for Fahrenheit unit");

	}
}
