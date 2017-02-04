package com.embedjournal.InventoryManager.view;

import com.embedjournal.InventoryManager.model.Item;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ItemEditController {

	private boolean okClicked = false;
	private Stage editState = null;
	private Item item = null;
	@FXML
	private TextField manufPno;
	@FXML
	private TextField manuf;
	@FXML
	private TextField footprint;
	@FXML
	private TextField desc;
	@FXML
	private TextField stock;

	@FXML
	private void initialize() {
	}

	public void setItemEditStage(Stage editState) {
		this.editState = editState;
	}

	public void setItem(Item item) {
		this.item = item;

		manufPno.setText(item.getManufPno());
		manuf.setText(item.getManufacturer());
		footprint.setText(item.getFootPrint());
		desc.setText(item.getDescription());
		stock.setText(Integer.toString(item.getStock()));
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			if(hasChanged()) {
				item.setManufacturer(manuf.getText());
				item.setFootPrint(footprint.getText());
				item.setDescription(desc.getText());
				item.setStock(Integer.parseInt(stock.getText()));
				okClicked = true;
			}
			this.editState.close();
		}
	}
	
	private boolean hasChanged() {
		boolean noChange = false;
		do {
			if (manuf.getText().equalsIgnoreCase(item.getManufacturer()) == false)
				break;
			if (footprint.getText().equalsIgnoreCase(item.getFootPrint()) == false)
				break;
			if (desc.getText().equalsIgnoreCase(item.getDescription()) == false)
				break;
			if (Integer.parseInt(stock.getText()) != item.getStock() == false)
				break;
			noChange = true;
		} while(false);
		return noChange;
	}

	private boolean isInputValid() {
		if (manuf.getText() == null || footprint.getText() == null || desc.getText() == null
				|| stock.getText() == null) {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.editState);
			alert.setTitle("Edit error");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("All fileds mandatory");
			alert.showAndWait();
			return false;
		}
		
		
		return true;
	}

	@FXML
	private void handleCancel() {
		editState.close();
	}

}
