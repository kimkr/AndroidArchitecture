package io.github.kimkr.data.repository.content;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.data.entity.ContentEntity;
import io.github.kimkr.data.entity.ContentEntityDao;
import io.github.kimkr.data.repository.RxDao;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Singleton
public class ContentLocalDataStore implements ContentDataStore {

    private final RxDao<ContentEntity, Long> contentRxDao;

    @Inject
    public ContentLocalDataStore(RxDao<ContentEntity, Long> contentRxDao) {
        this.contentRxDao = contentRxDao;
    }

    @Override
    public Single<List<ContentEntity>> getContents() {
        return contentRxDao.loadAll();
    }

    @Override
    public Single<ContentEntity> getContent(long id) {
        return contentRxDao.load(id);
    }

    @Override
    public Single<Integer> getContentCount() {
        return contentRxDao.count()
                .map(count -> count != null ? count.intValue() : null);
    }

    @Override
    public Single<ContentEntity> save(ContentEntity contentEntity) {
        return contentRxDao.insertOrReplace(contentEntity);
    }

    @Override
    public Completable delete(long id) {
        return contentRxDao.deleteByKey(id);
    }

    @Override
    public Completable delete(String path) {
        return contentRxDao.deleteInTx(
                contentRxDao.getDao()
                        .queryBuilder()
                        .where(ContentEntityDao.Properties.Path.eq(path))
                        .list());
    }
}
