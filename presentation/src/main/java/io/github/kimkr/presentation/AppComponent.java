package io.github.kimkr.presentation;

/**
 * Created by kkr on 2017. 10. 28..
 */

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.kimkr.data.repository.content.ContentRepositoryModule;
import io.github.kimkr.data.repository.DaoModule;
import io.github.kimkr.data.repository.user.UserRepositoryModule;
import io.github.kimkr.presentation.library.logger.LoggerModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        LoggerModule.class,
        DaoModule.class,
        UserRepositoryModule.class,
        ContentRepositoryModule.class,
        ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
