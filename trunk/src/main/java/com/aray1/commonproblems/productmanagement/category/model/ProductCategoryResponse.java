package com.aray1.commonproblems.productmanagement.category.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(NON_EMPTY)
public class ProductCategoryResponse {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty("parent_id")
    private String parentId;

    @JsonProperty
    private List<ProductCategoryResponse> subCategories = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void addSubCategory(ProductCategoryResponse subCategoryResponse) {
        this.subCategories.add(subCategoryResponse);
    }

    public void addSubcategories(List<ProductCategoryResponse> subCategories) {
        if (subCategories != null) {
            this.subCategories.addAll(subCategories);
        }
    }

    public List<ProductCategoryResponse> getSubCategories() {
        return subCategories;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
