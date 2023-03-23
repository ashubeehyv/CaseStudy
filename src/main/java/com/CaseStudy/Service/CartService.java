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
            return cartRepository.save(cart);
        } else {
            Cart cart = cartRepository.findByUserId(userId);
            Cart updatedCart = helperService.updateCart(cart,productId);
            return cartRepository.save(updatedCart);
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

    public Cart removeProduct(HttpServletRequest request, int cartItemId){
        User user = helperService.fetchUserFromToken(request);
        int userId = user.getUserId();
        Cart cart = cartRepository.findByUserId(userId);
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        return cart;

    }

}
