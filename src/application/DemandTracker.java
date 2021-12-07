package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DemandTracker extends Application { 
	
	public static DemandTracker dt;
	private Stage window;
	
	public DemandTracker() {
		dt = this;
	}

	@Override
	public void start(Stage primaryStage) throws Exception  {
		
		try {
			
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}

	public Stage getWindow() {
		
		return window;
	}

	public void setWindow(Stage window) {
		
		this.window = window;
	}
	
}
