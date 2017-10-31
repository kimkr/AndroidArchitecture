package io.github.kimkr.presentation.view.auth;

import android.content.Context;

import java.lang.ref.WeakReference;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.R;

/**
 * Created by kkr on 2017. 10. 31..
 */

@Module
public class AuthModule {

    @Provides
    @ActivityScope
    WeakReference<AuthActivity> provideAuthActivityWeakReference(AuthActivity authActivity) {
        return new WeakReference<>(authActivity);
    }

    @Provides
    @ActivityScope
    @Named("auth_sign_up_id_invalid")
    String provideErrorFieldId(Context context) {
        return context.getString(R.string.auth_all_id_invalid_message);
    }

    @Provides
    @ActivityScope
    @Named("auth_sign_up_pwd_invalid")
    String provideErrorFieldPwd(Context context) {
        return context.getString(R.string.auth_all_pwd_invalid_message);
    }

    @Provides
    @ActivityScope
    @Named("auth_sign_up_repwd_invalid")
    String provideErrorFieldRePwd(Context context) {
        return context.getString(R.string.auth_all_repwd_invalid_message);
    }
}
