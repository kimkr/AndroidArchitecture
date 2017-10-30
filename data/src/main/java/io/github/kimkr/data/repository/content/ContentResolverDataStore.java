package io.github.kimkr.data.repository.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.github.kimkr.data.entity.ContentEntity;
import rx.Completable;
import rx.Single;
import rx.functions.Func1;

/**
 * Created by kkr on 20/01/2017.
 */

@Singleton
public class ContentResolverDataStore implements ContentDataStore {

    private final String ID = MediaStore.MediaColumns._ID;
    private final String FILEPATH = MediaStore.MediaColumns.DATA;
    private final String FILENAME = MediaStore.MediaColumns.DISPLAY_NAME;
    private final String MIME = MediaStore.MediaColumns.MIME_TYPE;
    private final String DATE_ADDED = MediaStore.MediaColumns.DATE_ADDED;
    private final String DATE_MODIFIED = MediaStore.MediaColumns.DATE_MODIFIED;
    private final String WIDTH = MediaStore.MediaColumns.WIDTH;
    private final String HEIGHT = MediaStore.MediaColumns.HEIGHT;
    private final String SIZE = MediaStore.MediaColumns.SIZE;
    private final String DATE_TAKEN = MediaStore.Images.ImageColumns.DATE_TAKEN;
    private final String LATITUDE = MediaStore.Images.ImageColumns.LATITUDE;
    private final String LONGITUDE = MediaStore.Images.ImageColumns.LONGITUDE;
    private final String ORIENTATION = MediaStore.Images.ImageColumns.ORIENTATION;
    private final String LOCATION = MediaStore.Images.ImageColumns.DESCRIPTION;
    private final String DURATION = MediaStore.Video.VideoColumns.DESCRIPTION;
    private final String[] PROJECTION = {
            ID, FILEPATH, FILENAME, MIME, DATE_ADDED, DATE_MODIFIED, DATE_TAKEN,
            WIDTH, HEIGHT, SIZE, LATITUDE, LONGITUDE, LOCATION, ORIENTATION
    };
    private final String ORDERBY =
            String.format("IFNULL(%s, IFNULL(%s, 0)) ASC", DATE_ADDED, DATE_TAKEN);
    private final Func1<Cursor, ContentEntity> mapper = cursor -> {
        ContentEntity contentEntity = new ContentEntity();
        int idxId = cursor.getColumnIndex(ID);
        int idxFilepath = cursor.getColumnIndex(FILEPATH);
        int idxFileName = cursor.getColumnIndex(FILENAME);
        int idxMime = cursor.getColumnIndex(MIME);
        int idxDateAdded = cursor.getColumnIndex(DATE_ADDED);
        int idxDateModified = cursor.getColumnIndex(DATE_MODIFIED);
        int idxDateTaken = cursor.getColumnIndex(DATE_TAKEN);
        int idxWidth = cursor.getColumnIndex(WIDTH);
        int idxHeight = cursor.getColumnIndex(HEIGHT);
        int idxSize = cursor.getColumnIndex(SIZE);
        int idxLat = cursor.getColumnIndex(LATITUDE);
        int idxLon = cursor.getColumnIndex(LONGITUDE);
        int idxLoc = cursor.getColumnIndex(LOCATION);
        int idxOrientation = cursor.getColumnIndex(ORIENTATION);
        contentEntity.setId(cursor.getLong(idxId));
        contentEntity.setPath(cursor.getString(idxFilepath));
        contentEntity.setName(cursor.getString(idxFileName));
        contentEntity.setMime(cursor.getString(idxMime));
        contentEntity.setTaken(cursor.getLong(idxDateTaken));
        contentEntity.setAdded(cursor.getLong(idxDateAdded));
        contentEntity.setModified(cursor.getLong(idxDateModified));
        contentEntity.setWidth(cursor.getLong(idxWidth));
        contentEntity.setHeight(cursor.getLong(idxHeight));
        contentEntity.setSize(cursor.getLong(idxSize));
        contentEntity.setLatitude(cursor.getFloat(idxLat));
        contentEntity.setLongitude(cursor.getFloat(idxLon));
        contentEntity.setLocation(cursor.getString(idxLoc));
        contentEntity.setOrientation(cursor.getInt(idxOrientation));
        return contentEntity;
    };
    private final Context context;
    private final Uri dataStoreUri;

    @Inject
    public ContentResolverDataStore(Context context,
                                    @Named("app_content_uri") Uri dataStoreUri) {
        this.context = context;
        this.dataStoreUri = dataStoreUri;
    }

    @Override
    public Single<List<ContentEntity>> getContents() {
        return Single.fromCallable(() -> {
            Cursor cursor = context.getContentResolver()
                    .query(dataStoreUri,
                            PROJECTION,
                            null,
                            null,
                            null);
            List<ContentEntity> contentEntities = new ArrayList<>();
            while (cursor.moveToNext()) {
                contentEntities.add(mapper.call(cursor));
            }
            return contentEntities;
        });
    }

    @Override
    public Single<ContentEntity> getContent(long id) {
        return Single.fromCallable(() -> {
            Cursor cursor = context.getContentResolver()
                    .query(dataStoreUri,
                            PROJECTION,
                            null,
                            null,
                            null);
            if (cursor.moveToNext()) {
                return mapper.call(cursor);
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
    public Single<ContentEntity> save(ContentEntity contentEntity) {
        return Single.just(null);
    }

    @Override
    public Completable delete(long id) {
        return Completable.fromAction(() -> {
            context.getContentResolver().delete(
                    dataStoreUri,
                    ID + "=?",
                    new String[]{String.valueOf(id)});
        });
    }

    @Override
    public Completable delete(String path) {
        return Completable.fromAction(() -> {
            context.getContentResolver().delete(
                    dataStoreUri,
                    FILEPATH + "=?",
                    new String[]{path});
        });
    }
}
