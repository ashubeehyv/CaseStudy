package com.CaseStudy.HelperClasses;


public class ProductFilter {
    private String category;
    private String subCategory;
    private int minValue;
    private int maxValue;

    public ProductFilter() {
    }

    public ProductFilter(String category, String subCategory, int minValue, int maxValue) {
        this.category = category;
        this.subCategory = subCategory;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public String toString() {
        return "ProductFilter{" +
                "category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
