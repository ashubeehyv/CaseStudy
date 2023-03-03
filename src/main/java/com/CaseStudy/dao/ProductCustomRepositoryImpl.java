package com.CaseStudy.dao;

import com.CaseStudy.Entities.Product.Product;
import com.CaseStudy.Entities.Product.ProductCategory;
import com.CaseStudy.Entities.Product.ProductSubcategory;
import com.CaseStudy.HelperClasses.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        List<Product> result1 = new ArrayList<>();
        if(!filter.getCategory().equals("Select All")){
            ProductCategory category = productCategoryRepository.findByCategoryName(filter.getCategory());
            Predicate categoryFinder = cb.equal(root.get("category"),category);
            Predicate range = cb.between(root.get("price"), filter.getMinValue(), filter.getMaxValue());
            cr.select(root).where(cb.and(categoryFinder,range));
            TypedQuery<Product> query = entityManager.createQuery(cr);
            List<Product> result = query.getResultList();
            if(!filter.getSubCategory().equals("Select All")){
                ProductSubcategory subcategory = productSubCategoryRepository.findBySubCategoryName(filter.getSubCategory());
                List<Product> productsSubCat = subcategory.getProducts();
                result.retainAll(productsSubCat);
            }
            return result;
        }
        else {
            cr.select(root).where(cb.between(root.get("price"), filter.getMinValue(), filter.getMaxValue()));
            TypedQuery<Product> query = entityManager.createQuery(cr);
            return query.getResultList();

        }
    }
}
