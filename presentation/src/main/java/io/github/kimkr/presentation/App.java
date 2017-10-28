package io.github.kimkr.presentation;

import android.app.Application;

import javax.inject.Inject;

import timber.log.Timber;

public class App extends Application {

    @Inject
    Timber.Tree timberTree;

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent component = DaggerAppComponent.builder()
                .build();
        component.inject(this);
        Timber.plant(timberTree);
    }
}
