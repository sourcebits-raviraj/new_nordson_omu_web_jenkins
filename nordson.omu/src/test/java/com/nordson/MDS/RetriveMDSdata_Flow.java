package com.nordson.MDS;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import com.nordson.utilities.ReadConfig;
import com.nordson.utilities.XLUtils;

public class RetriveMDSdata_Flow {

	int rwindx = 0;
	String UIfildtobefetched = "";

	// Variables Declared to store the Default1,Min1,Max1 from MDS Flow File


	String default1 ="";
	String min1 = "";
	String max1 = "";
	
	// Variables Declared to store the Default1,Min1,Max1 indexes from MDS Flow File 
	
	int units1=0;
	int default1indx=0;
	int min1indx=0;
	int max1indx=0;

	HashMap<Integer, String> MDSrowhding = new HashMap<Integer, String>();
	MDSGetterandSetters_Flow mdsgs = new MDSGetterandSetters_Flow();
	ReadConfig rcf = new ReadConfig();

	//Method to store column index of MDS Pressure values
	public void getColindx(String path) throws IOException
	{
		int colcount = XLUtils.getCellCount(path, "ProBlue Flex", 1);
		for (int i = 0; i < colcount; i++) {
			MDSrowhding.put(0, XLUtils.getCellData(path, "ProBlue Flex", 0, i));

			for (String lbls : MDSrowhding.values()) {

				if (lbls.contains("Units1"))
					units1 = XLUtils.getColumnindex(path, "ProBlue Flex", "Units1");
				if (lbls.contains("Default1"))
					default1indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Default1");
				if (lbls.contains("Min1"))
					min1indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Min1");
				if (lbls.contains("Max1"))
					max1indx = XLUtils.getColumnindex(path, "ProBlue Flex", "Max1");
			                                          }
		                                       }
	   }

	// Method to extract the values of flow fields from MDS file.
	public void getMDSDataVal(String UIfieldTobefetched) throws IOException {

		String path = System.getProperty("user.dir") + rcf.getExcelMDSpathFlow();
		getColindx(path);
		int colcount = XLUtils.getCellCount(path, "ProBlue Flex", 1);

		for (int i = 0; i < colcount; i++) {
			// storing the heading row values in hashmap
			MDSrowhding.put(0, XLUtils.getCellData(path, "ProBlue Flex", 0, i));

			for (String lbls : MDSrowhding.values()) {
				//checking whether heading row contains UI Label
				if (lbls.contains("UI Label")) {
					int columnindxUIlbl = XLUtils.getColumnindexnum(path, "ProBlue Flex", 0);
					List<String> UIlblcol = XLUtils.getCellDataColindx(path, "ProBlue Flex", 0, columnindxUIlbl);
					for (String colnms : UIlblcol) {
						// Verifying the UI field to be fetched is present or not and storing the row index of that label
						   rwindx = XLUtils.getrowindex(path, "ProBlue Flex", colnms);
							// Getting the cell data from MDS file and setting to setters methods
						   if (colnms.equalsIgnoreCase(UIfieldTobefetched)) {
							mdsgs.setUnits1(XLUtils.getCellData(path, "ProBlue Flex", rwindx, units1));
							String dflt1="";
							dflt1=XLUtils.getCellData(path, "ProBlue Flex", rwindx, default1indx).replaceAll("[-+]*","");	
							if(dflt1.equalsIgnoreCase("As teached or user entered")&& UIfieldTobefetched.equalsIgnoreCase("Target Add-On"))
							dflt1="1000";
							else if(dflt1.contains("target_add_on")) {
								String dfltval[]=dflt1.split(" ");
								dflt1=String.valueOf((int) Math.round(Double.parseDouble(dfltval[0])*Integer.parseInt(mdsgs.getTargetAddon())));
								System.out.println(dflt1+"default val");}
							mdsgs.setDefault1(dflt1);
							min1 = XLUtils.getCellData(path, "ProBlue Flex", rwindx, min1indx).replaceAll("[-+]*","");
							if(min1.contains("target_add_on")) {
								String min1val[]=min1.split(" ");
								min1=String.valueOf((int) Math.round(Double.parseDouble(min1val[0])*Integer.parseInt(mdsgs.getTargetAddon())));
								System.out.println(min1+"min1 val");}
							mdsgs.setMin1(min1);
							max1=XLUtils.getCellData(path, "ProBlue Flex", rwindx, max1indx).replaceAll("[-+]*","");
							if(max1.contains("target_add_on")) {
								String max1val[]=max1.split(" ");
								max1=String.valueOf((int) Math.round(Double.parseDouble(max1val[0])*Integer.parseInt(mdsgs.getTargetAddon())));
								System.out.println(max1+"max1 val");}
							mdsgs.setMax1(max1);
							} } } } } }
							
	public void setUIfieldTobefetched(String UIfieldTobefetched) {
		this.UIfildtobefetched = UIfieldTobefetched;
   	}

}
