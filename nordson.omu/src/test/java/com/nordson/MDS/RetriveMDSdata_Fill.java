package com.nordson.MDS;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import com.nordson.utilities.ReadConfig;
import com.nordson.utilities.XLUtils;

public class RetriveMDSdata_Fill {
	

	int rwindx = 0;
	String UIfildtobefetched = "";

	// Variables Declared to store the Default1,Min1,Max1 from MDS Fill File


	String default1 ="";
	String min1 = "";
	String max1 = "";
	
	// Variables Declared to store the Default1,Min1,Max1 indexes from MDS Fill File 
	
	int prmyuntindx=0;
	int default1indx=0;
	int min1indx=0;
	int max1indx=0;


	  
	HashMap<Integer, String> MDSrowhding = new HashMap<Integer, String>();
	MDSGettersandSetters_Fill mdsgs = new MDSGettersandSetters_Fill();
	ReadConfig rcf = new ReadConfig();

	//Method to store column index of MDS Pressure values
	public void getColindx(String path) throws IOException

	{
		int colcount = XLUtils.getCellCount(path, "Settings", 1);
		for (int i = 0; i < colcount; i++) {
			MDSrowhding.put(0, XLUtils.getCellData(path, "Settings", 0, i));

			for (String lbls : MDSrowhding.values()) {

				if (lbls.contains("Primary Units1"))
					prmyuntindx = XLUtils.getColumnindex(path, "Settings", "Primary Units1");
				if (lbls.contains("Default1"))
					default1indx = XLUtils.getColumnindex(path, "Settings", "Default1");
				if (lbls.contains("Min1"))
					min1indx = XLUtils.getColumnindex(path, "Settings", "Min1");
				if (lbls.contains("Max1"))
					max1indx = XLUtils.getColumnindex(path, "Settings", "Max1");

			                                          }
		                                       }
	   }

	// Method to extract the values of fill fields from MDS file.
	public void getMDSDataVal(String UIfieldTobefetched) throws IOException {

		String path = System.getProperty("user.dir") + rcf.getExcelMDSpathFill();
		getColindx(path);
		int colcount = XLUtils.getCellCount(path, "Settings", 1);

		for (int i = 0; i < colcount; i++) {
			// storing the heading row values in hashmap
			MDSrowhding.put(0, XLUtils.getCellData(path, "Settings", 0, i));

			for (String lbls : MDSrowhding.values()) {
				//checking whether heading row contains UI Label
				if (lbls.contains("UI Label")) {
					int columnindxUIlbl = XLUtils.getColumnindexnum(path, "Settings", 0);
					List<String> UIlblcol = XLUtils.getCellDataColindx(path, "Settings", 0, columnindxUIlbl);
					for (String colnms : UIlblcol) {
						// Verifying the UI field to be fetched is present or not and storing the row index of that label
						   rwindx = XLUtils.getrowindex(path, "Settings", colnms);
							// Getting the cell data from MDS file and setting to setters methods
						   if (colnms.equalsIgnoreCase(UIfieldTobefetched)) {
							mdsgs.setPrmryunt(XLUtils.getCellData(path, "Settings", rwindx, prmyuntindx));
							mdsgs.setDefault1(XLUtils.getCellData(path, "Settings", rwindx, default1indx));
							min1 = XLUtils.getCellData(path, "Settings", rwindx, min1indx);
							if(UIfieldTobefetched.equalsIgnoreCase("Target Fill Level"))
							{
								String[] minmium1=min1.split(" ");
							StringBuffer cnvds=new StringBuffer();
							for(int j=0;j<minmium1[1].length();j++) {
								  if (Character.isDigit(min1.charAt(j))) {
									  cnvds.append(min1.charAt(j)); }	}
							mdsgs.setMin1(cnvds.toString());
							}else
								mdsgs.setMin1(min1);
							mdsgs.setMax1(XLUtils.getCellData(path, "Settings", rwindx, max1indx)); } } } } } }
							
	public void setUIfieldTobefetched(String UIfieldTobefetched) {
		this.UIfildtobefetched = UIfieldTobefetched;
   	}

}
