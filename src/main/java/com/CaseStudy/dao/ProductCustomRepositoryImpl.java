package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.HelperClasses.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCustomRepositoryImpl implements ProductCustomRepository{

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private  ProductSubCategoryRepository productSubCategoryRepository;
    @Override
    public List<Product> getFilteredProducts(ProductFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cr = cb.createQuery(Product.class);
        Root<Product> root = cr.from(Product.class);
        Join<Product, ProductSubcategory> subcategories = root.joinList("subCategories", JoinType.INNER);
        Predicate range = cb.between(root.get("price"), filter.getMinValue(), filter.getMaxValue());
        if(!filter.getCategory().equals("Select All")){
            ProductCategory category = productCategoryRepository.findByCategoryName(filter.getCategory());
            Predicate categoryFinder = cb.equal(root.get("category"),category);


            if(!filter.getSubCategory().equals("Select All")){
                ProductSubcategory subcategory = productSubCategoryRepository.findBySubCategoryName(filter.getSubCategory());
                Predicate subcategoryFinder = cb.equal(subcategories,subcategory);
                cr.select(root).where(cb.and(categoryFinder,range,subcategoryFinder)).distinct(true);
                TypedQuery<Product> query = entityManager.createQuery(cr);
                List<Product> result = query.getResultList();
                return result;
            }
            cr.select(root).where(cb.and(categoryFinder,range)).distinct(true);
            TypedQuery<Product> query = entityManager.createQuery(cr);
            List<Product> result = query.getResultList();
            return result;
        }
        else {
            if(!filter.getSubCategory().equals("Select All")){
                ProductSubcategory subcategory = productSubCategoryRepository.findBySubCategoryName(filter.getSubCategory());
                Predicate subcategoryFinder = cb.equal(subcategories,subcategory);
                cr.select(root).where(cb.and(range,subcategoryFinder)).distinct(true);
                TypedQuery<Product> query = entityManager.createQuery(cr);
                List<Product> result = query.getResultList();
                return result;
            }
            cr.select(root).where(cb.between(root.get("price"), filter.getMinValue(), filter.getMaxValue())).distinct(true);
            TypedQuery<Product> query = entityManager.createQuery(cr);
            List<Product> result = query.getResultList();
            return result;

        }
    }

    public List<Product> getSearchedProducts(String value){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cr = cb.createQuery(Product.class);
        Root<Product> root = cr.from(Product.class);
        cr.select(root).where(cb.like(root.get("name"),"%" + value +"%"));
        TypedQuery<Product> query = entityManager.createQuery(cr);
        List<Product> result = query.getResultList();
        return result;
    }
}
