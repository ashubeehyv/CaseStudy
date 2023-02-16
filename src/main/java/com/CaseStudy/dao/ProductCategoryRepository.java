package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    public ProductCategory findByCategoryName(String name);

    public boolean existsByCategoryName(String name);
}
