package com.aray1.commonproblems.productmanagement.category.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.aray1.commonproblems.productmanagement.category.entity.ProductCategoryDocument;

public interface ProductCategoryRepository extends MongoRepository<ProductCategoryDocument, String> {

    @Query("{'parentId' : ?0}")
    List<ProductCategoryDocument> getChildProductCategories(String productCategoryId);

}