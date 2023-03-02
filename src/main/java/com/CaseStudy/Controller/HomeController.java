package com.CaseStudy.Controller;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Entities.User.User;

import com.CaseStudy.Helper.LoginCredentials;
import com.CaseStudy.Helper.SignupData;
import com.CaseStudy.Service.HomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private HomeService homeService;


    @RequestMapping("/")
    public User success(Principal principal) {
        return homeService.success(principal);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserByEmail(HttpServletRequest request){
        return new ResponseEntity<>(homeService.getUserProfile(request), HttpStatus.OK);
    }

//    @GetMapping("/welcome")
//    public String checking(){
//        return "Authentication is working";
//    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return homeService.getAllProducts();
    }

    @GetMapping("/categories")
    public List<ProductCategory> getAllCategories() {
        return homeService.getAllCategories();
    }

    @GetMapping("/subCategories")
    public List<ProductSubcategory> getAllSubCategories() {
        return homeService.getAllSubCategories();
    }

    @GetMapping("/filteredProducts")
    public List<Product> getFilteredProduct(){
        return homeService.getFilteredProduct();
    }

    @PostMapping("/signup")
    public LoginCredentials addUser(@RequestBody SignupData signupData){
        return homeService.addUser(signupData);
    }




}
