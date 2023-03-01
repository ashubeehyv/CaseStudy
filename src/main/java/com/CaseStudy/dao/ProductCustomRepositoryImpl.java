package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.Helper.ProductFilter;
import com.CaseStudy.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductCustomRepositoryImpl implements ProductCustomRepository{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Product> getFilteredProducts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cr = cb.createQuery(Product.class);
        Root<Product> root = cr.from(Product.class);
        ProductCategory category = new ProductCategory(2,"Fruit");
        int minValue= 0;
        int maxValue= 500;
//        ProductSubcategory subcategory = new ProductSubcategory(7,"Sweet",null);
        Predicate categoryFinder = cb.equal(root.get("category"),category);
        Predicate range = cb.between(root.get("price"), minValue, maxValue);
        cr.select(root).where(cb.and(categoryFinder,range));
        TypedQuery<Product> query = entityManager.createQuery(cr);

        return query.getResultList();
    }
}
