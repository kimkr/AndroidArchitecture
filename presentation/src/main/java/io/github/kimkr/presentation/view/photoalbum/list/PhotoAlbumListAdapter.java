package io.github.kimkr.presentation.view.photoalbum.list;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumItemViewModel;
import io.github.kimkr.presentation.viewcomponent.recyclerview.BaseDataBindingAdapter;

/**
 * Created by kkr on 2017. 10. 27..
 */

@ActivityScope
public class PhotoAlbumListAdapter extends BaseDataBindingAdapter<PhotoAlbumItemViewModel,
        PhotoAlbumListViewModel> {

    @Inject
    public PhotoAlbumListAdapter() {
    }

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.photo_album_list_content_item;
    }
}
