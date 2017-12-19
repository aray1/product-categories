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
public class ProductCategoriesApplication implements CommandLineRunner {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductCategoriesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
        repository.deleteAll();
        productRepository.deleteAll();

        ProductCategory parent = new ProductCategory("root", "root desc");
        ProductCategory child1 = new ProductCategory("child1", "child1 desc", parent.getId());
        ProductCategory child11 = new ProductCategory("child11", "child11 desc", child1.getId());

        ProductCategory child2 = new ProductCategory("child2", "child2 desc", parent.getId());

        // save a couple of customers
        repository.save(Arrays.asList(parent, child1, child11, child2));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (ProductCategory customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

       Product productAudio =
                new Product("My Way", "Jack desc", new AudioProductAttribute("abc_audio", "Frank Sinatra"), new Pricing(12, "EUR"),
                        Sets.newHashSet("123"));

        Product productVideo = new Product("Jacket", "Jack desc", new VideoProductAttribute("abc_video", "John Lenon", "John Lenon"),
                new Pricing(1200, "EUR"), Sets.newHashSet("123"));
        productRepository.save(Arrays.asList(productAudio, productVideo));

        for (Product customer : productRepository.findAll()) {
            System.out.println(customer);
        }
        */

    }

    private void getProductHierarchy(String id) {
        for (ProductCategoryDocument customer : repository.getChildProductCategories(id)) {
            System.out.println(customer);
            getProductHierarchy(customer.getId());
        }
    }
}
