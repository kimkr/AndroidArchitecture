package io.github.kimkr.data.repository.content;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.data.entity.ContentEntity;
import io.github.kimkr.domain.entity.Content;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Singleton
public class ContentDomainToDataMapper {

    @Inject
    public ContentDomainToDataMapper() {
    }

    /**
     * Content -> ContentEntity
     */
    public ContentEntity transform(Content content) {
        ContentEntity contentEntity = null;
        if (content != null) {
            contentEntity = new ContentEntity();
            contentEntity.setId(content.getId());
            contentEntity.setPath(content.getPath());
            contentEntity.setName(content.getName());
            contentEntity.setMime(content.getMime());
            contentEntity.setLocation(content.getLocation());
            contentEntity.setLatitude(content.getLatitude());
            contentEntity.setLongitude(content.getLongitude());
            contentEntity.setWidth(content.getWidth());
            contentEntity.setHeight(content.getHeight());
            contentEntity.setTimeStamp(content.getTimeStamp());
            contentEntity.setAdded(content.getAdded());
            contentEntity.setTaken(content.getTaken());
            contentEntity.setModified(content.getModified());
            contentEntity.setSize(content.getSize());
            contentEntity.setOrientation(content.getOrientation());
        }
        return contentEntity;
    }

    public List<ContentEntity> transform(Collection<Content> contentCollection) {
        final List<ContentEntity> contentEntityList = new ArrayList<>(20);
        for (Content content : contentCollection) {
            final ContentEntity contentEntity = transform(content);
            if (contentEntity != null) {
                contentEntityList.add(contentEntity);
            }
        }
        return contentEntityList;
    }
}
