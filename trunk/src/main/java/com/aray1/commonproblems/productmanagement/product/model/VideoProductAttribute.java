package com.aray1.commonproblems.productmanagement.product.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoProductAttribute extends ProductAttribute {

    @NotNull
    @JsonProperty
    private String title;

    @NotEmpty
    @JsonProperty
    private List<String> actors = new ArrayList<>();

    @NotEmpty
    @JsonProperty
    private List<String> directors = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

}
