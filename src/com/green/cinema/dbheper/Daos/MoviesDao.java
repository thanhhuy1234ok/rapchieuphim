package com.green.cinema.dbheper.Daos;

import com.green.cinema.models.Movie;
import com.green.cinema.models.StaffManager;

import java.sql.*;
import java.util.ArrayList;

public class MoviesDao {
    private final String QUERY_MOVIES = "SELECT * FROM PHIM";
    private static final String ADD_MOVIE = "INSERT INTO PHIM ( ID, TEN_PHIM, QUOC_GIA, THOI_LUONG, THE_LOAI, DIEN_VIEN, NGAY, SO_LUONG_VE) " +
            "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TBL_MOVIE = "UPDATE PHIM SET TEN_PHIM = ?, QUOC_GIA = ?, THE_LOAI = ?, THOI_LUONG = ?, DIEN_VIEN = ?  ,NGAY = ?,SO_LUONG_VE =?  " +
            "WHERE (ID = ?)";
    private static final String DELETE_TBL_MOVIE = "DELETE FROM PHIM WHERE (`ID` = ?);";

    public ArrayList<Movie> getAllMovies(Connection connection){
        ArrayList<Movie> listMovie = new ArrayList<>() ;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_MOVIES);

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getString("ID"));
                movie.setName(resultSet.getString("TEN_PHIM"));
                movie.setQuoc_gia(resultSet.getString("QUOC_GIA"));
                movie.setThoi_luong(resultSet.getString("THOI_LUONG"));
                movie.setThe_loai(resultSet.getString("THE_LOAI"));
                movie.setDien_vien(resultSet.getString("DIEN_VIEN"));
                movie.setNgay_chieu(resultSet.getString("NGAY"));
                movie.setSo_Luong_Ve(resultSet.getInt("SO_LUONG_VE"));
                listMovie.add(movie);
            }
            System.out.println("Table Movie created");
        } catch (SQLException exception) {
            System.out.println("Create table exception: " + exception.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return listMovie;
    }

    public int addMovie(Connection connection, Movie movie) {
        int autoIncKeyFromFunc = -1;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(ADD_MOVIE);
            preparedStatement.setString(1,movie.getId());
            preparedStatement.setString(2, movie.getName());
            preparedStatement.setString(3, movie.getQuoc_gia());
            preparedStatement.setString(4, movie.getThoi_luong());
            preparedStatement.setString(5, movie.getThe_loai());
            preparedStatement.setString(6, movie.getDien_vien());
            preparedStatement.setString(7, movie.getNgay_chieu());
            preparedStatement.setInt(8, movie.getSo_Luong_Ve());
            preparedStatement.executeUpdate();
            System.out.println("thanh cong");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                autoIncKeyFromFunc = rs.getInt(1);
            } else {
                // throw an exception from here
            }

        } catch (SQLException exception) {
            System.out.println("insert exception: " + exception.getMessage());
            System.out.println("insert Movie");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return autoIncKeyFromFunc;
    }

    public int deleteMovie(Connection connection, String movieID) {
        int result = 0;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DELETE_TBL_MOVIE);
            preparedStatement.setString(1, movieID);
            preparedStatement.executeUpdate();
            System.out.println("thanh cong");
        } catch (SQLException exception) {
            result = -1;
            System.out.println("insert exception: " + exception.getMessage());
            System.out.println("delete movie");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }

            return result;
        }
    }

    public void updateMovie(Connection connection,String id, String name, String quoc_gia, String the_loai, String thoi_luong, String dien_vien, String ngay_chieu, Integer so_luong_ve) {
        int result = 0;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_TBL_MOVIE);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, quoc_gia);
            preparedStatement.setString(3, the_loai);
            preparedStatement.setString(4, thoi_luong);
            preparedStatement.setString(5, dien_vien);
            preparedStatement.setString(6, ngay_chieu);
            preparedStatement.setInt(7, so_luong_ve);
            preparedStatement.setString(8, id);
            preparedStatement.executeUpdate();
            System.out.println("thanh cong");
        } catch (SQLException exception) {
            result = -1;
            System.out.println("update exception: " + exception.getMessage());
            System.out.println("update NhanVien");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
