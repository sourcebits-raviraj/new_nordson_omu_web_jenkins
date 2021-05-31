package com.nordson.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nordson.utilities.ActionMethods;

public class LandingPage {

	WebDriver ldriver;
    ActionMethods Am=new ActionMethods();
	// Constructor of the LoginPage to initiate driver
	public LandingPage(WebDriver rdriver) {
		// TODO Auto-generated constructor stub

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//div[normalize-space()='DASHBOARD']")
	@CacheLookup
	WebElement DashBoard;
	
	@FindBy(xpath = "//*[contains(@panelclass,'lang-select')]//div[@class='mat-select-arrow']")
	WebElement Languagearrow;
	
	@FindBy(xpath = "//*[contains(@class,'lang-select')]//mat-option/span")
	List<WebElement> Languageselection;
	

	// Page Action Methods for all the WebElements declared
	public void dashBoardLink() {
		DashBoard.click();
	}
	
	public void clickLanguage() throws InterruptedException {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@panelclass,'lang-select')]//div[@class='mat-select-arrow']"));
		Languagearrow.click();
	}
	
	public void LanaguageDropdowns(String lngindx)
	{
		Languageselection.get(Integer.parseInt(lngindx)).click();
	}

}
