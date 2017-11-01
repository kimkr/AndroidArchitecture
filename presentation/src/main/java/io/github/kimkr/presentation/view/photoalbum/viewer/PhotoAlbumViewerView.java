package io.github.kimkr.presentation.view.photoalbum.viewer;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.databinding.PhotoAlbumViewerBinding;
import io.github.kimkr.presentation.view.BaseDialogFragment;

import static io.github.kimkr.presentation.view.Constants.BUNDLE_CONTENT_ID;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumViewerView extends BaseDialogFragment<PhotoAlbumViewerBinding> {

    @Inject
    PhotoAlbumViewerViewModel viewerViewModel;
    Long startContentId;

    @Override
    protected void injectDependency(Bundle bundle) {
        startContentId = bundle.getLong(BUNDLE_CONTENT_ID);
        AndroidInjection.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.photo_album_viewer;
    }

    @Override
    protected void bind(PhotoAlbumViewerBinding binding) {
        binding.setViewerViewModel(viewerViewModel);
        getLifecycle().addObserver(viewerViewModel);
    }
}
