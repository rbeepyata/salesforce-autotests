package com.epam.automation.utilities;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Epam Automation - Utilities
 * @author Raghunandan Beepyata
 * 
 */

public class ExcelWriter {
	
	private String xlFilePath;
	/*private FileInputStream fis = null;
	private FileOutputStream fileOut = null;

	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;*/
	
	/*public ExcelWriter(String xlFilePath) {

		this.xlFilePath = xlFilePath;
		try {
			fis = new FileInputStream(xlFilePath);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	
	public void setData(int indexOfSheet, int rowNumber, int columnNumber, String valueToWrite){
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(xlFilePath)));
			XSSFSheet sheet = workbook.getSheetAt(indexOfSheet);
			
			XSSFRow row = sheet.createRow(rowNumber);

			XSSFCell cell = row.createCell(columnNumber);
			cell.setCellValue(valueToWrite);
			
			FileOutputStream fileOutputStream = new FileOutputStream(xlFilePath);
			workbook.write(fileOutputStream);
			workbook.close();
//			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setData(int indexOfSheet, int rowNumber, int columnNumber, String valueToWrite, Color color){
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(xlFilePath)));
			XSSFSheet sheet = workbook.getSheetAt(indexOfSheet);
			
			XSSFRow row = sheet.createRow(rowNumber);

			XSSFCell cell = row.createCell(columnNumber);
			cell.setCellValue(valueToWrite);
			
			XSSFColor greenColor = new XSSFColor(color);
			 XSSFFont font = workbook.createFont();
		      font.setBold(true);
		      font.setColor(greenColor);
		    XSSFCellStyle style = workbook.createCellStyle();
		    style.setFont(font);
		    cell.setCellStyle(style);

			FileOutputStream fileOutputStream = new FileOutputStream(xlFilePath);
			workbook.write(fileOutputStream);
			workbook.close();
//			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void setData(String testReportFilePath, int indexOfSheet, int rowNumber, int columnNumber, String valueToWrite){
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(testReportFilePath)));
			XSSFSheet sheet = workbook.getSheetAt(indexOfSheet);
			
			XSSFRow row = sheet.createRow(rowNumber);

			XSSFCell cell = row.createCell(columnNumber);
			cell.setCellValue(valueToWrite);
		    
			FileOutputStream fileOutputStream = new FileOutputStream(testReportFilePath);
			workbook.write(fileOutputStream);
			workbook.close();
			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setData(String testReportFilePath, int indexOfSheet, int rowNumber, int columnNumber, String valueToWrite, Color color){
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(testReportFilePath)));
			XSSFSheet sheet = workbook.getSheetAt(indexOfSheet);
			
			XSSFRow row = sheet.createRow(rowNumber);

			XSSFCell cell = row.createCell(columnNumber);
			cell.setCellValue(valueToWrite);
			XSSFColor greenColor = new XSSFColor(color);
			 XSSFFont font = workbook.createFont();
		      font.setBold(true);
		      font.setColor(greenColor);
		    XSSFCellStyle style = workbook.createCellStyle();
		    style.setFont(font);
		    cell.setCellStyle(style);
		    
			FileOutputStream fileOutputStream = new FileOutputStream(testReportFilePath);
			workbook.write(fileOutputStream);
			workbook.close();
			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
