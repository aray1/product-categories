package com.aray1.commonproblems.productmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aray1.commonproblems.productmanagement.category.entity.ProductCategoryDocument;
import com.aray1.commonproblems.productmanagement.category.repository.ProductCategoryRepository;
import com.aray1.commonproblems.productmanagement.category.service.ProductCategoryService;
import com.aray1.commonproblems.productmanagement.product.repository.ProductRepository;

@SpringBootApplication
public class ProductCategoriesApplication {   

    public static void main(String[] args) {
        SpringApplication.run(ProductCategoriesApplication.class, args);
    }   
}
