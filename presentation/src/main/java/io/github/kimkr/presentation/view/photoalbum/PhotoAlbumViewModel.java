package io.github.kimkr.presentation.view.photoalbum;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import io.github.kimkr.presentation.BR;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumViewModel extends BaseObservable implements LifecycleObserver {

    private ViewMode viewMode = ViewMode.LIST;
    public final ObservableField<String> text = new ObservableField<>("init");
    public final ObservableList<PhotoAlbumItemViewModel> items = new ObservableArrayList<>();

    @Bindable
    public ViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
        notifyPropertyChanged(BR.viewMode);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        text.set("started");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        text.set("stopped");
    }
}
