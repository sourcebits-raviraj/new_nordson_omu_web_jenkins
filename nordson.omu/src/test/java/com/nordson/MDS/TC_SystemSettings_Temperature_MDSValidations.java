package com.nordson.MDS;


import org.testng.Assert;
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
	ActionMethods Am=new ActionMethods();
	TC_LoginTest_DDT_001 lg;
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Temperature mds = new MDSGetterandSetters_Temperature();
	RetriveMDSdata_Temperature rmds = new RetriveMDSdata_Temperature();
	ReadConfig rcf = new ReadConfig();

	@Test(priority = 1,enabled = true)
	@Feature("System settings over temperature threshold set point validations for MDS values")
	@Description("Verify MDS Over Temperature Threshold set  values for Celsius Temperature Unit")
	public void Test_Systemsettings_OverTempThreshold_MDS_Celsius() throws Exception {

		tss = new TemperatureSystemSettings(driver);
		tss.createNewNORfile();
		tss.clickSystemSettingsBtn();
		
		tss.clickPreferencesBtn();
		
		log.info("Clicked on Preferences Link");
		
	
		tss.clickCelsiusTempUnit();
		
	
		tss.clickTemperatureBtn();
		

		// check for default values
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_OTT());
		
	
		System.out.println("Default value of Over temperature threshold unit in MDS file for celsius unit = "+mds.getDefault1());
		Am.sleepTime(1500);
	
		softAssert.assertEquals(tss.getOTTemperature(), mds.getDefault1(),
				"Over Temperature threshold temp is not set to Default value : " + mds.getDefault1());
		log.info("Over Temperature threshold temp is set to Default value  :" + mds.getDefault1());
		
		
		// check for Min value for OTT Celsius
		Am.sleepTime(1500);
		System.out.println("Minimum value of Over temperature threshold unit in MDS file for celsius unit = "+mds.getMin1());
		tss.clearOTTemperature();
		
		tss.setOTTemperature(mds.getMin1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			
			 Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for MDS min Over temperature threshold  Set point Celsius unit");
		   //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}
		// check for Max values for OTT Celsius
		
		tss.clearOTTemperature();
		
		System.out.println("Maximum value of Over temperature threshold unit in MDS file for celsius unit = "+mds.getMax1());
		tss.setOTTemperature(mds.getMax1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for MDS max Over temperature threshold celsius unit");
		  //  softAssert.assertEquals(tss.getSavebtnstatus(),false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// Check for Temperature unit
		if (tss.getOTTTemperatureunt().contains(mds.getPrmryunt().toUpperCase())) {
				Am.drawBorder(tss.OTTemperatureunt, driver);
			log.info("OTT is set to celsius unit");}
		else {
			Am.drawBorderFail(tss.OTTemperatureunt, driver);
			log.info("OTT is not set to celsius unit");}
	}

	@Test(priority = 2,enabled = true)
	@Feature("System settings under temperature threshold set point validations for MDS values")
	public void Test_Systemsettings_UnderTempThreshold_MDS_Celsius() throws Exception {

		// check for default values
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_UTT());
		
		Am.drawBorder(tss.UTTemperature, driver);
		
		System.out.println("Default value of Under temperature threshold unit in MDS file for celsius unit = "+mds.getDefault1());
		Am.sleepTime(1500);
		Am.drawBorder(tss.Temperature, driver);
		softAssert.assertEquals(tss.getUTTemperature(), mds.getDefault1(),
				"Under Temperature threshold temp is not set to Default value : " + mds.getDefault1());
		log.info("Under Temperature threshold temp is set to Default value  :" + mds.getDefault1());
		
		
		// check for Min value for UTT Celsius
		Am.sleepTime(1500);
		System.out.println("Minimum value of Under temperature threshold unit in MDS file for celsius unit = "+mds.getMin1());
		tss.clearUTTemperature();
		
		tss.setUTTemperature(mds.getMin1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for MDS min Under temperature threshold  Set point Celsius unit");
		}
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);}
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// check for Max values for UTT Celsius
		
		tss.clearUTTemperature();
		
		System.out.println("Maximum value of Under temperature threshold unit in MDS file for celsius unit = "+mds.getMax1());
		tss.setUTTemperature(mds.getMax1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for MDS max Under temperature threshold celsius unit");
		    
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}
	}

	@Test(priority = 3,enabled = true)
	@Feature("System settings temperature Set back set point validations for MDS values")
	@Description("Verify MDS Temperature Set back set  values for Celsius Temperature Unit")
	public void Test_Systemsettings_TemperatureSetbck_MDS_Celsius() throws Exception {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_Tmpstbck());
		
		// check for default values
		
		Am.drawBorder(tss.TemperatureSetback, driver);
		Am.sleepTime(1500);
		System.out.println("Default value of temperature set back unit in MDS file for celsius unit = "+mds.getDefault1());
		softAssert.assertEquals(tss.getTemperatureSetback(), mds.getDefault1(),
				"Temperature set back is not set to Default value : " + mds.getDefault1());
		log.info("Temperature set back is set to Default value : " + mds.getDefault1());

		// check for Min value for Temperature set back Celsius
		tss.clearTemperaturesetbck();
	
		System.out.println("Minimum value of temperature set back unit in MDS file for celsius unit = "+mds.getMin1());
	
		tss.setSetbckTemperature(mds.getMin1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Temperature set back min val of celsius unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// check for Max values for Temperature set back Celsius
		tss.clearTemperaturesetbck();
	
		System.out.println("Maximum value of temperature set back unit in MDS file for celsius unit = "+mds.getMax1());
	
		tss.setSetbckTemperature(mds.getMax1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Temperature set back max val of celsius unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);
		}
	}
	@Test(priority = 4,enabled = true)
	@Feature("System settings over temperature threshold set point validations for MDS values")
	@Description("Verify MDS Over Temperature Threshold set  values for Fahrenheit Temperature Unit")
	public void Test_Systemsettings_OverTempThreshold_MDS_Fahrenheit() throws Exception {
		
		Am.sleepTime(1800);
		Am.drawBorder(tss.Dashboard, driver);
		Am.sleepTime(2000);
		tss.clickDashboard();
		log.info("Clicked on dashboard to create Nor file");
		Thread.sleep(1000);
		tss.createNewNORfile();
		
		tss.clickSystemSettingsBtn();
		log.info("Clicked on System Settings button");
		Am.drawBorder(tss.Preferences, driver);
		tss.clickPreferencesBtn();
		
		log.info("Clicked on Preferences Link");
		
		Am.drawBorder(tss.FarhenitTemperatureunit, driver);
		tss.clickFahrenheitTempUnit();
		
		Am.drawBorder(tss.SAVE, driver);
		tss.clickSave();
		if (tss.getToastmsgststus() == true) {
			Am.drawBorder(tss.Toastmsg, driver);
			softAssert.assertEquals(tss.getToastmsg(), Constants.Preferencessucssmsg);
		} else
			Am.drawBorderFail(tss.Toastmsg, driver);
		log.info("Saved the preferences");
		Am.drawBorder(tss.Temperature, driver);
		tss.clickTemperatureBtn();
		
		// check for default values
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_OTT());
		
		Am.drawBorder(tss.OTTemperature, driver);
		Am.sleepTime(1500);
		System.out.println("Default value of Under temperature threshold unit in MDS file for Fahrenheit unit = "+mds.getDefault2());
		softAssert.assertEquals(tss.getOTTemperature(), mds.getDefault2(),
				"Over Temperature threshold temp is not set to Default value : " + mds.getDefault2());
		log.info("Over Temperature threshold temp is set to Default value  :" + mds.getDefault2());
		
		// check for Minimum MDS value
		tss.clearOTTemperature();
		
		System.out.println("Minimum value of over temperature threshold unit in MDS file for Fahrenheit unit = "+mds.getMin2());
		tss.setOTTemperature(mds.getMin2());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for over temperature threshold min val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// check for Maximum MDS value
		tss.clearOTTemperature();
		System.out.println("Maximum value of over temperature threshold unit in MDS file for Fahrenheit unit = "+mds.getMax2());
	
		tss.setOTTemperature(mds.getMax2());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for over temperature threshold max val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}
		// Check for Temperature unit
		if (tss.getOTTTemperatureunt().contains(mds.getScndryunt().toUpperCase())) {
			Am.drawBorder(tss.OTTemperatureunt, driver);
			log.info("OTT is set to Fahrenheit unit");}
		else {
			Am.drawBorderFail(tss.OTTemperatureunt, driver);
			log.info("OTT is not set to Fahrenheit unit"); }
		softAssert.assertAll();

	}
	@Test(priority = 5,enabled = true)
	@Feature("System settings under temperature threshold set point validations for MDS values")
	@Description("Verify MDS Under Temperature Threshold set  values for Fahrenheit Temperature Unit")
	public void Test_Systemsettings_UnderTempThreshold_MDS_Fahrenheit() throws Exception {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_UTT());
		// check for default values
		
		Am.drawBorder(tss.UTTemperature, driver);
		Am.sleepTime(1500);
		System.out.println("Default value of Under temperature threshold unit in MDS file for Fahrenheit unit = "+mds.getDefault2());
	
		softAssert.assertEquals(tss.getUTTemperature(), mds.getDefault2(),
				"Under Temperature threshold temp is not set to Default value : " + mds.getDefault2());
		log.info("Under Temperature threshold  temp is set to Default value  :" + mds.getDefault2());

		// check for Minimum MDS value
		tss.clearUTTemperature();
	
		System.out.println("Minimum value of Under temperature threshold unit in MDS file for Fahrenheit unit = "+mds.getMin2());
	
		tss.setUTTemperature(mds.getMin2());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Under temperature threshold min val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// check for Maximum MDS value
		tss.clearUTTemperature();
	
		System.out.println("Maximum value of Under temperature threshold unit in MDS file for Fahrenheit unit = "+mds.getMax2());
	
		tss.setUTTemperature(mds.getMax2());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Under temperature threshold max val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

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
	public void Test_Systemsettings_TemperatureSetbck_MDS_Fahrenheit() throws Exception {
		
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_Tmpstbck());
		// check for default values
		
		Am.drawBorder(tss.TemperatureSetback, driver);
		Am.sleepTime(1500);
		System.out.println("Default value of temperature set back unit in MDS file for Fahrenheit unit = "+mds.getDefault2());
	
		softAssert.assertEquals(tss.getTemperatureSetback(), mds.getDefault2(),
				"temperature set back is not set to Default value : " + mds.getDefault2());
		log.info("Otemperature set back  is set to Default value  :" + mds.getDefault2());

		// check for Minimum MDS value
		tss.clearTemperaturesetbck();
	
		System.out.println("Minimum value of temperature set back unit in MDS file for Fahrenheit unit = "+mds.getMin2());
	
		tss.setSetbckTemperature(mds.getMin2());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for temperature set back min val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}
		// check for Maximum MDS value
		tss.clearTemperaturesetbck();
	
		System.out.println("Maximum value of temperature set back unit in MDS file for Fahrenheit unit = "+mds.getMax2());
	
		tss.setSetbckTemperature(mds.getMax2());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for temperature set back max val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}
	}

	@Test(priority = 7,enabled = true)
	@Feature("System settings SmartMelt Time delay validations for MDS values")
	@Description("Verify MDS SmartMelt Time delay")
	public void Test_Systemsettings_SmartMeltTimeDelay_MDS() throws Exception {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_SMTTmeDly());

		// check for Smart Melt Time delay default value
	
		System.out.println("Default value of Smart Melt Time Delay in MDS file = "+mds.getDefault1());
	
		Am.drawBorder(tss.SmrtMeltTmeDly, driver);
		softAssert.assertEquals(tss.getSmartMeltTimeDly(), mds.getDefault1(),
				"Smart Melt Time delay is not set to Default time : " + mds.getDefault1());
		log.info("Smart Melt Time delay is set to Default time  :" + mds.getDefault1());

		// check for Smart Melt Time delay Min value
		tss.clearSmartMeltTimeDly();
	
		System.out.println("Minimum value of Smart Melt Time Delay in MDS file = "+mds.getMin1());
	
		tss.setSmartMeltTimeDly(mds.getMin1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Smart Melt Time Delay default val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// check for Smart Melt Time delay Max value
		
		tss.clearSmartMeltTimeDly();
	
		System.out.println("Minimum value of Smart Melt Time Delay in MDS file = "+mds.getMax1());
	
		tss.setSmartMeltTimeDly(mds.getMax1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Smart Melt Time Delay min val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}
		softAssert.assertAll();
	}

	@Test(priority = 8,enabled = true)
	@Feature("Verify MDS System Set Back Delay Time")
	@Description("System System Set Back Delay Time validations for MDS values")
	public void Test_Systemsettings_SystemSetBackDelay_MDS() throws Exception {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_SystemSetbckDly());

		// check for System Set Back Delay delay default value
	
		System.out.println("Default value of System Set Back Delay in MDS file = "+mds.getDefault1());
	
		Am.drawBorder(tss.SystemsetBckDly, driver);
		softAssert.assertEquals(tss.getSystemSetbackTmeDly(), mds.getDefault1(),
				"System Set Back delay is not set to Default time : " + mds.getDefault1());
		log.info("System Set Back delay is set to Default time  :" + mds.getDefault1());

		// check for System Set Back Delay delay Min value
	
		System.out.println("Minimum value of System Set Back Delay in MDS file = "+mds.getMin1());
	
		tss.clearSystemsetbckTmeDly();
		tss.setSystemsetbckTmeDly(mds.getMin1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Smart Melt Time Delay min val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// check for System Set Back Delay delay Max value
	
		System.out.println("Maximum value of System Set Back Delay in MDS file = "+mds.getMax1());
	
		tss.clearSystemsetbckTmeDly();
		tss.setSystemsetbckTmeDly(mds.getMax1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Smart Melt Time Delay max val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}
		softAssert.assertAll();
	}

	@Test(priority = 9,enabled = true)
	@Feature("Verify MDS Auto Heater Off Time")
	@Description("System settings Auto Heater Off Time validations for MDS values")
	public void Test_Systemsettings_AutoHeaterOffTime_MDS() throws Exception {

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_AutoHeaterOffTme());

		// check for Auto HeaterOff Time delay default value
	
		System.out.println("Default value of Auto HeaterOff Time delay in MDS file = "+mds.getDefault1());
	
		Am.drawBorder(tss.AutoHterOffTme, driver);
		softAssert.assertEquals(tss.getAutoHeatersOffTme(), mds.getDefault1(),
				"Auto HeaterOff Time delay is not set to Default time : " + mds.getDefault1());
		log.info("Auto HeaterOff Time delay is set to Default time  :" + mds.getDefault1());

		// check for Auto HeaterOff Time delay Min value
	
		System.out.println("Minimum value of Auto HeaterOff Time delay in MDS file = "+mds.getMin1());
	
		tss.clearAutoHeatersOffTme();
		tss.setAutoHeatersOffTme(mds.getMin1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Auto HeaterOff Time delay min val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		// check for Auto HeaterOff Time delay Max value
	
		System.out.println("Maximum value of Auto HeaterOff Time delay in MDS file = "+mds.getMax1());
	
		tss.clearAutoHeatersOffTme();
		tss.setAutoHeatersOffTme(mds.getMax1());
		tss.clickSavebtn();
		if (tss.getToastmsgststus() == true) {
			 
		    Am.drawBorder(tss.Toastmsg, driver);
		    softAssert.assertEquals(tss.getToastmsg(), Constants.Sucssmsg,
				"Toast msg is not shown for Auto HeaterOff Time delay max val of Fahrenheit unit");
		    //softAssert.assertEquals(tss.getSavebtnstatus(), false);
		    }
		else {
		Am.drawBorderFail(tss.Toastmsg, driver);}

		softAssert.assertAll();
		
	}
   
}
