package io.github.kimkr.data.datasource;

import android.content.Context;
import android.net.Uri;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kkr on 2017. 10. 29..
 */

@Module
public class DataStoreModule {

    @Provides
    @Singleton
    @Named("app_content_uri")
    Uri provideContentUri(Context context) {
        return Uri.parse("content://media/external/file");
    }
}
