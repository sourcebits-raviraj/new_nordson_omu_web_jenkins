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
	// ArrayList<String> tabs;
	// ArrayList<String> tab1;
	private SoftAssert softAssert = new SoftAssert();

	// Driver Object Instantiation
	User_Registration_All_Links rpl;
	ReadConfig readconfig = new ReadConfig();

	@Test(dataProvider = "RegistrationContine", dataProviderClass = com.nordson.utilities.XLUtils.class, priority = 1, enabled = true)
	public void Registration_Continue_Link(String fname, String companyname, String address, String plant,
			String phoneno) throws InterruptedException, IOException {

		log.info("URL is launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		log.info("Wait for the page load time out");

		rpl = new User_Registration_All_Links(driver);

		// driver.get("http://omupreprod.azurewebsites.net/register");

		rpl.clickSingUp();
		log.info("Sign Up Page is opened");

		rpl.setFullName(fname);
		log.info("first name provided");

		rpl.setCompanyName(companyname);
		log.info("Company name provided");

		rpl.setCompanyType();
		log.info("Company type provided");

		rpl.setAddress(address);
		log.info("Address provided");

		rpl.selectCountry();
		log.info("Country provided");

		rpl.setPlant(plant);
		log.info("plant provided");

		rpl.setPhoneNumber(phoneno);
		log.info("Phone number provided");

		if (rpl.clickContinue() == true) {
			log.info("Continue button is enabled");
			System.out.println("Clicked on Continue button is  enabled");
			Assert.assertTrue(true);
		} else {
			log.info("Clicked on Continue button is not enabled");
			System.out.println("Clicked on Continue button is not enabled");
			softAssert.assertTrue(false);
		}
	}

	@Test(priority = 2, enabled = true)
	public void Registration_Nordson_CopyRightText() throws InterruptedException, IOException {

		if (rpl.getCopyRightDisplayed() == true) {

			log.info("Copy Right Displayed Nordson Corporation");
			softAssert.assertTrue(true);
			String copytext = rpl.getCopyRightText();
			softAssert.assertEquals(Constants.copyRight, copytext);
			Am = new ActionMethods();
			Am.captureScreen(driver, "CopyRightPass");

		}

		else {
			log.info("Copy Right not Displayed");
			softAssert.assertTrue(true);
			Am = new ActionMethods();
			Am.captureScreen(driver, "CopyRightFail");
			System.out.println("Copy Right Not Present");
			softAssert.assertTrue(false);

		}

	}

	@Test(priority = 3, enabled = true)
	public void Registration_Nordson_Privacy_Policy() throws InterruptedException, IOException {

		if (rpl.PrivacyPolicyDisplayed() == true) {

			log.info("Privacy Link Displayed");
			softAssert.assertTrue(true);
			String PrivacyPolicyText = rpl.getPrivacyPolicyText();
			softAssert.assertEquals(Constants.privacyPolicy, PrivacyPolicyText);
			System.out.println("Privacy Policy link is displayed");

			rpl.clickPrivacy();

			Am.switchToNexttab();
			// tabs = new ArrayList<String>(driver.getWindowHandles());
			// System.out.println(tabs.size());
			// driver.switchTo().window(tabs.get(1));

			// action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
			// Thread.sleep(3000);
		}

		else {
			softAssert.assertTrue(false);
			log.info("Privacy Link not Displayed");
			System.out.println("Privacy Policy link is not displayed");

		}

		if (readconfig.getPrivacyPolicyURL().equals(driver.getCurrentUrl())) {
			// Am = new ActionMethods();
			softAssert.assertTrue(true);
			Am.captureScreen(driver, "PrivacyPolicyPass");
			System.out.println(driver.getCurrentUrl());
			System.out.println("Privacy Policy link is opened and naviagated");
			log.info("Privacy Policy link");
			Am.closeCurrentTab_SwitchtoPrevioustab();
			// driver.close();
			// driver.switchTo().window(tabs.get(0));
			// action = new Actions(driver);
			// action.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();

		}

		else {
			log.info("Privacy Policy link not displayed ");
			// Am
			Am.captureScreen(driver, "PrivacyPolicyFail");
			System.out.println("Privacy Policy not naviagted");
			softAssert.assertTrue(false);

		}

	}

	@Test(priority = 4, enabled = true)
	public void Registration_Nordosn_Terms_of_Services() throws InterruptedException, IOException {

		if (rpl.TermsOfServiceDisplayed() == true) {

			log.info("Terms Of Service text is Displayed");
			softAssert.assertTrue(true);
			String TermsServicesText = rpl.getTermsOFServiceText();
			softAssert.assertEquals(Constants.termsOfServices, TermsServicesText);
			System.out.println("Terms of Service text is displayed");

		}

		else {

			softAssert.assertTrue(false);
			log.info("Terms of Service text is displayed");
			System.out.println("Terms of Service text is displayed");
		}

	}

	@Test(priority = 5, enabled = true)
	public void Registration_Nordson_Cookies() throws InterruptedException, IOException {

		if (rpl.cookiesDisplayed() == true) {

			log.info("Cookies Link Displayed");
			softAssert.assertTrue(true);
			String CookiesText = rpl.getCookiesText();
			softAssert.assertEquals(Constants.CookiesText, CookiesText);
			System.out.println("Cookies link is displayed");
			rpl.clickCookies();
			Am.switchToNexttab();
			// tab1 = new ArrayList<String>(driver.getWindowHandles());
			// driver.switchTo().window(tab1.get(1));

		}

		else {

			log.info("Cookies Link not Displayed");
			System.out.println("Cookies Link is not displayed");
			softAssert.assertTrue(false);
		}

		if (readconfig.getCookiesURL().equals(driver.getCurrentUrl())) {

			// Am = new ActionMethods();
			softAssert.assertTrue(true);
			Am.captureScreen(driver, "CookiesPass");
			System.out.println(driver.getCurrentUrl());
			System.out.println("Cookies link is opened and navigated");
			log.info("Cookies link is opened and navigated");
			Am.closeCurrentTab_SwitchtoPrevioustab();
			// driver.close();
			// driver.switchTo().window(tabs.get(0));

		}

		else {

			log.info("Cookies link not displayed ");
			// Am = new ActionMethods();
			Am.captureScreen(driver, "CookiesFail");
			System.out.println("Cookies Link not opened");
			softAssert.assertTrue(false);

		}

	}

	@Test(priority = 6, enabled = true)
	public void Registration_Nordson_Contact_Us() throws InterruptedException, IOException {

		if (rpl.contactUsDisplayed() == true) {

			log.info("Contact Us Link Displayed");
			softAssert.assertTrue(true);
			String ContactUsText = rpl.getContactUsText();
			softAssert.assertEquals(Constants.ContactUs, ContactUsText);
			System.out.println("Contact Us link is displayed");

		}

		else {

			log.info("Contact us link not Displayed");
			Am.captureScreen(driver, "CookiesFail");
			System.out.println("Cookies Link not opened");
			softAssert.assertTrue(false);
		}

		rpl.clickContactUs();
		String ContactHeader = rpl.getContactUsHeaderText();
		softAssert.assertEquals(Constants.ContactUsHeader, ContactHeader);
		softAssert.assertTrue(true);

	}

}
