package io.github.kimkr.presentation.view.photoalbum;

import android.Manifest;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions.RxPermissions;

import io.github.kimkr.data.datasource.content.ContentHelper;
import io.github.kimkr.data.datasource.content.LocalContentDataStore;
import io.github.kimkr.presentation.BR;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.view.photoalbum.list.PhotoAlbumListViewModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumActivity extends AppCompatActivity implements LifecycleOwner {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private PhotoAlbumViewModel viewModel;
    private PhotoAlbumListViewModel listViewModel;
    private LocalContentDataStore localContentDataStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localContentDataStore = new LocalContentDataStore(this, ContentHelper.URI_IMAGE_EXTERNAL);
        viewModel = new PhotoAlbumViewModel();
        listViewModel = new PhotoAlbumListViewModel(viewModel);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.photoalbum);
        binding.setVariable(BR.viewModel, viewModel);
        binding.setVariable(BR.listViewModel, listViewModel);
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
                .subscribe(items -> {
                    viewModel.items.addAll(items);
                }, Timber::e);
    }

    @Override
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }
}
