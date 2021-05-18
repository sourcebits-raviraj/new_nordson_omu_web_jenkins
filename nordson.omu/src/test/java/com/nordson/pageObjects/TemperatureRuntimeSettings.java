package com.nordson.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.nordson.utilities.ActionMethods;


public class TemperatureRuntimeSettings {

	WebDriver ldriver;
	WebDriverWait wait;
	ActionMethods Am = new ActionMethods();

	// Constructor of the LoginPage to initiate driver
	public TemperatureRuntimeSettings(WebDriver rdriver) {
		// TODO Auto-generated constructor stub

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "bt")
	public WebElement SetUpToolButton;

	@FindBy(xpath = "//*[contains(text(),'CREATE NEW')]")
	public WebElement CreateNewButton;

	@FindBy(xpath = "//*[@class='btn-set-up submit-btn-color']")
	public WebElement SubmitButton;

	@FindBy(xpath = "//*[contains(text(),'System Settings')]/ancestor::span")
	public WebElement SystemSettings;
	
	@FindBy(xpath = "//*[contains(text(),'System Settings')]")
	public WebElement SystemSettingsLnk;
	
	@FindBy(xpath = "//*[contains(text(),'Preferences')]")
	public WebElement Preferences;

	@FindBy(xpath = "//*[@formcontrolname='TempUnits']//*[contains(@class,'mat-radio-checked')]//div[@class='mat-radio-container']")
	public List< WebElement> Temperatureunit;

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

	@FindBy(xpath = "//*[contains(@routerlink,'settings/temp-zone')]")
	public WebElement ZoneTemperature;

	@FindBy(xpath = "//*[contains(text(),'Global Setpoint')]/../following-sibling::tr/td//input")
	public WebElement GlobalSetPoint;

	@FindBy(xpath = "//*[@formcontrolname='globalSetPoint']")
	public WebElement MinmaxGlobalSetPoint;

	@FindBy(xpath = "//*[contains(text(),'Tank')]/../following-sibling::tr/td//input")
	public WebElement Tank;

	@FindBy(xpath = "//*[contains(text(),'Manifold')]/../following-sibling::tr/td//input")
	public WebElement Manifold;
	@FindBy(xpath = "//*[contains(text(),'Hose')]")
	List< WebElement> Hose;

	@FindBy(xpath = "//*[contains(text(),'Applicator')]")
	List< WebElement> Applicator;

	@FindBy(xpath = "//*[contains(@name,'hose')]")
	List< WebElement> HoseSetpoint;

	@FindBy(xpath = "//*[contains(@name,'applicator')]")
	List< WebElement> ApplicatorSetpoint;

	@FindBy(xpath = "//*[@name='hose1']")
	public WebElement Hose1;

	@FindBy(xpath = "//*[@name='applicator1']")
	public WebElement Applicator1;

	@FindBy(xpath = "//*[text()='Hose 1']/following-sibling::td//*[not(contains(@class,'mat-checked'))]//input[@aria-checked='false']/..")
	public WebElement HoseEnbbtn;

	@FindBy(xpath = "//*[text()='Applicator 1']/following-sibling::td//*[not(contains(@class,'mat-checked'))]//input[@aria-checked='false']/..")
	public WebElement ApplicatorEnbbtn;

	@FindBy(xpath = "//div[contains(@class,'toast-message ng-star-inserted')]")
	public WebElement Toastmsg;
	
	@FindBy(xpath = "//*[contains(text(),'DASHBOARD')]")
	public WebElement Dashboard;

	
public void clickDashboard() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'DASHBOARD')]"));
		Am.waitForAnElementToBeClickable(Dashboard);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", Dashboard);
		//Dashboard.click();
	}

	public void clickSetUpToolBtn() throws InterruptedException {
		Am.waitForAnElementPresence(By.xpath("//*[@id='bt']"));
		Am.waitForAnElementToBeClickable(SetUpToolButton);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SetUpToolButton);
		//SetUpToolButton.click();
	}

	public void clickCreateNewBtn() {
		Am.waitForAnElementPresence(CreateNewButton);
		Am.waitForAnElementToBeClickable(CreateNewButton);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", CreateNewButton);
		//CreateNewButton.click();
	}

	public void clickSubmitBtn() throws InterruptedException {
		Am.waitForAnElementPresence(SubmitButton);
		Am.waitForAnElementToBeClickable(SubmitButton);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SubmitButton);
		//SubmitButton.click();
	}
	public void clickSystemSettingsBtn() throws Exception {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'System Settings')]/ancestor::span"));
		Am.drawBorder(SystemSettingsLnk, ldriver);
		Am.waitForAnElementToBeClickable(SystemSettings);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SystemSettings);
		// SystemSettings.click();
	}

	public void clickPreferencesBtn() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'Preferences')]"));
		Am.waitForAnElementToBeClickable(Preferences);
		Preferences.click();
	}

	public Boolean getTemperatureunitstut() {
		Boolean tempvalue = false;
		if (Temperatureunit.size() == 1)
			tempvalue = true;
		else
			tempvalue = false;
		return tempvalue;
		// SystemSettings.click();

	}
	public String getSelectedTemperatureunit() {
		String tempunt;
		tempunt = SelectedTemperatureunit.getText();
		return tempunt;
	}
	public String getTemperatureunt() {
		String tempunt;
		Am.waitForAnElementPresence(Temperatureunt);
		tempunt = Temperatureunt.getText();
		return tempunt;
	}
	public void clickCelsiusUnit() {

		Am.waitForAnElementPresence(
				By.xpath("//*[@formcontrolname='TempUnits']//*[contains(text(),'C')]//preceding-sibling::div"));
		CelsiusTemperatureunit.click();
	}
	public void clickFarhenitUnit() {
		Am.waitForAnElementPresence(
				By.xpath("//*[@formcontrolname='TempUnits']//*[contains(text(),'F')]//preceding-sibling::div"));
		FarhenitTemperatureunit.click();
	}
	public void clickSave() {

		Am.waitForAnElementPresence(SAVE);
		Am.waitForAnElementToBeClickable(SAVE);
		SAVE.click();

	}

	public void clickSavebtn() {
		Am.waitForAnElementPresence(By.xpath("//*[@class='apply btn submit-bt']"));
		Am.waitForAnElementToBeClickable(SAVE1);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SAVE1);
		//SAVE1.click();

	}

	public void RuntimeSettingsBtn() throws InterruptedException {

		Am.waitForAnElementPresence(RuntimeSettings);
		Am.waitForAnElementToBeClickable(RuntimeSettings);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", RuntimeSettings);

		// RuntimeSettings.click();
	}

	public void ZoneTemperatureBtn() {

		Am.waitForAnElementPresence(ZoneTemperature);
		Am.waitForAnElementToBeClickable(ZoneTemperature);
		ZoneTemperature.click();

	}

	public String getGlobalSetPoint() {

		Am.waitForAnElementPresence(GlobalSetPoint);

		String globalSetPoint = GlobalSetPoint.getAttribute("value");

		return globalSetPoint;

	}

	public void setGlobalSetPoint(String globalpnt) {
		Am.waitForAnElementPresence(GlobalSetPoint);
		((JavascriptExecutor)ldriver).executeScript("arguments[0].scrollIntoView(true);",ldriver.findElement(By.xpath("(//*[contains(@class,'set-light-blue table-one')])[1]")));
		GlobalSetPoint.sendKeys(globalpnt);
	}

	public void MinmaxsetGlobalSetPoint(String globalpnt) {

		Am.waitForAnElementPresence(MinmaxGlobalSetPoint);
		MinmaxGlobalSetPoint.sendKeys(globalpnt);

	}

	public String getTankSetPoint() throws InterruptedException {

		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'Tank')]/../following-sibling::tr/td//input"));
		Am.waitFortexttoBePresent(By.xpath("//*[contains(text(),'Tank')]/../following-sibling::tr/td//input"));
		String tanksetpoint = Tank.getAttribute("value");
		return tanksetpoint;
	}

	public void setTankSetPoint(String tnkpnt) {
		Am.waitForAnElementPresence(Tank);
		Tank.sendKeys(tnkpnt);

	}

	public void setManifoldSetPoint(String Mnfld) {
		Am.waitForAnElementPresence(Manifold);
		Manifold.sendKeys(Mnfld);

	}
	public String getManifold() throws InterruptedException {

		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'Manifold')]/../following-sibling::tr/td//input"));
		Am.waitFortexttoBePresent(By.xpath("//*[contains(text(),'Manifold')]/../following-sibling::tr/td//input"));
		String manifold = Manifold.getAttribute("value");
		return manifold;
	}

	public List<Integer> getHoseEnableStatus() throws InterruptedException {
		String hose = "";
		int hosewebelcunt = 0;
		List<Integer> hosecuntele = new ArrayList<Integer>();

		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'Hose')]"));

		for (int i = 1; i <= Hose.size(); i++) {
			hose = "//*[text()='Hose " + i + "']/following-sibling::td//*[not(contains(@class,'mat-checked'))]//input[@aria-checked='false']";
			Thread.sleep(1500);
			hosewebelcunt = ldriver.findElements(By.xpath(hose)).size();
			hosecuntele.add(hosewebelcunt);
		}

		return hosecuntele;
	}

	public List<Integer> getApplicatorEnableStatus() throws InterruptedException {
		String applicator = "";
		int applicatorWebenblcunt = 0;
		List<Integer> applicatorlscunt = new ArrayList<Integer>();
		for (int i = 1; i <= Applicator.size(); i++) {
			applicator = "//*[text()='Applicator " + i
					+ "']/following-sibling::td//*[not(contains(@class,'mat-checked'))]//input[@aria-checked='false']";
			Thread.sleep(1500);
			applicatorWebenblcunt = ldriver.findElements(By.xpath(applicator)).size();
			applicatorlscunt.add(applicatorWebenblcunt);

		}
		return applicatorlscunt;
	}

	public List<String> getHoseSetTemp() throws Exception {
		String hosesetpnt = "";
		List<String> Hosesetpointslst = new ArrayList<String>();

		for (int i = 1; i <= HoseSetpoint.size(); i++) {
			hosesetpnt = "//*[@name='hose" + i + "']";
		    WebElement el = ldriver.findElement(By.xpath(hosesetpnt));
			Am.waitFortexttoBePresent(By.xpath(hosesetpnt));
			Am.sleepTime(2200);
			if(el.isDisplayed()==true) {
				((JavascriptExecutor)ldriver).executeScript("arguments[0].scrollIntoView(true);",el);
				Am.drawBorder(el, ldriver);}
				else
					Am.drawBorderFail(el, ldriver);
			String hosesettemppnt = el.getAttribute("value");
			Hosesetpointslst.add(hosesettemppnt);
		}

		return Hosesetpointslst;
	}

	public List<String> getApplicatorSetTemp() throws Exception {
		String applicatorsetpnt = "";
		List<String> Applicatorsetpointslst = new ArrayList<String>();

		for (int i = 1; i <= Applicator.size(); i++) {
			applicatorsetpnt = "//*[@name='applicator" + i + "']";
			 WebElement el = ldriver.findElement(By.xpath(applicatorsetpnt));
			Am.waitFortexttoBePresent(By.xpath(applicatorsetpnt));
			Am.sleepTime(2200);
			if(el.isDisplayed()==true) {
				((JavascriptExecutor)ldriver).executeScript("arguments[0].scrollIntoView(true);",el);
				Am.drawBorder(el, ldriver);
			}
				else
					Am.drawBorderFail(el, ldriver);
			String hosesettemppnt = el.getAttribute("value");
			Applicatorsetpointslst.add(hosesettemppnt);
		}
		return Applicatorsetpointslst;
	}

	public String getHosetemp1()
	{
		Am.waitFortexttoBePresent(By.xpath("//*[@name='hose1']"));	
		String hsettemp = Hose1.getAttribute("value");
		return hsettemp;
	}

	public String getAPP1temp1()

	{
		Am.waitFortexttoBePresent(By.xpath("//*[@name='applicator1']"));
		String appl1 = Applicator1.getAttribute("value");
		return appl1;

	}

	public void clearTanktemperature() {

		Am.waitForAnElementPresence(Tank);
		Tank.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}
	public void clearManifoldtemperature() {

		Am.waitForAnElementPresence(Manifold);
		Manifold.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public void clearHosesetTemperature() {

		Am.waitForAnElementPresence(Hose1);
		Hose1.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	public void clearApplicator1setTemperature() {

		Am.waitForAnElementPresence(Applicator1);
		Applicator1.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));

	}

	public void setHose1emperature(String hosetemp) {

		Am.waitForAnElementPresence(Hose1);
		Hose1.sendKeys(hosetemp);

	}

	public void setApplicator1temperature(String Appl1temp) {

		Am.waitForAnElementPresence(Applicator1);
		Applicator1.sendKeys(Appl1temp);

	}
	public Boolean getSavebtnstatus() {
		Am.waitForAnElementPresence(SAVE1);
		Boolean sttus = SAVE1.isEnabled();
		return sttus;
	}

	public void clickOnHose1toEnable() throws InterruptedException {

		List<Integer> hosestuts = getHoseEnableStatus();
		int hose1ele = hosestuts.get(0);

		if (hose1ele == 1) {

			HoseEnbbtn.click();
		}
	}

	public void clickApplicator1toEnable() throws InterruptedException {

		List<Integer> hosestuts = getApplicatorEnableStatus();
		int Applicator1 = hosestuts.get(0);

		if (Applicator1 == 1)

		{
			Am.waitForAnElementPresence(ApplicatorEnbbtn);

			Am.waitForAnElementToBeClickable(ApplicatorEnbbtn);

			ApplicatorEnbbtn.click();
		}

	}

	public String getToastmsg() {
		    
			String toastmsg = Toastmsg.getText();
			System.err.println("Value of toast message is"+toastmsg);
			Am.waitForAnElementIsInVisible(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
		return toastmsg;
	}

	public Boolean getToastmsgststus() {
		Am.waitForAnElementPresence(By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]"));
		return Toastmsg.isDisplayed();
	}

	public void clickCelsiusTempUnit() {

		Boolean tempvalue = getTemperatureunitstut();

		if (tempvalue == true) {

			String tempunt = getSelectedTemperatureunit();
			if (!(tempunt.equalsIgnoreCase("oC"))) {
				clickCelsiusUnit();

			}
		} else
			System.out.println("Temperature already selected");
	}

	public void clickFarnheitTempUnit() {

		Boolean tempvalue = getTemperatureunitstut();

		if (tempvalue == true) {
			String tempunt = getSelectedTemperatureunit();
			if (!(tempunt.equalsIgnoreCase("oF"))) {
				clickFarhenitUnit();

			}
		} else
			System.out.println("Temperature not selected");
	}

	public void gethosesstatus() throws InterruptedException {

		int count = 1;
		List<Integer> hoseEnableststus = getHoseEnableStatus();

		for (int i = 0; i < hoseEnableststus.size(); i++) {
			if (hoseEnableststus.get(i) > 0) {
				System.out.println("Hose" + count + "set point temperature is disabled by default val");
			} else

				System.out.println("Hose" + count + "set point temperature is not disabled by default val");
			count++;
		}

	}

	public void getApplicatorsstatus() throws InterruptedException {
		int countas = 1;
		List<Integer> applicatorEnableststus = getApplicatorEnableStatus();

		for (int i = 0; i < applicatorEnableststus.size(); i++) {
			if (applicatorEnableststus.get(i) > 0) {
				System.out.println("Applicator" + countas + " Set point is disbaled by default Val");
			} else
				Assert.fail("Applicator" + countas + "Set point is enabled and not disabled by default Val");
			countas++;
		}

	}

	public void getHosesSettempStatus(String defaulttemp) throws Exception {
		List<String> hosestpnt = getHoseSetTemp();
		int hosestpntsz = hosestpnt.size();
		int count = 1;
		for (int i = 0; i < hosestpntsz; i++) {
			if (hosestpnt.get(i).equals(defaulttemp)) {
				System.out.println("Hose" + count + "set point temperature set to " + hosestpnt.get(i) + " value");
			} else
				System.out.println("Hose" + count + "set point temperature not set" + hosestpnt.get(i) + " value ");
			count++;
		}
	}

	public void getApplicatorsSettempStatus(String defsettemp) throws Exception {
		List<String> applicatorsetpnt = getApplicatorSetTemp();
		int count = 1;
		for (int i = 0; i < applicatorsetpnt.size(); i++) {
			if (applicatorsetpnt.get(i).equals(defsettemp)) {
				System.out.println("Applicator" + count + "set point temperature set to " + applicatorsetpnt.get(i));
			} else

				System.out
						.println("Applicator" + count + "set point temperature not set to " + applicatorsetpnt.get(i));
			count++;
		}
	}
	public void setHosestemp(String hosesettemp) throws InterruptedException {
		String hose="";
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'Hose')]"));
		for (int i = 1; i <= Hose.size(); i++) {
			hose = "//*[text()='Hose " + i
					+ "']/following-sibling::td//*[not(contains(@class,'mat-checked'))]//input[@aria-checked='false']/..";
			String hosesetbx = "//*[@name='hose" + i + "']";
			if(ldriver.findElements(By.xpath(hose)).size()==1)
				((JavascriptExecutor)ldriver).executeScript("arguments[0].click();", ldriver.findElement(By.xpath(hose)));
			//ldriver.findElement(By.xpath(hose)).click();
			
			else
				System.out.println("hose"+i+"already enabled");
				ldriver.findElement(By.xpath(hosesetbx)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				ldriver.findElement(By.xpath(hosesetbx)).sendKeys(hosesettemp);
		}
	}

	public void setApplicatorstemp(String appsettemp) throws InterruptedException {
		String Applcator="";
		for (int i = 1; i <= Applicator.size(); i++) {
			String Applcatorsettmp = "//*[@name='applicator" + i + "']";
			Applcator = "//*[text()='Applicator " + i
					+ "']/following-sibling::td//*[not(contains(@class,'mat-checked'))]//input[@aria-checked='false']/..";
			if(ldriver.findElements(By.xpath(Applcator)).size()==1)
				((JavascriptExecutor)ldriver).executeScript("arguments[0].click();", ldriver.findElement(By.xpath(Applcator)));
				//ldriver.findElement(By.xpath(Applcator)).click();
			else
				System.out.println("applicator"+i+"already enabled");
				
			ldriver.findElement(By.xpath(Applcatorsettmp)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				ldriver.findElement(By.xpath(Applcatorsettmp)).sendKeys(appsettemp);
		}
		
	}

	public void createNewNORfile() throws Exception {
		clickSetUpToolBtn();
		Am.sleepTime(1000);
		clickCreateNewBtn();
		Am.sleepTime(1000);
		clickSubmitBtn();
		Am.sleepTime(1000);
	}


}
