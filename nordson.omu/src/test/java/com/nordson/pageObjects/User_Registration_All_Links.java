package com.nordson.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class User_Registration_All_Links {

	WebDriver ldriver;
	WebDriverWait wait;
	// ActionMethods Am=new ActionMethods;

	// Constructor of the LoginPage to initiate driver
	public User_Registration_All_Links(WebDriver rdriver) {
		// TODO Auto-generated constructor stub

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Sign Up']")
	WebElement SignUp;

	@FindBy(xpath = "//input[@id='fname']")
	WebElement FullName;

	@FindBy(xpath = "//input[@id='cname']")
	WebElement CompanyName;

	@FindBy(xpath = "//input[@id='cname']/following::span[1]")
	WebElement CompanyType;

	@FindBy(xpath = "//span[contains(@class,'mat-option-text')][normalize-space()='End User']")
	WebElement CompanyTypeValue;

	@FindBy(id = "address")
	WebElement Address;

	@FindBy(xpath = "//mat-form-field[@id='register-mat-sel-1']//div[@class='mat-form-field-infix']")
	WebElement Country;

	@FindBy(xpath = "//span[normalize-space()='Afghanistan']")
	WebElement CountryValue;

	@FindBy(id = "plant")
	WebElement Plant;

	@FindBy(xpath = "//input[@id='padding-left-66']")
	WebElement PhoneNumber;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	@CacheLookup
	WebElement Continue;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	@CacheLookup
	WebElement Continue2;

	@FindBy(id = "sno")
	@CacheLookup
	WebElement SerialNo;

	@FindBy(name = "uid")
	@CacheLookup
	WebElement UnqiueID;

	@FindBy(name = "description")
	@CacheLookup
	WebElement Desc;

	@FindBy(name = "email")
	@CacheLookup
	WebElement EmailAddress;

	@FindBy(name = "cemail")
	@CacheLookup
	WebElement ConfirmEmailAddress;

	@FindBy(name = "password")
	@CacheLookup
	WebElement Password;

	@FindBy(id = "ccpwd")
	@CacheLookup
	WebElement ConfirmPassword;

	@FindBy(xpath = "//div[@class='mat-checkbox-inner-container']")
	WebElement AgreeCheckBox;

	@FindBy(xpath = "//button[normalize-space()='Accept and Sign Up']")
	@CacheLookup
	WebElement AcceptAndSignup;

	@FindBy(xpath = "//span[@class='copyright']")
	public WebElement CopyRight;

	@FindBy(xpath = "//a[normalize-space()='Privacy Policy']")
	public WebElement PrivacyPolicy;

	@FindBy(linkText = "Terms of Service")
	public WebElement TermsofService;

	@FindBy(xpath = "//a[normalize-space()='Cookies']")
	public WebElement Cookies;

	@FindBy(xpath = "//div[@class='right footer-text cursor-pointer pad-responsive-signup']")
	public WebElement ContactUs;

	@FindBy(xpath = "//a[normalize-space()='Terms of Service']")
	public WebElement TermsService;

	@FindBy(xpath = "//div[@class='contact-head']")
	public WebElement ContactUsHeader;

	// Page Action Methods for all the WebElements declared
	public void clickSingUp() throws InterruptedException {

		SignUp.click();

	}

	public void setFullName(String fullname) {

		FullName.sendKeys(fullname);

	}

	public void setCompanyName(String companyname) {
		CompanyName.sendKeys(companyname);

	}

	public void setCompanyType() throws InterruptedException {

		// Select drpCompany = new Select(CompanyType);
		// drpCompany.selectByVisibleText("End User");
		Thread.sleep(1000);
		CompanyType.click();
		Thread.sleep(2000);
		CompanyTypeValue.click();

	}

	public void setAddress(String address) {

		Address.sendKeys(address);

	}

	public void selectCountry() throws InterruptedException {

		Country.click();
		Thread.sleep(1000);
		CountryValue.click();
		// countries.selectByVisibleText("Iraq");
		// Select countries = new Select(Country);
	}

	public void setPlant(String plant) {
		Plant.sendKeys(plant);

	}

	public void setPhoneNumber(String phonenumber) {
		PhoneNumber.sendKeys(phonenumber);
	}

	public boolean clickContinue() throws InterruptedException {
		Thread.sleep(2000);
		return Continue.isEnabled();
	}

	public void addSerialNo(String sno) {
		SerialNo.sendKeys(sno);
	}

	public void addUniqueNo(String uno) {
		UnqiueID.sendKeys(uno);
	}

	public void addDescription(String desc) {
		Desc.sendKeys(desc);
	}

	public void clickoNContinue() {
		Continue2.click();
	}

	public void setEmailId(String emailid) {

		EmailAddress.sendKeys(emailid);
	}

	public void setConfirmEmail(String confemail) {
		ConfirmEmailAddress.sendKeys(confemail);
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

	public void waitForElementClickabled() {
		WebDriverWait wait = new WebDriverWait(ldriver, 45);
		wait.until(ExpectedConditions.elementToBeClickable(AcceptAndSignup));

	}

	public void acceptAndSignUP() {
		AcceptAndSignup.click();

	}

	public String getCopyRightText() {
		return CopyRight.getText();
	}

	public boolean getCopyRightDisplayed() {
		return CopyRight.isDisplayed();
	}

	public String getPrivacyPolicyText() {
		return PrivacyPolicy.getText();

	}

	public boolean PrivacyPolicyDisplayed() {
		return PrivacyPolicy.isDisplayed();
	}

	public void clickPrivacy() {
		PrivacyPolicy.click();
	}

	public boolean TermsOfServiceDisplayed() {
		return TermsService.isDisplayed();
	}

	public String getTermsOFServiceText() {
		return TermsService.getText();

	}

	public boolean cookiesDisplayed() {
		return Cookies.isDisplayed();

	}

	public String getCookiesText() {
		return Cookies.getText();

	}

	public void clickCookies() {
		Cookies.click();
	}

	public boolean contactUsDisplayed() {
		return ContactUs.isDisplayed();

	}

	public String getContactUsText() {
		return ContactUs.getText();

	}

	public void clickContactUs() {
		ContactUs.click();
	}

	public String getContactUsHeaderText() {
		return ContactUsHeader.getText();

	}
}
