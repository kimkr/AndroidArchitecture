package io.github.kimkr.presentation;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import io.github.kimkr.presentation.view.auth.AuthActivity;
import io.github.kimkr.presentation.view.auth.AuthComponent;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumActivity;
import io.github.kimkr.presentation.view.photoalbum.PhotoAlbumComponent;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(PhotoAlbumActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindPhotoAlbumActivity(
            PhotoAlbumComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(AuthActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindAuthActivity(
            AuthComponent.Builder builder);
}
