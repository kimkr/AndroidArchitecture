package io.github.kimkr.presentation.view.photoalbum;

import android.Manifest;

import com.tbruyelle.rxpermissions.RxPermissions;

import io.github.kimkr.data.datasource.content.ContentHelper;
import io.github.kimkr.data.datasource.content.LocalContentDataStore;
import io.github.kimkr.presentation.BR;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.databinding.PhotoAlbumBinding;
import io.github.kimkr.presentation.view.BaseBindingActivity;
import io.github.kimkr.presentation.view.photoalbum.grid.PhotoAlbumGridViewModel;
import io.github.kimkr.presentation.view.photoalbum.list.PhotoAlbumListViewModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumActivity extends BaseBindingActivity<PhotoAlbumBinding> {

    private PhotoAlbumViewModel viewModel;
    private PhotoAlbumListViewModel listViewModel;
    private PhotoAlbumGridViewModel gridViewModel;
    private LocalContentDataStore localContentDataStore;

    @Override
    protected int getLayout() {
        return R.layout.photo_album;
    }

    @Override
    protected void injectDependency() {
        localContentDataStore = new LocalContentDataStore(this, ContentHelper.URI_IMAGE_EXTERNAL);
        viewModel = new PhotoAlbumViewModel();
        listViewModel = new PhotoAlbumListViewModel(viewModel);
        gridViewModel = new PhotoAlbumGridViewModel(viewModel);
    }

    @Override
    protected void bind(PhotoAlbumBinding binding) {
        binding.setVariable(BR.viewModel, viewModel);
        binding.setVariable(BR.listViewModel, listViewModel);
        binding.setVariable(BR.gridViewModel, gridViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .filter(granted -> granted)
                .flatMap(granted -> localContentDataStore.getContents()
                        .toObservable()
                        .flatMap(Observable::from)
                        .map(PhotoAlbumItemViewModel::new)
                        .toList()
                        .subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> viewModel.items.addAll(items),
                        Timber::e);
    }
}
