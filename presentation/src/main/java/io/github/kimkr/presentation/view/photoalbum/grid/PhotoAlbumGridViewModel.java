package io.github.kimkr.presentation.view.photoalbum.grid;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableList;

import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumItemViewModel;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumViewModel;

/**
 * Created by kkr on 2017. 10. 27..
 */

public class PhotoAlbumGridViewModel extends BaseObservable {

    private final PhotoAlbumViewModel viewModel;
    private final PhotoAlbumGridAdapter adapter;

    public PhotoAlbumGridViewModel(PhotoAlbumViewModel viewModel) {
        this.viewModel = viewModel;
        this.adapter = new PhotoAlbumGridAdapter();
    }

    @Bindable
    public ObservableList<PhotoAlbumItemViewModel> getItems() {
        return viewModel.items;
    }

    @Bindable
    public PhotoAlbumGridAdapter getAdapter() {
        return adapter;
    }
}
