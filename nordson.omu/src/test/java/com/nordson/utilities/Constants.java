package com.nordson.utilities;

public class Constants {

	// Default Value settings

	public static final String Tank = "177";
	public static final String Manifold = "177";
	public static final String HoseSetTemp = "177";
	public static final String ApplicatorSetTemp = "177";

	// Global Point Values

	public static final String Minglobaltankpntpnt = "40";

	public static final String Maxsetglobaltankpntpnt = "204";
	public static final String Maxsetglobalmanifldpnt = "204";

	public static final String MaxsetglobalHosepnt = "232";
	public static final String MaxsetglobalApplicatorpnt = "232";

	// For Farnheit Value

	public static final String MinglobalMinFarh = "100";

	public static final String MaxsetglobaltankpntpntFarh = "400";
	public static final String MaxsetglobalManifoldpntFarh = "400";

	// System Settings Default value
	public static final String OTTemp = "15";
	public static final String UTTemp = "25";
	public static final String Tempsetback = "56";

	// Pressure Default value
	public static final String PSIMinsetpointdefaultValue = "15";
	public static final String PSIMaxsetpointdefaultValue = "100";
	public static final String KPAMinsetpointdefaultValue = "103";
	public static final String KPAMaxsetpointdefaultValue = "690";
	public static final String BARMinsetpointdefaultValue = "1.03";
	public static final String BARMaxsetpointdefaultValue = "6.9";

	// Runtime Setting Toast msgs

	public static final String TnkpntnullErrmsg = "Enter Valid Tank Set Points";
	public static final String TnkpntErrmsg = "Tank & Manifold should be between 40 and 204";
	public static final String TnkpntErrmsgFH = "Tank & Manifold should be between 100 and 400";

	public static final String Hosecelsius = "Hose husnum should be between 40 and 232";
	public static final String Appcelsius = "Applicator Appnum should be between 40 and 232";

	public static final String ApplictorFH = "Applicator Appnum should be between 100 and 450";
	public static final String HoseFH = "Hose husnum should be between 100 and 450";

	public static final String SucssmsgRuntime = "Temperature Zones updated successfully";

	// System Toast Error message

	public static final String OTTErrmsg = "Over Temperature Threshold should be between 5 and 60";
	public static final String UTTErrmsg = "Under Temperature Threshold should be between 9 and 60";
	public static final String TempstbckErrmsg = "Temperature Setback should be between 5 and 60";

	public static final String OTTErrmsgFH = "Over Temperature Threshold should be between 9 and 108";
	public static final String UTTErrmsgFH = "Under Temperature Threshold should be between 16 and 108";
	public static final String TempstbckErrmsgFH = "Temperature Setback should be between 9 and 108";

	public static final String Sucssmsg = "Temperature Settings updated successfully";
	public static final String Fillsucssmsg = "Pump updated successfully";

	// Successful Message after User Model Registration
	public static final String SucssText = "We have sent you a link!";
	public static final String copyRight = "© 2021 Nordson Corporation";
	public static final String privacyPolicy = "Privacy Policy";
	public static final String termsOfServices = "Terms of Service";
	public static final String CookiesText = "Cookies";
	public static final String ContactUs = "Contact Us";
	public static final String ContactUsHeader = "Contact Us";

	// Sucessful message for Flow
	public static final String FlowSucssmsg = "Flow Updated Successfully";

	public static final String CreateNewNor = "CREATE NEW";
	public static final String PreviousFileText = "USE PREVIOUS FILE";
	public static final String LoadFromUSB = "LOAD FROM USB/COMPUTER";

	public static final String MediaCenterText = "MEDIA CENTER";
	public static final String LincenseText = "MANAGE LICENSES";
	public static final String HelpCenterText = "HELP CENTER";
	public static final String ProfileText = "Profile";
	public static final String FullName = "Full Name";
	public static final String Email = "Email";
	public static final String Country = "Country";
	public static final String PhoneNumber = "Phone Number";
	public static final String PlantName = "Plant Name";
	public static final String CompanyName = "Company Name";
	public static final String CompanyType = "Company Type";
	public static final String Address = "Address";
	public static final String NordosnAccountNumber = "Nordson Account Number";
	public static final String ChangePassword = "Change Password";
	public static final String EditProfile = "Edit Profile";
	public static final String AddSubUserHeader = "ADD SUB-USER";
	public static final String SubUserFullName = " Full Name ";
	public static final String SubUserEmail = " Email ";
	public static final String AddButton = "ADD";
	public static final String CancelButton = "CANCEL";
	public static final String ReadWriteText = "Read/Write";
	public static final String ReadOnly = "Read Only";
	public static final String SubUsersmsg = "Sub User Added Successfully";
	public static final String SubUserRegistration = "Sub User Registered Successfully";

	// Pressure preferences message
	public static final String Pressuresucssmsg = "Pressure updated successfully";
	public static final String Preferencessucssmsg = "Preferences updated successfully";

	public static final String ElctnicadjstPSImaxerrormsg="Minimum and Maximum Pressure Set Point Range difference should be minimum 5 PSI";
	public static final String ElctnicadjstBARmaxerrormsg="Minimum and Maximum Pressure Set Point Range difference should be minimum 0.34 BAR";
	public static final String ElctnicadjstKPAmaxerrormsg="Minimum and Maximum Pressure Set Point Range difference should be minimum 34 kPa";
	public static final String ElctnicadjstPSIlowpressurethholderrormsg="Low Pressure Alert Threshold should be between 5 and 70 PSI";
	public static final String ElctnicadjstPSIhighpressurethholderrormsg="High Pressure Alert Threshold should be between 5 and 70 PSI";
	public static final String ElctnicadjstKPAlowpressurethholderrormsg="Low Pressure Alert Threshold should be between 34 and 483 kPa";
	public static final String ElctnicadjstKPAhighpressurethholderrormsg="High Pressure Alert Threshold should be between 34 and 483 kPa";
	public static final String ElctnicadjstBARlowpressurethholderrormsg="Low Pressure Alert Threshold should be between 0.34 and 4.83 BAR";
	public static final String ElctnicadjstBARhighpressurethholderrormsg="High Pressure Alert Threshold should be between 0.34 and 4.83 BAR";
	
	public static final String ElctnicadjstBARPressuresetpointerrormsg="Pressure Set Point should be between 0.69 and 6.9 BAR";
	public static final String ElctnicadjstBARPressuresetpointerrormsg2="Pressure Set Point should be between 0.7 and 6.9 BAR";
	public static final String ElctnicadjstBARPressuresetpointerrormsg3="Pressure Set Point should be between 0.9 and 6.9 BAR";
	public static final String ElctnicadjstBARPressuresetpointerrormsg4="Pressure Set Point should be 2.2 BAR";
	
	public static final String ElctnicadjstBARMinPressuresetpointRangeerrormsg="Minimum Pressure Set Point Range should be 0.69 BAR";
	public static final String ElctnicadjstBARMaxPressuresetpointRangeerrormsg="Maximum Pressure Set Point Range should be between 1.38 and 6.9 BAR";
	
	public static final String ElctnicadjstKPAPressuresetpointerrormsg="Pressure Set Point should be between 69 and 690 kPa";
	public static final String ElctnicadjstKPAPressuresetpointerrormsg2="Pressure Set Point should be between 70 and 690 kPa";
	public static final String ElctnicadjstKPAPressuresetpointerrormsg3="Pressure Set Point should be between 90 and 690 kPa";
	public static final String ElctnicadjstKPAPressuresetpointerrormsg4="Pressure Set Point should be 220 kPa";
	
	public static final String ElctnicadjstKPAMinPressuresetpointRangeerrormsg="Minimum Pressure Set Point Range should be 69 kPa";
	public static final String ElctnicadjstKPAMaxPressuresetpointRangeerrormsg="Maximum Pressure Set Point Range should be between 138 and 690 kPa";
	
	public static final String ElctnicadjstPSIPressuresetpointerrormsg="Pressure Set Point should be between 10 and 100 PSI";
	public static final String ElctnicadjstPSIPressuresetpointerrormsg2="Pressure Set Point should be between 11 and 100 PSI";
	public static final String ElctnicadjstPSIPressuresetpointerrormsg3="Pressure Set Point should be between 21 and 100 PSI";
	public static final String ElctnicadjstPSIPressuresetpointerrormsg4="Pressure Set Point should be 50 PSI";
	
	public static final String ElctnicadjstPSIMinPressuresetpointRangeerrormsg="Minimum Pressure Set Point Range should be 10 PSI";
	public static final String ElctnicadjstPSIMaxPressuresetpointRangeerrormsg="Maximum Pressure Set Point Range should be between 20 and 100 PSI";
	
	public static final String ElctnicadjstMaxlessMinPressuresetpointRangeerrormsg="Minimum Pressure Setpoint Range should be less than Maximum Pressure Setpoint Range";
	


	
	public static final String MinpresssetptPSIerrormsg = "Minimum Pressure Alert should be between 0 and 95 PSI";
	public static final String MinpresssetptKPAerrormsg = "Minimum Pressure Alert should be between 0 and 655 kPa";
	public static final String MinpresssetptBARerrormsg = "Minimum Pressure Alert should be between 0 and 6.55 BAR";
	public static final String MaxpresssetptPSIerrormsg = "Maximum Pressure Alert should be between 5 and 100 PSI";
	public static final String MaxpresssetptKPAerrormsg = "Maximum Pressure Alert should be between 34 and 690 kPa";
	public static final String MaxpresssetptBARerrormsg = "Maximum Pressure Alert should be between 0.34 and 6.9 BAR";
	public static final String Maxsetptbelowminsetpterrormsg = "Minimum Pressure Alert Range should be less than Maximum Pressure Alert Range";
	public static final String PressalrtrangediffPSIerrormsg = "Pressure Alert range should have a minimum difference of 5 PSI";
	public static final String PressalrtrangediffKPAerrormsg = "Pressure Alert range should have a minimum difference of 34 kPa";
	public static final String PressalrtrangediffBARerrormsg = "Pressure Alert range should have a minimum difference of 0.34 BAR";

	// Password for setting up password
	public static final String pass = "12345678";

}
