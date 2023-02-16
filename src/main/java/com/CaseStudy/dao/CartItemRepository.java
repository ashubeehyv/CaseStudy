package com.CaseStudy.dao;

import com.CaseStudy.Entities.Cart.CartItem;
import com.CaseStudy.Entities.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    public CartItem findByProduct(Product product);
}
