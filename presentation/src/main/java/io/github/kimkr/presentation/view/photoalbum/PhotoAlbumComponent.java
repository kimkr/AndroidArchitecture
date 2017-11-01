package io.github.kimkr.presentation.view.photoalbum;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.github.kimkr.data.injection.ActivityScope;

/**
 * Created by kkr on 2017. 10. 30..
 */

@ActivityScope
@Subcomponent(modules = {
        PhotoAlbumModule.class,
        PhotoAlbumViewerProvider.class})
public interface PhotoAlbumComponent extends AndroidInjector<PhotoAlbumActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PhotoAlbumActivity> {
    }
}
