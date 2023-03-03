/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 *
 * @author beehyv
 */
public interface ProductRepository extends JpaRepository<Product,Integer>, ProductCustomRepository{

    public List<Product> findAllByCategory(ProductCategory category);

    public boolean existsByName(String name);

    public Product findByName(String productName);


    
}
