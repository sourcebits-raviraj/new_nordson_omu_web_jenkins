package com.nordson.testCases;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Pressure_Min_Max_Validations;
import com.nordson.pageObjects.User_Dashboard_Details_Landing_Page;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

public class TC_OMU_1891_1936_User_Dashboard_Links extends TC_LoginTest_DDT_001 {

	ActionMethods Am = new ActionMethods();

	Pressure_Min_Max_Validations pmv;
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
			Am.drawBorderFail(lp.logo, driver);
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
			Am.drawBorderFail(lp.welcome, driver);
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
			Am.drawBorderFail(lp.RecentlyViewed, driver);
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
			Am.drawBorderFail(lp.EventLogFiles, driver);
			log.info("Recently Created or Imported Setting Files are not displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 6, enabled = true)
	public void CopyRightText() throws Exception {

		// lp.pageRefresh();
		// Am.sleepTime(1000);
		// lp.clickDashboard();

		if (lp.getCopyRightDisplayed() == true) {

			log.info("Copy Right Displayed Nordson Corporation");
			softAssert.assertTrue(true);
			String copytext = lp.getCopyRightText();
			Am.drawBorder(lp.CopyRight, driver);

			softAssert.assertEquals(Constants.copyRight, copytext);
			Am = new ActionMethods();
			Am.captureScreen(driver, "CopyRightPassDashboard");

		}

		else {
			Am.drawBorderFail(lp.CopyRight, driver);
			log.info("Copy Right not Displayed");
			Assert.assertTrue(true);
			Am = new ActionMethods();
			Am.captureScreen(driver, "CopyRightFailDashboard");
			System.out.println("Copy Right Not Present");

		}

	}

	@Test(priority = 7, enabled = true)
	public void Registration_Nordson_Privacy_Policy() throws Exception {

		if (lp.PrivacyPolicyDisplayed() == true) {

			log.info("Privacy Link Displayed");
			softAssert.assertTrue(true);
			String PrivacyPolicyText = lp.getPrivacyPolicyText();
			Am.drawBorder(lp.PrivacyPolicy, driver);
			softAssert.assertEquals(Constants.privacyPolicy, PrivacyPolicyText);
			System.out.println("Privacy Policy link is displayed");

		}

		else {
			Am.drawBorderFail(lp.PrivacyPolicy, driver);
			Assert.assertTrue(false);
			log.info("Privacy Link not Displayed");
			System.out.println("Privacy Policy link is not displayed");

		}
	}

	@Test(priority = 8, enabled = true)
	public void Registration_Nordosn_Terms_of_Services() throws Exception {

		if (lp.TermsOfServiceDisplayed() == true) {

			log.info("Terms Of Service text is Displayed");
			softAssert.assertTrue(true);
			String TermsServicesText = lp.getTermsOFServiceText();
			Am.drawBorder(lp.TermsService, driver);
			softAssert.assertEquals(Constants.termsOfServices, TermsServicesText);
			System.out.println("Terms of Service text is displayed");

		}

		else {
			Am.drawBorderFail(lp.TermsService, driver);
			Assert.assertTrue(false);
			log.info("Terms of Service text is displayed");
			System.out.println("Terms of Service text is displayed");
		}

	}

	@Test(priority = 9, enabled = true)
	public void Registration_Nordson_Cookies() throws Exception {

		if (lp.cookiesDisplayed() == true) {

			log.info("Cookies Link Displayed");
			softAssert.assertTrue(true);
			String CookiesText = lp.getCookiesText();
			Am.drawBorder(lp.Cookies, driver);
			softAssert.assertEquals(Constants.CookiesText, CookiesText);
			System.out.println("Cookies link is displayed");

		}

		else {
			Am.drawBorderFail(lp.Cookies, driver);
			log.info("Cookies Link not Displayed");
			System.out.println("Cookies Link is not displayed");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 10, enabled = true)
	public void Registration_Nordson_Contact_Us() throws Exception {

		if (lp.contactUsDisplayed() == true) {

			log.info("Contact Us Link Displayed");
			softAssert.assertTrue(true);
			String ContactUsText = lp.getContactUsText();
			Am.drawBorder(lp.ContactUs, driver);
			softAssert.assertEquals(Constants.ContactUs, ContactUsText);
			System.out.println("Contact Us link is displayed");

		}

		else {
			Am.drawBorderFail(lp.ContactUs, driver);
			log.info("Contact us link not Displayed");
			Am.captureScreen(driver, "CookiesFail");
			System.out.println("Cookies Link not opened");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 11, enabled = true)
	public void View_DashBoard() throws Exception {

		if (lp.Dashboard_Text().equalsIgnoreCase("DASHBOARD")) {

			Am.drawBorder(lp.DashBoard, driver);
			Am.sleepTime(1000);
			log.info("Dashboard Text displayed");
			softAssert.assertTrue(true);

		} else {
			Am.drawBorderFail(lp.DashBoard, driver);
			log.info("Dashboard Text not displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 12, enabled = true)
	public void Model_Registration() throws Exception {

		if (lp.Model_Registration().equalsIgnoreCase("Model Registration")) {

			Am.drawBorder(lp.ModelRegistration, driver);
			Am.sleepTime(1000);
			log.info("Model Registration Text displayed");
			softAssert.assertTrue(true);

		} else {
			Am.drawBorderFail(lp.ModelRegistration, driver);
			log.info("Model Registration not Text displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 13, enabled = true)
	public void Sub_User_Account_Link() throws Exception {

		if (lp.SubUserAccount().equalsIgnoreCase("Sub User Account")) {

			Am.drawBorder(lp.SubUserAccount, driver);
			Am.sleepTime(1000);
			log.info("Sub User Account Text displayed");
			softAssert.assertTrue(true);

		} else {
			Am.drawBorderFail(lp.SubUserAccount, driver);
			log.info("Sub User Account not Text displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 14, enabled = true)
	public void Set_Up_Tool() throws Exception {

		if (lp.SetUpTool().equalsIgnoreCase("SETUP TOOL")) {

			Am.sleepTime(1000);
			log.info("Clicked on Set Up Tool Link");
			lp.clickOnSetUpTool();
			Am.drawBorder(lp.SetUpToolLink, driver);

			softAssert.assertEquals(lp.CreateNewFile(), Constants.CreateNewNor);
			Am.drawBorder(lp.CreateNewNorFileButton, driver);

			softAssert.assertEquals(lp.LoadFromUSB(), Constants.LoadFromUSB);
			Am.drawBorder(lp.LoadFromUSB, driver);

			softAssert.assertEquals(lp.PreviousFileText(), Constants.PreviousFileText);
			Am.drawBorder(lp.PreviousFile, driver);

			log.info("Set Up tool Displayed");
			Am.sleepTime(1000);
			driver.navigate().back();

			softAssert.assertTrue(true);

		} else {

			Am.drawBorderFail(lp.SetUpToolLink, driver);
			Am.drawBorderFail(lp.CreateNewNorFileButton, driver);
			Am.drawBorderFail(lp.LoadFromUSB, driver);
			Am.drawBorderFail(lp.PreviousFile, driver);

			log.info("Set Up tool not  Displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 15, enabled = true)
	public void MediaCenter() throws Exception {

		if (lp.MediaCenterText().equalsIgnoreCase("Media Center")) {

			Am.sleepTime(1000);
			log.info("Clicked on Media Center Link");
			// lp.pageRefresh();
			// Am.sleepTime(1000);
			lp.MediaCenterClick();
			Assert.assertEquals(lp.MediaCenterText(), Constants.MediaCenterText);
			Am.drawBorder(lp.MediaCenter, driver);
			Am.sleepTime(3000);

			// Am.drawBorder(lp.CreateNewNorFileButton, driver);
			// Am.drawBorder(lp.LoadFromUSB, driver);
			// Am.drawBorder(lp.PreviousFile, driver);
			// Am.sleepTime(1000);
			log.info("Set Up tool Displayed");
			softAssert.assertTrue(true);

		} else {
			Am.drawBorderFail(lp.MediaCenter, driver);
			log.info("Set Up tool not  Displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 16, enabled = true)
	public void Manage_Lincenses() throws InterruptedException, IOException {

		if (lp.manageLincenseDisplayed() == true) {

			log.info("Manage License Link Displayed");
			softAssert.assertTrue(true);
			String LincenseText = lp.getLincenseText();
			softAssert.assertEquals(Constants.LincenseText, LincenseText);
			System.out.println("Cookies link is displayed");
			Am.drawBorder(lp.ManageLincense, driver);
			lp.clickManageLincense();
			Am.switchToNexttab();
			// tab1 = new ArrayList<String>(driver.getWindowHandles());
			// driver.switchTo().window(tab1.get(1));

		}

		else {
			Am.drawBorderFail(lp.ManageLincense, driver);
			log.info("Manage Lincense Link not Displayed");
			System.out.println("Cookies Link is not displayed");
			Assert.assertTrue(false);
		}

		if (readconfig.getManageLincenseURL().equals(driver.getCurrentUrl())) {

			// Am = new ActionMethods();
			softAssert.assertTrue(true);
			Am.captureScreen(driver, "ManageLicensePass");
			System.out.println(driver.getCurrentUrl());
			System.out.println("Manage Lincense link is opened and navigated");
			log.info("Manage Lincense link is opened and navigated");
			Am.closeCurrentTab_SwitchtoPrevioustab();
			// driver.close();
			// driver.switchTo().window(tabs.get(0));

		}

		else {

			log.info("Manage Lincense link is not displayed ");
			// Am = new ActionMethods();
			Am.captureScreen(driver, "ManageLicenseFail");
			System.out.println("Manage Lincense Link not opened");
			Assert.assertTrue(false);

		}

	}

	@Test(priority = 17, enabled = true)
	public void Help_Center() throws Exception {

		if (lp.helpCenterDisplayed() == true) {

			log.info("Help Center Link Displayed");
			softAssert.assertTrue(true);
			String HelpCenterText = lp.getHelpCenterText();
			softAssert.assertEquals(Constants.HelpCenterText, HelpCenterText);
			System.out.println("Help Center link is displayed");
			Am.drawBorder(lp.HelpCenter, driver);
			lp.clickHelpCenter();
			Am.switchToNexttab();
			// tab1 = new ArrayList<String>(driver.getWindowHandles());
			// driver.switchTo().window(tab1.get(1));

		}

		else {
			Am.drawBorderFail(lp.HelpCenter, driver);
			log.info("Help Center Link not Displayed");
			System.out.println("Help Center Link is not displayed");
			Assert.assertTrue(false);
		}

		if (readconfig.getHelpCenterURL().equals(driver.getCurrentUrl())) {

			// Am = new ActionMethods();
			softAssert.assertTrue(true);
			Am.captureScreen(driver, "ManageLicensePass");
			System.out.println(driver.getCurrentUrl());
			System.out.println("Manage Lincense link is opened and navigated");
			log.info("Manage Lincense link is opened and navigated");
			Am.closeCurrentTab_SwitchtoPrevioustab();
			// driver.close();
			// driver.switchTo().window(tabs.get(0));

		}

		else {

			log.info("Manage Lincense link is not displayed ");
			// Am = new ActionMethods();
			Am.captureScreen(driver, "ManageLicenseFail");
			System.out.println("Manage Lincense Link not opened");
			Assert.assertTrue(false);
			Am.sleepTime(3000);

		}

	}

	@Test(priority = 18, enabled = true)
	public void Langauges() throws Exception {

		lp.clickLanguage();
		Am.sleepTime(1000);

		if (lp.LanguageDropdownsCount() == 9) {

			log.info("Language count is 9");
			softAssert.assertTrue(true);

			System.out.println("Language count is = 9");
			Am.drawBorder(lp.ClickLanguage, driver);

		}

		else {
			Am.drawBorderFail(lp.ClickLanguage, driver);
			log.info("Language count is not equal 8");
			System.out.println("Language count is not equal 8");
			Assert.assertTrue(false);
		}

		String EnglishText = lp.LanaguageDropdowns.get(0).getText();

		if (EnglishText.equalsIgnoreCase("English")) {

			System.out.println("English Language is present");
			log.info("English Language is present");
			softAssert.assertTrue(true);
			Am.captureScreen(driver, "EnglisDropdownPass");

		} else {

			System.out.println("English Language is not present");
			log.info("English Language is not present ");
			// Am = new ActionMethods();
			Am.captureScreen(driver, "EnglisDropdownFail");
			System.out.println("English Language is not present");
			Assert.assertTrue(false);

		}

	}

	@Test(priority = 19, enabled = true)
	public void Profile() throws Exception {

		lp.LanaguageDropdowns.get(0).click();

		lp.clickProfile();
		Am.sleepTime(3000);

		if (lp.profileDisplayed() == true) {

			log.info("Profile Displayed");
			softAssert.assertTrue(true);

			softAssert.assertEquals(Constants.ProfileText, lp.profileText());
			Am.drawBorder(lp.ProfileDetails, driver);

			softAssert.assertEquals(Constants.FullName, lp.FullNameProfile());
			Am.drawBorder(lp.FullName, driver);

			softAssert.assertEquals(Constants.Email, lp.EmailText());
			Am.drawBorder(lp.Email, driver);

			softAssert.assertEquals(Constants.Country, lp.CountryText());
			Am.drawBorder(lp.Country, driver);

			softAssert.assertEquals(Constants.PhoneNumber, lp.PhoneNumberText());
			Am.drawBorder(lp.PhoneNumber, driver);

			softAssert.assertEquals(Constants.PlantName, lp.PlantNameText());
			Am.drawBorder(lp.PlantName, driver);

			softAssert.assertEquals(Constants.CompanyName, lp.CompanyNameText());
			Am.drawBorder(lp.CompanyName, driver);

			softAssert.assertEquals(Constants.CompanyType, lp.CompanyTypeText());
			Am.drawBorder(lp.CompanyType, driver);

			softAssert.assertEquals(Constants.Address, lp.AddressText());
			Am.drawBorder(lp.Address, driver);

			softAssert.assertEquals(Constants.NordosnAccountNumber, lp.NordsonAccountNumberText());
			Am.drawBorder(lp.NordsonAccountNumber, driver);

			softAssert.assertEquals(Constants.ChangePassword, lp.ChangePasswordText());
			Am.drawBorder(lp.ChangePassword, driver);

			softAssert.assertEquals(Constants.EditProfile, lp.EditProfileText());
			Am.drawBorder(lp.EditProfile, driver);

			System.out.println("All the profile fields are displayed");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();

		}

		else {
			Am.drawBorderFail(lp.ProfileDetails, driver);
			log.info("Profile not Displayed");
			Am.captureScreen(driver, "ProfileFile");
			System.out.println("Profile not opened");
			Assert.assertTrue(false);
		}

	}

}
