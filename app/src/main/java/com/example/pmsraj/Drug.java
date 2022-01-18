package com.example.pmsraj;

public class Drug {

    private Integer drug_id;
    private String  drug_name;
    private Float drug_price;
    private String	rack_name;
    private String	generic_name;
    private String	brand_name;
    private Integer stock_quantity;

    public Integer getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(Integer drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public Float getDrug_price() {
        return drug_price;
    }

    public void setDrug_price(Float drug_price) {
        this.drug_price = drug_price;
    }

    public String getRack_name() {
        return rack_name;
    }

    public void setRack_name(String rack_name) {
        this.rack_name = rack_name;
    }

    public String getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(String generic_name) {
        this.generic_name = generic_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }
}
