package com.green.cinema.controllers;


import com.green.cinema.dbheper.Daos.AccountNVDao;
import com.green.cinema.dbheper.Daos.AccountNVDao;
import com.green.cinema.models.AccountNV;
import com.green.cinema.view.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginWindowController extends BaseController {
    public LoginWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }


    AccountNVDao accountNVDao = new AccountNVDao();
    String stError = "";
    AccountNV accountNV = new AccountNV() ;


    @FXML
    private Label lbcinema;

    @FXML
    private TextField tf_TaiKhoan;

    @FXML
    private PasswordField tf_Password;

    @FXML
    private Button bt_Login;

    @FXML
    void action_Loigin(ActionEvent event) throws SQLException {
        String username = tf_TaiKhoan.getText();
        String password = tf_Password.getText();

        if (checkTextFied(username, password)) {
            viewFactory.showMainAdmin();
            Stage stage = (Stage) this.bt_Login.getScene().getWindow();
            this.viewFactory.closeStage(stage);
        } else {
            alterError(stError);
        }
    }

    public boolean checkTextFied(String username, String password) {
        if (username.isEmpty()) {
            stError = "Nhập tên đăng nhập";
            return false;
        } else if (password.isEmpty()) {
            stError = "Nhập mật khẩu";
            return false;
        } else
            return true;

    }

    public void alterError(String st) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Lỗi đăng nhập");
        alert.setContentText(st);

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);
        alert.show();
    }
}
