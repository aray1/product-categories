package com.aray1.commonproblems.productmanagement.product.service.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.product.entity.VideoProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.model.VideoProductAttribute;

@Service
public class VideoProductDocumentToAttributeMapper
        implements Function<VideoProductAttributeDocument, VideoProductAttribute> {
    @Override
    public VideoProductAttribute apply(VideoProductAttributeDocument productAttribute) {
        VideoProductAttribute audioProductAttribute = new VideoProductAttribute();
        audioProductAttribute.setType(productAttribute.getType());
        audioProductAttribute.setTitle(productAttribute.getTitle());
        audioProductAttribute.setActors(productAttribute.getActors());
        audioProductAttribute.setDirectors(productAttribute.getDirectors());
        return audioProductAttribute;
    }
}
