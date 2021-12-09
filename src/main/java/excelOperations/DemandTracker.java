package main.java.excelOperations;

import java.io.IOException;
import main.java.application.ExcelReader;
import main.java.application.GUI;

public class DemandTracker {
	
	private String excelFilePath;
	private ExcelReader excelReader;
	private ExcelWriter excelWriter;
	private GUI gui;
	
	public DemandTracker() {
		
		String date = java.time.LocalDate.now().toString();
		System.out.println("Date: " + date);

		setExcelFilePath(".\\datafiles\\21-11 Demand Sheet Puyallup.xlsx");
		setExcelWriter(new ExcelWriter());
		setExcelReader(new ExcelReader());
		setGui(new GUI());
		
		Modifier mod = new Modifier();
		
		try {
			
//			mod.newRental(excelFilePath, "10x7.5", "Upper-Temp", "Call");
			mod.terminateRental(excelFilePath, "7x12", "Upper-Temp");
			
		} catch (IOException e) {
			
			System.out.println("Failed to modify the file.");
			e.printStackTrace();
		}
	}
	
	

	public String getExcelFilePath() {
		
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		
		this.excelFilePath = excelFilePath;
	}

	public ExcelReader getExcelReader() {
		
		return excelReader;
	}

	public void setExcelReader(ExcelReader excelReader) {
		
		this.excelReader = excelReader;
	}

	public ExcelWriter getExcelWriter() {
		
		return excelWriter;
	}

	public void setExcelWriter(ExcelWriter excelWriter) {
		
		this.excelWriter = excelWriter;
	}

	public GUI getGui() 
	{
		return gui;
	}


	public void setGui(GUI gui) {
		
		this.gui = gui;
	}
}
