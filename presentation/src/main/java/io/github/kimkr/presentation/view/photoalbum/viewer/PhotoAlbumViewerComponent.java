package io.github.kimkr.presentation.view.photoalbum.viewer;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.github.kimkr.data.injection.FragmentScope;

/**
 * Created by kkr on 2017. 10. 30..
 */

@FragmentScope
@Subcomponent(modules = PhotoAlbumViewerModule.class)
public interface PhotoAlbumViewerComponent extends AndroidInjector<PhotoAlbumViewerView> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PhotoAlbumViewerView> {
    }
}