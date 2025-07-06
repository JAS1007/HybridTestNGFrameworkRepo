package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	
	public static final int PAGE_LOAD_TIME = 10;
	
	public static String generateEmailWithTimeStamp() {
		
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_"); 
		return "random"+timestamp+"@gmail.com";
		
	}
	
	public static Object[][] getDataFromExcelFile(String sheetName) {
		
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook = null;
		
		try {
			
			FileInputStream fisExcel = new FileInputStream(f);
			workbook = new XSSFWorkbook(fisExcel);
		
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object [rows][columns];
		
		for(int i = 0; i < rows; i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			
			for (int j = 0; j < columns; j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
					
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
					
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;
					
				}
			}
		}
		
		return data;
		
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		TakesScreenshot t = (TakesScreenshot)driver;
		File srcScreenshot = t.getScreenshotAs(OutputType.FILE);
		String destScreenshotPath = System.getProperty("user.dir") + "/ScreenShots/"+testName+".png";
		File destScreenshot = new File(destScreenshotPath);
		try {
			
			FileUtils.copyFile(srcScreenshot, destScreenshot);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return destScreenshotPath;
		
	}

}
