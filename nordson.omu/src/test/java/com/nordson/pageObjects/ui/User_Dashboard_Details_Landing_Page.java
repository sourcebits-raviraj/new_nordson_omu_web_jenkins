package com.nordson.pageObjects.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

	@FindBy(xpath = "//p[@class='sub-heading']/preceding::div[1]")
	public WebElement welcome;

	@FindBy(xpath = "//div[1]/div[1]/span/img")
	public WebElement dashbaord;

	// @FindBy(xpath = "//div[normalize-space()='DASHBOARD']")
	@FindBy(xpath = "(//*[@routerlink='/dashboard']//div)[3]")
	public WebElement DashBoard;

	@FindBy(xpath = "//*[contains(@href,'privacy-statement')]")
	public WebElement PrivacyPolicy;

	@FindBy(linkText = "Terms of Service")
	public WebElement TermsofService;

	@FindBy(xpath = "//div[@class='right footer-text cursor-pointer pad-responsive-signup']")
	public WebElement ContactUs;

	@FindBy(xpath = "//*[contains(@href,'privacy-statement')]/..//preceding-sibling::div[1]")
	public WebElement TermsService;

	@FindBy(xpath = "//div[@class='contact-head']")
	public WebElement ContactUsHeader;

	@FindBy(xpath = "//*[@id='bt']//div[2]")
	public WebElement SetUpTool;
	
	@FindBy(id = "pop-up-setup-tools")
	public WebElement SetUpToolPopup;
	
	@FindBy(xpath = "//*[@id='pop-up-setup-tools']//*[@class='button-div']")
	public WebElement LoadFromUSB;
	
	@FindBy(xpath = "//*[@id='pop-up-setup-tools']//*[@class='button-div']/label")
	public WebElement LoadFromUSBText;
	
	@FindBy(xpath = "(//*[@id='pop-up-setup-tools']//div[@class='ng-star-inserted'])[2]//div[1]/div")
	public WebElement UsePreviousFile;

	@FindBy(xpath = "(//*[@id='pop-up-setup-tools']//div[@class='button-div display-flex-al-center cursor-pointer'])[2]/div/div")
	public WebElement CreateNew;
	
	@FindBy(xpath = "//*[@class='select-load-text']/../..")
	public WebElement CreateNorFilePopup;
	
	@FindBy(xpath = "(//*[contains(@class,'text-above-setup')])[2]")
	public WebElement NORFileDescription;
	
	@FindBy(xpath = "//*[@class='btn-set-up cancel-btn-color']")
	public WebElement UsePreviousCancel;
	
	@FindBy(xpath = "(//*[@id='pop-up-setup-tools']//*[@class='ng-star-inserted'])[1]")
	public WebElement CreateNewNorFilePopup;
	
	@FindBy(xpath = "//*[@panelclass='equipment-panel-select']")
	public WebElement EquipmentPanel;
	
	@FindBy(xpath = "//*[contains(@class,'descriptionInput')]")
	public WebElement EquipmentDescription;
	
	@FindBy(xpath = "//*[contains(@class,'descriptionInput')]/preceding-sibling::label")
	public WebElement EquipmentDescriptionlbl;
	
	@FindBy(xpath = "//textarea[@placeholder='Write Description here...']")
	public WebElement NORFileAddDescriptionText;
	
	@FindBy(xpath = "//*[contains(@class,'btn-set-up submit-btn-color')]")
	public WebElement SubmitButton;
	
	@FindBy(xpath = "//*[contains(@class,'temp-heading')]//*[contains(@class,'font-responsive')]")
	public WebElement TemperatureZoneHeading;
	
	@FindBy(xpath = "//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[2]")
	public WebElement SystemSettingsLink;
	
	@FindBy(xpath = "//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[2]//mat-panel-description")
	public WebElement SystemSettingstext;

	@FindBy(xpath = "//*[contains(@href,'/preferences')]")
	public WebElement PreferencesLink;
	
	@FindBy(xpath = "//*[contains(@href,'/preferences')]//div")
	public WebElement Preferencestxt;
	
	@FindBy(xpath = "//*[@formcontrolname='PressureUnits']//*[contains(@class,'psi')]//div[1]/div[1]")
	public WebElement PSIUnit;
	
	@FindBy(xpath = "//*[@class='btn apply']")
	public WebElement PrefrencesSave;
	
	@FindBy(xpath = "//div[contains(@class,'toast-message ng-star-inserted')]")
	WebElement ToastMessage;
	
	@FindBy(xpath = "//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[1]")
	public WebElement RuntimeSettingsLink;
	
	@FindBy(xpath = "//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[1]//mat-panel-description")
	public WebElement RuntimeSettingstext;
	
	@FindBy(xpath = "//*[@routerlink='settings/pressure']")
	public WebElement RuntimeSettingsPressureLink;
	
	@FindBy(xpath = "//*[@routerlink='settings/pressure']/div")
	public WebElement RuntimeSettingsPressuretext;
	
	@FindBy(xpath = "//*[contains(@class,'temp-heading')]//*[contains(@class,'font-responsive')]")
	public WebElement PressureHeading;

	@FindBy(xpath = "//*[contains(@class,'temp-heading')]//following-sibling::div[1]//div[@class='paragraph']")
	public WebElement RuntimeSettingsPressureSubTitletext;
	
	@FindBy(xpath = "//*[contains(@class,'card-applicator')]/div[1]/div[1]")
	public WebElement MainPressureModeTitle;
	
	@FindBy(xpath = "//*[contains(@class,'card-applicator')]/div[3]/div/div[2]/div[1]")
	public WebElement PressureSettingRangeAlertText;
	
	@FindBy(xpath = "//div[@class='ng-star-inserted']/div[3]/div[1]")
	public WebElement MinimumPressureAlertText;
	
	@FindBy(xpath = "//div[@class='ng-star-inserted']/div[4]/div[1]")
	public WebElement MaximumPressureAlertText;
	
	@FindBy(xpath = "//*[@formcontrolname='selectPressure']//div[@class='mat-select-arrow']")
	public WebElement MainPressureModeSelection;
	
	@FindBy(xpath = "//input[contains(@name,'ulPressureMinSetPoint')]")
	public WebElement MinimumPressureAlert;

	@FindBy(xpath = "//input[contains(@name,'ulPressureMaxSetPoint')]")
	public WebElement MaximumPressureAlert;
	
	@FindBy(xpath = "//*[@class='download']")
	public WebElement Download;
	
	@FindBy(xpath = "//*[@class='download']/img")
	public WebElement Downloadtxt;
	
	
	@FindBy(xpath = "//button[@class='apply btn']")
	public WebElement Savebutton;
	
	
	
	// Page Action Methods for all the WebElements declared

	public boolean welcomeDisplayed() throws InterruptedException {

		return welcome.isDisplayed();

	}

	public boolean dashboardDisplayed() throws InterruptedException {

		return dashbaord.isDisplayed();

	}

	public String Dashboard_Text() {

		String DashboardText = DashBoard.getText();
		return DashboardText;

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
	
	public boolean setUpToolDisplayed() throws Exception {
		Am.sleepTime(1500);
		return SetUpTool.isDisplayed();
	}

	public String getSetUpToolText() {
		return SetUpTool.getText();
	}
	
	public void clickSetUpTool() {
		Am.waitForAnElementClickable(By.xpath("//*[@id='bt']//div[2]"));
	    SetUpTool.click();
	}
	
	public boolean setUpToolPopUpDisplayed() throws Exception {
		Am.sleepTime(1500);
		return SetUpToolPopup.isDisplayed();
	}
	
	public String getLoadFromUSB_ComputerText() {
		return LoadFromUSBText.getText();
	}
	
	public boolean loadFromUSBIsDisplayed() throws Exception {
		Am.waitForAnElementPresence(By.xpath("//*[@id='pop-up-setup-tools']//*[@class='button-div']"));
		Am.sleepTime(1500);
		return LoadFromUSB.isDisplayed();
	}
	
	public void clickLoadFromUSB() {
		Am.waitForAnElementClickable(By.xpath("//*[@id='pop-up-setup-tools']//*[@class='button-div']"));
		LoadFromUSB.click();
	}
	
	public boolean UsePreviousDisplayed() throws Exception {
		Am.waitForAnElementPresence(By.xpath("(//*[@id='pop-up-setup-tools']//div[@class='ng-star-inserted'])[2]//div[1]/div"));
		Am.sleepTime(1500);
		return UsePreviousFile.isDisplayed();
	}
	
	public String getUsePreviousText() {
		return UsePreviousFile.getText();
	}
	
	public void clickUsePreviousFile() {
		Am.waitForAnElementClickable(By.xpath("(//*[@id='pop-up-setup-tools']//div[@class='ng-star-inserted'])[2]//div[1]/div"));
		UsePreviousFile.click();
	}
	
	public void clickUsePreviousFileCancel() {
		Am.waitForAnElementClickable(By.xpath("//*[@class='btn-set-up cancel-btn-color']"));
		UsePreviousCancel.click();
	}
	
	public boolean CreateNewDisplayed() throws Exception {
		Am.waitForAnElementPresence(By.xpath("(//*[@id='pop-up-setup-tools']//div[@class='button-div display-flex-al-center cursor-pointer'])[2]/div/div"));
		Am.sleepTime(1500);
		return CreateNew.isDisplayed();
	}
	
	public String getCreateNewText() {
		return CreateNew.getText();
	}
	
	public void clickCreateNew() {
		Am.waitForAnElementClickable(By.xpath("(//*[@id='pop-up-setup-tools']//div[@class='button-div display-flex-al-center cursor-pointer'])[2]/div/div"));
		CreateNew.click();
	}
	
	
	public boolean CreateNewNorfilePopupDisplayed() throws Exception {
		Am.waitForAnElementPresence(By.xpath("(//*[@id='pop-up-setup-tools']//*[@class='ng-star-inserted'])[1]"));
		Am.sleepTime(1500);
		return CreateNewNorFilePopup.isDisplayed();
	}
	
	public boolean EquipmentSerialNoDisplayed() throws Exception {
		Am.waitForAnElementPresence(By.xpath("//*[@panelclass='equipment-panel-select']"));
		Am.sleepTime(1500);
		return EquipmentPanel.isDisplayed();
	}
	
	public boolean EquipmentDescriptionDisplayed() throws Exception {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@class,'descriptionInput')]"));
		Am.sleepTime(1500);
		return EquipmentDescription.isDisplayed();
	}
	
	public boolean NORFileDescriptionDisplayed() throws Exception {
		Am.waitForAnElementPresence(By.xpath("(//*[contains(@class,'text-above-setup')])[2]"));
		Am.sleepTime(1500);
		return NORFileDescription.isDisplayed();
	}
	
	public void clickSubmitbutton() {
		Am.waitForAnElementClickable(By.xpath("//*[contains(@class,'btn-set-up submit-btn-color')]"));
		SubmitButton.click();
	}
	
	public boolean SubmitbuttonISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@class,'btn-set-up submit-btn-color')]"));
		return SubmitButton.isDisplayed();
	}

	public void addDescription() {
		Am.waitForAnElementPresence(By.xpath("//textarea[@placeholder='Write Description here...']"));
		NORFileAddDescriptionText.sendKeys("Description");
	}
	
	public String getEquipmentDescriptionText() {
		return EquipmentDescriptionlbl.getText();
	}
	
	public String getNorFileDescriptionText() {
		return NORFileDescription.getText();
	}
	
	public boolean TemperatureTitleISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@class,'temp-heading')]//*[contains(@class,'font-responsive')]"));
		return TemperatureZoneHeading.isDisplayed();
	}
	
	public String geTemperatureZoneHeadingText() {
		return TemperatureZoneHeading.getText();
	}
	
	public boolean SystemSettingsISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[2]"));
		return SystemSettingsLink.isDisplayed();
	}
	
	public String getSystemSettingsText() {
		return SystemSettingstext.getText();
	}
	
	public void clickSystemSettings() {
		Am.waitForAnElementClickable(By.xpath("//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[2]"));
		SystemSettingsLink.click();
	}
	
	public boolean PreferencesISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@href,'/preferences')]"));
		return PreferencesLink.isDisplayed();
	}
	
	public String getPreferencesText() {
		return Preferencestxt.getText();
	}
	
	public void clickPreferences() {
		//Am.waitForAnElementClickable(By.xpath("//*//*[contains(@href,'/preferences')]"));
		//PreferencesLink.click();
		((JavascriptExecutor)ldriver).executeScript("arguments[0].click();", PreferencesLink);
		
	}
	
	public boolean PSIUnitISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[@formcontrolname='PressureUnits']//*[contains(@class,'psi')]//div[1]/div[1]"));
		return PreferencesLink.isDisplayed();
	}
	
	public void ClickPSIUnit() throws InterruptedException {
		try {
		
			Am.waitForAnElementPresence(
					By.xpath("//*[@formcontrolname='PressureUnits']//*[contains(@class,'psi')]//div[1]/div[1]"));
			PSIUnit.click();
		} catch (Exception e) {
			System.out.println("Unable to click on PSI Radio button");
		}
	}

	public boolean PreferencesSaveISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[@class='btn apply']"));
		return PrefrencesSave.isDisplayed();
	}
	
	public void clickPreferencesSave() {
		Am.waitForAnElementClickable(By.xpath("//*[@class='btn apply']"));
		PrefrencesSave.click();
	}
	
	public boolean toastmessageDisplayed() {
		Am.waitForAnElementPresence(By.cssSelector("div#toast-container"));
		boolean tmsg = ToastMessage.isDisplayed();
		System.out.println("The toast message is displayed=" + tmsg);
		return tmsg;
	}
	
	  public String getToastMessageText() {
		String tm="";
		Am.waitForAnElementPresence(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
		if(ToastMessage.isDisplayed()==true) {
			 tm = ToastMessage.getText();
			System.out.println("Value of the toast message is=" + tm);
			Am.waitForAnElementIsInVisible(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
			 }
		else
			System.out.println("Toastmsg not displayed");
		
		return tm;
		}
	public boolean RuntimeSettingsISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[1]"));
		return RuntimeSettingsLink.isDisplayed();
	}
	
	public String getRuntimeSettingsText() {
		return RuntimeSettingstext.getText();
	}
	
	public void clickRuntimeSettings() {
		Am.waitForAnElementClickable(By.xpath("//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[1]"));
		RuntimeSettingsLink.click();
	}
	
	
	public boolean RuntimeSettingsPressureISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[@routerlink='settings/pressure']"));
		return RuntimeSettingsPressureLink.isDisplayed();
	}
	
	public String getRuntimeSettingsPressureText() {
		return RuntimeSettingsPressuretext.getText();
	}
	
	public void clickRuntimeSettingsPressure() {
		Am.waitForAnElementClickable(By.xpath("//*[@routerlink='settings/pressure']"));
		((JavascriptExecutor)ldriver).executeScript("arguments[0].click();", RuntimeSettingsPressureLink);
		//RuntimeSettingsPressureLink.click();
	}
	
	
	public boolean PressureSettingsTitleISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@class,'temp-heading')]//*[contains(@class,'font-responsive')]"));
		return PressureHeading.isDisplayed();
	}
	
	public String PressureSettingsHeadingText() {
		return PressureHeading.getText();
	}
	
	public boolean PressureSettingsSubTitleISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@class,'temp-heading')]//*[contains(@class,'font-responsive')]"));
		return RuntimeSettingsPressureSubTitletext.isDisplayed();
	}
	
	public String PressureSettingsSubTitleText() {
		return RuntimeSettingsPressureSubTitletext.getText();
	}
	
	public boolean PressureSettingsMainPressureModeTitleISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@class,'card-applicator')]/div[1]/div[1]"));
		return MainPressureModeTitle.isDisplayed();
	}
	
	public String getPressureSettingsMainPressureModeTitleText() {
		return MainPressureModeTitle.getText();
	}
	
	public boolean PressureSettingsRangeAlertTitleISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(@class,'card-applicator')]/div[3]/div/div[2]/div[1]"));
		return PressureSettingRangeAlertText.isDisplayed();
	}
	
	public String getPressureSettingsRangeAlertTitleText() {
		return PressureSettingRangeAlertText.getText();
	}
	

	public boolean MinimumPressureAlertTitleISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//div[@class='ng-star-inserted']/div[3]/div[1]"));
		return MinimumPressureAlertText.isDisplayed();
	}
	
	public String getMinimumPressureAlertTitleText() {
		return MinimumPressureAlertText.getText();
	}
	
	public boolean MaximumPressureAlertTitleISDisplayed() {
		Am.waitForAnElementPresence(By.xpath("//div[@class='ng-star-inserted']/div[4]/div[1]"));
		return MaximumPressureAlertText.isDisplayed();
	}
	
	public String getMaximumPressureAlertTitleText() {
		return MaximumPressureAlertText.getText();
	}
	
	// Pressure mode selection
		public void SelectMainPressureModeSelectionDropdown(String txttobeselected) throws InterruptedException {
			Am.waitForAnElementPresence(
					By.xpath("//*[@formcontrolname='selectPressure']//div[@class='mat-select-arrow']"));
			Am.waitForAnElementClickable(
					By.xpath("//*[@formcontrolname='selectPressure']//div[@class='mat-select-arrow']"));
			MainPressureModeSelection.click();
			Am.waitForAnElementPresence(By.xpath("//mat-option/span[contains(.,'" + txttobeselected + "')]"));
			ldriver.findElement(By.xpath("//mat-option/span[contains(.,'" + txttobeselected + "')]")).click();
		}
		
		public String getMinimumPressureAlertValue() {
			return MinimumPressureAlert.getAttribute("value");
		}
		
		public String getMaximumPressureAlertValue() {
			return MaximumPressureAlert.getAttribute("value");
		}
		
		public void clearMinimumPressureAlertValue() {
			MinimumPressureAlert.click();
			for (int i = 0; i <= 4; i++) {
				MinimumPressureAlert.sendKeys(Keys.BACK_SPACE);
			}
		}
		
		public void clearMaximumPressureAlertValue() {
			MaximumPressureAlert.click();
			for (int i = 0; i <= 4; i++) {
				MaximumPressureAlert.sendKeys(Keys.BACK_SPACE);
			}
			
		}
		public void setMinimumPressureAlertValue(String minvalue) {
			  MinimumPressureAlert.sendKeys(minvalue);
		}
		
		public void setMaximumPressureAlertValue(String maxvalue) {
			  MaximumPressureAlert.sendKeys(maxvalue);
			}
		
		  public void clickdownload() {
			   
			  Am.waitForAnElementClickable(By.xpath("//*[@class='download']"));
				Download.click();
			}
		  
		  public void saveButton() throws Exception {
				Am.sleepTime(2000);
				Am.waitForAnElementClickable(By.xpath("//button[@class='apply btn']"));
				Savebutton.click();
			}
		  
		
		  public boolean DownloadISDisplayed() {
			   
			  Am.waitForAnElementClickable(By.xpath("//*[@class='download']"));
				return Download.isDisplayed();
			}
		  
			public String getDownloadtext() {
				
				System.out.println(Download.getText());
				return Download.getText().trim();
			}
	
}
