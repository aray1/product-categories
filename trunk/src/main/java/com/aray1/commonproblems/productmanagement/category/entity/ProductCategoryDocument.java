package com.aray1.commonproblems.productmanagement.category.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class ProductCategoryDocument {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private String parentId;

    @Transient
    private List<ProductCategoryDocument> subcategories = new ArrayList<>();

    public ProductCategoryDocument() {
    }

    public ProductCategoryDocument(String name, String description) {
        this(name, description, null);
    }

    public ProductCategoryDocument(String name, String description, String parentId) {
        this(UUID.randomUUID().toString() + "-" + name, name, description, parentId);
    }

    public ProductCategoryDocument(String id, String name, String description, String parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentId = parentId;
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

    public String getParentId() {
        return parentId;
    }

    public void addSubCategories(List<ProductCategoryDocument> subcategories) {
        this.subcategories.addAll(subcategories);
    }

    public List<ProductCategoryDocument> getSubcategories() {
        return Collections.unmodifiableList(subcategories);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
