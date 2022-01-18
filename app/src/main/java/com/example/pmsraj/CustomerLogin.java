package com.example.pmsraj;

public class CustomerLogin {


    private Integer id;



    private String username;



    private String password;


    public CustomerLogin(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public CustomerLogin() {

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
