package io.github.kimkr.presentation.view.photoalbum;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import io.github.kimkr.presentation.BR;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.databinding.PhotoAlbumBinding;
import io.github.kimkr.presentation.view.BaseActivity;
import io.github.kimkr.presentation.view.Constants;
import io.github.kimkr.presentation.view.photoalbum.grid.PhotoAlbumGridViewModel;
import io.github.kimkr.presentation.view.photoalbum.list.PhotoAlbumListViewModel;
import io.github.kimkr.presentation.view.photoalbum.viewer.PhotoAlbumViewerView;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumActivity extends BaseActivity<PhotoAlbumBinding> implements
        HasFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    PhotoAlbumViewModel viewModel;
    @Inject
    PhotoAlbumListViewModel listViewModel;
    @Inject
    PhotoAlbumGridViewModel gridViewModel;
    String id;

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void injectDependency(@Nullable Bundle savedInstanceState) {
        id = getIntent().getStringExtra(Constants.EXTRA_ID);
        AndroidInjection.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.photo_album;
    }

    @Override
    protected void bind(PhotoAlbumBinding binding) {
        binding.setVariable(BR.viewModel, viewModel);
        binding.setVariable(BR.listViewModel, listViewModel);
        binding.setVariable(BR.gridViewModel, gridViewModel);
        getLifecycle().addObserver(viewModel);
    }

    public void showViewer(Long startContent) {
        Timber.d("showViewer startContent : " + startContent);
        PhotoAlbumViewerView viewerView = new PhotoAlbumViewerView();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.BUNDLE_CONTENT_ID, startContent);
        viewerView.setArguments(bundle);
        viewerView.show(getFragmentManager(), PhotoAlbumViewerView.class.getSimpleName());
    }
}
