package com.nordson.testCases;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.pageObjects.LinksPages;
import com.nordson.pageObjects.LoginPage;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.XLUtils;

public class TC_VerifyLinkTests_003 extends BaseClass {

	LoginPage lp;
	ActionMethods Am;
	LinksPages lpg;
	private SoftAssert softAssert = new SoftAssert();

	@Test(dataProvider = "LoginLinksTestData")
	public void LinksLoginDDT(String user, String pwd) throws InterruptedException, IOException {

		log.info("URL is launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		log.info("Wait for the page load time out");
		lp = new LoginPage(driver);

		lp.setUserName(user);

		log.info("user name provided");

		lp.setPassword(pwd);
		log.info("password provided");

		lp.clickLoginBtn();
		Thread.sleep(6000);

		// verify the login is successful
		log.info("Verify page source contains welcome");
		if (driver.getPageSource().contains("welcome ravi")) {
			System.out.println("Login Successful");
			log.info("Login Successful");
			Am = new ActionMethods();
			Am.captureScreen(driver, "LinksLoginDDTPass");
			Assert.assertTrue(true);
			lp.clickLogoutBtn();

		}

		else {

			Am = new ActionMethods();
			Am.captureScreen(driver, "LinksLoginDDTFail");
			System.out.println("Login Failed");
			softAssert.assertTrue(false);

		}

		// Store all the tagname (a) in links List
		List<WebElement> links = driver.findElements(By.tagName("a"));

		// To print the total number of links
		System.out.println("Total links are " + links.size());

		// used for loop to
		for (int i = 0; i < links.size(); i++) {

			WebElement element = links.get(i);

			// By using "href" attribute, we could get the url of the requried link
			String url = element.getAttribute("href");

			// calling verifyLink() method here. Passing the parameter as url which we
			// collected in the above link
			// See the detailed functionality of the verifyLink(url) method
			lpg = new LinksPages();
			log.info("Verify Link Method called from LinkPages class");
			LinksPages.verifyLink(url);

		}
	}

	@DataProvider(name = "LoginLinksTestData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Login");
		int colcount = XLUtils.getCellCount(path, "Login", 1);

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcount);
		String logindata[][] = new String[rownum][colcount];

		// For Loop to get the array values in Logindata
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Login", i, j);// 1 0
			}

		}
		return logindata;
	}

}