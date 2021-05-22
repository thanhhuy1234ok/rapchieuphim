package com.green.cinema.controllers;

import com.green.cinema.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class MainAdmin extends BaseController {

    public MainAdmin(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    private String TAG = "MainWindowController";

    @FXML
    void menuOptionAction(ActionEvent event) {
        System.out.println(TAG + "::menuOptionAction()");
        viewFactory.showOptionWidow();
    }

    @FXML
    void menuManagementAction(ActionEvent event) {
        System.out.println(TAG + "::menuManagerAction()");
        viewFactory.showStaffWindow();
    }

    @FXML
    void menuRegistrationManagementAction(ActionEvent event) {
        System.out.println(TAG + "::menuAccountrAction()");
        viewFactory.showRegistrationWindow();
    }

    @FXML
    void menuActionMovie(ActionEvent event) {
        System.out.println(TAG + "::menuMovieAction()");
        viewFactory.showMovieWindow();
    }

}
