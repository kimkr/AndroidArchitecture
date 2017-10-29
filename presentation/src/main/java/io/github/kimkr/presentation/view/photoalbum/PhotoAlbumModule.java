package io.github.kimkr.presentation.view.photoalbum;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.data.injection.ActivityScope;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public class PhotoAlbumModule {

    @Provides
    @ActivityScope
    @Named("photo_album_id")
    String provideArgumentId(PhotoAlbumActivity activity) {
        return activity.id;
    }
}
