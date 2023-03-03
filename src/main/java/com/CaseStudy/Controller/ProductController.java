/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Controller;

import com.CaseStudy.Entities.Product.Product;
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
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;
    

    @GetMapping("getById/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productService.getProductById(id);
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public String modifyProduct(@RequestBody Product modifiedProduct) {
        return productService.updateProduct(modifiedProduct);
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId){
        return productService.deleteProductById(productId);
    }

    @GetMapping("/subcategory/{subCategoryName}")
    public List<Product> getProductsBySubCategory(@PathVariable("subCategoryName") String categoryName) {
        return productService.getProductsBySubCategory(categoryName);
    }

}
