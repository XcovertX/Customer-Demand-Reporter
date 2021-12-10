package main.java.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;

public class Controller {

	@FXML private URL location;
	@FXML private ResourceBundle resources;
	@FXML private BorderPane basePane;
	
	@FXML private Button newRentalButton;
	@FXML private Button terminateRentalButton;
	@FXML private ChoiceBox<String> rentalSizeDropdown;
	@FXML private ChoiceBox<String> rentalTypeDropdown;
	@FXML private ChoiceBox<String> rentalSourceDropdown;
	
	public Controller() {}
	
	@FXML
	private void initialize() {
	

	}
	
	public void loadNewStorageRentalDropdowns(ActionEvent e) {
		
		if (!(rentalSizeDropdown.getItems().size() == 16)) {
			
			rentalSizeDropdown.getSelectionModel().clearSelection();
			
			int itemListSize = rentalSizeDropdown.getItems().size();
			
			for (int i = 0; i < itemListSize; i++) {
				rentalSizeDropdown.getItems().remove(0);
			}
			
			rentalSizeDropdown.getItems().add("5x5");
			rentalSizeDropdown.getItems().add("7x5");
			rentalSizeDropdown.getItems().add("7x7");
			rentalSizeDropdown.getItems().add("10x5");
			rentalSizeDropdown.getItems().add("7x12");
			rentalSizeDropdown.getItems().add("10x7");
			rentalSizeDropdown.getItems().add("10x7.5");
			rentalSizeDropdown.getItems().add("10x10");
			rentalSizeDropdown.getItems().add("10x12");
			rentalSizeDropdown.getItems().add("10x13");
			rentalSizeDropdown.getItems().add("10x15");
			rentalSizeDropdown.getItems().add("10x18");
			rentalSizeDropdown.getItems().add("10x20");
			rentalSizeDropdown.getItems().add("10x25");
			rentalSizeDropdown.getItems().add("10x30");
			rentalSizeDropdown.getItems().add("12x38");
		}
		
	}
	
	public void loadNewParkingRentalDropdowns(ActionEvent e) {
	
		if (!(rentalSizeDropdown.getItems().size() == 9)) {
		
			rentalSizeDropdown.getSelectionModel().clearSelection();
			
			int itemListSize = rentalSizeDropdown.getItems().size();
			
			for (int i = 0; i < itemListSize; i++) {
				rentalSizeDropdown.getItems().remove(0);
			}
			
			rentalSizeDropdown.getItems().add("10x16");
			rentalSizeDropdown.getItems().add("10x29");
			rentalSizeDropdown.getItems().add("10x32");
			rentalSizeDropdown.getItems().add("10x35");
			rentalSizeDropdown.getItems().add("10x40");
			rentalSizeDropdown.getItems().add("10x45");
			rentalSizeDropdown.getItems().add("10x50");
			rentalSizeDropdown.getItems().add("10x55");
			rentalSizeDropdown.getItems().add("11x32");	
		}
		
	}
}
