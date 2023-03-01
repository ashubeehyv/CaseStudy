package com.CaseStudy.Controller;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Entities.User.User;
import com.CaseStudy.Helper.ProductFilter;
import com.CaseStudy.Service.ProductService;
import com.CaseStudy.Service.UserService;
import com.CaseStudy.dao.ProductCategoryRepository;
import com.CaseStudy.dao.ProductRepository;
import com.CaseStudy.dao.ProductSubCategoryRepository;
import com.CaseStudy.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public User success(Principal principal) {
        String email = principal.getName();
        return userService.getUserByEmail(email);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/categories")
    public List<ProductCategory> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/subCategories")
    public List<ProductSubcategory> getAllSubCategories() {
        return productService.getAllSubCategories();
    }

    @GetMapping("/filteredProducts")
    public List<Product> getFilteredProduct(){
        return productService.getFilteredProduct();
    }


}
