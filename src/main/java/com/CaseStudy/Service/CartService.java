/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Service;

import com.CaseStudy.Entities.Cart.Cart;
import com.CaseStudy.Entities.Cart.CartItem;
import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.dao.CartItemRepository;
import com.CaseStudy.dao.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author beehyv
 */
@Component
public class CartService {

    //
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperService helperService;

    @Autowired
    private ProductService productService;

    public String addToCart(int userId, int productId) {
        if (userService.getUserById(userId) == null) {
            return "Wrong entry no such user exist";
        }
        if (cartRepository.findByUserId(userId) == null) {
            Cart cart = helperService.createNewCart(userId,productId);
            Cart result = cartRepository.save(cart);
            return "Product successfully added in the cart!!" + result;
        } else {
            Cart cart = cartRepository.findByUserId(userId);
            Cart updatedCart = helperService.updateCart(cart,productId,"addProduct");
            if(cart.getCartId() == updatedCart.getCartId()){
                Cart result = cartRepository.save(updatedCart);
                return "Product successfully added in the cart!!" + result;
            }

            Cart updatedCart1 = helperService.updateCart(cart,productId,"addNewProduct");
            Cart result = cartRepository.save(updatedCart1);
            return "Product successfully added in the cart!!" + result;
        }


    }

    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    public CartItem getCartItem(int cartItemId) {
        return cartItemRepository.findById(cartItemId).get();
    }

    public CartItem changeQuantity(int cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public CartItem changeQuantity2(int userId, int productId, int quantity){
        if (userService.getUserById(userId) == null) {
            return null;
        }
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getProductById(productId);
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct() == product) {
                cartItem.setQuantity(quantity);
                cart.setCartItems(cartItems);
                Cart result = cartRepository.save(cart);
                return cartItem;
            }
        }
        return null;
    }

    public String removeProduct(int userId, int productId){
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getProductById(productId);
        Cart updatedCart = helperService.updateCart(cart,productId,"removeProduct");
        if(cart.getCartId() == updatedCart.getCartId()){
            Cart result = cartRepository.save(updatedCart);
            return product.getName() + " removed!!";
        }
        return "Product does not exist in the user cart!!";
    }

}
