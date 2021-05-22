package com.green.cinema.controllers;

import com.green.cinema.dbheper.Daos.MoviesDao;
import com.green.cinema.models.Movie;
import com.green.cinema.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MovieManagementController extends BaseController implements Initializable {
    public MovieManagementController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    ObservableList<Movie> listMovies;
    private int currentIndex = 0;
    MoviesDao moviesDao = new MoviesDao();

    @FXML
    private TableView<Movie> table_Movie;

    @FXML
    private TableColumn<Movie, Integer> col_ID;

    @FXML
    private TableColumn<Movie, String> col_Name;

    @FXML
    private TableColumn<Movie, String> col_Nation;

    @FXML
    private TableColumn<Movie, String> col_TheLoai;

    @FXML
    private TableColumn<Movie, String> col_Time;

    @FXML
    private TableColumn<Movie, String> col_Cast;

    @FXML
    private TableColumn<Movie, String> col_Day;

    @FXML
    private TableColumn<Movie, Integer> col_Tickes;

    @FXML
    private Button btn_Add;

    @FXML
    private Button btn_Del;

    @FXML
    void Action_Add(ActionEvent event) {
        viewFactory.showAddMovieWindow();
        Stage stage = (Stage) this.btn_Add.getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    @FXML
    void Action_Del(ActionEvent event) {
        Movie seleted = table_Movie.getSelectionModel().getSelectedItem();
        listMovies.remove(seleted);
        moviesDao.deleteMovie(viewFactory.getDbManager().getDBConnection(), seleted.getId());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listMovies = FXCollections.observableArrayList();

        printDataFromDBToTableView();
        ArrayList<Movie> listMovie = moviesDao.getAllMovies(viewFactory.getDbManager().getDBConnection());
        listMovies.setAll(listMovie);

        table_Movie.getSelectionModel().select(currentIndex);
        table_Movie.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                for (int index : change.getList()) {
                    System.out.println("change list: " + index);
                }
            }
        });
    }

    private void printDataFromDBToTableView(){
        listMovies = FXCollections.observableArrayList();

        table_Movie.setEditable(true);

        col_ID.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));

        col_Name.setCellValueFactory(new PropertyValueFactory<Movie, String>("name"));
        editNameCell();

        col_Nation.setCellValueFactory(new PropertyValueFactory<Movie, String>("quoc_gia"));
        editquocGiaCell();

        col_TheLoai.setCellValueFactory(new PropertyValueFactory<Movie, String>("the_loai"));
        editTheLoaiCell();

        col_Time.setCellValueFactory(new PropertyValueFactory<Movie, String>("thoi_luong"));
        editThoiLuongCell();

        col_Cast.setCellValueFactory(new PropertyValueFactory<Movie, String>("dien_vien"));
        editDienVienCell();

        col_Day.setCellValueFactory(new PropertyValueFactory<Movie, String>("ngay_chieu"));
        editNgayCell();

        col_Tickes.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("so_Luong_Ve"));
     //   editSoVeCell();

        table_Movie.setItems(listMovies);
    }

    private void editNameCell (){
        col_Name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Movie, String> cellEditing) {
                ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setName(cellEditing.getNewValue());

                moviesDao.updateMovie(viewFactory.getDbManager().getDBConnection(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getName(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getQuoc_gia(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThe_loai(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThoi_luong(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getDien_vien(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getNgay_chieu(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getSo_Luong_Ve()
                );
            }
        });
    }

    private void editquocGiaCell (){
        col_Nation.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Nation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Movie, String> cellEditing) {
                ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setQuoc_gia(cellEditing.getNewValue());

                moviesDao.updateMovie(viewFactory.getDbManager().getDBConnection(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getName(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getQuoc_gia(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThe_loai(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThoi_luong(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getDien_vien(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getNgay_chieu(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getSo_Luong_Ve()
                );
            }
        });
    }

    private void editThoiLuongCell (){
        col_Time.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Time.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Movie, String> cellEditing) {
                ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setThoi_luong(cellEditing.getNewValue());

                moviesDao.updateMovie(viewFactory.getDbManager().getDBConnection(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getName(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getQuoc_gia(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThe_loai(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThoi_luong(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getDien_vien(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getNgay_chieu(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getSo_Luong_Ve()
                );
            }
        });
    }

    private void editTheLoaiCell (){
        col_TheLoai.setCellFactory(TextFieldTableCell.forTableColumn());
        col_TheLoai.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Movie, String> cellEditing) {
                ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setThe_loai(cellEditing.getNewValue());

                moviesDao.updateMovie(viewFactory.getDbManager().getDBConnection(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getName(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getQuoc_gia(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThe_loai(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThoi_luong(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getDien_vien(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getNgay_chieu(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getSo_Luong_Ve()
                );
            }
        });
    }

    private void editDienVienCell (){
        col_Cast.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Cast.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Movie, String> cellEditing) {
                ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setDien_vien(cellEditing.getNewValue());

                moviesDao.updateMovie(viewFactory.getDbManager().getDBConnection(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getName(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getQuoc_gia(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThe_loai(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThoi_luong(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getDien_vien(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getNgay_chieu(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getSo_Luong_Ve()
                );
            }
        });
    }

    private void editNgayCell (){
        col_Day.setCellFactory(TextFieldTableCell.forTableColumn());
        col_Day.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Movie, String> cellEditing) {
                ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setNgay_chieu(cellEditing.getNewValue());

                moviesDao.updateMovie(viewFactory.getDbManager().getDBConnection(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getName(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getQuoc_gia(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThe_loai(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThoi_luong(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getDien_vien(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getNgay_chieu(),
                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getSo_Luong_Ve()
                );
            }
        });
    }

//    private void editSoVeCell (){
//        col_Name.setCellFactory(TextFieldTableCell.forTableColumn());
//        col_Name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<Movie, String> cellEditing) {
//                ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).setSo_Luong_Ve(Integer.parseInt(cellEditing.getNewValue()));
//
//                moviesDao.updateMovie(viewFactory.getDbManager().getDBConnection(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getId(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getName(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getQuoc_gia(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThe_loai(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getThoi_luong(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getDien_vien(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getNgay_chieu(),
//                        ((Movie) cellEditing.getTableView().getItems().get(cellEditing.getTablePosition().getRow())).getSo_Luong_Ve()
//                );
//            }
//        });
//    }

}
