package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.HelperClasses.ProductFilter;

import java.util.List;

public interface ProductCustomRepository {
    public List<Product> getFilteredProducts(ProductFilter filter);

    public List<Product> getSearchedProducts(String searchString);
}
