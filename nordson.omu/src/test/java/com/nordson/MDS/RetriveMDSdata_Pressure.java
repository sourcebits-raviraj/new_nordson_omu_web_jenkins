package com.nordson.MDS;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import com.nordson.utilities.ReadConfig;
import com.nordson.utilities.XLUtils;

public class RetriveMDSdata_Pressure {

	int rwindx = 0;
	String UIfildtobefetched = "";

	// Variables Declared to store the Default1,Min1,Max1 from MDS File for PSI unit
	
	String default1 = "";
	String min1 = "";
	String max1 = "";

	// Variables Declared to store the Default2,Min2,Max2 from MDS File for BAR unit
	String default2 = "";
	String min2 = "";
	String max2 = "";

	// Variables Declared to store the Default1,Min1,Max1 from MDS File for kPa unit
	String default3 = "";
	String min3 = "";
	String max3 = "";

	// Variables Declared to store the column index of  Default1,Min1,Max1 from MDS File for PSI unit
	int Units1 = 0;
	int dfault1indx = 0;
	int min1indx = 0;
	int max1indx = 0;

	// Variables Declared to store the column index of Default2,Min2,Max2 from MDS File for BAR unit
	int Units2 = 0;
	int dfault2indx = 0;
	int min2indx = 0;
	int max2indx = 0;

	// Variables Declared to store the column index of Default3,Min3,Max3 from MDS File for kPa unit
	int Units3 = 0;
	int dfault3indx = 0;
	int min3indx = 0;
	int max3indx = 0;
  
	HashMap<Integer, String> MDSrowhding = new HashMap<Integer, String>();
	MDSGetterandSetters_Pressure mdsp = new MDSGetterandSetters_Pressure();
	ReadConfig rcf = new ReadConfig();

	//Method to store column index of MDS Pressure values
	public void getColindx(String path) throws IOException

	{
		int colcount = XLUtils.getCellCount(path, "ProBlue Flex", 1);
		for (int i = 0; i < colcount; i++) {
			MDSrowhding.put(0, XLUtils.getCellData(path, "ProBlue Flex", 0, i));

			for (String lbls : MDSrowhding.values()) {

				if (lbls.contains("Units1"))
					Units1 = XLUtils.getColumnindex(path, "ProBlue Flex", "Units1");
				if (lbls.contains("Default1"))
					dfault1indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Default1");
				if (lbls.contains("Min1"))
					min1indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Min1");
				if (lbls.contains("Max1"))
					max1indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Max1");

				if (lbls.contains("Units2"))
					Units2 = XLUtils.getColumnindex(path, "ProBlue Flex", "Units2");
				if (lbls.contains("Default2"))
					dfault2indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Default2");
				if (lbls.contains("Min2"))
					min2indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Min2");
				if (lbls.contains("Max2"))
					max2indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Max2");

				if (lbls.contains("Units3"))
					Units3 = XLUtils.getColumnindex(path, "ProBlue Flex", "Units3");
				if (lbls.contains("Default3"))
					dfault3indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Default3");
				if (lbls.contains("Min3"))
					min3indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Min3");
				if (lbls.contains("Max3"))
					max3indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Max3");

			}

		}

	}

	// Method to extract the values of Pressure fields from MDS file.
	public void getMDSDataVal(String UIfieldTobefetched) throws IOException {

		String path = System.getProperty("user.dir") + rcf.getExcelMDSpathPressure();
		getColindx(path);
		int colcount = XLUtils.getCellCount(path, "ProBlue Flex", 1);

		for (int i = 0; i < colcount; i++) {
			MDSrowhding.put(0, XLUtils.getCellData(path, "ProBlue Flex", 0, i));

			for (String lbls : MDSrowhding.values()) {

				if (lbls.contains("UI Label")) {

					int columnindxUIlbl = XLUtils.getColumnindexnum(path, "ProBlue Flex", 0);
					List<String> UIlblcol = XLUtils.getCellDataColindx(path, "ProBlue Flex", 0, columnindxUIlbl);

					for (String colnms : UIlblcol) {

						if (colnms.trim().equalsIgnoreCase(UIfieldTobefetched)) {
						   if(UIfieldTobefetched.equalsIgnoreCase("Set Point"))
								rwindx=10;
							else		
								rwindx = XLUtils.getrowindex(path, "ProBlue Flex", colnms);
							mdsp.setUnits1(XLUtils.getCellData(path, "ProBlue Flex", rwindx, Units1));
							default1 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, dfault1indx);
							String dflt1 = default1.replaceAll("[-+]*", "");
							StringBuffer cnvds=new StringBuffer();
							for(int j=0;j<dflt1.length();j++) {
								  if (Character.isDigit(dflt1.charAt(j))) {
									  cnvds.append(dflt1.charAt(j)); }	}
							mdsp.setDefault1(cnvds.toString());
							min1 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, min1indx);
							String mn1 = min1.replaceAll("[-+]*", "");
							StringBuffer mn1bf=new StringBuffer();
							// to store only the numeric value from MDS file
							for(int j=0;j<mn1.length();j++) {
								  if (Character.isDigit(mn1.charAt(j))) {
									  mn1bf.append(mn1.charAt(j)); }	}
							mdsp.setMin1(mn1bf.toString());
							max1 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, max1indx);
							String mx1 = max1.replaceAll("[-+]*", "");
							mdsp.setMax1(mx1);
							mdsp.setUnits2(XLUtils.getCellData(path, "ProBlue Flex", rwindx, Units2));
							
							String dflt2val="";
							String dflt2="";
							StringBuffer sbfdf=new StringBuffer();
							default2 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, dfault2indx);
							dflt2val = default2.replaceAll("-+]*", "");
							dflt2=dflt2val.replaceAll("[a-zA-Z/]*","");
							// to store only the numeric value from MDS file and round off to one decimal place wherever required
							for(int j=0;j<dflt2.length();j++) {
								  if (Character.isDigit(dflt2.charAt(j))&& j==3 ) {
									  if(dflt2.charAt(3)=='0' && !(dflt2.charAt(2)=='0')) {
										  DecimalFormat df = new DecimalFormat("#.#");
										  dflt2=sbfdf.append(df.format(Double.parseDouble(dflt2))).toString(); }}}
							mdsp.setDefault2(dflt2);
							
							min2 = XLUtils.getCellData(path,"ProBlue Flex", rwindx, min2indx);
							String mn2 = min2.replaceAll("[-+]*", "");
							mdsp.setMin2(mn2); 
							max2 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, max2indx);
							StringBuffer sbf=new StringBuffer();
						   String  mx2 = max2.replaceAll("[-+]*", "");
							for(int j=0;j<mx2.length();j++) {
								  if (Character.isDigit(mx2.charAt(j)) && j==3) {
									  if(mx2.charAt(3)=='0') {
										  DecimalFormat df = new DecimalFormat("#.#");
										mx2=sbf.append(df.format(Double.parseDouble(mx2))).toString(); }  } }
						    mdsp.setMax2(mx2);			  
							mdsp.setunits3(XLUtils.getCellData(path, "ProBlue Flex", rwindx, Units3));
							default3 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, dfault3indx);
							mdsp.setDefault3(default3.replaceAll("[-+]*", ""));
							min3 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, min3indx);
							mdsp.setMin3(min3.replaceAll("[-+]*", ""));
							max3 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, max3indx);
							mdsp.setMax3(max3.replaceAll("[-+]*", ""));

						}

					}

				}
			}
		}

	}

	public void setUIfieldTobefetched(String UIfieldTobefetched) {

		this.UIfildtobefetched = UIfieldTobefetched;

	}

}
