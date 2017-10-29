package io.github.kimkr.presentation.view.photoalbum.grid;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableList;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumActivity;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumItemViewModel;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumViewModel;

/**
 * Created by kkr on 2017. 10. 27..
 */

@ActivityScope
public class PhotoAlbumGridViewModel extends BaseObservable {

    @Inject
    PhotoAlbumViewModel viewModel;
    @Inject
    WeakReference<PhotoAlbumActivity> activityWeakReference;
    PhotoAlbumGridAdapter adapter;

    @Inject
    public PhotoAlbumGridViewModel() {
    }

    @Inject
    public void setAdapter(PhotoAlbumGridAdapter adapter) {
        this.adapter = adapter;
        adapter.setViewModel(this);
    }

    @Bindable
    public ObservableList<PhotoAlbumItemViewModel> getItems() {
        return viewModel.items;
    }

    @Bindable
    public PhotoAlbumGridAdapter getAdapter() {
        return adapter;
    }

    public void onClickContent(Long id) {
        activityWeakReference.get().showViewer(id);
    }
}
