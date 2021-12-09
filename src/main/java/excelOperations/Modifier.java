package main.java.excelOperations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Modifier {
	
	private XSSFWorkbook workbook;
	private XSSFSheet YTDDemandSummarySheet;
	private XSSFSheet inputTabSheet;
	
	public Modifier() { }
	
	public void newRental(String fileLocation, String unitSize, String unitType, String marketing) throws IOException {
		
		readFromFile(fileLocation);
		int rowNum = locateRow(unitSize, unitType);
		int sourceNum = getSource(marketing);
		int numOfUnits = getNumOfUnits(rowNum);
		incrementRented(unitSize, unitType, rowNum, numOfUnits, sourceNum);
		evaluateCells();
		writeToFile(fileLocation);	
	}
	
	public void terminateRental(String fileLocation, String unitSize, String unitType) throws IOException {
		
		readFromFile(fileLocation);
		int rowNum = locateRow(unitSize, unitType);
		boolean transferring = false;
		
		decrementRented(unitSize, unitType, rowNum, transferring);
		evaluateCells();
		writeToFile(fileLocation);	
	}
	
	public void transferRental(String fileLocation, 
							   String unitSizeFrom, String unitTypeFrom,
			 				   String unitSizeTo,   String unitTypeTo) throws IOException {
		
		readFromFile(fileLocation);
		
		int rowNumFrom = locateRow(unitSizeFrom, unitTypeFrom);
		int rowNumTo = locateRow(unitSizeTo, unitTypeTo);
		int numOfUnitsTo = getNumOfUnits(rowNumTo);
		int noSource = -1;
		boolean transferring = true;
		int rentedColumn = 4;
		
		XSSFRow rowFrom = inputTabSheet.getRow(rowNumFrom);
		XSSFCell cellFrom = rowFrom.getCell(rentedColumn);
		
		XSSFRow rowTo = inputTabSheet.getRow(rowNumTo);
		XSSFCell cellTo = rowTo.getCell(rentedColumn);
		
		int newValueFrom = (int) (cellFrom.getNumericCellValue() - 1);
		int newValueTo = (int) (cellTo.getNumericCellValue() + 1);
		
		if (newValueFrom >= 0 && newValueTo <= numOfUnitsTo) {
			
			decrementRented(unitSizeFrom, unitTypeFrom, rowNumFrom, transferring);
			incrementRented(unitSizeTo, unitTypeTo, rowNumTo, numOfUnitsTo, noSource);
			System.out.println("Transferred!");
			
		} else {
			
			System.out.println("Failed to transfer!");
		}
		
		evaluateCells();
		writeToFile(fileLocation);
	}
	
	public void addDemand(String fileLocation, String unitSize, String unitType, String marketing) throws IOException {
		
		readFromFile(fileLocation);

		int rowNum = locateRow(unitSize, unitType);
		int sourceNum = getSource(marketing);
		
		incrementSource(rowNum, sourceNum);	
		
		evaluateCells();
		writeToFile(fileLocation);
	}
	
	private int locateRow(String unitSize, String unitType) {

		int rows = inputTabSheet.getLastRowNum();
		
		for (int i = 5; i < rows; i++) {
			
			try {
				
				XSSFRow row = inputTabSheet.getRow(i);
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
		
		return (int) inputTabSheet.getRow(rowNum).getCell(3).getNumericCellValue();
	}
	
	private int getSource(String source) {
		
		if(source.equals("Webform")) {
			
			return 7;
			
		} else if(source.equals("Drive-By")) {
			
			return 8;
			
		} else if(source.equals("Call")) {
			
			return 10;
			
		} else if(source.equals("Refferal")) {
			
			return 11;
			
		} else if(source.equals("Loyalty")) {
			
			return 12;
			
		} else {
			
			return 0;
		}
	}
	
	private int incrementRented(String unitSize, String unitType, int rowNum, int numOfUnits, int sourceNum) {
		
		int rentedColumn = 4;
		
		XSSFRow row = inputTabSheet.getRow(rowNum);
		XSSFCell cell = row.getCell(rentedColumn);
		
		int newValue = (int) (cell.getNumericCellValue() + 1);
		
		if (!(newValue > numOfUnits)) {
			
			cell.setCellValue( newValue );
			
			if (sourceNum >= 0) {
				
				incrementSource(rowNum, sourceNum);
				incrementRentals(rowNum);
			}
			
			return 1;
			
		} else {
			
			System.out.println("There are no more " + unitType + " " + unitSize + "'s available to rent.");
			return 0;
		}
		
	}
	
	private int incrementSource(int rowNum, int colNum) {
		
		XSSFRow row = inputTabSheet.getRow(rowNum);
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
	
	private int incrementRentals(int rowNum) {
		
		int rentalsColumn = 14;
		
		XSSFRow row = inputTabSheet.getRow(rowNum);
		XSSFCell cell = row.getCell(rentalsColumn);
		
		try {
			
			int newValue = (int) (cell.getNumericCellValue() + 1);
			cell.setCellValue(newValue);
			return 1;
			
		} catch (NullPointerException e) {
			
			cell.setCellValue(1);
			return 0;
		}
		
	}
	
	private int decrementRented(String unitSize, String unitType, int rowNum, boolean isTransferring) {
		
		int rentedColumn = 4;
		
		XSSFRow row = inputTabSheet.getRow(rowNum);
		XSSFCell cell = row.getCell(rentedColumn);
		
		int newValue = (int) (cell.getNumericCellValue() - 1);
		
		if (!(newValue < 0)) {
			
			cell.setCellValue(newValue);
			
			if (!isTransferring) {
				
				incrementVacates(rowNum);
			}
			return 1;
			
		} else {
			
			System.out.println("All of the " + unitType + " " + unitSize + "'s are currently available.");
			return 0;
		}
		
	}
	
	private int incrementVacates(int rowNum) {
		
		int rentalsColumn = 15;
		
		XSSFRow row = inputTabSheet.getRow(rowNum);
		XSSFCell cell = row.getCell(rentalsColumn);
		
		try {
			
			int newValue = (int) (cell.getNumericCellValue() + 1);
			cell.setCellValue(newValue);
			return 1;
			
		} catch (NullPointerException e) {
			
			cell.setCellValue(1);
			return 0;
		}
		
	}
	
	private void readFromFile(String fileLocation) throws IOException {
		
		FileInputStream inputStream = new FileInputStream(fileLocation);
		
		workbook = new XSSFWorkbook(inputStream);
		
		YTDDemandSummarySheet = workbook.getSheet("YTD Demand Summary");
		inputTabSheet = workbook.getSheet("Input Tab");

		inputStream.close();
	}
	
	private void writeToFile(String fileLocation) throws IOException {
		
		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	private void evaluateCells() {
		
		XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
	}

}
