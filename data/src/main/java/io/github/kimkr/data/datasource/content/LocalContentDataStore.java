package io.github.kimkr.data.datasource.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import io.github.kimkr.domain.model.Content;
import io.github.kimkr.domain.repository.ContentDataStore;
import rx.Completable;
import rx.Single;
import timber.log.Timber;

/**
 * Created by kkr on 20/01/2017.
 */

public class LocalContentDataStore implements ContentDataStore<Content> {

    private final Context context;
    private final Uri dataStoreUri;

    public LocalContentDataStore(Context context,
                                 Uri dataStoreUri) {
        this.context = context;
        this.dataStoreUri = dataStoreUri;
        Timber.tag(LocalContentDataStore.class.getSimpleName());
    }

    @Override
    public Single<List<Content>> getContents() {
        return Single.fromCallable(() -> {
            Cursor cursor = context.getContentResolver()
                    .query(dataStoreUri,
                            ContentHelper.PROJECTION,
                            null,
                            null,
                            null);
            List<Content> contents = new ArrayList<>();
            while (cursor.moveToNext()) {
                contents.add(ContentHelper.mapper.call(cursor));
            }
            return contents;
        });
    }

    @Override
    public Single<Content> getContent(long id) {
        return Single.fromCallable(() -> {
            Cursor cursor = context.getContentResolver()
                    .query(dataStoreUri,
                            ContentHelper.PROJECTION,
                            null,
                            null,
                            null);
            if (cursor.moveToNext()) {
                return ContentHelper.mapper.call(cursor);
            } else {
                throw new Exception();
            }
        });
    }

    @Override
    public Single<Integer> getContentCount() {
        return Single.fromCallable(() -> {
            Cursor cursor = context.getContentResolver()
                    .query(dataStoreUri,
                            null,
                            null,
                            null,
                            null);
            return cursor.getCount();
        });
    }

    @Override
    public Completable delete(long id) {
        return Completable.fromAction(() -> {
            context.getContentResolver().delete(
                    dataStoreUri,
                    ContentHelper.ID + "=?",
                    new String[]{String.valueOf(id)});
        });
    }

    @Override
    public Completable delete(String path) {
        return Completable.fromAction(() -> {
            context.getContentResolver().delete(
                    dataStoreUri,
                    ContentHelper.FILEPATH + "=?",
                    new String[]{path});
        });
    }
}
