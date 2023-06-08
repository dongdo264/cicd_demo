package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.Date;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @Column(name = "productCode")
    private int productCode;
    @Column(name = "productLine")
    private String productLine;
    @Column(name = "productName")
    private String productName;
    @Column(name = "buyPrice")
    private int buyPrice;
    @Column(name = "warrantyPeriod")
    private String warrantyPeriod;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "createAt")
    private Date createAt;

    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductDetailEntity productDetail;

    public ProductEntity() {
    }

    public ProductEntity(int productCode, String productLine, String productName, int buyPrice, String warrantyPeriod, String status, Date createAt) {
        this.productCode = productCode;
        this.productLine = productLine;
        this.productName = productName;
        this.buyPrice = buyPrice;
        this.warrantyPeriod = warrantyPeriod;
        this.status = status;
        this.createAt = createAt;
    }

    public ProductEntity(ProductEntity product) {
        this.productCode = product.getProductCode();
        this.productLine = product.getProductLine();
        this.productName = product.getProductName();
        this.buyPrice = product.getBuyPrice();
        this.warrantyPeriod = product.getWarrantyPeriod();
        this.status = product.getStatus();
        this.createAt = product.getCreateAt();
    }
    public ProductEntity(int productCode, String productLine, String productName, int buyPrice, String warrantyPeriod, String status, Date createAt, ProductDetailEntity productDetail) {
        this.productCode = productCode;
        this.productLine = productLine;
        this.productName = productName;
        this.buyPrice = buyPrice;
        this.warrantyPeriod = warrantyPeriod;
        this.status = status;
        this.createAt = createAt;
        this.productDetail = productDetail;
    }

    public ProductDetailEntity getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailEntity productDetail) {
        this.productDetail = productDetail;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

//    public ProductDetailEntity getProductDetailEntity() {
//        return productDetail;
//    }
//
//    public void setProductDetailEntity(ProductDetailEntity productDetail) {
//        this.productDetail = productDetail;
//    }


    @Override
    public String toString() {
        return "{" +
                "productCode=" + this.getProductCode() +
                ", productLine=" + this.getProductLine()  +
                ", productName=" + this.getProductName() +
                ", buyPrice=" + this.getBuyPrice() +
                ", status=" + status +
                '}';
    }

}
