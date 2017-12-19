package com.aray1.commonproblems.productmanagement.product.service.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.product.entity.AudioProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.model.AudioProductAttribute;

@Service
public class AudioProductDocumentToAttributeMapper
        implements Function<AudioProductAttributeDocument, AudioProductAttribute> {
    @Override
    public AudioProductAttribute apply(AudioProductAttributeDocument productAttribute) {
        AudioProductAttribute audioProductAttribute = new AudioProductAttribute();
        audioProductAttribute.setType(productAttribute.getType());
        audioProductAttribute.setArtist(productAttribute.getArtist());
        audioProductAttribute.setTitle(productAttribute.getTitle());
        return audioProductAttribute;
    }
}
