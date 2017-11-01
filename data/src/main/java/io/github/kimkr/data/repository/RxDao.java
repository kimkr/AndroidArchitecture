package io.github.kimkr.data.repository;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kkr on 2017. 11. 1..
 */

public class RxDao<T, K> {

    private final AbstractDao<T, K> dao;
    private final Scheduler scheduler;

    public RxDao(AbstractDao<T, K> dao) {
        this(dao, Schedulers.io());
    }

    public RxDao(AbstractDao<T, K> dao, Scheduler scheduler) {
        this.dao = dao;
        this.scheduler = scheduler;
    }

    public AbstractDao<T, K> getDao() {
        return dao;
    }

    public Single<List<T>> loadAll() {
        return wrap(Single.fromCallable(() -> dao.loadAll()));
    }

    public Single<T> load(final K key) {
        return wrap(Single.fromCallable(() -> dao.load(key)));
    }

    public Single<T> refresh(final T entity) {
        return wrap(Single.fromCallable(() -> {
            dao.refresh(entity);
            return entity;
        }));
    }

    public Single<T> insert(final T entity) {
        return wrap(Single.fromCallable(() -> {
            dao.insert(entity);
            return entity;
        }));
    }

    public Single<Iterable<T>> insertInTx(final Iterable<T> entities) {
        return wrap(Single.fromCallable(() -> {
            dao.insertInTx(entities);
            return entities;
        }));
    }

    public Single<Object[]> insertInTx(final T... entities) {
        return wrap(Single.fromCallable(() -> {
            dao.insertInTx(entities);
            return entities;
        }));
    }

    public Single<T> insertOrReplace(final T entity) {
        return wrap(Single.fromCallable(() -> {
            dao.insertOrReplace(entity);
            return entity;
        }));
    }

    public Single<Iterable<T>> insertOrReplaceInTx(final Iterable<T> entities) {
        return wrap(Single.fromCallable(() -> {
            dao.insertOrReplaceInTx(entities);
            return entities;
        }));
    }

    public Single<Object[]> insertOrReplaceInTx(final T... entities) {
        return wrap(Single.fromCallable(() -> {
            dao.insertOrReplaceInTx(entities);
            return entities;
        }));
    }

    public Single<T> save(final T entity) {
        return wrap(Single.fromCallable(() -> {
            dao.save(entity);
            return entity;
        }));
    }

    public Single<Iterable<T>> saveInTx(final Iterable<T> entities) {
        return wrap(Single.fromCallable(() -> {
            dao.saveInTx(entities);
            return entities;
        }));
    }

    public Single<Object[]> saveInTx(final T... entities) {
        return wrap(Single.fromCallable(() -> {
            dao.saveInTx(entities);
            return entities;
        }));
    }

    public Single<T> update(final T entity) {
        return wrap(Single.fromCallable(() -> {
            dao.update(entity);
            return entity;
        }));
    }

    public Single<Iterable<T>> updateInTx(final Iterable<T> entities) {
        return wrap(Single.fromCallable(() -> {
            dao.updateInTx(entities);
            return entities;
        }));
    }

    public Single<Object[]> updateInTx(final T... entities) {
        return wrap(Single.fromCallable(() -> {
            dao.updateInTx(entities);
            return entities;
        }));
    }

    public Completable delete(final T entity) {
        return wrap(Completable.fromAction(() -> {
            dao.delete(entity);
        }));
    }

    public Completable deleteByKey(final K key) {
        return wrap(Completable.fromAction(() -> {
            dao.deleteByKey(key);
        }));
    }

    public Completable deleteAll() {
        return wrap(Completable.fromAction(() -> {
            dao.deleteAll();
        }));
    }

    public Completable deleteInTx(final Iterable<T> entities) {
        return wrap(Completable.fromAction(() -> {
            dao.deleteInTx(entities);
        }));
    }

    public Completable deleteInTx(final T... entities) {
        return wrap(Completable.fromAction(() -> {
            dao.deleteInTx(entities);
        }));
    }

    public Completable deleteByKeyInTx(final Iterable<K> keys) {
        return wrap(Completable.fromAction(() -> {
            dao.deleteByKeyInTx(keys);
        }));
    }

    public Completable deleteByKeyInTx(final K... keys) {
        return wrap(Completable.fromAction(() -> {
            dao.deleteByKeyInTx(keys);
        }));
    }

    public Single<Long> count() {
        return wrap(Single.fromCallable(() -> dao.count()));
    }

    private <T> Flowable<T> wrap(Flowable<T> flowable) {
        if (scheduler != null) {
            return flowable.subscribeOn(scheduler);
        } else {
            return flowable;
        }
    }

    private <T> Single<T> wrap(Single<T> single) {
        if (scheduler != null) {
            return single.subscribeOn(scheduler);
        } else {
            return single;
        }
    }

    private Completable wrap(Completable completable) {
        if (scheduler != null) {
            return completable.subscribeOn(scheduler);
        } else {
            return completable;
        }
    }
}
