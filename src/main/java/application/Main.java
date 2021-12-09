package main.java.application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import main.java.excelOperations.DemandTracker;

public class Main extends Application {
	
	public static void main(String[] args) {
		
		Application.launch(Main.class, args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		DemandTracker dt = new DemandTracker();
		
	}
}
