package com.aray1.commonproblems.productmanagement.product.service.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.product.entity.ProductDocument;
import com.aray1.commonproblems.productmanagement.product.model.Pricing;
import com.aray1.commonproblems.productmanagement.product.model.ProductResponse;

@Service
public class ProductDocumentToBasicResponseMapper implements Function<ProductDocument, ProductResponse> {

    @Override
    public ProductResponse apply(ProductDocument productDocument) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productDocument.getId());
        productResponse.setDescription(productDocument.getDescription());
        productResponse.setName(productDocument.getName());

        Pricing pricing = new Pricing();
        pricing.setPrice(productDocument.getPricingDocument().getValue());
        pricing.setCurrency(productDocument.getPricingDocument().getCurrency());
        productResponse.setPricing(pricing);

        return productResponse;
    }
}
