package io.github.kimkr.presentation.view.photoalbum;

import java.lang.ref.WeakReference;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.view.photoalbum.viewer.PhotoAlbumViewerComponent;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module(subcomponents = PhotoAlbumViewerComponent.class)
public class PhotoAlbumModule {

    @Provides
    @ActivityScope
    @Named("photo_album_id")
    String provideArgumentId(PhotoAlbumActivity activity) {
        return activity.id;
    }

    @Provides
    @ActivityScope
    WeakReference<PhotoAlbumActivity> provideActivityWeakReference(PhotoAlbumActivity activity) {
        return new WeakReference<>(activity);
    }
}
