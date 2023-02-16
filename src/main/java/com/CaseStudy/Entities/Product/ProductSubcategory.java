package com.CaseStudy.Entities.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProductSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subCategoryId;

    private String subCategoryName;

    @JsonIgnore
    @ManyToMany(mappedBy = "subCategories", fetch = FetchType.LAZY)
    private List<Product> products;

    public ProductSubcategory() {
    }

    public ProductSubcategory(int subCategoryId, String subCategoryName, List<Product> products) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.products = products;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductSubcategory{" +
                "subCategoryId=" + subCategoryId +
                ", categoryName='" + subCategoryName + '\'' +
                '}';
    }
}
