package com.aray1.commonproblems.productmanagement.category.service.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.category.entity.ProductCategoryDocument;
import com.aray1.commonproblems.productmanagement.category.model.ProductCategoryRequest;

@Service
public class ProductCategoryRequestToDocumentMapper implements Function<ProductCategoryRequest, ProductCategoryDocument> {
    @Override
    public ProductCategoryDocument apply(ProductCategoryRequest productCategoryRequest) {
        return new ProductCategoryDocument(productCategoryRequest.getName(), productCategoryRequest.getDescription(),
                productCategoryRequest.getParentId());
    }
}
