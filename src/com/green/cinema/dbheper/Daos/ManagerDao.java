package com.green.cinema.dbheper.Daos;

import com.green.cinema.emum.Staff;
import com.green.cinema.models.StaffManager;
import javafx.scene.control.DatePicker;

import java.sql.*;
import java.util.ArrayList;

public class ManagerDao {

    private static final String QUERY_NHANVIEN = "SELECT * FROM NHAN_VIEN ";
    private static final String ADD_NHANVIEN = "INSERT INTO NHAN_VIEN (HOTEN, EMAIL, ADDRESS, Phone, DateofBirth, CHUC_VU) " +
            "VALUES ( ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TBL_VALUE = "UPDATE NHAN_VIEN SET HOTEN = ?, ADDRESS = ?, Phone = ?, DateofBirth = ?, CHUC_VU =?" +
            "WHERE (ID = ?)";
    private static final String DELETE_TBL_NHANVIEN = "DELETE FROM NHAN_VIEN WHERE (`ID` = ?);";


    public ArrayList<StaffManager> getAllManager(Connection connection) {
        ArrayList<StaffManager> listManager = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_NHANVIEN);

            while (resultSet.next()) {
                StaffManager manager = new StaffManager();
                manager.setId(resultSet.getInt("ID"));
                manager.setHoTen(resultSet.getString("HOTEN"));
                manager.setEmail(resultSet.getString("EMAIL"));
                manager.setAddress(resultSet.getString("ADDRESS"));
                manager.setPhone(resultSet.getInt("Phone"));
                manager.setBirth(resultSet.getString("DateofBirth"));
                manager.setPosition(resultSet.getString("CHUC_VU"));
                listManager.add(manager);
            }
            System.out.println("Table NhanVien created");
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
        return listManager;
    }

    public int addNhanVien(Connection connection, StaffManager manager) {
        int autoIncKeyFromFunc = -1;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(ADD_NHANVIEN);
            preparedStatement.setString(1, manager.getHoTen());
            preparedStatement.setString(2, manager.getEmail());
            preparedStatement.setString(3, manager.getAddress());
            preparedStatement.setInt(4, manager.getPhone());
            preparedStatement.setString(5, manager.getBirth());
            preparedStatement.setString(6, manager.getPosition());
            preparedStatement.executeUpdate();


            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                autoIncKeyFromFunc = rs.getInt(1);
            } else {
                // throw an exception from here
            }

        } catch (SQLException exception) {
            System.out.println("insert exception: " + exception.getMessage());
            System.out.println("insert Nhan vien");
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

    public int deletenhanvien(Connection connection, int nhanvienID) {
        int result = 0;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(DELETE_TBL_NHANVIEN);
            preparedStatement.setInt(1, nhanvienID);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            result = -1;
            System.out.println("insert exception: " + exception.getMessage());
            System.out.println("delete nhan vien");
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

    public void updateNhanVien(Connection connection, int id, String hoTen, String address, int phone, String birth, String position) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(UPDATE_TBL_VALUE);
            preparedStatement.setString(1, hoTen);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, phone);
            preparedStatement.setString(4, birth);
            preparedStatement.setString(5, position);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
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
