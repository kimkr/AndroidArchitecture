package io.github.kimkr.presentation.view.photoalbum;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.github.kimkr.domain.model.Content;
import io.github.kimkr.presentation.BR;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.view.photoalbum.list.PhotoAlbumListViewModel;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumActivity extends AppCompatActivity implements LifecycleOwner {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private PhotoAlbumViewModel viewModel;
    private PhotoAlbumListViewModel listViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new PhotoAlbumViewModel();
        listViewModel = new PhotoAlbumListViewModel(viewModel);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.photoalbum);
        binding.setVariable(BR.viewModel, viewModel);
        binding.setVariable(BR.listViewModel, listViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        getLifecycle().addObserver(viewModel);
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                    viewModel.items.add(new PhotoAlbumItemViewModel(mock(1, "path/kimkr/is/super/great")));
                    viewModel.items.add(new PhotoAlbumItemViewModel(mock(2, "path/kimkr/will/be/a/star")));
                });
    }

    @Override
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }


    private Content mock(long id, String path) {
        Content content = new Content();
        content.setId(id);
        content.setPath(path);
        return content;
    }
}
