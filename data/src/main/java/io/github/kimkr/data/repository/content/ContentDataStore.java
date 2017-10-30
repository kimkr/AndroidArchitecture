package io.github.kimkr.data.repository.content;

import java.util.List;

import io.github.kimkr.data.entity.ContentEntity;
import rx.Completable;
import rx.Single;

/**
 * Created by kkr on 2017. 10. 28..
 */

public interface ContentDataStore {

    Single<List<ContentEntity>> getContents();

    Single<ContentEntity> getContent(long id);

    Single<Integer> getContentCount();

    Single<ContentEntity> save(ContentEntity contentEntity);

    Completable delete(long id);

    Completable delete(String path);
}
