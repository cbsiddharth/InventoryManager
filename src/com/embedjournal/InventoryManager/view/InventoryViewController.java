package com.embedjournal.InventoryManager.view;

import com.embedjournal.InventoryManager.MainApp;
import com.embedjournal.InventoryManager.model.Item;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InventoryViewController {
	
	@SuppressWarnings("unused")
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
        inventroyTable.setItems(mainApp.getInventoryData());
    }
}
