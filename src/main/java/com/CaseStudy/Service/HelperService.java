package com.CaseStudy.Service;

import com.CaseStudy.Config.JwtUtil;
import com.CaseStudy.Entities.Cart.Cart;
import com.CaseStudy.Entities.Cart.CartItem;
import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Entities.User.User;
import com.CaseStudy.dao.ProductCategoryRepository;
import com.CaseStudy.dao.ProductRepository;
import com.CaseStudy.dao.ProductSubCategoryRepository;
import com.CaseStudy.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class HelperService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;



    public Cart createNewCart(int userId, int productId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepository.findById(productId).get());
        cartItem.setQuantity(1);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);
        return cart;
    }

    public Cart updateCart(Cart cart, int productId) {
        List<CartItem> cartItems = cart.getCartItems();
        Product product = productRepository.findById(productId).get();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct() == product) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cart.setCartItems(cartItems);
                return cart;
            }
        }

        CartItem newcartItem = new CartItem();
        newcartItem.setQuantity(1);
        newcartItem.setProduct(product);
        cartItems.add(newcartItem);
        cart.setCartItems(cartItems);
        return cart;
    }

    public Product makeProperProduct(Product product){
        //Category Fixing
        ProductCategory category = product.getCategory();
        /*Checking the above category is in my db or not if not I am adding else replacing this category with the existing
          category. If not the db with category duplicate category with different id*/
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
                //If subcategory exists in db, adding the existing subcategory in the list
                subcategoriesToAdd.add(productSubCategoryRepository.findBySubCategoryName(subCategory.getSubCategoryName()));
            }
            else{
                //Here adding the subcategory as new
                subcategoriesToAdd.add(subCategory);
            }
        }
        product.setSubcategories(subcategoriesToAdd);


        return product;
    }

    public User fetchUserFromToken(HttpServletRequest request){
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);
        String email = this.jwtUtil.extractUsername(jwtToken);
        return userRepository.findByEmail(email);
    }

//    public void upDateCategoryTable(ProductCategory category){
//        List<Product> products = productRepository.findAllByCategory(category);
//        if (products.isEmpty()) {
//            productCategoryRepository.delete(category);
//        }
//    }
//
//    public void upDateSubCategoryTable(List<ProductSubcategory> subcategories){
//        for (ProductSubcategory subcategory : subcategories) {
//            ProductSubcategory products1 = productSubCategoryRepository.findById(subcategory.getSubCategoryId()).get();
//            if (products1.getProducts().isEmpty()) {
//                productSubCategoryRepository.delete(subcategory);
//            }
//        }
//    }
//
//    public void updateRelatedTables(ProductCategory category,List<ProductSubcategory> subcategories){
//        List<Product> products = productRepository.findAllByCategory(category);
//        if (products.isEmpty()) {
//            productCategoryRepository.delete(category);
//        }
//        for (ProductSubcategory subcategory : subcategories) {
//            ProductSubcategory products1 = productSubCategoryRepository.findById(subcategory.getSubCategoryId()).get();
//            if (products1.getProducts().isEmpty()) {
//                productSubCategoryRepository.delete(subcategory);
//            }
//        }
//    }


}
