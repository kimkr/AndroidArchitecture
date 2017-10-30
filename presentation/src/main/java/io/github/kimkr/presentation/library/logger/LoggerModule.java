package io.github.kimkr.presentation.library.logger;

import android.content.Context;

import com.facebook.stetho.Stetho;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Created by kkr on 08/02/2017.
 */

@Module
public class LoggerModule {

    @Provides
    @Singleton
    Timber.Tree provideTimberDebugTree(Context context) {
        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
                        .build());
        return new Timber.DebugTree();
    }
}
