package com.aray1.commonproblems.productmanagement.product.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = AudioProductAttribute.class, name = "audio"),
        @JsonSubTypes.Type(value = VideoProductAttribute.class, name = "video")})
public abstract class ProductAttribute {

    @NotNull
    @JsonProperty
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
