package main.java.excelOperations;

import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {
	
	String excelFilePath;
	
	public void pullFile() {
		
	}
	
	public void read(String fileLocation) throws IOException {
		
		this.excelFilePath = fileLocation;
		
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = workbook.getSheet("Input Tab");
		
		// For loop to retrieve data
		int rows = sheet.getLastRowNum();
		int cols = 17;
		
		for(int i = 0; i < rows; i++) {
			
			XSSFRow row = sheet.getRow(i);
			System.out.println("row: " + i);
			System.out.println("col total: " + cols);
			
			for(int j = 0; j < cols; j++) {
				
				try {
					
					XSSFCell cell = row.getCell(j);
					
					switch(cell.getCellType()) {
					
					case STRING: System.out.println("row: " + i + " | col: " + j + " | String Value: " + cell.getStringCellValue());
						break;
					
					case NUMERIC: System.out.println("row: " + i + " | col: " + j + " | Numeric Value: " + cell.getNumericCellValue());
						break;
					
					case BOOLEAN: System.out.println("Boolean Value: " + cell.getBooleanCellValue());
						break;
					 	
					default:
						break;
					}
					
				} catch (NullPointerException e) {
					
					 System.out.println("row: " + i + " | col: " + j +" | null");
				}
			}
			System.out.println("All cells in row " + i + " have been read.");
			System.out.println("");
		}
		workbook.close();
		
	}

}
