package com.green.cinema.controllers;

import com.green.cinema.dbheper.Daos.AccountNVDao;
import com.green.cinema.emum.Staff;
import com.green.cinema.models.AccountNV;
import com.green.cinema.models.StaffManager;
import com.green.cinema.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrationManagementCotronller extends BaseController implements Initializable {
    public RegistrationManagementCotronller(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    AccountNV accountNV;
    AccountNVDao accountNVDao = new AccountNVDao();
    ObservableList<AccountNV> listAccounts;
    private int currentIndex = 0;

    @FXML
    private TableView<AccountNV> tableLogin;

    @FXML
    private TableColumn<Integer, AccountNV> tb_ID;

    @FXML
    private TableColumn<String, AccountNV> tb_Use;

    @FXML
    private TableColumn<String, AccountNV> tb_Pass;

    @FXML
    private TableColumn<ComboBox, AccountNV> tb_AccountType;

    @FXML
    private TextField tf_User;

    @FXML
    private TextField tf_Password;

    @FXML
    private ComboBox<Staff> cb_AccountType;

    @FXML
    private Button btn_Regíter;

    @FXML
    void ActionRegister(ActionEvent event) {
        listAccounts = FXCollections.observableArrayList();
        accountNV = new AccountNV();

        accountNV.setAccount(tf_User.getText());
        accountNV.setPassword(tf_Password.getText());
        accountNV.setAccounttype(cb_AccountType.getValue().toString());

        int last_id = accountNVDao.addAccount(viewFactory.getDbManager().getDBConnection(), accountNV);
        accountNV.setId(last_id);
        listAccounts.add(accountNV);
        System.out.println("add true");
        Stage stage = (Stage) this.btn_Regíter.getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listAccounts = FXCollections.observableArrayList();

        tb_ID.setCellValueFactory(new PropertyValueFactory<Integer, AccountNV>("id"));
        tb_Use.setCellValueFactory(new PropertyValueFactory<String, AccountNV>("account"));
        tb_Pass.setCellValueFactory(new PropertyValueFactory<String, AccountNV>("password"));
        tb_AccountType.setCellValueFactory(new PropertyValueFactory<ComboBox, AccountNV>("accounttype"));

        ArrayList<AccountNV> listAccount = accountNVDao.getAllAccount(viewFactory.getDbManager().getDBConnection());
        initChoiceBoxTheme();
        listAccounts.setAll(listAccount);
        tableLogin.setItems(listAccounts);
        tableLogin.getSelectionModel().select(currentIndex);


        tableLogin.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                for (int index : change.getList()) {
                    System.out.println("change list: " + index);
                }
            }
        });
    }

    private void initChoiceBoxTheme() {
        cb_AccountType.setItems(FXCollections.observableArrayList(Staff.values()));
        cb_AccountType.setValue(viewFactory.getStaff());

    }
}
