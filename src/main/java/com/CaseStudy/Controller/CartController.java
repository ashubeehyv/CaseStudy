/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.Controller;

import com.CaseStudy.Entities.Cart.Cart;
import com.CaseStudy.Entities.Cart.CartItem;
import com.CaseStudy.Entities.Quantity;
import com.CaseStudy.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author beehyv
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {
    
    @Autowired
    private CartService cartService;

    //Add Items to the cart for the specific user
    @GetMapping("/add/{productId}")
    public Cart addToCart(HttpServletRequest request,@PathVariable("productId") int productId) {
        return cartService.addToCart(request, productId);
    }

    //To get the cart for the particular user
    @GetMapping("/getCart")
    public ResponseEntity<Cart> getCart(HttpServletRequest request){
        return new ResponseEntity<>(cartService.getCart(request), HttpStatus.OK);
    }

    //To get the cart item through cart item id
    @GetMapping("/getCartItem/{cartItemId}")
    public CartItem getCartItem(@PathVariable("cartItemId") int cartItemId){
        return cartService.getCartItem(cartItemId);
    }

    //Changing item quantity using cartItemId
    @PostMapping("/changeQuantity/{cartItemId}")
    public CartItem changeQuantity(@RequestBody Quantity data, @PathVariable("cartItemId") int cartItemId){
        return cartService.changeQuantity(cartItemId, data.getQuantity());
    }

    //Changing item quantity using userId and productId
    @PostMapping("/{userId}/changeQuantity/{productId}")
    public CartItem changeQuantity2(@RequestBody Quantity data, @PathVariable("userId") int userId, @PathVariable("productId") int productId){
        return cartService.changeQuantity2(userId, productId, data.getQuantity());
    }

    //Remove Product from cart
    @GetMapping("/remove/{productId}")
    public Cart removeProduct(HttpServletRequest request,@PathVariable("productId") int productId){
        return cartService.removeProduct(request,productId);
    }



}
