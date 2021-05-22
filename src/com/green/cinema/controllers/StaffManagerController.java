package com.green.cinema.controllers;

import com.green.cinema.dbheper.Daos.ManagerDao;
import com.green.cinema.models.Movie;
import com.green.cinema.models.StaffManager;
import com.green.cinema.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class StaffManagerController extends BaseController implements Initializable {
    public StaffManagerController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    ObservableList<StaffManager> listManager;
    ManagerDao managerDao = new ManagerDao();
    StaffManager staffManager = new StaffManager();

    private int currentIndex = 0;

    @FXML
    private TableView<StaffManager> tableManager;

    @FXML
    private TableColumn<StaffManager, Integer> tb_ID;

    @FXML
    private TableColumn<StaffManager, String> tb_HoTen;

    @FXML
    private TableColumn<StaffManager, String> tb_Email;

    @FXML
    private TableColumn<StaffManager, String> tb_Address;

    @FXML
    private TableColumn<StaffManager, String> tb_Phone;

    @FXML
    private TableColumn<StaffManager, String> tb_Birth;

    @FXML
    private TableColumn<StaffManager, String> tb_Position;

    @FXML
    private Button bt_Add;

    @FXML
    private Button bt_Del;

    @FXML
    void buttonAddAction(ActionEvent event) {
        viewFactory.showAddManagerWindow();
        Stage stage = (Stage) this.bt_Add.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void buttonDelAction(ActionEvent event) {
        StaffManager seleted = tableManager.getSelectionModel().getSelectedItem();
        listManager.remove(seleted);
        tableManager.refresh();
        managerDao.deletenhanvien(viewFactory.getDbManager().getDBConnection(), seleted.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prinftableManager();

        ArrayList<StaffManager> listNV = managerDao.getAllManager(viewFactory.getDbManager().getDBConnection());
        listManager.setAll(listNV);

        tableManager.getSelectionModel().select(currentIndex);
        tableManager.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                for (int index : change.getList()) {
                    System.out.println("change list: " + index);
                }
            }
        });
    }

    public void prinftableManager() {
        listManager = FXCollections.observableArrayList();

        tableManager.setEditable(true);

        tb_ID.setCellValueFactory(new PropertyValueFactory<StaffManager, Integer>("id"));
        tb_HoTen.setCellValueFactory(new PropertyValueFactory<StaffManager, String>("hoTen"));
        editNameCell();
        tb_Email.setCellValueFactory(new PropertyValueFactory<StaffManager, String>("email"));
        tb_Address.setCellValueFactory(new PropertyValueFactory<StaffManager, String>("address"));
        editAddressCell();
        tb_Phone.setCellValueFactory(new PropertyValueFactory<StaffManager, String>("phone"));
        tb_Birth.setCellValueFactory(new PropertyValueFactory<StaffManager, String>("birth"));
        editbirthCell();
        tb_Position.setCellValueFactory(new PropertyValueFactory<StaffManager, String>("position"));
        editChucVuCell();

        tableManager.setItems(listManager);
    }


    private void editNameCell (){
        tb_HoTen.setCellFactory(TextFieldTableCell.forTableColumn());
        tb_HoTen.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffManager, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StaffManager, String> cellEditing) {
                ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setHoTen(cellEditing.getNewValue());

                managerDao.updateNhanVien(viewFactory.getDbManager().getDBConnection(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getHoTen(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getAddress(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPhone(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getBirth(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPosition()
                );
            }
        });
    }

    private void editAddressCell (){
        tb_Address.setCellFactory(TextFieldTableCell.forTableColumn());
        tb_Address.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffManager, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StaffManager, String> cellEditing) {
                ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setAddress(cellEditing.getNewValue());

                managerDao.updateNhanVien(viewFactory.getDbManager().getDBConnection(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getHoTen(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getAddress(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPhone(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getBirth(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPosition()
                );
            }
        });
    }

//    private void editPhoneCell (){
//        tb_Phone.setCellFactory(TextFieldTableCell.forTableColumn());
//        tb_Phone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffManager, String>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<StaffManager, String> cellEditing) {
//               ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setPhone(cellEditing.getNewValue().toString());
//
//                managerDao.updateNhanVien(viewFactory.getDbManager().getDBConnection(),
//                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
//                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getHoTen(),
//                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getAddress(),
//                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPhone(),
//                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getBirth(),
//                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPosition()
//                );
//            }
//        });
//    }

    private void editbirthCell (){
        tb_Birth.setCellFactory(TextFieldTableCell.forTableColumn());
        tb_Birth.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffManager, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StaffManager, String> cellEditing) {
                ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setBirth(cellEditing.getNewValue());

                managerDao.updateNhanVien(viewFactory.getDbManager().getDBConnection(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getHoTen(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getAddress(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPhone(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getBirth(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPosition()
                );
            }
        });
    }

    private void editChucVuCell (){
        tb_Position.setCellFactory(TextFieldTableCell.forTableColumn());
        tb_Position.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StaffManager, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StaffManager, String> cellEditing) {
                ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setPosition(cellEditing.getNewValue());

                managerDao.updateNhanVien(viewFactory.getDbManager().getDBConnection(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getHoTen(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getAddress(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPhone(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getBirth(),
                        ((StaffManager) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getPosition()
                );
            }
        });
    }

}
