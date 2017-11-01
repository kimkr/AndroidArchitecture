package io.github.kimkr.presentation.view.photoalbum.viewer;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import javax.inject.Inject;
import javax.inject.Named;

import io.github.kimkr.data.injection.FragmentScope;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumItemViewModel;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumViewModel;

/**
 * Created by kkr on 2017. 10. 29..
 */

@FragmentScope
public class PhotoAlbumViewerViewModel extends BaseObservable implements DefaultLifecycleObserver {

    @Inject
    WeakReference<PhotoAlbumViewerView> viewerViewWeakReference;
    @Inject
    PhotoAlbumViewModel viewModel;
    @Inject
    @Named("viewer_start_content")
    Long startContent;
    PhotoAlbumViewerAdapter adapter;

    @Inject
    public PhotoAlbumViewerViewModel() {
    }

    @Inject
    public void setAdapter(PhotoAlbumViewerAdapter adapter) {
        this.adapter = adapter;
        adapter.setViewModel(this);
    }

    @Bindable
    public ObservableList<PhotoAlbumItemViewModel> getItems() {
        return viewModel.items;
    }

    @Bindable
    public PhotoAlbumViewerAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        if (startContent != null) {
            viewerViewWeakReference.get().showToast(String.valueOf(startContent));
        }
    }
}
