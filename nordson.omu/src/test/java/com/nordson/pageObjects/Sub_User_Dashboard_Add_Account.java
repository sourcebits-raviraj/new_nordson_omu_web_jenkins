package com.nordson.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.utilities.ActionMethods;

public class Sub_User_Dashboard_Add_Account {

	WebDriver ldriver;
	WebDriverWait wait;
	// ActionMethods Am;
	ActionMethods Am = new ActionMethods();

	// Constructor of the LoginPage to initiate driver
	public Sub_User_Dashboard_Add_Account(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//div[normalize-space()='Sub User Account']")
	public WebElement SubUserAccount;

	@FindBy(css = "body > nordson-root:nth-child(1) > div:nth-child(1) > nordson-subuser:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")
	public WebElement ManageSubUSer;

	@FindBy(xpath = "//button[contains(@class,'ng-star-inserted')]")
	public WebElement AddSubUSerAccount;

	@FindBy(xpath = "//div[@class='add-subuser-head']")
	public WebElement AddSubUerpop;

	@FindBy(xpath = "//div[@class='add-subuser-head']/following::mat-label[1]")
	public WebElement FullNameText;

	@FindBy(xpath = "//div[@class='add-subuser-head']/following::mat-label[2]")
	public WebElement EmailTextBox;

	@FindBy(xpath = "//div[@class='add-subuser-head']/following::input[1]")
	public WebElement FullNameInput;

	@FindBy(xpath = "//div[@class='add-subuser-head']/following::input[2]")
	public WebElement EmailInput;

	@FindBy(xpath = "//span[@class='add-svg']")
	public WebElement AddButton;

	@FindBy(xpath = "//button[normalize-space()='ADD']")
	public WebElement AddButtonText;

	@FindBy(xpath = "//button[normalize-space()='CANCEL']")
	public WebElement CancelButton;

	@FindBy(xpath = "//label[@for='mat-radio-2-input']//div[@class='mat-radio-label-content']")
	public WebElement ReadWriteText;

	@FindBy(xpath = "//button[normalize-space()='ADD']/preceding::span[1]")
	public WebElement ReadyOnlyButton;

	// mat-card[@class='grid-col grid-col-responsive cursor-pointer mat-card
	// ng-star-inserted']

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

	@FindAll(@FindBy(xpath = "//mat-card[@class='grid-col grid-col-responsive cursor-pointer mat-card ng-star-inserted']"))
	public List<WebElement> NoOfUsers;

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

	// Page Action Methods for all the WebElements declared

	public String SubUserAccountText() {
		String SubuserText = SubUserAccount.getText();
		return SubuserText;
	}

	public void SubUserAccountClick() {
		SubUserAccount.click();

	}

	public boolean SubUserAccountDisplayed() throws InterruptedException {

		boolean sub = SubUserAccount.isDisplayed();
		System.out.println("The Sub account text displayed is=" + sub);
		return sub;
	}

	public String ManageSubUserAccountText() {
		String ManageSubuserText = ManageSubUSer.getText();
		return ManageSubuserText;
	}

	public boolean AddSubUserAccountDisplayed() throws InterruptedException {
		Am.waitForAnElementPresence(AddSubUSerAccount);
		boolean AddSub = AddSubUSerAccount.isDisplayed();
		System.out.println("The Add Sub account button displayed is=" + AddSub);
		return AddSub;
	}

	public boolean AddSubUserAccountEnabled() throws InterruptedException {

		boolean AddSubEnabled = AddButton.isEnabled();
		System.out.println("The Add Sub account button enabled=" + AddSubEnabled);
		return AddSubEnabled;
	}

	public void ClickAddSubUserAccount() throws InterruptedException {

		AddButton.click();

	}

	public String AddSubUSerPopUPtext() throws InterruptedException {

		String subusertext = AddSubUerpop.getText();
		return subusertext;

	}

	public String FullNametext() throws InterruptedException {

		String fullname = FullNameText.getText();
		return fullname;

	}

	public String Emailtext() throws InterruptedException {

		String email = EmailTextBox.getText();
		return email;

	}

	public String Canceltext() throws InterruptedException {

		String cancel = CancelButton.getText();
		return cancel;

	}

	public String AddButtontext() throws InterruptedException {

		String addButton = AddButtonText.getText();
		return addButton;

	}

	public String ReadWritetext() throws InterruptedException {

		String ReadWrite = ReadWriteText.getText();
		return ReadWrite;

	}

	public String ReadOnlytext() throws InterruptedException {

		String ReadOnly = ReadyOnlyButton.getText();
		return ReadOnly;

	}

	public void SetFullName(String fullname) throws InterruptedException {

		FullNameInput.sendKeys(fullname);
	}

	public void SetEmail(String email) throws InterruptedException {

		EmailInput.sendKeys(email);
	}

	public void ClickAddButton() throws InterruptedException {
		Am.waitForAnElementPresence(AddButtonText);
		AddButtonText.click();

	}

	public boolean AddButtonPopEnabled() throws InterruptedException {

		return AddButtonText.isEnabled();

	}

	public void ClickReadWriteRadioButton() throws Exception {

		Am.sleepTime(2000);
		ReadWriteText.click();

	}

	public void ClikcReadOnlyRadioButton() throws InterruptedException {

		ReadyOnlyButton.click();

	}

	public int NoOfUsersAdded() {
		int count = NoOfUsers.size();
		System.out.println("No of users Added=" + count);
		return count;
	}
}
