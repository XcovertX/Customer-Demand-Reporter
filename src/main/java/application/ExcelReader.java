package main.java.application;

import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {
	
	String excelFilePath;
	
	public void read(String fileLocation) throws IOException {
		
		this.excelFilePath = fileLocation;
		
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = workbook.getSheet("SalesOrders");
		
		// For loop to retrieve data
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();
		
		for(int i = 0; i < rows; i++) {
			
			XSSFRow row = sheet.getRow(i);
			
			for(int j = 0; j < cols; j++) {
				
				XSSFCell cell = row.getCell(j);
				
				switch(cell.getCellType()) {
				
				case STRING: System.out.println("String Value: " + cell.getStringCellValue());
				break;
				
				case NUMERIC: System.out.println("Numeric Value: " + cell.getNumericCellValue());
				break;
				
				case BOOLEAN: System.out.println("Boolean Value: " + cell.getBooleanCellValue());
				break;
				}
			}
			System.out.println("");
			System.out.println("A cells in row " + rows + " have been read.");
		}
		workbook.close();
		
	}

}
