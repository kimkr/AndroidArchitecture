package io.github.kimkr.presentation;

import android.app.Application;

import javax.inject.Inject;

import io.github.kimkr.presentation.library.media.ImageViewBinding;
import timber.log.Timber;

public class App extends Application {

    @Inject
    Timber.Tree timberTree;
    @Inject
    ImageViewBinding imageViewBinding;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        component.inject(this);
        Timber.plant(timberTree);
    }
}
