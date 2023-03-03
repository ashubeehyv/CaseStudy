/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Service;

import com.CaseStudy.Entities.Cart.Cart;
import com.CaseStudy.Entities.Cart.CartItem;
import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.User.User;
import com.CaseStudy.dao.CartItemRepository;
import com.CaseStudy.dao.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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

    public Cart addToCart(HttpServletRequest request, int productId) {
        User user = helperService.fetchUserFromToken(request);
        int userId = user.getUserId();
        if (cartRepository.findByUserId(userId) == null) {
            Cart cart = helperService.createNewCart(userId,productId);
            Cart result = cartRepository.save(cart);
            return result;
        } else {
            Cart cart = cartRepository.findByUserId(userId);
            Cart updatedCart = helperService.updateCart(cart,productId,"addProduct");
            if(cart.getCartId() == updatedCart.getCartId()){
                Cart result = cartRepository.save(updatedCart);
                return result;
            }

            Cart updatedCart1 = helperService.updateCart(cart,productId,"addNewProduct");
            Cart result = cartRepository.save(updatedCart1);
            return result;
        }


    }

    public Cart getCart(HttpServletRequest request) {
        User user = helperService.fetchUserFromToken(request);
        return cartRepository.findByUserId(user.getUserId());
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

    public Cart removeProduct(HttpServletRequest request, int productId){
        User user = helperService.fetchUserFromToken(request);
        int userId = user.getUserId();
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getProductById(productId);
        Cart updatedCart = helperService.updateCart(cart,productId,"removeProduct");
        Cart result = cartRepository.save(updatedCart);
        return result;

    }

}
