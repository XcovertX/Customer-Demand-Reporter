package application;

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
		
		try {
			
			FXMLLoader fxmll =  new FXMLLoader( getClass().getClassLoader().getResource("Main.fxml"));
			
			try {
				
				root = fxmll.load();
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("main.css").toExternalForm());
			
			controller = new Controller();
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
