package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import excelOperations.DemandTracker;

public class GUI { 
	
	private DemandTracker dt;
	private Stage window;
    private Parent root;
    private Scene scene;
    private Controller controller;
	
	public GUI() {
		
//		this.setDt(new DemandTracker());
		this.window = new Stage();
		
		try {
			
			FXMLLoader fxmll =  new FXMLLoader(getClass().getClassLoader().getResource("Main.fxml"));
			
			try {
				
				root = fxmll.load();
				
			} catch (IOException e) {

				System.out.println( "Could not load Main.fxml");
				e.printStackTrace();
				System.exit( 0 );
			}
			
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("main.css").toExternalForm());
			
			setController(new Controller());
			
			window.setTitle("Demand Tracker");
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {

			System.out.println( "Could not find Main.fxml");
			e.printStackTrace();
			System.exit( 0 );
		}
	}

	public Controller getController() {
		
		return controller;
	}

	public void setController(Controller controller) {
		
		this.controller = controller;
	}

	public DemandTracker getDt() {
		
		return dt;
	}

	public void setDt(DemandTracker dt) {
		
		this.dt = dt;
	}
}
