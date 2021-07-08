package com.nordson.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.utilities.ActionMethods;

public class OMU_1638_Sub_User_Registration_Account {

	WebDriver ldriver;
	WebDriverWait wait;
	ActionMethods Am = new ActionMethods();
	ActionMethods customwait = new ActionMethods();

	// Constructor of the LoginPage to initiate driver
	public OMU_1638_Sub_User_Registration_Account(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	// WebElements FindBy and Cache look up

	@FindBy(xpath = "//input[@id='login']")
	public WebElement YopMailBox;

	@FindBy(xpath = "//input[@id='login']/following::button[1]//i")
	public WebElement YopMailBoxClickGo;

	@FindBy(xpath = "//input[@id='fname']")
	public WebElement FullName;

	@FindBy(xpath = "//input[@id='cname']")
	public WebElement CompanyName;

	@FindBy(xpath = "//input[@id='cname']/following::span[1]")
	public WebElement CompanyType;

	@FindBy(xpath = "//span[contains(@class,'mat-option-text')][normalize-space()='End User']")
	public WebElement CompanyTypeValue;

	@FindBy(id = "address")
	public WebElement Address;

	@FindBy(xpath = "//mat-form-field[@id='register-mat-sel-1']//div[@class='mat-form-field-infix']")
	public WebElement Country;

	@FindBy(xpath = "//span[normalize-space()='India']")
	public WebElement CountryValue;

	@FindBy(id = "plant")
	public WebElement Plant;

	@FindBy(xpath = "//input[@id='padding-left-66']")
	public WebElement PhoneNumber;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	public WebElement Continue;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement Continue2;

	@FindBy(xpath = "//input[@id='sno']")
	public WebElement SerialNo;

	@FindBy(xpath = "//input[@name='uid']")
	public WebElement UnqiueID;

	@FindBy(xpath = "//textarea[@name='description']")
	public WebElement Desc;

	@FindBy(xpath = "//input[@id='ccode']")
	public WebElement configcode;

	@FindBy(css = "#email[name='email']")
	public WebElement EmailAddress;

	@FindBy(css = "#email[name='cemail']")
	public WebElement ConfirmEmailAddress;

	@FindBy(xpath = "//input[@id='pwd']")
	public WebElement Password;

	@FindBy(xpath = "//input[@id='ccpwd']")
	@CacheLookup
	public WebElement ConfirmPassword;

	@FindBy(xpath = "//div[@class='mat-checkbox-inner-container']")
	public WebElement AgreeCheckBox;

	@FindBy(xpath = "//button[normalize-space()='Accept and Sign Up']")
	@CacheLookup
	public WebElement AcceptAndSignup;

	@FindBy(xpath = "//div[contains(@class,'toast-message ng-star-inserted')]")
	WebElement Toastmsg;

	@FindBy(xpath = "//button[normalize-space()='Sign In']")
	@CacheLookup
	WebElement LoginButton;

	@FindBy(xpath = "//p[@class='sub-heading']/preceding::div[1]")
	public WebElement welcome;

	// Page Action Methods for all the WebElements declared

	public void SetYopMailId(String EmailID) {
		customwait.waitForAnElementPresence(YopMailBox);
		YopMailBox.sendKeys(EmailID);
	}

	public void ClickGoYopMailId() throws Exception {
		Am.sleepTime(1000);
		YopMailBoxClickGo.click();
	}

	public void selectCountry() throws InterruptedException {

		Country.click();
		Thread.sleep(1000);
		CountryValue.click();

	}

	public void setPlant(String plant) {
		Plant.sendKeys(plant);

	}

	public void setPhoneNumber(String phonenumber) {
		PhoneNumber.sendKeys(phonenumber);
	}

	public boolean clickContinueEnabled() throws InterruptedException {
		Thread.sleep(2000);
		return Continue.isEnabled();
	}

	public void clickContinue() throws InterruptedException {
		Thread.sleep(2000);
		Continue.click();
	}

	public String getEmailAddress() {

		return EmailAddress.getText();

	}

	public void setConfirmEmailId(String genEmail) {
		ConfirmEmailAddress.sendKeys(genEmail);
	}

	public void setPassword(String pass) {
		Password.sendKeys(pass);
	}

	public void setConfirmPassword(String confpass) {
		ConfirmPassword.sendKeys(confpass);
	}

	public void checkboxAgreeTerms() {
		AgreeCheckBox.click();

	}

	public boolean acceptAndSignUPEnabled() {

		return AcceptAndSignup.isEnabled();

	}

	public void clickAcceptAndSignUP() throws InterruptedException {

		AcceptAndSignup.click();

	}

	// Get the toast message text

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

	// Verify Toast message is displayed

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

	public void setEmailId(String EmailiD) {

		EmailAddress.sendKeys(EmailiD);

	}

	public void clickLoginBtn() {
		LoginButton.click();

	}

	public boolean welcomeDisplayed() throws InterruptedException {

		return welcome.isDisplayed();

	}
}
