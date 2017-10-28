package io.github.kimkr.presentation.library.logger;

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
    Timber.Tree provideTimberDebugTree() {
        return new Timber.DebugTree();
    }
}
