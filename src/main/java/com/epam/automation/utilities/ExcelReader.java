package com.epam.automation.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Epam Automation - Utilities
 * @author Raghunandan Beepyata
 * 
 */

public class ExcelReader {
	
	final static Logger logger = Logger.getLogger(ExcelReader.class);

	public String xlFilePath;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;

	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	
	public ExcelReader(String xlFilePath) {

		this.xlFilePath = xlFilePath;
		try {
			fis = new FileInputStream(xlFilePath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			sheet.getSheetName();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ExcelReader fromSheet(String sheetName) {
		this.sheet = workbook.getSheet(sheetName);
		return this;

	}

	@SuppressWarnings("deprecation")
	public Map<String, String> getDataFromColumn(String columnName) {
		Map<String, String> columnData;

		int columnNumber = -1;
		columnData = new HashMap<String, String>();
		try {
			for (int j = 0; j <= sheet.getRow(0).getLastCellNum(); j++) {
				if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString().isEmpty()) {
					continue;
				} else if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString()
						.equalsIgnoreCase(columnName)) {
					columnNumber = j;
					break;
				}
			}
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				columnData.put(sheet.getRow(i).getCell(0).toString(), sheet.getRow(i).getCell(columnNumber).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnData;
	}

	@SuppressWarnings("deprecation")
	public Map<String, String> getDataFromRow(String rowName) {
		Map<String, String> rowData;

		int rowNumber = -1;
		rowData = new HashMap<String, String>();
		try {
			for (int j = 1; j <= sheet.getPhysicalNumberOfRows(); j++) {
				if (sheet.getRow(j).getCell(0, Row.CREATE_NULL_AS_BLANK).toString().isEmpty()) {
					continue;
				} else if (sheet.getRow(j).getCell(0, Row.CREATE_NULL_AS_BLANK).toString()
						.equalsIgnoreCase(rowName)) {
					rowNumber = j;
					break;
				}
			}
			for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
				rowData.put(sheet.getRow(0).getCell(i).toString(), sheet.getRow(rowNumber).getCell(i).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowData;
	}
	
	
	@SuppressWarnings("deprecation")
	public String[] getDataArrayFromColumn(String columnName) {
		String[] columnData;

		int columnNumber = -1;
		columnData = new String[sheet.getLastRowNum()];
		try {
			for (int j = 0; j <= sheet.getRow(0).getLastCellNum(); j++) {
				if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString().isEmpty()) {
					continue;
				} else if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString()
						.equalsIgnoreCase(columnName)) {
					columnNumber = j;
					break;
				}
			}
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				columnData[i-1]=sheet.getRow(i).getCell(columnNumber).toString();
				//columnData.add(sheet.getRow(i).getCell(columnNumber).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnData;
	}
	
	@SuppressWarnings("deprecation")
	public List<String> getListFromColumn(String columnName) {
		List<String> columnData = new ArrayList<>();

		int columnNumber = -1;
		try {
			for (int j = 0; j <= sheet.getRow(0).getLastCellNum(); j++) {
				if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString().isEmpty()) {
					continue;
				} else if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString()
						.equalsIgnoreCase(columnName)) {
					columnNumber = j;
					break;
				}
			}
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				columnData.add(sheet.getRow(i).getCell(columnNumber).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnData;
	}

	

	@SuppressWarnings("deprecation")
	public String[] getDataArrayFromRow(String rowName) {
		/*String[] rowData;

		int rowNumber = -1;
		rowData = new String[sheet.getRow(1).getPhysicalNumberOfCells()];
		try {
			for (int j = 0; j <= sheet.getRow(0).getLastCellNum(); j++) {
				if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString().isEmpty()) {
					continue;
				} else if (sheet.getRow(0).getCell(j, Row.CREATE_NULL_AS_BLANK).toString()
						.equalsIgnoreCase(rowName)) {
					rowNumber = j;
					break;
				}
			}
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				rowData[i-1]=sheet.getRow(i).getCell(rowNumber).toString();
				//columnData.add(sheet.getRow(i).getCell(columnNumber).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowData;*/
		
		int rowNumber = -1;
		String[] rowData = new String[(sheet.getRow(0).getPhysicalNumberOfCells())-1];

		int numberOfColumns = sheet.getRow(0).getLastCellNum(); 
		for (int j = 0; j <= numberOfColumns; j++) {
			if (sheet.getRow(j).getCell(0, Row.CREATE_NULL_AS_BLANK).toString().isEmpty()) {
				continue;
			} else if (sheet.getRow(j).getCell(0, Row.CREATE_NULL_AS_BLANK).toString()
					.equalsIgnoreCase(rowName)) {
				rowNumber = j;
				break;
			}
		}
		
		
		for (int i = 1; i < (sheet.getRow(rowNumber).getPhysicalNumberOfCells()); i++) {
			rowData[i-1] = sheet.getRow(rowNumber).getCell(i).toString();
			
		}
		return rowData;
	
	}

	
	public String[][] getDataArrayBySheet(String sheetName) {
		int rows = -1;
		int columns = -1;
		String[][] data = null;
		try {
			int index = workbook.getSheetIndex(sheetName);
			boolean flag = false;
			if (index == -1) {
				return null;
			}
			sheet = workbook.getSheetAt(index);
			rows = sheet.getPhysicalNumberOfRows();
			columns = sheet.getRow(0).getLastCellNum();
			data = new String[rows - 1][columns];
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					try {
						data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
					} catch (NullPointerException e) {
						break;
					}
				}
			}
			return data;
		}
		catch (Exception e) {
			return null;
		}
	}


}
