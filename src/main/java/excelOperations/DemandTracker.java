package main.java.excelOperations;

import java.io.IOException;

import javafx.application.Application;
import main.java.application.ExcelReader;
import main.java.application.GUI;

public class DemandTracker {
	
	private String excelFilePath = ".\\datafiles\\21-11 Demand Sheet Puyallup.xlsx";
	
	public DemandTracker(String[] args) {
		
		String date = java.time.LocalDate.now().toString();
		System.out.println("Date: " + date);

		ExcelReader er = new ExcelReader();
		
		try {
			
			er.read(excelFilePath);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Application.launch(GUI.class, args);
	}

	public String getExcelFilePath() {
		
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		
		this.excelFilePath = excelFilePath;
	}
	
	
}
