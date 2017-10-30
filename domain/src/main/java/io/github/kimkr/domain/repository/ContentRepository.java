package io.github.kimkr.domain.repository;

import java.util.List;

import io.github.kimkr.domain.entity.Content;
import rx.Completable;
import rx.Single;

/**
 * Created by kkr on 2017. 10. 31..
 */

public interface ContentRepository {

    Single<List<Content>> getContents();

    Single<Content> getContent(long id);

    Single<Integer> getContentCount();

    Single<Content> save(Content content);

    Completable delete(long id);

    Completable delete(String path);
}
