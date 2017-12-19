package com.aray1.commonproblems.productmanagement.product.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AudioProductAttribute extends ProductAttribute {

    @NotNull
    @JsonProperty
    private String title;

    @NotNull
    @JsonProperty
    private String artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
