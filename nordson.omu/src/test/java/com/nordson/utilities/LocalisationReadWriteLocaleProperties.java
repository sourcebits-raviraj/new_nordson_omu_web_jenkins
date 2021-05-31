package com.nordson.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class LocalisationReadWriteLocaleProperties { 

	// Create a HashMap which will store keys and values from xls file provided
	Map< String, String > properties = new LinkedHashMap<String, String>();
    ReadConfig rcf=new ReadConfig();
   
	public void Readwriteproperties() throws Exception { 
    	// Call readExcelFile method .This method will store keys and values to hashMap from loacalisation excel file
    	  readExcelFile();
    	 
		// Call writeToPropertiesFile method .This method will store keys and values from hashMap to properties file
	      writeToPropertiesFile();
	    
	}
    
	public void readExcelFile() throws IOException{ 
		XSSFRow row=null;
		String keyCell="";
		String value="";
		int colnum=0;
	                     
			// Create a FileInputStream by passing the location of excel
			System.out.println(rcf.getExcelEndevaourStringfilepath());
			FileInputStream input = new FileInputStream( System.getProperty("user.dir")+rcf.getExcelEndevaourStringfilepath());         
	
			// Create a Workbook using XSSFWorkbook object
			
			XSSFWorkbook wb=new XSSFWorkbook(input); 

			// get the sheet at location 0 by calling getSheetAt() method of the Workbook
			XSSFSheet sht = wb.getSheetAt(0); 
			
			 switch (rcf.getLocaleLanguage()) {

		        case "English":
		            colnum=2;
		            break;
		        case "Mandarin":
		        	colnum=10;
		            break;
		        case "France":
		            colnum=5;
		            break;    
		        case "German":
		            colnum=3;
		            break;
		        case "Italian":
		            colnum=6;
		            break;
		        case "Spanish":
		            colnum=4;
		            break;
		        case "Dutch":
		            colnum=8;
		            break;
		        case "Portuguese":
		            colnum=9;
		            break;
		        case "Japanese":
		            colnum=7;
		            break;
		        }
			 
			 
			for(int i=1;i<sht.getPhysicalNumberOfRows();i++) {
				row=sht.getRow(i);
				keyCell =row.getCell(1).getStringCellValue();
					Cell c = row.getCell(colnum);
					CellType ctype = c.getCellType();
						if (c != null && ctype != CellType.BLANK && ctype == CellType.STRING) {
							 value = c.getStringCellValue();
							 properties.put(keyCell, value);
						}

						else if (c != null && ctype != CellType.BLANK && ctype == CellType.NUMERIC) {
							double cellvalue = c.getNumericCellValue();
							properties.put(keyCell, String.valueOf(cellvalue));
						}
						
			}
	
			 System.out.println(properties.get(keyCell)+"lnkedhshmp"+properties);
		
	}
	
	public void writeToPropertiesFile() {
		// Creating a new Properties object
		Properties props = new Properties();
		
		
		// Creating a File object which will point to location of properties file
		File propertiesFile = new File(System.getProperty("user.dir")+"/src/test/resources/ApplicationResources_"+rcf.getLocaleLanguage()+".properties");
		try {
			   for (Map.Entry<String,String> entry : properties.entrySet()) {
				props.setProperty(entry.getKey(), entry.getValue());
			    }

			// Finally storing the properties to real properties file.
			 props.store(new FileOutputStream(propertiesFile), null);
			 System.out.println(props);
		
	    }
		catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}