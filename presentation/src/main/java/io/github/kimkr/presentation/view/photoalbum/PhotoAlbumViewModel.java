package io.github.kimkr.presentation.view.photoalbum;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumViewModel extends BaseObservable implements LifecycleObserver {

    public final ObservableField<String> text = new ObservableField<>("init");

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        text.set("started");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        text.set("stopped");
    }
}
