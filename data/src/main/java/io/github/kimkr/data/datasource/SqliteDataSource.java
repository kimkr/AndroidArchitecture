package io.github.kimkr.data.datasource;

import org.greenrobot.greendao.rx.RxDao;

import java.util.List;

import rx.Observable;


/**
 * Created by kkr on 2017. 10. 21..
 */

public class SqliteDataSource<T, K> implements DataSource<T, K> {

    protected final RxDao<T, K> dao;

    public SqliteDataSource(RxDao<T, K> dao) {
        this.dao = dao;
    }

    @Override
    public Observable<T> create(T entity) {
        return dao.insert(entity);
    }

    @Override
    public Observable<T> update(T entity) {
        return dao.update(entity);
    }

    @Override
    public Observable<T> read(K id) {
        return dao.load(id);
    }

    @Override
    public Observable<List<T>> read() {
        return dao.loadAll();
    }

    @Override
    public Observable<Void> delete(T entity) {
        return dao.delete(entity);
    }
}
