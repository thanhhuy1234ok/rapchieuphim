module Cinema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swt;
    requires javafx.swing;

    opens com.green.cinema;
    opens com.green.cinema.controllers;
    opens com.green.cinema.view;
    opens com.green.cinema.dbheper;
    opens com.green.cinema.models;
    opens com.green.cinema.dbheper.Daos;
}