package com.example.pmsraj;

public class Prescriptions {



    private Integer id;

    private String prescription_image;

    private String description ;

    private Integer cus_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrescription_image() {
        return prescription_image;
    }

    public void setPrescription_image(String prescription_image) {
        this.prescription_image = prescription_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCus_id() {
        return cus_id;
    }

    public void setCus_id(Integer cus_id) {
        this.cus_id = cus_id;
    }
}
