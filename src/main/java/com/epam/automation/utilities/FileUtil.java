package com.epam.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;

/**
 * Epam Automation - Utilities
 * @author Raghunandan Beepyata
 * 
 */

public class FileUtil {
	
	public static void copy(String sourceFilePath, String targetFilePath) {
		Path template = Paths.get(sourceFilePath);
		Path testReportFile = Paths.get(targetFilePath);
		try {
			Files.copy(template, testReportFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean makeDirectories(String filepath) {
		return new File(filepath).mkdirs();
	}

	public static boolean makeParentDirectories(String filepath) {
		return new File(filepath).getParentFile().mkdirs();
	}
	
	public static void deleteDirectory(String filepath) {
		File file = new File(filepath);
		try {
			FileUtils.deleteDirectory(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		boolean isDirectoryCreated = file.mkdir();
//		if (isDirectoryCreated) {
////			System.out.println("successfully made");
//		} else {
////			file.mkdir();
////			System.out.println("deleted and made");
//		}
	}

}
