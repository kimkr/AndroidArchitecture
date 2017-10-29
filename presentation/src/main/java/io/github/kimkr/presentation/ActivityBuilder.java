package io.github.kimkr.presentation;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumActivity;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumModule;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = PhotoAlbumModule.class)
    abstract PhotoAlbumActivity bindPhotoAlbumActivity();
}
