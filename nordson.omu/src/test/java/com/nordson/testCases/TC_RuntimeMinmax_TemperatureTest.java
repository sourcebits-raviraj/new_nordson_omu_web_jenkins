package com.nordson.testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.TemperatureRuntimeSettings;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.XLUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Regression Tests")
@Feature("Temperature Runtime settings Min and Max Validations with different combinations of data")
public class TC_RuntimeMinmax_TemperatureTest extends TC_LoginTest_DDT_001 {

	TemperatureRuntimeSettings Trs;
	ActionMethods Am;
	TC_LoginTest_DDT_001 lg;
	SoftAssert softAssert = new SoftAssert();
	

	static String Tankpoint = "";
	static String Manifoldpoint = "";
	static String Hosepnt = "";
	static String Applicatorpnt = "";
	
	@Test(priority = 0)
	public void ExcelSheetName()
			throws Exception {
		XLUtils.setExcelSheetNm("GlobalPointCelsius");
	}

	// Global Point Validations
	@Story("Temperature Runtimesettings Global Point validations with boundary values in celsius unit")
	@Test(priority = 1, dataProvider = "GlobalPointValues_Celsius", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void Test_Celsius_RuntimeMinmaxGlobalPointValidations(String lessminval, String moremaxval, String Inrng)
			throws InterruptedException, IOException {

		Trs = new TemperatureRuntimeSettings(driver);

		Trs.clickSetUpToolBtn();
		log.info("Clicked on SetUp Tool button");

		Trs.clickCreateNewBtn();
		log.info("Clicked on CreateNew button");

		Trs.clickSubmitBtn();
		log.info("Clicked on Submit button");

		Trs.clickSystemSettingsBtn();
		log.info("Clicked on System settings button");

		Trs.clickPreferencesBtn();
		log.info("Clicked on Preferences button");

		Trs.clickCelsiusUnit();
		log.info("Temperature Unit celsius is selected");

		Trs.RuntimeSettingsBtn();
		log.info("Clicked on Runtime settings button to verfiy Global set temperature for celsius unit");

		Trs.ZoneTemperatureBtn();
		log.info("Clicked on Zone temperature button to setglobal min value for celsius unit");

		// setting global point to Min-1 value and validating tank,Manifold,hose and
		// applicator values

		Trs.setGlobalSetPoint(lessminval);
		log.info("global set point temperature set to min-1 value " + lessminval + "for celsius unit");

		Trs.clickSavebtn();
		log.info("global point saved to " + lessminval + "for celsius unit");

		if (Trs.getToastmsgststus() == true) {
			log.info("Preferences sucessfully updated");
		}

		String tankspforgpmin = Trs.getTankSetPoint();
		softAssert.assertEquals(tankspforgpmin, Constants.Minglobaltankpntpnt,
				"Tank set point is not set to Minimum value : " + tankspforgpmin + "for min-1 celsius unit");

		log.info("Tank set point for global minimun point is set to :" + tankspforgpmin + "for celsius unit");

		String manifoldforgpmin = Trs.getManifold();
		softAssert.assertEquals(manifoldforgpmin, Constants.Minglobaltankpntpnt,
				"Manifold set point is not set to Default value min value : " + manifoldforgpmin + "for celsius unit");
		log.info("Manifold set point is set to Minium value :" + manifoldforgpmin + "for celsius unit");

		Trs.getHosesSettempStatus(Constants.Minglobaltankpntpnt);
		log.info("All the Hoses are set to min value for min-1 global set point");

		Trs.getApplicatorsSettempStatus(Constants.Minglobaltankpntpnt);
		log.info("All the Applicators are set to min value for min-1 global set point");

		// setting global point to Max+1 value and validating tank,Manifold,hose and
		// applicator values

		Trs.setGlobalSetPoint(moremaxval);
		log.info("global set point temperature set to max+1 value " + moremaxval + "for celsius unit");

		Trs.clickSavebtn();
		log.info("global point saved to " + moremaxval + "for celsius unit");

		if (Trs.getToastmsgststus() == true) {
			log.info("Preferences sucessfully updated");
		}

		String tankspforgpmax = Trs.getTankSetPoint();
		softAssert.assertEquals(Constants.Maxsetglobaltankpntpnt, tankspforgpmax,
				"Tank set point is not set to maximum value : " + tankspforgpmax + "for celsius unit");
		log.info("Tank set point for global maximum point is set to :" + tankspforgpmax + "for celsius unit");

		String manifoldforgpmax = Trs.getManifold();
		softAssert.assertEquals(Constants.Maxsetglobalmanifldpnt, manifoldforgpmax,
				"Manifold set point is not set to Default value : " + manifoldforgpmax + "for celsius unit");
		log.info("Manifold set point is set to Maximum value :" + manifoldforgpmax + "for celsius unit");

		Trs.getHosesSettempStatus(Constants.MaxsetglobalHosepnt);
		log.info("All Hoses set point temperature for global max+1 value set to max for celsius unit");

		Trs.getApplicatorsSettempStatus(Constants.MaxsetglobalApplicatorpnt);
		log.info("All Applicators set point temperature for global max+1 value set to max for celsius unit");

		// setting global point to Inrange value and validating tank,Manifold,hose and
		// applicator values

		Trs.setGlobalSetPoint(Inrng);
		log.info("global set point temperature set to in range" + Inrng + "for celsius unit");

		Trs.clickSavebtn();
		log.info("global point saved to " + Inrng + "for celsius unit");

		if (Trs.getToastmsgststus() == true) {
			log.info("Preferences sucessfully updated");
		}

		String tankspforInrng = Trs.getTankSetPoint();
		softAssert.assertEquals(tankspforInrng, Inrng,
				"Tank set point is not set to Inrng value : " + Inrng + "for celsius unit");
		log.info("Tank set point for global Inrng point is set to :" + Inrng + "for celsius unit");

		String manifoldforInrng = Trs.getManifold();
		softAssert.assertEquals(Inrng, manifoldforInrng,
				"Manifold set point is not set to Default value : " + Inrng + "for celsius unit");
		log.info("Manifold set point is set to Inrng value :" + Inrng + "for celsius unit");

		Trs.getHosesSettempStatus(Inrng);
		log.info("All Hoses set point temperature for global Inrng value set to Inrang for celsius unit");

		Trs.getApplicatorsSettempStatus(Inrng);
		log.info("All Applicators set point temperature for global Inrng value set to Inrang for celsius unit");

		XLUtils.setExcelSheetNm("GlobalPointFarnhit");
		softAssert.assertAll();

	}

	// Global Point Farnheit Validations
	@Test(priority = 2, dataProvider = "GlobalPointValues_Farnhenit", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Story("Temperature Runtimesettings Global Point validations with boundary values in Farnheit unit")
	public void Test_Runtime_Farnhit_MinmaxGlobalPointValidations(String lessfhminval, String maxplusfhval,
			String Inrngfh) throws InterruptedException, IOException {

		// setting global point to Min-1 value and validating tank,Manifold,hose and
		// applicator values for farnheit unit

		Trs.clickSystemSettingsBtn();
		log.info("Clicked on System settings button");

		Trs.clickPreferencesBtn();
		log.info("Clicked on Preferences button");

		Trs.clickFarhenitUnit();
		log.info("Temperature Unit farnhenit is selected");

		Trs.clickSave();
		log.info("Temperature Unit farnhenit is saved");
		
		if (Trs.getToastmsgststus() == true) {
			log.info("Preferences sucessfully updated");
		}

		Trs.RuntimeSettingsBtn();
		log.info("Clicked on Runtime settings button to verfiy Global set temperature for farnhenit unit");

		Trs.ZoneTemperatureBtn();
		log.info("Clicked on Zone temperature button to setglobal min value for farnhenit unit");
		
		// setting global point to Min-1 value and validating tank,Manifold,hose and
				// applicator values for farnheit unit


		Trs.setGlobalSetPoint(lessfhminval);
		log.info("global set point temperature set to min-1 value " + lessfhminval + "for farnhenit unit");

		Trs.clickSavebtn();
		log.info("global point saved to " + lessfhminval + "for farnhenit unit");

		if (Trs.getToastmsgststus() == true) {
			log.info("Preferences sucessfully updated");
		}
		else
			log.info("Preferences not sucessfully updated");

		String tankspforgpminfh = Trs.getTankSetPoint();
		softAssert.assertEquals(tankspforgpminfh, Constants.MinglobalMinFarh,
				"Tank set point is not set to Minimum value : " + Constants.MinglobalMinFarh + "for farnhenit unit");
		log.info("Tank set point for global minimun point is set to :" + tankspforgpminfh
				+ "for farnhenit unit");

		String manifoldforgpminfh = Trs.getManifold();
		softAssert.assertEquals(manifoldforgpminfh, Constants.MinglobalMinFarh,
				"Manifold set point is not set to Default value : " + Constants.MinglobalMinFarh
						+ "for farnhenit unit");
		log.info("Manifold set point is set to Minium value :" + Constants.MinglobalMinFarh + "for farnhenit unit");

		Trs.getHosesSettempStatus(Constants.MinglobalMinFarh);
		log.info("All the Hoses are set to min value for min-1 global set point for farnheit unit");

		Trs.getApplicatorsSettempStatus(Constants.MinglobalMinFarh);
		log.info("All the Applicators are set to min value for min-1 global set point for farnheit unit");

		// setting global point to Max+1 value and validating tank,Manifold,hose and
		// applicator values for farnheit unit

		Trs.setGlobalSetPoint(maxplusfhval);
		log.info("global set point temperature set to max+1 value " + maxplusfhval + "for farnhenit unit");

		Trs.clickSavebtn();
		log.info("global point saved to " + maxplusfhval + "for farnhenit unit");

		if (Trs.getToastmsgststus() == true) 
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");
		
		String tankspforgpmaxfh = Trs.getTankSetPoint();
		softAssert.assertEquals(Constants.MaxsetglobaltankpntpntFarh, tankspforgpmaxfh,
				"Tank set point is not set to maximum value : " + Constants.MaxsetglobaltankpntpntFarh
						+ "for farnhenit unit");
		log.info("Tank set point for global maximum point is set to :" + Constants.MaxsetglobaltankpntpntFarh
				+ "for farnhenit unit");

		String manifoldforgpmaxfh = Trs.getManifold();
		softAssert.assertEquals(Constants.MaxsetglobalManifoldpntFarh, manifoldforgpmaxfh,
				"Manifold set point is not set to Default value : " + Constants.MaxsetglobalManifoldpntFarh
						+ "for farnhenit unit");
		log.info("Manifold set point is set to Maximum value :" + Constants.MaxsetglobalManifoldpntFarh
				+ "for farnhenit unit");

		Trs.getHosesSettempStatus(ActionMethods.getConversionToFahrenheit(Constants.MaxsetglobalHosepnt));
		log.info("All the Hoses are set to max value for max+1 global set point for farnheit unit");

		Trs.getApplicatorsSettempStatus(ActionMethods.getConversionToFahrenheit(Constants.MaxsetglobalApplicatorpnt));
		log.info("All the Applicators are set to min value for max+1 global set point for farnheit unit");

		// setting global point to Inrange value and validating tank,Manifold,hose and
		// applicator values

		Trs.setGlobalSetPoint(Inrngfh);
		log.info("global set point temperature set to min-1 value " + Inrngfh + "for farnhenit unit");

		Trs.clickSavebtn();
		log.info("global point saved to " + Inrngfh + "for farnhenit unit");

		if (Trs.getToastmsgststus() == true) 
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");

		String tankspforInrngfh = Trs.getTankSetPoint();
		softAssert.assertEquals(tankspforInrngfh,Inrngfh, 
				"Tank set point is not set to Inrng value : " + Inrngfh + "for farnhenit unit");
		log.info("Tank set point for global Inrng point is set to :" + Inrngfh + "for farnhenit unit");

		String manifoldforInrngfh = Trs.getManifold();
		softAssert.assertEquals(Inrngfh, manifoldforInrngfh,
				"Manifold set point is not set to Default value : " + Inrngfh + "for farnhenit unit");
		log.info("Manifold set point is set to Inrng value :" + Inrngfh + "for farnhenit unit");

		Trs.getHosesSettempStatus(Constants.MinglobalMinFarh);
		log.info("All the Hoses are set to Inrng val when global set point for farnheit unit is in Inrng");

		Trs.getApplicatorsSettempStatus(Constants.MinglobalMinFarh);
		log.info("All the Applicators are set to Inrng val when global set point for farnheit unit is in Inrng");

		XLUtils.setExcelSheetNm("TankPointCelsius");
		softAssert.assertAll();

	}

	// tank field validations

	@Test(priority = 3, dataProvider = "TankPointValues_Celsius", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Story("Temperature Runtimesettings Tank set Point validations with boundary values in celsius unit")
	public void Test_Celsius_RuntimeMinmaxTankval(String minlsvalue, String maxplusval, String Inrngval)
			throws InterruptedException, IOException {

		Trs = new TemperatureRuntimeSettings(driver);

		// Setting back to celsius unit to verfiy tank setpoint

		Trs.clickSystemSettingsBtn();
		log.info("Clicked on System settings button");

		Trs.clickPreferencesBtn();
		log.info("Preferences is clicked to opt Unit celsius temperature");
		Thread.sleep(1000);

		Trs.clickCelsiusUnit();
		log.info("Temperature Unit celsuis is selected by clicking");

		Trs.clickSave();
		log.info("Temperature Unit celsuis is saved ");
		
		if (Trs.getToastmsgststus() == true) 
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");
		

		Trs.RuntimeSettingsBtn();
		log.info("Clicked on Runtime settings button to verfiy temperature Celsius Unit vals");

		Trs.ZoneTemperatureBtn();
		log.info("Clicked on Zone temperature button to setglobal min value in Celsuis");

		// clearing the Tank Point and verifying the Error for Celsius unit

		Trs.clearTanktemperature();
		log.info("Cleared the temperature set");
		
		Trs.clickSavebtn();
		log.info("Clicked on the saved button");
		
		String tsterrmsg = Trs.getToastmsg();
		softAssert.assertEquals(Constants.TnkpntnullErrmsg, tsterrmsg,
				"Toast Error message is not shown up for null val for Celsius unit");
		log.info("Toast Error message is shown up for null val save for Celsius unit");
		

		boolean stusclrerrmsg = Trs.getSavebtnstatus();
		softAssert.assertEquals(true, stusclrerrmsg, "Error message is shown up for null val for Celsius unit");
		log.info("Error message is shown up for null val save for Celsius unit");

		// min-1 val

		Trs.clearTanktemperature();
		Trs.setTankSetPoint(minlsvalue);
		Trs.clickSavebtn();

		String tstminerr = Trs.getToastmsg();
		softAssert.assertEquals(Constants.TnkpntErrmsg, tstminerr, "Toast Error message is shown up for Celsius unit");
		log.info("Toast Error message is shown up for " + minlsvalue + " min-1 val for Celsius unit");

		boolean stusminerr = Trs.getSavebtnstatus();
		softAssert.assertEquals(true, stusminerr, "Error message is shown up for Celsius unit");
		log.info("Error message is shown up for " + minlsvalue + " min-1 val for Celsius unit");

		// max+1 val

		Trs.clearTanktemperature();
		Trs.setTankSetPoint(maxplusval);
		Trs.clickSavebtn();

		String tstmaxerr = Trs.getToastmsg();
		softAssert.assertEquals(Constants.TnkpntErrmsg, tstmaxerr,
				"Toast Error message is shown up for " + maxplusval + " val for Celsius unit");
		log.info("Toast Error message is shown up for max val");

		boolean stusmaxerr = Trs.getSavebtnstatus();
		softAssert.assertEquals(true, stusmaxerr,
				"Error message is shown up for " + maxplusval + " val for Celsius unit");

		// In Range value testing

		Trs.clearTanktemperature();
		log.info("Tank temperature cleared to enter Inrange value for Celsius unit");
		Trs.setTankSetPoint(Inrngval);
		Trs.clickSavebtn();
			String tstsucssmsg = Trs.getToastmsg();
			Thread.sleep(1200);
			
			softAssert.assertEquals(Constants.SucssmsgRuntime, tstsucssmsg,
					"Toast msg In range values are not saved for Celsius unit");
			log.info("Toast msg In range values are saved successfully for Celsius unit");
			
			
			boolean svebtn = Trs.getSavebtnstatus();
			softAssert.assertEquals(svebtn, false,
					"Error message is shown up for " + maxplusval + " val for Celsius unit");
		
	
		XLUtils.setExcelSheetNm("TankPointFarnhit");
		softAssert.assertAll();

	}

	@Test(priority = 4, dataProvider = "TankPointValues_Farnhenit", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Story("Temperature Runtimesettings Tank set Point validations with boundary values in Farnheit unit")
	public void Test_Farnhit_RuntimeMinmaxTankval(String minlsvaluefh, String maxplusvalfh, String Inrngvaluefh)
			throws InterruptedException, IOException {

		{
			// clearing the Tank Point and verifying the Error for Farnheit unit

			Am = new ActionMethods();
			
			Thread.sleep(1000);
			Trs.clickSystemSettingsBtn();
			log.info("Clicked on System settings button");
			Thread.sleep(1200);

			Trs.clickPreferencesBtn();
			log.info("Preferences is clicked to opt Unit Farhenit temperature");

			Trs.clickFarhenitUnit();
			log.info("Temperature Unit Farhenit is selected by clicking");

			Trs.clickSave();
			log.info("Clicked on Save button to Opt farnheit Unit temperature");
			
			if (Trs.getToastmsgststus() == true) 
				log.info("Preferences sucessfully updated");
			else
				log.info("Preferences not sucessfully updated");

			Trs.RuntimeSettingsBtn();
			log.info("Clicked on Runtime settings button to verfiy temperature farnheit Unit vals");

			Trs.ZoneTemperatureBtn();
			log.info("Clicked on Zone temperature button to setglobal min value in farheit");

			// Null value Err tst verfication

			Trs.clearTanktemperature();
			log.info("Tank temperature cleared to verfiy err msg");
			Trs.clickSavebtn();

			softAssert.assertEquals(Trs.getToastmsg(),Constants.TnkpntnullErrmsg, 
					"Toast Error message is shown up for farnheit unit null val save");
			log.info("Toast Error message is shown up for null val for farnheit unit");

			boolean stusnullerrfh = Trs.getSavebtnstatus();
			softAssert.assertEquals(stusnullerrfh,true,  "Error message is shown up for farnheit unit null val save");
			log.info("Error message is shown up for null val for farnheit unit");

			// min-1 val

			Trs.clearTanktemperature();
			Trs.setTankSetPoint(minlsvaluefh);
			log.info("Tank temperature set to min-1 value");

			Trs.clickSavebtn();

			String tstminerrfh = Trs.getToastmsg();
			softAssert.assertEquals(tstminerrfh,Constants.TnkpntErrmsgFH, 
					"Toast Error message is shown up for farnheit unit");
			log.info("Toast Error message is shown up for " + minlsvaluefh + " min-1 val for farnheit unit");

			boolean stusminerrfh = Trs.getSavebtnstatus();
			softAssert.assertEquals( stusminerrfh,true, "Error message is shown up for farnheit unit");
			log.info("Error message is shown up for " + minlsvaluefh + " min-1 val for farnheit unit");

			// max+1 val

			Trs.clearTanktemperature();

			Trs.setTankSetPoint(maxplusvalfh);
			log.info("Tank temperature set to max+1 value");
			Trs.clickSavebtn();

			String tstmaxerrfh = Trs.getToastmsg();
			softAssert.assertEquals( tstmaxerrfh,Constants.TnkpntErrmsgFH,
					"Toast Error message is shown up for " + maxplusvalfh + " val for Farnheit unit");
			log.info("Toast Error message is shown up for" + maxplusvalfh + " for Farnheit unit");

			boolean stusmaxerrfh = Trs.getSavebtnstatus();
			softAssert.assertEquals(stusmaxerrfh,true, 
					"Error message is shown up for " + maxplusvalfh + " val for Farnheit unit");
			log.info("Error message is shown up for" + maxplusvalfh + " for Farnheit unit");

			// In Range value testing

			Trs.clearTanktemperature();
			log.info("Tank temperature cleared to enter Inrange value for Farnheit unit");

			Tankpoint = Inrngvaluefh;
			Trs.setTankSetPoint(Inrngvaluefh);
			log.info("Tank temperature set to Inrng value");
			Trs.clickSavebtn();
			
			softAssert.assertEquals(Trs.getToastmsg(),Constants.SucssmsgRuntime,
					"Toast msg In range values are not saved for farnheit unit");
			log.info("Toast msg In range values are saved successfully for farnheit unit");


			XLUtils.setExcelSheetNm("HoseCelsius");
			softAssert.assertAll();
		}

	}

	@Test(priority = 5, dataProvider = "HoseAppPointValues_Celsius", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Story("Temperature Runtimesettings Hoseand Applicator1 set Point validations with boundary values in celsius unit")
	public void Test_Celsius_RuntimeMinmaxHoseval(String minlsvalue, String maxplusval, String Inrngval)
			throws InterruptedException, IOException {

		// Verfiying the values by checking the status and enabling hose for celsius
		// unit
		Am = new ActionMethods();
		

		Trs.clickSystemSettingsBtn();
		log.info("Clicked on System settings button");
		Thread.sleep(1200);

		Trs.clickPreferencesBtn();
		log.info("Preferences is clicked to opt Unit Farhenit temperature");

		Trs.clickPreferencesBtn();
		log.info("Preferences is clicked to opt Unit Celsius temperature");
		Thread.sleep(1000);

		Trs.clickCelsiusUnit();
		log.info("Temperature Unit Celsius is selected by clicking");

		Trs.clickSave();
		log.info("Clicked on Save button to Opt Celsius Unit temperature");
		
		
		if (Trs.getToastmsgststus() == true) 
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");

		Trs.RuntimeSettingsBtn();

		log.info("Clicked on Runtime settings button to verfiy temperature Celsius Unit vals");

		Trs.ZoneTemperatureBtn();
		log.info("Clicked on Zone temperature button to setglobal min value in Celsius");
		
		
          
		/*
		 * // checking the status and enabling hose
		 * 
		 * Trs.clickOnHose1toEnable();
		 * 
		 * // clearing the hose1
		 * 
		 * Trs.clearHosesetTemperature(); Trs.clickSavebtn();
		 * 
		 * String stusclrerrmsgtst = Trs.getToastmsg(); softAssert.assertEquals(
		 * stusclrerrmsgtst,Constants.Hose1,
		 * "Toast Error message is not shown up for max Celsius val");
		 * log.info("Toast Error message is shown up for null Celsius val save");
		 * 
		 * boolean stusclrerrmsg = Trs.getSavebtnstatus();
		 * softAssert.assertEquals(stusclrerrmsg,true,
		 * "Error message is not shown up for max Celsius val");
		 * log.info("Error message is shown up for null Celsius val save");
		 */

		
		
		// min-1 val

		Trs.setHosestemp(minlsvalue);
		// max+1 val
		
        Trs.setHosestemp(maxplusval);
        softAssert.assertAll();

		// In Range value testing

		Trs.clearHosesetTemperature();
		log.info("Tank temperature cleared to enter Inrange value");

		Trs.setHose1emperature(Inrngval);
		Trs.clickSavebtn();

		String tstsucssmsg = Trs.getToastmsg();
		softAssert.assertEquals( tstsucssmsg,Constants.SucssmsgRuntime,
				"Toast msg In range values are not saved for Celsius unit");
		log.info("Toast msg In range values are saved successfully for Celsius unit");

		boolean stus = Trs.getSavebtnstatus();
		softAssert.assertEquals(stus,false, "In range values are saved successfully for Celsius unit");
		log.info("In range values are saved successfully for Celsius unit");


		// checking the status and enabling Applicator

		Trs.clickApplicator1toEnable();

		// clearing the Applicator1

		Trs.clearApplicator1setTemperature();
		Trs.clickSavebtn();

		String appclrerrmsgtst = Trs.getToastmsg();
		softAssert.assertEquals( appclrerrmsgtst,Constants.ApplictorFH,
				"Toast Error message is not shown up for null Celsius val for Applicator1");
		log.info("Toast Error message is shown up for null Celsius val save for Applicator1");

		boolean appclrerrmsg = Trs.getSavebtnstatus();
		softAssert.assertEquals(appclrerrmsg,true, 
				"Error message is not shown up for null Celsius val for Applicator1");
		log.info("Error message is shown up for null Celsius val save for Applicator1");

		// min-1 val

		Trs.clearApplicator1setTemperature();
		Trs.setApplicator1temperature(minlsvalue);
		Trs.clickSavebtn();

		String apptstminerr = Trs.getToastmsg();
		softAssert.assertEquals(apptstminerr,Constants.ApplictorFH, 
				"ToastError message is not shown up for min-1 Celsius unit Applicator1 ");
		log.info("Toast Error message is shown up for min-1" + minlsvalue + "for Celsius unit Applicator1");

		boolean appstusminerr = Trs.getSavebtnstatus();
		softAssert.assertEquals(appstusminerr,true,  "Error message is not shown up for Celsius unit Applicator1");
		log.info("Error message is shown up for min-1" + minlsvalue + "for Celsius unit Applicator1");

		// max+1 val

		Trs.clearApplicator1setTemperature();
		Trs.setApplicator1temperature(maxplusval);
		Trs.clickSavebtn();

		String apptstmaxerr = Trs.getToastmsg();
		softAssert.assertEquals(apptstmaxerr,Constants.ApplictorFH, 
				"Toast Error message is  not shown up for max plus val for Celsius unit Applicator1");
		log.info("Toast Error message is shown up for max val plus " + maxplusval + " for Celsius unit Applicator1");

		boolean appstusmaxerr = Trs.getSavebtnstatus();
		softAssert.assertEquals(appstusmaxerr,true, 
				"Error message is  not shown up for max plus val for Celsius unit Applicator1");
		log.info("Error message is shown up for max val plus " + maxplusval + " for Celsius unit Applicator1");

		// In Range value testing

		Trs.clearApplicator1setTemperature();
		log.info("Tank temperature cleared to enter Inrange value");

		Trs.setApplicator1temperature(Inrngval);
		Trs.clickSavebtn();
		

		String apptstsucssmsg = Trs.getToastmsg();
		softAssert.assertEquals( apptstsucssmsg,Constants.SucssmsgRuntime,
				"Toast msg In range values are not saved for Celsius unit Applicator1");
		log.info("Toast msg In range values are saved successfully for Celsius unit Applicator1");

		boolean appstus = Trs.getSavebtnstatus();
		softAssert.assertEquals(appstus,false,  "In range values are saved successfully for Celsius unit Applicator1");
		log.info("In range values are saved successfully for Celsius unit Applicator1");

		XLUtils.setExcelSheetNm("HoseFarnhit");
		softAssert.assertAll();
	}

	@Test(priority = 6, dataProvider = "HoseAppPointValues_Farnhenit", dataProviderClass = com.nordson.utilities.XLUtils.class)
	@Story("Temperature Runtimesettings Hoseand Applicator1 set Point validations with boundary values in Farnheit unit")
	public void Test_Farnhit_RuntimeMinmaxHoseApplicator1val(String minlsvaluefh, String maxplusvalfh,
			String Inrngvaluefh) throws InterruptedException, IOException {

		Thread.sleep(2000);
		Trs.clickSystemSettingsBtn();
		Trs.clickPreferencesBtn();
		Thread.sleep(1000);

		Trs.clickFarhenitUnit();
		log.info("Temperature Unit Farhenit is selected for verfying hose and applicator value");
		Thread.sleep(1000);

		Trs.clickSave();
		if (Trs.getToastmsgststus() == true) 
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");

		log.info("Saved Farhenit option for verfying hose and applicator value");

		Trs.RuntimeSettingsBtn();
		log.info("Clicked on Temperature in Runtime settings to validate Celsius to farhenit conversion");

		Trs.ZoneTemperatureBtn();

		// Null value Validations

		Trs.clearHosesetTemperature();

		Trs.clickSavebtn();
		log.info("Cleared the hose to verfiy error toast msg");
	
		String tstclrerrmsgfh = Trs.getToastmsg();
		softAssert.assertEquals( tstclrerrmsgfh,Constants.HoseFH,
				"Toast Error message is not shown up for null farnheit val");
		log.info("Toast Error message is shown up for null farnheit val save");

		boolean stusclrerrmsgfh = Trs.getSavebtnstatus();
		softAssert.assertEquals(stusclrerrmsgfh,true,"Error message is not shown up for null farnheit val");
		log.info("Error message is shown up for null farnheit val save");

		// min-1 val

		Trs.clearHosesetTemperature();
		Trs.setHose1emperature(minlsvaluefh);
		Trs.clickSavebtn();

		String tstminerrfh = Trs.getToastmsg();
		softAssert.assertEquals( tstminerrfh,Constants.HoseFH,
				"Toast error message is not shown up for farnheit unit");
		log.info("Toast error message is shown up for min-1" + minlsvaluefh + "for farnheit unit");

		boolean stusminerrfh = Trs.getSavebtnstatus();
		softAssert.assertEquals( stusminerrfh,true, "Error message is not shown up for farnheit unit");
		log.info("Error message is shown up for min-1" + minlsvaluefh + "for farnheit unit");

		// max+1 val

		Trs.clearHosesetTemperature();

		Trs.setHose1emperature(maxplusvalfh);

		Trs.clickSavebtn();

		String tstmaxerrfh = Trs.getToastmsg();
		softAssert.assertEquals( tstmaxerrfh,Constants.HoseFH,
				"Toast error message is  not shown up for max plus val for farnheit unit");
		log.info("Toast error message is shown up for max val plus " + maxplusvalfh + " for farnheit unit");

		boolean stusmaxerrfh = Trs.getSavebtnstatus();
		softAssert.assertEquals( stusmaxerrfh,true,
				"Error message is  not shown up for max plus val for farnheit unit");
		log.info("Error message is shown up for max val plus " + maxplusvalfh + " for farnheit unit");

		// In Range value testing

		Trs.clearHosesetTemperature();

		log.info("Tank temperature cleared to enter Inrange value for farnheit unit");

		Hosepnt = Inrngvaluefh;
		Trs.setHose1emperature(Inrngvaluefh);

		Trs.clickSavebtn();

		String tstmsgfh = Trs.getToastmsg();
		softAssert.assertEquals( tstmsgfh,Constants.SucssmsgRuntime,
				"Toast msg In range values are saved successfully for farnheit unit");
		log.info("Toast msg In range values are saved successfully for farnheit unit");

		boolean stusfh = Trs.getSavebtnstatus();
		softAssert.assertEquals( stusfh,false, "In range values are saved successfully for farnheit unit");
		log.info("In range values are saved successfully for farnheit unit");

		// checking the status and enabling Applicator

		// clearing the Applicator1

		Trs.clearApplicator1setTemperature();

		Trs.clickSavebtn();

		String appclrerrmsgtst = Trs.getToastmsg();
		softAssert.assertEquals( appclrerrmsgtst,Constants.ApplictorFH,
				"Toast Error message is not shown up for max Farnhit val for Applicator1");
		log.info("Toast Error message is shown up for null Farnhit val save for Applicator1");

		boolean appclrerrmsg = Trs.getSavebtnstatus();
		softAssert.assertEquals( appclrerrmsg,true,
				"Error message is not shown up for max Farnhit val for Applicator1");
		log.info("Error message is shown up for null Farnhit val save for Applicator1");

		// min-1 val

		Trs.clearApplicator1setTemperature();

		Trs.setApplicator1temperature(minlsvaluefh);

		Trs.clickSavebtn();

		String apptstminerr = Trs.getToastmsg();
		softAssert.assertEquals( apptstminerr,Constants.ApplictorFH,
				"ToastError message is not shown up for Farnhit unit Applicator1");
		log.info("Toast Error message is shown up for min-1" + minlsvaluefh + "for Farnhit unit Applicator1");

		boolean appstusminerr = Trs.getSavebtnstatus();
		softAssert.assertEquals(appstusminerr,true, "Error message is not shown up for Farnhit unit Applicator1");
		log.info("Error message is shown up for min-1" + minlsvaluefh + "for Farnhit unit Applicator1");

		// max+1 val

		Trs.clearApplicator1setTemperature();

		Trs.setApplicator1temperature(maxplusvalfh);

		Trs.clickSavebtn();

		String apptstmaxerr = Trs.getToastmsg();
		softAssert.assertEquals( apptstmaxerr,Constants.ApplictorFH,
				"Toast Error message is  not shown up for max plus val for Farnhit unit Applicator1");
		log.info("Toast Error message is shown up for max val plus " + maxplusvalfh + " for Farnhit unit Applicator1");

		boolean appstusmaxerr = Trs.getSavebtnstatus();
		softAssert.assertEquals( appstusmaxerr,true,
				"Error message is  not shown up for max plus val for Farnhit unit Applicator1");
		log.info("Error message is shown up for max val plus " + maxplusvalfh + " for Farnhit unit Applicator1");

		// In Range value testing

		Trs.clearApplicator1setTemperature();

		log.info("Applicator1 temperature cleared to enter Inrange value");

		Applicatorpnt = Inrngvaluefh;
		Trs.setApplicator1temperature(Inrngvaluefh);

		Trs.clickSavebtn();

		String apptstsucssmsg = Trs.getToastmsg();
		softAssert.assertEquals( apptstsucssmsg,Constants.SucssmsgRuntime,
				"Toast msg In range values are not saved for Farnhit unit Applicator1");
		log.info("Toast msg In range values are saved successfully for Farnhit unit Applicator1");

		boolean appstus = Trs.getSavebtnstatus();
		softAssert.assertEquals(false, appstus, "In range values are saved successfully for Farnhit unit Applicator1");
		log.info("In range values are saved successfully for Farnhit unit Applicator1");

		softAssert.assertAll();

	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 7)
	@Story("Temperature Runtimesettings Farnheit  to celsius unit conversion")
	public void Test_farnhit_ToCelsius_RuntimeSetttings_Conversion() throws InterruptedException {

		Trs.clickSystemSettingsBtn();

		Trs.clickPreferencesBtn();

		Trs.clickCelsiusUnit();
		log.info("Temperature Unit Farhenit is selected by clicking to verfiy conversion");

		Trs.clickSave();
		if (Trs.getToastmsgststus() == true) 
			log.info("Preferences sucessfully updated");
		else
			log.info("Preferences not sucessfully updated");

		log.info("Saved Farhenit option to verfiy conversion");

		Trs.RuntimeSettingsBtn();
		log.info("Clicked on Temperature in Runtime settings to validate Celsius to farhenit conversion");

		Trs.ZoneTemperatureBtn();

		// Tank point
		String Tnkclspnt = ActionMethods.getConversionToCelsius(Tankpoint);
		String tspui = Trs.getTankSetPoint();
		System.out.println(tspui);
		softAssert.assertEquals(Tnkclspnt, tspui, "Celsius to Farnhit unit conversion not correct for tank point");
		log.info("Celsius" + Tankpoint + "is converted to Farnhit unit" + Tnkclspnt
				+ " conversion for Tank point is correct");

		// Hose Point
		String hosetemp1 = Trs.getHosetemp1();
		System.out.println(hosetemp1);
		String hoseclspnt = ActionMethods.getConversionToCelsius(Hosepnt);
		softAssert.assertEquals(hoseclspnt, hosetemp1, "Celsius to Farnhit unit conversion not correct for Hose point");
		log.info("Celsius" + Hosepnt + "is converted to Farnhit unit" + hoseclspnt
				+ " conversion for Tank point is correct");

		// Applicator

		String Applicatorpntcvtd = ActionMethods.getConversionToCelsius(Applicatorpnt);
		String apptemp1 = Trs.getAPP1temp1();
		System.out.println(apptemp1);
		softAssert.assertEquals(Applicatorpntcvtd, apptemp1,
				"Celsius to Farnhit unit conversion not correct for applicator point");
		log.info("Celsius" + Hosepnt + "is converted to Farnhit unit" + Applicatorpntcvtd
				+ " conversion for applicator point is correct");

		softAssert.assertAll();

	}

	
}
