package com.aray1.commonproblems.productmanagement.product.service.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.aray1.commonproblems.productmanagement.product.entity.VideoProductAttributeDocument;
import com.aray1.commonproblems.productmanagement.product.model.VideoProductAttribute;

@Service
public class VideoProductAttributeToDocumentMapper
        implements Function<VideoProductAttribute, VideoProductAttributeDocument> {
    @Override
    public VideoProductAttributeDocument apply(VideoProductAttribute videoProductAttribute) {
        return new VideoProductAttributeDocument(videoProductAttribute.getType(), videoProductAttribute.getTitle(),
                videoProductAttribute.getActors(), videoProductAttribute.getDirectors());
    }
}
