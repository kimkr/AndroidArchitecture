package io.github.kimkr.domain.repository;

import java.util.List;

import rx.Completable;
import rx.Single;

/**
 * Created by kkr on 2017. 10. 28..
 */

public interface ContentDataStore<T> {

    Single<List<T>> getContents();

    Single<T> getContent(long id);

    Single<Integer> getContentCount();

    Completable delete(long id);

    Completable delete(String path);
}
