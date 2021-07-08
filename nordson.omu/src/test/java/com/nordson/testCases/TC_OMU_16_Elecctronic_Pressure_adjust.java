package com.nordson.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.LoginPage;
import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.JiraPolicy;
import com.nordson.utilities.XLUtils;

public class TC_OMU_16_Elecctronic_Pressure_adjust extends TC_LoginTest_DDT_001 {

	LoginPage lp;
	Pressure_Min_Max_Validations pvm;
	ActionMethods Am = new ActionMethods();

	private SoftAssert softAssert = new SoftAssert();

	@JiraPolicy(logTicketReady = false)
	@Test(priority = 1, enabled = true)
	// @Feature("")
	public void navigateToElectronicadjust() throws Exception {

		pvm = new Pressure_Min_Max_Validations(driver);
		log.info("driver instantiated");

		pvm.clickSetUpToolLink();
		log.info("setup tool link clicked");

		pvm.clickCreateNewFile();
		log.info("create new file clicked");

		pvm.clickSubmit();
		log.info("submit button clicked");
		Am.sleepTime(1000);

		pvm.clickPressure();
		log.info("pressure clicked");

		pvm.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");

	}

	@JiraPolicy(logTicketReady = false)
	@Test(priority = 2, enabled = true)
	// @Feature("")
	public void VerifyToggleButton() throws Exception {

		if (pvm.pressureAlertTogglebuttonStatus().equals("true")) {
             
			Am.drawBorder(pvm.PressureToggle, driver);
			log.info("By default Pressure Setting Range Alert toggle is enabled");
			
		} else {
			
			Am.drawBorderFail(pvm.PressureToggle, driver);
			log.info("Pressure Setting Range Alert toggle is not enabled");
		}

		if (pvm.pressureAlertTogglebuttonStatus().equals("true")) {

			Am.drawBorder(pvm.PressureToggle, driver);
			pvm.clickPressureToggle();
			log.info("successfully Toggle button is disabled");
			
		} else {
			
			Am.drawBorderFail(pvm.PressureToggle, driver);
			log.info("Toggle button is not getting disable when toggle bar is enable");
		}
		
		Am.drawBorder(pvm.SaveButton, driver);
		pvm.saveButton();
		log.info("Save button is clicked");

		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		if (pvm.pressureAlertTogglebuttonStatus().equals("false")) {

			pvm.clickPressureToggle();
			log.info("successfully Toggle button is enabled when toggle bar is disabled");
		} else {
			log.info("Toggle button is not getting enabled when toggle bar is disable");
		}

		pvm.saveButton();
		log.info("Save button is clicked");

		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		// XLUtils.setExcelSheetNm("PressureSetPointNullBAR");
		// XLUtils.setExcelSheetNm("PressureSetPointNullBAR");
		// XLUtils.setExcelSheetNm("MaxsetpointrangeNull");
	}
	
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 3, enabled = true)
	  // @Feature("")
	  public void VerifyLowPressAlertThresholdNullValueBAR() throws Exception {
	  
	  pvm.clearLowPressureAlertThreshold();
	  log.info("Low pressure alert field Cleared");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
		  
	  Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARlowpressurethholderrormsg);
	  
	  log.info("Low Pressure Alert Threshold should be between 0.34 and 4.83 BAR");
	  
	  } 
	  else { Assert.assertTrue(false);
	  log.info("toast Message is not displayed"); }
	  
	  //softAssert.assertAll();
	  
	  XLUtils.setExcelSheetNm("LowPressThBelowMInBAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 4, enabled = true, dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyLowPressAlertThresholdbelowMinValueBAR(String min) throws Exception {
	  
	  
	  
	  pvm.setLowPressureAlertThreshold(min);
	  log.info("Low pressure alert value provided"); 
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
		  
	  Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARlowpressurethholderrormsg); 
	  log.info("Low Pressure Alert Threshold should be between 0.34 and 4.83 BAR message displayed");
	  
	  }
      else { Assert.assertTrue(false);
      
	  log.info("toast Message is not displayed"); }
	  
	  XLUtils.setExcelSheetNm("LowPressThMInBAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 5, enabled = true,dataProvider = "ElectronicPressureAdjust",dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("")
	  public void VerifyLowPressAlertThresholdMinValueBAR(String min) throws Exception {
	  
	  pvm.clearLowPressureAlertThreshold();
	  log.info("Low pressure alert field cleared");
	  
	  pvm.setLowPressureAlertThreshold(min);
	  log.info("Low pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } 
	  else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMIn+1BAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 6, enabled = true,dataProvider = "ElectronicPressureAdjust",
	  dataProviderClass = com.nordson.utilities.XLUtils.class) // @Feature("")
	  public void VerifyLowPressAlertThresholdmorethanMinValueBAR(String min)throws Exception { 
	  pvm.clearLowPressureAlertThreshold();
	  log.info("Low pressure alert field cleared");
	  
	  pvm.setLowPressureAlertThreshold(min);
	  log.info("Low pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  }
	  else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThBelowMaxBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 7, enabled = true,dataProvider = "ElectronicPressureAdjust",dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("")
	  public void VerifyLowPressAlertThresholdlessthanMaxValueBAR(String min) throws Exception {
	  
	  pvm.clearLowPressureAlertThreshold();
	  log.info("Low pressure alert field cleared");
	  
	  pvm.setLowPressureAlertThreshold(min);
	  log.info("Low pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMaxBAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 8, enabled = true,dataProvider = "ElectronicPressureAdjust",dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("")
	  public void VerifyLowPressAlertThresholdMaxValueBAR(String min) throws Exception {
	  
	  pvm.clearLowPressureAlertThreshold();
	  log.info("Low pressure alert field cleared");
	  
	  pvm.setLowPressureAlertThreshold(min);
	  log.info("Low pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false);
	  log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMax+1BAR");
	  	    
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 9, enabled = true,dataProvider = "ElectronicPressureAdjust",dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("")
	  public void VerifyLowPressAlertThresholdmorethanMaxValueBAR(String min)throws Exception {
	  
	  pvm.clearLowPressureAlertThreshold();
	  log.info("Low pressure alert field cleared");
	  
	  pvm.setLowPressureAlertThreshold(min);
	  log.info("Low pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
		  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARlowpressurethholderrormsg);
	  log.info("Low Pressure Alert Threshold should be between 0.34 and 4.83 BAR message displayed" );
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	     log.info("toast Message is not displayed");
	     
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMidBAR");
	  
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 10, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyLowPressAlertThresholdMidValueBAR(String min) throws Exception {
	  
	  pvm.clearLowPressureAlertThreshold();
	  log.info("Low pressure alert field cleared");
	  
	  pvm.setLowPressureAlertThreshold(min);
	  log.info("Low pressure alert value provided"); 
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
	  
		  Assert.assertTrue(false); 
	      log.info("toast Message is not displayed"); 
	      
	  }
	  	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 11, enabled = true) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdNullValueBAR() throws Exception {
	  
	  pvm.clearHighPressureAlertThreshold();
	  log.info("High pressure alert field cleared"); 
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
		  
	  Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARhighpressurethholderrormsg);
	  log.info("High Pressure Alert Threshold should be between 0.34 and 4.83 BAR");
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	  }
	  
	  //softAssert.assertAll();
	  
	  XLUtils.setExcelSheetNm("LowPressThBelowMInBAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 12, enabled = true, dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdbelowMinValueBAR(String min) throws Exception {
	  
	  
	  pvm.setHighPressureAlertThreshold(min);;
	  log.info("High pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
		  
	  Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARhighpressurethholderrormsg); 
	  log.info("High Pressure Alert Threshold should be between 0.34 and 4.83 BAR message displayed");
	  
	  } else {
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMInBAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 13, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdMinValueBAR(String min) throws Exception {
	  
	  pvm.clearHighPressureAlertThreshold();
	  log.info("High pressure alert field cleared");
	  
	  pvm.setHighPressureAlertThreshold(min);;
	  log.info("High pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMIn+1BAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 14, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdmorethanMinValueBAR(String min) throws Exception {
		  
	  pvm.clearHighPressureAlertThreshold();
	  log.info("High pressure alert field cleared");
	  
	  pvm.setHighPressureAlertThreshold(min);;
	  log.info("High pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThBelowMaxBAR"); 
	  
	  } 
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 15, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdlessthanMaxValueBAR(String min) throws Exception
	  {
	  
	  pvm.clearHighPressureAlertThreshold();
	  log.info("High pressure alert field cleared");
	  
	  pvm.setHighPressureAlertThreshold(min);;
	  log.info("High pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else{
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMaxBAR");
	  
	  } 
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 16, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdMaxValueBAR(String min) throws Exception {
	  
	  pvm.clearHighPressureAlertThreshold();
	  log.info("High pressure alert field cleared");
	  
	  pvm.setHighPressureAlertThreshold(min);;
	  log.info("High pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
	  
		  Assert.assertTrue(false); 
		  log.info("toast Message is not displayed");
		  
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMax+1BAR");
	   
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 17, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdmorethanMaxValueBAR(String min) throws Exception
	  {
	  
	  pvm.clearHighPressureAlertThreshold();
	  log.info("High pressure alert field cleared");
	  
	  pvm.setHighPressureAlertThreshold(min);;
	  log.info("High pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARhighpressurethholderrormsg); log.
	  info("High Pressure Alert Threshold should be between 0.34 and 4.83 BAR message displayed");
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	  }
	  
	  XLUtils.setExcelSheetNm("LowPressThMidBAR");
	  
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 18, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyHighPressAlertThresholdMidValueBAR(String min) throws Exception {
	  
	  pvm.clearHighPressureAlertThreshold();
	  log.info("High pressure alert field cleared");
	  
	  pvm.setHighPressureAlertThreshold(min);;
	  log.info("High pressure alert value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointNullBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 19, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointNullValueBAR(String min,String max) throws Exception {
	  
	  pvm.clearPressureMinimumSetPointRange(); Am.sleepTime(1500);
	  pvm.setMinimumPressureSetPointRange(min);
	  log.info("Min pressure set point range provided");
	  
	  pvm.clearMaximumPressureSetPointRange(); Am.sleepTime(1500);
	  pvm.setMaximumPressureSetPointRange(max);
	  log.info("Max pressure set point range provided");
	  
	  pvm.clearPressureSetPoint(); log.info("Pressure Set point value cleared");
	  Am.sleepTime(1500);
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARPressuresetpointerrormsg); log.
	  info("Pressure Set Point should be between 0.69 and 6.9 BAR message displayed");
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	  }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointmin-1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 20, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointlessthanMinValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	  
	  
	  pvm.setPressureSetPoint(min);
	  log.info("pressure set point value provided");
	  
	  
	  pvm.saveButton();
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARPressuresetpointerrormsg); log.
	  info("Pressure Set Point should be between 0.69 and 6.9 BAR message displayed"); 
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	  }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointminBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 21, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointMinValueBAR(String min) throws Exception {
	  
	  pvm.clearPressureSetPoint();
	  log.info("Pressure Set point value cleared"); 
	  
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
	  
		  Assert.assertTrue(false); 
		  log.info("toast Message is not displayed"); 
		  
	  }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointmin+1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 22, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointMorethanMinValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	 
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointmax-1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 23, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointlessthanMaxValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	  
	  
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointmaxBAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 24, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointMaxValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	  
	  
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointmax+1BAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 25, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointMorethanMaxValueBAR(String min) throws Exception {
	  
	  
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	
	  pvm.setPressureSetPoint(min);
      log.info("pressure set point value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARPressuresetpointerrormsg); log.
	  info("Pressure Set Point should be between 0.69 and 6.9 BAR message displayed"
	  ); } else { Assert.assertTrue(false);
	  log.info("toast Message is not displayed"); }
	  
	  XLUtils.setExcelSheetNm("PressureSetPointmidvalueBAR"); }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 26, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointmidValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	 
	  
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MinPressSetPointRangeNullBAR");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 27, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("")
	  public void VerifyMinPressureSetpointRangeNullValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	
	  
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARMinPressuresetpointRangeerrormsg); log.
	  info("Minimum Pressure Set Point Range should be 0.69 BAR message displyed");
	  } else { Assert.assertTrue(false);
	  log.info("toast Message is not displayed"); }
	  
	  XLUtils.setExcelSheetNm("MinPressSetPointRangeMin-1BAR"); }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 28, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMinPressureSetpointRangelessThanMinValueBAR(String min) throws Exception {
	  
	  
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.setMinimumPressureSetPointRange(min);
	  log.info("Min pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARMinPressuresetpointRangeerrormsg); 
	  log.info("Minimum Pressure Set Point Range should be 0.69 BAR message displyed");
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	     log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MinPressSetPointRangeMinBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 29, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMinPressureSetpointRangeMinValueBAR(String min) throws Exception {
	  
	  
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.setMinimumPressureSetPointRange(min);
	  log.info("Min pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MinPressSetPointRangeMin+1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 30, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMinPressureSetpointRangeMoreThanMinValueBAR(String min) throws
	  Exception {
	  
	  
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.setMinimumPressureSetPointRange(min);
	  log.info("Min pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARPressuresetpointerrormsg2); log.
	  info("Pressure Set Point should be between 0.7 and 6.9 BAR message displyed"); 
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	  }
	  
	  XLUtils.setExcelSheetNm("MinPressSetPointval-1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 31, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMinPressureSetpointRangeLessThanSetMinValueBAR(String min,String max) throws Exception {
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	  
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.setMinimumPressureSetPointRange(max);
	  log.info("Min pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MinPressSetPointvalBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 32, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMinPressureSetpointRangeMinPressSetPointvalBAR(String min) throws Exception {
	  
	  
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared");
	  
	  pvm.setMinimumPressureSetPointRange(min);
	  log.info("Min pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed");
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MinPressSetPointval+1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 33, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMinPressureSetpointRangeMoreThanSetMinValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.setMinimumPressureSetPointRange(min);
	  log.info("Min pressure set point range provided");
	  
	  pvm.saveButton();
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARPressuresetpointerrormsg3); log.
	  info("Pressure Set Point should be between 0.9 and 6.9 BAR message displyed"); 
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed");
	      
	  }
	  
	  XLUtils.setExcelSheetNm("MaxsetpointrangeNull"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 34, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("")
	  public void VerifyMaxPressureSetpointRangeNullValueBAR(String min,String max) throws
	  Exception {
	  
	  pvm.clearPressureSetPoint(); 
	  log.info("Pressure Set point value cleared");
	  
	  pvm.setPressureSetPoint(min); 
	  log.info("pressure set point value provided");
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.setMinimumPressureSetPointRange(max);
	  log.info("Min pressure set point range provided");
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared"); 
	  
	  pvm.saveButton();
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARMaxPressuresetpointRangeerrormsg); 
	  log.info("Maximum Pressure Set Point Range should be should be between 1.38 and 6.9 BAR message displyed");
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed");
	      
	  }
	  
	  XLUtils.setExcelSheetNm("MaxSetPTRangeMin-1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 35, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxPressureSetpointRangeLessThanMinValueBAR(String min) throws
	  Exception {
	  
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared"); 
	  
	  pvm.setMaximumPressureSetPointRange(min);
	  log.info("Max pressure set point range provided");
	  
	  pvm.saveButton();
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),
	  Constants.ElctnicadjstBARMaxPressuresetpointRangeerrormsg); 
	  log.info("Maximum Pressure Set Point Range should be should be between 1.38 and 6.9 BAR message displyed"); 
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	  }
	  
	  XLUtils.setExcelSheetNm("MaxSetPTRangeMinBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 36, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxPressureSetpointRangeMinValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared");
	  
	  pvm.setMaximumPressureSetPointRange(min);
	  log.info("Max pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MaxSetPTRangeMin+1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 37, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxPressureSetpointRangeMoreThanMinValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared"); 
	  
	  pvm.setMaximumPressureSetPointRange(min);
	  log.info("Max pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
		  
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed"); 
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MaxSetPTRangeMax-1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 38, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxPressureSetpointRangeLessThanMaxValueBAR(String min) throws
	  Exception {
	  
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared"); 
	  
	  pvm.setMaximumPressureSetPointRange(min);
	  log.info("Max pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
	  Assert.assertTrue(false); 
	  log.info("toast Message is not displayed");
	  
	  }
	  
	  XLUtils.setExcelSheetNm("MaxSetPTRangeMaxBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 39, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxPressureSetpointRangeMaxValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared"); 
	  
	  pvm.setMaximumPressureSetPointRange(min);
	  log.info("Max pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked");
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	  log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
		  
	  Assert.assertTrue(false); log.info("toast Message is not displayed");
	    
	  }
	  
	  XLUtils.setExcelSheetNm("MaxSetPTRangeMax+1BAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 40, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxPressureSetpointRangeMoreThanMaxValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared"); 
	  
	  pvm.setMaximumPressureSetPointRange(min);
	  log.info("Max pressure set point range provided");
	  
	  pvm.saveButton();
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	  Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARMaxPressuresetpointRangeerrormsg); 
	  log.info("Maximum Pressure Set Point Range should be should be between 1.38 and 6.9 BAR message displyed");
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed");
	      
	      }
	  
	  XLUtils.setExcelSheetNm("MaxSetPTRangeMidBAR"); 
	 }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 41, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxPressureSetpointRangeMidValueBAR(String min) throws Exception {
	  
	  
	  pvm.clearMaximumPressureSetPointRange();
	  log.info("Max pressure set point range Cleared"); 
	  
	  pvm.setMaximumPressureSetPointRange(min);
	  log.info("Max pressure set point range provided");
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	         Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
	         log.info("Pressure updated successfully message displyed"); 
	  
	  } else {
		  
	          Assert.assertTrue(false); 
	          log.info("toast Message is not displayed"); 
	  
	          }
	  
	  
	  XLUtils.setExcelSheetNm("SPLessThanMinMaxRangeBar");
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 42, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyPressureSetpointwithSameMinMaxRangeValueBAR(String min,String max) throws Exception {
			  
			  
			  
			  pvm.clearPressureMinimumSetPointRange();
			  log.info("Min pressure set point range cleared"); 
			  
			  pvm.setMinimumPressureSetPointRange(min);
			  log.info("Min pressure set point range provided");
			  
			  pvm.clearPressureSetPoint(); 
			  log.info("Pressure Set point value cleared");
			  
			  pvm.setPressureSetPoint(max); 
			  log.info("pressure set point value provided");
			  
			  
			  pvm.saveButton(); 
			  log.info("Save Button Clicked");
			  Am.sleepTime(1500);
			  
			  if (pvm.toastmessageDisplayed() == true) {
			  
			             Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARPressuresetpointerrormsg4);
			             log.info("Pressure Set Point should be 2.2 BAR message displyed"); 
			  
			  } else {
				  
			             Assert.assertTrue(false); log.info("toast Message is not displayed"); 
			             
			          }
	  	  
	  XLUtils.setExcelSheetNm("MinMoreThanMaxRangeBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 43, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMinPressureSetpointRangeMoreThanMaxPressSetPTRangeValueBAR(String min)
	  throws Exception {
	  
	  
	  
	  pvm.clearPressureMinimumSetPointRange();
	  log.info("Min pressure set point range cleared"); 
	  
	  pvm.setMinimumPressureSetPointRange(min);
	  log.info("Min pressure set point range provided");
	  
	  
	  pvm.saveButton(); 
	  log.info("Save Button Clicked"); 
	  Am.sleepTime(1500);
	  
	  if (pvm.toastmessageDisplayed() == true) {
	  
	      Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstMaxlessMinPressuresetpointRangeerrormsg); 
	      log.info("Minimum Pressure Setpoint Range should be less than Maximum Pressure Setpoint Range message displyed");
	  
	  } else { 
		  
		  Assert.assertTrue(false);
	      log.info("toast Message is not displayed"); 
	      
	         }
	  
	  XLUtils.setExcelSheetNm("SPMaXMinRangeSameBAR"); 
	  
	  }
	  
	  @JiraPolicy(logTicketReady = false)
	  
	  @Test(priority = 44, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxMinPressureSetpointRangeSameValueBAR(String min,String max) throws Exception {
		  
		  pvm.clearPressureSetPoint();
		  log.info("Pressure Set point value cleared");
		  
		  pvm.setPressureSetPoint(min); 
		  log.info("pressure set point value provided");
		  
		  pvm.clearPressureMinimumSetPointRange();
		  log.info("Min pressure set point range cleared");
		  
		  pvm.setMinimumPressureSetPointRange(max);
		  log.info("Min pressure set point range provided");
		  
		  
		  pvm.saveButton();
		  log.info("Save Button Clicked"); 
		  Am.sleepTime(1500);
		  
		  if (pvm.toastmessageDisplayed() == true) {
		  
		          Assert.assertEquals(pvm.getToastMessageText(),Constants.ElctnicadjstBARmaxerrormsg); 
		          log.info("Minimum and Maximum Pressure Set Point Range difference should be minimum 0.34 BAR message displyed"); 
		  
		  } else {
			  
			       Assert.assertTrue(false);
		           log.info("toast Message is not displayed");
		           
		           }

		  XLUtils.setExcelSheetNm("SPMaXMinDiffRangeBAR");
	  
	   }
	  @Test(priority = 45, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class) 
	  // @Feature("") 
	  public void VerifyMaxMinPressureSetpointRangeDiffValueBAR(String min) throws Exception {
		  
		  pvm.clearPressureMinimumSetPointRange();
		  log.info("Min pressure set point range cleared");
		  
		  pvm.setMinimumPressureSetPointRange(min);
		  log.info("Min pressure set point range provided");
		  
		  
		  pvm.saveButton();
		  log.info("Save Button Clicked"); 
		  Am.sleepTime(1500);
		  
		  if (pvm.toastmessageDisplayed() == true) {
			  
		         Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
		         log.info("Pressure updated successfully message displyed"); 
		  
		  } else {
			  
		          Assert.assertTrue(false); 
		          log.info("toast Message is not displayed"); 
		  
		          }
	  
	   }
	 
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 46, enabled = true)
	// @Feature("")
	public void navigateToElectronicadjustKPA() throws Exception {

		
		
		pvm.clickDashboard();
		log.info("Dashboard link clicked");
		
		pvm.clickSetUpToolLink();
		log.info("setup tool link clicked");

		pvm.clickCreateNewFile();
		log.info("create new file clicked");

		pvm.clickSubmit();
		log.info("submit button clicked");
		
		pvm.ClickSystemSettingsLink();
		log.info("SystemSetting Link Clicked");
		
		pvm.ClickPreferencesLink();
		log.info("Preference Link Clicked");
		
		pvm.SelectKPAUnit();
		log.info("KPA unit selected");
		
		pvm.saveButton();
		log.info("Save Button clicked");
		
		pvm.clickRunTimeSettings();
		log.info("Runtime setting link clicked");
		Am.sleepTime(1500);

		pvm.clickPressure();
		log.info("pressure clicked");

		pvm.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		
		
		
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 47, enabled = true)
	// @Feature("")
	public void VerifyLowPressAlertThresholdNullValueKPA() throws Exception {
		
	 pvm.clearLowPressureAlertThreshold();
	 log.info("Low pressure alert field Cleared");
	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAlowpressurethholderrormsg);
			log.info("Low Pressure Alert Threshold should be between 34 and 483 KPA message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("LowPressThBelowMInKPA");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 48, enabled = true, dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdbelowMinValueKPA(String min) throws Exception {
	
	
	 
	 pvm.setLowPressureAlertThreshold(min);
	 log.info("Low pressure alert value provided");
	 	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAlowpressurethholderrormsg);
			log.info("Low Pressure Alert Threshold should be between 34 and 483 KPA message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMInKPA");
	
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 49, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdMinValueKPA(String min) throws Exception {
	
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
		XLUtils.setExcelSheetNm("LowPressThMIn+1KPA");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 50, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdmorethanMinValueKPA(String min) throws Exception {
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThBelowMaxKPA");
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 51, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdlessthanMaxValueKPA(String min) throws Exception {
	
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMaxKPA");
	
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 52, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdMaxValueKPA(String min) throws Exception {
		 
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMax+1KPA");
	
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 53, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdmorethanMaxValueKPA(String min) throws Exception {
	
		pvm.clearLowPressureAlertThreshold();
		log.info("Low pressure alert field cleared");
		 
		pvm.setLowPressureAlertThreshold(min);
		log.info("Low pressure alert value provided");
		Am.sleepTime(1500);
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAlowpressurethholderrormsg);
			log.info("Low Pressure Alert Threshold should be between 34 and 483 KPA message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMidKPA");
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 54, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdMidValueKPA(String min) throws Exception {
		
		pvm.clearLowPressureAlertThreshold();
		log.info("Low pressure alert field cleared");
		 
		pvm.setLowPressureAlertThreshold(min);
		log.info("Low pressure alert value provided");
		Am.sleepTime(1500);
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 55, enabled = true)
	// @Feature("")
	public void VerifyHighPressAlertThresholdNullValueKPA() throws Exception {
		
	 pvm.clearHighPressureAlertThreshold();
	 log.info("High pressure alert field cleared");
	 Am.sleepTime(1500);
	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAhighpressurethholderrormsg);
			log.info("High Pressure Alert Threshold should be between 34 and 483 KPA message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("LowPressThBelowMInKPA");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 56, enabled = true, dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdbelowMinValueKPA(String min) throws Exception {
		
	 
	 pvm.setHighPressureAlertThreshold(min);;
	 log.info("High pressure alert value provided");
	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAhighpressurethholderrormsg);
			log.info("High Pressure Alert Threshold should be between 34 and 483 KPA message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMInKPA");
	
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 57, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdMinValueKPA(String min) throws Exception {
	 
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
		XLUtils.setExcelSheetNm("LowPressThMIn+1KPA");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 58, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdmorethanMinValueKPA(String min) throws Exception {
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThBelowMaxKPA");
	}
	//MinPressThMIn+1BAR
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 59, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdlessthanMaxValueKPA(String min) throws Exception {
	
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMaxKPA");
	
	}
	//
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 60, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdMaxValueKPA(String min) throws Exception {
		
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMax+1KPA");
	
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 61, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdmorethanMaxValueKPA(String min) throws Exception {
	
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAhighpressurethholderrormsg);
			log.info("High Pressure Alert Threshold should be between 34 and 483 KPA message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMidKPA");
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 62, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdMidValueKPA(String min) throws Exception {
		
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("PressureSetPointNullKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 63, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointNullValueKPA(String min,String max) throws Exception {
		
		pvm.clearPressureMinimumSetPointRange();
		Am.sleepTime(1500);
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		pvm.clearMaximumPressureSetPointRange();
		Am.sleepTime(1500);
		pvm.setMaximumPressureSetPointRange(max);
		log.info("Max pressure set point range provided");
		 
		pvm.clearPressureSetPoint();
		log.info("Pressure Set point value cleared");
		Am.sleepTime(1500);
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAPressuresetpointerrormsg);
				log.info("Pressure Set Point should be between 69 and 690 BAR message displayed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmin-1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 64, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointlessthanMinValueKPA(String min) throws Exception {
		
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAPressuresetpointerrormsg);
				log.info("Pressure Set Point should be between 69 and 690 BAR message displayed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("PressureSetPointminKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 65, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointMinValueKPA(String min) throws Exception {
		
		//Am.sleepTime(1500);
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1500);
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmin+1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 66, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointMorethanMinValueKPA(String min) throws Exception {
		
		
	    pvm.clearPressureSetPoint(); 
	    log.info("Pressure Set point value cleared");
	    Am.sleepTime(1500);
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmax-1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 67, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointlessthanMaxValueKPA(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1000);
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmaxKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 68, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointMaxValueKPA(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1000);
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmax+1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 69, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointMorethanMaxValueKPA(String min) throws Exception {
		
		
		  
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1500);
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAPressuresetpointerrormsg);
				log.info("Pressure Set Point should be between 69 and 690 BAR message displayed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmidvalueKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 70, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointmidValueKPA(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1500);
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeNullKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 71, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeNullValueKPA(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAMinPressuresetpointRangeerrormsg);
			log.info("Minimum Pressure Set Point Range should be 69 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeMin-1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 72, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangelessThanMinValueKPA(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAMinPressuresetpointRangeerrormsg);
			log.info("Minimum Pressure Set Point Range should be 69 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeMinKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 73, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMinValueKPA(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeMin+1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 74, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMoreThanMinValueKPA(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAPressuresetpointerrormsg2);
			log.info("Pressure Set Point should be between 70 and 690 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointval-1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 75, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeLessThanSetMinValueKPA(String min,String max) throws Exception {
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(max);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointvalKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 76, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMinPressSetPointvalKPA(String min) throws Exception {
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointval+1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 77, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMoreThanSetMinValueKPA(String min) throws Exception {
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAPressuresetpointerrormsg3);
			log.info("Pressure Set Point should be between 90 and 690 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxsetpointrangeNullKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 78, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeNullValueKPA(String min,String max) throws Exception {
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(max);
		log.info("Min pressure set point range provided");
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);

		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAMaxPressuresetpointRangeerrormsg);
			log.info("Maximum Pressure Set Point Range should be should be between 138 and 690 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMin-1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 79, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeLessThanMinValueKPA(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAMaxPressuresetpointRangeerrormsg);
			log.info("Maximum Pressure Set Point Range should be should be between 138 and 690 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMinKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 80, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMinValueKPA(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMin+1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 81, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMoreThanMinValueKPA(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMax-1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 82, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeLessThanMaxValueKPA(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMaxKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 83, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMaxValueKPA(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMax+1KPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 84, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMoreThanMaxValueKPA(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAMaxPressuresetpointRangeerrormsg);
			log.info("Maximum Pressure Set Point Range should be should be between 138 and 690 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMidKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 85, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMidValueKPA(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("SPLessThanMinMaxRangeKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 86, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
public void VerifyPressureSetpointwithSameMinMaxRangeValueKPA(String min,String max) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(max);
		log.info("pressure set point value provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAPressuresetpointerrormsg4);
			log.info("Pressure Set Point should be 220 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

	
	
		 XLUtils.setExcelSheetNm("MinMoreThanMaxRangeKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 87, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMoreThanMaxPressSetPTRangeValueKPA(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstMaxlessMinPressuresetpointRangeerrormsg);
			log.info("Minimum Pressure Setpoint Range should be less than Maximum Pressure Setpoint Range message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("SPMaXMinRangeSameKPA");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 88, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
    public void VerifyMaxMinPressureSetpointRangeSameValueKPA(String min,String max) throws Exception {
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(max);
		log.info("Min pressure set point range provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstKPAmaxerrormsg);
			log.info("Minimum and Maximum Pressure Set Point Range difference should be minimum 34 KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		XLUtils.setExcelSheetNm("SPMaXMinDiffRangeKPA");
		 
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 89, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
    public void VerifyMaxMinPressureSetpointRangeDiffValueKPA(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");

		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		
		 
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 90, enabled = true)
	// @Feature("")
	public void navigateToElectronicadjustPSI() throws Exception {

		
		
		pvm.clickDashboard();
		log.info("Dashboard link clicked");
		
		pvm.clickSetUpToolLink();
		log.info("setup tool link clicked");

		pvm.clickCreateNewFile();
		log.info("create new file clicked");

		pvm.clickSubmit();
		log.info("submit button clicked");
		
		pvm.ClickSystemSettingsLink();
		log.info("SystemSetting Link Clicked");
		
		pvm.ClickPreferencesLink();
		log.info("Preference Link Clicked");
		
		pvm.SelectPSIUnit();
		log.info("PSI unit selected");
		
		pvm.saveButton();
		log.info("Save Button clicked");
		
		pvm.clickRunTimeSettings();
		log.info("Runtime setting link clicked");
		Am.sleepTime(1500);

		pvm.clickPressure();
		log.info("pressure clicked");

		pvm.SelectMainPressureModeSelectionDropdown("Electronic Pressure Adjust");
		
		
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 91, enabled = true)
	// @Feature("")
	public void VerifyLowPressAlertThresholdNullValuePSI() throws Exception {
		
	 pvm.clearLowPressureAlertThreshold();
	 log.info("Low pressure alert field Cleared");
	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIlowpressurethholderrormsg);
			log.info("Low Pressure Alert Threshold should be between 5 and 70 PSI message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("LowPressThBelowMInPSI");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 92, enabled = true, dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdbelowMinValuePSI(String min) throws Exception {
	
	
	 
	 pvm.setLowPressureAlertThreshold(min);
	 log.info("Low pressure alert value provided");
	 	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIlowpressurethholderrormsg);
			log.info("Low Pressure Alert Threshold should be between 5 and 70 PSI message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMInPSI");
	
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 93, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdMinValuePSI(String min) throws Exception {
	
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
		XLUtils.setExcelSheetNm("LowPressThMIn+1PSI");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 94, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdmorethanMinValuePSI(String min) throws Exception {
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThBelowMaxPSI");
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 95, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdlessthanMaxValuePSI(String min) throws Exception {
	
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMaxPSI");
	
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 96, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdMaxValuePSI(String min) throws Exception {
		 
		 pvm.clearLowPressureAlertThreshold();
		 log.info("Low pressure alert field cleared");
		 
		 pvm.setLowPressureAlertThreshold(min);
		 log.info("Low pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMax+1PSI");
	
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 97, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdmorethanMaxValuePSI(String min) throws Exception {
	
		pvm.clearLowPressureAlertThreshold();
		log.info("Low pressure alert field cleared");
		 
		pvm.setLowPressureAlertThreshold(min);
		log.info("Low pressure alert value provided");
		Am.sleepTime(1500);
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIlowpressurethholderrormsg);
			log.info("Low Pressure Alert Threshold should be between 5 and 70 PSI message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMidPSI");
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 98, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyLowPressAlertThresholdMidValuePSI(String min) throws Exception {
		
		pvm.clearLowPressureAlertThreshold();
		log.info("Low pressure alert field cleared");
		 
		pvm.setLowPressureAlertThreshold(min);
		log.info("Low pressure alert value provided");
		Am.sleepTime(1500);
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 99, enabled = true)
	// @Feature("")
	public void VerifyHighPressAlertThresholdNullValuePSI() throws Exception {
		
	 pvm.clearHighPressureAlertThreshold();
	 log.info("High pressure alert field cleared");
	 Am.sleepTime(1500);
	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIhighpressurethholderrormsg);
			log.info("High Pressure Alert Threshold should be between 5 and 70 PSI message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

		//softAssert.assertAll();
		
		XLUtils.setExcelSheetNm("LowPressThBelowMInPSI");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 100, enabled = true, dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdbelowMinValuePSI(String min) throws Exception {
		
	 
	 pvm.setHighPressureAlertThreshold(min);;
	 log.info("High pressure alert value provided");
	 
	 pvm.saveButton();
	 log.info("Save Button Clicked");
	 Am.sleepTime(1500);
	 
	 if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIhighpressurethholderrormsg);
			log.info("High Pressure Alert Threshold should be between 5 and 70 PSI message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMInPSI");
	
	}
	
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 101, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdMinValuePSI(String min) throws Exception {
	 
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
		XLUtils.setExcelSheetNm("LowPressThMIn+1PSI");
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 102, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdmorethanMinValuePSI(String min) throws Exception {
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThBelowMaxPSI");
	}
	//MinPressThMIn+1BAR
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 103, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdlessthanMaxValuePSI(String min) throws Exception {
	
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMaxPSI");
	
	}
	//
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 104, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdMaxValuePSI(String min) throws Exception {
		
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		 pvm.saveButton();
		 log.info("Save Button Clicked");
		 Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("LowPressThMax+1PSI");
	
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 105, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdmorethanMaxValuePSI(String min) throws Exception {
	
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		
		if (pvm.toastmessageDisplayed() == true) {
			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIhighpressurethholderrormsg);
			log.info("High Pressure Alert Threshold should be between 5 and 70 PSI message displayed");

		} else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	 
	 XLUtils.setExcelSheetNm("LowPressThMidPSI");
	
	
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 106, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyHighPressAlertThresholdMidValuePSI(String min) throws Exception {
		
		pvm.clearHighPressureAlertThreshold();
		log.info("High pressure alert field cleared");
		 
		pvm.setHighPressureAlertThreshold(min);;
		log.info("High pressure alert value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
				log.info("Pressure updated successfully message displyed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("PressureSetPointNullPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 107, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointNullValuePSI(String min,String max) throws Exception {
		
		pvm.clearPressureMinimumSetPointRange();
		Am.sleepTime(1500);
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		pvm.clearMaximumPressureSetPointRange();
		Am.sleepTime(1500);
		pvm.setMaximumPressureSetPointRange(max);
		log.info("Max pressure set point range provided");
		 
		pvm.clearPressureSetPoint();
		log.info("Pressure Set point value cleared");
		Am.sleepTime(1500);
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIPressuresetpointerrormsg);
				log.info("Pressure Set Point should be between 10 and 100 PSI message displayed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmin-1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 108, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointlessthanMinValuePSI(String min) throws Exception {
		
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		 if (pvm.toastmessageDisplayed() == true) {

				Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIPressuresetpointerrormsg);
				log.info("Pressure Set Point should be between 10 and 100 PSI message displayed");
			} 
			else {
				Assert.assertTrue(false);
				log.info("toast Message is not displayed");
			}
	
		 XLUtils.setExcelSheetNm("PressureSetPointminPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 109, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointMinValuePSI(String min) throws Exception {
		
		//Am.sleepTime(1500);
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1500);
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmin+1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 110, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointMorethanMinValuePSI(String min) throws Exception {
		
		
	    pvm.clearPressureSetPoint(); 
	    log.info("Pressure Set point value cleared");
	    Am.sleepTime(1500);
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmax-1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 111, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointlessthanMaxValuePSI(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1000);
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("PressureSetPointmaxPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 112, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointMaxValuePSI(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1000);
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
			
		 XLUtils.setExcelSheetNm("PressureSetPointmidvaluePSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 113, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyPressureSetpointmidValuePSI(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		//Am.sleepTime(1500);
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeNullPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 114, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeNullValuePSI(String min) throws Exception {
		
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		 
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIMinPressuresetpointRangeerrormsg);
			log.info("Minimum Pressure Set Point Range should be 10 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeMin-1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 115, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangelessThanMinValuePSI(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIMinPressuresetpointRangeerrormsg);
			log.info("Minimum Pressure Set Point Range should be 10 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeMinPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 116, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMinValuePSI(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointRangeMin+1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 117, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMoreThanMinValuePSI(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIPressuresetpointerrormsg2);
			log.info("Pressure Set Point should be between 11 and 100 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointval-1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 118, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeLessThanSetMinValuePSI(String min,String max) throws Exception {
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(max);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointvalPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 119, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMinPressSetPointvalPSI(String min) throws Exception {
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MinPressSetPointval+1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 120, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMoreThanSetMinValuePSI(String min) throws Exception {
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIPressuresetpointerrormsg3);
			log.info("Pressure Set Point should be between 21 and 100 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxsetpointrangeNullPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 121, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeNullValuePSI(String min,String max) throws Exception {
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(max);
		log.info("Min pressure set point range provided");
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);

		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIMaxPressuresetpointRangeerrormsg);
			log.info("Maximum Pressure Set Point Range should be between 20 and 100 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMin-1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 122, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeLessThanMinValuePSI(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIMaxPressuresetpointRangeerrormsg);
			log.info("Maximum Pressure Set Point Range should be between 20 and 100 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMinPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 123, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMinValuePSI(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMin+1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 124, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMoreThanMinValuePSI(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMax-1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 125, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeLessThanMaxValuePSI(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMaxPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 126, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMaxValuePSI(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMax+1PSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 127, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMoreThanMaxValuePSI(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIMaxPressuresetpointRangeerrormsg);
			log.info("Maximum Pressure Set Point Range should be between 20 and 100 PSI KPA message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("MaxSetPTRangeMidPSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 128, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMaxPressureSetpointRangeMidValuePSI(String min) throws Exception {
		
		
		pvm.clearMaximumPressureSetPointRange();
		log.info("Max pressure set point range Cleared");
		//Am.sleepTime(1500);
     
		pvm.setMaximumPressureSetPointRange(min);
     	log.info("Max pressure set point range provided");
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("SPLessThanMinMaxRangePSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 129, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
public void VerifyPressureSetpointwithSameMinMaxRangeValuePSI(String min,String max) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(max);
		log.info("pressure set point value provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSIPressuresetpointerrormsg4);
			log.info("Pressure Set Point should be 50 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}

	
	
		 XLUtils.setExcelSheetNm("MinMoreThanMaxRangePSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 130, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
	public void VerifyMinPressureSetpointRangeMoreThanMaxPressSetPTRangeValuePSI(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstMaxlessMinPressuresetpointRangeerrormsg);
			log.info("Minimum Pressure Setpoint Range should be less than Maximum Pressure Setpoint Range message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
	
		 XLUtils.setExcelSheetNm("SPMaXMinRangeSamePSI");
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 131, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
    public void VerifyMaxMinPressureSetpointRangeSameValuePSI(String min,String max) throws Exception {
		
		pvm.clearPressureSetPoint(); 
		log.info("Pressure Set point value cleared");
		
		pvm.setPressureSetPoint(min);
		log.info("pressure set point value provided");
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");
		//Am.sleepTime(1000);
		
		pvm.setMinimumPressureSetPointRange(max);
		log.info("Min pressure set point range provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.ElctnicadjstPSImaxerrormsg);
			log.info("Minimum and Maximum Pressure Set Point Range difference should be minimum 5 PSI message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		XLUtils.setExcelSheetNm("SPMaXMinDiffRangePSI");
		 
	}
	@JiraPolicy(logTicketReady = false)
	@Test(priority = 132, enabled = true,dataProvider = "ElectronicPressureAdjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	// @Feature("")
    public void VerifyMaxMinPressureSetpointRangeDiffValuePSI(String min) throws Exception {
		
		
		
		pvm.clearPressureMinimumSetPointRange();
		log.info("Min pressure set point range cleared");

		
		pvm.setMinimumPressureSetPointRange(min);
		log.info("Min pressure set point range provided");
		
		 
		pvm.saveButton();
		log.info("Save Button Clicked");
		Am.sleepTime(1500);
		 
		if (pvm.toastmessageDisplayed() == true) {

			Assert.assertEquals(pvm.getToastMessageText(), Constants.Pressuresucssmsg);
			log.info("Pressure updated successfully message displyed");
		} 
		else {
			Assert.assertTrue(false);
			log.info("toast Message is not displayed");
		}
		
		 
	}

	
}
