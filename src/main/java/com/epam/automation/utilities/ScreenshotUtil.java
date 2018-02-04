package com.epam.automation.utilities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.epam.automation.core.report.Screenshot;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenshotUtil {

	public static Screenshot takeScreenshot(WebDriver driver, String screenshotFilePath) {
		
		ru.yandex.qatools.ashot.Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	    File screenshotFile = new File(screenshotFilePath);
		try {
			ImageIO.write(screenshot.getImage(),"PNG", screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Screenshot(screenshotFilePath, screenshotFile);
	}

	public static Screenshot takeScreenshot2(WebDriver driver, String screenshotFilePath) {
		//Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(screenshotFilePath);

                //Copy file at destination

                try {
					FileUtils.copyFile(SrcFile, DestFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                return new Screenshot(screenshotFilePath, DestFile);

	}
	
	
}
