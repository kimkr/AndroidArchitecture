package io.github.kimkr.presentation.view.photoalbum.list;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableList;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumItemViewModel;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumViewModel;

/**
 * Created by kkr on 2017. 10. 27..
 */

@ActivityScope
public class PhotoAlbumListViewModel extends BaseObservable {

    private final PhotoAlbumViewModel viewModel;
    private final PhotoAlbumListAdapter adapter;

    @Inject
    public PhotoAlbumListViewModel(PhotoAlbumViewModel viewModel) {
        this.viewModel = viewModel;
        this.adapter = new PhotoAlbumListAdapter();
    }

    @Bindable
    public ObservableList<PhotoAlbumItemViewModel> getItems() {
        return viewModel.items;
    }

    @Bindable
    public PhotoAlbumListAdapter getAdapter() {
        return adapter;
    }
}
