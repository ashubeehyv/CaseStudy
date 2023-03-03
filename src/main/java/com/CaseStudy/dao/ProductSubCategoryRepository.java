package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSubCategoryRepository extends JpaRepository<ProductSubcategory,Integer> {
    public ProductSubcategory findBySubCategoryName(String name);

    public boolean existsBySubCategoryName(String name);


}
