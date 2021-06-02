package com.nordson.MDS;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.TemperatureSystemSettings;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_SystemSettings_Temperature_MDSValidations extends TC_LoginTest_DDT_001 {

	TemperatureSystemSettings tss;
	ActionMethods Am;
	TC_LoginTest_DDT_001 lg;
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Temperature mds = new MDSGetterandSetters_Temperature();
	RetriveMDSdata_Temperature rmds = new RetriveMDSdata_Temperature();
	ReadConfig rcf = new ReadConfig();

	@Test(priority = 1,enabled = true)
	@Feature("System settings over temperature threshold set point validations for MDS values")
	@Description("Verify MDS Over Temperature Threshold set  values for Celsius Temperature Unit")
	public void Test_Systemsettings_OverTempThreshold_MDS_Celsius() throws InterruptedException, IOException {

		tss = new TemperatureSystemSettings(driver);
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_OTT());
		tss.createNewNORfile();
		Thread.sleep(1000);
		tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button");

		// check for default values
		softAssert.assertEquals(tss.getOTTemperature(), mds.getDefault1(),
				"Over  Temperature threshold temp is not set to Default value : " + mds.getDefault1());
		log.info("Over Temperature threshold temp is set to Default value  :" + mds.getDefault1());
		// check for Min value for OTT Celsius
		tss.clearOTTemperature();
		tss.setOTTemperature(mds.getMin1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg, "Minimum MDS value not saved for Celsius unit");
		log.info("Minimum OTT Temp MDS value saved successfully for Celsius unit");
		boolean OTTSavebtnstutsmin1 = tss.getSavebtnstatus();
		softAssert.assertEquals(OTTSavebtnstutsmin1, false);
		log.info("Save button is disabled for OTTemp mds min val for celsius unit");

		// check for Max values for OTT Celsius
		tss.clearOTTemperature();
		tss.setOTTemperature(mds.getMax1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg, "Maxmium MDS value not saved for Celsius unit");
		log.info("Maxmium OTT MDS value saved successfully for Celsius unit");
		boolean OTTSavebtnstutsmax1 = tss.getSavebtnstatus();
		softAssert.assertEquals(OTTSavebtnstutsmax1, false);
		log.info("Save button is disabled for OTTemp mds max val for celsius unit");

		// Check for Temperature unit
		if (tss.getOTTTemperatureunt().contains(mds.getPrmryunt()))
			log.info("OTT is set to celsius unit");
		else
			log.info("OTT is not set to celsius unit");
	}

	@Test(priority = 2,enabled = true)
	@Feature("System settings under temperature threshold set point validations for MDS values")
	@Description("Verify MDS Under Temperature Threshold set  values for Celsius Temperature Unit")
	public void Test_Systemsettings_UnderTempThreshold_MDS_Celsius() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_UTT());
		// check for default values
		softAssert.assertEquals(tss.getUTTemperature(), mds.getDefault1(),
				"Under  Temperature threshold global temp is not set to Default value : " + mds.getDefault1());
		log.info("Under Temperature threshold global temp is set to Default value  :" + mds.getDefault1());

		// check for Min value for UTT Celsius
		tss.clearUTTemperature();
		tss.setUTTemperature(mds.getMin1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg, "Minimum MDS value not saved for Celsius unit");
		log.info("Minimum UTT Temp MDS value saved successfully for Celsius unit");
		boolean UTTSavebtnstutsmin1 = tss.getSavebtnstatus();
		softAssert.assertEquals(UTTSavebtnstutsmin1, false);
		log.info("Save button is disabled for UTTemp mds min val for celsius unit");

		// check for Max values for UTT Celsius
		tss.clearUTTemperature();
		tss.setUTTemperature(mds.getMax1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg, "Maxmium MDS value not saved for Celsius unit");
		log.info("Maxmium UTT Temp MDS value saved successfully for Celsius unit");
		boolean UTTSavebtnstutsmax1 = tss.getSavebtnstatus();
		softAssert.assertEquals(UTTSavebtnstutsmax1, false);
		log.info("Save button is disabled for UTTemp mds max val for celsius unit");

	}

	@Test(priority = 3,enabled = true)
	@Feature("System settings temperature Set back set point validations for MDS values")
	@Description("Verify MDS Temperature Set back set  values for Celsius Temperature Unit")
	public void Test_Systemsettings_TemperatureSetbck_MDS_Celsius() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_Tmpstbck());
		// check for default values
		softAssert.assertEquals(tss.getTemperatureSetback(), mds.getDefault1(),
				"Temperature set back is not set to Default value : " + mds.getDefault1());
		log.info("Temperature set back is set to Default value : " + mds.getDefault1());

		// check for Min value for Temperature set back Celsius
		tss.clearTemperaturesetbck();
		tss.setSetbckTemperature(mds.getMin1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg, "Minimum MDS value not saved for Celsius unit");
		log.info("Minimum Temperature set back MDS value saved successfully for Celsius unit");
		boolean TmpstbckSavebtnstutsmin1 = tss.getSavebtnstatus();
		softAssert.assertEquals(TmpstbckSavebtnstutsmin1, false);
		log.info("Save button is disabled for Temp set back mds min val for celsius unit");

		// check for Max values for Temperature set back Celsius
		tss.clearTemperaturesetbck();
		tss.setSetbckTemperature(mds.getMax1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg, "Maxmium MDS value not saved for Celsius unit");
		log.info("Maxmium Temperature set back MDS value saved successfully for Celsius unit");
		boolean TempstbcTSavebtnstutsmax1 = tss.getSavebtnstatus();
		softAssert.assertEquals(TempstbcTSavebtnstutsmax1, false);
		log.info("Save button is disabled for Temp set bck mds max val for celsius unit");

		// Check for Temperature unit
		if (tss.getTempsetbckTemperatureunt().contains(mds.getPrmryunt()))
			log.info("Temp set bck is set to celsius unit");
		else
			log.info("Temp Set bck is not set to celsius unit");
	}

	@Test(priority = 4,enabled = true)
	@Feature("System settings over temperature threshold set point validations for MDS values")
	@Description("Verify MDS Over Temperature Threshold set  values for Fahrenheit Temperature Unit")
	public void Test_Systemsettings_OverTempThreshold_MDS_Fahrenheit() throws InterruptedException, IOException {
		
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_OTT());
		tss.clickDashboard();
		log.info("Clicked on dashboard to create Nor file");
		Thread.sleep(1000);
		tss.createNewNORfile();
		tss.clickPreferencesBtn();
		Thread.sleep(1000);
		tss.clickFahrenheit();
		log.info("Temperature Unit Fahrenheit is selected by clicking");
		tss.clickSave();
		log.info("Saved Fahrenheit OPtion");
		if (tss.getToastmsgststus() == false)
			log.info("Preferences updated sucessfully");
		else
			log.info("Preferences not updated sucessfully");
		tss.clickTemperatureBtn();
		Thread.sleep(2000);
		// check for default values
		softAssert.assertEquals(tss.getOTTemperature(), mds.getDefault2(),
				"Over Temperature threshold temp is not set to Default value : " + mds.getDefault2());
		log.info("Over Temperature threshold temp is set to Default value  :" + mds.getDefault2());
		// check for Minimum MDS value
		tss.clearOTTemperature();
		tss.setOTTemperature(mds.getMin2());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg for min val not saved for Fahrenheit unit");
		log.info("Minimum OTTemp MDS val for Fahrenheit unit saved sucessfully");
		boolean OTTSavebtnstutsmin2 = tss.getSavebtnstatus();
		softAssert.assertEquals(OTTSavebtnstutsmin2, false,
				"Save button is not disabled for OTTemp min val for Fahrenheit unit");
		log.info("Save button is disabled for OTTemp min val for Fahrenheit unit");

		// check for Maximum MDS value
		tss.clearOTTemperature();
		tss.setOTTemperature(mds.getMax2());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast error message is shown up for Fahrenheit unit");
		log.info("Maximum OTTemp MDS val for Fahrenheit unit saved sucessfully");
		boolean OTTSavebtnstutsmax2 = tss.getSavebtnstatus();
		softAssert.assertEquals(OTTSavebtnstutsmax2, false,
				"Save button is not disabled for OTTemp max val for Fahrenheit unit");
		log.info("Save button is disabled for OTTemp max val for Fahrenheit unit");

		// Check for Temperature unit
		if (tss.getOTTTemperatureunt().contains(mds.getScndryunt()))
			log.info("OTT is set to Fahrenheit unit");
		else
			log.info("OTT is not set to Fahrenheit unit");
		softAssert.assertAll();

	}

	@Test(priority = 5,enabled = true)
	@Feature("System settings under temperature threshold set point validations for MDS values")
	@Description("Verify MDS Under Temperature Threshold set  values for Fahrenheit Temperature Unit")
	public void Test_Systemsettings_UnderTempThreshold_MDS_Fahrenheit() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_UTT());
		// check for default values
		softAssert.assertEquals(tss.getUTTemperature(), mds.getDefault2(),
				"Under Temperature threshold temp is not set to Default value : " + mds.getDefault2());
		log.info("Under Temperature threshold  temp is set to Default value  :" + mds.getDefault2());

		// check for Minimum MDS value
		tss.clearUTTemperature();
		tss.setUTTemperature(mds.getMin2());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg for min val not saved for Fahrenheit unit");
		log.info("Minimum MDS UTTemp val for Fahrenheit unit Saved successfully");
		boolean UTTSavebtnstutsmin2 = tss.getSavebtnstatus();
		softAssert.assertEquals(UTTSavebtnstutsmin2, false,
				"Save button is not disabled for UTTemp min val for Fahrenheit unit");
		log.info("Save button is disabled for UTTemp min val for Fahrenheit unit");

		// check for Maximum MDS value
		tss.clearUTTemperature();
		tss.setUTTemperature(mds.getMax2());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast error message is shown up for Fahrenheit unit");
		log.info("Maximum MDS UTTemp val for Fahrenheit unit Saved successfully");
		boolean UTTSavebtnstutsmax2 = tss.getSavebtnstatus();
		softAssert.assertEquals(UTTSavebtnstutsmax2, false,
				"Save button is not disabled for UTTemp max val for Fahrenheit unit");
		log.info("Save button is disabled for UTTemp max val for Fahrenheit unit");

		// Check for Temperature unit
		if (tss.getUTTemperatureunt().contains(mds.getScndryunt()))
			log.info("UTT is set to Fahrenheit unit");
		else
			log.info("UTT is not set to Fahrenheit unit");

		softAssert.assertAll();
	}

	@Test(priority = 6,enabled = true)
	@Feature("System settings temperature Set back set point validations for MDS values")
	@Description("Verify MDS Temperature seet back set values for Fahrenheit Temperature Unit")
	public void Test_Systemsettings_TemperatureSetbck_MDS_Fahrenheit() throws InterruptedException, IOException {
		
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_Tmpstbck());
		// check for default values
		softAssert.assertEquals(tss.getTemperatureSetback(), mds.getDefault2(),
				"Over Temperature threshold global temp is not set to Default value : " + mds.getDefault2());
		log.info("Over Temperature threshold global temp is set to Default value  :" + mds.getDefault2());

		// check for Minimum MDS value
		tss.clearTemperaturesetbck();
		tss.setSetbckTemperature(mds.getMin2());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg for min val not saved for Fahrenheit unit");
		log.info("Minimum Temp Set back val for Fahrenheit unit saved sucessfully");
		boolean TempstbckSavebtnstutsmin2 = tss.getSavebtnstatus();
		softAssert.assertEquals(TempstbckSavebtnstutsmin2, false,
				"Save button is not disabled for Temp Set back min val for Fahrenheit unit");
		log.info("Save button is disabled for Temp Set back min val for Fahrenheit unit");

		// check for Maximum MDS value
		tss.clearTemperaturesetbck();
		tss.setSetbckTemperature(mds.getMax2());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast error message is not shown up for Fahrenheit unit");
		log.info("Maximum Temp Set back val for Fahrenheit unit saved sucessfully");
		boolean TempstbckSavebtnstutsmax2 = tss.getSavebtnstatus();
		softAssert.assertEquals(TempstbckSavebtnstutsmax2, false,
				"Save button is not disabled for Temp Set back max val for Fahrenheit unit");
		log.info("Save button is disabled for Temp Set back max val for Fahrenheit unit");

		// Check for Temperature unit
		if (tss.getTempsetbckTemperatureunt().contains(mds.getScndryunt()))
			log.info("Temp set bck is set to Fahrenheit unit");
		else
			log.info("Temp Set bck is not set to Fahrenheit unit");
		softAssert.assertAll();
	}

	@Test(priority = 7,enabled = true)
	@Feature("System settings SmartMelt Time delay validations for MDS values")
	@Description("Verify MDS SmartMelt Time delay")
	public void Test_Systemsettings_SmartMeltTimeDelay_MDS() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_SMTTmeDly());

		// check for Smart Melt Time delay default value
		softAssert.assertEquals(tss.getSmartMeltTimeDly(), mds.getDefault1(),
				"Smart Melt Time delay is not set to Default time : " + mds.getDefault1());
		log.info("Smart Melt Time delay is set to Default time  :" + mds.getDefault1());

		// check for Smart Melt Time delay Min value
		tss.clearSmartMeltTimeDly();
		tss.setSmartMeltTimeDly(mds.getMin1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Smart melt time delay Minimum MDS val not saved sucessfully");
		log.info("Smart melt time delay Minimum MDS val saved sucessfully");
		boolean SmrtmlttmdlySavebtnstutsmin1 = tss.getSavebtnstatus();
		softAssert.assertEquals(SmrtmlttmdlySavebtnstutsmin1, false,
				"Save button is not disabled for Smart melt time delay min MDS val ");
		log.info("Save button is disabled for minimum Smart melt time delay MDS val");

		// check for Smart Melt Time delay Max value
		tss.clearSmartMeltTimeDly();
		tss.setSmartMeltTimeDly(mds.getMax1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Smart melt time delay Maximum MDS val not saved sucessfully");
		log.info("Smart melt time delay Maximum MDS val saved sucessfully");
		boolean SmrtmlttmdlySavebtnstutsmax1 = tss.getSavebtnstatus();
		softAssert.assertEquals(SmrtmlttmdlySavebtnstutsmax1, false,
				"Save button is not disabled for Smart melt time delay max MDS val ");
		log.info("Save button is disabled for maximum Smart melt time delay MDS val");

		softAssert.assertAll();
	}

	@Test(priority = 8,enabled = true)
	@Feature("Verify MDS System Set Back Delay Time")
	@Description("System System Set Back Delay Time validations for MDS values")
	public void Test_Systemsettings_SystemSetBackDelay_MDS() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_SystemSetbckDly());

		// check for System Set Back Delay delay default value
		softAssert.assertEquals(tss.getSystemSetbackTmeDly(), mds.getDefault1(),
				"System Set Back delay is not set to Default time : " + mds.getDefault1());
		log.info("System Set Back delay is set to Default time  :" + mds.getDefault1());

		// check for System Set Back Delay delay Min value
		tss.clearSystemsetbckTmeDly();
		tss.setSystemsetbckTmeDly(mds.getMin1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"System Set Back delay Minimum MDS val not saved sucessfully");
		log.info("System Set Back delay Minimum MDS val saved sucessfully");
		boolean autohtroffSavebtnstutsmin1 = tss.getSavebtnstatus();
		softAssert.assertEquals(autohtroffSavebtnstutsmin1, false,
				"Save button is not disabled for System Set Back delay min MDS val ");
		log.info("Save button is disabled for minimum System Set Back delay MDS val");

		// check for System Set Back Delay delay Max value
		tss.clearSystemsetbckTmeDly();
		tss.setSystemsetbckTmeDly(mds.getMax1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"System Set Back  delay Maximum MDS val not saved sucessfully");
		log.info("System Set Back delay Maximum MDS val saved sucessfully");
		boolean autohtroffSavebtnstutsmax1 = tss.getSavebtnstatus();
		softAssert.assertEquals(autohtroffSavebtnstutsmax1, false,
				"Save button is not disabled for System Set Back delay max MDS val ");
		log.info("Save button is disabled for maximum System Set Back Delay delay MDS val");
		softAssert.assertAll();
	}

	@Test(priority = 9,enabled = true)
	@Feature("Verify MDS Auto Heater Off Time")
	@Description("System settings Auto Heater Off Time validations for MDS values")
	public void Test_Systemsettings_AutoHeaterOffTime_MDS() throws InterruptedException, IOException {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_AutoHeaterOffTme());

		// check for Auto HeaterOff Time delay default value
		softAssert.assertEquals(tss.getAutoHeatersOffTme(), mds.getDefault1(),
				"Auto HeaterOff Time delay is not set to Default time : " + mds.getDefault1());
		log.info("Auto HeaterOff Time delay is set to Default time  :" + mds.getDefault1());

		// check for Auto HeaterOff Time delay Min value
		tss.clearAutoHeatersOffTme();
		tss.setAutoHeatersOffTme(mds.getMin1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Auto HeaterOff Time delay Minimum MDS val not saved sucessfully");
		log.info("Auto HeaterOff Time delay Minimum MDS val saved sucessfully");
		boolean autohtroffSavebtnstutsmin1 = tss.getSavebtnstatus();
		softAssert.assertEquals(autohtroffSavebtnstutsmin1, false,
				"Save button is not disabled for Auto HeaterOff Time delay min MDS val ");
		log.info("Save button is disabled for minimum Auto HeaterOff Time delay MDS val");

		// check for Auto HeaterOff Time delay Max value
		tss.clearAutoHeatersOffTme();
		tss.setAutoHeatersOffTme(mds.getMax1());
		tss.clickSavebtn();
		softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Auto HeaterOff Time delay Maximum MDS val not saved sucessfully");
		log.info("Auto HeaterOff Time delay Maximum MDS val saved sucessfully");
		boolean autohtroffSavebtnstutsmax1 = tss.getSavebtnstatus();
		softAssert.assertEquals(autohtroffSavebtnstutsmax1, false,
				"Save button is not disabled for Auto HeaterOff Time delay max MDS val ");
		log.info("Save button is disabled for maximum Auto HeaterOff Time delay MDS val");

		softAssert.assertAll();

	}

}
