package io.github.kimkr.presentation.view.photoalbum;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import io.github.kimkr.domain.model.Content;

/**
 * Created by kkr on 2017. 10. 28..
 */

public class PhotoAlbumItemViewModel extends BaseObservable {

    private Content content;

    public PhotoAlbumItemViewModel(Content content) {
        this.content = content;
    }

    @Bindable
    public Long getId() {
        return content.getId();
    }

    @Bindable
    public String getPath() {
        return content.getPath();
    }
}
