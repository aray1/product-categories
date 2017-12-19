package com.aray1.commonproblems.productmanagement.category.service.mapper;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.category.entity.ProductCategoryDocument;
import com.aray1.commonproblems.productmanagement.category.model.ProductCategoryResponse;

@Service
public class ProductCategoryDocumentToResponseMapper implements Function<ProductCategoryDocument, ProductCategoryResponse> {
    @Override
    public ProductCategoryResponse apply(ProductCategoryDocument productCategoryDocument) {
        ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
        productCategoryResponse.setId(productCategoryDocument.getId());
        productCategoryResponse.setName(productCategoryDocument.getName());
        productCategoryResponse.setDescription(productCategoryDocument.getDescription());
        productCategoryResponse.setParentId(productCategoryDocument.getParentId());

        productCategoryResponse
                .addSubcategories(productCategoryDocument.getSubcategories().stream().map(this).collect(Collectors.toList()));

        return productCategoryResponse;
    }
}
