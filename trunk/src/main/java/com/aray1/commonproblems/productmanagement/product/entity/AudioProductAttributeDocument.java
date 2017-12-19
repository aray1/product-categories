package com.aray1.commonproblems.productmanagement.product.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.mongodb.core.mapping.Field;

public class AudioProductAttributeDocument extends ProductAttributeDocument {

    @Field
    private final String title;

    @Field
    private final String artist;

    public AudioProductAttributeDocument(String type, String title, String artist) {
        super(type);
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
