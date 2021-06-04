package com.nordson.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.RegistrationPage;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.ReadConfig;

public class TC_Registration_DDT_002 extends BaseClass {

	RegistrationPage rp;

	ActionMethods Am = new ActionMethods();
	ReadConfig readconfig = new ReadConfig();
	private SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "RegistrationTestData", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void RegistrationDDT(String fname, String companyname, String address, String plant, String phoneno,
			String serial, String uniqueid, String desc, String pass, String cpass)
			throws Exception {

		log.info("URL is launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Wait for the page load time out");

		// Driver Object Instantiation
		rp = new RegistrationPage(driver);

		rp.clickSingUp();
		log.info("Sign Up Page is opened");

		rp.setFullName(fname);
		log.info("first name provided");

		rp.setCompanyName(companyname);
		log.info("Company name provided");

		rp.setCompanyType();
		log.info("Company type provided");

		rp.setAddress(address);
		log.info("Address provided");

		rp.selectCountry();
		log.info("Country provided");

		rp.setPlant(plant);
		log.info("plant provided");

		rp.setPhoneNumber(phoneno);
		log.info("plant provided");

		rp.clickContinue();
		log.info("Clicked on Continue button");
		Thread.sleep(1000);

		rp.addSerialNo(serial);
		log.info("serial provided");

		rp.addUniqueNo(uniqueid);
		log.info("UiqnueID provided");

		rp.addDescription(desc);
		log.info("Description provided");

		rp.clickoNContinue();
		log.info("Clicked on Continue button SECOND");
		Thread.sleep(1000);

		rp.setEmailId();
		log.info("emailID provided");

		rp.setPassword(pass);
		log.info(" password provided");

		rp.setConfirmPassword(cpass);
		log.info(" confirm password provided");

		rp.checkboxAgreeTerms();

		log.info("Checked the Terms and Conditions");
		Thread.sleep(3000);

		if (rp.acceptAndSignUPEnabled() == true) {
			rp.acceptAndSignUP();
			log.info("Accept and Sign Up Button is enabled");

		}

		else {
			log.info("Accept and Sign Up Button is disbaled");

		}
		String text = rp.getTextSentYouText();
		softAssert.assertEquals(Constants.SucssText, text);

		if (readconfig.getSignUpURL().equals(driver.getCurrentUrl())) {
			System.out.println(driver.getCurrentUrl());
			log.info("Registration Successful");
			// Am = new ActionMethods();
			Am.captureScreen(driver, "RegistrationDDTPass");
			Assert.assertTrue(true);
		}

		else {

			// Am = new ActionMethods();
			Am.captureScreen(driver, "RegistrationDDTFail");
			System.out.println("Registration Failed");
			Assert.assertTrue(false);
			log.info("Registration failed");

		}
	}

}
