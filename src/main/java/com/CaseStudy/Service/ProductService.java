/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Service;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.dao.ProductCategoryRepository;
import com.CaseStudy.dao.ProductRepository;
import com.CaseStudy.dao.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public Product getProductById(int id) {
        Product product = productRepository.findById(id).get();
        return product;

    }

    public List<Product> getProductsByCategory(String categoryName) {
        ProductCategory category = productCategoryRepository.findByCategoryName(categoryName);
        List<Product> products = productRepository.findAllByCategory(category);
        return products;

    }
    public List<Product> getProductsBySubCategory(String subCategoryName) {
        ProductSubcategory subCategory = productSubCategoryRepository.findBySubCategoryName(subCategoryName);
        List<Product> products = subCategory.getProducts();
        return products;
    }

    public String addProduct(Product product) {
        if (!productRepository.existsByName(product.getName())) {
            Product result = productRepository.save(product);
            return "Product successfully added!!" + result;
        }
        else{
            return "Product all ready exist!!";
        }

}

    public String updateProduct(Product modifiedProduct) {
        if (productRepository.existsByName(modifiedProduct.getName())) {
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

    public Product makeProperProduct(Product product){
        //Category Fixing
        ProductCategory category = product.getCategory();
        if(!productCategoryRepository.existsByCategoryName(category.getCategoryName())){
            product.setCategory(category);
        }
        else{
            product.setCategory(productCategoryRepository.findByCategoryName(category.getCategoryName()));
        }

        //SubCategory Fixing
        List<ProductSubcategory> subCategories = product.getSubcategories();
        List<ProductSubcategory> subcategoriesToAdd = new ArrayList<>();
        for(ProductSubcategory subCategory:subCategories){
            if(productSubCategoryRepository.existsBySubCategoryName(subCategory.getSubCategoryName())){
                subcategoriesToAdd.add(productSubCategoryRepository.findBySubCategoryName(subCategory.getSubCategoryName()));
            }
            else{
                subcategoriesToAdd.add(subCategory);
            }
        }
        product.setSubcategories(subcategoriesToAdd);


        return product;
    }

    public void upDateCategoryTable(ProductCategory category){
        List<Product> products = productRepository.findAllByCategory(category);
        if (products.isEmpty()) {
            productCategoryRepository.delete(category);
        }
    }

    public void upDateSubCategoryTable(List<ProductSubcategory> subcategories){
        for (ProductSubcategory subcategory : subcategories) {
            ProductSubcategory products1 = productSubCategoryRepository.findById(subcategory.getSubCategoryId()).get();
            if (products1.getProducts().isEmpty()) {
                productSubCategoryRepository.delete(subcategory);
            }
        }
    }

    public void updateRelatedTables(ProductCategory category,List<ProductSubcategory> subcategories){
        List<Product> products = productRepository.findAllByCategory(category);
        if (products.isEmpty()) {
            productCategoryRepository.delete(category);
        }
        for (ProductSubcategory subcategory : subcategories) {
            ProductSubcategory products1 = productSubCategoryRepository.findById(subcategory.getSubCategoryId()).get();
            if (products1.getProducts().isEmpty()) {
                productSubCategoryRepository.delete(subcategory);
            }
        }
    }


}
