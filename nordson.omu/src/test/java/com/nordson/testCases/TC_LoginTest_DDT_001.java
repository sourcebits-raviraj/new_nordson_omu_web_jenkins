package com.nordson.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nordson.pageObjects.LoginPage;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XLUtils;

public class TC_LoginTest_DDT_001 extends BaseClass {

	LoginPage lp;
	ActionMethods Am;
	// private SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "LoginTestData")
	public void loginDDT(String user, String pwd) throws InterruptedException, IOException {

		log.info("URL is launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		log.info("Wait for the page load time out");
		lp = new LoginPage(driver);

		Thread.sleep(2000);
		lp.setUserName(user);
		log.info("user name provided");
		lp.setPassword(pwd);
		log.info("password provided");
		lp.clickLoginBtn();
		Thread.sleep(4000);

		// verify the login is successful

		if (driver.getPageSource().contains(" Sub User Account ")) {

			System.out.println("Login Successful, page source conatins Sub user account");
			Am = new ActionMethods();
			Am.captureScreen(driver, "loginDDT");
			Assert.assertTrue(true);
			// lp.clickLogoutBtn();

			if (driver.getPageSource().contains("Welcome")) {

				if (driver.getPageSource().contains(" Sub User Account ")) {

					System.out.println("Login Successful");
					Am = new ActionMethods();
					Am.captureScreen(driver, "loginDDT");
					Assert.assertTrue(true);

					// lp.clickLogoutBtn();

					// lp.clickLogoutBtn();

				}

				else {

					Am = new ActionMethods();
					Am.captureScreen(driver, "loginDDT");
					System.out.println("Login Failed");
					Assert.assertTrue(false);

				}

			}
		}
	}

	@DataProvider(name = "LoginTestData")
	public static String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Login");
		int colcount = XLUtils.getCellCount(path, "Login", 1);

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcount);
		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Login", i, j);// 1 0
			}

		}
		return logindata;
	}

}
