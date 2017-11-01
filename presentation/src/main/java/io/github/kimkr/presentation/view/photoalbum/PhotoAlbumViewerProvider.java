package io.github.kimkr.presentation.view.photoalbum;

import android.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.FragmentKey;
import dagger.multibindings.IntoMap;
import io.github.kimkr.presentation.view.photoalbum.viewer.PhotoAlbumViewerComponent;
import io.github.kimkr.presentation.view.photoalbum.viewer.PhotoAlbumViewerView;

/**
 * Created by kkr on 2017. 10. 30..
 */

@Module
public abstract class PhotoAlbumViewerProvider {

    @Binds
    @IntoMap
    @FragmentKey(PhotoAlbumViewerView.class)
    abstract AndroidInjector.Factory<? extends Fragment> provideDetailFragmentFactory(
            PhotoAlbumViewerComponent.Builder builder);
}
