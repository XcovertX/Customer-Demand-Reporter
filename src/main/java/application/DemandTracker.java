package main.java.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DemandTracker extends Application { 
	
	public static DemandTracker dt;
	private Stage window;
    private Parent root;
    private Scene scene;
    private Controller controller;
	
	public DemandTracker() {
		dt = this;
	}

	@Override
	public void start(Stage primaryStage) throws Exception  {
		
		window = primaryStage;
		
		try {
			
			FXMLLoader fxmll =  new FXMLLoader( getClass().getClassLoader().getResource("Main.fxml"));
			
			try {
				
				root = fxmll.load();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("main.css").toExternalForm());
			
			controller = new Controller();
			
			window.setTitle("Demand Tracker");
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		ExcelReader er = new ExcelReader();
		
		er.read(".\\datafiles\\21-11 Demand Sheet Puyallup.xlsx");
	}
	
}
