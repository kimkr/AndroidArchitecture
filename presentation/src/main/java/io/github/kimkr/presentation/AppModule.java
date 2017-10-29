package io.github.kimkr.presentation;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application app) {
        return app.getApplicationContext();
    }
}