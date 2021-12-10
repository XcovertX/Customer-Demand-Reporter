package main.java.application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

public class Controller {

	@FXML private URL location;
	@FXML private ResourceBundle resources;
	@FXML private BorderPane basePane;
	
	@FXML private ToggleButton newRentalButton;
	@FXML private ToggleButton terminateRentalButton;
	@FXML private ToggleButton transferRentalButton;
	@FXML private ToggleButton addDemandButton;
	@FXML private ChoiceBox<String> parkStoreDropdown;
	@FXML private ChoiceBox<String> rentalSizeDropdown;
	@FXML private ChoiceBox<String> rentalTypeDropdown;
	@FXML private ChoiceBox<String> rentalSourceDropdown;
	
	public Controller() {}
	
	@FXML
	private void initialize() {
	

	}
	
	public void newRental(ActionEvent e) {
		
		terminateRentalButton.setSelected(false);
		transferRentalButton.setSelected(false);
		addDemandButton.setSelected(false);
		
		loadParkStoreDropdown();
	}
	
	public void terminateRental(ActionEvent e) {
		
		newRentalButton.setSelected(false);
		transferRentalButton.setSelected(false);
		addDemandButton.setSelected(false);
		
		loadParkStoreDropdown();
	}
	
	public void transferRental(ActionEvent e) {

		newRentalButton.setSelected(false);
		terminateRentalButton.setSelected(false);
		addDemandButton.setSelected(false);
		
		loadParkStoreDropdown();
	}
	
	public void addDemand(ActionEvent e) {

		newRentalButton.setSelected(false);
		terminateRentalButton.setSelected(false);
		transferRentalButton.setSelected(false);
		
		loadParkStoreDropdown();
	}
	
	private void loadParkStoreDropdown() {

		clearDropdown(parkStoreDropdown);
		
		parkStoreDropdown.getItems().add("Storage");
		parkStoreDropdown.getItems().add("Parking");
		
		parkStoreDropdown.setOnAction((event) -> {

		    String selectedItem = parkStoreDropdown.getSelectionModel().getSelectedItem();

		    if (selectedItem.equals("Storage")) {
		    	
		    	loadStorageSizeDropdown();
		    
		    } else if (selectedItem.equals("Parking")) {
		    	
		    	loadParkingSizeDropdown();
		    }
		});
	}
	
	private void loadStorageSizeDropdown() {
		
		if (!(rentalSizeDropdown.getItems().size() == 16)) {
			
			clearDropdown(rentalSizeDropdown);
			
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
			
			rentalSizeDropdown.setOnAction((event) -> {

			    loadStorageTypeDropdown();

			});
		}
		
	}
	
	private void loadStorageTypeDropdown() {
		
		clearDropdown(rentalTypeDropdown);
		
		rentalTypeDropdown.getItems().add("Ground-Temp");
		rentalTypeDropdown.getItems().add("Upper-Temp");
		rentalTypeDropdown.getItems().add("Exterior");
		rentalTypeDropdown.getItems().add("Ext-Temp");
		
		rentalTypeDropdown.setOnAction((event) -> {

		    loadSourceDropdown();

		});
	}
	
	private void loadSourceDropdown() {
		
		clearDropdown(rentalSourceDropdown);

		rentalSourceDropdown.getItems().add("Webform");
		rentalSourceDropdown.getItems().add("Drive-By");
		rentalSourceDropdown.getItems().add("Call");
		rentalSourceDropdown.getItems().add("Referral");
		rentalSourceDropdown.getItems().add("Loyalty");
	}
	
	private void loadParkingSizeDropdown() {
	
		if (!(rentalSizeDropdown.getItems().size() == 11)) {
			
			clearDropdown(rentalSizeDropdown);
			
			rentalSizeDropdown.getItems().add("10x16");
			rentalSizeDropdown.getItems().add("10x25");
			rentalSizeDropdown.getItems().add("10x29");
			rentalSizeDropdown.getItems().add("10x30");
			rentalSizeDropdown.getItems().add("10x32");
			rentalSizeDropdown.getItems().add("10x35");
			rentalSizeDropdown.getItems().add("10x40");
			rentalSizeDropdown.getItems().add("10x45");
			rentalSizeDropdown.getItems().add("10x50");
			rentalSizeDropdown.getItems().add("10x55");
			rentalSizeDropdown.getItems().add("11x32");	
			
			rentalSizeDropdown.setOnAction((event) -> {

			    loadParkingTypeDropdown();

			});
		}
	}
	
	private void loadParkingTypeDropdown() {
		
		clearDropdown(rentalTypeDropdown);
		
		rentalTypeDropdown.getItems().add("Cov'd Pk");
		rentalTypeDropdown.getItems().add("Non- Cov'd Pk");
		
		rentalTypeDropdown.setOnAction((event) -> {

		    loadSourceDropdown();

		});
	}
	
	private void clearDropdown(ChoiceBox<String> dropdown) {
		
		dropdown.getSelectionModel().clearSelection();
		
		int itemListSize = dropdown.getItems().size();
		
		for (int i = 0; i < itemListSize; i++) {
			
			dropdown.getItems().remove(0);
		}
	}
}
