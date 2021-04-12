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
		Thread.sleep(2000);
		fss.clickCreateNewBtn();
		fss.clickSubmitBtn();
		Thread.sleep(2000);
		fss.clickFlowRuntimesettingsbtn();
		System.out.println(fss.getTargetAddOn()+"value of target addon");
		fmds.setTargetAddon(fss.getTargetAddOn());
	}
	@Test(priority = 2, enabled = true)
	@Feature("Verify MDS values for Flow System Settings Screen")
	@Description("Verification of default,min and max values for Low Stop Threshold field in System Settings")
	public void TC_OMU_Verify_LowStopThreshold_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LowStopThreshold());
		fss.clickDashboard();
		Thread.sleep(2000);
		fss.createNewNORfile();
		fss.clickFlowSystemsettingsbtn();
		// Verifying the default value of Low Stop Threshold field
		softAssert.assertEquals(fss.getLowStopThreshold(), fmds.getDefault1(),
				"Low Stop Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("Low Stop Thresholdn is set to Default value  :" + fmds.getDefault1());
		// Verifying the Minimum value of Low Stop Threshold field
		fss.clearLowStopThreshold();
		fss.setLowStopThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Low Stop Threshold min MDS value is not accepted");
		log.info("Low Stop Threshold MDS minimum value saved successfully");
		boolean LowStopThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowStopThresholdSavebtnstutsmin1, false);
		log.info("Save button is disabled for Low Stop Threshold MDS minimum value");
		
		  // Verifying the Max value of Low Stop Threshold field
		  fss.clearLowStopThreshold(); fss.setLowStopThreshold(fmds.getMax1());
		  fss.clickSavebtn(); softAssert.assertEquals(fss.getToastmsg(),
		  Constants.FlowSucssmsg, "Low Stop Threshold max MDS value is not accepted");
		  log.info("Low Stop Threshold MDS maximum value saved successfully"); boolean
		  LowStopThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		  softAssert.assertEquals(LowStopThresholdSavebtnstutsmax1, false);
		  log.info("Save button is disabled for Low Stop Threshold MDS maximum value"
		  );
		 
		softAssert.assertAll();
	}
	
	@Test(priority = 3, enabled = true)
	@Description("Verification of default,min and max values for High Stop Threshold field in System Settings")
	public void TC_OMU_Verify_HighStopThreshold_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HighStopThreshold());
		// Verifying the default value of High Stop Threshold field
		softAssert.assertEquals(fss.getHighStopThreshold(), fmds.getDefault1(),
				"High Alert Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("High Alert Thresholdn is set to Default value  :" + fmds.getDefault1());
		// Verifying the Minimum value of High Stop Threshold field
		fss.clearHighStopThreshold();
		fss.setHighStopThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"High Alert Threshold min MDS value is not accepted");
		log.info("High Alert Threshold MDS minimum value saved successfully");
		boolean HighStopThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(HighStopThresholdSavebtnstutsmin1, false);
		log.info("Save button is disabled for High Stop Threshold MDS minimum value");
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
	
	@Test(priority = 4, enabled = true)
	@Description("Verification of default,min and max values for Low Stop Threshold field in System Settings")
	public void TC_OMU_Verify_LowAlertThreshold_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LowAlertThreshold());
		fss.clickDashboard();
		Thread.sleep(2000);
		fss.createNewNORfile();
		fss.clickFlowSystemsettingsbtn();
		// Verifying the default value of Low Alert Threshold field
		softAssert.assertEquals(fss.getLowAlertThreshold(), fmds.getDefault1(),
				"Low Alert Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("Low Alert Thresholdn is set to Default value  :" + fmds.getDefault1());
		// Verifying the Minimum value of Low Alert Threshold field
		fss.clearLowAlertThreshold();
		fss.setLowAlertThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"Low Alert Threshold min MDS value is not accepted");
		log.info("Low Alert Threshold MDS minimum value saved successfully");
		boolean LowAlertThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowAlertThresholdSavebtnstutsmin1, false);
		log.info("Save button is diabled for Low Alert Threshold MDS minimum value");
		
		  // Verifying the Max value of Low Alert Threshold field
		  fss.clearLowAlertThreshold(); 
		  fss.setLowAlertThreshold(fmds.getMax1());
		  fss.clickSavebtn(); softAssert.assertEquals(fss.getToastmsg(),
		  Constants.FlowSucssmsg, "Low Alert Threshold max MDS value is not accepted");
		  log.info("Low Alert Threshold MDS maximum value saved successfully"); boolean
		  LowAlertThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		  softAssert.assertEquals(LowAlertThresholdSavebtnstutsmax1, false);
		  log.info("Save button is disabled for Low Alert Threshold MDS maximum value"
		  );
		 
		softAssert.assertAll();
	}
	
	@Test(priority = 5, enabled = true)
	@Description("Verification of default,min and max values for High Alert Threshold field in System Settings")
	public void TC_OMU_Verify_HighAlertThreshold_MDSvalidation_default_min_max()
			throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_HighAlertThreshold());
		// Verifying the default value of High Alert Threshold field
		softAssert.assertEquals(fss.getHighAlertThreshold(), fmds.getDefault1(),
				"High Alert Threshold is not set to Default value : " + fmds.getDefault1());
		log.info("High Alert Thresholdn is set to Default value  :" + fmds.getDefault1());
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
		// Verifying the Max value of High Alert Threshold field
		fss.clearHighAlertThreshold();
		fss.setHighAlertThreshold(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(), Constants.FlowSucssmsg,
				"High Alert Threshold max MDS value is not accepted");
		log.info("High Alert Threshold MDS maximum value saved successfully");
		boolean HighAlertThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(HighAlertThresholdSavebtnstutsmax1, false);
		log.info("Save button is disabled for High Alert Threshold MDS maximum value");
		softAssert.assertAll();
	}
}