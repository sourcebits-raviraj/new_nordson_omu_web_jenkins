package com.nordson.testCases;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.User_Dashboard_Details_Landing_Page;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.ReadConfig;

public class TC_OMU_1891_1936_User_Dashboard_Links extends TC_LoginTest_DDT_001 {

	ActionMethods Am = new ActionMethods();
	Actions action;

	private SoftAssert softAssert = new SoftAssert();

	// Driver Object Instantiation
	User_Dashboard_Details_Landing_Page lp;
	ReadConfig readconfig = new ReadConfig();

	@Test(priority = 1, enabled = true)
	public void Logo_Displayed() throws Exception {

		lp = new User_Dashboard_Details_Landing_Page(driver);
		Am.sleepTime(2000);
		if (lp.logoDisplayed() == true) {
			Am.drawBorder(lp.logo, driver);
			Am.sleepTime(1000);
			log.info("Nordosn Logo is displayed");
			softAssert.assertTrue(true);

		} else

		{
			log.info("Nordosn Logo is not displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 2, enabled = true)
	public void Welcome_Displayed() throws Exception {

		if (lp.welcomeDisplayed() == true) {

			Am.drawBorder(lp.welcome, driver);
			Am.sleepTime(1000);
			Assert.assertTrue(lp.welcome.getText().contains("Welcome"));
			log.info("Welcome text is displayed");
			softAssert.assertTrue(true);

		} else

		{
			log.info("Welcome text is not displayed");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 3, enabled = true)
	public void Cards_Display_Setting_Event_log_File() throws Exception {

		if (lp.setting_Event_Cards_Displayed() == 2) {

			log.info("Number of cards displayed in displayed are 2");
			softAssert.assertTrue(true);

		} else

		{
			log.info("No Cards Displayed");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 4, enabled = true)
	public void Recently_Viewed_Card_Text() throws Exception {

		if (lp.Recently_Viewed_Event_Logs_Text().equalsIgnoreCase("Recently Viewed")) {

			Am.drawBorder(lp.RecentlyViewed, driver);
			Am.sleepTime(1000);
			// Assert.assertTrue((lp.RecentlyViewed).getText().contains("Recently Viewed"));
			log.info("Recently Viewed Event log Files are displayed");
			softAssert.assertTrue(true);

		} else {

			log.info("Recently Viewed Event logs are not displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 5, enabled = true)
	public void Event_Log_Files_Card_Text() throws Exception {

		if (lp.Recently_Created_Setting_Files_Text().equalsIgnoreCase("Recently Created or Imported Setting Files")) {

			Am.drawBorder(lp.EventLogFiles, driver);
			Am.sleepTime(1000);
			// Assert.assertTrue((lp.EventLogFiles).getText().contains("Recently Created or
			// Imported Setting Files"));
			log.info("Recently Created or Imported Setting Files are displayed");
			softAssert.assertTrue(true);

		} else {
			log.info("Recently Created or Imported Setting Files are not displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 6, enabled = true)
	public void View_DashBoard() throws Exception {

		if (lp.Dashboard_Text().equalsIgnoreCase("DASHBOARD")) {

			Am.drawBorder(lp.DashBoard, driver);
			Am.sleepTime(1000);
			log.info("Dashboard Text displayed");
			softAssert.assertTrue(true);

		} else {
			log.info("Dashboard Text not displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 7, enabled = true)
	public void Model_Registration() throws Exception {

		if (lp.Model_Registration().equalsIgnoreCase("Model Registration")) {

			Am.drawBorder(lp.ModelRegistration, driver);
			Am.sleepTime(1000);
			log.info("Model Registration Text displayed");
			softAssert.assertTrue(true);

		} else {
			log.info("Model Registration not Text displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 8, enabled = true)
	public void Sub_User_Account_Link() throws Exception {

		if (lp.SubUserAccount().equalsIgnoreCase("Sub User Account")) {

			Am.drawBorder(lp.SubUserAccount, driver);
			Am.sleepTime(1000);
			log.info("Sub User Account Text displayed");
			softAssert.assertTrue(true);

		} else {
			log.info("Sub User Account not Text displayed");
			Assert.assertTrue(false);
		}
	}
}
