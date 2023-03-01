/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Entities.Product;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author beehyv
 */
@Entity
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    
    private String name;
    
    private int price;

    private String imageUrl;
    private String detail;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory category;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductSubcategory> subCategories;
    

    public Product() {
    }

    public Product(int productId, String name, int price, String imageUrl, String detail, ProductCategory category, List<ProductSubcategory> subCategories) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.detail = detail;
        this.category = category;
        this.subCategories = subCategories;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<ProductSubcategory> getSubcategories() {
        return subCategories;
    }

    public void setSubcategories(List<ProductSubcategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", detail='" + detail + '\'' +
                ", category=" + category +
                ", subCategories=" + subCategories +
                '}';
    }
}
