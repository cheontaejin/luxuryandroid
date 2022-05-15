package com.example.luxuryshop;

import java.util.List;

public class ProductDto {

    private Long id;
    private String productName;
    private String productContent;
    private Integer productPrice;
    private String productCategory;
    private List<String> productImageurl;
    private List<String> productSize;
    private List<String> productColor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public List<String> getProductImageurl() {
        return productImageurl;
    }

    public void setProductImageurl(List<String> productImageurl) {
        this.productImageurl = productImageurl;
    }

    public List<String> getProductSize() {
        return productSize;
    }

    public void setProductSize(List<String> productSize) {
        this.productSize = productSize;
    }

    public List<String> getProductColor() {
        return productColor;
    }

    public void setProductColor(List<String> productColor) {
        this.productColor = productColor;
    }

}

