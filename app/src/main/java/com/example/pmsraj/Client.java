package com.example.pmsraj;

public class Client {

    private Integer customer_id;


    private String name;


    private String phone;

    private String mail;

    private CustomerLogin customerLogin;

    public Client(String name, String phone, String mail, CustomerLogin customerLogin) {

        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.customerLogin = customerLogin;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public CustomerLogin getCustomerLogin() {
        return customerLogin;
    }


    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCustomerLogin(CustomerLogin customerLogin) {
        this.customerLogin = customerLogin;
    }

    public Client() {
    }
}
