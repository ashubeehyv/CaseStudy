package com.CaseStudy.Controller;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Entities.User.User;
import com.CaseStudy.HelperClasses.LoginCredentials;
import com.CaseStudy.HelperClasses.ProductFilter;
import com.CaseStudy.HelperClasses.SignupData;
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


    //Fetching User Profile after login but its using principal
    @RequestMapping("/")
    public User success(Principal principal) {
        return homeService.success(principal);
    }


    //Fetching User Profile after login
    @GetMapping("/profile")
    public ResponseEntity<User> getUserByEmail(HttpServletRequest request){
        return new ResponseEntity<>(homeService.getUserProfile(request), HttpStatus.OK);
    }

    //Fetching entire product
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return homeService.getAllProducts();
    }

    //Fetching all categories
    @GetMapping("/categories")
    public List<ProductCategory> getAllCategories() {
        return homeService.getAllCategories();
    }

    //Fetching all subCategories
    @GetMapping("/subCategories")
    public List<ProductSubcategory> getAllSubCategories() {
        return homeService.getAllSubCategories();
    }

    //Fetching all the products after applying filter
    @PostMapping("/filteredProducts")
    public List<Product> getFilteredProduct(@RequestBody ProductFilter filter){
        return homeService.getFilteredProduct(filter);
    }


    //New user Signup
    @PostMapping("/signup")
    public LoginCredentials addUser(@RequestBody SignupData signupData){
        return homeService.addUser(signupData);
    }




}
