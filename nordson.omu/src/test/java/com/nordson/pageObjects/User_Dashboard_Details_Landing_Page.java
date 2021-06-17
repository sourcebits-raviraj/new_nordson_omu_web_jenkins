package com.nordson.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
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
	ActionMethods Am = new ActionMethods();

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

	//@FindBy(xpath = "//div[normalize-space()='DASHBOARD']")
	@FindBy(xpath = "(//*[@routerlink='/dashboard']//div)[3]")
	public WebElement DashBoard;

	@FindBy(xpath = "//div[normalize-space()='Model Registration']")
	public WebElement ModelRegistration;

	@FindBy(xpath = "//div[normalize-space()='Sub User Account']")
	public WebElement SubUserAccount;

	@FindBy(xpath = "//div[contains(text(),' SETUP TOOL ')]")
	public WebElement SetUpToolLink;

	@FindBy(xpath = "//div[contains(text(),'CREATE NEW')]")
	public WebElement CreateNewNorFileButton;

	@FindBy(xpath = "//div[contains(text(),'Use Previous File')]")
	public WebElement UsePrevoiusFile;

	@FindBy(xpath = "//label[normalize-space()='Load from USB/Computer']")
	public WebElement LoadFromUSB;

	@FindBy(xpath = "//div[contains(text(),'Use Previous File')]")
	public WebElement PreviousFile;

	@FindBy(xpath = "//div[normalize-space()='Media Center']")
	public WebElement MediaCenter;

	@FindBy(xpath = "//div[contains(text(),'Manage Licenses')]")
	public WebElement ManageLincense;

	@FindBy(css = "div[class='col s10 m10 l10 xl10 pad-20 uppercase']")
	public WebElement HelpCenter;

	@FindBy(xpath = "//div[@id='lang-select']")
	public WebElement ClickLanguage;

	@FindAll(@FindBy(xpath = "//span[@class='mat-option-text']"))
	public List<WebElement> LanaguageDropdowns;

	@FindBy(xpath = "//span[. = 'RaviRaj Metri']")
	public WebElement UserProfile;

	@FindBy(xpath = "//div[. = ' Profile ']")
	public WebElement Profile;

	@FindBy(xpath = "//div/div/div/div/article[1]/div[1]/div")
	public WebElement ProfileDetails;

	@FindBy(xpath = "//article[1]/div[2]/div")
	public WebElement FullName;

	@FindBy(xpath = "//article[2]/div[1]/div[1]")
	public WebElement Email;

	@FindBy(xpath = "//article[2]/div[1]/div[2]")
	public WebElement Country;

	@FindBy(xpath = "//article[2]/div[2]/div[1]")
	public WebElement PhoneNumber;

	@FindBy(xpath = "//article/div[2]/div[2]")
	public WebElement PlantName;

	@FindBy(xpath = "//article/div[3]/div[1]")
	public WebElement CompanyName;

	@FindBy(xpath = "//div[@class='col m12 s12 l6 xl6 responsive-pad-top']/following::div[16]")
	public WebElement CompanyType;

	@FindBy(xpath = "//div[@class='col m12 s12 l6 xl6 responsive-pad-top']/following::div[20]")
	public WebElement Address;

	@FindBy(xpath = "//div[@class='col m12 s12 l6 xl6 responsive-pad-top']/following::div[25]")
	public WebElement NordsonAccountNumber;

	@FindBy(xpath = "//span[@class='pointer ng-star-inserted']")
	public WebElement ChangePassword;

	@FindBy(xpath = "//span[@class='pointer']")
	public WebElement EditProfile;

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

	@FindBy(css = ".right.footer-text.pad-responsive-signup.pad-tp-15-responsive.cursor-pointer")
	public WebElement TermsService;

	@FindBy(xpath = "//div[@class='contact-head']")
	public WebElement ContactUsHeader;

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

	public String SetUpTool() {

		String SubuserText = SetUpToolLink.getText();
		return SubuserText;

	}

	public void clickOnSetUpTool() throws InterruptedException {

		Am = new ActionMethods();
		Am.waitForAnElementPresence(By.xpath("//div[contains(text(),' SETUP TOOL ')]"));
		Am.waitForAnElementClickable(By.xpath("//div[contains(text(),' SETUP TOOL ')]"));
		SetUpToolLink.click();
	}

	public String CreateNewFile() {

		String NewFile = CreateNewNorFileButton.getText();
		return NewFile;
	}

	public String PreviousFileText() {

		String previousFile = PreviousFile.getText();
		return previousFile;
	}

	public String LoadFromUSB() {

		String LoadUSB = LoadFromUSB.getText();
		return LoadUSB;
	}

	public void MediaCenterClick() {

		MediaCenter.click();
	}

	public void pageRefresh() {

		ldriver.navigate().refresh();
	}

	public void clickDashboard() throws InterruptedException {

		dashbaord.click();

	}

	public String MediaCenterText() {

		String Media = MediaCenter.getText();
		return Media;
	}

	public boolean manageLincenseDisplayed() {
		return ManageLincense.isDisplayed();

	}

	public String getLincenseText() {
		return ManageLincense.getText();

	}

	public void clickManageLincense() {
		ManageLincense.click();
	}

	public boolean helpCenterDisplayed() {
		return HelpCenter.isDisplayed();

	}

	public String getHelpCenterText() {
		return HelpCenter.getText();

	}

	public void clickHelpCenter() {
		HelpCenter.click();
	}

	public void clickLanguage() {
		ClickLanguage.click();
	}

	public int LanguageDropdownsCount() {

		int dropdowns = LanaguageDropdowns.size();

		// Get the length
		System.out.println("Size of country" + LanaguageDropdowns.size());

		return dropdowns;
	}

	public List<WebElement> LanguageDropdownsCountries() {

		// Loop to print languages
		for (int j = 0; j < LanaguageDropdowns.size(); j++) {
			System.out.println(LanaguageDropdowns.get(j).getText());

		}

		LanaguageDropdowns.get(3).getText();

		return LanaguageDropdowns;

	}

	public void clickProfile() throws Exception {

		Am.sleepTime(1000);
		UserProfile.click();
		Am.sleepTime(1000);
		Profile.click();
	}

	public String profileText() {
		return ProfileDetails.getText();

	}

	public boolean profileDisplayed() {
		return ProfileDetails.isDisplayed();

	}

	public String FullNameProfile() {
		return FullName.getText();

	}

	public String EmailText() {
		return Email.getText();

	}

	public String CountryText() {
		return Country.getText();

	}

	public String PhoneNumberText() {
		return PhoneNumber.getText();

	}

	public String PlantNameText() {
		return PlantName.getText();

	}

	public String CompanyNameText() {
		return CompanyName.getText();

	}

	public String CompanyTypeText() {
		return CompanyType.getText();

	}

	public String AddressText() {
		return Address.getText();

	}

	public String NordsonAccountNumberText() {
		return NordsonAccountNumber.getText();

	}

	public String ChangePasswordText() {
		return ChangePassword.getText();

	}

	public String EditProfileText() {
		return EditProfile.getText();

	}

	public boolean getCopyRightDisplayed() {
		return CopyRight.isDisplayed();
	}

	public String getCopyRightText() {
		return CopyRight.getText();
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
