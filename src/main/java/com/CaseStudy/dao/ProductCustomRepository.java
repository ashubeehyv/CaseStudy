package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.Product;
import java.util.List;

public interface ProductCustomRepository {
    public List<Product> getFilteredProducts();
}
