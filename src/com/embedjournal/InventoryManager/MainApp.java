package com.embedjournal.InventoryManager;

import java.io.IOException;
import java.util.ArrayList;

import com.embedjournal.InventoryManager.model.Item;
import com.embedjournal.InventoryManager.view.InventoryViewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage = null;
	private BorderPane rootLayout = null;
	private ObservableList<Item> inventoryData = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Inventory Manager");
		initRootLayout();
		showInventoryView();
	}

	public MainApp() {
		Inventory inventory = new Inventory();
		ArrayList<Item> itemList = inventory.getItemListfromDB();
		inventoryData = FXCollections.observableArrayList(itemList);
	}

	public ObservableList<Item> getInventoryData() {
		return inventoryData;
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showInventoryView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/InventoryView.fxml"));
			AnchorPane inventoryView = (AnchorPane) loader.load();

			// Set inventory view into the center of root layout.
			rootLayout.setCenter(inventoryView);

			// Give the controller access to the main app.
			InventoryViewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}