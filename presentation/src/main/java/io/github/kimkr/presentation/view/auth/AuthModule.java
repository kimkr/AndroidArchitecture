package io.github.kimkr.presentation.view.auth;

import android.content.Context;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.ref.WeakReference;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.viewcomponent.input.InputViewModel;

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
    GoogleSignInOptions provideGoogleSignInOptions(Context context) {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.google_client_id))
                .requestEmail()
                .build();
    }

    @Provides
    @ActivityScope
    GoogleApiClient provideGoogleApiClient(AuthActivity authActivity, GoogleSignInOptions gso) {
        return new GoogleApiClient.Builder(authActivity)
                .enableAutoManage(authActivity, authActivity)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Provides
    @ActivityScope
    GoogleSignInApi provideGoogleSignInApi() {
        return Auth.GoogleSignInApi;
    }

    @Provides
    @ActivityScope
    CallbackManager provideCallbackManager() {
        return CallbackManager.Factory.create();
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

    @Provides
    @ActivityScope
    @Named("auth_id_view_model")
    InputViewModel provideIdInputViewModel() {
        return new InputViewModel(true, false);
    }

    @Provides
    @ActivityScope
    @Named("auth_pwd_view_model")
    InputViewModel providePwdInputViewModel() {
        return new InputViewModel(true, true);
    }

    @Provides
    @ActivityScope
    @Named("auth_repwd_view_model")
    InputViewModel provideRePwdInputViewModel() {
        return new InputViewModel(true, true);
    }
}
