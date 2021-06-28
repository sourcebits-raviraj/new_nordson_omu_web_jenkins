package com.nordson.utilities;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

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
		public static final String MaxsetglobalManifoldpntFarh ="400";
		
	
		
		// System Settings Default value
		public static final String OTTemp = "15";
		public static final String UTTemp = "25";
		public static final String Tempsetback = "56";
		
		// Runtime Setting Toast msgs
		
		public static final String TnkpntnullErrmsg="Enter Valid Tank Set Points";
		public static final String TnkpntErrmsg="Manifold should be between 40 and 204";
		public static final String TnkpntErrmsgFH="Manifold should be between 100 and 400";
		
		public static final String Hose1="Hose 1 should be between 40 and 232";
		public static final String Applictor1="Applicator 1 should be between 40 and 232";
		
		public static final String Applictor1FH="Applicator 1 should be between 100 and 450";
		public static final String Hose1FH="Hose 1 should be between 100 and 450";
		
		public static final String SucssmsgRuntime="Temperature Zones updated successfully";
		
		
		//System Toast Error message
		
		public static final String OTTErrmsg="Over Temperature Threshold should be between 5 and 60";
		public static final String UTTErrmsg="Under Temperature Threshold should be between 9 and 60";
		public static final String TempstbckErrmsg="Temperature Setback should be between 5 and 60";
		
		public static final String OTTErrmsgFH= "Over Temperature Threshold should be between 9 and 108";
		public static final String UTTErrmsgFH  ="Under Temperature Threshold should be between 16 and 108";
		public static final String TempstbckErrmsgFH="Temperature Setback should be between 9 and 108";
		
		
		public static final String Sucssmsg="Temperature Settings updated successfully";
		
		//Pump ratio constants
		
		public static final String config_cdmsg_pumpratiochng="Your configuration code has changed. Do you want to update your system configuration?";
		public static final String STD_STD_DA="15";
		public static final String HOD_HO_DA="16";
		public static final String SHO_SHO_SA="12";
		public static final String COD_LP_LV="6";
		public static final String LBD_LP="6";
		public static final String WAD_LV="15";
	}


