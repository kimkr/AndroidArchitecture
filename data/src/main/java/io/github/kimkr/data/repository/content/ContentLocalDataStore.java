package io.github.kimkr.data.repository.content;

import org.greenrobot.greendao.rx.RxDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kimkr.data.entity.ContentEntity;
import io.github.kimkr.data.entity.ContentEntityDao;
import rx.Completable;
import rx.Single;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Singleton
public class ContentLocalDataStore implements ContentDataStore {

    private final ContentEntityDao contentEntityDao;
    private final RxDao<ContentEntity, Long> contentRxDao;

    @Inject
    public ContentLocalDataStore(ContentEntityDao contentEntityDao) {
        this.contentEntityDao = contentEntityDao;
        this.contentRxDao = contentEntityDao.rx();
    }

    @Override
    public Single<List<ContentEntity>> getContents() {
        return contentRxDao.loadAll().toSingle();
    }

    @Override
    public Single<ContentEntity> getContent(long id) {
        return contentRxDao.load(id).toSingle();
    }

    @Override
    public Single<Integer> getContentCount() {
        return contentRxDao.count()
                .map(count -> count != null ? count.intValue() : null)
                .toSingle();
    }

    @Override
    public Single<ContentEntity> save(ContentEntity contentEntity) {
        return contentRxDao.insertOrReplace(contentEntity).toSingle();
    }

    @Override
    public Completable delete(long id) {
        return contentRxDao.deleteByKey(id).toCompletable();
    }

    @Override
    public Completable delete(String path) {
        return contentRxDao.deleteInTx(
                contentRxDao.getDao()
                        .queryBuilder()
                        .where(ContentEntityDao.Properties.Path.eq(path))
                        .list())
                .toCompletable();
    }
}
