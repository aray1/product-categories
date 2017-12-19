package com.aray1.commonproblems.productmanagement.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aray1.commonproblems.productmanagement.product.entity.ProductDocument;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {

}