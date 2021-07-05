package com.nordson.ui.testcases;


import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nordson.NORXML.XMLClass;

import com.nordson.pageObjects.ui.User_Dashboard_Details_Landing_Page;
import com.nordson.testCases.TC_LoginTest_DDT_001;
import com.nordson.utilities.ActionMethods;
import com.nordson.utilities.Constants;
import com.nordson.utilities.Language;
import com.nordson.utilities.LocaleMSgReader;
import com.nordson.utilities.LocalisationReadWriteLocaleProperties;
import com.nordson.utilities.ReadConfig;
import com.nordson.utilities.XLUtils;
import com.nordson.utilities.XMLTagConstants;

import io.qameta.allure.Description;

public class TC_OMU_1891_1936_User_Dashboard_Links extends TC_LoginTest_DDT_001 {

	ActionMethods Am = new ActionMethods();
	XMLClass xmlval = new XMLClass();
	 Robot robot;

	
	private SoftAssert softAssert = new SoftAssert();

	// Driver Object Instantiation
	User_Dashboard_Details_Landing_Page lp;
	ReadConfig readconfig = new ReadConfig();
	LocalisationReadWriteLocaleProperties lrwprop=new LocalisationReadWriteLocaleProperties();
	Language lng = new Language();

	@Test(priority = 1, enabled = true)
	public void LanguageSetup() throws Exception {
		lrwprop.Readwriteproperties();
		Am.sleepTime(3000);
		lng.selectLanguage(driver, readconfig.getLocaleLanguage());
	}

	@Test(priority = 2, enabled = true)
	public void Welcome_Displayed() throws Exception {
		
		lp = new User_Dashboard_Details_Landing_Page(driver);
		String wlcmsg = LocaleMSgReader.getString("WELCOME");
		System.out.println(wlcmsg + " welcome text from  locale properties file ");
		if (lp.welcomeDisplayed() == true) {
			Am.sleepTime(1000);
			if (lp.welcome.getText().contains(wlcmsg)) {
				log.info("Welcome text is displayed in UI :" + lp.welcome.getText());
				Am.drawBorder(lp.welcome, driver);
			} else {
				log.info("Welcome text is not matching");
				Am.drawBorderFail(lp.welcome, driver);
				Assert.assertTrue(false);
			}
		} else {
			log.info("Welcome text is not displayed");
			Am.drawBorderFail(lp.welcome, driver);
			Assert.assertTrue(false);
		}
		
	}

	@Test(priority = 3, enabled = true)
	public void View_DashBoard() throws Exception {

		String dashbord = LocaleMSgReader.getString("DASHBOARD");
		System.out.println(dashbord + "Dashbord text from  locale properties file ");
		
		if (lp.dashboardDisplayed() == true)
		{
			log.info("Dashboard Link Displayed");
				if (lp.Dashboard_Text().equalsIgnoreCase((dashbord))) {
						Am.drawBorder(lp.DashBoard, driver);
						Am.sleepTime(1000);
						log.info("Dashboard Text displayed in UI :" + lp.Dashboard_Text()); 
						Assert.assertTrue(true);
				}
		 		else {
		 				Am.drawBorderFail(lp.DashBoard, driver);
		 				log.info("Dashboard Text not displayed"); 
		 				Assert.assertTrue(false);}
		}		
		 else {
		 		Am.drawBorderFail(lp.dashbaord, driver);
		 		log.info("Dashboard Link not Displayed");
		 		System.out.println("Dashboard link is not displayed");
		 		Assert.assertTrue(false);
		 	 } 				
		       
		}
	
	@Test(priority = 4, enabled = true)
	public void Registration_Nordson_Privacy_Policy() throws Exception {
		
		System.out.println(LocaleMSgReader.getString("PRIVACY_POLICY") + "Privacy Policy text from  locale properties file ");
		if (lp.PrivacyPolicyDisplayed() == true) {
			log.info("Privacy Link Displayed");

			if (lp.getPrivacyPolicyText().equalsIgnoreCase(LocaleMSgReader.getString("PRIVACY_POLICY"))) {
				log.info("Privacy Policy text is displayed in UI :" + lp.getPrivacyPolicyText());
				Am.drawBorder(lp.PrivacyPolicy, driver);
				Assert.assertTrue(true);
				
			} else {
				log.info("Privacy Policy text is not matching");
				Am.drawBorderFail(lp.PrivacyPolicy, driver);
				Assert.assertTrue(false);;
			}
		} else {
			Am.drawBorderFail(lp.PrivacyPolicy, driver);
			log.info("Privacy Link not Displayed");
			System.out.println("Privacy Policy link is not displayed");
			Assert.assertTrue(false);
		}
		
		
	}

	@Test(priority = 5, enabled = true)
	public void Registration_Nordosn_Terms_of_Services() throws Exception {
		
		Am.sleepTime(2000);
		System.out.println(LocaleMSgReader.getString("TERMS_OF_SERVICE") + "TERMS OF SERVICE text from  locale properties file ");
		if (lp.TermsOfServiceDisplayed() == true) {
			log.info("Terms Of Service text is Displayed");
			if (lp.getTermsOFServiceText().equalsIgnoreCase(LocaleMSgReader.getString("TERMS_OF_SERVICE"))) {
				log.info("Terms of Service text is displayed in UI :" + lp.getTermsOFServiceText());
				Am.drawBorder(lp.TermsService, driver);
				Assert.assertTrue(true);
			} else {
				log.info("Terms of Service text is not matching");
				Am.drawBorderFail(lp.TermsService, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.TermsService, driver);
			log.info("Terms Of Service link not Displayed");
			System.out.println("Terms Of Service link is not displayed");
			Assert.assertTrue(false);
		}
		
		
	}

	@Test(priority = 6, enabled = true)
	public void Registration_Nordson_Contact_Us() throws Exception {
		
		System.out.println(LocaleMSgReader.getString("CONTACT_US") + "CONTACT US text from locale properties file ");
		if (lp.contactUsDisplayed() == true) {
			log.info("Contact Us Link Displayed");
			if (lp.getContactUsText().equalsIgnoreCase(LocaleMSgReader.getString("CONTACT_US"))) {
				log.info("Contact Us text is displayed in UI :" + lp.getContactUsText());
				Am.drawBorder(lp.ContactUs, driver);
				Assert.assertTrue(true);
			} else {
				log.info("Contact us text is not Displayed");
				Am.drawBorderFail(lp.TermsService, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.TermsService, driver);
			log.info("Contact us link not Displayed");
			System.out.println("Contact Us link not dispalyed");
			Assert.assertTrue(false);
		}

		
	}
	
	@Test(priority = 7, enabled = true)
	public void SETUP_Tool_Verficiation() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("SETUP_TOOL") + "SETUP_TOOL text from locale properties file ");
		if (lp.setUpToolDisplayed()== true) {
			log.info("SETUP Tool Link Displayed");
			if (lp.getSetUpToolText().equalsIgnoreCase(LocaleMSgReader.getString("SETUP_TOOL"))) {
				log.info("SET Up Tool text is displayed in UI :" + lp.getSetUpToolText());
				Am.drawBorder(lp.SetUpTool, driver);
				lp.clickSetUpTool();
				Am.sleepTime(2000);
				Assert.assertTrue(true);
			} else {
				log.info("SET Up Tool text is not Displayed");
				Am.drawBorderFail(lp.SetUpTool, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.SetUpTool, driver);
			log.info("SET Up Tool link not Displayed");
			System.out.println("SET Up Tool link not dispalyed");
			Assert.assertTrue(false);
		}
		
		
	}
	
	@Test(priority = 8, enabled = true)
	public void TC_Verficiation_LoadFromUSBnOrComputer_Option_In_Configuration_Popup() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("LOAD_FROM_USB/COMPUTER") + " LOAD_FROM_USB/COMPUTER text from locale properties file ");
		if (lp.setUpToolPopUpDisplayed()== true && lp.loadFromUSBIsDisplayed()) {
			log.info("Load From USB/Computer Option is Displayed");
			if (lp.getLoadFromUSB_ComputerText().equalsIgnoreCase(LocaleMSgReader.getString("LOAD_FROM_USB/COMPUTER"))) {
				log.info("Load from USB text is displayed in UI :" + lp.getLoadFromUSB_ComputerText());
				Am.drawBorder(lp.LoadFromUSBText, driver);
				lp.clickLoadFromUSB();
				Am.sleepTime(2500);
			    robot = new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
				robot.keyRelease(KeyEvent.VK_ESCAPE);
				Am.sleepTime(2000);
				Assert.assertTrue(true);
			} else {
				log.info("Load from USB text is not displayed in UI");
				Am.drawBorderFail(lp.LoadFromUSBText, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.LoadFromUSBText, driver);
			log.info("Load From USB/Computer Option is not Displayed");
			System.out.println("Load From USB/Computer Option is not Displayed");
			Assert.assertTrue(false);
		}
		
	}
	
	
	@Test(priority = 8, enabled = true)
	public void TC_Verficiation_UsePreviousFile_Option_In_Configuration_Popup() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("USE_PREVIOUS_FILE") + "USE_PREVIOUS_FILE text from locale properties file ");
		if (lp.setUpToolPopUpDisplayed()== true && lp.UsePreviousDisplayed()) {
			log.info("Load From USB/Computer Option is Displayed");
			if (lp.getUsePreviousText().equalsIgnoreCase(LocaleMSgReader.getString("USE_PREVIOUS_FILE"))) {
				log.info("USE PREVIOUS FILE text is displayed in UI :" + lp.getUsePreviousText());
				Am.drawBorder(lp.UsePreviousFile, driver);
				lp.clickUsePreviousFile();
				Am.sleepTime(2000);
				lp.clickUsePreviousFileCancel();
				Am.sleepTime(2000);
				Assert.assertTrue(true);
			} else {
				log.info("USE PREVIOUS FILE text is not displayed in UI");
				Am.drawBorderFail(lp.UsePreviousFile, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.UsePreviousFile, driver);
			log.info("USE PREVIOUS FILE Option is not Displayed");
			System.out.println("USE PREVIOUS FILE Option is not Displayed");
			Assert.assertTrue(false);
		}
		
		

	}
	
	@Test(priority = 9, enabled = true)
	public void TC_Verficiation_CreateNew_Option_In_Configuration_Popup() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("CREATE_NEW") + "CREATE_NEW text from locale properties file ");
		if (lp.setUpToolPopUpDisplayed()== true && lp.CreateNewDisplayed()) {
			log.info("CREATE_NEW Option is Displayed");
			if (lp.getCreateNewText().equalsIgnoreCase(LocaleMSgReader.getString("CREATE_NEW"))) {
				log.info("CREATE_NEW text is displayed in UI :" + lp.getUsePreviousText());
				Am.sleepTime(1500);
				Am.drawBorder(lp.CreateNew, driver);
				lp.clickCreateNew();
				Am.sleepTime(2500);
				Assert.assertTrue(true);
			} else {
				log.info("CREATE_NEWtext is not displayed in UI");
				Am.drawBorderFail(lp.CreateNew, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.CreateNew, driver);
			log.info("CREATE_NEW Option is not Displayed");
			System.out.println("CREATE_NEW Option is not Displayed");
			Assert.assertTrue(false);
		}
		
		
	}
	
	
	@Test(priority = 10, enabled = true)
	public void TC_Verficiation_CreateNewNorfile_Popup_Elements() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		if (lp.CreateNewNorfilePopupDisplayed()== true && lp.EquipmentSerialNoDisplayed()&& lp.EquipmentDescriptionDisplayed() && lp.NORFileDescriptionDisplayed()) {
			log.info("Create New Nor File Description Options are Displayed");
			    Am.drawBorder(lp.EquipmentPanel, driver);
			    Am.sleepTime(1000);
			    System.out.println("EQUIPMENT_DESCRIPTION value from properties file : "+LocaleMSgReader.getString("EQUIPMENT_DESCRIPTION"));
				if (lp.getEquipmentDescriptionText().equalsIgnoreCase(LocaleMSgReader.getString("EQUIPMENT_DESCRIPTION"))) {
					log.info("Equipment Description text is displayed in UI :" + lp.getEquipmentDescriptionText());
					 Am.drawBorder(lp.EquipmentDescriptionlbl, driver);
					 Assert.assertTrue(true);
				} else {
					log.info("Equipment Description text is not displayed in UI");
					Am.drawBorderFail(lp.CreateNew, driver);
					softAssert.assertTrue(false);
				}
			    Am.sleepTime(1000);
			    System.out.println("EQUIPMENT_DESCRIPTION value from properties file : "+LocaleMSgReader.getString(".NOR_FILE_DESCRIPTION"));
			    if (lp.getNorFileDescriptionText().equalsIgnoreCase(LocaleMSgReader.getString(".NOR_FILE_DESCRIPTION"))) {
					log.info(".NOR File Description text is displayed in UI :" + lp.getNorFileDescriptionText());
					 Am.drawBorder(lp.NORFileDescription, driver);
					 softAssert.assertTrue(true);
				} else {
					log.info(".NOR File Description text is not displayed in UI");
					Am.drawBorderFail(lp.NORFileDescription, driver);
					softAssert.assertTrue(false);
				}
				lp.addDescription();
				Am.sleepTime(1000);
				if(lp.SubmitbuttonISDisplayed()==true) {
					Am.drawBorder(lp.SubmitButton, driver);
				lp.clickSubmitbutton();
				Am.sleepTime(3500);
				softAssert.assertTrue(true);}
			 else {
				log.info("Submit is not displayed in UI");
				Am.drawBorderFail(lp.SubmitButton, driver);
				softAssert.assertTrue(false);
			     }  }
		 else {
			Am.drawBorderFail(lp.CreateNewNorFilePopup, driver);
			log.info("Create New Nor File Description Options are not Displayed");
			System.out.println("Create New Nor File Description Options are not Displayed");
			softAssert.assertTrue(false);
		}
		
		softAssert.assertAll();
	}
	
	@Test(priority = 11, enabled = true)
	public void Verficiation_Title_TemperatureZones() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("TEMPERATURE_ZONES") + "TEMPERATURE_ZONES text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.TemperatureTitleISDisplayed()== true) {
			log.info("TEMPERATURE_ZONES Title Link Displayed");
			if (lp.geTemperatureZoneHeadingText().equalsIgnoreCase(LocaleMSgReader.getString("TEMPERATURE_ZONES"))) {
				log.info("TEMPERATURE ZONES Tool text is displayed in UI :" + lp.geTemperatureZoneHeadingText());
				Am.drawBorder(lp.TemperatureZoneHeading, driver);
				Assert.assertTrue(true);
			} else {
				log.info("TEMPERATURE ZONES text is not matched with String file");
				Am.drawBorderFail(lp.TemperatureZoneHeading, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.TemperatureZoneHeading, driver);
			log.info("TEMPERATURE ZONES Title is not Displayed");
			System.out.println("TEMPERATURE ZONES Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test(priority = 12, enabled = true)
	public void TC_Verficiation_SystemSettings() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("SYSTEM_SETTINGS").toUpperCase()+ "SYSTEM_SETTINGS text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.SystemSettingsISDisplayed()== true) {
			log.info("SYSTEM_SETTINGS Title Link Displayed");
			if (lp.getSystemSettingsText().equalsIgnoreCase(LocaleMSgReader.getString("SYSTEM_SETTINGS"))) {
				log.info("SYSTEM_SETTINGS text is displayed in UI :" + lp.getSystemSettingsText());
				Am.drawBorder(lp.SystemSettingstext, driver);
				lp.clickSystemSettings();
				Am.sleepTime(2000);
				Assert.assertTrue(true);
			} else {
				log.info("SYSTEM_SETTINGS text is not matched with String file");
				Am.drawBorderFail(lp.SystemSettingstext, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.SystemSettingsLink, driver);
			log.info("SYSTEM_SETTINGS Title is not Displayed");
			System.out.println("SYSTEM_SETTINGS Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 13, enabled = true)
	public void TC_Verficiation_Preferences() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("PREFERENCES") + "PREFERENCES text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.PreferencesISDisplayed()== true) {
			log.info("PREFERENCES Title Link Displayed");
			if (lp.getPreferencesText().equalsIgnoreCase(LocaleMSgReader.getString("PREFERENCES"))) {
				log.info("PREFERENCES text is displayed in UI :" + lp.getPreferencesText());
				Am.drawBorder(lp.Preferencestxt, driver);
				lp.clickPreferences();
				Am.sleepTime(2000);
				Assert.assertTrue(true);
			} else {
				log.info("PREFERENCES text is not matched with String file");
				Am.drawBorderFail(lp.Preferencestxt, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.PreferencesLink, driver);
			log.info("PREFERENCES Title is not Displayed");
			System.out.println("PREFERENCES Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 14, enabled = true)
	public void TC_Preferences_PSIUnit_Selection() throws Exception {
		
		if (lp.PSIUnitISDisplayed()== true) {
			log.info("PSI Unit is Displayed");
				Am.drawBorder(lp.PSIUnit, driver);
				lp.ClickPSIUnit();
				  if(lp.PreferencesSaveISDisplayed()==true) {
					  log.info("SAVE Button is Displayed");
					  Am.drawBorder(lp.PrefrencesSave, driver);
					  lp.clickPreferencesSave();
					  Assert.assertEquals(lp.getToastMessageText(),LocaleMSgReader.getString("PREFERENCES_UPDATED_SUCCESS"));
				  		Assert.assertTrue(true);}
				  else
				  {
					  log.info("SAVE Button is not Displayed");
					  Am.drawBorderFail(lp.PrefrencesSave, driver);
				  		Assert.assertTrue(false);
				  }
			} else {
				log.info("PSI Unit is not Displayed");
				Am.drawBorderFail(lp.Preferencestxt, driver);
				Assert.assertTrue(false);
			}
	}
	
	@Test(priority = 15, enabled = true)
	public void TC_Verficiation_RuntimeSettings() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("RUNTIME_SETTINGS")+ "RUNTIME_SETTINGS text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.RuntimeSettingsISDisplayed()== true) {
			log.info("RUNTIME_SETTINGS Title Link Displayed");
			if (lp.getRuntimeSettingsText().equalsIgnoreCase(LocaleMSgReader.getString("RUNTIME_SETTINGS"))) {
				log.info("RUNTIME_SETTINGS text is displayed in UI :" + lp.getRuntimeSettingsText());
				Am.drawBorder(lp.RuntimeSettingstext, driver);
				lp.clickRuntimeSettings();
				Am.sleepTime(2000);
				Assert.assertTrue(true);
			} else {
				log.info("RUNTIME_SETTINGS text is not matched with String file");
				Am.drawBorderFail(lp.RuntimeSettingstext, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.RuntimeSettingsLink, driver);
			log.info("RUNTIME_SETTINGS Title is not Displayed");
			System.out.println("RUNTIME_SETTINGS Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test(priority = 16, enabled = true)
	public void TC_Verficiation_RuntimeSettingsPressure() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("PRESSURE")+ "PRESSURE text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.RuntimeSettingsPressureISDisplayed()== true) {
			log.info("PRESSURE Title Link Displayed");
			if (lp.getRuntimeSettingsPressureText().equalsIgnoreCase(LocaleMSgReader.getString("PRESSURE"))) {
				log.info("PRESSURE text is displayed in UI :" + lp.getRuntimeSettingsText());
				Am.drawBorder(lp.RuntimeSettingsPressuretext, driver);
				lp.clickRuntimeSettingsPressure();
				Am.sleepTime(2000);
				Assert.assertTrue(true);
			} else {
				log.info("PRESSURE text is not matched with String file");
				Am.drawBorderFail(lp.RuntimeSettingsPressuretext, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.RuntimeSettingsPressureLink, driver);
			log.info("PRESSURE Title is not Displayed");
			System.out.println("PRESSURE Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test(priority = 17, enabled = true)
	public void TC_Verficiation_Pressure_ManualAdjust_PressureSettings_Title_UI() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("PRESSURE_SETTING") + "PRESSURE_SETTING text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.PressureSettingsTitleISDisplayed()== true) {
			log.info("PRESSURE_SETTING Title Link Displayed");
			if (lp.PressureSettingsHeadingText().equalsIgnoreCase(LocaleMSgReader.getString("PRESSURE_SETTING"))) {
				log.info("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION Tool text is displayed in UI :" + lp.PressureSettingsHeadingText());
				Am.drawBorder(lp.PressureHeading, driver);
				Assert.assertTrue(true);
			} else {
				log.info("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION text is not matched with String file");
				Am.drawBorderFail(lp.PressureHeading, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.PressureHeading, driver);
			log.info("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION Title is not Displayed");
			System.out.println("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 18, enabled = true)
	public void TC_Verficiation_Pressure_ManualAdjust_PressureSettings_SubTitle_UI() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("SETTINGS_ON_THIS_PAGE_DEPEND_ON_THE_INSTALLED_OPTION") + "SETTINGS_ON_THIS_PAGE_DEPEND_ON_THE_INSTALLED_OPTION text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.PressureSettingsSubTitleISDisplayed()== true) {
			log.info("SETTINGS_ON_THIS_PAGE_DEPEND_ON_THE_INSTALLED_OPTION Title Link Displayed");
			if (lp.PressureSettingsSubTitleText().equalsIgnoreCase(LocaleMSgReader.getString("SETTINGS_ON_THIS_PAGE_DEPEND_ON_THE_INSTALLED_OPTION"))) {
				log.info("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION Tool text is displayed in UI :" + lp.PressureSettingsSubTitleText());
				Am.drawBorder(lp.RuntimeSettingsPressureSubTitletext, driver);
				Assert.assertTrue(true);
			} else {
				log.info("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION text is not matched with String file");
				Am.drawBorderFail(lp.RuntimeSettingsPressureSubTitletext, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.RuntimeSettingsPressureSubTitletext, driver);
			log.info("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION Title is not Displayed");
			System.out.println("SETTINGS ON THIS PAGE DEPEND ON THE INSTALLED OPTION Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 19, enabled = true)
	public void TC_Verficiation_Pressure_ManualAdjust_MainPressureModeSelection_UI() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("MAIN_PRESSURE_MODE_SELECTION") + "MAIN_PRESSURE_MODE_SELECTION text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.PressureSettingsMainPressureModeTitleISDisplayed()== true) {
			log.info("MAIN_PRESSURE_MODE_SELECTION Title Link Displayed");
			if (lp.getPressureSettingsMainPressureModeTitleText().equalsIgnoreCase(LocaleMSgReader.getString("MAIN_PRESSURE_MODE_SELECTION"))) {
				log.info("MAIN PRESSURE MODE SELECTION Tool text is displayed in UI :" + lp.getPressureSettingsMainPressureModeTitleText());
				Am.drawBorder(lp.MainPressureModeTitle, driver);
				Assert.assertTrue(true);
			} else {
				log.info("MAIN PRESSURE MODE SELECTION text is not matched with String file");
				Am.drawBorderFail(lp.MainPressureModeTitle, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.MainPressureModeTitle, driver);
			log.info("MAIN PRESSURE MODE SELECTION Title is not Displayed");
			System.out.println("MAIN PRESSURE MODE SELECTION Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 20, enabled = true)
	public void TC_Verficiation_Pressure_ManualAdjust_PressureSettingRangeAlert_UI() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("PRESSURE_SETTING_RANGE_ALERT") + "PRESSURE_SETTING_RANGE_ALERT text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.PressureSettingsRangeAlertTitleISDisplayed()== true) {
			log.info("PRESSURE_SETTING_RANGE_ALERT Title Link Displayed");
			if (lp.getPressureSettingsRangeAlertTitleText().equalsIgnoreCase(LocaleMSgReader.getString("PRESSURE_SETTING_RANGE_ALERT"))) {
				log.info("PRESSURE SETTING RANGE ALERT Tool text is displayed in UI :" + lp.getPressureSettingsRangeAlertTitleText());
				Am.drawBorder(lp.PressureSettingRangeAlertText, driver);
				Assert.assertTrue(true);
			} else {
				log.info("PRESSURE SETTING RANGE ALERT text is not matched with String file");
				Am.drawBorderFail(lp.PressureSettingRangeAlertText, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.PressureSettingRangeAlertText, driver);
			log.info("PRESSURE SETTING RANGE ALERT Title is not Displayed");
			System.out.println("PRESSURE SETTING RANGE ALERT Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 21, enabled = true)
	public void TC_Verficiation_Pressure_ManualAdjust_MinimumPressureAlert_UI() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("MIN_PRESSURE_ALERT") + "MIN_PRESSURE_ALERT text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.MinimumPressureAlertTitleISDisplayed()== true) {
			log.info("MIN_PRESSURE_ALERT Title Link Displayed");
			if (lp.getMinimumPressureAlertTitleText().equalsIgnoreCase(LocaleMSgReader.getString("MIN_PRESSURE_ALERT"))) {
				log.info("MIN PRESSURE ALERT Tool text is displayed in UI :" + lp.getMinimumPressureAlertTitleText());
				Am.drawBorder(lp.MinimumPressureAlertText, driver);
				Assert.assertTrue(true);
			} else {
				log.info("MIN PRESSURE ALERT text is not matched with String file");
				Am.drawBorderFail(lp.MinimumPressureAlertText, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.MinimumPressureAlertText, driver);
			log.info("MIN PRESSURE ALERT Title is not Displayed");
			System.out.println("MIN PRESSURE ALERT Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 22, enabled = true)
	public void TC_Verficiation_Pressure_ManualAdjust_MaximumPressureAlert_UI() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("MAX_PRESSURE_ALERT") + "MAX_PRESSURE_ALERT text from locale properties file ");
		Am.sleepTime(1500);
		if (lp.MaximumPressureAlertTitleISDisplayed()== true) {
			log.info("MAX_PRESSURE_ALERT Title Link Displayed");
			if (lp.getMaximumPressureAlertTitleText().equalsIgnoreCase(LocaleMSgReader.getString("MAX_PRESSURE_ALERT"))) {
				log.info("MAX PRESSURE ALERT Tool text is displayed in UI :" + lp.getMaximumPressureAlertTitleText());
				Am.drawBorder(lp.MaximumPressureAlertText, driver);
				Assert.assertTrue(true);
			} else {
				log.info("MAX PRESSURE ALERT text is not matched with String file");
				Am.drawBorderFail(lp.MaximumPressureAlertText, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.MaximumPressureAlertText, driver);
			log.info("MAX PRESSURE ALERT Title is not Displayed");
			System.out.println("MAX PRESSURE ALERT Title not dispalyed");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test(priority = 23, enabled = true)
	public void TC_Verficiation_Pressure_ManualAdjust_PSI_Default_Values_InNORFile() throws Exception {
		
		lp=new User_Dashboard_Details_Landing_Page(driver);
		System.out.println(LocaleMSgReader.getString("MANUAL_ADJUST") + "MANUAL_ADJUST text from locale properties file ");
		Am.sleepTime(1500);
		lp.SelectMainPressureModeSelectionDropdown(LocaleMSgReader.getString("MANUAL_ADJUST"));
		Am.sleepTime(4500);
		// Verifying the Download text
		if (lp.DownloadISDisplayed()== true) {
			log.info("Download Link Displayed");
			if (lp.getDownloadtext().equalsIgnoreCase(LocaleMSgReader.getString("DOWNLOAD_NOR_FILE"))) {
				log.info("DOWNLOAD .NOR File text is displayed in UI :" + lp.getDownloadtext());
				Am.drawBorder(lp.Download, driver);
				Assert.assertTrue(true);
			} else {
				log.info("DOWNLOAD .NOR File text is not matched with String file");
				Am.drawBorderFail(lp.Download, driver);
				Assert.assertTrue(false);
			}
		} else {
			Am.drawBorderFail(lp.Download, driver);
			log.info("DOWNLOAD .NOR File  is not Displayed");
			System.out.println("DOWNLOAD .NOR File is not dispalyed");
			Assert.assertTrue(false);
		}
		
		
	
		// Downloading the .Nor file
				lp.clickdownload();
				Am.sleepTime(3000);
				
				// check for windows popup
				 robot.delay(2000);
				 robot.keyPress(KeyEvent.VK_DOWN);
			     robot.delay(4000);
			     robot.keyPress(KeyEvent.VK_TAB);
			     robot.keyPress(KeyEvent.VK_TAB);
			     robot.keyPress(KeyEvent.VK_TAB);
			     robot.keyPress(KeyEvent.VK_ENTER);
			     robot.delay(2000);
				
				
				// Storing the downloaded file to the projec tlocation and converting it to XML
		
		  String flnm = Am.getlatestDownloadedNorFilenm(); 
		  Am.sleepTime(4000);
		  Am.NorcopyFile(flnm); 
		  Am.sleepTime(2000); 
		  String newfilename = Am.removeSpaces(flnm);
		  Am.sleepTime(3500); 
		  Am.ConversionfromNorToXML(newfilename);
		 
				Thread.sleep(3500);
				// Getting the value from RecipeCurrent.XML
				System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
				System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
				
				// writing the value to excel sheet
				XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_Default_PSI_Manualadjust",
						XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
						xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
						xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

				// Comparsion of old tags
				Am.sleepTime(3000);
				System.out.println("Default Value of old tag Minimum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value");
				System.out.println(("Default Value of old tag for Minimum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert)));
				Assert.assertEquals(Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic(), xmlval
						.XMLParser(XMLTagConstants.MinimumPressureAlert));
				
				
				System.out.println("Default Value of old tag Maximum Pressure Alert in UI ="+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value");
				System.out.println(("Default Value of old tag for Maxmium Pressure Alert in XML ="+xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert)));
				
				Assert.assertEquals(Am.conversion_of_App_PSI_Default_vlaue_Max_for_Norfile_comparision_Pneumatic(), xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlert));
				
				// Verfication of native tags
				Am.sleepTime(2000);
				
				XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Mnladjst_defult_ntv",
						XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
						xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
						xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
				
				System.out.println("Default Value of Minimum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value");
				System.out.println(("Default Value of Native tag Minimum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative)));
				
				Assert.assertEquals(Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic(), xmlval
						.XMLParser(XMLTagConstants.MinimumPressureAlertNative));
				
				System.out.println("Default Value of Maximum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+"App converted value");
				System.out.println(("Default Value of Native tag for Maxmium Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative)));
				
				Assert.assertEquals(Am.conversion_of_App_PSI_Default_vlaue_Max_for_Norfile_comparision_Pneumatic(), xmlval
						.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
				
	}
	
	@Test(priority = 24,enabled=true)
	public void setExcelSheetNameforMinPSIValues()
	{
		XLUtils.setExcelSheetNm("Input_Min_PSI_manualadjust");
	}
	
	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert Min values in downloaded NOR file for PSI Unit")
	@Test(priority = 25, enabled = true, dataProvider = "min_max_Presure_for_norfile_PSI_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Pneumatic_Manualadjust_Min_Pressure_Value_comparision_with_NorFile_PSI(String MinPressurealrtminval, String MaxPressurealrtminval)
			throws Exception {

		//clear the existing Strings in UI
		
		lp.clearMinimumPressureAlertValue();
		lp.clearMaximumPressureAlertValue();
		
		// set the Min and Max values from excel file
		
		lp.setMinimumPressureAlertValue(MinPressurealrtminval);
		lp.setMaximumPressureAlertValue(MaxPressurealrtminval);
		
		lp.saveButton();
		Assert.assertEquals(lp.getToastMessageText(),Constants.Pressuresucssmsg);
		Am.sleepTime(3500);
		// getting the minimum and max value from the UI String
		String pressminVal = lp.getMinimumPressureAlertValue();
		String pressmaxVal = lp.getMaximumPressureAlertValue();
		
		// Downloading the .Nor file
		lp.clickdownload();
		Am.sleepTime(3000);

		// Downloading the .Nor file
	     robot.delay(4000);
	     robot.keyPress(KeyEvent.VK_TAB);
	     robot.keyPress(KeyEvent.VK_TAB);
	     robot.keyPress(KeyEvent.VK_TAB);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.delay(2000);
	
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		 Am.sleepTime(4000);
		  Am.NorcopyFile(flnm); 
		  Am.sleepTime(2000); 
		  String newfilename = Am.removeSpaces(flnm);
		  Am.sleepTime(3500); 
		Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(4000);
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
		System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		
		
		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_Manualadjust_PSI_Minvals",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparison of old tags
		
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -PSI
		
		System.out.println("Min Value of Minimum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Min Value of old tag Minimum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert));
		
		System.out.println("Min Value of Maximum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Min Value of old tag Maximum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert)));
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -PSI
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Verification of native tags
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Mnladjst_ntv_minvals",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));

		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -PSI
		System.out.println("Min Value of Minimum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Min Value of Native tag Minimum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative)));
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative));
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -PSI
		System.out.println("Min Value of Maximum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Min Value of Native tag Maximum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative)));
		
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
	}
	
	@Test(priority = 26,enabled=true)
	public void setExcelSheetNameforMaxPSIValues()
	{
		XLUtils.setExcelSheetNm("Input_Max_PSI_manualadjust");
	}
	
	@Description("Verify the Minimum Pressure Alert and Maximum Pressure Alert Max values in downloaded NOR file for PSI Unit")
	@Test(priority = 27, enabled = true, dataProvider = "min_max_Presure_for_norfile_PSI_manualadjust", dataProviderClass = com.nordson.utilities.XLUtils.class)
	public void TC_Pneumatic_Manualadjust_Max_Pressure_Value_comparision_with_NorFile_PSI(String MinPressurealrtmaxval, String MaxPressurealrtmaxval)
			throws Exception {

		//clear the existing Strings in UI
		
		lp.clearMinimumPressureAlertValue();
		lp.clearMaximumPressureAlertValue();
		
		// set the Max values from excel file
		
		lp.setMinimumPressureAlertValue(MinPressurealrtmaxval);
		lp.setMaximumPressureAlertValue(MaxPressurealrtmaxval);
		
		lp.saveButton();
		Assert.assertEquals(lp.getToastMessageText(),Constants.Pressuresucssmsg);
	
		Am.sleepTime(2000);
		// getting the minimum and max value from the UI String
		String pressminVal = lp.getMinimumPressureAlertValue();
		String pressmaxVal = lp.getMaximumPressureAlertValue();
	
		// Downloading the .Nor file
		lp.clickdownload();
		Thread.sleep(2500);
		
		// Window Popup while downloading .Nor file
	     robot.delay(4000);
	     robot.keyPress(KeyEvent.VK_TAB);
	     robot.keyPress(KeyEvent.VK_TAB);
	     robot.keyPress(KeyEvent.VK_TAB);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.delay(2000);
		
		// Storing the downloaded file to the project location and converting it to XML
		String flnm = Am.getlatestDownloadedNorFilenm();
		  Am.sleepTime(4000);
		  Am.NorcopyFile(flnm); 
		  Am.sleepTime(2000); 
		  String newfilename = Am.removeSpaces(flnm);
		  Am.sleepTime(3500); 
		  Am.ConversionfromNorToXML(newfilename);
		Thread.sleep(3500);
		// Getting the value from RecipeCurrent.XML
		System.out.println(xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert));
		System.out.println(xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));
		
		
		// writing the value to excel sheet
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Manualadjust_Maxvals",
				XMLTagConstants.MinimumPressureAlert, XMLTagConstants.MaximumPressureAlert,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Comparison of old tags
		
		System.out.println("Max Value of Minimum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Max Value of old tag Minimum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		//Verification of ulLowPressureAlertThreshold tag with Pressure Unit as -PSI
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlert));
		
		//Verification of ulHighPressureAlertThreshold tag with Pressure Unit as -PSI
		System.out.println("Max Value of Maximum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Max Value of old tag Maximum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MaximumPressureAlert)));
		
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlert));

		// Verification of native tags
		XLUtils.setNorXMLValues_Pressure_Min_and_Max("Output_PSI_Mnladjst_maxvls_ntv",
				XMLTagConstants.MinimumPressureAlertNative, XMLTagConstants.MaximumPressureAlertNative,
				xmlval.XMLParser(XMLTagConstants.MinimumPressureAlertNative),
				xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative));

		//Verification of ulLowPressureAlertThresholdNative tag with Pressure Unit as -PSI
		System.out.println("Max Value of Minimum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Max Value of Native tag Minimum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MinimumPressureAlert)));
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressminVal), xmlval
				.XMLParser(XMLTagConstants.MinimumPressureAlertNative));
		
		
		System.out.println("Max Value of Maximum Pressure Alert in UI = "+Am.conversion_of_App_PSI_Default_vlaue_for_Norfile_comparision_Pneumatic()+" App converted value ");
		System.out.println(("Max Value of Native tag Maximum Pressure Alert in XML = "+xmlval.XMLParser(XMLTagConstants.MaximumPressureAlertNative)));
		
		//Verification of ulHighPressureAlertThresholdNative tag with Pressure Unit as -PSI
		Assert.assertEquals(Am.conversion_of_App_vlaue_for_Norfile_comparision_Pneumatic(pressmaxVal), xmlval
				.XMLParser(XMLTagConstants.MaximumPressureAlertNative));
		
	}
	
}