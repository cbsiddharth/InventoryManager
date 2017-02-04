package com.embedjournal.InventoryManager;

import java.io.IOException;

import com.embedjournal.InventoryManager.model.Item;
import com.embedjournal.InventoryManager.view.InventoryViewController;
import com.embedjournal.InventoryManager.view.ItemEditController;
import com.embedjournal.InventoryManager.view.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Stage primaryStage = null;
	private BorderPane rootLayout = null;
	private InventoryViewController ivController = null;

	@Override
	public void start(Stage primaryStage) {
		this.setPrimaryStage(primaryStage);
		this.primaryStage.getIcons().add(new Image("file:res/img/icon.png"));
		this.getPrimaryStage().setTitle("Inventory Manager");
		initRootLayout();
		showInventoryView();
	}

	public MainApp() {

	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			this.getPrimaryStage().setScene(scene);
			this.getPrimaryStage().show();
			
			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
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
			setIvController(loader.getController());
			getIvController().setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showItemEditor(Item item) {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ItemEditView.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Stage editStage = new Stage();
	        editStage.setTitle("Edit Item");
	        editStage.initModality(Modality.WINDOW_MODAL);
	        editStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        editStage.setScene(scene);

	        ItemEditController controller = loader.getController();
	        controller.setItemEditStage(editStage);
	        controller.setItem(item);

	        // Show the dialog and wait until the user closes it
	        editStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public InventoryViewController getIvController() {
		return ivController;
	}

	public void setIvController(InventoryViewController ivController) {
		this.ivController = ivController;
	}
}