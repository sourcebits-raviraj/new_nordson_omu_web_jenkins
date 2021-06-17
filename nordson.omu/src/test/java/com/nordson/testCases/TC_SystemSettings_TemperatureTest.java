package com.nordson.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.TemperatureSystemSettings;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.XLUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;


@Epic("Regression Tests")
@Feature(" System settings Temperature verification")
public class TC_SystemSettings_TemperatureTest extends TC_LoginTest_DDT_001 {

	TemperatureSystemSettings Tss;
	ActionMethods Am;
	TC_LoginTest_DDT_001 lg;

	public static String OTTtempcsl = "";
	public static String OTTtempfarnh = "";

	public static String UTTtempcsl = "";
	public static String UTTtempfarnh = "";

	public static String tmpstbckcsl = "";
	public static String tmpstbckfarnh = "";

	private SoftAssert softAssert = new SoftAssert();

	@Test(priority = 1)
	@Feature("Temperature System settings Default values validations")
	@Description("Verify the Default Values for Celsius system settings Temperature Unit")
	public void Test_Celsius_SysDefaultSettings() throws InterruptedException, IOException {

		Tss = new TemperatureSystemSettings(driver);

		Tss.clickSetUpToolBtn();
		log.info("Clicked on SetUp Tool button");

		Tss.clickCreateNewBtn();
		log.info("Clicked on CreateNew button");	

		Tss.clickSubmitBtn();
		log.info("Clicked on Submit button");
		Thread.sleep(1000);
		
		Tss.clickSystemSettingsBtn();
		log.info("Clicked on System settings button");

		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button");
		
		// check for default values

		softAssert.assertEquals(Tss.getOTTemperature(), Constants.OTTemp,
				"Over Temperature threshold global temp is not set to Default value : " + Constants.OTTemp);
		log.info("Over Temperature threshold global temp is set to Default value  :" + Constants.OTTemp);

		softAssert.assertEquals(Tss.getUTTemperature(), Constants.UTTemp,
				"Under Temperature threshold global temp is not set to Default value : " + Constants.UTTemp);
		log.info("Over Temperature threshold global temp is set to Default value  :" + Constants.UTTemp);


		softAssert.assertEquals(Tss.getTemperatureSetback(),Constants.Tempsetback,
				"Temperature set back for global temp is not set to Default value : " + Constants.Tempsetback);
		log.info("Temperature set back for global temp is set to Default value  :" + Constants.Tempsetback);

		softAssert.assertAll();

	}

	@Test(priority = 2)
	@Description("Verify the Default Values for Farnheit system settings Temperature Unit")
	public void Test_Farnheit_SysDefaultSettings() throws InterruptedException, IOException {

		// Farhenit Validations For default Values

		Tss.clickPreferencesBtn();

		Tss.clickFarhenitUnit();
		log.info("Temperature Unit Farhenit is selected by clicking");

		Tss.clickSave();
		log.info("Saved Farhenit OPtion");
		
		
		if(Tss.getToastmsgststus()==true)
			log.info("Prefrences updated sucessfully");	
		else
			log.info("Prefrences not updated sucessfully");
	

		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button to validate farhenit default options");

		Thread.sleep(2000);	
		
		softAssert.assertEquals(Tss.getOTTemperature(), ActionMethods.getConversionToFahrenheitSys(Constants.OTTemp),
				"Over Temperature threshold global temp is not set to Default Farhenit value : " +  ActionMethods.getConversionToFahrenheitSys(Constants.OTTemp));
		log.info("Over Temperature threshold global temp is set to Default Farhenit value  :" +  ActionMethods.getConversionToFahrenheitSys(Constants.OTTemp));

		softAssert.assertEquals(Tss.getUTTemperature(), ActionMethods.getConversionToFahrenheitSys(Constants.UTTemp),
				"Under Temperature threshold global temp is not set to Default Farhenit value : " + ActionMethods.getConversionToFahrenheitSys(Constants.UTTemp));
		log.info("Over Temperature threshold global temp is set to Default Farhenit value  :" + ActionMethods.getConversionToFahrenheitSys(Constants.UTTemp));

		softAssert.assertEquals(Tss.getTemperatureSetback(), ActionMethods.getConversionToFahrenheitSys(Constants.Tempsetback),
				"Temperature set back for global temp is not set to Default Farhenit value : " + ActionMethods.getConversionToFahrenheitSys(Constants.Tempsetback));
		log.info("Temperature set back for global temp is set to Default Farhenit value  :" + ActionMethods.getConversionToFahrenheitSys(Constants.Tempsetback));

		XLUtils.setExcelSheetNm("OTTCelsius");
		softAssert.assertAll();

	}

	@Test(priority = 3, dataProvider = "OTTValues_Celsius", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Feature("Temperature System settings over temperature Threshold validations")
	@Description("Verify the boundary Values for Celsius Temperature Unit of OTT field")
	public void Test_Celsius_OTT_Syssettings(String minlssval, String maxplusval, String Inrngval)
			throws InterruptedException, IOException {

		Tss.clickPreferencesBtn();

		Tss.clickCelsiusUnit();
		log.info("Temperature Unit Celsius is selected by clicking");

		Tss.clickSave();
     	log.info("Saved Celsius Option");
     	
		if(Tss.getToastmsgststus()==true)
			log.info("Prefrences updated sucessfully");	
		else
			log.info("Prefrences not updated sucessfully");
		

		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button to validate Celsius OTT Min and max values");
		Thread.sleep(1500);

		// null value validations

		Tss.clearOTTemperature();
        Tss.clickSavebtn();
 
		String tststusclrerrmsg = Tss.getToastmsg();
		softAssert.assertEquals(Constants.OTTErrmsg, tststusclrerrmsg,
				"Toast error message is shown up for max OTTemp Celsius val");
		log.info("Toast error message is shown up for null OTTemp val save for celsius unit");

		boolean stusclrerrmsg = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusclrerrmsg, " Error message is shown up for max OTTemp Celsius val");
		log.info("Error message is shown up for null OTTemp val save for celsius unit");

		// min-1 val

		Tss.setOTTemperature(minlssval);

		Tss.clickSavebtn();

		String tststusminerr = Tss.getToastmsg();
		softAssert.assertEquals(tststusminerr,Constants.OTTErrmsg,"Toast error message is shown up for for celsius unit");
		log.info("Toast error message is shown up for OTTemp min val for celsius unit");

		boolean stusminerr = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusminerr, "Error message is shown up for for celsius unit");
		log.info("Error message is shown up for OTTemp min val for celsius unit");

		// max+1 val

		Tss.clearOTTemperature();

		Tss.setOTTemperature(maxplusval);

		Tss.clickSavebtn();

		String tststusmaxerr = Tss.getToastmsg();
		softAssert.assertEquals(Constants.OTTErrmsg, tststusmaxerr,
				"Toast error message is shown up for OTTemp max val for celsius unit");
		log.info("Toast error message is shown up for max val for celsius unit");

		boolean stusmaxerr = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusmaxerr, "Error message is shown up for OTTemp max val for celsius unit");
		log.info("Error message is shown up for max val for celsius unit");

		// In Range value testing

		Tss.clearOTTemperature();

		Tss.setOTTemperature(Inrngval);

		Tss.clickSavebtn();

		String tststus = Tss.getToastmsg();
		softAssert.assertEquals(tststus,Constants.Sucssmsg, 
				"Toast msg for In range values are saved successfully for OTTemp for celsius unit");
		log.info("Toast msg for In range values are saved successfully for celsius unit");

		OTTtempcsl = Tss.getOTTemperature();
		softAssert.assertAll();

	}

	@Test(priority = 4)
	@Description("verfication of celsius to farnheit conversion OTT field")
	public void Test_Celsius_Tofarnhit_Conversion_OTTfield() throws InterruptedException {

		Tss.clickPreferencesBtn();
		Thread.sleep(2000);

		Tss.clickFarhenitUnit();
		log.info("Temperature Unit Farhenit is selected by clicking to verfiy conversion");

		Tss.clickSave();
		if(Tss.getToastmsgststus()==true)
			log.info("Prefrences updated sucessfully");	
		else
			log.info("Prefrences not updated sucessfully");
	

		log.info("Saved Farhenit Option to verfiy conversion");

		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button to validate OTT Celsius to farhenit conversion");

		String cvtfh = ActionMethods.getConversionToFahrenheitSys(OTTtempcsl);
		String OTTtemp = Tss.getOTTemperature();
		softAssert.assertEquals(cvtfh, OTTtemp, "Celsius to Farnhit unit conversion not correct");
		log.info("Celsius to Farnhit unit conversion for OTT is correct");
		XLUtils.setExcelSheetNm("OTTFarnhit");
		softAssert.assertAll();

	}

	@Test(priority = 5, dataProvider = "OTTValues_Farnheit", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Description("Verify the boundary Values for farnheit Temperature Unit of OTT field")
	public void Test_Farnhit_OTT_Syssettings(String minlssvalfh, String maxplusvalfh, String Inrngvalfh)
			throws InterruptedException, IOException {

		// Farhenit Validations For null,min-1,max+1,Inrange Values

		// Null Value Validations for farnhit

		Tss.clearOTTemperature();

		Tss.clickSavebtn();

		String tststusclrerrmsgfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.OTTErrmsgFH, tststusclrerrmsgfh,
				"Toast error message is shown up for max OTTemp val for Farhnit unit");
		log.info("Toast error message is shown up for null OTTemp val save for Farhnit");

		boolean stusclrerrmsgfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusclrerrmsgfh, "Error message is shown up for max OTTemp val for Farhnit unit");
		log.info("Error message is shown up for null OTTemp val save for Farhnit");

		// min-1 val

		Tss.setOTTemperature(minlssvalfh);

		Tss.clickSavebtn();

		String tststusminerrfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.OTTErrmsgFH, tststusminerrfh,
				"Toast error message is shown up for Farhnit unit");
		log.info("Toast error message is shown up for OTTemp min val Farhnit unit");

		boolean stusminerrfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusminerrfh, "Error message is shown up for Farhnit unit");
		log.info("Error message is shown up for OTTemp min val Farhnit unit");

		// max+1 val

		Tss.clearOTTemperature();

		Tss.setOTTemperature(maxplusvalfh);

		Tss.clickSavebtn();

		String tststusmaxerrfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.OTTErrmsgFH, tststusmaxerrfh,
				"Toast error message is shown up for OTTemp max val Farhnit unit");
		log.info("Toast error message is shown up for max val Farhnit unit");

		boolean stusmaxerrfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusmaxerrfh, "Error message is shown up for OTTemp max val Farhnit unit");
		log.info("Error message is shown up for max val Farhnit unit");

		// In Range value testing

		Tss.clearOTTemperature();
    	Tss.setOTTemperature(Inrngvalfh);

		Tss.clickSavebtn();
		String tststusfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.Sucssmsg, tststusfh,
				"Toastmsg for In range values are saved successfully for OTTemp Farhnit unit");
		log.info("Toast msg for In range values are saved successfully for Farhnit unit");

		boolean stusfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(false, stusfh, "In range values are saved successfully for OTTemp Farhnit unit");
		log.info("In range values are saved successfully for Farhnit unit");

		OTTtempfarnh = Tss.getOTTemperature();

		softAssert.assertAll();

	}

	@Test(priority = 6)
	@Description("Verification of Farnheit to Celsius unit conversion for OTT field")
	public void Test_Farnhit_ToCelsius_Conversion_OTTfield() throws InterruptedException {

		Tss.clickPreferencesBtn();
		Thread.sleep(2000);

		Tss.clickCelsiusUnit();
		log.info("Temperature Unit Celsius is selected by clicking to verfiy conversion");

		Tss.clickSave();
        log.info("Saved Celsius Option to verfiy conversion");
		   

		if(Tss.getToastmsgststus()==true)
			log.info("Prefrences updated sucessfully");	
		else
			log.info("Prefrences not updated sucessfully");
	
		

		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button to validate OTT farhenit to celsius conversion");

		String fhtocelsius = ActionMethods.getConversionToCelsiusSys(OTTtempfarnh);
		String OTTtemp = Tss.getOTTemperature();
		softAssert.assertEquals(OTTtemp, fhtocelsius, "Farnhit to Celsius unit conversion not correct");
		log.info("Farnhit to Celsius unit conversion for OTT is correct");

		XLUtils.setExcelSheetNm("UTTCelsius");
		softAssert.assertAll();

	}

	@Test(priority = 7, dataProvider = "UTTValues_Celsius", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Feature("Temperature System settings Under temperature Threshold Field validations")
	@Description("Verify the boundary Values for celsius Temperature Unit of UTT field")
	public void Test_Celsius_SysMinmaxForUTT(String minlssval, String maxplusval, String Inrngval)
			throws InterruptedException, IOException {

		// Null Values Error msg Test
		Tss.clearUTTemperature();
		Thread.sleep(1000);
		Tss.clickSavebtn();
		String tststusclrerrmsg = Tss.getToastmsg();
		softAssert.assertEquals(Constants.UTTErrmsg, tststusclrerrmsg,
				"Toast error message is not shown up for null valus UTTemp Celsius val");
		log.info("Toast error message is shown up for null UTTemp val for Celsius unit after save");

		boolean stusclrerrmsg = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusclrerrmsg, "Error message is not shown up for max UTTemp Celsius val");
		log.info("Error message is shown up for null UTTemp val for Celsius unit after save");

		// min-1 val

		Tss.clearUTTemperature();

		Tss.setUTTemperature(minlssval);

		Tss.clickSavebtn();

		String tststusminerr = Tss.getToastmsg();
		softAssert.assertEquals(Constants.UTTErrmsg, tststusminerr,
				"Toast error message is not shown up for UTTemp Celsius unit");
		log.info("Toast error message is shown up for min UTTemp val for Celsius unit");

		// max+1 val

		Tss.clearUTTemperature();

		Tss.setUTTemperature(maxplusval);

		Tss.clickSavebtn();

		String tststusmaxerr = Tss.getToastmsg();
		softAssert.assertEquals(Constants.UTTErrmsg, tststusmaxerr,
				"Toast error message is not shown up for Celsius unit max val");
		log.info("Toast error message is shown up for max UTTemp Celsius unit val");

		boolean stusmaxerr = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusmaxerr, "Error message is not shown up for Celsius unit max val");
		log.info("Error message is shown up for max UTTemp Celsius unit val");

		// In Range value testing

		Tss.clearUTTemperature();

		Tss.setUTTemperature(Inrngval);

		Tss.clickSavebtn();

		String tstmsgstus = Tss.getToastmsg();
		softAssert.assertEquals(Constants.Sucssmsg, tstmsgstus,
				"Toast msg for In range values are not saved successfully for UTTemp Celsius unit");
		log.info("Toast msg for In range values are saved successfully for UTTemp Celsius unit");

		boolean stus = Tss.getSavebtnstatus();
		softAssert.assertEquals(false, stus, "In range values are not saved successfully for UTTemp Celsius unit");
		log.info("In range values are saved successfully for UTTemp Celsius unit");

		UTTtempcsl = Tss.getUTTemperature();

		softAssert.assertAll();

	}

	@Test(priority = 8)
	@Description("Verify the celsius to farnheit temperature Unit conversion for UTT field")
	public void Test_Celsius_Tofarnhit_Conversion_UTTfield() throws InterruptedException {

		Tss.clickPreferencesBtn();
		Thread.sleep(1000);

		Tss.clickFarhenitUnit();
		log.info("Temperature Unit Farhenit is selected by clicking to verfiy conversion");

		Tss.clickSave();
        log.info("Saved Farhenit Option to verfiy conversion");
        
        
        if (Tss.getToastmsgststus()==true)
        	log.info("Preferences are sucessfully updated");
        else
        	log.info("Preferences are not sucessfully updated");
	
		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button to validate UTT Celsius to farhenit conversion");
  
		String cvtfh = ActionMethods.getConversionToFahrenheitSys(UTTtempcsl);
		String UTTtemp = Tss.getUTTemperature();
		softAssert.assertEquals(UTTtemp, cvtfh, "Celsius to Farnhit unit conversion not correct");
		log.info("Celsius to Farnhit unit conversion for UTT is correct");

		XLUtils.setExcelSheetNm("UTTFarnhit");
		softAssert.assertAll();

	}

	@Test(priority = 9, dataProvider = "UTTValues_Farnheit", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Description("Verify the boundary Values for farnheit Temperature Unit of UTT field")
	public void Test_Farnhit_SysMinmaxForUTT(String minlssvalfh, String maxplusvalfh, String Inrngvalfh)
			throws InterruptedException, IOException {

		// Farhenit Minmax Val testing for Under Threshold Field

		// Null Values Error msg Test

		Tss.clearUTTemperature();

		Tss.clickSavebtn();

		String tststusclrerrmsgfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.UTTErrmsgFH, tststusclrerrmsgfh,
				"Toast error message is not shown up for max UTTemp Farhenit val");
		log.info("Toast error message is shown up for null UTTemp val for Farhenit unit after save");

		boolean stusclrerrmsgfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusclrerrmsgfh, "Error message is not shown up for max UTTemp Farhenit val");
		log.info("Error message is shown up for null UTTemp val for Farhenit unit after save");

		// min-1 val

		Tss.clearUTTemperature();

		Tss.setUTTemperature(minlssvalfh);

		Tss.clickSavebtn();

		String tststusminerrfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.UTTErrmsgFH, tststusminerrfh,
				"Toast error message is not shown up for UTTemp Farhenit unit");
		log.info("Toast error message is shown up for min UTTemp val for Farhenit unit");

		boolean stusminerrfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusminerrfh, "Error message is not shown up for UTTemp Farhenit unit");
		log.info("Error message is shown up for min UTTemp val for Farhenit unit");

		// max+1 val

		Tss.clearUTTemperature();

		Tss.setUTTemperature(maxplusvalfh);

		Tss.clickSavebtn();

		String tststusmaxerrfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.UTTErrmsgFH, tststusmaxerrfh,
				"Toast error message is not shown up for Farhenit unit max val");
		log.info("Toast error message is shown up for max UTTemp Farhenit unit val");

		boolean stusmaxerrfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusmaxerrfh, "Error message is not shown up for Farhenit unit max val");
		log.info("Error message is shown up for max UTTemp Farhenit unit val");

		// In Range value testing

		Tss.clearUTTemperature();

		Tss.setUTTemperature(Inrngvalfh);

		Tss.clickSavebtn();

		String tststusfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.Sucssmsg, tststusfh,
				"Toast msgIn range values are not saved successfully for UTTemp Farhenit unit");
		log.info("Toast msg In range values are saved successfully for UTTemp Farhenit unit");

		boolean stusfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(false, stusfh, "In range values are not saved successfully for UTTemp Farhenit unit");
		log.info("In range values are saved successfully for UTTemp Farhenit unit");

		UTTtempfarnh = Tss.getUTTemperature();

		softAssert.assertAll();

	}

	@Test(priority = 10)
	@Description("Verify the farnheit to celsius Temperature Unit convesion of UTT field")
	public void Test_Farnhit_ToCelsius_Conversion_UTTfield() throws InterruptedException {

		Tss.clickPreferencesBtn();
		Thread.sleep(2000);

		Tss.clickCelsiusUnit();
		log.info("Temperature Unit Celsius is selected by clicking to verfiy conversion");

		Tss.clickSave();
		log.info("Saved Celsius Option to verfiy conversion");
		
		  
        if (Tss.getToastmsgststus()==true)
        	log.info("Preferences are sucessfully updated");
        else
        	log.info("Preferences are not sucessfully updated");
		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button to validate OTT farhenit to celsius conversion");

		String fhtocelsius = ActionMethods.getConversionToCelsiusSys(UTTtempfarnh);
		String UTTtemp = Tss.getUTTemperature();
		softAssert.assertEquals(UTTtemp, fhtocelsius, "Farnhit to Celsius unit conversion not correct");
		log.info("Farnhit to Celsius unit conversion for OTT is correct");

		XLUtils.setExcelSheetNm("TempsetbckCelsius");
		softAssert.assertAll();

	}

	@Test(priority = 11, dataProvider = "TempstbckValues_Celsius", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Feature("Verfication of Boundary values for temperature system settings")
	@Description("Verification of boundary Values for farnheit Temperature Unit of temperature set back field")
	public void Test_Celsius_SysMinmaxForTmpstbck(String minlssval, String maxplusval, String Inrngval)
			throws InterruptedException, IOException {

		// Null Values validation for Celsius Unit

		Tss.clearTemperaturesetbck();

		Tss.clickSavebtn();

		String tststusclrerrmsg = Tss.getToastmsg();
		softAssert.assertEquals(Constants.TempstbckErrmsg, tststusclrerrmsg,
				"Toast error message is not shown up for max Tempsetbck val for celsuis unit");
		log.info("Toast error message is shown up for null Tempsetbck val save celsuis unit");

		boolean stusclrerrmsg = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusclrerrmsg,
				"Error message is not shown up for max Tempsetbck val for celsuis unit");
		log.info("Error message is shown up for null Tempsetbck val save celsuis unit");

		// min-1 val

		Tss.clearTemperaturesetbck();

		Tss.setSetbckTemperature(minlssval);

		Tss.clickSavebtn();

		String tststusminerr = Tss.getToastmsg();
		softAssert.assertEquals(Constants.TempstbckErrmsg, tststusminerr,
				"Toast error message is not shown up for Tempsetbck celsuis unit");
		log.info("Toast error message is shown up for min Tempsetbck val celsuis unit");

		boolean stusminerr = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusminerr, "Error message is not shown up for Tempsetbck celsuis unit");
		log.info("Error message is shown up for min Tempsetbck val celsuis unit");

		// max+1 val

		Tss.clearTemperaturesetbck();

		Tss.setSetbckTemperature(maxplusval);

		Tss.clickSavebtn();

		String tststusmaxerr = Tss.getToastmsg();
		softAssert.assertEquals(Constants.TempstbckErrmsg, tststusmaxerr,
				"Toast error message is not shown up for max Tempsetbck val celsuis unit ");
		log.info("Toast error message is shown up for max Tempsetbck val celsuis unit");

		boolean stusmaxerr = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusmaxerr, "Error message is not shown up for max Tempsetbck val celsuis unit ");
		log.info("Error message is shown up for max Tempsetbck val celsuis unit");

		// In Range value testing

		Tss.clearTemperaturesetbck();

		Tss.setSetbckTemperature(Inrngval);

		Tss.clickSavebtn();

		String tststus = Tss.getToastmsg();
		softAssert.assertEquals(Constants.Sucssmsg, tststus,
				"Toast msg for In range values are not saved successfully for Tempsetbck celsuis unit");
		log.info("Toast msg for In range values are saved successfully for Tempsetbck celsuis unit");

		boolean stus = Tss.getSavebtnstatus();
		softAssert.assertEquals(false, stus, "In range values are not saved successfully for Tempsetbck celsuis unit");
		log.info("In range values are saved successfully for Tempsetbck celsuis unit");

		tmpstbckcsl = Tss.getTemperatureSetback();

		softAssert.assertAll();

	}

	@Test(priority = 12)
	@Description("Verfication of celsius to farnheit conversion for temperature set back field in system settings")
	public void Test_Celsius_Tofarnhit_Conversion_Tempstbck() throws InterruptedException {

	    Thread.sleep(1500);
		Tss.clickPreferencesBtn();
		 Thread.sleep(1200);

		Tss.clickFarhenitUnit();
		log.info("Temperature Unit Farhenit is selected by clicking to verfiy conversion");

		Tss.clickSave();

		log.info("Saved Farhenit Option to verfiy conversion");

		if(Tss.getToastmsgststus()==true)
			log.info("Prefrences updated sucessfully");	
		else
			log.info("Prefrences not updated sucessfully");
	

		Tss.clickTemperatureBtn();
		log.info("Clicked on Temperature in System settings button to validate OTT Celsius to farhenit conversion");

		String cvtfh = ActionMethods.getConversionToFahrenheitSys(tmpstbckcsl);
		String OTTtemp = Tss.getOTTemperature();
		softAssert.assertEquals(OTTtemp, cvtfh, "Celsius to Farnhit unit conversion not correct");
		log.info("Celsius to Farnhit unit conversion for OTT is correct");

		XLUtils.setExcelSheetNm("TempsetbckFarnhit");
		softAssert.assertAll();

	}

	@Test(priority = 13, dataProvider = "TempstbckValues_Farnheit", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Description("Verification of boundary Values for farnheit Temperature Unit of temperature set back field")
	public void Test_Farnhit_SysMinmaxForTmpstbck(String minlssvalfh, String maxplusvalfh, String Inrngvalfh)
			throws InterruptedException, IOException {

		// Farhenit Validations for Temperature Set Back
		// Null Values validation for farhenit Unit

		Tss.clearTemperaturesetbck();

		Tss.clickSavebtn();

		String tststusclrerrmsgfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.TempstbckErrmsgFH, tststusclrerrmsgfh,
				"Toast error message is not shown up for max Tempsetbck val for farhenit unit");
		log.info("Toast Error message is shown up for null Tempsetbck val save celsuis unit");

		boolean stusclrerrmsgfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusclrerrmsgfh,
				"Error message is not shown up for max Tempsetbck val for farhenit unit");
		log.info("Error message is shown up for null Tempsetbck val save farhenit unit");

		// min-1 val

		Tss.clearTemperaturesetbck();

		Tss.setSetbckTemperature(minlssvalfh);

		Tss.clickSavebtn();

		String tststusminerrfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.TempstbckErrmsgFH, tststusminerrfh,
				"Toast error message is not shown up for Tempsetbck farhenit unit");
		log.info("Toast error message is shown up for min Tempsetbck val celsuis unit");

		boolean stusminerrfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusminerrfh, "Error message is not shown up for Tempsetbck farhenit unit");
		log.info("Error message is shown up for min Tempsetbck val celsuis unit");

		// max+1 val

		Tss.clearTemperaturesetbck();

		Tss.setSetbckTemperature(maxplusvalfh);

		Tss.clickSavebtn();

		String tststusmaxerrfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.TempstbckErrmsgFH, tststusmaxerrfh,
				"Toast error message is not shown up for max Tempsetbck val farhenit unit ");
		log.info("Toast error message is shown up for max Tempsetbck val celsuis unit");

		boolean stusmaxerrfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(true, stusmaxerrfh,
				"Error message is not shown up for max Tempsetbck val farhenit unit ");
		log.info("Error message is shown up for max Tempsetbck val celsuis unit");

		// In Range value testing

		Tss.clearTemperaturesetbck();

		Tss.setSetbckTemperature(Inrngvalfh);

		Tss.clickSavebtn();

		String tststusfh = Tss.getToastmsg();
		softAssert.assertEquals(Constants.Sucssmsg, tststusfh,
				"Toast msg for In range values are not saved successfully for Tempsetbck farhenit unit");
		log.info("Toast msg for In range values are saved successfully for Tempsetbck farhenit unit");

		boolean stusfh = Tss.getSavebtnstatus();
		softAssert.assertEquals(false, stusfh,
				"In range values are not saved successfully for Tempsetbck farhenit unit");
		log.info("In range values are saved successfully for Tempsetbck farhenit unit");

		tmpstbckfarnh = Tss.getTemperatureSetback();

		softAssert.assertAll();

	}

	@Test(priority = 14)
	@Description("Verfication of fanheit to celsius conversion for temperature set back field in system settings")
	public void Test_Farnhit_ToCelsius_Conversion_Tempstbck() throws InterruptedException {

		Tss.clickPreferencesBtn();
		Thread.sleep(2000);

		Tss.clickCelsiusUnit();
		log.info("Temperature Unit Celsius is selected by clicking to verfiy conversion");

		Tss.clickSave();

		log.info("Saved Celsius Option to verfiy conversion");
		

		if(Tss.getToastmsgststus()==true)
			log.info("Prefrences updated sucessfully");	
		else
			log.info("Prefrences not updated sucessfully");
	

		Tss.clickTemperatureBtn();
		log.info(
				"Clicked on Temperature in System settings button to validate tempstbck farhenit to celsius conversion");

		String fhtocelsius = ActionMethods.getConversionToCelsiusSys(tmpstbckfarnh);

		String tmpstbck = Tss.getTemperatureSetback();
		softAssert.assertEquals(tmpstbck, fhtocelsius, "Farnhit to Celsius unit conversion not correct for tmpstbck");
		log.info("Farnhit to Celsius unit conversion for tmpstbck is correct");

		softAssert.assertAll();

	}

}
