package com.aray1.commonproblems.productmanagement.product.service.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.product.entity.PricingDocument;
import com.aray1.commonproblems.productmanagement.product.entity.ProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.entity.ProductDocument;
import com.aray1.commonproblems.productmanagement.product.model.AudioProductAttribute;
import com.aray1.commonproblems.productmanagement.product.model.ProductAttribute;
import com.aray1.commonproblems.productmanagement.product.model.ProductRequest;
import com.aray1.commonproblems.productmanagement.product.model.VideoProductAttribute;

@Service
public class ProductRequestToDocumentMapper implements Function<ProductRequest, ProductDocument> {

    private final AudioProductAttributeToDocumentMapper audioProductAttributeToDocumentMapper;
    private final VideoProductAttributeToDocumentMapper videoProductAttributeToDocumentMapper;

    @Autowired
    public ProductRequestToDocumentMapper(AudioProductAttributeToDocumentMapper audioProductAttributeToDocumentMapper,
            VideoProductAttributeToDocumentMapper videoProductAttributeToDocumentMapper) {
        this.audioProductAttributeToDocumentMapper = audioProductAttributeToDocumentMapper;
        this.videoProductAttributeToDocumentMapper = videoProductAttributeToDocumentMapper;
    }

    @Override
    public ProductDocument apply(ProductRequest productRequest) {

        ProductAttribute productAttribute = productRequest.getProductAttribute();
        ProductAttributeDocument productAttributeDocument;
        if (productAttribute instanceof AudioProductAttribute) {
            productAttributeDocument =
                    audioProductAttributeToDocumentMapper.apply((AudioProductAttribute) productAttribute);
        } else {
            productAttributeDocument =
                    videoProductAttributeToDocumentMapper.apply((VideoProductAttribute) productAttribute);
        }

        PricingDocument pricingDocument =
                new PricingDocument(productRequest.getPricing().getPrice(), productRequest.getPricing().getCurrency());
        return new ProductDocument(productRequest.getName(), productRequest.getDescription(), productAttributeDocument,
                pricingDocument, productRequest.getCategories());
    }
}
