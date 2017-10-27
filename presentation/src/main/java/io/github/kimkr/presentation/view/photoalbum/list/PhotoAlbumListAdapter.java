package io.github.kimkr.presentation.view.photoalbum.list;

import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumItemViewModel;
import io.github.kimkr.presentation.viewcomponent.recyclerview.BaseDataBindingAdapter;

/**
 * Created by kkr on 2017. 10. 27..
 */

public class PhotoAlbumListAdapter extends BaseDataBindingAdapter<PhotoAlbumItemViewModel,
        PhotoAlbumListViewModel> {

    @Override
    public int getItemLayoutId(int position) {
        return R.layout.photoalbum_list_content_item;
    }
}
