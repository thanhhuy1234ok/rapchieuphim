package com.green.cinema.view;

import com.green.cinema.controllers.*;
import com.green.cinema.dbheper.DBManager;
import com.green.cinema.emum.ColorTheme;
import com.green.cinema.emum.FontSize;
import com.green.cinema.emum.Staff;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private FontSize fontSize = FontSize.SMALL;
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private Staff staff = Staff.POSSITION;

    private DBManager dbManager;

    public DBManager getDbManager() {
        return dbManager;
    }

    public ViewFactory(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void showLoginWindow() {
        System.out.println("Show Login Window");
        BaseController controller = new LoginWindowController(this, "LoginWindow.fxml");
        initializeStage(controller);
    }

    public void showMainAdmin() {
        System.out.println("Show Main Window");
        BaseController controller = new MainAdmin(this, "MainWindowAdmin.fxml");
        initializeStage(controller);
    }

    public void showOptionWidow() {
        System.out.println("Show Main Window");
        BaseController controller = new OptionWindowContrller(this, "OptionWindow.fxml");
        initializeStage(controller, true);
    }

    public void showStaffWindow() {
        System.out.println("Show Staff Window");
        BaseController controller = new StaffManagerController(this, "StaffManager.fxml");
        initializeStage(controller);
    }

    public void showRegistrationWindow() {
        System.out.println("Show Regítration Window");
        BaseController controller = new RegistrationManagementCotronller(this, "RegistrationManagement.fxml");
        initializeStage(controller);
    }

    public void showAddManagerWindow() {
        System.out.println("Show Add Window");
        BaseController controller = new AddManagerController(this, "AddManager.fxml");
        initializeStage(controller, true);
    }

    public void showMovieWindow() {
        System.out.println("Show Movie Window");
        BaseController controller = new MovieManagementController(this, "MovieManagement.fxml");
        initializeStage(controller);
    }

    public void showAddMovieWindow() {
        System.out.println("Show add Movie Window");
        BaseController controller = new AddMovieController(this, "AddMovie.fxml");
        initializeStage(controller);
    }


    public void closeStage(Stage stage) {
        stage.close();
    }

    private void initializeStage(BaseController controller) {
        initializeStage(controller, false);
    }

    private void initializeStage(BaseController controller, boolean isModal) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("initializeStage: failed to load fxml");
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        if (isModal == true) {
            stage.initModality(Modality.APPLICATION_MODAL);
        }
        stage.setScene(scene);
        stage.show();
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
