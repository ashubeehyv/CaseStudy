/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Service;

import com.CaseStudy.Entities.Cart;
import com.CaseStudy.Entities.CartItem;
import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.dao.CartItemRepository;
import com.CaseStudy.dao.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    UserService userService;

    @Autowired
    private ProductService productService;

    public String addToCart(int userId, int productId) {
        if (userService.getUserById(userId) == null) {
            return "Wrong entry no such user exist";
        }
        if (cartRepository.findByUserId(userId) == null) {
            Cart cart = new Cart();
            cart.setUserId(userId);
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productService.getProductById(productId));
            cartItem.setQuantity(1);
            List<CartItem> products = new ArrayList<>();
            products.add(cartItem);
            cart.setCartItems(products);
            Cart result = cartRepository.save(cart);
            return "Product successfully added in the cart!!" + cart;
        } else {
            Cart cart = cartRepository.findByUserId(userId);
            List<CartItem> products = cart.getCartItems();
            Product product = productService.getProductById(productId);
            for (CartItem cartItem : products) {
                if (cartItem.getProduct() == product) {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    cart.setCartItems(products);
                    Cart result = cartRepository.save(cart);
                    return "Product successfully added in the cart!!" + cart;
                }
            }
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            products.add(cartItem);
            cart.setCartItems(products);
            Cart result = cartRepository.save(cart);
            return "Product successfully added in the cart!!" + cart;


        }


    }

    public Cart getCartByUserId(int userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return cart;
    }

    public CartItem getCartItem(int cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        return cartItem;
    }

    public CartItem changeQuantity(int cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).get();
        cartItem.setQuantity(quantity);
        CartItem result = cartItemRepository.save(cartItem);
        return cartItem;
    }

    public CartItem changeQuantity2(int userId, int productId, int quantity){
        if (userService.getUserById(userId) == null) {
            return null;
        }
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getProductById(productId);
        List<CartItem> products = cart.getCartItems();
        for (CartItem cartItem : products) {
            if (cartItem.getProduct() == product) {
                cartItem.setQuantity(quantity);
                cart.setCartItems(products);
                Cart result = cartRepository.save(cart);
                return cartItem;
            }
        }
        return null;
    }

    public String removeProduct(int userId, int productId){
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.getProductById(productId);
        List<CartItem> products = cart.getCartItems();
        for (CartItem cartItem : products) {
            if (cartItem.getProduct() == product) {
                products.remove(cartItem);
                cart.setCartItems(products);
                cartItemRepository.delete(cartItem);
                Cart result = cartRepository.save(cart);
                return product.getName() + " removed!!";
            }
        }
        return "Product does not exist in the user cart!!";
    }

}
