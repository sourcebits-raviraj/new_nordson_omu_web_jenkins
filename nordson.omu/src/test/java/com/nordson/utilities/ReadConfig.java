package com.nordson.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

  Properties pro;

	public ReadConfig() {
		File src = new File("./configs/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Expecption is " + e.getMessage());
		}
	}

	public String getAppURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}

	public String getSuperAdminUsername() {
		String uname = pro.getProperty("superadminusername");
		return uname;
	}

	public String getSuperAdminPassword() {
		String pwd = pro.getProperty("superadminpassword");
		return pwd;
	}

	public String getChromepath() {
		String chropath = pro.getProperty("chromepath");
		return chropath;
	}

	public String getFirepath() {
		String chropath = pro.getProperty("firepath");
		return chropath;
	}

	public String getIEpath() {

		String chropath = pro.getProperty("IEpath");
		return chropath;

	}

	// Locale properties
	public String getLocaleLanguage() {
		return pro.getProperty("locale.language");
	}
	
	public String getLocaleCountry() {
		return pro.getProperty("locale.country");
	}
	
// Endevaour file path
	
	public String getExcelEndevaourStringfilepath() {
		return pro.getProperty("EndevaourStringfile");
	}

// MDS properties
	
	public String getExcelMDSpathTemperature() {

		String MDSpath = pro.getProperty("MDSpathtemp");
		return MDSpath;
	}
	
	public String getExcelMDSpathPressure() {

		String MDSpath = pro.getProperty("MDSpathPressure");
		return MDSpath;
	}

	public String getExcelMDSpathFlow() {
		return pro.getProperty("MDSpathFlow");
	}

	public String getExcelMDSpathFill() {
		return pro.getProperty("MDSpathFill");
	}

	// Temperature Properties to be fetched for MDS Validations from config file

	public String getUIfieldTobefetched_GP() {
		return pro.getProperty("UIfildTobefetched_GP");
	}

	public String getUIfieldTobefetched_TP() {
		return pro.getProperty("UIfildTobefetched_TP");
	}

	public String getUIfieldTobefetched_MFP() {
		return pro.getProperty("UIfildTobefetched_MFP");
	}

	public String getUIfieldTobefetched_HoseApp() {
		return pro.getProperty("UIfildTobefetched_Hose/Applicator");
	}

	public String getUIfieldTobefetched_OTT() {
		return pro.getProperty("UIfildTobefetched_OTT");
	}

	public String getUIfieldTobefetched_UTT() {
		return pro.getProperty("UIfildTobefetched_UTT");
	}

	public String getUIfieldTobefetched_Tmpstbck() {
		return pro.getProperty("UIfildTobefetched_Tempstbck");
	}

	public String getUIfieldTobefetched_SMTTmeDly() {
		return pro.getProperty("UIfildTobefetched_SMTTmeDly");
	}

	public String getUIfieldTobefetched_AutoHeaterOffTme() {
		return pro.getProperty("UIfildTobefetched_AutoHeaterOffTme");
	}

	public String getUIfieldTobefetched_SystemSetbckDly() {
		return pro.getProperty("UIfildTobefetched_SystemSetbckDly");
	}
// Pressure Properties to be fetched from config file for validations

	public String getUIfieldTobefetched_MinimumPressureAlrt() {
		return pro.getProperty("UIfildTobefetched_MinPA");
	}

	public String getUIfieldTobefetched_MaximumPressureAlrt() {
		return pro.getProperty("UIfildTobefetched_MaxPA");
	}

	public String getUIfieldTobefetched_PressureSetPoint() {
		return pro.getProperty("UIfildTobefetched_PressureSetPoint");
	}

	public String getUIfieldTobefetched_LowPressureAlertThreshold() {
		return pro.getProperty("UIfildTobefetched_LowPressureAlertThreshold");
	}

	public String getUIfieldTobefetched_HighPressureAlertThreshold() {
		return pro.getProperty("UIfildTobefetched_HighPressureAlertThreshold");
	}

	public String getUIfieldTobefetched_MinimumPressureSetPointRange() {
		return pro.getProperty("UIfildTobefetched_MinimumPressureSetPointRange");
	}

	public String getUIfieldTobefetched_MaximumPressureSetPointRange() {
		return pro.getProperty("UIfildTobefetched_MaximumPressureSetPointRange");
	}

	public String getUIfieldTobefetched_LowSpeedPressureSetting() {
		return pro.getProperty("UIfildTobefetched_LowSpeedPressureSetting");
	}

	public String getUIfieldTobefetched_LowLineSpeedSetting() {
		return pro.getProperty("UIfildTobefetched_LowLineSpeedSetting");
	}

	public String getUIfieldTobefetched_HighSpeedPressureSetting() {
		return pro.getProperty("UIfildTobefetched_HighSpeedPressureSetting");
	}

	public String getUIfieldTobefetched_HighLineSpeedSetting() {
		return pro.getProperty("UIfildTobefetched_HighLineSpeedSetting");
	}

	public String getUIfieldTobefetched_SetMaximumPressureLimit() {
		return pro.getProperty("UIfildTobefetched_SetMaximumPressureLimit");
	}

	public String getUIfieldTobefetched_SetMinimumPressureLimit() {
		return pro.getProperty("UIfildTobefetched_SetMinimumPressureLimit");
	}

	public String getUIfieldTobefetched_FullScaleLineSpeed() {
		return pro.getProperty("UIfildTobefetched_FullScaleLineSpeed");
	}

	// Fill Properties to be fetched from MDS file for Validations
	public String getUIfieldTobefetched_MaximumFillTime() {
		return pro.getProperty("UIfildTobefetched_MaximumFillTime");
	}

	public String getUIfieldTobefetched_TargetFillLevel() {
		return pro.getProperty("UIfildTobefetched_TargetFillLevel");
	}

	public String getUIfiledTobefetched_LowLevelThreshold() {
		return pro.getProperty("UIfildTobefetched_LowLevelThreshold");
	}

	public String getUIfieldTobefetched_LidOpenAlertTime() {
		return pro.getProperty("UIfildTobefetched_LidOpenAlertTime");
	}

	// Sign up url method to fetch the signup url
	public String getSignUpURL() {
		String url = pro.getProperty("SignupUrl");
		return url;
	}

	// Privacy Policy URL
	public String getPrivacyPolicyURL() {
		String policyurl = pro.getProperty("privacyPolicyUrl");
		return policyurl;
	}

	public String getCookiesURL() {
		String cookiesurl = pro.getProperty("CookiesUrl");
		return cookiesurl;
	}

	public String getManageLincenseURL() {
		String lincensesurl = pro.getProperty("LincenseUrl");
		return lincensesurl;
	}

	public String getHelpCenterURL() {
		String HCurl = pro.getProperty("HelpCenterUrl");
		return HCurl;
	}

// Flow Properties to be fetched from config file
	public String getUIfieldTobefetched_TargetAddOn() {
		return pro.getProperty("UIfildTobefetched_TargetAddOn");
	}

	public String getUIfieldTobefetched_LowAlertThreshold() {
		return pro.getProperty("UIfildTobefetched_LowAlertThreshold");
	}

	public String getUIfieldTobefetched_HighAlertThreshold() {
		return pro.getProperty("UIfildTobefetched_HighAlertThreshold");
	}

	public String getUIfieldTobefetched_LowStopThreshold() {
		return pro.getProperty("UIfildTobefetched_LowStopThreshold");
	}

	public String getUIfieldTobefetched_HighStopThreshold() {
		return pro.getProperty("UIfildTobefetched_HighStopThreshold");
	}

	public String getUIfieldTobefetched_AlertDelayCount() {
		return pro.getProperty("UIfildTobefetched_AlertDelayCount");
	}

	public String getUIfieldTobefetched_StopDelayCount() {
		return pro.getProperty("UIfildTobefetched_StopDelayCount");
	}

	public String getUIfieldTobefetched_SpecificGravity() {
		return pro.getProperty("UIfildTobefetched_SpecificGravity");
	}

	public String getUIfieldTobefetched_CalibrationConstantSetting() {
		return pro.getProperty("UIfildTobefetched_CalibrationConstantSetting");
	}

	public String getUIfieldTobefetched_ProductSkipCount() {
		return pro.getProperty("UIfildTobefetched_ProductSkipCount");
	}

	public String getUIfieldTobefetched_ProductstoAverage() {
		return pro.getProperty("UIfildTobefetched_ProductstoAverage");
	}

	public String getUIfieldTobefetched_IdleSkipTime() {
		return pro.getProperty("UIfildTobefetched_IdleSkipTime");
	}

	public String getUIfieldTobefetched_StartupSkipCount() {
		return pro.getProperty("UIfildTobefetched_StartupSkipCount");
	}

}
