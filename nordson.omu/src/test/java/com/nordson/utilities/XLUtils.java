package com.nordson.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String sheetNm;
	public static String excelName;
;

	public static void setExcelSheetNm(String SheetName) {
		XLUtils.sheetNm = SheetName;
	}
	public static void setExceltNm(String ExcelName) {
		XLUtils.excelName = ExcelName;
	}

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public static void setminmaxPressurevaluePSI(String mindata, String maxdata) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test.xlsx";
		File file=new File(path);
        
		wb = new XSSFWorkbook();
		ws = wb.createSheet("Psi_min_max_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Min_Pressure_XML_PSI");
		  ws.getRow(0).createCell(1).setCellValue("Max_Pressure_XML_PSI");
			
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		  ws.getRow(1).createCell(1).setCellValue(maxdata);
		 
		
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	
	public static void setminmaxPressurevalueBAR(String mindata, String maxdata) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test1.xlsx";
		File file=new File(path);
        
		wb = new XSSFWorkbook();
		ws = wb.createSheet("BAR_min_max_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Min_Pressure_XML_BAR");
		  ws.getRow(0).createCell(1).setCellValue("Max_Pressure_XML_BAR");
			
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		  ws.getRow(1).createCell(1).setCellValue(maxdata);
		 
		
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	//setminmaxPressurevalueKPA
	
	public static void setminmaxPressurevalueKPA(String mindata, String maxdata) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test2.xlsx";
		
		File file=new File(path);
        
		  wb = new XSSFWorkbook();
		
		  ws = wb.createSheet("KPA_min_max_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Min_Pressure_XML_KPA");
		  ws.getRow(0).createCell(1).setCellValue("Max_Pressure_XML_KPA");
			
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		  ws.getRow(1).createCell(1).setCellValue(maxdata);
		 
		
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	public static void setvalue(String mindata, String maxdata) throws IOException {
	String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/NorfilePressure_Pneumatic1.xlsx";
	File file=new File(path);
    
	wb = new XSSFWorkbook();
	
	ws=wb.getSheet("Pressure_for_norfile_KPA");
	int totalrow=ws.getLastRowNum()+1;
	for(int i=1;i<totalrow;i++) {
		row =ws.getRow(i);
		String cell=row.getCell(1).getStringCellValue();
		if(row.getCell(1).getStringCellValue()!=null) {
			row.createCell(3).setCellValue("Min_Pressure_XML_KPA");
		}
	}
	}
	public static void setminPressurevaluePSI(String mindata) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test3.xlsx;";
		File file=new File(path);
		wb = new XSSFWorkbook();
		ws = wb.createSheet("PSI_min_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Min_Pressure_XML_PSI");
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	public static void setmaxPressurevaluePSI(String maxdata ) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test4.xlsx";
		File file=new File(path);
        
		wb = new XSSFWorkbook();
		ws = wb.createSheet("PSI_min_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Max_Pressure_XML_PSI");
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(maxdata);
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	public static void setminPressurevalueBAR(String mindata) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test5.xlsx";
		File file=new File(path);
        
		wb = new XSSFWorkbook();
		ws = wb.createSheet("PSI_min_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Min_Pressure_XML_BAR");
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	public static void setmaxPressurevalueBAR(String maxdata ) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test6.xlsx";
		File file=new File(path);
        
		wb = new XSSFWorkbook();
		ws = wb.createSheet("PSI_min_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Max_Pressure_XML_BAR");
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(maxdata);
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	public static void setminPressurevalueKPA(String mindata) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test7.xlsx";
		File file=new File(path);
        
		wb = new XSSFWorkbook();
		ws = wb.createSheet("PSI_min_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Min_Pressure_XML_KPA");
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	public static void setmaxPressurevalueKPA(String maxdata ) throws IOException {
		String path = System.getProperty("user.dir") + "./XMLOutputfolder/Test8.xlsx";
		File file=new File(path);
        
		wb = new XSSFWorkbook();
		ws = wb.createSheet("PSI_min_xml");
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue("Max_Pressure_XML_KPA");
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(maxdata);
		fo = new FileOutputStream(file);
		wb.write(fo);
		wb.close();

		fo.close();

	}
	
	
	  public static void setsheet(String Nor_min_UI,String mindata) throws IOException, InvalidFormatException { String
	  path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/norminuixml.xlsx"; 
	  File file=new File(path);
	  fi=new FileInputStream(file);
	  wb = new XSSFWorkbook(file);
	  System.out.println(ws.getSheetName());
	  int totalRow = ws.getLastRowNum()+1;
	  
	  for(int i=1;i<totalRow;i++) {
		  
		  row=ws.getRow(i);
		  
		  String cell=row.getCell(1).getStringCellValue();
		  
		  if(cell.contains(Nor_min_UI)) {
			  row.createCell(2).setCellValue("Min_Pressure_XML_KPA");
		
	  
	  
	  ws.getRow(0).createCell(0).setCellValue("Min_Pressure_XML_KPA");
	  ws.createRow(1); ws.getRow(1).createCell(0).setCellValue(mindata); 
	  fo = new FileOutputStream(file);
	  wb.write(fo);
	  wb.close();
	  
	  fo.close();
	  }
}
	 
	  }	

	

	public static int getColumnindexnum(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int colnum = cell.getColumnIndex();
		return colnum;
	}

	public static int getColumnindex(String xlfile, String xlsheet, String Colnm) throws IOException {

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(0);
		int colindx = 0;
		for (Cell cell : row) {
			if (cell.getStringCellValue().equalsIgnoreCase(Colnm))
				colindx = cell.getColumnIndex();
			else
				System.out.println("Colnname not found");
		}
		return colindx;
	}

	public static List<String> getCellDataColindx(String xlfile, String xlsheet, int rownum, int colnum)
			throws IOException {

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);

		List<String> UIlabl = new ArrayList<String>();

		if (colnum != 0) {
			for (Row row : ws) {
				Cell c = row.getCell(colnum);
				CellType ctype = c.getCellType();

				if (c != null && ctype != CellType.BLANK && ctype == CellType.STRING) {
					String cllvalue = c.getStringCellValue();
					UIlabl.add(cllvalue);

				}

				else if (c != null && ctype != CellType.BLANK && ctype == CellType.NUMERIC) {
					double cllvalue = c.getNumericCellValue();
					UIlabl.add(String.valueOf(cllvalue));
				}
			}

		}

		return UIlabl;

	}

	public static int getrowindex(String xlfile, String xlsheet, String UIfild) throws IOException {

		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rwindx = 0;
		for (Row row : ws) {
			for (Cell cell : row) {
				CellType ctype = cell.getCellType();

				if (ctype == CellType.STRING) {
					if (cell.getStringCellValue().equals(UIfild)) {

						rwindx = row.getRowNum();

					}

				}
			}

		}
		return rwindx;

	}

	@DataProvider(name = "GlobalPointValues_Celsius")
	public static String[][] getDataGP_Celsius() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "GlobalPointValues_Farnhenit")
	public static String[][] getDataGP_Farnheit() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "TankPointValues_Celsius")
	public static String[][] getDataTP_Celsius() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "TankPointValues_Farnhenit")
	public static String[][] getDataTP_Farnheit() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "HoseAppPointValues_Celsius")
	public static String[][] getDataHAP_Celsius() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "HoseAppPointValues_Farnhenit")
	public static String[][] getDataHAP_Farnheit() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "OTTValues_Celsius")
	public static String[][] getDataOTT_Celsius() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "OTTValues_Farnheit")
	public static String[][] getDataOTT_Farnheit() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "UTTValues_Celsius")
	public static String[][] getDataUTT_Celsius() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "UTTValues_Farnheit")
	public static String[][] getDataTT_Farnheit() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "TempstbckValues_Celsius")
	public static String[][] getDataTempstbck_Celsius() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "TempstbckValues_Farnheit")
	public static String[][] getDataTempstbck_Farnheit() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/Temperature.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String tempdata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				tempdata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return tempdata;
	}

	@DataProvider(name = "PressureValuesMinMax0_0")
	public static String[][] getData_0_0() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}

	@DataProvider(name = "PressureValuesMinMax_0_691")
	public static String[][] getDataZero_Max_Value_691() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}

	@DataProvider(name = "PressureValuesMinMax_0_690")
	public static String[][] getDataZero_Max_Value_690() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}

	@DataProvider(name = "PressureValues_BAR_MinMax_7_7")
	public static String[][] getData_BAR_Max_Value_7_7() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;

	}

	@DataProvider(name = "PressureValuesMinMax_1.9_2")
	public static String[][] getData_BAR_Max_Value_19_2() throws IOException

	{

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}

	@DataProvider(name = "PressureValues_MinMax_0_6.9")
	public static String[][] getData_BAR_Max_Value_0_69() throws IOException

	{

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}

	@DataProvider(name = "PressureValues_PSI_MinMax_50_51")
	public static String[][] getData_PSI_Max_Value_50_51() throws IOException

	{

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}

	@DataProvider(name = "PressureValues_PSI_MinMax_0_101")
	public static String[][] getData_PSI_Max_Value_0_101() throws IOException

	{

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}

	@DataProvider(name = "PressureValues_PSI_MinMax_0_100")
	public static String[][] getData_PSI_Max_Value_0_100() throws IOException

	{

		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/PressureValues.xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		int colcnt = colcount - 1;

		System.out.println("No of Rows= " + rownum);
		System.out.println("No of Columns= " + colcnt);
		String pressuredata[][] = new String[rownum][colcnt];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j <= colcnt; j++) {
				pressuredata[i - 1][j - 1] = XLUtils.getCellData(path, sheetNm, i, j);// 1 1

			}

		}
		return pressuredata;
	}
	// Data Provider for Nor file XML Verfication
	@DataProvider(name = "min_Presure_for_norfile_KPA_manualadjust")
	public static String[][] setminPressureForNorFileKPA() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);}
		}
		return minpressueDataforNor;}
	@DataProvider(name = "max_Presure_for_norfile_KPA_manualadjust")
	public static String[][] setmaxPressureForNorFileKPA() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
	
		String maxpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				maxpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);			}
		}
		return maxpressueDataforNor;}
	@DataProvider(name = "min_Presure_for_norfile_BAR_manualadjust")
	public static String[][] setminPressureForNorFileBAR() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);}}
		return minpressueDataforNor;
	}
	@DataProvider(name = "max_Presure_for_norfile_BAR_manualadjust")
	public static String[][] setmaxPressureForNorFileBAR() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String maxpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				maxpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);		}}
		return maxpressueDataforNor;
	}
	
	@DataProvider(name = "min_Presure_for_norfile_PSI_manualadjust")
	public static String[][] setminPressureForNorFilePSI() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);	}}
		return minpressueDataforNor;
	}
	
	@DataProvider(name = "max_Presure_for_norfile_PSI_manualadjust")
	public static String[][] setmaxPressureForNorFilePSI() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String maxpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				maxpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);	}		}
		return maxpressueDataforNor;
	}
	
	@DataProvider(name = "min_max_Presure_for_norfile_KPA_manualadjust")
	public static String[][] setminmaxPressureForNorFileKPA() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minmaxpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minmaxpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);}}
		return minmaxpressueDataforNor;
	}
	@DataProvider(name = "min_max_Presure_for_norfile_BAR_manualadjust")
	public static String[][] setminmaxPressureForNorFileBAR() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minmaxpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minmaxpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);	}}
		return minmaxpressueDataforNor;
	}
	@DataProvider(name = "min_max_Presure_for_norfile_PSI_manualadjust")
	public static String[][] setminmaxPressureForNorFilePSI() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	public static void setNorXMLValues_Pressure_Min_or_Max(String sheetnm,String Xmltag,String mindata) throws IOException, InvalidFormatException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		wb= new XSSFWorkbook(new FileInputStream(path));
		System.out.println(wb.getNumberOfSheets());
			 if (wb.getNumberOfSheets()!=0) {
				 for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					    if (wb.getSheetName(i).equals(sheetnm)) {
					    	wb.removeSheetAt(wb.getSheetIndex(sheetnm));}
					    }
				 ws = wb.createSheet(sheetnm);}
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue(Xmltag);
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		  fo = new FileOutputStream(new File(path));
		  wb.write(fo);
		  wb.close();
		  fo.close();
	}
	
	public static void setNorXMLValues_Pressure_Min_and_Max(String sheetnm,String mintag,String maxtag,String mindata,String maxdata) throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		wb= new XSSFWorkbook(new FileInputStream(path));
		System.out.println(wb.getNumberOfSheets());
			 if (wb.getNumberOfSheets()!=0) {
				 for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					    if (wb.getSheetName(i).equals(sheetnm)) {
					    	wb.removeSheetAt(wb.getSheetIndex(sheetnm));}
					    }
				 ws = wb.createSheet(sheetnm);}
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue(mintag);
		  ws.getRow(0).createCell(1).setCellValue(maxtag);
			
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(mindata);
		  ws.getRow(1).createCell(1).setCellValue(maxdata);
	     
		  fo = new FileOutputStream(new File(path));
		  wb.write(fo);
		  wb.close();

		  fo.close();
	}
	@DataProvider(name = "Presure_for_norfile_KPA_electronicadjust")
	public static String[][] setminmaxPressureForNorFilKPAeleadjust() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	
	@DataProvider(name = "Presure_for_norfile_BAR_electronicadjust")
	public static String[][] setminmaxPressureForNorFileBAReleadjust() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	

	@DataProvider(name = "Presure_for_norfile_PSI_electronicadjust")
	public static String[][] setminmaxPressureForNorFilePSIeleadjust() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	
	
	public static void setNorXMLValues_Pressure_electronicadjust(String sheetnm,String prssstpnttg,String prssurstpnt,String lowlvlthrshldtag,String lowlvlthrshld,
			String hghlvlthrshldtag,String hghlvlthrshld,String minprsprngtag,String minprsprng,String maxprsprngtag,String maxprsprng) throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		wb= new XSSFWorkbook(new FileInputStream(path));
		System.out.println(wb.getNumberOfSheets());
			 if (wb.getNumberOfSheets()!=0) {
				 for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					    if (wb.getSheetName(i).equals(sheetnm)) {
					    	wb.removeSheetAt(wb.getSheetIndex(sheetnm));}
					    }
				 ws = wb.createSheet(sheetnm);}
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue(prssstpnttg);
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(prssurstpnt);
		 
		  ws.getRow(0).createCell(1).setCellValue(lowlvlthrshldtag);
		  ws.getRow(1).createCell(1).setCellValue(lowlvlthrshld);
		  
		  ws.getRow(0).createCell(2).setCellValue(hghlvlthrshldtag);
		  ws.getRow(1).createCell(2).setCellValue(hghlvlthrshld);
		  
		  ws.getRow(0).createCell(3).setCellValue(minprsprngtag);
		  ws.getRow(1).createCell(3).setCellValue(minprsprng);
		  
		  ws.getRow(0).createCell(4).setCellValue(maxprsprngtag);
		  ws.getRow(1).createCell(4).setCellValue(maxprsprng);
		  
		  fo = new FileOutputStream(new File(path));
		  wb.write(fo);
		  wb.close();
		  fo.close();
	 }
	
	@DataProvider(name = "Presure_for_norfile_KPA_RunUp")
	public static String[][] setminmaxPressureForNorFileKPARunup() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	
	@DataProvider(name = "Presure_for_norfile_BAR_RunUp")
	public static String[][] setminmaxPressureForNorFileBARRunup() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	
	@DataProvider(name = "Presure_for_norfile_PSI_RunUp")
	public static String[][] setminmaxPressureForNorFilePSIRunup() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	
	public static void setNorXMLValues_Pressure_Runup(String sheetnm,String lowlvlthrshldtag,String lowlvlthrshld,
			String hghlvlthrshldtag,String hghlvlthrshld,String lwspdpresssttngtag,String lwspdpresssttng,
			String hghspdpresssttngtag,String hghspdpresssttng,String stmxprslmttag,String stmxprslmt,
			String stminprslmttag,String stminprslmt
			) throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		wb= new XSSFWorkbook(new FileInputStream(path));
		System.out.println(wb.getNumberOfSheets());
			 if (wb.getNumberOfSheets()!=0) {
				 for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					    if (wb.getSheetName(i).equals(sheetnm)) {
					    	wb.removeSheetAt(wb.getSheetIndex(sheetnm));}
					    }
				 ws = wb.createSheet(sheetnm);}
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  
		  ws.createRow(0);
		  ws.createRow(1);
		  ws.getRow(0).createCell(0).setCellValue(lowlvlthrshldtag);
		  ws.getRow(1).createCell(0).setCellValue(lowlvlthrshld);
		 
		  ws.getRow(0).createCell(1).setCellValue(hghlvlthrshldtag);
		  ws.getRow(1).createCell(1).setCellValue(hghlvlthrshld);
		  
		  ws.getRow(0).createCell(2).setCellValue(lwspdpresssttngtag);
		  ws.getRow(1).createCell(2).setCellValue(lwspdpresssttng);
		
		  
		  ws.getRow(0).createCell(3).setCellValue(hghspdpresssttngtag);
		  ws.getRow(1).createCell(3).setCellValue(hghspdpresssttng);
		  
		
		  ws.getRow(0).createCell(4).setCellValue(stmxprslmttag);
		  ws.getRow(1).createCell(4).setCellValue(stmxprslmt);
		  
		  ws.getRow(0).createCell(5).setCellValue(stminprslmttag);
		  ws.getRow(1).createCell(5).setCellValue(stminprslmt);
		  
	
		  
		  fo = new FileOutputStream(new File(path));
		  wb.write(fo);
		  wb.close();
		  fo.close();
	 }
	
	@DataProvider(name = "Presure_for_norfile_RunUp_LineSpeed")
	public static String[][] Presurefornorfile_RunUp_LineSpeed() throws IOException {
		String path = System.getProperty("user.dir") + "./src/test/java/com/nordson/testData/"+excelName+".xlsx";

		int rownum = XLUtils.getRowCount(path, sheetNm);
		int colcount = XLUtils.getCellCount(path, sheetNm, 1);
		String minpressueDataforNor[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				minpressueDataforNor[i - 1][j] = XLUtils.getCellData(path, sheetNm, i, j);} }
		return minpressueDataforNor;
	}
	
	
	public static void setNorXMLValues_Pressure_Runup_LineSpeed(String sheetnm,
			String lwlnspdpresssttngtag,String lwlnspdpresssttng,String hghlnspdpresssttngtag,String hghlnspdpresssttng,String fullscllnspdtag,String fullscllnspd
			) throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/nordson/testData/"+excelName+".xlsx";
		wb= new XSSFWorkbook(new FileInputStream(path));
		System.out.println(wb.getNumberOfSheets());
			 if (wb.getNumberOfSheets()!=0) {
				 for (int i = 0; i < wb.getNumberOfSheets(); i++) {
					    if (wb.getSheetName(i).equals(sheetnm)) {
					    	wb.removeSheetAt(wb.getSheetIndex(sheetnm));}
					    }
				 ws = wb.createSheet(sheetnm);}
		  System.out.println(ws.getFirstRowNum());
		  System.out.println(ws.getLastRowNum()); 
		  
		  ws.createRow(0);
		  ws.getRow(0).createCell(0).setCellValue(lwlnspdpresssttngtag);
		  ws.createRow(1);
		  ws.getRow(1).createCell(0).setCellValue(lwlnspdpresssttng);
		 
		  ws.getRow(0).createCell(1).setCellValue(hghlnspdpresssttngtag);
		  ws.getRow(1).createCell(1).setCellValue(hghlnspdpresssttng);
		  
		  ws.getRow(0).createCell(2).setCellValue(fullscllnspdtag);
		  ws.getRow(1).createCell(2).setCellValue(fullscllnspd);
		  
		  fo = new FileOutputStream(new File(path));
		  wb.write(fo);
		  wb.close();
		  fo.close();
	}  
	
}