package com.nordson.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordson.utilities.ActionMethods;

public class Flow_System_Settings {
	
	WebDriver ldriver;
	WebDriverWait wait;
	ActionMethods Am = new ActionMethods();

	// Constructor of the LoginPage to initiate driver
	public Flow_System_Settings(WebDriver rdriver) {
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
	
	@FindBy(xpath = "//*[@routerlink='flow']/div")
	WebElement FlowSystemsettingsbtn;
	
	@FindBy(css ="*[formcontrolname='ATSTargetAddon']")
	WebElement TargetAddOn;
	
	@FindBy(css = "*[class='apply btn']")
	WebElement SAVE;
	
	@FindBy(xpath = "//*[@routerlink='flow-runtime']/div")
	WebElement Flowruntimesettingsbtn;
	
	@FindBy(css = "*[name='ATSAlertLowerLimit']")
	WebElement LowAlertThreshold;
	
	@FindBy(css = "*[name='ATSAlertUpperLimit']")
	WebElement HighAlertThreshold;

	@FindBy(css = "*[name='ATSAlertDelay']")
	WebElement AlertDelayCount;
	
	@FindBy(css = "*[name='ATSFaultLowerLimit']")
	WebElement LowStopThreshold;
	
	@FindBy(css = "*[name='ATSFaultUpperLimit']")
	WebElement HighStopThreshold;
	
	@FindBy(css = "*[name='ATSFaultDelay']")
	WebElement StopDelayCount;
	
	@FindBy(css = "*[name='ATSSpecificGravity']")
	WebElement SpecificGravity;
	
	@FindBy(css = "*[name='ATSKFactor']")
	WebElement CallibrationConstantSetting;
	
	@FindBy(css = "*[name='ATSNumProdToSkip']")
	WebElement ProductSkipCount;
	
	@FindBy(css = "*[name='ATSNumProdToAverage']")
	WebElement ProductToAverage;

	@FindBy(css = "*[name='ATSProdSkipTime']")
	WebElement IdleSkipTime;
	
	@FindBy(css = "*[name='ATSStartupSkipCount']")
	WebElement StartUpSkipCount;
	
	
	public void clickDashboard() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'DASHBOARD')]"));
		Am.waitForAnElementToBeClickable(Dashboard);
		Dashboard.click();
	}
	public void clickSetUpToolBtn() {
		Am.waitForAnElementPresence(By.id("bt"));
		//Am.waitForAnElementToBeClickable(SetUpToolButton);
		((JavascriptExecutor)ldriver).executeScript("window.scrollTo(0,"+Am.getYcoordinatetoclick(SetUpToolButton)+")");
		SetUpToolButton.click();
	}
	public void clickCreateNewBtn() {
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'CREATE NEW')]"));
		Am.waitForAnElementToBeClickable(CreateNewButton);
		JavascriptExecutor executor = (JavascriptExecutor) ldriver;
		executor.executeScript("arguments[0].click();", CreateNewButton);
	}
	public void clickSubmitBtn() throws InterruptedException {
		Am.waitForAnElementPresence(By.xpath("//*[@class='btn-set-up submit-btn-color']"));
		//Am.waitForAnElementToBeClickable(SubmitButton);
		((JavascriptExecutor)ldriver).executeScript("window.scrollTo(0,"+Am.getYcoordinatetoclick(SubmitButton)+")");
		SubmitButton.click();
	}
	
	public void clickSystemSettingsBtn() {
		
		Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'System Settings')]/ancestor::span"));
		Am.waitForAnElementToBeClickable(SystemSettings);
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SystemSettings);
	
	}
	
	public void clickFlowRuntimesettingsbtn() throws InterruptedException {
		Am.waitForAnElementPresence(By.xpath("//*[@routerlink='flow-runtime']/div"));
		Thread.sleep(800);
		Am.waitForAnElementToBeClickable(Flowruntimesettingsbtn);
		//Flowruntimesettingsbtn.click();
		((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", Flowruntimesettingsbtn);
	}
	
	public void clickFlowSystemsettingsbtn() throws InterruptedException {
		Am.waitForAnElementPresence(By.xpath("//*[@routerlink='flow']/div"));
		Thread.sleep(800);
		Am.waitForAnElementToBeClickable(FlowSystemsettingsbtn);
		FlowSystemsettingsbtn.click();
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
   
   public void clearLowAlertThreshold() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertLowerLimit']"));
		LowAlertThreshold.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setLowAlertThreshold(String lowalrtthshld) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertLowerLimit']"));
	   LowAlertThreshold.sendKeys(lowalrtthshld);
	}
   public String getLowAlertThreshold() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertLowerLimit']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSAlertLowerLimit']"));
		return LowAlertThreshold.getAttribute("value");
	}
   
   public void clearHighAlertThreshold() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertUpperLimit']"));
	   HighAlertThreshold.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setHighAlertThreshold(String highalrtthsld) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertUpperLimit']"));
	   HighAlertThreshold.sendKeys(highalrtthsld);
	}
   public String getHighAlertThreshold() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertUpperLimit']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSAlertUpperLimit']"));
		return HighAlertThreshold.getAttribute("value");}

   public void clearAlertDelayCount() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertDelay']"));
	   AlertDelayCount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setAlertDelayCount(String alrtdlycnt) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertDelay']"));
	   AlertDelayCount.sendKeys(alrtdlycnt);
	}
   public String getAlertDelayCount() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSAlertDelay']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSAlertDelay']"));
		return AlertDelayCount.getAttribute("value");
	}
   
   public void clearLowStopThreshold() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultLowerLimit']"));
		LowStopThreshold.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setLowStopThreshold(String lowstpthsld) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultLowerLimit']"));
	   LowStopThreshold.sendKeys(lowstpthsld);
	}
   public String getLowStopThreshold() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultLowerLimit']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSFaultLowerLimit']"));
		return LowStopThreshold.getAttribute("value");
	}
   
   public void clearHighStopThreshold() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultUpperLimit']"));
		HighStopThreshold.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setHighStopThreshold(String highstpthsld) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultUpperLimit']"));
	   HighStopThreshold.sendKeys(highstpthsld);
	}
   public String getHighStopThreshold() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultUpperLimit']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSFaultUpperLimit']"));
		return HighStopThreshold.getAttribute("value");
	}
   
   public void clearStopDelayCount() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultDelay']"));
	   StopDelayCount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setStopDelayCount(String alrtdlycnt) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultDelay']"));
	   StopDelayCount.sendKeys(alrtdlycnt);
	}
   public String getStopDelayCount() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSFaultDelay']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSFaultDelay']"));
		return StopDelayCount.getAttribute("value");
	}
   
   public void clearSpecificgravity() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSSpecificGravity']"));
	   SpecificGravity.click();
		for (int i = 0; i <= 6; i++) {
			SpecificGravity.sendKeys(Keys.BACK_SPACE);
		}
   }	
   public void setSpecificGravity(String specificGravity) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSSpecificGravity']"));
	   SpecificGravity.sendKeys(specificGravity);
	}
   public String getSpecificGravity() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSSpecificGravity']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSSpecificGravity']"));
		return SpecificGravity.getAttribute("value");
	}
   
   public void clearCalibrationConstantSetting() throws Exception {
	   
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSKFactor']"));
	  // CallibrationConstantSetting.click();
	   Am.sleepTime(1800);
		for (int i = 0; i <= 8; i++) {
			CallibrationConstantSetting.sendKeys(Keys.BACK_SPACE);
		}
	  // CallibrationConstantSetting.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setCalibrationConstantSetting(String calibrationConstantSetting) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSKFactor']"));
	   CallibrationConstantSetting.sendKeys(calibrationConstantSetting);
	}
   public String getCalibrationConstantSetting() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSKFactor']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSKFactor']"));
		return CallibrationConstantSetting.getAttribute("value");
	}
   
   public void clearProductSkipCount() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSNumProdToSkip']"));
	   ProductSkipCount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setProductSkipCount(String productSkipCount) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSNumProdToSkip']"));
	   ProductSkipCount.sendKeys(productSkipCount);
	}
   public String getProductSkipCount() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSNumProdToSkip']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSNumProdToSkip']"));
		return ProductSkipCount.getAttribute("value");
	}
   
   public void clearProductstoAverage() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSNumProdToAverage']"));
	   ProductToAverage.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setProductstoAverage(String productstoAverage) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSNumProdToAverage']"));
	   ProductToAverage.sendKeys(productstoAverage);
	}
   public String getProductstoAverage() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSNumProdToAverage']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSNumProdToAverage']"));
		return ProductToAverage.getAttribute("value");
	}
   
   public void clearIdleSkipTime() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSProdSkipTime']"));
	   IdleSkipTime.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setIdleSkipTime(String idleSkipTime) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSProdSkipTime']"));
	   IdleSkipTime.sendKeys(idleSkipTime);
	}
   public String getIdleSkipTime() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSProdSkipTime']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSProdSkipTime']"));
		return IdleSkipTime.getAttribute("value");
	}
   public void clearStartupSkipCount() {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSStartupSkipCount']"));
	   StartUpSkipCount.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}	
   public void setStartupSkipCount(String startupSkipCount) {
	   Am.waitForAnElementPresence(By.cssSelector("*[name='ATSStartupSkipCount']"));
	   StartUpSkipCount.sendKeys(startupSkipCount);
	}
   public String getStartupSkipCount() throws InterruptedException {
		Am.waitForAnElementPresence(By.cssSelector("*[name='ATSStartupSkipCount']"));
		Am.waitFortexttoBePresent(By.cssSelector("*[name='ATSStartupSkipCount']"));
		return StartUpSkipCount.getAttribute("value");
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
		Thread.sleep(1000);
		clickSubmitBtn();
		clickSystemSettingsBtn();
	}
	public Boolean getSavebtnstatus() {
		Am.waitForAnElementPresence(SAVE);
		Boolean sttus = SAVE.isEnabled();
		return sttus;
	}

}

