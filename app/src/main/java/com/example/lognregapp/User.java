package com.example.lognregapp;

public class User {
    private int Id = 1;
    private String Login;
    private String Password;
    private String Email;

    public User(String login, String password, String email) {
        Login = login;
        Password = password;
        Email = email;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
