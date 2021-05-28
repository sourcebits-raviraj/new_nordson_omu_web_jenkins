package com.nordson.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.User_Registration_All_Links;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

public class TC_OMU_1849_1880_User_Registration_Links extends BaseClass {

	ActionMethods Am = new ActionMethods();
	Actions action;
	private SoftAssert softAssert = new SoftAssert();

	// Driver Object Instantiation
	User_Registration_All_Links rpl;
	ReadConfig readconfig = new ReadConfig();

	@Test(dataProvider = "RegistrationContine", dataProviderClass = com.nordson.utilities.XLUtils.class, priority = 1, enabled = true)
	public void Registration_Continue_Link(String fname, String companyname, String address, String plant,
			String phoneno) throws Exception {

		log.info("URL is launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		log.info("Wait for the page load time out");

		rpl = new User_Registration_All_Links(driver);
		Am = new ActionMethods();

		rpl.clickSingUp();
		log.info("Sign Up Page is opened");
		Am.sleepTime(3000);

		Am.drawBorder(rpl.FullName, driver);
		rpl.setFullName(fname);
		log.info("first name provided");

		Am.drawBorder(rpl.CompanyName, driver);
		rpl.setCompanyName(companyname);
		log.info("Company name provided");

		Am.drawBorder(rpl.CompanyType, driver);
		rpl.setCompanyType();
		log.info("Company type provided");

		Am.drawBorder(rpl.Address, driver);
		rpl.setAddress(address);
		log.info("Address provided");

		Am.drawBorder(rpl.Country, driver);
		rpl.selectCountry();
		log.info("Country provided");

		Am.drawBorder(rpl.Plant, driver);
		rpl.setPlant(plant);
		log.info("plant provided");

		Am.drawBorder(rpl.PhoneNumber, driver);
		rpl.setPhoneNumber(phoneno);
		log.info("Phone number provided");

		if (rpl.clickContinue() == true) {
			Am.drawBorder(rpl.Continue, driver);
			log.info("Continue button is enabled");
			System.out.println("Clicked on Continue button is  enabled");
			softAssert.assertTrue(true);

		} else {
			Am.drawBorderFail(rpl.Continue, driver);
			log.info("Clicked on Continue button is not enabled");
			System.out.println("Clicked on Continue button is not enabled");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 2, enabled = true)
	public void Registration_Nordson_CopyRightText() throws Exception {

		if (rpl.getCopyRightDisplayed() == true) {

			Am.drawBorder(rpl.CopyRight, driver);
			log.info("Copy Right Displayed Nordson Corporation");
			softAssert.assertTrue(true);
			String copytext = rpl.getCopyRightText();
			softAssert.assertEquals(Constants.copyRight, copytext);
			Am = new ActionMethods();
			Am.captureScreen(driver, "CopyRightPass");

		}

		else {
			Am.drawBorderFail(rpl.CopyRight, driver);
			log.info("Copy Right not Displayed");
			Assert.assertTrue(true);
			Am = new ActionMethods();
			Am.captureScreen(driver, "CopyRightFail");
			System.out.println("Copy Right Not Present");

		}

	}

	@Test(priority = 3, enabled = true)
	public void Registration_Nordson_Privacy_Policy() throws Exception {

		if (rpl.PrivacyPolicyDisplayed() == true) {

			Am.drawBorder(rpl.PrivacyPolicy, driver);
			log.info("Privacy Link Displayed");
			softAssert.assertTrue(true);
			String PrivacyPolicyText = rpl.getPrivacyPolicyText();
			softAssert.assertEquals(Constants.privacyPolicy, PrivacyPolicyText);
			System.out.println("Privacy Policy link is displayed");

			rpl.clickPrivacy();
			Am.switchToNexttab();

		}

		else {
			Am.drawBorderFail(rpl.PrivacyPolicy, driver);
			softAssert.assertTrue(false);
			log.info("Privacy Link not Displayed");
			System.out.println("Privacy Policy link is not displayed");

		}

		if (readconfig.getPrivacyPolicyURL().equals(driver.getCurrentUrl())) {

			softAssert.assertTrue(true);
			Am.captureScreen(driver, "PrivacyPolicyPass");
			System.out.println(driver.getCurrentUrl());
			System.out.println("Privacy Policy link is opened and naviagated");
			log.info("Privacy Policy link");
			Am.closeCurrentTab_SwitchtoPrevioustab();

		}

		else {
			log.info("Privacy Policy link not displayed ");

			Am.captureScreen(driver, "PrivacyPolicyFail");
			System.out.println("Privacy Policy not naviagted");
			Assert.assertTrue(false);

		}

	}

	@Test(priority = 4, enabled = true)
	public void Registration_Nordosn_Terms_of_Services() throws Exception {

		if (rpl.TermsOfServiceDisplayed() == true) {

			Am.drawBorder(rpl.TermsService, driver);
			log.info("Terms Of Service text is Displayed");
			softAssert.assertTrue(true);
			String TermsServicesText = rpl.getTermsOFServiceText();
			softAssert.assertEquals(Constants.termsOfServices, TermsServicesText);
			System.out.println("Terms of Service text is displayed");

		}

		else {
			Am.drawBorderFail(rpl.TermsService, driver);
			Assert.assertTrue(false);
			log.info("Terms of Service text is displayed");
			System.out.println("Terms of Service text is displayed");
		}

	}

	@Test(priority = 5, enabled = true)
	public void Registration_Nordson_Cookies() throws Exception {

		if (rpl.cookiesDisplayed() == true) {
			Am.drawBorder(rpl.Cookies, driver);
			log.info("Cookies Link Displayed");
			softAssert.assertTrue(true);
			String CookiesText = rpl.getCookiesText();
			softAssert.assertEquals(Constants.CookiesText, CookiesText);
			System.out.println("Cookies link is displayed");
			rpl.clickCookies();
			Am.switchToNexttab();

		}

		else {
			Am.drawBorderFail(rpl.Cookies, driver);
			log.info("Cookies Link not Displayed");
			System.out.println("Cookies Link is not displayed");
			Assert.assertTrue(false);
		}

		if (readconfig.getCookiesURL().equals(driver.getCurrentUrl())) {

			softAssert.assertTrue(true);
			Am.captureScreen(driver, "CookiesPass");
			System.out.println(driver.getCurrentUrl());
			System.out.println("Cookies link is opened and navigated");
			log.info("Cookies link is opened and navigated");
			Am.closeCurrentTab_SwitchtoPrevioustab();

		}

		else {

			log.info("Cookies link not displayed ");
			Am.captureScreen(driver, "CookiesFail");
			System.out.println("Cookies Link not opened");
			Assert.assertTrue(false);

		}

	}

	@Test(priority = 6, enabled = true)
	public void Registration_Nordson_Contact_Us() throws Exception {

		if (rpl.contactUsDisplayed() == true) {
			Am.drawBorder(rpl.ContactUs, driver);
			log.info("Contact Us Link Displayed");
			Assert.assertTrue(true);
			String ContactUsText = rpl.getContactUsText();
			softAssert.assertEquals(Constants.ContactUs, ContactUsText);
			System.out.println("Contact Us link is displayed");

		}

		else {
			Am.drawBorderFail(rpl.ContactUs, driver);
			log.info("Contact us link not Displayed");
			Am.captureScreen(driver, "ContactusFail");
			System.out.println("Contact Link not opened");
			Assert.assertTrue(false);
		}

		rpl.clickContactUs();
		String ContactHeader = rpl.getContactUsHeaderText();
		softAssert.assertEquals(Constants.ContactUsHeader, ContactHeader);
		Assert.assertTrue(true);

	}

}
