package com.nordson.MDS;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.nordson.pageObjects.Flow_System_Settings;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_SystemSettings_Flow_MDSValues extends TC_LoginTest_DDT_001 {

	Flow_System_Settings fss;
	ActionMethods Am;
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Flow fmds = new MDSGetterandSetters_Flow();
	RetriveMDSdata_Flow rmds = new RetriveMDSdata_Flow();
	ReadConfig rcf = new ReadConfig();

	@Test(priority = 1, enabled = true)
	@Description("Retrieving Target Add on Value from Runtime Settings to validate system settings")
	public void TC_OMU_Verify_TargetAddOn_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		fss = new Flow_System_Settings(driver);
		fss.clickSetUpToolBtn();
		Thread.sleep(1000);
		fss.clickCreateNewBtn();
		Thread.sleep(1000);
		fss.clickSubmitBtn();
		Thread.sleep(1000);
		fss.clickFlowRuntimesettingsbtn();
		System.out.println(fss.getTargetAddOn() + "value of target addon");
		fmds.setTargetAddon(fss.getTargetAddOn());

	}

	@Test(priority = 2, enabled = true)
	@Description("Verification of default value for Low Alert Threshold field in System Settings")
	public void TC_OMU_Verify_Default_LowAlertThreshold_MDSvalidation() throws InterruptedException, IOException {
		
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LowAlertThreshold());
		System.out.println(fmds.getDefault1()+fmds.getMin1()+fmds.getMax1());
		fss.clickDashboard();
		Thread.sleep(2000);
		fss.createNewNORfile();
		fss.clickFlowSystemsettingsbtn();
		// Verifying the default value of Low Alert Threshold field
		softAssert.assertEquals(fss.getLowAlertThreshold(), fmds.getDefault1(),
				"Low Alert Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("Low Alert Thresholdn is set to Default value  :" + fmds.getDefault1());
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true)
	@Description("Verification of Min val for Low Alert Threshold field in System Settings")
	public void TC_OMU_Verify_Min1_LowAlertThreshold_MDSvalidation() throws InterruptedException, IOException {
		// Verifying the Minimum value of Low Alert Threshold field
		fss.clearLowAlertThreshold();
		System.out.println(fmds.getMin1());
		fss.setLowAlertThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Low Alert Threshold min MDS value is not accepted");
		log.info("Low Alert Threshold MDS minimum value saved successfully");
		boolean LowAlertThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowAlertThresholdSavebtnstutsmin1, false);
		log.info("Save button is diabled for Low Alert Threshold MDS minimum value");
		softAssert.assertAll();
	}

	@Test(priority = 4, enabled = true)
	@Description("Verification of Max val for Low Alert Threshold field in System Settings")
	public void TC_OMU_Verify_Max1_LowAlertThreshold_MDSvalidation() throws InterruptedException, IOException {
		// Verifying the Max value of Low Alert Threshold field
		fss.clearLowAlertThreshold();
		fss.setLowAlertThreshold(fmds.getMax1());

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LowStopThreshold());
		fss.clearLowStopThreshold();
		fss.setLowStopThreshold(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Low Alert Threshold max MDS value is not accepted");
		log.info("Low Alert Threshold MDS maximum value saved successfully");
		boolean LowAlertThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowAlertThresholdSavebtnstutsmax1, false);
		log.info("Save button is disabled for Low Alert Threshold MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 5, enabled = true)
	@Description("Verification of Default value for High Alert Threshold field in System Settings")
	public void TC_OMU_Verify_Default_HighAlertThreshold_MDSvalidation() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HighAlertThreshold());
		// Verifying the default value of High Alert Threshold field
		softAssert.assertEquals(fss.getHighAlertThreshold(), fmds.getDefault1(),
				"High Alert Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("High Alert Thresholdn is set to Default value  :" + fmds.getDefault1());
		softAssert.assertAll();
	}

	@Test(priority = 6, enabled = true)
	@Description("Verification of Min value for High Alert Threshold field in System Settings")
	public void TC_OMU_Verify_Min1_HighAlertThreshold_MDSvalidation() throws InterruptedException, IOException {
		// Verifying the Minimum value of High Alert Threshold field
		fss.clearHighAlertThreshold();
		fss.setHighAlertThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"High Alert Threshold min MDS value is not accepted");
		log.info("High Alert Threshold MDS minimum value saved successfully");
		boolean HighAlertThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(HighAlertThresholdSavebtnstutsmin1, false);
		log.info("Save button is disabled for High Alert Threshold MDS minimum value");
		softAssert.assertAll();
	}
	
	@Test(priority = 7, enabled = true)
	@Description("Verification of Min value for High Alert Threshold field in System Settings")
	public void TC_OMU_Verify_Max1_HighAlertThreshold_MDSvalidation() throws InterruptedException, IOException {
		// Verifying the Minimum value of High Alert Threshold field
		fss.clearHighAlertThreshold();
		fss.setHighAlertThreshold(fmds.getMax1());
		
		fss.clearHighStopThreshold();
		fss.setHighStopThreshold(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"High Alert Threshold max MDS value is not accepted");
		log.info("High Alert Threshold MDS maximum value saved successfully");
		boolean HighAlertThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(HighAlertThresholdSavebtnstutsmax1, false);
		log.info("Save button is disabled for High Alert Threshold MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 8, enabled = true)
	@Description("Verification of Default val for Low Stop Threshold field in System Settings")
	public void TC_OMU_Verify_Default_LowStopThreshold_MDSvalidation() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LowStopThreshold());
		fss.clickDashboard();
		Thread.sleep(2000);
		fss.createNewNORfile();
		fss.clickFlowSystemsettingsbtn();
		// Verifying the default value of Low Stop Threshold field
		softAssert.assertEquals(fss.getLowStopThreshold(), fmds.getDefault1(),
				"Low Stop Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("Low Stop Thresholdn is set to Default value  :" + fmds.getDefault1());
		softAssert.assertAll();
	}

	@Test(priority = 9, enabled = true)
	@Description("Verification of Min val for Low Stop Threshold field in System Settings")
	public void TC_OMU_Verify_Min1_LowStopThreshold_MDSvalidation() throws InterruptedException, IOException {
		// Verifying the Minimum value of Low Stop Threshold field
		fss.clearLowStopThreshold();
		fss.setLowStopThreshold(fmds.getMin1());

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LowStopThreshold());
		fss.clearLowAlertThreshold();
		fss.setLowAlertThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Low Stop Threshold min MDS value is not accepted");
		log.info("Low Stop Threshold MDS minimum value saved successfully");
		boolean LowStopThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowStopThresholdSavebtnstutsmin1, false);
		log.info("Save button is disabled for Low Stop Threshold MDS minimum value");
		softAssert.assertAll();
	}

	@Test(priority = 10, enabled = true)
	@Description("Verification of Max val for Low Stop Threshold field in System Settings")
	public void TC_OMU_Verify_Max1_LowStopThreshold_MDSvalidation() throws InterruptedException, IOException {
		// Verifying the Max value of Low Stop Threshold field
		fss.clearLowStopThreshold();
		fss.setLowStopThreshold(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Low Stop Threshold max MDS value is not accepted");
		log.info("Low Stop Threshold MDS maximum value saved successfully");
		boolean LowStopThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowStopThresholdSavebtnstutsmax1, false);
		log.info("Save button is disabled for Low Stop Threshold MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 11, enabled = true)
	@Description("Verification of default value for High Stop Threshold field in System Settings")
	public void TC_OMU_Verify_Default_HighStopThreshold_MDSvalidation() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HighStopThreshold());
		// Verifying the default value of High Stop Threshold field
		softAssert.assertEquals(fss.getHighStopThreshold(), fmds.getDefault1(),
				"High Alert Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("High Alert Thresholdn is set to Default value  :" + fmds.getDefault1());
		softAssert.assertAll();
	}

	@Test(priority = 12, enabled = true)
	@Description("Verification of Min value for High Stop Threshold field in System Settings")
	public void TC_OMU_Verify_Min1_HighStopThreshold_MDSvalidation() throws InterruptedException, IOException {
		// Verifying the Minimum value of High Stop Threshold field
		fss.clearHighStopThreshold();
		fss.setHighStopThreshold(fmds.getMin1());

		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HighAlertThreshold());
		fss.clearHighAlertThreshold();
		fss.setHighAlertThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"High Alert Threshold min MDS value is not accepted");
		log.info("High Alert Threshold MDS minimum value saved successfully");
		boolean HighStopThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(HighStopThresholdSavebtnstutsmin1, false);
		log.info("Save button is disabled for High Stop Threshold MDS minimum value");
		softAssert.assertAll();
	}

	@Test(priority = 13, enabled = true)
	@Description("Verification of Max value for High Stop Threshold field in System Settings")
	public void TC_OMU_Verify_Max1_HighStopThreshold_MDSvalidation() throws InterruptedException, IOException {

		// Verifying the Max value of High Stop Threshold field
		fss.clearHighStopThreshold();
		fss.setHighStopThreshold(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"High Alert Threshold max MDS value is not accepted");
		log.info("High Alert Threshold MDS maximum value saved successfully");
		boolean HighStopThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(HighStopThresholdSavebtnstutsmax1, false);
		log.info("Save button is disabled for High Stop Threshold MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 14, enabled = true)
	@Description("Verification of default,min and max value for Alert Delay Count in System Settings")
	public void TC_OMU_Verify_AlertDelayCount_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_AlertDelayCount());
		fss.clickDashboard();
		Thread.sleep(2000);
		fss.createNewNORfile();
		fss.clickFlowSystemsettingsbtn();
		// Verifying the Default value of Alert Delay Count field
		softAssert.assertEquals(fss.getAlertDelayCount(), fmds.getDefault1(),
				"Alert Delay Count is not set to Default value : " + fmds.getDefault1());
		log.info("Alert Delay Count is set to Default value  :" + fmds.getDefault1());
		// Verifying the Min value of Alert Delay Count field
		fss.clearAlertDelayCount();
		fss.setAlertDelayCount(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Alert Delay Count min MDS value is not accepted");
		log.info("Alert Delay Count MDS minimum value saved successfully");
		boolean AlertDelayCountSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(AlertDelayCountSavebtnstutsmin1, false);
		log.info("Save button is disabled for Alert Delay Count MDS minimum value");
		// Verifying the Max value of Alert Delay Count field
		fss.clearAlertDelayCount();
		fss.setAlertDelayCount(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Alert Delay Count max MDS value is not accepted");
		log.info("Alert Delay Count MDS maximum value saved successfully");
		boolean AlertDelayCountSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(AlertDelayCountSavebtnstutsmax1, false);
		log.info("Save button is disabled for Alert Delay Count MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 15, enabled = true)
	@Description("Verification of default,min and max value for Stop Delay Count in System Settings")
	public void TC_OMU_Verify_StopDelayCount_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_StopDelayCount());
		// Verifying the Default value of Stop Delay Count field
		softAssert.assertEquals(fss.getStopDelayCount(), fmds.getDefault1(),
				"Stop Delay Count is not set to Default value : " + fmds.getDefault1());
		log.info("Stop Delay Count is set to Default value  :" + fmds.getDefault1());
		// Verifying the Min value of Stop Delay Count field
		fss.clearStopDelayCount();
		fss.setStopDelayCount(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Stop Delay Count min MDS value is not accepted");
		log.info("Stop Delay Count MDS minimum value saved successfully");
		boolean StopDelayCountSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(StopDelayCountSavebtnstutsmin1, false);
		log.info("Save button is disabled for Stop Delay Count MDS minimum value");
		// Verifying the Max value of Stop Delay Count field
		fss.clearStopDelayCount();
		fss.setStopDelayCount(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Stop Delay Count max MDS value is not accepted");
		log.info("Stop Delay Count MDS maximum value saved successfully");
		boolean StopDelayCountSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(StopDelayCountSavebtnstutsmax1, false);
		log.info("Save button is disabled for Stop Delay Count MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 16, enabled = true)
	@Description("Verification of default,min and max value for Specific gravity in System Settings")
	public void TC_OMU_Verify_SpecificGravity_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_SpecificGravity());
		// Verifying the Default value of Specific gravity field
		softAssert.assertEquals(fss.getSpecificGravity(), fmds.getDefault1(),
				"Specific gravity is not set to Default value : " + fmds.getDefault1());
		log.info("Specific gravity is set to Default value  :" + fmds.getDefault1());
		// Verifying the Min value of Specific gravity field
		fss.clearSpecificgravity();
		fss.setSpecificGravity(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Specific gravity min MDS value is not accepted");
		log.info("Specific gravity MDS minimum value saved successfully");
		boolean SpecificgravitySavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmin1, false);
		log.info("Save button is disabled for Specific gravity MDS minimum value");
		// Verifying the Max value of Specific gravity field
		fss.clearSpecificgravity();
		fss.setSpecificGravity(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Specific gravity max MDS value is not accepted");
		log.info("Specific gravity MDS maximum value saved successfully");
		boolean SpecificgravitySavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmax1, false);
		log.info("Save button is disabled for Specific gravity MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 17, enabled = true)
	@Description("Verification of default,min and max value for Calibration Constant Setting in System Settings")
	public void TC_OMU_Verify_CalibrationConstantSetting_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_CalibrationConstantSetting());
		// Verifying the Default value of Calibration Constant Setting field
		softAssert.assertEquals(fss.getCalibrationConstantSetting(), fmds.getDefault1(),
				"Calibration Constant Setting is not set to Default value : " + fmds.getDefault1());
		log.info("Calibration Constant Setting is set to Default value  :" + fmds.getDefault1());
		// Verifying the Min value of Calibration Constant Setting field
		fss.clearCalibrationConstantSetting();
		fss.setCalibrationConstantSetting(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Calibration Constant Setting min MDS value is not accepted");
		log.info("Calibration Constant Setting MDS minimum value saved successfully");
		boolean SpecificgravitySavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmin1, false);
		log.info("Save button is disabled for Calibration Constant Setting MDS minimum value");
		// Verifying the Max value of Calibration Constant Setting field
		fss.clearCalibrationConstantSetting();
		fss.setCalibrationConstantSetting(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Calibration Constant Setting max MDS value is not accepted");
		log.info("Calibration Constant Setting MDS maximum value saved successfully");
		boolean SpecificgravitySavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmax1, false);
		log.info("Save button is disabled for Calibration Constant Setting MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 18, enabled = true)
	@Description("Verification of default,min and max value for Product Skip Count in System Settings")
	public void TC_OMU_Verify_ProductSkipCount_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_ProductSkipCount());
		// Verifying the Default value of Product Skip Count field
		softAssert.assertEquals(fss.getProductSkipCount(), fmds.getDefault1(),
				"Product Skip Count is not set to Default value : " + fmds.getDefault1());
		log.info("Product Skip Count is set to Default value  :" + fmds.getDefault1()); // Verifying the Min value of
																						// Product Skip Count field
		fss.clearProductSkipCount();
		fss.setProductSkipCount(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Product Skip Count min MDS value is not accepted");
		log.info("Product Skip Count MDS minimum value saved successfully");
		boolean SpecificgravitySavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmin1, false);
		log.info("Save button is disabled for Product Skip Count MDS minimum value");
		// Verifying the Max value of Product Skip Count field
		fss.clearProductSkipCount();
		fss.setProductSkipCount(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Product Skip Count max MDS value is not accepted");
		log.info("Product Skip Count MDS maximum value saved successfully");
		boolean SpecificgravitySavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmax1, false);
		log.info("Save button is disabled for Product Skip Count MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 19, enabled = true)
	@Description("Verification of default,min and max value for Product to Average Count in System Settings")
	public void TC_OMU_Verify_ProductstoAverage_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_ProductstoAverage());
		// Verifying the Default value of Products to Average field
		softAssert.assertEquals(fss.getProductstoAverage(), fmds.getDefault1(),
				"Products to Average is not set to Default value : " + fmds.getDefault1());
		log.info("Products to Average is set to Default value  :" + fmds.getDefault1());

		// Verifying the Min value of Product to Average field
		fss.clearProductstoAverage();
		fss.setProductstoAverage(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Products to Average min MDS value is not accepted");
		log.info("Products to Average MDS minimum value saved successfully");
		boolean SpecificgravitySavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmin1, false);
		log.info("Save button is disabled for Products to Average MDS minimum value"); 
		// Verifying the Max value of Products to Average field
		fss.clearProductstoAverage();
		fss.setProductstoAverage(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Products to Average max MDS value is not accepted");
		log.info("Products to Average MDS maximum value saved successfully");
		boolean SpecificgravitySavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmax1, false);
		log.info("Save button is disabled for Products to Average MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 20, enabled = true)
	@Description("Verification of default,min and max value for Idle Skip Time in System Settings")
	public void TC_OMU_Verify_IdleSkipTime_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_IdleSkipTime());
		// Verifying the Default value of Idle Skip Time field
		softAssert.assertEquals(fss.getIdleSkipTime(), fmds.getDefault1(),
				"Idle Skip Time is not set to Default value : " + fmds.getDefault1());
		log.info("Idle Skip Time is set to Default value  :" + fmds.getDefault1());
		// Verifying the Min value of Idle Skip Time field 
		fss.clearIdleSkipTime();
		fss.setIdleSkipTime(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Idle Skip Time min MDS value is not accepted");
		log.info("Idle Skip Time MDS minimum value saved successfully");
		boolean SpecificgravitySavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmin1, false);
		log.info("Save button is disabled for Idle Skip Time MDS minimum value");
		// Verifying the Max value of Idle Skip Time field field
		fss.clearIdleSkipTime();
		fss.setIdleSkipTime(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Idle Skip Time max MDS value is not accepted");
		log.info("Idle Skip Time MDS maximum value saved successfully");
		boolean SpecificgravitySavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmax1, false);
		log.info("Save button is disabled for Idle Skip Time MDS maximum value");
		softAssert.assertAll();
	}

	@Test(priority = 21, enabled = true)
	@Description("Verification of default,min and max value for Start up Skip Count in System Settings")
	public void TC_OMU_Verify_StartupSkipCount_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_StartupSkipCount());
		// Verifying the Default value of Start up Skip Count field
		softAssert.assertEquals(fss.getStartupSkipCount(), fmds.getDefault1(),
				"Start up Skip Count is not set to Default value : " + fmds.getDefault1());
		log.info("Start up Skip Count is set to Default value  :" + fmds.getDefault1()); 
		// Verifying the Min value of Start up Skip Count field
		fss.clearStartupSkipCount();
		fss.setStartupSkipCount(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Start up Skip Count min MDS value is not accepted");
		log.info("Start up Skip Count MDS minimum value saved successfully");
		boolean SpecificgravitySavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmin1, false);
		log.info("Save button is disabled for Start up Skip Count MDS minimum value"); 
		// Verifying the Max value ofStart  up Skip Count field
		fss.clearStartupSkipCount();
		fss.setStartupSkipCount(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Start up Skip Count max MDS value is not accepted");
		log.info("Start up Skip Count MDS maximum value saved successfully");
		boolean SpecificgravitySavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(SpecificgravitySavebtnstutsmax1, false);
		log.info("Save button is disabled for Start up Skip Count MDS maximum value");
		softAssert.assertAll();
	}

}