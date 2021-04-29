package com.nordson.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.utilities.ActionMethods;

public class User_Dashboard_Details_Landing_Page {

	WebDriver ldriver;
	WebDriverWait wait;
	ActionMethods Am;

	public User_Dashboard_Details_Landing_Page(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//div[1]/div[1]/span/img")
	public WebElement logo;

	@FindBy(xpath = "//p[@class='sub-heading']/preceding::div[1]")
	public WebElement welcome;

	@FindBy(xpath = "//div[1]/div[1]/span/img")
	public WebElement dashbaord;

	@FindAll(@FindBy(xpath = "//div[@class='card-panel cards']"))
	public List<WebElement> cardsEvents;

	@FindBy(xpath = "//div[normalize-space()='Recently Viewed']")
	public WebElement RecentlyViewed;

	@FindBy(xpath = "//div[contains(text(),'Recently Created or Imported Setting Files')]")
	public WebElement EventLogFiles;

	@FindBy(xpath = "//div[normalize-space()='DASHBOARD']")
	public WebElement DashBoard;

	@FindBy(xpath = "//div[normalize-space()='Model Registration']")
	public WebElement ModelRegistration;

	@FindBy(xpath = "//div[normalize-space()='Sub User Account']")
	public WebElement SubUserAccount;

	// Page Action Methods for all the WebElements declared
	public boolean logoDisplayed() throws InterruptedException {

		return logo.isDisplayed();

	}

	public boolean welcomeDisplayed() throws InterruptedException {

		return welcome.isDisplayed();

	}

	public boolean dashboardDisplayed() throws InterruptedException {

		return dashbaord.isDisplayed();

	}

	public int setting_Event_Cards_Displayed() {
		int cards = cardsEvents.size();
		// List<WebElement> allOptions= cardsEvents
		for (int i = 0; i <= cards - 1; i++) {

			System.out.println(cardsEvents.get(i).getText());

		}
		return cards;
	}

	public String Recently_Viewed_Event_Logs_Text() {

		String RecentlyViewedText = RecentlyViewed.getText();
		return RecentlyViewedText;

	}

	public String Recently_Created_Setting_Files_Text() {

		String EventLogText = EventLogFiles.getText();
		return EventLogText;

	}

	public String Dashboard_Text() {

		String DashboardText = DashBoard.getText();
		return DashboardText;

	}

	public String Model_Registration() {

		String ModelText = ModelRegistration.getText();
		return ModelText;

	}

	public String SubUserAccount() {

		String SubuserText = SubUserAccount.getText();
		return SubuserText;

	}

}
