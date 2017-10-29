package io.github.kimkr.presentation;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumComponent;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module(subcomponents = {PhotoAlbumComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application app) {
        return app.getApplicationContext();
    }
}