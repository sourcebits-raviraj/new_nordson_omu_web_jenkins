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

public class Fill_System_Settings {

		WebDriver ldriver;
		WebDriverWait wait;
		ActionMethods Am = new ActionMethods();

		// Constructor of the LoginPage to initiate driver
		public Fill_System_Settings(WebDriver rdriver) {
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
		
		@FindBy(xpath = "//a[contains(@class,'uppercase')]//*[normalize-space()='Fill']")
		WebElement Fillbtn;
		
		@FindBy(xpath = "(//*[@name='selectfill']//span)[2]")
		WebElement SelectedFilltxt;
		
		@FindBy(xpath = "//*[@name='selectfill']//div[@class='mat-select-arrow']")
		WebElement FillOptnarrw;
		
		@FindBy(css ="*[name='MaxFillTime']")
		WebElement MaxFillTme;
		
		@FindBy(css ="*[name='TargetFillLevel']")
		WebElement TargetFillLevel;
		
		@FindBy(css ="*[name='LowLevelAlertThreshold']")
		WebElement LowLevelAlertThreshold;
		
		@FindBy(css ="*[formcontrolname='LidOpenAlertTimeout']")
		WebElement LidOpenAlertTimeout;
		
		@FindBy(xpath = "//*[@class='apply btn']")
		WebElement SAVE;
		
		public void clickDashboard() {
			Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'DASHBOARD')]"));
			Am.waitForAnElementToBeClickable(Dashboard);
			Dashboard.click();
		}
		public void clickSetUpToolBtn() {
			Am.waitForAnElementPresence(By.id("bt"));
			//Am.waitForAnElementToBeClickable(SetUpToolButton);
			((JavascriptExecutor)ldriver).executeScript("window.scrollTo(0,"+Am.getXcoordinatetoclick(SetUpToolButton)+")");
			SetUpToolButton.click();
		}
		public void clickCreateNewBtn() {
			Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'CREATE NEW')]"));
			//Am.waitForAnElementToBeClickable(CreateNewButton);
			//CreateNewButton.click();
			((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", CreateNewButton);
		}
		public void clickSubmitBtn() throws InterruptedException {
			Am.waitForAnElementPresence(By.xpath("//*[@class='btn-set-up submit-btn-color']"));
		    Am.waitForAnElementToBeClickable(SubmitButton); 
		   ((JavascriptExecutor)ldriver).executeScript("window.scrollTo(0,"+Am.getXcoordinatetoclick(SubmitButton)+")");
		    SubmitButton.click();
		 //((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SubmitButton);
		}
		public void clickSystemSettingsBtn() {
			Am.waitForAnElementPresence(By.xpath("//*[contains(text(),'System Settings')]/ancestor::span"));
			Am.waitForAnElementToBeClickable(SystemSettings);
			((JavascriptExecutor) ldriver).executeScript("arguments[0].click();", SystemSettings);
			// SystemSettings.click();
		}
		
		public void clickFillbtn() throws InterruptedException {
			Am.waitForAnElementPresence(By.xpath("//a[contains(@class,'uppercase')]//*[normalize-space()='Fill']"));
			Thread.sleep(800);
			Am.waitForAnElementToBeClickable(Fillbtn);
			//((JavascriptExecutor)ldriver).executeScript("window.scrollTo(0,"+Am.getXcoordinatetoclick(Fillbtn)+")");
			Fillbtn.click();
		}
		
		// Fill mode selection
		public void SelectFillmode(String txttobeselected) throws InterruptedException {
			Am.waitForAnElementPresence(
					By.cssSelector("*[name='selectfill']"));
			Am.waitForAnElementClickable(By.xpath("//*[@name='selectfill']//div[@class='mat-select-arrow']"));
			if(!(SelectedFilltxt.getText().equalsIgnoreCase(txttobeselected))) {
		        FillOptnarrw.click();
		        Am.waitForAnElementPresence(By.xpath("//mat-option/span[contains(.,'" + txttobeselected + "')]"));
				ldriver.findElement(By.xpath("//mat-option/span[contains(.,'" + txttobeselected + "')]")).click();}
			else
				System.out.println("Already"+SelectedFilltxt.getText()+"fill option selected");
		}
	   public void clearMaxFillTime() {
		   Am.waitForAnElementPresence(By.cssSelector("*[name='MaxFillTime']"));
			MaxFillTme.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		}	
	   public void setMaxFillTime(String maxFillTime) {
		   Am.waitForAnElementPresence(By.cssSelector("*[name='MaxFillTime']"));
			MaxFillTme.sendKeys(maxFillTime);
		}
	   public String getMaxFillTime() throws InterruptedException {
			Am.waitForAnElementPresence(By.cssSelector("*[name='MaxFillTime']"));
			Am.waitFortexttoBePresent(By.cssSelector("*[name='MaxFillTime']"));
			return MaxFillTme.getAttribute("value");
		}
	   public void clearTargetFillLevel() {
			Am.waitForAnElementPresence(TargetFillLevel);
			TargetFillLevel.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		}	
	   public void setTargetFillLevel(String targetFillLevel) {
			Am.waitForAnElementPresence(TargetFillLevel);
			TargetFillLevel.sendKeys(targetFillLevel);
		}
	   public String getTargetFillLevel() throws InterruptedException {
			Am.waitForAnElementPresence(By.cssSelector("*[name='TargetFillLevel']"));
			Am.waitFortexttoBePresent(By.cssSelector("*[name='TargetFillLevel']"));
			return TargetFillLevel.getAttribute("value");
		} 
	   
	   public void clearLowLevelAlertThreshold() {
			Am.waitForAnElementPresence(LowLevelAlertThreshold);
			LowLevelAlertThreshold.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		}	
	   public void setLowLevelAlertThreshold(String lowLevelAlertThreshold) {
			Am.waitForAnElementPresence(LowLevelAlertThreshold);
			LowLevelAlertThreshold.sendKeys(lowLevelAlertThreshold);
		}
	   public String getLowLevelAlertThreshold() throws InterruptedException {
			Am.waitForAnElementPresence(By.cssSelector("*[name='LowLevelAlertThreshold']"));
			Am.waitFortexttoBePresent(By.cssSelector("*[name='LowLevelAlertThreshold']"));
			return LowLevelAlertThreshold.getAttribute("value");
		}
	   public void clearLidOpenAlertTimeout() {
				Am.waitForAnElementPresence(LidOpenAlertTimeout);
				LidOpenAlertTimeout.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			}	
	   public void setLidOpenAlertTimeout(String lidOpenAlertTimeout) {
				Am.waitForAnElementPresence(LidOpenAlertTimeout);
				LidOpenAlertTimeout.sendKeys(lidOpenAlertTimeout);
			}
	    public String getLidOpenAlertTimeout() throws InterruptedException {
				Am.waitForAnElementPresence(By.cssSelector("*[name='LidOpenAlertTimeout']"));
				Am.waitFortexttoBePresent(By.cssSelector("*[name='LidOpenAlertTimeout']"));
				return LidOpenAlertTimeout.getAttribute("value");
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
			//Am.waitForAnElementPresence(SAVE);
			Am.waitForAnElementPresence(By.xpath("//*[@class='apply btn']"));
			//Am.waitForAnElementToBeClickable(SAVE);
			//SAVE.click();
			((JavascriptExecutor)ldriver).executeScript("window.scrollTo(0,"+Am.getYcoordinatetoclick(SAVE)+")");
			SAVE.click();
		}
		public void createNewNORfile() throws Exception {
			clickSetUpToolBtn();
			clickCreateNewBtn();
			Am.sleepTime(2000);
			clickSubmitBtn();
			clickSystemSettingsBtn();
		}
		public Boolean getSavebtnstatus() {
			Am.waitForAnElementPresence(SAVE);
			Boolean sttus = SAVE.isEnabled();
			return sttus;
		}

}
