package com.nordson.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.utilities.ActionMethods;

public class TemperatureSystemSettings {

	WebDriver ldriver;
	WebDriverWait wait;
	ActionMethods Am = new ActionMethods();

	// Constructor of the LoginPage to initiate driver
	public TemperatureSystemSettings(WebDriver rdriver) {
		// TODO Auto-generated constructor stub

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "bt")
	public WebElement SetUpToolButton;

	@FindBy(xpath = "//div[contains(text(),'CREATE NEW')]")
	public WebElement CreateNewButton;

	@FindBy(xpath = "//*[@class='btn-set-up submit-btn-color']")

	public WebElement SubmitButton;

	@FindBy(xpath = "//*[contains(text(),'System Settings')]/ancestor::span")

	public WebElement SystemSettings;

	@FindBy(xpath = "//*[contains(text(),'Preferences')]")

	public WebElement Preferences;

	@FindBy(xpath = "//*[@formcontrolname='TempUnits']//*[contains(@class,'mat-radio-checked')]//div[@class='mat-radio-container']")

	List<WebElement> Temperatureunit;

	@FindBy(xpath = "//*[@formcontrolname='TempUnits']/*[contains(@class,'mat-radio-checked')]//div[@class='mat-radio-label-content']")

	public WebElement SelectedTemperatureunit;

	@FindBy(xpath = "//*[@formcontrolname='TempUnits']//*[contains(text(),'C')]//preceding-sibling::div")

	public WebElement CelsiusTemperatureunit;

	@FindBy(xpath = "//*[@formcontrolname='TempUnits']//*[contains(text(),'F')]//preceding-sibling::div")

	public WebElement FarhenitTemperatureunit;

	@FindBy(xpath = "(//span[@class='min'])[1]")
	public WebElement Temperatureunt;

	@FindBy(xpath = "//*[@class='btn apply']")

	public WebElement SAVE;

	@FindBy(xpath = "//*[@class='apply btn submit-bt']")

	public WebElement SAVE1;

	@FindBy(xpath = "//*[contains(text(),'Runtime settings')]")
	public WebElement RuntimeSettings;

	@FindBy(xpath = "//*[contains(@routerlink,'settings/temperaturesettings')]")
	public WebElement Temperature;

	@FindBy(xpath = "//*[@formcontrolname='OTDelta']")
	public WebElement OTTemperature;

	@FindBy(xpath = "//*[@formcontrolname='OTDelta']/../span")
	public WebElement OTTemperatureunt;

	@FindBy(xpath = "//*[@formcontrolname='UTDelta']")
	public WebElement UTTemperature;

	@FindBy(xpath = "//*[@formcontrolname='UTDelta']/../span")
	public WebElement UTTemperatureunt;

	@FindBy(xpath = "//*[@formcontrolname='StandbyDelta']")
	public WebElement TemperatureSetback;

	@FindBy(xpath = "//*[@formcontrolname='StandbyDelta']/../span")
	public WebElement Tmpstbckemperatureunt;

	@FindBy(xpath = "//div[contains(@class,'toast-message ng-star-inserted')]")

	public WebElement Toastmsg;

	@FindBy(xpath = "//*[@formcontrolname='SmartMeltTime']")
	public WebElement SmrtMeltTmeDly;

	@FindBy(xpath = "//*[@formcontrolname='AutoHeatersOffTime']")
	public WebElement AutoHterOffTme;

	@FindBy(xpath = "//*[@formcontrolname='AutoStandbyTime']")
	public WebElement SystemsetBckDly;

	@FindBy(xpath = "//*[@formcontrolname='SmartMeltTime']//../following-sibling::span")
	public WebElement SmrtMeltTmeDlyunt;

	@FindBy(xpath = "//*[@formcontrolname='AutoHeatersOffTime']//../following-sibling::span")
	public WebElement AutoHterOffTmeunt;

	@FindBy(xpath = "//*[@formcontrolname='AutoStandbyTime']//../following-sibling::span")
	public WebElement SystemsetBckDlyunt;

	@FindBy(xpath = "//*[contains(text(),'DASHBOARD')]")
	public WebElement Dashboard;

	public void clickDashboard() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'DASHBOARD')]"));
		Am.waitForAnElementToBeClickable(Dashboard);
		Dashboard.click();
	}

	public void clickCreateNewBtn() {

		Am.waitForAnElementPresence(By.xpath("//div[contains(text(),'CREATE NEW')]"));
		Am.waitForAnElementPresence(CreateNewButton);
		Am.waitForAnElementToBeClickable(CreateNewButton);
		JavascriptExecutor executor = (JavascriptExecutor) ldriver;
		executor.executeScript("arguments[0].click();", CreateNewButton);
	}

	public void clickSubmitBtn() throws InterruptedException {
		Thread.sleep(1500);
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

	public void clickPreferencesBtn() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'Preferences')]"));
		Am.waitForAnElementToBeClickable(Preferences);
		Preferences.click();
	}

	public Boolean getTemperatureunitstut() {
		Boolean tempvalue = false;
		Am.waitForAnElementPresence(By.xpath(
				"//*[@formcontrolname='TempUnits']//*[contains(@class,'mat-radio-checked')]//div[@class='mat-radio-container']"));
		if (Temperatureunit.size() == 1)
			tempvalue = true;
		else
			tempvalue = false;
		return tempvalue;
	}

	public String getSelectedTemperatureunit() {
		String tempunt;
		tempunt = SelectedTemperatureunit.getText();
		return tempunt;
	}

	public String getTemperatureunt() {
		String tempunt;
		tempunt = Temperatureunt.getText();
		return tempunt;

	}

	public String getOTTTemperatureunt() {
		String Ottempunt;
		Ottempunt = OTTemperatureunt.getText();
		return Ottempunt;

	}

	public String getUTTemperatureunt() {
		String Uttempunt;
		Uttempunt = UTTemperatureunt.getText();
		return Uttempunt;

	}

	public String getTempsetbckTemperatureunt() {
		String tempstbcktempunt;
		tempstbcktempunt = UTTemperatureunt.getText();
		return tempstbcktempunt;
	}

	public void clickCelsiusUnit() {

		Am.waitForAnElementPresence(
				By.xpath("//*[@formcontrolname='TempUnits']//*[contains(text(),'C')]//preceding-sibling::div"));
		Am.waitForAnElementToBeClickable(CelsiusTemperatureunit);
		CelsiusTemperatureunit.click();

	}

	public void clickFahrenheit() {
		Am.waitForAnElementPresence(
				By.xpath("//*[@formcontrolname='TempUnits']//*[contains(text(),'F')]//preceding-sibling::div"));
		Am.waitForAnElementToBeClickable(CelsiusTemperatureunit);
		FarhenitTemperatureunit.click();
	}

	public void clickFarhenitUnit() {

		Am.waitForAnElementPresence(
				By.xpath("//*[@formcontrolname='TempUnits']//*[contains(text(),'F')]//preceding-sibling::div"));
		FarhenitTemperatureunit.click();
	}

	public void clickSave() {

		Am.waitForAnElementPresence(By.xpath("//*[@class='btn apply']"));
		Am.waitForAnElementPresence(SAVE);
		Am.waitForAnElementToBeClickable(SAVE);
		SAVE.click();

	}

	public void clickSavebtn() {
		// Am.waitForAnElementPresence(SAVE1);
		// Am.waitForAnElementPresence(By.xpath("//*[@class='apply btn submit-bt']"));
		// Am.waitForAnElementToBeClickable(SAVE1);
		SAVE1.click();

	}

	public void clickSetUpToolBtn() {
		Am.waitForAnElementPresence(SetUpToolButton);
		Am.waitForAnElementPresence(By.id("bt"));
		Am.waitForAnElementToBeClickable(SetUpToolButton);
		SetUpToolButton.click();
	}

	public void RuntimeSettingsBtn() throws InterruptedException {

		Am.waitForAnElementPresence(RuntimeSettings);
		Am.waitForAnElementToBeClickable(RuntimeSettings);
		JavascriptExecutor executor = (JavascriptExecutor) ldriver;
		executor.executeScript("arguments[0].click();", RuntimeSettings);
		// RuntimeSettings.click();
	}

	public void clickTemperatureBtn() {
		Am.waitForAnElementPresence(Temperature);
		Am.waitForAnElementPresence(By.xpath("//*[contains(@routerlink,'settings/temperaturesettings')]"));
		Am.waitForAnElementToBeClickable(Temperature);
		Temperature.click();
	}

	public void setOTTemperature(String OTTtemp) {

		Am.waitForAnElementPresence(OTTemperature);
		OTTemperature.sendKeys(OTTtemp);

	}

	public void setUTTemperature(String UTTtemp) {

		Am.waitForAnElementPresence(UTTemperature);
		UTTemperature.sendKeys(UTTtemp);

	}

	public void setSetbckTemperature(String tempstbck) {
		Am.waitForAnElementPresence(TemperatureSetback);
		TemperatureSetback.sendKeys(tempstbck);

	}

	public String getOTTemperature() throws InterruptedException {

		Am.waitForAnElementPresence(By.xpath("//*[@formcontrolname='OTDelta']"));
		Am.waitFortexttoBePresent(By.xpath("//*[@formcontrolname='OTDelta']"));
		String OTTemp = OTTemperature.getAttribute("value");
		return OTTemp;
	}

	public String getUTTemperature() throws InterruptedException {

		Am.waitForAnElementPresence(UTTemperature);
		Am.waitFortexttoBePresent(By.xpath("//*[@formcontrolname='UTDelta']"));
		String UTTemp = UTTemperature.getAttribute("value");
		return UTTemp;
	}

	public String getTemperatureSetback() throws InterruptedException {

		Am.waitForAnElementPresence(TemperatureSetback);
		Am.waitFortexttoBePresent(By.xpath("//*[@formcontrolname='StandbyDelta']"));
		String TempSB = TemperatureSetback.getAttribute("value");
		return TempSB;
	}

	public void clearOTTemperature() {

		Am.waitForAnElementPresence(OTTemperature);

		OTTemperature.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	public void clearUTTemperature() {

		Am.waitForAnElementPresence(UTTemperature);
		UTTemperature.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	public void clearTemperaturesetbck() {

		Am.waitForAnElementPresence(TemperatureSetback);
		TemperatureSetback.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	public Boolean getSavebtnstatus() {
		Am.waitForAnElementPresence(SAVE1);
		Boolean sttus = SAVE1.isEnabled();
		return sttus;
	}

	public Boolean getToastmsgststus() {
		Boolean toastmsg = false;
		Am.waitForAnElementPresence(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
		if (Toastmsg.isDisplayed()) {
			toastmsg = true;
		} else
			toastmsg = false;
		return toastmsg;
	}

	public String getToastmsg() {

		Am.waitForAnElementPresence(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
		String toastmsg = "";
		if (Toastmsg.isDisplayed()) {
			toastmsg = Toastmsg.getText();
			Am.waitForAnElementIsInVisible(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));

		} else
			System.out.println("toast msg not displayed");

		return toastmsg;
	}

	public void clickCelsiusTempUnit() {
		Boolean tempvalue = getTemperatureunitstut();
		if (tempvalue == true) {
			String tempunt = getSelectedTemperatureunit();
			if (!(tempunt.equalsIgnoreCase("oC"))) {
				clickCelsiusUnit();

			}
		} else
			System.out.println("Temperature not selected");
	}

	public void clickFahrenheitTempUnit() {
		Boolean tempvalue = getTemperatureunitstut();

		if (tempvalue == true) {
			String tempunt = getSelectedTemperatureunit();
			if (!(tempunt.equalsIgnoreCase("oF"))) {
				clickFahrenheit();
			} else
				System.out.println("Temperature not selected");
		}
	}

	public String getSmartMeltTimeDly() throws InterruptedException {

		Am.waitForAnElementPresence(SmrtMeltTmeDly);
		Am.waitFortexttoBePresent(By.xpath("//*[@formcontrolname='SmartMeltTime']"));
		String smartmelttmdly = SmrtMeltTmeDly.getAttribute("value");
		return smartmelttmdly;
	}

	public String getAutoHeatersOffTme() throws InterruptedException {

		Am.waitForAnElementPresence(AutoHterOffTme);
		Am.waitFortexttoBePresent(By.xpath("//*[@formcontrolname='AutoHeatersOffTime']"));
		String autohtrofftme = AutoHterOffTme.getAttribute("value");
		return autohtrofftme;
	}

	public String getSystemSetbackTmeDly() throws InterruptedException {

		Am.waitForAnElementPresence(SystemsetBckDly);
		Am.waitFortexttoBePresent(By.xpath("//*[@formcontrolname='AutoStandbyTime']"));
		String systemsetbckdly = SystemsetBckDly.getAttribute("value");
		return systemsetbckdly;
	}

	public void clearSmartMeltTimeDly() {
		Am.waitForAnElementPresence(SmrtMeltTmeDly);
		SmrtMeltTmeDly.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public void clearAutoHeatersOffTme() {
		Am.waitForAnElementPresence(AutoHterOffTme);
		AutoHterOffTme.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public void clearSystemsetbckTmeDly() {
		Am.waitForAnElementPresence(SystemsetBckDly);
		SystemsetBckDly.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public void setSmartMeltTimeDly(String smarttimedly) {
		Am.waitForAnElementPresence(SmrtMeltTmeDly);
		SmrtMeltTmeDly.sendKeys(smarttimedly);
	}

	public void setAutoHeatersOffTme(String autohtrofftme) {
		Am.waitForAnElementPresence(AutoHterOffTme);
		AutoHterOffTme.sendKeys(autohtrofftme);
	}

	public void setSystemsetbckTmeDly(String Systmsetbckdly) {
		Am.waitForAnElementPresence(SystemsetBckDly);
		SystemsetBckDly.sendKeys(Systmsetbckdly);
	}

	public String getSmartMeltTimeDlyunt() {
		String smartmlttimedly;
		smartmlttimedly = SmrtMeltTmeDlyunt.getText();
		return smartmlttimedly;
	}

	public String getSystemsetbckdlyunt() {
		String systmsetbckdly;
		systmsetbckdly = SystemsetBckDlyunt.getText();
		return systmsetbckdly;
	}

	public String getAutoheaterofftimeunt() {
		String AutoHterOffTimeunt;
		AutoHterOffTimeunt = AutoHterOffTmeunt.getText();
		return AutoHterOffTimeunt;
	}

	public void createNewNORfile() throws InterruptedException {
		clickSetUpToolBtn();
		Thread.sleep(1000);
		clickCreateNewBtn();
		Thread.sleep(1000);
		clickSubmitBtn();
	}

}
