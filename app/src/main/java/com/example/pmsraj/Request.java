package com.example.pmsraj;

public class Request {

    private Integer id;

    private String cus_email;

    private Integer cus_contact ;

    private String request;

    private Integer req_status;


    private String phatmacist_id;

    private String request_date;

    private String response_date;

    private String response;


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public Integer getCus_contact() {
        return cus_contact;
    }

    public void setCus_contact(Integer cus_contact) {
        this.cus_contact = cus_contact;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getReq_status() {
        return req_status;
    }

    public void setReq_status(Integer req_status) {
        this.req_status = req_status;
    }



    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getResponse_date() {
        return response_date;
    }

    public void setResponse_date(String response_date) {
        this.response_date = response_date;
    }

    public String getPhatmacist_id() {
        return phatmacist_id;
    }

    public void setPhatmacist_id(String phatmacist_id) {
        this.phatmacist_id = phatmacist_id;
    }

    public Request(String cus_email, Integer cus_contact, String request, Integer req_status, String phatmacist_id, String request_date, String response_date, String response) {
        this.cus_email = cus_email;
        this.cus_contact = cus_contact;
        this.request = request;
        this.req_status = req_status;
        this.phatmacist_id = phatmacist_id;
        this.request_date = request_date;
        this.response_date = response_date;
        this.response = response;
    }
}
