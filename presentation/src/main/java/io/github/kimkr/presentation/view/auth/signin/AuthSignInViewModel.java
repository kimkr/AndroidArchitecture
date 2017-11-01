package io.github.kimkr.presentation.view.auth.signin;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;

import java.lang.ref.WeakReference;

import javax.inject.Inject;
import javax.inject.Named;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.domain.repository.UserRepository;
import io.github.kimkr.presentation.view.auth.AuthActivity;
import io.github.kimkr.presentation.view.navigation.Navigator;
import io.github.kimkr.presentation.viewcomponent.input.InputValidator;
import io.github.kimkr.presentation.viewcomponent.input.InputViewModel;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 31..
 */

@ActivityScope
public class AuthSignInViewModel extends BaseObservable {

    @Inject
    @Named("auth_id_view_model")
    public InputViewModel idViewModel;
    @Inject
    @Named("auth_pwd_view_model")
    public InputViewModel pwdViewModel;
    @Inject
    WeakReference<AuthActivity> authActivityWeakReference;
    @Inject
    Navigator navigator;
    @Inject
    InputValidator inputValidator;
    @Inject
    UserRepository userRepository;
    @Inject
    GoogleApiClient googleApiClient;
    @Inject
    @Named("auth_sign_up_id_invalid")
    String invalidIdMsg;
    @Inject
    @Named("auth_sign_up_pwd_invalid")
    String invalidPwdMsg;

    @Inject
    public AuthSignInViewModel() {
    }

    @Bindable
    public Function<String, Boolean> getIdChecker() {
        return (input) -> inputValidator.isValidEmail(input);
    }

    @Bindable
    public Function<String, Boolean> getPwdChecker() {
        return (input) -> inputValidator.isValidPwd(input);
    }

    public void onClickSignIn() {
        String email = idViewModel.getInput();
        String password = pwdViewModel.getInput();
        signIn(email, password);
    }

    public void onClickGoogleSignIn() {
        Timber.d("onClickGoogleSignIn");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        authActivityWeakReference.get().startActivityForResult(signInIntent, AuthActivity.RC_GOOGLE_SIGN_IN);
    }

    public void signIn(String email, String password) {
        if (email == null || !inputValidator.isValidEmail(email)) {
            authActivityWeakReference.get().showToast(invalidIdMsg);
            return;
        }
        if (password == null || !inputValidator.isValidPwd(password)) {
            authActivityWeakReference.get().showToast(invalidPwdMsg);
            return;
        }
        userRepository.signIn(email.trim(), password.trim())
                .subscribe(user -> {
                    Timber.d("sign in success");
                    navigator.navigateToPhotoAlbum(authActivityWeakReference.get(), "test");
                }, e -> authActivityWeakReference.get().showToast(e.toString()));
    }

    public void signIn(AuthCredential authCredential) {
        userRepository.signInWithCredential(authCredential)
                .subscribe(user -> {
                    Timber.d("sign in success");
                    navigator.navigateToPhotoAlbum(authActivityWeakReference.get(), "test");
                }, e -> authActivityWeakReference.get().showToast(e.toString()));
    }
}
