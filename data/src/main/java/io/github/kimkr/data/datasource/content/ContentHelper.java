package io.github.kimkr.data.datasource.content;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import io.github.kimkr.domain.model.Content;
import rx.functions.Func1;

/**
 * Created by kkr on 2017. 10. 28..
 */

public class ContentHelper {

    public static final Uri URI_IMAGE_EXTERNAL = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static final Uri URI_VIDEO_EXTERNAL = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    public static final String ID = MediaStore.MediaColumns._ID;
    public static final String FILEPATH = MediaStore.MediaColumns.DATA;
    public static final String FILENAME = MediaStore.MediaColumns.DISPLAY_NAME;
    public static final String MIME = MediaStore.MediaColumns.MIME_TYPE;
    public static final String DATE_ADDED = MediaStore.MediaColumns.DATE_ADDED;
    public static final String DATE_MODIFIED = MediaStore.MediaColumns.DATE_MODIFIED;
    public static final String WIDTH = MediaStore.MediaColumns.WIDTH;
    public static final String HEIGHT = MediaStore.MediaColumns.HEIGHT;
    public static final String SIZE = MediaStore.MediaColumns.SIZE;
    public static final String DATE_TAKEN = MediaStore.Images.ImageColumns.DATE_TAKEN;
    public static final String LATITUDE = MediaStore.Images.ImageColumns.LATITUDE;
    public static final String LONGITUDE = MediaStore.Images.ImageColumns.LONGITUDE;
    public static final String ORIENTATION = MediaStore.Images.ImageColumns.ORIENTATION;
    public static final String LOCATION = MediaStore.Images.ImageColumns.DESCRIPTION;
    public static final String DURATION = MediaStore.Video.VideoColumns.DESCRIPTION;
    public static final String[] PROJECTION = {
            ID, FILEPATH, FILENAME, MIME, DATE_ADDED, DATE_MODIFIED, DATE_TAKEN,
            WIDTH, HEIGHT, SIZE, LATITUDE, LONGITUDE, LOCATION, ORIENTATION
    };
    public static final String ORDERBY =
            String.format("IFNULL(%s, IFNULL(%s, 0)) ASC", DATE_ADDED, DATE_TAKEN);

    public static Func1<Cursor, Content> mapper = new Func1<Cursor, Content>() {
        @Override
        public Content call(Cursor cursor) {
            Content content = new Content();
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
            content.setId(cursor.getLong(idxId));
            content.setPath(cursor.getString(idxFilepath));
            content.setName(cursor.getString(idxFileName));
            content.setMime(cursor.getString(idxMime));
            content.setTaken(cursor.getLong(idxDateTaken));
            content.setAdded(cursor.getLong(idxDateAdded));
            content.setModified(cursor.getLong(idxDateModified));
            content.setWidth(cursor.getLong(idxWidth));
            content.setHeight(cursor.getLong(idxHeight));
            content.setSize(cursor.getLong(idxSize));
            content.setLatitude(cursor.getFloat(idxLat));
            content.setLongitude(cursor.getFloat(idxLon));
            content.setLocation(cursor.getString(idxLoc));
            content.setOrientation(cursor.getInt(idxOrientation));
            return content;
        }
    };
}
