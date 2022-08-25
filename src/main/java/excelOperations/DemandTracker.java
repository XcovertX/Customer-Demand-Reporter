package excelOperations;

import application.ExcelReader;
import application.GUI;

public class DemandTracker {
	
	public static DemandTracker dt;
	private String excelFilePath;
	private ExcelReader excelReader;
	private ExcelWriter excelWriter;
	private GUI gui;
	
	public DemandTracker() {
		
		dt = this;
		String date = java.time.LocalDate.now().toString();
		System.out.println("Date: " + date);

		setExcelFilePath(".\\datafiles\\21-11 Demand Sheet Puyallup.xlsx");
		setExcelWriter(new ExcelWriter());
		setExcelReader(new ExcelReader());
		setGui(new GUI());
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
