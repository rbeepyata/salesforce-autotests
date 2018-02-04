package com.epam.automation.core.report.excel;

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

import com.epam.automation.core.report.ActionResult;
import com.epam.automation.core.report.ScreenshotPolicy;
import com.epam.automation.core.report.TestReporter;
//TODO: JavaDocs and comments
/**
 * Epam Automation - Core
 * @author Raghunandan Beepyata
 * 
 */
//TODO: This was part of a POC for initial design of framework.
public class ExcelTestReporter implements TestReporter {

//	FileInputStream fileInputStream;
//	FileOutputStream fileOutputStream;
//	XSSFWorkbook workbook;
//	XSSFSheet sheet;
	String testReportFilePath;
	private static int testCaseCounter = 1;
	
	@Override
	public void initTestReporter(String testReportFilePath, ScreenshotPolicy screenshotPolicy) {
		this.testReportFilePath = testReportFilePath;
		/*try  {
			fileInputStream = new FileInputStream(new File(testReportFilePath));
			fileOutputStream = new FileOutputStream(new File(testReportFilePath));
			workbook = new XSSFWorkbook(fileInputStream);
			sheet = workbook.getSheet("New Sheet Name");
			
//			workbook.write(fileOutputStream);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	
	}

	@Override
	public void passTestCase(String testCaseName, String... details) { 
	
		/*String testReportFilePath = this.testReportFilePath;
		int indexOfSheet = 0;
		int rowNumber = getCurrentTestCaseNumber();
		int columnNumber = 0;
		
		ExcelWriter.setData(testReportFilePath, indexOfSheet, rowNumber, columnNumber, testCaseName);
		
		ExcelWriter.setData(testReportFilePath, indexOfSheet, rowNumber, columnNumber + 1, "PASS", Color.GREEN);
		
		ExcelWriter.setData(testReportFilePath, indexOfSheet, rowNumber, columnNumber + 2, details[0]);
	*/	
		
		String testReportFilePath = this.testReportFilePath;
		int indexOfSheet = 0;
		int rowNumber = getCurrentTestCaseNumber();
		int columnNumber = 0;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(testReportFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(indexOfSheet);
			
			XSSFRow row = sheet.createRow(rowNumber);

			XSSFCell cell1 = row.createCell(columnNumber);
			cell1.setCellValue(testCaseName);
			
			XSSFCell cell2 = row.createCell(columnNumber + 1);
			cell2.setCellValue("PASS");
			XSSFColor greenColor = new XSSFColor(Color.GREEN);
			 XSSFFont font = workbook.createFont();
		      font.setBold(true);
		      font.setColor(greenColor);
		    XSSFCellStyle style = workbook.createCellStyle();
		    style.setFont(font);
		    cell2.setCellStyle(style);
		    
			XSSFCell cell3 = row.createCell(columnNumber + 2);
			cell3.setCellValue(details[0]);
			
			fileInputStream.close();
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
		
		
		
		incrementTestCaseCounter();
		
	}

	@Override
	public void failTestCase(String testCaseName, String... details) {
		
		String testReportFilePath = this.testReportFilePath;
		int indexOfSheet = 0;
		int rowNumber = getCurrentTestCaseNumber();
		int columnNumber = 0;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(new File(testReportFilePath));
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

			XSSFSheet sheet = workbook.getSheetAt(indexOfSheet);
			
			XSSFRow row = sheet.createRow(rowNumber);

			XSSFCell cell1 = row.createCell(columnNumber);
			cell1.setCellValue(testCaseName);
			
			XSSFCell cell2 = row.createCell(columnNumber + 1);
			cell2.setCellValue("FAIL");
			XSSFColor greenColor = new XSSFColor(Color.RED);
			 XSSFFont font = workbook.createFont();
		      font.setBold(true);
		      font.setColor(greenColor);
		    XSSFCellStyle style = workbook.createCellStyle();
		    style.setFont(font);
		    cell2.setCellStyle(style);
		    
			XSSFCell cell3 = row.createCell(columnNumber + 2);
			cell3.setCellValue(details[0]);
			
			fileInputStream.close();
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
		
		incrementTestCaseCounter();
		
		
	}



	@Override
	public void closeTestReport() {
		/*try {
			workbook.close();
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		*/
	}



	@Override
	public void launchTestReport() {
		// TODO Auto-generated method stub
		
	}
	
	private static void incrementTestCaseCounter(){
		testCaseCounter++;
	}

	private static int getCurrentTestCaseNumber(){
		return testCaseCounter;
	}

	@Override
	public void passTestStep(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failTestStep(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTestCase(String testCaseName, String description) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void passTestStep(ActionResult actionResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failTestStep(ActionResult actionResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScreenshotPolicy getScreenshotPolicy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScreenshotPolicy(ScreenshotPolicy screenshotPolicy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTestReporter(String testReportFilePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTestCase(String testCaseName, String description, String category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String details) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(ActionResult actionResult) {
		// TODO Auto-generated method stub
		
	}
}
