package com.CaseStudy.Service;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Helper.LoginCredentials;
import com.CaseStudy.Helper.SignupData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CaseStudy.Entities.User.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HelperService helperService;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public User getUserProfile(HttpServletRequest request){
        return helperService.fetchUserFromToken(request);

    }

    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    public List<ProductCategory> getAllCategories() {
        return productService.getAllCategories();
    }

    public List<ProductSubcategory> getAllSubCategories() {
        return productService.getAllSubCategories();
    }

    public List<Product> getFilteredProduct(){
        return productService.getFilteredProduct();
    }

    public User success(Principal principal) {
        String email = principal.getName();
        return userService.getUserByEmail(email);
    }

    public LoginCredentials addUser(SignupData signupData) {
        System.out.println(signupData);
        return userService.addUser(signupData);
    }
}
