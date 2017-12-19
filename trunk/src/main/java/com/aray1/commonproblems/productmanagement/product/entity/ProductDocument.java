package com.aray1.commonproblems.productmanagement.product.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class ProductDocument {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private ProductAttributeDocument productAttributeDocument;

    @Field
    private PricingDocument pricingDocument;

    @Field
    private Set<String> categories = new HashSet<>();

    public ProductDocument() {
    }

    public ProductDocument(String name, String description, ProductAttributeDocument productAttributeDocument,
            PricingDocument pricingDocument, Set<String> categories) {
        this(null, name, description, productAttributeDocument, pricingDocument, categories);
    }

    public ProductDocument(String id, String name, String description,
            ProductAttributeDocument productAttributeDocument, PricingDocument pricingDocument,
            Set<String> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productAttributeDocument = productAttributeDocument;
        this.pricingDocument = pricingDocument;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductAttributeDocument getProductAttributeDocument() {
        return productAttributeDocument;
    }

    public PricingDocument getPricingDocument() {
        return pricingDocument;
    }

    public Set<String> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
