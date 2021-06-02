package com.nordson.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.Flow_Runtime_Settings;
import com.nordson.pageObjects.LoginPage;
import com.nordson.pageObjects.Sub_User_Dashboard_Add_Account;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;

public class TC_OMU_2199_Sub_User_Dashbaord_Add_Account extends BaseClass {

	Flow_Runtime_Settings frs;
	Sub_User_Dashboard_Add_Account sud;
	ActionMethods Am = new ActionMethods();
	Actions action;
	LoginPage lp;
	private SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "SubUserData", priority = 1, dataProviderClass = com.nordson.utilities.XLUtils.class, enabled = true)
	public void Sub_User_Account(String user, String pwd) throws Exception {

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

	@Test(dataProvider = "AddSubUser", dataProviderClass = com.nordson.utilities.XLUtils.class, priority = 6, enabled = true)
	public void Add_Sub_User_Read_Only_Forms(String fname) throws Exception {

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

			String emailid = ActionMethods.emailID();
			sud.SetEmail(emailid);
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

			softAssert.assertEquals(frs.getToastmsg(), Constants.SubUsersmsg, "Sub USer Added successfully");

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

	}

	@Test(dataProvider = "AddSubUser", dataProviderClass = com.nordson.utilities.XLUtils.class, priority = 5, enabled = true)
	public void Add_Sub_Read_Write_User_Successful(String fname) throws Exception {

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

			String emailid = ActionMethods.emailID();
			sud.SetEmail(emailid);
			Assert.assertEquals(Constants.SubUserEmail, sud.Emailtext());
			Am.drawBorder(sud.EmailInput, driver);
			log.info("Set the email id in pop up");

			Assert.assertEquals(Constants.CancelButton, sud.Canceltext());
			Am.drawBorder(sud.CancelButton, driver);
			log.info("Verified the Cancel button");

			Assert.assertEquals(Constants.AddButton, sud.AddButtontext());
			Am.drawBorder(sud.AddButtonText, driver);
			Am.sleepTime(2000);

			sud.ClickReadWriteRadioButton();
			log.info("Selected Read Write radio button");
			Am.drawBorder(sud.AddButtonText, driver);
			Am.sleepTime(1000);

			if (sud.AddButtonPopEnabled() == true) {

				log.info("Add Button in the pop up enabled");
				sud.ClickAddButton();
				Am.sleepTime(500);
				log.info("CLicked on add button");
				System.out.println("CLicked on add button");

			}

			else {

				log.info("Add button not enabled , wrong with field name or email id");
			}

			softAssert.assertEquals(frs.getToastmsg(), Constants.SubUsersmsg, "Sub USer Added successfully");

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

	}
}
