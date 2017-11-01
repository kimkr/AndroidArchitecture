package io.github.kimkr.presentation.view.photoalbum.viewer;

import java.lang.ref.WeakReference;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.data.injection.FragmentScope;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public class PhotoAlbumViewerModule {

    @Provides
    @FragmentScope
    @Named("viewer_start_content")
    Long provideStartContentId(PhotoAlbumViewerView view) {
        return view.startContentId;
    }

    @Provides
    @FragmentScope
    WeakReference<PhotoAlbumViewerView> providePhotoAlbumViewerViewWeakReference(PhotoAlbumViewerView view) {
        return new WeakReference<>(view);
    }
}
