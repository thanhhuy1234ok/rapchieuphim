package com.green.cinema;

import com.green.cinema.dbheper.DBManager;
import com.green.cinema.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(new DBManager());

        viewFactory.showLoginWindow();
    }
}
