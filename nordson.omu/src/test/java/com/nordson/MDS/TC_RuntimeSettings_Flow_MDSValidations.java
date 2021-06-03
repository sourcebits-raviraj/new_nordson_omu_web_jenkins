package com.nordson.MDS;

import java.io.IOException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.nordson.pageObjects.Flow_Runtime_Settings;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;


public class TC_RuntimeSettings_Flow_MDSValidations extends TC_LoginTest_DDT_001 {

	Flow_Runtime_Settings frs;
	ActionMethods Am;
	private SoftAssert softAssert = new SoftAssert();
	MDSGetterandSetters_Flow fmds = new MDSGetterandSetters_Flow();
	RetriveMDSdata_Flow rmds = new RetriveMDSdata_Flow();
	ReadConfig rcf = new ReadConfig(); 
	
	@Test(priority = 1,enabled = true)
	@Feature("Verify MDS values for Flow Runtime Settings Screen")
	@Description("Verification of default,min and max values for Target Add-On field in Runtime Settings ")
	public void TC_OMU_Verify_TargetAddOn_MDSvalidation_default_min_max() throws InterruptedException, IOException {
	
		frs=new Flow_Runtime_Settings(driver);
		rmds.getMDSDataVal(rcf.getUIfieldTobefetched_TargetAddOn());
		frs.clickSetUpToolBtn();
		Thread.sleep(2000);
		frs.clickCreateNewBtn();
		frs.clickSubmitBtn();
		Thread.sleep(2000);
		frs.clickFlowRuntimesettingsbtn();
		
		// Verifying the default value of TargetAddOn field
		  softAssert.assertEquals(frs.getTargetAddOn(), fmds.getDefault1(),
						"Target Add-On is not set to Default value : " + fmds.getDefault1());
				log.info("Target Add-On is set to Default value  :" + fmds.getDefault1());
				// Verifying the Minimum value of TargetAddOn field
				frs.clearTargetAddon();
				frs.setTargetAddon(fmds.getMin1());
				frs.clickSavebtn();
				softAssert.assertEquals(frs.getToastmsg(),Constants.FlowSucssmsg, "TargetAddOn min MDS value is not accepted");
				log.info("TargetAddOn MDS minimum value saved successfully");
				boolean TargetAddOnSavebtnstutsmin1 = frs.getSavebtnstatus();
				softAssert.assertEquals(TargetAddOnSavebtnstutsmin1, false);
				log.info("Save button is disabled for TargetAddOn MDS minimum value");
				// Verifying the Max value of TargetAddOn field
				frs.clearTargetAddon();
				frs.setTargetAddon(fmds.getMax1());
				frs.clickSavebtn();
				softAssert.assertEquals(frs.getToastmsg(),Constants.FlowSucssmsg, "TargetAddOn max MDS value is not accepted");
				log.info("TargetAddOn MDS maximum value saved successfully");
				boolean TargetAddOnSavebtnstutsmax1 = frs.getSavebtnstatus();
				softAssert.assertEquals(TargetAddOnSavebtnstutsmax1, false);
				log.info("Save button is disabled for TargetAddOn MDS maximum value");
				softAssert.assertAll();
			}
}