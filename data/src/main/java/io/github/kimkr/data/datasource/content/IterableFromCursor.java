package io.github.kimkr.data.datasource.content;

import android.database.Cursor;

import java.util.Iterator;

import rx.functions.Func1;

/**
 * Created by kkr on 25/01/2017.
 */

public class IterableFromCursor<T> implements Iterable<T> {

    private Cursor cursor;
    private Func1<Cursor, T> objectMapper;

    public IterableFromCursor(Cursor cursor, Func1<Cursor, T> objectMapper) {
        this.cursor = cursor;
        this.objectMapper = objectMapper;
        this.cursor.moveToPosition(-1);
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !cursor.isClosed() && cursor.moveToNext();
            }

            @Override
            public T next() {
                return objectMapper.call(cursor);
            }

            @Override
            public void remove() {
            }
        };
    }
}