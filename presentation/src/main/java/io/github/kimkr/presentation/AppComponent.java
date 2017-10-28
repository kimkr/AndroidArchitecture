package io.github.kimkr.presentation;

/**
 * Created by kkr on 2017. 10. 28..
 */

import javax.inject.Singleton;

import dagger.Component;
import io.github.kimkr.presentation.library.logger.LoggerModule;

@Singleton
@Component(modules = {ContextModule.class, LoggerModule.class})
public interface AppComponent {

    void inject(App app);
}
