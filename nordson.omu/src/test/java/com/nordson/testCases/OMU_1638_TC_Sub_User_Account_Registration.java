
package com.nordson.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Flow_Runtime_Settings;
import com.nordson.pageObjects.LoginPage;
import com.nordson.pageObjects.OMU_1638_Sub_User_Registration_Account;
import com.nordson.pageObjects.Sub_User_Dashboard_Add_Account;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OMU_1638_TC_Sub_User_Account_Registration extends BaseClass {

	Flow_Runtime_Settings frs;
	Sub_User_Dashboard_Add_Account sud;
	OMU_1638_Sub_User_Registration_Account sura;
	ActionMethods Am = new ActionMethods();
	Actions action;
	LoginPage lp;
	public String YopURL = readconfig.getYopMailURL();
	private SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "LoginLinksTestData", priority = 1, dataProviderClass = com.nordson.utilities.XLUtils.class, enabled = true)
	public void Sub_User_Account_Registration(String user, String pwd) throws Exception {

		log.info("URL is launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		log.info("Wait for the page load time out");

		lp = new LoginPage(driver);
		sud = new Sub_User_Dashboard_Add_Account(driver);
		frs = new Flow_Runtime_Settings(driver);

		Am.sleepTime(1000);
		lp.setUserName(user);
		log.info("user name provided");
		lp.setPassword(pwd);
		log.info("password provided");
		lp.clickLoginBtn();
		Am.sleepTime(4000);

		if (sud.SubUserAccountText().equalsIgnoreCase("Sub User Account")) {

			Am.drawBorder(sud.SubUserAccount, driver);
			Am.sleepTime(1000);
			log.info("Sub User Account Text displayed");
			softAssert.assertTrue(true);

		} else {
			Am.drawBorderFail(sud.SubUserAccount, driver);
			log.info("Sub User Account not Text displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 2, enabled = true)
	public void Sub_User_Account__Click() throws Exception {

		if (sud.SubUserAccountDisplayed() == true) {
			sud.SubUserAccountClick();
			Am.drawBorder(sud.SubUserAccount, driver);
			Am.sleepTime(1000);
			log.info("Sub User Account Text displayed");
			softAssert.assertTrue(true);

		} else

		{
			log.info("Sub User Account not Text displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3, enabled = true)
	public void Manage_Sub_User_Account() throws Exception {

		if (sud.ManageSubUserAccountText().equalsIgnoreCase("Manage Sub-User Account")) {

			Am.sleepTime(1000);
			Am.drawBorder(sud.ManageSubUSer, driver);
			log.info("Manage Sub User Account Text displayed");
			softAssert.assertTrue(true);

		} else {
			log.info("Manage Sub User Account not Text displayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 4, enabled = true)
	public void Add_Sub_User_Button() throws Exception {

		Am.sleepTime(2000);
		if (sud.AddSubUserAccountDisplayed() == true) {

			Am.drawBorder(sud.AddSubUSerAccount, driver);
			Am.sleepTime(1000);
			log.info("Sub User Account Text displayed");
			softAssert.assertTrue(true);

		} else

		{
			log.info("Sub User Account not Text displayed");
			Assert.assertTrue(false);
		}

		if (sud.AddSubUserAccountEnabled() == true) {

			Am.sleepTime(1000);
			log.info("Sub User Account button enabled is true");
			softAssert.assertTrue(true);

		} else

		{
			log.info("Sub User Account button enabled is false");
			Assert.assertTrue(false);
		}

	}

	@Test(dataProvider = "AddSubUserRegistration", dataProviderClass = com.nordson.utilities.XLUtils.class, priority = 6, enabled = true)
	public void Add_Sub_User_Read_Only_Forms(String fname, String emailID) throws Exception {

		Am.sleepTime(2000);

		if (sud.AddSubUserAccountEnabled() == true) {

			sud.ClickAddSubUserAccount();
			Am.sleepTime(1000);
			log.info("Clicked on Add SUb user account button");

			softAssert.assertEquals(Constants.AddSubUserHeader, sud.AddSubUSerPopUPtext());
			Am.drawBorder(sud.AddSubUerpop, driver);
			log.info("Verify the header of pop up, sub user header");

			sud.SetFullName(fname);
			Assert.assertEquals(Constants.SubUserFullName, sud.FullNametext());
			Am.drawBorder(sud.FullNameInput, driver);
			log.info("Verify the Full Name in pop up");

			// String emailid = ActionMethods.emailID();
			sud.SetEmail(emailID);
			Assert.assertEquals(Constants.SubUserEmail, sud.Emailtext());
			Am.drawBorder(sud.EmailInput, driver);
			log.info("Set the email id in pop up");

			Assert.assertEquals(Constants.CancelButton, sud.Canceltext());
			Am.drawBorder(sud.CancelButton, driver);
			log.info("Verified the Cancel button");

			Assert.assertEquals(Constants.AddButton, sud.AddButtontext());
			Am.drawBorder(sud.AddButtonText, driver);
			Am.sleepTime(2000);

			if (sud.AddButtonPopEnabled() == true) {

				log.info("Add Button in the pop up enabled");
				sud.ClickAddButton();
				log.info("CLicked on add button");
				System.out.println("CLicked on add button");

			}

			else {

				log.info("Add button not enabled , wrong with field name or email id");
			}
			Am.sleepTime(500);
			softAssert.assertEquals(frs.getToastmsg(), Constants.SubUsersmsg, "Sub User Added successfully");

			log.info("Verified the toast message, Sub User added succesfully");
			System.out.println("Verified the toast message, Sub User added succesfully");

			if (sud.AddSubUserAccountDisplayed() == true) {

				System.out.println("Still user can add ");
				log.info("Still user can add users ");
				sud.NoOfUsersAdded();

			} else {

				System.out.println("User Can add further users ");
				log.info("Users Cannot add further users");
				sud.NoOfUsersAdded();
			}

			Am.sleepTime(1000);
			log.info("Sub User Account Text displayed");
			softAssert.assertTrue(true);

		}
		driver.close();

	}

	@Test(dataProvider = "AddSubUserRegistration", priority = 7, dataProviderClass = com.nordson.utilities.XLUtils.class, enabled = true)
	public void Confirm_Mail(String fname, String emailID) throws Exception {

		// Webdriver Manager Set up Path of chromedriver
		WebDriverManager.chromedriver().setup();

		// Instantiate a Chromedriver class.
		driver = new ChromeDriver();
		log.info("New Chrome driver Instantiated");

		// yopmail URL is luanched on chrome browser
		try

		{

			driver.get(YopURL);

		} catch (Exception e) {

			e.printStackTrace();
			log.info("Error in launching yopmail URL on chrome browser");
		}

		log.info("URL is launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		log.info("Wait for the page load time out");

		Am.sleepTime(20000);
		sura = new OMU_1638_Sub_User_Registration_Account(driver);

		sura.SetYopMailId(emailID);
		sura.ClickGoYopMailId();
		Am.sleepTime(7000);
		driver.switchTo().frame("ifmail");
		Am.sleepTime(7000);
		driver.findElement(By.xpath("//a[contains(text(),'Confirm the email')]")).click();
		Am.sleepTime(7000);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		Am.switchToNexttab();
		/*
		 * if (sud.SubUserAccountText().equalsIgnoreCase("Sub User Account")) {
		 * 
		 * Am.drawBorder(sud.SubUserAccount, driver); Am.sleepTime(1000);
		 * log.info("Sub User Account Text displayed"); softAssert.assertTrue(true);
		 * 
		 * } else { log.info("Sub User Account not Text displayed");
		 * Assert.assertTrue(false); }
		 */
	}

	@Test(dataProvider = "SubRegistrationContine", dataProviderClass = com.nordson.utilities.XLUtils.class, priority = 8, enabled = true)
	public void Registration_Continue_Link(String plant, String phoneno) throws Exception {
		Am.sleepTime(7000);
		sura = new OMU_1638_Sub_User_Registration_Account(driver);

		Am.drawBorder(sura.FullName, driver);
		log.info("first name displayed");

		Am.drawBorder(sura.CompanyName, driver);
		log.info("Company name displayed");

		Am.drawBorder(sura.CompanyType, driver);
		log.info("Company type provided");

		Am.drawBorder(sura.Address, driver);
		log.info("Address provided");

		Am.drawBorder(sura.Country, driver);
		sura.selectCountry();
		log.info("Country provided");

		Am.drawBorder(sura.Plant, driver);
		sura.setPlant(plant);
		log.info("plant provided");

		Am.drawBorder(sura.PhoneNumber, driver);
		sura.setPhoneNumber(phoneno);
		log.info("Phone number provided");

		if (sura.clickContinueEnabled() == true) {
			Am.drawBorder(sura.Continue, driver);
			log.info("Continue button is enabled");
			System.out.println("Clicked on Continue button is  enabled");
			softAssert.assertTrue(true);
			sura.clickContinue();
			Am.sleepTime(5000);
			log.info("Clicked on Continue button");

		} else {
			Am.drawBorderFail(sura.Continue, driver);
			log.info("Clicked on Continue button is not enabled");
			System.out.println("Clicked on Continue button is not enabled");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 9, enabled = true)
	public void Registration_Second_Continue_Link() throws Exception {

		// sura = new OMU_1638_Sub_User_Registration_Account(driver);
		Am.sleepTime(5000);
		Am.drawBorder(sura.SerialNo, driver);
		log.info("Serial Number displayed");

		Am.drawBorder(sura.UnqiueID, driver);
		log.info("unique ID displayed");

		Am.drawBorder(sura.Desc, driver);
		log.info("Description provided");

		Am.drawBorder(sura.configcode, driver);
		log.info("Config Code provided");

		if (sura.clickContinueEnabled() == true) {
			Am.drawBorder(sura.Continue, driver);
			log.info("Second Continue button is enabled");
			System.out.println("Clicked on second Continue button is  enabled");
			softAssert.assertTrue(true);
			sura.clickContinue();
			Am.sleepTime(5000);

			log.info("Clicked on second Continue button");

		} else {
			Am.drawBorderFail(sura.Continue, driver);
			log.info("Continue button is not enabled");
			System.out.println("secondContinue button is not enabled");
			Assert.assertTrue(false);
		}
	}

	@Test(dataProvider = "AddSubUserRegistration", dataProviderClass = com.nordson.utilities.XLUtils.class, priority = 10, enabled = true)
	public void Third_Screen_Email_and_Password_Agree_Terms_SignUp(String fname, String emailID) throws Exception {

		// String emailID = sura.getEmailAddress();

		// Problem is here not setting up mail id

		sura.setConfirmEmailId(emailID);
		log.info("emailID provided");

		sura.setPassword(Constants.pass);
		log.info("password provided");

		sura.setConfirmPassword(Constants.pass);
		log.info("confirm password provided");

		sura.checkboxAgreeTerms();
		log.info("Agreed Terms and conditions");

		if (sura.acceptAndSignUPEnabled() == true) {
			sura.clickAcceptAndSignUP();
			log.info("Accept and Sign Up Button is enabled");

		}

		else {

			log.info("Accept and Sign Up Button is disbaled, Email Id or Password might be wrong");

		}

		Am.sleepTime(5000);
		sura.setEmailId(emailID);
		log.info("emailID provided");

		sura.setPassword(Constants.pass);
		log.info("password provided");

		sura.clickLoginBtn();
		log.info("clicked on Login");
		Am.sleepTime(2000);
		if (sura.welcomeDisplayed() == true) {

			Am.drawBorder(sura.welcome, driver);
			Am.sleepTime(2000);
			Assert.assertTrue(sura.welcome.getText().contains("Welcome TestSub"));
			log.info("Welcome text is displayed");
			softAssert.assertTrue(true);

		} else

		{
			log.info("Welcome text is not displayed");
			Am.drawBorderFail(sura.welcome, driver);
			Assert.assertTrue(false);

		}

	}

}
