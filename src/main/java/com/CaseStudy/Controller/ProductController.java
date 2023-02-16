/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Controller;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author beehyv
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    

    @GetMapping("getById/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        Product product = productService.getProductById(id);
        return product;
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        String result = productService.addProduct(product);
        return result;
    }

    @PutMapping("/update")
    public String modifyProduct(@RequestBody Product modifiedProduct) {
        String result = productService.updateProduct(modifiedProduct);
        return result;
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        List<Product> products = productService.getProductsByCategory(categoryName);
        return products;
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId){
        String result = productService.deleteProductById(productId);
        return result;
    }

    @GetMapping("/subcategory/{subCategoryName}")
    public List<Product> getProductsBySubCategory(@PathVariable("subCategoryName") String categoryName) {
        List<Product> products = productService.getProductsBySubCategory(categoryName);
        return products;
    }

}
