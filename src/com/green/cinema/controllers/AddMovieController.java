package com.green.cinema.controllers;

import com.green.cinema.dbheper.Daos.MoviesDao;
import com.green.cinema.models.Movie;
import com.green.cinema.models.StaffManager;
import com.green.cinema.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMovieController extends BaseController{
    public AddMovieController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
    ObservableList<Movie> listMovie;
    Movie movie;
    MoviesDao moviesDao = new MoviesDao();

    @FXML
    private TextField tf_IDPhim;

    @FXML
    private TextField tf_Phim;

    @FXML
    private TextField tf_QuocGia;

    @FXML
    private TextField tf_ThoiLuong;

    @FXML
    private TextField tf_TheLoai;

    @FXML
    private TextField tf_DienVIen;

    @FXML
    private DatePicker datePiker;

    @FXML
    private TextField tf_SoLuongVe;

    @FXML
    private Button btn_Add;

    @FXML
    void ActionAdd(ActionEvent event) {
        String time = datePiker.getValue().toString();
        System.out.println("day time : " + time);

        listMovie = FXCollections.observableArrayList();
        movie = new Movie();
        movie.setId(tf_IDPhim.getText());
        movie.setName(tf_Phim.getText());
        movie.setQuoc_gia(tf_QuocGia.getText());
        movie.setThoi_luong(tf_ThoiLuong.getText());
        movie.setThe_loai(tf_TheLoai.getText());
        movie.setDien_vien(tf_DienVIen.getText());
        movie.setNgay_chieu(datePiker.getValue().toString());
        movie.setSo_Luong_Ve(Integer.parseInt(tf_SoLuongVe.getText()));

        moviesDao.addMovie(viewFactory.getDbManager().getDBConnection(), movie);

        listMovie.add(movie);
        System.out.println("add true");

        Stage stage = (Stage) this.btn_Add.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
