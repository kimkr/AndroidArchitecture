package io.github.kimkr.presentation.view.photoalbum.viewer;

import javax.inject.Inject;

import io.github.kimkr.data.injection.FragmentScope;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumItemViewModel;
import io.github.kimkr.presentation.viewcomponent.recyclerview.BaseDataBindingAdapter;

/**
 * Created by kkr on 2017. 10. 29..
 */

@FragmentScope
public class PhotoAlbumViewerAdapter extends BaseDataBindingAdapter<PhotoAlbumViewerViewModel,
        PhotoAlbumItemViewModel> {

    @Inject
    public PhotoAlbumViewerAdapter() {
    }

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.photo_album_grid_content_item;
    }
}
