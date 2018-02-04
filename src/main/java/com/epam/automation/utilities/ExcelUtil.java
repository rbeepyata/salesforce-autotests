package com.epam.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Epam Automation - Utilities
 * @author Raghunandan Beepyata
 * 
 */

public class ExcelUtil {
	
	public static void renameSheet(String excelFilePath, String existingSheetName, String newSheetName) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(excelFilePath)));
			XSSFSheet worksheet = workbook.getSheet(existingSheetName);
			workbook.setSheetName(workbook.getSheetIndex(worksheet), newSheetName);
			FileOutputStream fileOutputStream = new FileOutputStream(new File(excelFilePath));
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