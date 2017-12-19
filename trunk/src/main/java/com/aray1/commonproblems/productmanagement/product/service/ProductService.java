package com.aray1.commonproblems.productmanagement.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.exception.ProductCategoriesException;
import com.aray1.commonproblems.productmanagement.product.entity.ProductDocument;
import com.aray1.commonproblems.productmanagement.product.model.ProductRequest;
import com.aray1.commonproblems.productmanagement.product.model.ProductResponse;
import com.aray1.commonproblems.productmanagement.product.repository.ProductRepository;
import com.aray1.commonproblems.productmanagement.product.service.mapper.ProductDocumentToBasicResponseMapper;
import com.aray1.commonproblems.productmanagement.product.service.mapper.ProductDocumentToResponseMapper;
import com.aray1.commonproblems.productmanagement.product.service.mapper.ProductRequestToDocumentMapper;

@Service
public class ProductService {

    private final CurrencyService currencyService;
    private final ProductRepository productRepository;
    private final ProductRequestToDocumentMapper productRequestToDocumentMapper;
    private final ProductDocumentToResponseMapper productDocumentToResponseMapper;
    private final ProductDocumentToBasicResponseMapper productDocumentToBasicResponseMapper;

    @Autowired
    public ProductService(CurrencyService currencyService, ProductRepository productRepository,
            ProductRequestToDocumentMapper productRequestToDocumentMapper,
            ProductDocumentToResponseMapper productDocumentToResponseMapper,
            ProductDocumentToBasicResponseMapper productDocumentToBasicResponseMapper) {
        this.currencyService = currencyService;
        this.productRepository = productRepository;
        this.productRequestToDocumentMapper = productRequestToDocumentMapper;
        this.productDocumentToResponseMapper = productDocumentToResponseMapper;
        this.productDocumentToBasicResponseMapper = productDocumentToBasicResponseMapper;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        currencyService.validateCurrency(productRequest.getPricing().getCurrency());
        ProductDocument productDocument = productRequestToDocumentMapper.apply(productRequest);
        return productDocumentToResponseMapper.apply(productRepository.save(productDocument));
    }

    public ProductResponse getProductById(String productId) {
        ProductDocument productDocument = productRepository.findOne(productId);
        if (productDocument == null) {
            throw new ProductCategoriesException("Product not found");
        }
        return productDocumentToResponseMapper.apply(productDocument);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productDocumentToBasicResponseMapper)
                .collect(Collectors.toList());
    }

    public void updateProduct(String productId, ProductRequest productRequest) {
        ProductDocument productDocument = productRequestToDocumentMapper.apply(productRequest);

        if (!productRepository.exists(productId)) {
            throw new ProductCategoriesException("Product not found");
        }
        ProductDocument productDocumentWithUpdatedValues =
                new ProductDocument(productId, productDocument.getName(), productDocument.getDescription(),
                        productDocument.getProductAttributeDocument(), productDocument.getPricingDocument(),
                        productDocument.getCategories());
        productRepository.save(productDocumentWithUpdatedValues);
    }

    public void deleteProduct(String productId) {
        productRepository.delete(productId);
    }

}
