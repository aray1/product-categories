package com.aray1.commonproblems.productmanagement.product.service.mapper;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.product.entity.AudioProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.entity.ProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.entity.ProductDocument;
import com.aray1.commonproblems.productmanagement.product.entity.VideoProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.model.Pricing;
import com.aray1.commonproblems.productmanagement.product.model.ProductAttribute;
import com.aray1.commonproblems.productmanagement.product.model.ProductResponse;
import com.aray1.commonproblems.productmanagement.product.service.CurrencyService;

@Service
public class ProductDocumentToResponseMapper implements Function<ProductDocument, ProductResponse> {

    private final CurrencyService currencyService;
    private final AudioProductDocumentToAttributeMapper audioProductAttributeToDocumentMapper;
    private final VideoProductDocumentToAttributeMapper videoProductAttributeToDocumentMapper;

    @Autowired
    public ProductDocumentToResponseMapper(CurrencyService currencyService,
            AudioProductDocumentToAttributeMapper audioProductAttributeToDocumentMapper,
            VideoProductDocumentToAttributeMapper videoProductAttributeToDocumentMapper) {
        this.currencyService = currencyService;
        this.audioProductAttributeToDocumentMapper = audioProductAttributeToDocumentMapper;
        this.videoProductAttributeToDocumentMapper = videoProductAttributeToDocumentMapper;
    }

    @Override
    public ProductResponse apply(ProductDocument productDocument) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(productDocument.getId());
        productResponse.setDescription(productDocument.getDescription());
        productResponse.setName(productDocument.getName());

        Pricing pricing = new Pricing();
        Double exchangeRate = currencyService.getExchangeRate(productDocument.getPricingDocument().getCurrency());
        pricing.setPrice(productDocument.getPricingDocument().getValue() / exchangeRate);
        pricing.setCurrency("EUR");
        productResponse.setPricing(pricing);

        ProductAttributeDocument productAttributeDocument = productDocument.getProductAttributeDocument();
        ProductAttribute productAttribute;
        if (productAttributeDocument instanceof AudioProductAttributeDocument) {
            productAttribute = audioProductAttributeToDocumentMapper
                    .apply((AudioProductAttributeDocument) productAttributeDocument);
        } else {
            productAttribute = videoProductAttributeToDocumentMapper
                    .apply((VideoProductAttributeDocument) productAttributeDocument);
        }

        productResponse.setProductAttribute(productAttribute);

        return productResponse;
    }
}
