package com.CaseStudy.Service;

import com.CaseStudy.Entities.Cart.Cart;
import com.CaseStudy.Entities.Cart.CartItem;
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

@Component
public class HelperService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;



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

    public Cart updateCart(Cart cart, int productId, String operation) {
        List<CartItem> cartItems = cart.getCartItems();
        Product product = productRepository.findById(productId).get();
        switch (operation) {
            case ("addProduct"): {
                for (CartItem cartItem : cartItems) {
                    if (cartItem.getProduct() == product) {
                        cartItem.setQuantity(cartItem.getQuantity() + 1);
                        cart.setCartItems(cartItems);
                        return cart;
                    }
                }
                return new Cart();

            }
            case("addNewProduct"):{
                CartItem cartItem = new CartItem();
                cartItem.setQuantity(1);
                cartItem.setProduct(product);
                cartItems.add(cartItem);
                cart.setCartItems(cartItems);
                return cart;

            }
            case ("removeProduct"): {
                for (CartItem cartItem : cartItems) {
                    if (cartItem.getProduct() == product) {
                        cartItems.remove(cartItem);
                        cart.setCartItems(cartItems);
                        return cart;
                    }
                }
                return new Cart();

            }
        }

        return new Cart();
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
