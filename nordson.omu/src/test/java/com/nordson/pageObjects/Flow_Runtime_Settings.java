package com.nordson.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.utilities.ActionMethods;

public class Flow_Runtime_Settings {
	
	WebDriver ldriver;
	WebDriverWait wait;
	ActionMethods Am = new ActionMethods();

	// Constructor of the LoginPage to initiate driver
	public Flow_Runtime_Settings(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	@FindBy(xpath = "//*[contains(text(),'DASHBOARD')]")
	WebElement Dashboard;
	
	@FindBy(id = "bt")
	WebElement SetUpToolButton;

	@FindBy(xpath = "//*[contains(text(),'CREATE NEW')]")
	WebElement CreateNewButton;

	@FindBy(xpath = "//*[@class='btn-set-up submit-btn-color']")
	WebElement SubmitButton;

	@FindBy(xpath = "//*[contains(text(),'System Settings')]/ancestor::span")
	WebElement SystemSettings;
	
	@FindBy(xpath = "//div[contains(@class,'toast-message ng-star-inserted')]")
	WebElement Toastmsg;
	
	@FindBy(xpath = "//*[@routerlink='flow-runtime']/div")
	WebElement Flowruntimesettingsbtn;
	
	@FindBy(css ="*[formcontrolname='ATSTargetAddon']")
	WebElement TargetAddOn;
	
	@FindBy(css = "*[class='apply btn']")
	WebElement SAVE;
	
	public void clickDashboard() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'DASHBOARD')]"));
		Am.waitForAnElementToBeClickable(Dashboard);
		Dashboard.click();
	}
	public void clickSetUpToolBtn() {
		Am.waitForAnElementPresence(By.id("bt"));
		//Am.waitForAnElementToBeClickable(SetUpToolButton);
		
		SetUpToolButton.click();
	}
	public void clickCreateNewBtn() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'CREATE NEW')]"));
		Am.waitForAnElementToBeClickable(CreateNewButton);
		CreateNewButton.click();
	}
	public void clickSubmitBtn() throws InterruptedException {
		Am.waitForAnElementPresence(By.xpath("//*[@class='btn-set-up submit-btn-color']"));
		Am.waitForAnElementToBeClickable(SubmitButton);
		SubmitButton.click();
	}
	
	public void clickSystemSettingsBtn() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'System Settings')]/ancestor::span"));
		Am.waitForAnElementToBeClickable(SystemSettings);
		JavascriptExecutor executor = (JavascriptExecutor) ldriver;
		executor.executeScript("arguments[0].click();", SystemSettings);
		// SystemSettings.click();
	}
	
	public void clickFlowRuntimesettingsbtn() throws InterruptedException {
		Am.waitForAnElementPresence(By.xpath("//*[@routerlink='flow-runtime']/div"));
		Thread.sleep(800);
		Am.waitForAnElementToBeClickable(Flowruntimesettingsbtn);
		Flowruntimesettingsbtn.click();
	}
	
   public void clearTargetAddon() {
	   Am.waitForAnElementPresence(By.cssSelector("*[formcontrolname='ATSTargetAddon']"));
		TargetAddOn.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setTargetAddon(String targetAddOn) {
	   Am.waitForAnElementPresence(By.cssSelector("*[formcontrolname='ATSTargetAddon']"));
		TargetAddOn.sendKeys(targetAddOn);
	}
   public String getTargetAddOn() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[formcontrolname='ATSTargetAddon']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[formcontrolname='ATSTargetAddon']"));
		return TargetAddOn.getAttribute("value");
	}
   
	public String getToastmsg() {
		Am.waitForAnElementPresence(Toastmsg);
		String toastmsg = "";
		if (Toastmsg.isDisplayed()) {
			toastmsg = Toastmsg.getText();
			Am.waitForAnElementIsInVisible(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
		} else
			System.out.println("toast msg not displayed");
		return toastmsg;
	}
	public Boolean getToastmsgststus() {
		Am.waitForAnElementPresence(Toastmsg);
		Boolean toastmsg = false;
		if (Toastmsg.isDisplayed()) {
			toastmsg = true;
			Am.waitForAnElementIsInVisible(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
		} else
			toastmsg = false;
		return toastmsg;
	}	
	public void clickSavebtn() {
		Am.waitForAnElementPresence(By.cssSelector("*[class='apply btn']"));
		Am.waitForAnElementToBeClickable(SAVE);
		SAVE.click();
	}
	public void createNewNORfile() throws InterruptedException {
		clickSetUpToolBtn();
		clickCreateNewBtn();
		clickSubmitBtn();
		clickSystemSettingsBtn();
	}
	public Boolean getSavebtnstatus() {
		Am.waitForAnElementPresence(SAVE);
		Boolean sttus = SAVE.isEnabled();
		return sttus;
	}

}

