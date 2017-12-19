package com.aray1.commonproblems.productmanagement.product.entity;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.mongodb.core.mapping.Field;

public class VideoProductAttributeDocument extends ProductAttributeDocument {

    @Field
    private final String title;

    @Field
    private final List<String> actors;

    @Field
    private final List<String> directors;

    public VideoProductAttributeDocument(String type, String title, List<String> actors, List<String> directors) {
        super(type);
        this.title = title;
        this.actors = actors;
        this.directors = directors;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getActors() {
        return Collections.unmodifiableList(actors);
    }

    public List<String> getDirectors() {
        return Collections.unmodifiableList(directors);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
