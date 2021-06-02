package com.nordson.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.utilities.ActionMethods;

public class Pressure_Min_Max_Validations {

	private static final String String = null;
	WebDriver ldriver;
	WebDriverWait wait;
	ActionMethods customwait = new ActionMethods();

	// Constructor of the Pressure_Min_Max_Validations class to initiate driver
	public Pressure_Min_Max_Validations(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	// WebElements Locators
	@FindBy(xpath = "//div[contains(text(),' SETUP TOOL ')]")
	public WebElement SetUpToolLink;

	@FindBy(xpath = "//div[contains(text(),'CREATE NEW')]")
	WebElement CreateNewNorFileButton;

	@FindBy(xpath = "//textarea[@placeholder='Write Description here...']")
	WebElement AddDescriptionText;

	@FindBy(xpath = "//button[normalize-space()='SUBMIT']")
	WebElement SubmitButton;

	@FindBy(xpath = "//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[2]")
	WebElement SystemSettingsLink;

	@FindBy(xpath = "//div[contains(text(),'Preferences')]")
	WebElement PreferencesLink;

	@FindBy(xpath = "//article[normalize-space()='Pressure Unit']/following::div[2]")
	WebElement KPAUnit;

	@FindBy(xpath = "//button[normalize-space()='SAVE']")
	WebElement SaveButton;

	/*
	 * @FindBy(xpath =
	 * "//span[contains(text(), 'DASHBOARD')]/following::mat-panel-description[1]")
	 * WebElement RunTimeSettings;
	 */

	@FindBy(xpath = "//*[contains(text(), ' Runtime settings ')]")
	WebElement RunTimeSettings;

	@FindBy(xpath = "//div[normalize-space()='Pressure']//..")
	WebElement Pressure;

	@FindBy(xpath = "//input[contains(@name,'ulPressureMinSetPoint')]")
	WebElement clearKPAMinSetPoint;

	@FindBy(xpath = "//input[contains(@name,'ulPressureMaxSetPoint')]")
	WebElement clearKPAMaxSetPoint;

	@FindBy(xpath = "//input[contains(@name,'ulPressureMinSetPoint')]/following::span[1]")
	WebElement kPaText;

	@FindBy(xpath = "//input[contains(@name,'ulPressureMinSetPoint')]/following::span[1]")
	WebElement BARText;

	@FindBy(xpath = "//article[normalize-space()='Pressure Unit']/following::div[8]")
	WebElement BARUnit;

	@FindBy(xpath = "//div[contains(@class,'row row-margin')]//span[contains(@class,'min')][normalize-space()='BAR']/preceding::input[1]")
	WebElement BARMinSetPoint;

	@FindBy(xpath = "//div[contains(@class,'row display-flex-align-center')]//span[contains(@class,'min')][normalize-space()='BAR']/preceding::input[1]")
	WebElement BARMaxSetPoint;

	@FindBy(xpath = "//article[normalize-space()='Pressure Unit']/following::div[14]")
	WebElement PSIUnit;

	@FindBy(xpath = "//div[contains(@class,'row row-margin')]//span[contains(@class,'min')][normalize-space()='PSI']/preceding::input[1]")
	WebElement PSIMinSetPoint;

	@FindBy(xpath = "//div[contains(@class,'row display-flex-align-center')]//span[contains(@class,'min')][normalize-space()='PSI']/preceding::input[1]")
	WebElement PSIMaxSetPoint;

	@FindBy(xpath = "//div[contains(@class,'row row-margin')]//span[contains(@class,'min')][normalize-space()='PSI']")
	WebElement PSIText;

	@FindBy(xpath = "//span[contains(text(),'Pneumatic')]")
	WebElement SelectHydaulic;

	@FindBy(xpath = "//span[@class='mat-option-text'][normalize-space()='Hydraulic']")
	WebElement SelectHydaulicValue;

	@FindBy(css = "div#toast-container")
	WebElement ToastMessage;

	@FindBy(xpath = "//span[contains(text(), 'DASHBOARD')]")
	WebElement Dashboard;

	@FindBy(xpath = "//div[contains(@class,'row row-margin')]//span[contains(@class,'min')][normalize-space()='kPa']/preceding::input[1]")
	WebElement MinSetPoint;

	@FindBy(xpath = "//div[contains(@class,'row display-flex-align-center')]//span[contains(@class,'min')][normalize-space()='kPa']/preceding::input[1]")
	WebElement MaxSetPoint;

	@FindBy(xpath = "//*[@name='ulPressureSetPoint']")
	WebElement PressureSetPoint;

	@FindBy(xpath = "//*[@name='ulLowPressureAlertDelta']")
	WebElement LowPressureAlertThreshold;

	@FindBy(xpath = "//*[@name='ulHighPressureAlertDelta']")
	WebElement HighPressureAlertThreshold;

	@FindBy(xpath = "//*[@name='ulLowPressureAlertThreshold']")
	WebElement MinimumPressureSetPointRange;

	@FindBy(xpath = "//*[@name='ulHighPressureAlertThreshold']")
	WebElement MaximumPressureSetPointRange;

	@FindBy(xpath = "//*[@formcontrolname='selectPressure']//div[@class='mat-select-arrow']")
	WebElement MainPressureModeSelection;

	@FindBy(xpath = "//*[@id='logout-modal']//button[contains(@class,'button-logout logout')]")
	WebElement changesAlert;

	@FindBy(css = "input[name='lowSpeedPresSett']")
	WebElement LowSpeedPressureSettings;

	@FindBy(css = "input[name='lowLineSpeedSett']")
	WebElement LowLineSpeedSetting;

	@FindBy(css = "input[name='highSpeedPresSett']")
	WebElement HighSpeedPressureSettings;

	@FindBy(css = "input[name='highLineSpeedSett']")
	WebElement HighLineSpeedSetting;

	@FindBy(css = "input[name='maxPressureLimit']")
	WebElement SetMaximumPressurelimit;

	@FindBy(css = "input[name='minPressureLimit']")
	WebElement SetMinimumPressureLimit;

	@FindBy(css = "input[name='fullScaleLineSpeed']")
	WebElement FullScaleLineSpeed;

	@FindBy(xpath = "//mat-radio-button[not(contains(@class,'mat-radio-checked'))]//*[text()='ft/min']")
	WebElement LineSpeedftmin;

	@FindBy(xpath = "//mat-radio-button[not(contains(@class,'mat-radio-checked'))]//*[text()='m/min']")
	WebElement LineSpeedmmin;

	@FindBy(css = "div[class='logout-sub-head pad-tp']")
	WebElement DownwardSlopingRunupContent;

	// Page Action Methods for all the WebElements declared
	public void clickDashboard() throws InterruptedException {
		customwait.waitForAnElementPresence(By.xpath("//span[contains(text(), 'DASHBOARD')]"));
		customwait.waitForAnElementClickable(By.xpath("//span[contains(text(), 'DASHBOARD')]"));
		Dashboard.click();
	}

	public void clickSetUpToolLink() throws InterruptedException {

		customwait.waitForAnElementPresence(By.xpath("//div[contains(text(),' SETUP TOOL ')]"));
		customwait.waitForAnElementClickable(By.xpath("//div[contains(text(),' SETUP TOOL ')]"));
		SetUpToolLink.click();
	}

	public void clickCreateNewFile() {
		customwait.waitForAnElementPresence(By.xpath("//div[contains(text(),'CREATE NEW')]"));
		customwait.waitForAnElementClickable(By.xpath("//div[contains(text(),'CREATE NEW')]"));
		CreateNewNorFileButton.click();
	}

	public void addDescription() {
		customwait.waitForAnElementPresence(By.xpath("//textarea[@placeholder='Write Description here...']"));
		AddDescriptionText.sendKeys("Description");
	}

	public void clickSubmit() throws InterruptedException {
		Thread.sleep(2000);
		customwait.waitForAnElementPresence(By.xpath("//button[normalize-space()='SUBMIT']"));
		customwait.waitForAnElementClickable(By.xpath("//button[normalize-space()='SUBMIT']"));
		SubmitButton.click();
		Thread.sleep(2000);
	}

	public void ClickSystemSettingsLink() throws InterruptedException {
		Thread.sleep(3000);
		customwait.waitForAnElementPresence(
				By.xpath("//span[@class='heading-text-sidenav']/following::mat-expansion-panel-header[2]"));
		Thread.sleep(3000);
		SystemSettingsLink.click();
	}

	public void ClickPreferencesLink() throws InterruptedException {
		Thread.sleep(3000);
		customwait.waitForAnElementPresence(By.xpath("//div[contains(text(),'Preferences')]"));
		PreferencesLink.click();
	}

	public void SelectKPAUnit() throws InterruptedException {
		Thread.sleep(2000);
		customwait.waitForAnElementPresence(By.xpath("//article[normalize-space()='Pressure Unit']/following::div[2]"));
		KPAUnit.click();
	}

	public void saveButton() throws InterruptedException {
		Thread.sleep(2000);
		customwait.waitForAnElementClickable(By.xpath("//button[normalize-space()='SAVE']"));
		SaveButton.click();
	}

	public void clickRunTimeSettings() throws InterruptedException {
		try {
			Thread.sleep(3000);
			customwait.waitForAnElementPresence(By.xpath("//*[contains(text(), ' Runtime settings ')]"));
			RunTimeSettings.click();
		} catch (Exception e) {
			System.out.println("Unable to click on Run Time Settings link");
		}
	}

	public void clickPressure() throws InterruptedException {
		try {
			Thread.sleep(2000);
			customwait.waitForAnElementPresence(By.xpath("//div[normalize-space()='Pressure']//.."));
			Pressure.click();
		} catch (Exception e) {
			System.out.println("Unable to click on Pressure link");
		}
	}

	public void clearMinSetPoint() throws InterruptedException {

		Thread.sleep(2000);
		clearKPAMinSetPoint.click();
		for (int i = 0; i <= 5; i++) {
			clearKPAMinSetPoint.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
		}
	}

	public void clearMaxSetPoint() throws InterruptedException {
		Thread.sleep(2000);
		clearKPAMaxSetPoint.click();
		for (int i = 0; i <= 5; i++) {
			clearKPAMaxSetPoint.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
		}
	}

	public boolean saveButtonEnabled() throws InterruptedException {
		boolean button;
		if (button = SaveButton.isEnabled()) {
			System.out.println(button);
			return button = true;
		} else {
			System.out.println(button);
			return button = false;
		}
	}

	public void setMinValue(String minvalue) throws InterruptedException {
		Thread.sleep(1000);
		for (int i = 0; i <= 5; i++) {
			clearKPAMinSetPoint.sendKeys(Keys.BACK_SPACE);
			Thread.sleep(1000);
		}
		clearKPAMinSetPoint.sendKeys(minvalue);
	}

	public void setMaxValue(String maxvalue) throws InterruptedException {
		Thread.sleep(1000);
		clearKPAMaxSetPoint.click();
		Thread.sleep(1000);
		clearKPAMaxSetPoint.sendKeys(maxvalue);
	}

	// Action Methods For BAR Pressure Unit
	public void SelectBARUnit() throws InterruptedException {
		try {
			Thread.sleep(2000);
			customwait.waitForAnElementPresence(
					By.xpath("//article[normalize-space()='Pressure Unit']/following::div[8]"));
			BARUnit.click();
		} catch (Exception e) {
			System.out.println("Unable to click on BAR Radio button");
		}
	}

	public String getBARMinSetPoint() throws InterruptedException {
		// MinSetPoint.click();
		Thread.sleep(3000);
		boolean bool = BARMinSetPoint.isDisplayed();
		System.out.println("Min Set Point text box  for BAR is displayed=" + bool);
		String min = BARMinSetPoint.getAttribute("value");
		System.out.println("The default Minimum Set Point value for BAR unit=" + min);
		return min;
	}

	public String getBARMaxSetPoint() throws InterruptedException {
		Thread.sleep(2000);
		// MaxSetPoint.click();
		boolean bool1 = BARMaxSetPoint.isDisplayed();
		System.out.println("Max Set Point text box for BAR is displayed=" + bool1);
		customwait.waitFortexttoBePresent(By.xpath(
				"//div[contains(@class,'row display-flex-align-center')]//span[contains(@class,'min')][normalize-space()='BAR']/preceding::input[1]"));
		String max = BARMaxSetPoint.getAttribute("value");
		System.out.println("The default Maximum Set Point value for BAR unit=" + max);
		return max;
	}

	public boolean getBARText() throws InterruptedException {
		Thread.sleep(2000);
		boolean BAR = BARText.isDisplayed();
		System.out.println("The BAR Text value is displayed=" + BAR);
		return BAR;
	}

	// PSI Action methods
	public void SelectPSIUnit() throws InterruptedException {
		try {
			Thread.sleep(2000);
			customwait.waitForAnElementPresence(
					By.xpath("//article[normalize-space()='Pressure Unit']/following::div[14]"));
			PSIUnit.click();
		} catch (Exception e) {
			System.out.println("Unable to click on PSI Radio button");
		}
	}

	public String getPSIMinSetPoint() throws InterruptedException {
		// customwait.waitForAnElementPresence(By.xpath(
		// "//div[contains(@class,'row
		// row-margin')]//span[contains(@class,'min')][normalize-space()='PSI']/preceding::input[1]"));
		// MinSetPoint.click();
		Thread.sleep(2000);
		// boolean bool = PSIMinSetPoint.isDisplayed();
		// System.out.println("Min Set Point for PSI text box is displayed=" + bool);
		String min = PSIMinSetPoint.getAttribute("value");
		System.out.println("The default Minimum Set Point value for PSI unit=" + min);
		return min;
	}

	public String getPSIMaxSetPoint() throws InterruptedException {
		Thread.sleep(2000);
		// boolean bool1 = PSIMaxSetPoint.isDisplayed();
		// System.out.println("Max Set Point for PSI text box is displayed=" + bool1);
		String max = PSIMaxSetPoint.getAttribute("value");
		System.out.println("The default Maximum Set Point value for PSI unit is =" + max);
		return max;
	}

	public boolean getPSIText() throws InterruptedException {
		Thread.sleep(2000);
		boolean psi = PSIText.isDisplayed();
		System.out.println("The PSI Text value is displayed=" + psi);
		return psi;
	}

	public void SelectHydaulicDropdown() throws InterruptedException {
		Thread.sleep(2000);
		SelectHydaulic.click();
		Thread.sleep(2000);
		SelectHydaulicValue.click();
		Thread.sleep(2000);
	}

	public boolean toastmessageDisplayed() {
		customwait.waitForAnElementPresence(ToastMessage);
		boolean tmsg = ToastMessage.isDisplayed();
		System.out.println("The toast message is displayed=" + tmsg);
		return tmsg;
	}

	public String getToastMessageText() {
		String tm = ToastMessage.getText();
		System.out.println("Value of the toast message is=" + tm);
		customwait.waitForAnElementIsInVisible(By.cssSelector("div#toast-container"));
		return tm;
	}

	public void CreatNewNORfile() throws InterruptedException {
		Thread.sleep(1000);
		clickSetUpToolLink();
		Thread.sleep(1000);
		clickCreateNewFile();
		Thread.sleep(1000);
		addDescription();
		Thread.sleep(1000);
		clickSubmit();
		Thread.sleep(1000);
	}

	public String getkPaMinSetPoint() throws InterruptedException {
		// customwait.waitForAnElementPresence(By.xpath(
		// "//div[contains(@class,'row
		// row-margin')]//span[contains(@class,'min')][normalize-space()='kPa']/preceding::input[1]"));
		// MinSetPoint.click();
		Thread.sleep(2000);
		boolean bool = MinSetPoint.isDisplayed();
		System.out.println("Min Set Point text box is displayed=" + bool);
		String min = MinSetPoint.getAttribute("value");
		Thread.sleep(2000);
		System.out.println("The default Minimum Set Point value for Kpa unit=" + min);
		return min;
	}

	public String getKpaMaxSetPoint() throws InterruptedException {
		// customwait.waitForAnElementPresence(By.xpath(
		// "//div[contains(@class,'row
		// display-flex-align-center')]//span[contains(@class,'min')][normalize-space()='kPa']/preceding::input[1]"));
		// MaxSetPoint.click();
		Thread.sleep(2000);
		boolean bool1 = MaxSetPoint.isDisplayed();
		System.out.println("Max Set Point text box is displayed=" + bool1);
		String max = MaxSetPoint.getAttribute("value");
		Thread.sleep(2000);
		System.out.println("The default Maximum Set Point value for Kpa unit=" + max);
		return max;
	}

//   Electronic Pressure adjust Pressure mode action methods
	public String getPressureSetPoint() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.xpath("//*[@name='ulPressureSetPoint']"));
		boolean PressureSetPnt = PressureSetPoint.isDisplayed();
		System.out.println("The PressureSetPoint Text value is displayed=" + PressureSetPnt);
		return PressureSetPoint.getAttribute("value");
	}

	public void clearPressureSetPoint() {
		PressureSetPoint.click();
		for (int i = 0; i <= 4; i++) {
			PressureSetPoint.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setPressureSetPoint(String Pressurestpnt) {
		PressureSetPoint.sendKeys(Pressurestpnt);
	}

	public String getLowPressureAlertThreshold() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.xpath("//*[@name='ulLowPressureAlertDelta']"));
		boolean PressureSetPnt = LowPressureAlertThreshold.isDisplayed();
		System.out.println("The LowPressureAlertThreshold Text value is displayed=" + PressureSetPnt);
		return LowPressureAlertThreshold.getAttribute("value");
	}

	public void clearLowPressureAlertThreshold() {
		LowPressureAlertThreshold.click();
		for (int i = 0; i <= 4; i++) {
			LowPressureAlertThreshold.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setLowPressureAlertThreshold(String lowPressureAlertThreshold) {
		LowPressureAlertThreshold.sendKeys(lowPressureAlertThreshold);
	}

	public String getHighPressureAlertThreshold() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.xpath("//*[@name='ulHighPressureAlertDelta']"));
		boolean PressureSetPnt = HighPressureAlertThreshold.isDisplayed();
		System.out.println("The HighPressureAlertThreshold Text value is displayed=" + PressureSetPnt);
		return HighPressureAlertThreshold.getAttribute("value");
	}

	public void clearHighPressureAlertThreshold() {
		HighPressureAlertThreshold.click();
		for (int i = 0; i <= 4; i++) {
			HighPressureAlertThreshold.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setHighPressureAlertThreshold(String highPressureAlertThreshold) {
		HighPressureAlertThreshold.sendKeys(highPressureAlertThreshold);
	}

	public String getMinimumPressureSetPointRange() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.xpath("//*[@name='ulLowPressureAlertThreshold']"));
		boolean PressureSetPnt = MinimumPressureSetPointRange.isDisplayed();
		System.out.println("The MinimumPressureSetPointRange Text value is displayed=" + PressureSetPnt);
		return MinimumPressureSetPointRange.getAttribute("value");
	}

	public void clearPressureMinimumSetPointRange() {
		MinimumPressureSetPointRange.click();
		for (int i = 0; i <= 4; i++) {
			MinimumPressureSetPointRange.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setMinimumPressureSetPointRange(String minimumSetPointRang) {
		MinimumPressureSetPointRange.sendKeys(minimumSetPointRang);
	}

	public String getMaximumPressureSetPointRange() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.xpath("//*[@name='ulHighPressureAlertThreshold']"));
		boolean PressureSetPnt = MaximumPressureSetPointRange.isDisplayed();
		System.out.println("The MaximumPressureSetPointRange Text value is displayed=" + PressureSetPnt);
		return MaximumPressureSetPointRange.getAttribute("value");
	}

	public void clearMaximumPressureSetPointRange() {
		MaximumPressureSetPointRange.click();
		for (int i = 0; i <= 3; i++) {
			MaximumPressureSetPointRange.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setMaximumPressureSetPointRange(String MaximumSetPointRange) {
		MaximumPressureSetPointRange.sendKeys(MaximumSetPointRange);
	}

	// Pressure mode selection
	public void SelectMainPressureModeSelectionDropdown(String txttobeselected) throws InterruptedException {
		customwait.waitForAnElementPresence(
				By.xpath("//*[@formcontrolname='selectPressure']//div[@class='mat-select-arrow']"));
		customwait.waitForAnElementClickable(
				By.xpath("//*[@formcontrolname='selectPressure']//div[@class='mat-select-arrow']"));
		MainPressureModeSelection.click();
		customwait.waitForAnElementPresence(By.xpath("//mat-option/span[contains(.,'" + txttobeselected + "')]"));
		ldriver.findElement(By.xpath("//mat-option/span[contains(.,'" + txttobeselected + "')]")).click();
	}

	public void clickAcceptalert() {
		customwait.waitForAnElementPresence(changesAlert);
		changesAlert.click();
	}

//  Runup Pressure mode action methods
	public String getLowSpeedPressureSetting() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.cssSelector("input[name=lowSpeedPresSett]"));
		boolean lowSpeedPressureSettings = LowSpeedPressureSettings.isDisplayed();
		System.out.println("The LowSpeedPressureSettings Text value is displayed=" + lowSpeedPressureSettings);
		return LowSpeedPressureSettings.getAttribute("value");
	}

	public void clearLowSpeedPressureSetting() {
		LowSpeedPressureSettings.click();
		for (int i = 0; i <= 3; i++) {
			LowSpeedPressureSettings.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setLowSpeedPressureSettings(String lowSpeedPressureSettings) {
		LowSpeedPressureSettings.sendKeys(lowSpeedPressureSettings);
	}

	public String getLowLineSpeedSetting() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.cssSelector("input[name='lowLineSpeedSett']"));
		boolean lowLineSpeedSetting = LowLineSpeedSetting.isDisplayed();
		System.out.println("The HighLineSpeedSettings Text value is displayed=" + lowLineSpeedSetting);
		return LowLineSpeedSetting.getAttribute("value");
	}

	public void clearLowLineSpeedSetting() {
		LowLineSpeedSetting.click();
		for (int i = 0; i <= 3; i++) {
			LowLineSpeedSetting.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setLowLineSpeedSettings(String lowLineSpeedSetting) {
		LowLineSpeedSetting.sendKeys(lowLineSpeedSetting);
	}

	public String getHighSpeedPressureSetting() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.cssSelector("input[name=highSpeedPresSett]"));
		boolean highSpeedPressureSettings = HighSpeedPressureSettings.isDisplayed();
		System.out.println("The HighSpeedPressureSettings Text value is displayed=" + highSpeedPressureSettings);
		return HighSpeedPressureSettings.getAttribute("value");
	}

	public void clearHighSpeedPressureSetting() {
		HighSpeedPressureSettings.click();
		for (int i = 0; i <= 3; i++) {
			HighSpeedPressureSettings.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setHighSpeedPressureSettings(String highSpeedPressureSettings) {
		HighSpeedPressureSettings.sendKeys(highSpeedPressureSettings);
	}

	public String getHighLineSpeedSetting() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.cssSelector("input[name=highLineSpeedSett]"));
		boolean highLineSpeedSett = HighLineSpeedSetting.isDisplayed();
		System.out.println("The HighLineSpeedSettings Text value is displayed=" + highLineSpeedSett);
		return HighLineSpeedSetting.getAttribute("value");
	}

	public void clearHighLineSpeedSetting() {
		HighLineSpeedSetting.click();
		for (int i = 0; i <= 3; i++) {
			HighLineSpeedSetting.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setHighLineSpeedSettings(String highLineSpeedSettings) {
		HighLineSpeedSetting.sendKeys(highLineSpeedSettings);
	}

	public String getSetMaximumPressureLimit() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.cssSelector("input[name='maxPressureLimit']"));
		boolean maxPressureLimit = SetMaximumPressurelimit.isDisplayed();
		System.out.println("The SetMaximumPressureLimits Text value is displayed=" + maxPressureLimit);
		return SetMaximumPressurelimit.getAttribute("value");
	}

	public void clearSetMaximumPressureLimit() {
		SetMaximumPressurelimit.click();
		for (int i = 0; i <= 3; i++) {
			SetMaximumPressurelimit.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setMaximumPressureLimit(String setMaximumPressureLimits) {
		SetMaximumPressurelimit.sendKeys(setMaximumPressureLimits);
	}

	public String getSetMinimumPressureLimit() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.cssSelector("input[name='minPressureLimit']"));
		boolean minPressureLimit = SetMinimumPressureLimit.isDisplayed();
		System.out.println("The minPressureLimit Text value is displayed=" + minPressureLimit);
		return SetMinimumPressureLimit.getAttribute("value");
	}

	public void clearSetMinimumPressureLimit() {
		SetMinimumPressureLimit.click();
		for (int i = 0; i <= 3; i++) {
			SetMinimumPressureLimit.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setMinimumPressureLimit(String setMinimumPressureLimit) {
		SetMinimumPressureLimit.sendKeys(setMinimumPressureLimit);
	}

	public String getFullScaleLineSpeed() throws InterruptedException {
		customwait.waitFortexttoBePresent(By.cssSelector("input[name='fullScaleLineSpeed']"));
		boolean fullScaleLineSpeed = FullScaleLineSpeed.isDisplayed();
		System.out.println("The FullScaleLineSpeeds Text value is displayed=" + fullScaleLineSpeed);
		return FullScaleLineSpeed.getAttribute("value");
	}

	public void clearFullScaleLineSpeed() {
		FullScaleLineSpeed.click();
		for (int i = 0; i <= 3; i++) {
			FullScaleLineSpeed.sendKeys(Keys.BACK_SPACE);
		}
	}

	public void setFullScaleLineSpeeds(String fullScaleLineSpeed) {
		FullScaleLineSpeed.sendKeys(fullScaleLineSpeed);
	}

	public void SelectLineSpeedftUnit() throws InterruptedException {
		try {
			customwait.waitForAnElementPresence(
					By.xpath("//mat-radio-button[not(contains(@class,'mat-radio-checked'))]//*[text()='ft/min']"));
			LineSpeedftmin.click();
		} catch (Exception e) {
			System.out.println("Unable to click on ft/min Line speed unit Radio button");
		}
	}

	public void SelectLineSpeedminUnit() throws InterruptedException {
		try {
			customwait.waitForAnElementPresence(
					By.xpath("//mat-radio-button[not(contains(@class,'mat-radio-checked'))]//*[text()='m/min']"));
			LineSpeedmmin.click();
		} catch (Exception e) {
			System.out.println("Unable to click on m/min Line speed unit Radio button");
		}
	}

	public void isDisplayedDownardSlopingRunupcurvecontent() {
		customwait.waitForAnElementPresence((By.cssSelector("div[class='logout-sub-head pad-tp']")));
		if (DownwardSlopingRunupContent.isDisplayed()) {
			clickAcceptalert();
			System.out.println("Downward Sloping Runup curve content is displayed");
		} else
			System.out.println("Downward Sloping Runup curve content is not displayed");
	}
}