package com.embedjournal.InventoryManager.view;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.embedjournal.InventoryManager.Inventory;
import com.embedjournal.InventoryManager.MainApp;
import com.embedjournal.InventoryManager.model.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

public class InventoryViewController {
	private ObservableList<Item> inventoryData = FXCollections.observableArrayList();
	private Inventory inventory = null;
	private MainApp mainApp = null;
	@FXML
	private TableView<Item> inventroyTable;
	@FXML
	private TableColumn<Item, String> manufPnoColoumn;
	@FXML
	private TableColumn<Item, String> manufColoumn;
	@FXML
	private TableColumn<Item, String> footPrintColoumn;
	@FXML
	private TableColumn<Item, String> descriptionColoumn;
	@FXML
	private TableColumn<Item, Integer> stockColoumn;

	public InventoryViewController() {
		// if a DB file already existed, load it.
		File dbFile = new File("res/Inventory.DB");
		if (dbFile.exists()) {
			openDatabase(dbFile.getPath());
		}
	}

	private void openDatabase(String dbFile) {
		this.inventory = new Inventory(dbFile);
		List<Item> itemList = this.inventory.getItemListfromDB();
		inventoryData.removeAll(inventoryData); // remove all.
		for(Item i: itemList) {
			inventoryData.add(i);
		}
	}

	@FXML
	private void initialize() {
		manufPnoColoumn.setCellValueFactory(cellData -> cellData.getValue().getPropertyManufPno());
		manufColoumn.setCellValueFactory(cellData -> cellData.getValue().getPropertyManufPno());
		footPrintColoumn.setCellValueFactory(cellData -> cellData.getValue().getPropertyManufPno());
		descriptionColoumn.setCellValueFactory(cellData -> cellData.getValue().getPropertyManufPno());
		stockColoumn.setCellValueFactory(cellData -> cellData.getValue().getPropertyStock().asObject());
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the table
		this.inventroyTable.setItems(this.inventoryData);
		this.inventroyTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public TableView<Item> getInventroyTable() {
		return inventroyTable;
	}

	public void setInventroyTable(TableView<Item> inventroyTable) {
		this.inventroyTable = inventroyTable;
	}

	@FXML
	private void toolActionOpen() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(this.mainApp.getPrimaryStage());
		if (file != null) {
			this.openDatabase(file.getPath());
		}
	}

	@FXML
	private void toolActionNew() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Create new database");
		File file = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());
		if (file != null) {
			this.openDatabase(file.getPath());
		}
	}

	@FXML
	private void toolActionDeleteSelection() {
		ObservableList<Item> selection = this.inventroyTable.getSelectionModel().getSelectedItems();
		if (selection.size() == 0)
			return;
		List<Item> list = selection.stream().collect(Collectors.toList());
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Deleting " + list.size() + " items. Are you sure?");
		alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			this.inventory.deleteItemListFromDB(list);
			inventoryData.removeAll(selection);
		}
	}

	@FXML
	private void toolActionExport() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Export database");
		File file = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());
		if (file != null && this.inventory != null) {
			this.inventory.exportInventoryToCSV(file.getPath());
		}
	}

	@FXML
	private void toolActionImport() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(this.mainApp.getPrimaryStage());
		if (file != null && this.inventory != null) {
			this.inventory.importInventoryFromCSV(file.getPath());
		}
	}

	@FXML
	private void toolActionExportPurchase() {
		ObservableList<Item> selection = this.inventroyTable.getSelectionModel().getSelectedItems();
		if (selection.size() != 0) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Export Range");
			File file = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());
			if (file != null && this.inventory != null) {
				List<Item> list = selection.stream().collect(Collectors.toList());
				try {
					this.inventory.exportToCSV(file.getPath(), list);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Item Selected");
	        alert.setContentText("Please select a row in the table.");
	        alert.showAndWait();
		}
	}
		
	@FXML
	private void toolActionNewItem() {
	    Item item = new Item();
	    if (mainApp.showItemEditor(item)) {
	        inventoryData.add(item);
	    }
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void toolActionEdit() {
	    Item selectedItem = inventroyTable.getSelectionModel().getSelectedItem();
	    if (selectedItem != null) {
	        if (mainApp.showItemEditor(selectedItem))
	            inventory.updateItem(selectedItem);
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Item Selected");
	        alert.setContentText("Please select a row in the table.");
	        alert.showAndWait();
	    }
	}
}
