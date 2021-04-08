package com.nordson.MDS;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Fill_System_Settings;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;

public class TC_SystemSettings_Fill_MDSValidations extends TC_LoginTest_DDT_001 {

	Fill_System_Settings fss;
	ActionMethods Am;
	private SoftAssert softAssert = new SoftAssert();
	MDSGettersandSetters_Fill fmds = new MDSGettersandSetters_Fill();
	RetriveMDSdata_Fill rmds = new RetriveMDSdata_Fill();
	ReadConfig rcf = new ReadConfig();

	@Test(priority = 1,enabled = true)
	@Feature("Verify MDS values for Mod and Tank with Fill System")
	@Description("Verification of default,min and max values for Maximum Fill time field in System Settings")
	public void TC_OMU_2190_Verify_MaximumFillTime_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		fss = new Fill_System_Settings (driver);
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_MaximumFillTime());
		fss.createNewNORfile();
		fss.clickFillbtn();
		log.info("Clicked on Fill link in System settings button");
		fss.SelectFillmode("MOD and Tank with Fill System");
		// Verifying the default value of MaximumFillTime field
		softAssert.assertEquals(fss.getMaxFillTime(), fmds.getDefault1(),
				"Maximum  Fill Time is not set to Default value : " + fmds.getDefault1());
		log.info("Maximum  Fill Time is set to Default value  :" + fmds.getDefault1());
		// Verifying the Minimum value of MaximumFillTime field
		fss.clearMaxFillTime();
		fss.setMaxFillTime(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "MaximumFillTime min MDS value is not accepted");
		log.info("MaximumFillTime MDS minimum value saved successfully");
		boolean MaximumFillTimeSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(MaximumFillTimeSavebtnstutsmin1, false);
		log.info("Save button is disabled for MaximumFillTime MDS minimum value");
		// Verifying the Max value of MaximumFillTime field
		fss.clearMaxFillTime();
		fss.setMaxFillTime(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "MaximumFillTime max MDS value is not accepted");
		log.info("MaximumFillTime MDS maximum value saved successfully");
		boolean MaximumFillTimeSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(MaximumFillTimeSavebtnstutsmax1, false);
		log.info("Save button is disabled for MaximumFillTime MDS maximum value");
		softAssert.assertAll();
	}
	@Test(priority = 2,enabled = true)
	@Feature("Verify MDS values for Mod and Tank with Fill System")
	@Description("Verification of default,min and max values for Target Fill Level field in System Settings")
	public void TC_OMU_2192_Verify_TargetFillLevel_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TargetFillLevel());
		// Verifying the default value of TargetFillLevel field
		softAssert.assertEquals(fss.getTargetFillLevel(), fmds.getDefault1(),
				"Target  Fill Level is not set to Default value : " + fmds.getDefault1());
		log.info("Target Fill Level is set to Default value  :" + fmds.getDefault1());
		
		  // Verifying the Minimum value of TargetFillLevel field
		  fss.clearTargetFillLevel(); fss.setTargetFillLevel(fmds.getMin1());
		  fss.clickSavebtn();
		  softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg
		  ,"TargetFillLevel min MDS value is not accepted");
		  log.info("TargetFillLevel MDS minimum value saved successfully"); boolean
		  TargetFillLevelSavebtnstutsmin1 = fss.getSavebtnstatus();
		  softAssert.assertEquals(TargetFillLevelSavebtnstutsmin1, false);
		  log.info("Save button is disabled for TargetFillLevel MDS minimum value");
		 
		// Verifying the Max value of TargetFillLevel field
		fss.clearTargetFillLevel();
		fss.setTargetFillLevel(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg,"TargetFillLevel max MDS value is not accepted");
		log.info("TargetFillLevel MDS maximum value saved successfully");
		boolean TargetFillLevelSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(TargetFillLevelSavebtnstutsmax1, false);
		log.info("Save button is disabled for TargetFillLevel MDS maximum value");
		softAssert.assertAll();
	}
	@Test(priority = 3,enabled = true)
	@Feature("Verify MDS values for Mod and Tank with Fill System")
	@Description("Verification of default,min and max values for Low LevelAlert Threshold field in System Settings")
	public void TC_OMU_2193_Verify_LowLevelThreshold_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfiledTobefetched_LowLevelThreshold());
		// Verifying the default value of LowLevelAlertThreshold field
		softAssert.assertEquals(fss.getLowLevelAlertThreshold(), fmds.getDefault1(),
				"Target  Fill Level is not set to Default value : " + fmds.getDefault1());
		log.info("Target Fill Level is set to Default value  :" + fmds.getDefault1());
		// Verifying the Minimum value of LowLevelAlertThreshold field
		fss.clearLowLevelAlertThreshold();
		fss.setLowLevelAlertThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LowLevelAlertThreshold min MDS value is not accepted");
		log.info("LowLevelAlertThreshold MDS minimum value saved successfully");
		boolean LowlevelThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowlevelThresholdSavebtnstutsmin1, false);
		log.info("Save button is disabled for LowLevelAlertThreshold MDS minimum value");
		// Verifying the Max value of LowLevelAlertThreshold field
		fss.clearLowLevelAlertThreshold();
		fss.setLowLevelAlertThreshold(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LowLevelAlertThreshold max MDS value is not accepted");
		log.info("LowLevelAlertThreshold MDS maximum value saved successfully");
		boolean LowlevelThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowlevelThresholdSavebtnstutsmax1, false);
		log.info("Save button is disabled for LowLevelAlertThreshold MDS maximum value");
		softAssert.assertAll();
	}
	
	@Test(priority = 4,enabled = true)
	@Feature("Verify MDS values for Mod and Tank with Fill System")
	@Description("Verification of default,min and max values for Lid Open Alert Threshold field in System Settings")
	public void TC_OMU_2192_Verify__LidOpenAlert_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LidOpenAlertTime());
		// Verifying the default value of LidOpenAlert field
		softAssert.assertEquals(fss.getLidOpenAlertTimeout(), fmds.getDefault1(),
				"Lid Open Alert time is not set to Default value : " + fmds.getDefault1());
		log.info("Lid Open Alert time is set to Default value  :" + fmds.getDefault1());
		// Verifying the Minimum value of LidOpenAlert field
		fss.clearLidOpenAlertTimeout();
		fss.setLidOpenAlertTimeout(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LidOpenAlert min MDS value is not accepted");
		log.info("LidOpenAlert MDS minimum value saved successfully");
		boolean LidOpenAlertSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LidOpenAlertSavebtnstutsmin1, false);
		log.info("Save button is disabled for LidOpenAlert MDS minimum value");
		// Verifying the Max value of LidOpenAlert field
		fss.clearLidOpenAlertTimeout();
		fss.setLidOpenAlertTimeout(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LidOpenAlert max MDS value is not accepted");
		log.info("LidOpenAlert MDS maximum value saved successfully");
		boolean LidOpenAlertSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LidOpenAlertSavebtnstutsmax1, false);
		log.info("Save button is disabled for LidOpenAlert MDS maximum value");
		softAssert.assertAll();
	}
	
	@Test(priority = 5,enabled = true)
	@Feature("Verify MDS values for Tank Only Fill System settings")
	@Description("Verification of default,min and max values for Low LevelAlert Threshold field in System Settings")
	public void TC_OMU_2195_Verify_LowLevelThreshold_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		fss.clickDashboard();
		rmds.getMDSDataVal(rcf.getUIfiledTobefetched_LowLevelThreshold());
		fss.createNewNORfile();
		fss.clickFillbtn();
		log.info("Clicked on Fill link in System settings button");
		fss.SelectFillmode("Tank Only");
		Thread.sleep(1000);
		log.info("Tank Only Fill mode is selected");
		// Verifying the default value of LowLevelAlertThreshold field
		softAssert.assertEquals(fss.getLowLevelAlertThreshold(), fmds.getDefault1(),
				"Target  Fill Level is not set to Default value : " + fmds.getDefault1() +"for Tank Only fill settings");
		log.info("Target Fill Level is set to Default value  :" + fmds.getDefault1()+"for Tank Only fill settings");
		// Verifying the Minimum value of LowLevelAlertThreshold field
		fss.clearLowLevelAlertThreshold();
		fss.setLowLevelAlertThreshold(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LowLevelAlertThreshold min MDS value is not accepted for Tank Only fill settings");
		log.info("LowLevelAlertThreshold MDS minimum value saved successfully for Tank Only fill settings");
		boolean LowlevelThresholdSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowlevelThresholdSavebtnstutsmin1, false);
		log.info("Save button is disabled for LowLevelAlertThreshold MDS minimum value for Tank Only fill settings");
		// Verifying the Max value of LowLevelAlertThreshold field
		fss.clearLowLevelAlertThreshold();
		fss.setLowLevelAlertThreshold(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LowLevelAlertThreshold max MDS value is not accepted for Tank Only fill settings");
		log.info("LowLevelAlertThreshold MDS maximum value saved successfully for Tank Only fill settings");
		boolean LowlevelThresholdSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LowlevelThresholdSavebtnstutsmax1, false);
		log.info("Save button is disabled for LowLevelAlertThreshold MDS maximum value for Tank Only fill settings");
		softAssert.assertAll();
	}
	
	@Test(priority = 6,enabled = true)
	@Description("Verification of default,min and max values for Lid Open Alert Threshold field in System Settings")
	public void TC_OMU_2196_Verify__LidOpenAlert_MDSvalidation_default_min_max() throws InterruptedException, IOException {
		
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_LidOpenAlertTime());
		Thread.sleep(1000);
		// Verifying the default value of LidOpenAlert field for Tank Only fill settings
		softAssert.assertEquals(fss.getLidOpenAlertTimeout(), fmds.getDefault1(),
				"Lid Open Alert time is not set to Default value : " + fmds.getDefault1()+"for Tank Only fill settings");
		log.info("Lid Open Alert time is set to Default value  :" + fmds.getDefault1()+"for Tank Only fill settings");
		// Verifying the Minimum value of LidOpenAlert field for Tank Only fill settings
		fss.clearLidOpenAlertTimeout();
		fss.setLidOpenAlertTimeout(fmds.getMin1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LidOpenAlert min MDS value is not accepted for Tank Only fill settings");
		log.info("LidOpenAlert MDS minimum value saved successfully for Tank Only fill settings");
		boolean LidOpenAlertSavebtnstutsmin1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LidOpenAlertSavebtnstutsmin1, false);
		log.info("Save button is disabled for LidOpenAlert MDS minimum value for Tank Only fill settings");
		// Verifying the Max value of LidOpenAlert field for Tank Only fill settings
		fss.clearLidOpenAlertTimeout();
		fss.setLidOpenAlertTimeout(fmds.getMax1());
		fss.clickSavebtn();
		softAssert.assertEquals(fss.getToastmsg(),Constants.Fillsucssmsg, "LidOpenAlert max MDS value is not accepted for Tank Only fill settings");
		log.info("LidOpenAlert MDS maximum value saved successfully for Tank Only fill settings");
		boolean LidOpenAlertSavebtnstutsmax1 = fss.getSavebtnstatus();
		softAssert.assertEquals(LidOpenAlertSavebtnstutsmax1, false);
		log.info("Save button is disabled for LidOpenAlert MDS maximum value for Tank Only fill settings");
		softAssert.assertAll();
	}
}
