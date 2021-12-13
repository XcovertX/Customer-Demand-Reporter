package main.java.application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import main.java.excelOperations.DemandTracker;
import main.java.excelOperations.Modifier;

public class Controller {

	@FXML private URL location;
	@FXML private ResourceBundle resources;
	@FXML private BorderPane basePane;
	
	@FXML private ToggleButton newRentalButton;
	@FXML private ToggleButton terminateRentalButton;
	@FXML private ToggleButton transferRentalButton;
	@FXML private ToggleButton addDemandButton;
	@FXML private ToggleButton submitButton;
	@FXML private ChoiceBox<String> parkStoreDropdown;
	@FXML private ChoiceBox<String> rentalSizeDropdown;
	@FXML private ChoiceBox<String> rentalTypeDropdown;
	@FXML private ChoiceBox<String> rentalSourceDropdown;
	@FXML private TableView<Entry> demandTrackerTableView;
	@FXML private TextField nameTextField;
	@FXML private TextField phoneTextField;
	@FXML private TextField emailTextField;
	@FXML private DatePicker needByDatePicker;
	@FXML private TextArea notesTextArea;
	
	public String unusedCell = "----------";
	
	private DemandTracker demandTracker;
	
	public Controller() {}
	
	@FXML
	private void initialize() {
		
		demandTrackerTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("action"));
		demandTrackerTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("size"));
		demandTrackerTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
		demandTrackerTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactDate"));
		demandTrackerTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("needByDate"));
		demandTrackerTableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("source"));
		demandTrackerTableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("name"));
		demandTrackerTableView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("phone"));
		demandTrackerTableView.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("email"));
		demandTrackerTableView.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("notes"));
	}
	
	public void newRental(ActionEvent e) {
		
		demandTrackerTableView.getSelectionModel().clearSelection();
		terminateRentalButton.setSelected(false);
		transferRentalButton.setSelected(false);
		addDemandButton.setSelected(false);
		
		clearAllDropdowns();
		
		parkStoreDropdown.setDisable(false);
		rentalSizeDropdown.setDisable(false);
		rentalTypeDropdown.setDisable(false);
		rentalSourceDropdown.setDisable(false);
		nameTextField.setDisable(false);
		emailTextField.setDisable(false);
		phoneTextField.setDisable(false);
		needByDatePicker.setDisable(false);
		notesTextArea.setDisable(false);
		
		loadParkStoreDropdown();
	}
	
	public void terminateRental(ActionEvent e) {
		
		demandTrackerTableView.getSelectionModel().clearSelection();
		newRentalButton.setSelected(false);
		transferRentalButton.setSelected(false);
		addDemandButton.setSelected(false);
		
		disableAll();
		clearAllTextFields();
		clearAllDropdowns();
		
		parkStoreDropdown.setDisable(false);
		rentalSizeDropdown.setDisable(false);
		rentalTypeDropdown.setDisable(false);
		
		loadParkStoreDropdown();
	}
	
	public void transferRental(ActionEvent e) {

		demandTrackerTableView.getSelectionModel().clearSelection();
		newRentalButton.setSelected(false);
		terminateRentalButton.setSelected(false);
		addDemandButton.setSelected(false);
		
		disableAll();
		clearAllTextFields();
		clearAllDropdowns();
		
		parkStoreDropdown.setDisable(false);
		rentalSizeDropdown.setDisable(false);
		rentalTypeDropdown.setDisable(false);
		
		loadParkStoreDropdown();
	}
	
	public void addDemand(ActionEvent e) {

		demandTrackerTableView.getSelectionModel().clearSelection();
		newRentalButton.setSelected(false);
		terminateRentalButton.setSelected(false);
		transferRentalButton.setSelected(false);
		
		disableAll();
		clearAllTextFields();
		clearAllDropdowns();
		
		parkStoreDropdown.setDisable(false);
		rentalSizeDropdown.setDisable(false);
		rentalTypeDropdown.setDisable(false);
		rentalSourceDropdown.setDisable(false);
		nameTextField.setDisable(false);
		emailTextField.setDisable(false);
		phoneTextField.setDisable(false);
		needByDatePicker.setDisable(false);
		notesTextArea.setDisable(false);
		
		loadParkStoreDropdown();
	}
	
	public void submit() {
		
		if (newRentalButton.isSelected()) {
			
			String action = getSelectedAction();
			String catagory = parkStoreDropdown.getSelectionModel().getSelectedItem();
			String size = rentalSizeDropdown.getSelectionModel().getSelectedItem();
			String type = rentalTypeDropdown.getSelectionModel().getSelectedItem();
			String contactDate = LocalDate.now().toString();
			String needByDate = getNeedByDate();
			String source = rentalSourceDropdown.getSelectionModel().getSelectedItem();
			String name = getNameText();
			String phone = getPhoneText();
			String email = getEmailText();
			String notes = getNotesText();
			
			try {
				
				String filePath = DemandTracker.dt.getExcelFilePath();
	
				int newRentalSuccess = new Modifier().newRental(filePath, size, type, source);
				
				if (newRentalSuccess >= 0) {
					
					Entry entry = new Entry(action, catagory, size, type, contactDate, needByDate, 
							source, name, phone, email, notes);
					
					demandTrackerTableView.getItems().add(entry);
					
					clearAllDropdowns();
					clearAllTextFields();
					clearAllButtons();
					demandTrackerTableView.getSelectionModel().selectLast();
					
				} else {
	
					System.out.println("Failed to add new rental.");
				}
				
			} catch (NullPointerException e) {
				
				System.out.println("Missing required information.");
				submitButton.setSelected(false);
				return;
			}
			
		} else if (terminateRentalButton.isSelected()) {
			
			String action = getSelectedAction();
			String catagory = parkStoreDropdown.getSelectionModel().getSelectedItem();
			String size = rentalSizeDropdown.getSelectionModel().getSelectedItem();
			String type = rentalTypeDropdown.getSelectionModel().getSelectedItem();

			try {

				String filePath = DemandTracker.dt.getExcelFilePath();
				
				int terminateSuccess = new Modifier().terminateRental(filePath, size, type);
				
				if (terminateSuccess >= 0) {
					
					Entry entry = new Entry(action, catagory, size, type, unusedCell, unusedCell, 
							unusedCell, unusedCell, unusedCell, unusedCell, unusedCell);
					
					demandTrackerTableView.getItems().add(entry);
					
					clearAllDropdowns();
					clearAllTextFields();
					clearAllButtons();
					demandTrackerTableView.getSelectionModel().selectLast();
					
				} else {
					
					System.out.println("Failed to terminate rental.");
				}
				
			} catch (NullPointerException e) {
				
				System.out.println("Missing required information.");
				submitButton.setSelected(false);
				return;
			}
			
		} else if (transferRentalButton.isSelected()) {
			
			String action = getSelectedAction();
			String catagory = parkStoreDropdown.getSelectionModel().getSelectedItem();
			String size = rentalSizeDropdown.getSelectionModel().getSelectedItem();
			String type = rentalTypeDropdown.getSelectionModel().getSelectedItem();
			
			try {
				
				String filePath = DemandTracker.dt.getExcelFilePath();
				
				int transferRentalSuccess = new Modifier().transferRental(filePath, size, type, "10x10", "Upper-Temp");
				
				if (transferRentalSuccess >= 0) {
					
					Entry entry = new Entry(action, catagory, size, type, unusedCell, unusedCell, 
							unusedCell, unusedCell, unusedCell, unusedCell, unusedCell);
					
					demandTrackerTableView.getItems().add(entry);
					
					clearAllDropdowns();
					clearAllTextFields();
					clearAllButtons();
					demandTrackerTableView.getSelectionModel().selectLast();
					
				} else {
	
					System.out.println("Failed to transfer rental.");
				}
				
			} catch (NullPointerException e) {
				
				System.out.println("Missing required information.");
				submitButton.setSelected(false);
				return;
			}

		} else if (addDemandButton.isSelected()) {
			
			String action = getSelectedAction();
			String catagory = parkStoreDropdown.getSelectionModel().getSelectedItem();
			String size = rentalSizeDropdown.getSelectionModel().getSelectedItem();
			String type = rentalTypeDropdown.getSelectionModel().getSelectedItem();
			String contactDate = LocalDate.now().toString();
			String needByDate = getNeedByDate();
			String source = rentalSourceDropdown.getSelectionModel().getSelectedItem();
			String name = getNameText();
			String phone = getPhoneText();
			String email = getEmailText();
			String notes = getNotesText();
			
			try {
				
				String filePath = DemandTracker.dt.getExcelFilePath();
	
				int addDemandSuccess = new Modifier().addDemand(filePath, size, type, source);
				
				if (addDemandSuccess >= 0) {
				
					Entry entry = new Entry(action, catagory, size, type, contactDate, needByDate, 
							source, name, phone, email, notes);
					
					demandTrackerTableView.getItems().add(entry);
					
					clearAllDropdowns();
					clearAllTextFields();
					clearAllButtons();
					demandTrackerTableView.getSelectionModel().selectLast();
					
				} else {
					
					System.out.println("Failed to add demand.");
				}	
				
			} catch (NullPointerException e) {
				
				System.out.println("Missing required information.");
				submitButton.setSelected(false);
				return;
			}
		}
	}
	
	private String getNeedByDate() {
		
		String date = LocalDate.now().toString();
		
		try {
			
			date = needByDatePicker.getValue().toString();
			
		} catch (NullPointerException e) { }
		
		return date;
	}
	
	private String getNameText() {
		
		String entry = nameTextField.getText();
			
		if (entry.equals("")) {
			
			entry = "unknown";
		}
		
		return entry;
	}
	
	private String getPhoneText() {
		
		String entry = phoneTextField.getText();
		
		if (entry.equals("")) {
			
			entry = "unknown";
		}
		
		return entry;
	}
	
	private String getEmailText() {
		
		String entry = emailTextField.getText();
		
		if (entry.equals("")) {
			
			entry = "unknown";
		}
		
		return entry;
	}
	
	private String getNotesText() {
		
		String entry = notesTextArea.getText();
		
		if (entry.equals("")) {
			
			entry = "none";
		}
		
		return entry;
	}
	
	private String getSelectedAction() {
		
		if (newRentalButton.isSelected()) {
			
			return "Rental";
			
		} else if (terminateRentalButton.isSelected()) {
			
			return "Vacate";
			
		} else if (transferRentalButton.isSelected()) {
			
			return "Transfer";
			
		} else {
			
			return "Demand";
		}
	}
	
	private void loadParkStoreDropdown() {

		clearDropdown(parkStoreDropdown);
		
		parkStoreDropdown.getItems().add("Storage");
		parkStoreDropdown.getItems().add("Parking");
		
		parkStoreDropdown.setOnAction((event) -> {

		    String selectedItem = parkStoreDropdown.getSelectionModel().getSelectedItem();

		    try {
		    	
			    if (selectedItem.equals("Storage")) {
			    	
			    	loadStorageSizeDropdown();
			    
			    } else if (selectedItem.equals("Parking")) {
			    	
			    	loadParkingSizeDropdown();
			    }
			    
		    } catch (NullPointerException e) {
		    	
		    	System.out.println("catagory selection is null");
		    	
		    }
		});
	}
	
	private void loadStorageSizeDropdown() {
		
		if (parkStoreDropdown.getSelectionModel().getSelectedItem().equals("Storage")) {
			
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
			rentalSizeDropdown.getItems().add("12x43");
			
			rentalSizeDropdown.setOnAction((event) -> {

			    loadStorageTypeDropdown();

			});
		}
		
	}
	
	private void loadStorageTypeDropdown() {
		
		clearDropdown(rentalTypeDropdown);
		
		String catagorySelection = parkStoreDropdown.getSelectionModel().getSelectedItem();
		
		try {
			
			if (catagorySelection.equals("Storage")) {
				
				addStorageDropdownItems();
			}
			
		} catch (NullPointerException e) {
			
			System.out.println("type selection null");
		}

		rentalTypeDropdown.setOnAction((event) -> {

		    loadSourceDropdown();

		});
	}
	
	private void addStorageDropdownItems() {
		
		String sizeSelection = rentalSizeDropdown.getSelectionModel().getSelectedItem();
		
		try {
			
			if (sizeSelection.equals("10x30") || sizeSelection.equals("10x20")) {
	
				rentalTypeDropdown.getItems().add("Exterior");
			}
			
			if (sizeSelection.equals("12x38") || sizeSelection.equals("12x43")) {
	
				rentalTypeDropdown.getItems().add("Ext-Temp");
			}
			
			if (sizeSelection.equals("5x5")   || sizeSelection.equals("7x12")  ||
			    sizeSelection.equals("10x5")  || sizeSelection.equals("10x7")  ||
			    sizeSelection.equals("10x10") || sizeSelection.equals("10x12") ||
			    sizeSelection.equals("10x12") || sizeSelection.equals("10x15") ||
			    sizeSelection.equals("10x18") || sizeSelection.equals("10x20") ||
			    sizeSelection.equals("10x25") || sizeSelection.equals("10x30")) {
	
				rentalTypeDropdown.getItems().add("Ground-Temp");
			}
			
			if (sizeSelection.equals("5x5")   || sizeSelection.equals("10x7.5")||
				sizeSelection.equals("7x5")   || sizeSelection.equals("7x7")   ||
				sizeSelection.equals("10x5")  || sizeSelection.equals("10x7")  ||
		        sizeSelection.equals("10x10") || sizeSelection.equals("7x12")  ||
			    sizeSelection.equals("10x13") || sizeSelection.equals("10x12") || 
			    sizeSelection.equals("10x18") || sizeSelection.equals("10x20") ||
			    sizeSelection.equals("10x25") || sizeSelection.equals("10x30") ||
			    sizeSelection.equals("10x15")) {
	
				 rentalTypeDropdown.getItems().add("Upper-Temp");
			}
			
		} catch (NullPointerException e) {
			
			System.out.println("size selection is null.");
		}
	}
	
	private void addParkingDropdownItems() {
		
		String sizeSelection = rentalSizeDropdown.getSelectionModel().getSelectedItem();
		
		try {
			
			if (sizeSelection.equals("10x16") || sizeSelection.equals("10x30")) {
	
				rentalTypeDropdown.getItems().add("Non- Cov'd Pk");
			}
			
			if (sizeSelection.equals("10x16") || sizeSelection.equals("10x25") ||
			    sizeSelection.equals("10x29") || sizeSelection.equals("10x30") ||
			    sizeSelection.equals("10x32") || sizeSelection.equals("10x35") ||
			    sizeSelection.equals("10x40") || sizeSelection.equals("10x45") ||
			    sizeSelection.equals("10x50") || sizeSelection.equals("10x55") ||
			    sizeSelection.equals("11x32")) {
	
				rentalTypeDropdown.getItems().add("Cov'd Pk");
			}
			
		} catch (NullPointerException e) {
			
			System.out.println("size selection is null.");
		}
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
	
		if (parkStoreDropdown.getSelectionModel().getSelectedItem().equals("Parking")) {
			
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

		String catagorySelection = parkStoreDropdown.getSelectionModel().getSelectedItem();
		
		try {
		
			if (catagorySelection.equals("Parking")) {
				
				addParkingDropdownItems();
			}
			
		} catch (NullPointerException e) {
			
			System.out.println("type selection is null.");
		}
		
		rentalTypeDropdown.setOnAction((event) -> {

		    loadSourceDropdown();

		});
	}
	
	private void clearAllDropdowns() {

		clearDropdown(parkStoreDropdown);
		clearDropdown(rentalSizeDropdown);
		clearDropdown(rentalTypeDropdown);
		clearDropdown(rentalSourceDropdown);
	}
	
	private void clearAllTextFields() {
		
		nameTextField.clear();
		emailTextField.clear();
		phoneTextField.clear();
		notesTextArea.clear();
		needByDatePicker.setValue(null);
	}
	
	private void clearAllButtons() {
		
		newRentalButton.setSelected(false);
		terminateRentalButton.setSelected(false);
		transferRentalButton.setSelected(false);
		addDemandButton.setSelected(false);
		submitButton.setSelected(false);
	}
	
	private void disableAll() {
		
		parkStoreDropdown.setDisable(true);
		rentalSizeDropdown.setDisable(true);
		rentalTypeDropdown.setDisable(true);
		rentalSourceDropdown.setDisable(true);

		nameTextField.setDisable(true);
		emailTextField.setDisable(true);
		phoneTextField.setDisable(true);
		needByDatePicker.setDisable(true);
		notesTextArea.setDisable(true);
	}
	
	private void clearDropdown(ChoiceBox<String> dropdown) {
		
		dropdown.getSelectionModel().clearSelection();
		
		int itemListSize = dropdown.getItems().size();
		
		for (int i = 0; i < itemListSize; i++) {
			
			dropdown.getItems().remove(0);
		}
	}
}
