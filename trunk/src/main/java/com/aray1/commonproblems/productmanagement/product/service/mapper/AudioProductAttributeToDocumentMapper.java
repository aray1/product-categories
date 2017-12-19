package com.aray1.commonproblems.productmanagement.product.service.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.product.entity.AudioProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.model.AudioProductAttribute;

@Service
public class AudioProductAttributeToDocumentMapper
        implements Function<AudioProductAttribute, AudioProductAttributeDocument> {
    @Override
    public AudioProductAttributeDocument apply(AudioProductAttribute productAttribute) {
        return new AudioProductAttributeDocument(productAttribute.getType(), productAttribute.getTitle(),
                productAttribute.getArtist());
    }
}
