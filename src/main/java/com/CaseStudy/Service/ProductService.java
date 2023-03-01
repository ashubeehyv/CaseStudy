/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Service;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Helper.ProductFilter;
import com.CaseStudy.dao.ProductCategoryRepository;
import com.CaseStudy.dao.ProductRepository;
import com.CaseStudy.dao.ProductSubCategoryRepository;


import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.List;

/**
 * @author beehyv
 */
@Component
public class ProductService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HelperService helperService;

    public Product getProductById(int id) {
        return productRepository.findById(id).get();

    }

    public List<Product> getProductsByCategory(String categoryName) {
        ProductCategory category = productCategoryRepository.findByCategoryName(categoryName);
        return productRepository.findAllByCategory(category);

    }
    public List<Product> getProductsBySubCategory(String subCategoryName) {
        ProductSubcategory subCategory = productSubCategoryRepository.findBySubCategoryName(subCategoryName);
        return subCategory.getProducts();
    }

    public String addProduct(Product product) {
        Product productToAdd = helperService.makeProperProduct(product);
        if (!productRepository.existsByName(productToAdd.getName())) {
            Product result = productRepository.save(product);
            return "Product successfully added!!" + result;
        }
        else{
            return "Product all ready exist!!";
        }

}

    public String updateProduct(Product modifiedProduct) {
        Product productToUpdate = helperService.makeProperProduct(modifiedProduct);
        if (productRepository.existsByName(productToUpdate.getName())) {
            Product product = productRepository.findByName(modifiedProduct.getName());
            modifiedProduct.setProductId(product.getProductId());
            Product result = productRepository.save(modifiedProduct);
            return "Product successfully updated!!" + result;
        } else {
            return "Product does not exist, so it can't be updated!!";
        }

    }

    public String deleteProductById(int id) {
        if (productRepository.existsById(id)) {
            Product product = productRepository.findById(id).get();
            product.setCategory(null);
            product.setSubcategories(null);
            Product val = productRepository.save(product);
            productRepository.deleteById(id);
            return "Product successfully deleted!!";
        } else {
            return "Product does not exist, so it can't be deleted!!";
        }

    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<ProductCategory> getAllCategories(){
        return productCategoryRepository.findAll();
    }

    public List<ProductSubcategory> getAllSubCategories(){
        return productSubCategoryRepository.findAll();
    }

    public List<Product> getFilteredProduct() {
        return productRepository.getFilteredProducts();

    }

    public ProductCategory getCategory(String category){
        return productCategoryRepository.findByCategoryName(category);
    }
}
