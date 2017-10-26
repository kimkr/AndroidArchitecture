package io.github.kimkr.data.datasource;


import java.util.List;

import rx.Observable;


/**
 * Created by kkr on 2017. 10. 21..
 */

public interface DataSource<T, K> {

    Observable<T> create(T entity);

    Observable<T> update(T entity);

    Observable<T> read(K id);

    Observable<List<T>> read();

    Observable<Void> delete(T entity);
}
