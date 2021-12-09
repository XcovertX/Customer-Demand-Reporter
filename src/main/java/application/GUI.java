package main.java.application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI { 
	
	private Stage window;
    private Parent root;
    private Scene scene;
    private Controller controller;
	
	public GUI() {
		
		window = new Stage();
		
		try {
			
			FXMLLoader fxmll =  new FXMLLoader( getClass().getClassLoader().getResource("Main.fxml"));
			
			try {
				
				root = fxmll.load();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("main.css").toExternalForm());
			
			setController(new Controller());
			
			window.setTitle("Demand Tracker");
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}

	public Controller getController() {
		
		return controller;
	}

	public void setController(Controller controller) {
		
		this.controller = controller;
	}
}
