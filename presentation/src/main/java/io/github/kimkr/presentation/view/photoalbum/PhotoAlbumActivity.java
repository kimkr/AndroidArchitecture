package io.github.kimkr.presentation.view.photoalbum;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.github.kimkr.presentation.BR;
import io.github.kimkr.presentation.R;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class PhotoAlbumActivity extends AppCompatActivity implements LifecycleOwner {

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private PhotoAlbumViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new PhotoAlbumViewModel();
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.photoalbum);
        binding.setVariable(BR.viewModel, viewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        getLifecycle().addObserver(viewModel);
    }

    @Override
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }
}
