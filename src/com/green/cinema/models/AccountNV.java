package com.green.cinema.models;

public class AccountNV {
    private int id;
    private String account;
    private String password;
    private String accounttype;

    public AccountNV() {
    }

    public AccountNV(String account, String password, String accounttype, int id) {
        this.account = account;
        this.password = password;
        this.accounttype = accounttype;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
