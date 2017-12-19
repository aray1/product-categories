package com.aray1.commonproblems.productmanagement.category.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.category.entity.ProductCategoryDocument;
import com.aray1.commonproblems.productmanagement.category.model.ProductCategoryRequest;
import com.aray1.commonproblems.productmanagement.category.model.ProductCategoryResponse;
import com.aray1.commonproblems.productmanagement.category.repository.ProductCategoryRepository;
import com.aray1.commonproblems.productmanagement.category.service.mapper.ProductCategoryDocumentToResponseMapper;
import com.aray1.commonproblems.productmanagement.category.service.mapper.ProductCategoryRequestToDocumentMapper;
import com.aray1.commonproblems.productmanagement.exception.ProductCategoriesException;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryRequestToDocumentMapper productCategoryRequestToDocumentMapper;
    private final ProductCategoryDocumentToResponseMapper productCategoryDocumentToResponseMapper;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository,
            ProductCategoryRequestToDocumentMapper productCategoryRequestToDocumentMapper,
            ProductCategoryDocumentToResponseMapper productCategoryDocumentToResponseMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryRequestToDocumentMapper = productCategoryRequestToDocumentMapper;
        this.productCategoryDocumentToResponseMapper = productCategoryDocumentToResponseMapper;
    }

    public ProductCategoryResponse createCategory(ProductCategoryRequest productCategoryRequest) {
        if (productCategoryRequest.getParentId() != null) {
            ProductCategoryDocument parentProductCategoryDocument =
                    productCategoryRepository.findOne(productCategoryRequest.getParentId());
            if (parentProductCategoryDocument == null) {
                throw new ProductCategoriesException("Invalid parent category id");
            }
        }

        ProductCategoryDocument createdProductCategoryDocument =
                productCategoryRepository.save(productCategoryRequestToDocumentMapper.apply(productCategoryRequest));
        return productCategoryDocumentToResponseMapper.apply(createdProductCategoryDocument);
    }

    public ProductCategoryResponse getCategory(String productCategoryId) {
        ProductCategoryDocument productCategoryDocument = productCategoryRepository.findOne(productCategoryId);
        if (productCategoryDocument == null) {
            throw new ProductCategoriesException("Product category not found");
        }
        return productCategoryDocumentToResponseMapper.apply(productCategoryDocument);
    }

    public List<ProductCategoryResponse> getAllCategories() {
        return productCategoryRepository.findAll().stream().map(productCategoryDocumentToResponseMapper)
                .collect(toList());
    }

    public List<ProductCategoryResponse> getCategoryHierarchy(String parentProductCategoryId) {
        return getProductCategoryHierarchy(parentProductCategoryId).stream()
                .map(productCategoryDocumentToResponseMapper).collect(toList());
    }

    public void deleteProductCategory(String productCategoryId) {
        deleteProductCategoryHierarchy(productCategoryId);
        productCategoryRepository.delete(productCategoryId);
    }

    public void updateProductCategory(String productCategoryId, ProductCategoryRequest productCategoryRequest) {
        ProductCategoryDocument productCategoryDocument = productCategoryRepository.findOne(productCategoryId);
        if (productCategoryDocument == null) {
            throw new ProductCategoriesException("Product category not found");
        }

        if (productCategoryRequest.getParentId() != null) {
            ProductCategoryDocument parentProductCategoryDocument =
                    productCategoryRepository.findOne(productCategoryRequest.getParentId());
            if (parentProductCategoryDocument == null) {
                throw new ProductCategoriesException("Parent product category not found");
            }
        }

        if (getChildIds(productCategoryDocument.getId(), new HashSet<>())
                .contains(productCategoryRequest.getParentId())) {
            throw new ProductCategoriesException("Invalid parent product category");
        }

        productCategoryRepository
                .save(new ProductCategoryDocument(productCategoryDocument.getId(), productCategoryRequest.getName(),
                        productCategoryRequest.getDescription(), productCategoryRequest.getParentId()));
    }

    private Set<String> getChildIds(String parentProductCategoryId, Set<String> subCategoryIds) {
        List<ProductCategoryDocument> childProductCategories =
                productCategoryRepository.getChildProductCategories(parentProductCategoryId);
        for (ProductCategoryDocument productCategoryDocument : childProductCategories) {
            subCategoryIds.add(productCategoryDocument.getId());
            subCategoryIds.addAll(getChildIds(productCategoryDocument.getId(), subCategoryIds));
        }
        return subCategoryIds;
    }

    private List<ProductCategoryDocument> getProductCategoryHierarchy(String parentProductCategoryId) {
        List<ProductCategoryDocument> childProductCategories =
                productCategoryRepository.getChildProductCategories(parentProductCategoryId);

        List<ProductCategoryDocument> productCategories = new ArrayList<>();
        for (ProductCategoryDocument productCategoryDocument : childProductCategories) {
            productCategoryDocument.addSubCategories(getProductCategoryHierarchy(productCategoryDocument.getId()));
            productCategories.add(productCategoryDocument);
        }

        return productCategories;
    }

    private void deleteProductCategoryHierarchy(String productCategoryId) {
        List<ProductCategoryDocument> childProductCategories = getProductCategoryHierarchy(productCategoryId);
        for (ProductCategoryDocument productCategoryDocument : childProductCategories) {
            deleteProductCategoryHierarchy(productCategoryDocument.getId());
            productCategoryRepository.delete(productCategoryDocument);
        }
    }

}
