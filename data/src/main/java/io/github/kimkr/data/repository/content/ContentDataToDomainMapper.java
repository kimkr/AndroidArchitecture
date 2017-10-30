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
public class ContentDataToDomainMapper {

    @Inject
    public ContentDataToDomainMapper() {
    }

    /**
     * ContentEntity -> Content
     */
    public Content transform(ContentEntity contentEntity) {
        Content content = null;
        if (contentEntity != null) {
            content = new Content();
            content.setId(contentEntity.getId());
            content.setPath(contentEntity.getPath());
            content.setName(contentEntity.getName());
            content.setMime(contentEntity.getMime());
            content.setLocation(contentEntity.getLocation());
            content.setLatitude(contentEntity.getLatitude());
            content.setLongitude(contentEntity.getLongitude());
            content.setWidth(contentEntity.getWidth());
            content.setHeight(contentEntity.getHeight());
            content.setTimeStamp(contentEntity.getTimeStamp());
            content.setAdded(contentEntity.getAdded());
            content.setTaken(contentEntity.getTaken());
            content.setModified(contentEntity.getModified());
            content.setSize(contentEntity.getSize());
            content.setOrientation(contentEntity.getOrientation());
        }
        return content;
    }

    public List<Content> transform(Collection<ContentEntity> contentEntityCollection) {
        final List<Content> contentList = new ArrayList<>(20);
        for (ContentEntity contentEntity : contentEntityCollection) {
            final Content content = transform(contentEntity);
            if (content != null) {
                contentList.add(content);
            }
        }
        return contentList;
    }
}
