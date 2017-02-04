package com.embedjournal.InventoryManager.view;

import com.embedjournal.InventoryManager.MainApp;

import javafx.fxml.FXML;

public class RootLayoutController {
	@SuppressWarnings("unused")
	private MainApp mainApp;
	@FXML
	private void initialize() {

	}

	public RootLayoutController() {
		
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /* MenuBar Actions */
    @FXML
    private void menuActionExportSelection() {
    	return;
    }
	@FXML
    private void menuActionExitApplication() {
    	System.exit(0);
    }	
}
