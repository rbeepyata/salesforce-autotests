package com.epam.automation.testng;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReadUtilPoi {

	public HSSFWorkbook workbook = null;
	FileInputStream fileInputStream = null;

	/**
	 * constructor to initialize excel reader
	 * 
	 * @param path
	 */
	public ExcelReadUtilPoi(String path) {
		try {
			fileInputStream = new FileInputStream(path);
			workbook = new HSSFWorkbook(fileInputStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * to read data from shete
	 * 
	 * @param sheet
	 * @return
	 * @throws IOException
	 */
	public List<String> read(HSSFSheet sheet) throws IOException {
		List<String> excelVal = new ArrayList<String>();
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			Row rovVal = (Row) rowIterator.next();
			String rowVal = "";
			for (int i = 0; i < rovVal.getLastCellNum(); i++) {
				HSSFCell cellA1 = (HSSFCell) rovVal.getCell(i);
				rowVal = rowVal + cellA1.getStringCellValue() + "~";
			}

			excelVal.add(rowVal);

		}

		return excelVal;
	}

	// public static void main(String args[]) throws IOException{
	// ExcelReadUtilPoi excelRead = new
	// ExcelReadUtilPoi("src/main/resources/TestPackageDetails.xls");
	// HSSFSheet sheet1 = excelRead.workbook.getSheetAt(0);
	//
	// HashMap<String,String> excelValueMap = new HashMap<String,String>();
	// List<String> excelVal = excelRead.read(sheet1);
	//
	// for(String rowVal : excelVal){
	// String[] col1DatatArray = rowVal.split("~");
	// excelValueMap.put(col1DatatArray[0], col1DatatArray[1]);
	// }
	//
	// System.out.println("::::::"+excelValueMap);
	//
	// }

}
