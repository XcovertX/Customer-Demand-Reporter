package main.java.excelOperations;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Modifier {
	
	private XSSFSheet sheet;
	
	public Modifier() { }
	
	public void newRental(String fileLocation, String unitSize, String unitType, String marketing) throws IOException {
		
		setSheet(fileLocation, "Input Tab");
		int rowNum = locateRow(unitSize, unitType);
		int sourceNum = getSource(marketing);
		int numOfUnits = getNumOfUnits(rowNum);
		incrementRented(rowNum, numOfUnits);
		incrementSource(rowNum, sourceNum);
		
	}
	
	private int locateRow(String unitSize, String unitType) {

		int rows = sheet.getLastRowNum();
		
		for (int i = 5; i < rows; i++) {
			
			try {
				
				XSSFRow row = sheet.getRow(i);
				XSSFCell cell = row.getCell(1);
				
				if (unitSize.equals(cell.getStringCellValue())) {
					
					cell = row.getCell(2);
					
					if (unitType.equals(cell.getStringCellValue())) {
						
						return i;
					}
				}	
				
			} catch (NullPointerException e) {}
		}
		return 0;
	}
	
	private int getNumOfUnits(int rowNum) {
		
		return (int) sheet.getRow(rowNum).getCell(3).getNumericCellValue();
	}
	
	public int getSource(String source) {
		
		if(source.equals("Webform")) {
			
			return 4;
			
		} else if(source.equals("Drive-By")) {
			
			return 5;
			
		} else if(source.equals("Call")) {
			
			return 7;
			
		} else if(source.equals("Refferal")) {
			
			return 8;
			
		} else if(source.equals("Loyalty")) {
			
			return 9;
			
		} else {
			
			return 0;
		}
	}
	
	public int incrementRented(int rowNum, int numOfUnits) {
		
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(4);
		
		int newValue = (int) (cell.getNumericCellValue() + 1);
		
		if (!(newValue > numOfUnits)) {
			
			cell.setCellValue( newValue );
			return 1;
			
		} else {
			
			return 0;
		}
		
	}
	
	public int decrementRented(int rowNum, int colNum) {
		
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(colNum);
		
		int newValue = (int) (cell.getNumericCellValue() - 1);
		
		if (!(newValue < 0)) {
			
			cell.setCellValue( newValue );
			return 1;
			
		} else {
			
			return 0;
		}
		
	}
	
	public int incrementSource(int rowNum, int colNum) {
		
		XSSFRow row = sheet.getRow(rowNum);
		XSSFCell cell = row.getCell(colNum);
		
		try {
			
			int newValue = (int) (cell.getNumericCellValue() + 1);
			cell.setCellValue(newValue);
			return 1;
			
		} catch (NullPointerException e) {
			
			cell.setCellValue(1);
			return 0;
		}
	}
	
	public void setSheet(String fileLocation, String sheetName) throws IOException {
		
		FileInputStream inputStream = new FileInputStream(fileLocation);
		
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		
		sheet = workbook.getSheet(sheetName);

		workbook.close();
		
	}

}
