package io.github.kimkr.presentation.view.auth;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.github.kimkr.data.injection.ActivityScope;

/**
 * Created by kkr on 2017. 10. 31..
 */


@ActivityScope
@Subcomponent(modules = {AuthModule.class})
public interface AuthComponent extends AndroidInjector<AuthActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<AuthActivity> {
    }
}
