package io.github.kimkr.data.repository.content;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.domain.entity.Content;
import io.github.kimkr.domain.repository.ContentRepository;
import rx.Completable;
import rx.Single;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Singleton
public class ContentDataRepository implements ContentRepository {

    private final ContentDataToDomainMapper contentDataToDomainMapper;
    private final ContentDomainToDataMapper contentDomainToDataMapper;
    private final ContentResolverDataStore contentResolverDataStore;
    private final ContentLocalDataStore contentLocalDataStore;

    @Inject
    public ContentDataRepository(ContentDataToDomainMapper contentDataToDomainMapper,
                                 ContentDomainToDataMapper contentDomainToDataMapper,
                                 ContentResolverDataStore contentResolverDataStore,
                                 ContentLocalDataStore contentLocalDataStore) {
        this.contentDataToDomainMapper = contentDataToDomainMapper;
        this.contentDomainToDataMapper = contentDomainToDataMapper;
        this.contentResolverDataStore = contentResolverDataStore;
        this.contentLocalDataStore = contentLocalDataStore;
    }

    @Override
    public Single<List<Content>> getContents() {
        return contentResolverDataStore.getContents()
                .map(contentDataToDomainMapper::transform);
    }

    @Override
    public Single<Content> getContent(long id) {
        return contentResolverDataStore.getContent(id)
                .map(contentDataToDomainMapper::transform);
    }

    @Override
    public Single<Integer> getContentCount() {
        return contentResolverDataStore.getContentCount();
    }

    @Override
    public Single<Content> save(Content content) {
        Timber.d("save : %s", content.toString());
        return contentLocalDataStore.save(contentDomainToDataMapper.transform(content))
                .map(contentDataToDomainMapper::transform);
    }

    @Override
    public Completable delete(long id) {
        return contentLocalDataStore.delete(id);
    }

    @Override
    public Completable delete(String path) {
        return contentLocalDataStore.delete(path);
    }
}
