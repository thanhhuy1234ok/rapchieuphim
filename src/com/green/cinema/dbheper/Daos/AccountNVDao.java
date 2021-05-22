package com.green.cinema.dbheper.Daos;

import com.green.cinema.models.AccountNV;
import com.green.cinema.models.StaffManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.ArrayList;

public class AccountNVDao {
    private static final String QUERY_TK_NHANVIEN = "SELECT COUNT(*)  from tknhanvien WHERE EMAIL =? And PASS =? ";
    private static final String QUERY_TK = "SELECT * FROM tknhanvien";
    private static final String ADD_TK_NHANVIEN = "INSERT INTO tknhanvien (EMAIL, PASS, ID_NHANVIEN) " +
            "VALUES ( ?, ?, ?)";
    private static final String FIND_ACCOUNT_TYPE = "select count(*) from tknhanvien" +
            "where ID_NHANVIEN = MANAGER ";

    public AccountNVDao() {
    }

    String stError = "";

    public boolean checkAccount(Connection connection, String username, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_TK_NHANVIEN);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet queryresut = ps.executeQuery();
            while (queryresut.next()) {
                if (queryresut.getInt(1) == 1) {
                    return true;
                } else {
                    stError = "Sai tên đăng nhập hoặc mật khẩu";
                    alterError(stError);
                    return false;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            stError = "Lỗi đăng nhập";
            alterError(stError);
            return false;
        }
        return false;
    }

    public void alterError(String st) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Lỗi đăng nhập");
        alert.setContentText(st);

        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOk);
        alert.show();
    }

    public ArrayList<AccountNV> getAllAccount(Connection connection) {
        ArrayList<AccountNV> listAccount = new ArrayList<>();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_TK);

            while (resultSet.next()) {
                AccountNV account = new AccountNV();
                account.setId(resultSet.getInt("id"));
                account.setAccount(resultSet.getString("EMAIL"));
                account.setPassword(resultSet.getString("PASS"));
                account.setAccounttype(resultSet.getString("ID_NHANVIEN"));
                listAccount.add(account);
            }
            System.out.println("Table Account created");
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
        return listAccount;
    }

    public int addAccount(Connection connection, AccountNV accountNV) {
        int autoIncKeyFromFunc = -1;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(ADD_TK_NHANVIEN);
            preparedStatement.setString(1, accountNV.getAccount());
            preparedStatement.setString(2, accountNV.getPassword());
            preparedStatement.setString(3, accountNV.getAccounttype());
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
            System.out.println("insert Account");
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

    public boolean accountTypr(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ACCOUNT_TYPE);
            ResultSet queryresut = ps.executeQuery();
            while (queryresut.next()) {
                if (queryresut.getInt(1) > 1) {
                    return true;
                } else {
                    stError = "Sai tên đăng nhập hoặc mật khẩu";
                    alterError(stError);
                    return false;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            stError = "Lỗi đăng nhập";
            alterError(stError);
            return false;
        }
        return false;
    }
}
