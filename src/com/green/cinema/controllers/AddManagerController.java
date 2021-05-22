package com.green.cinema.controllers;

import com.green.cinema.dbheper.Daos.ManagerDao;
import com.green.cinema.emum.ColorTheme;
import com.green.cinema.emum.Staff;
import com.green.cinema.models.StaffManager;
import com.green.cinema.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddManagerController extends BaseController implements Initializable {
    public AddManagerController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    ManagerDao managerDao = new ManagerDao();
    ObservableList<StaffManager> listmanagers;
    StaffManager manager;
    private int currentIndex = 0;
    private Staff staff = Staff.POSSITION;
    StaffManagerController controller ;

    @FXML
    private TextField tf_Name;

    @FXML
    private TextField tf_Email;

    @FXML
    private TextField tf_Addres;

    @FXML
    private TextField tf_Phone;

    @FXML
    private DatePicker datePicker_Birth;

    @FXML
    private ComboBox<Staff> BoxStaff;

    @FXML
    private Button bt_Ok;

    @FXML
    void buttonOkAction(ActionEvent event) {

        String time = datePicker_Birth.getValue().toString();
        System.out.println("day time : " + time);

        listmanagers = FXCollections.observableArrayList();
        manager = new StaffManager();
        manager.setHoTen(tf_Name.getText());
        manager.setEmail(tf_Email.getText());
        manager.setAddress(tf_Addres.getText());
        manager.setPhone(Integer.parseInt(tf_Phone.getText()));
        manager.setBirth(datePicker_Birth.getValue().toString());
        manager.setPosition(BoxStaff.getValue().toString());

        int last_id = managerDao.addNhanVien(viewFactory.getDbManager().getDBConnection(), manager);

        manager.setId(last_id);
        listmanagers.add(manager);
        System.out.println("add true");

        Stage stage = (Stage) this.bt_Ok.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChoiceBoxTheme();

    }

    private void initChoiceBoxTheme() {
        BoxStaff.setItems(FXCollections.observableArrayList(Staff.values()));
        BoxStaff.setValue(viewFactory.getStaff());

    }
}
