package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "productdetails")
public class ProductDetailEntity {
    @Id
    @Column(name = "productCode")
    private int productCode;
    @Column(name = "size")
    private String size;
    @Column(name = "frame")
    private String fame;
    @Column(name = "shock")
    private String shock;
    @Column(name = "rims")
    private String rims;
    @Column(name = "tires")
    private String tires;
    @Column(name = "handlebar")
    private String handlebar;
    @Column(name = "saddle")
    private String saddle;
    @Column(name = "pedals")
    private String pedals;
    @Column(name = "brakes")
    private String brakes;
    @Column(name = "weight")
    private String weight;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "productCode")
    private ProductEntity productEntity;

    public ProductDetailEntity() {
    }



    public ProductDetailEntity(int productCode, String size, String fame, String shock, String rims, String tires, String handlebar, String saddle, String pedals, String brakes, String weight, ProductEntity productEntity) {
        this.productCode = productCode;
        this.size = size;
        this.fame = fame;
        this.shock = shock;
        this.rims = rims;
        this.tires = tires;
        this.handlebar = handlebar;
        this.saddle = saddle;
        this.pedals = pedals;
        this.brakes = brakes;
        this.weight = weight;
        this.productEntity = productEntity;
    }

    public ProductDetailEntity(ProductDetailEntity productDetail) {
        this.productCode = productDetail.getProductCode();
        this.size = productDetail.getSize();
        this.fame = productDetail.getFame();
        this.shock = productDetail.getShock();
        this.rims = productDetail.getRims();
        this.tires = productDetail.getTires();
        this.handlebar = productDetail.getHandlebar();
        this.saddle = productDetail.getSaddle();
        this.pedals = productDetail.getPedals();
        this.brakes = productDetail.getBrakes();
        this.weight = productDetail.getWeight();
    }

    public ProductDetailEntity(int productCode, String size, String fame, String shock, String rims, String tires, String handlebar, String saddle, String pedals, String brakes, String weight) {
        this.productCode = productCode;
        this.size = size;
        this.fame = fame;
        this.shock = shock;
        this.rims = rims;
        this.tires = tires;
        this.handlebar = handlebar;
        this.saddle = saddle;
        this.pedals = pedals;
        this.brakes = brakes;
        this.weight = weight;
    }

//    public ProductEntity getProductEntity() {
//        return productEntity;
//    }
//
//    public void setProductEntity(ProductEntity productEntity) {
//        this.productEntity = productEntity;
//    }



    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFame() {
        return fame;
    }

    public void setFame(String fame) {
        this.fame = fame;
    }

    public String getShock() {
        return shock;
    }

    public void setShock(String shock) {
        this.shock = shock;
    }

    public String getRims() {
        return rims;
    }

    public void setRims(String rims) {
        this.rims = rims;
    }

    public String getTires() {
        return tires;
    }

    public void setTires(String tires) {
        this.tires = tires;
    }

    public String getHandlebar() {
        return handlebar;
    }

    public void setHandlebar(String handlebar) {
        this.handlebar = handlebar;
    }

    public String getSaddle() {
        return saddle;
    }

    public void setSaddle(String saddle) {
        this.saddle = saddle;
    }

    public String getPedals() {
        return pedals;
    }

    public void setPedals(String pedals) {
        this.pedals = pedals;
    }

    public String getBrakes() {
        return brakes;
    }

    public void setBrakes(String brakes) {
        this.brakes = brakes;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
