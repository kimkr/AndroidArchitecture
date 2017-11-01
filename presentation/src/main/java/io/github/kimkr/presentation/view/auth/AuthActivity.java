package io.github.kimkr.presentation.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.github.kimkr.presentation.R;
import io.github.kimkr.presentation.databinding.AuthBinding;
import io.github.kimkr.presentation.view.BaseActivity;
import io.github.kimkr.presentation.view.auth.changepwd.AuthChangePwdViewModel;
import io.github.kimkr.presentation.view.auth.findpwd.AuthFindPwdViewModel;
import io.github.kimkr.presentation.view.auth.policy.AuthPolicyViewModel;
import io.github.kimkr.presentation.view.auth.signin.AuthSignInViewModel;
import io.github.kimkr.presentation.view.auth.signup.AuthSignUpViewModel;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 31..
 */

public class AuthActivity extends BaseActivity<AuthBinding> implements
        GoogleApiClient.OnConnectionFailedListener, FacebookCallback<LoginResult> {

    public static final int RC_GOOGLE_SIGN_IN = 7;

    @Inject
    AuthViewBinding authViewBinding;
    @Inject
    AuthViewModel viewModel;
    @Inject
    AuthSignInViewModel signInViewModel;
    @Inject
    AuthSignUpViewModel signUpViewModel;
    @Inject
    AuthFindPwdViewModel findPwdViewModel;
    @Inject
    AuthChangePwdViewModel changePwdViewModel;
    @Inject
    AuthPolicyViewModel policyViewModel;
    @Inject
    GoogleSignInApi googleSignInApi;
    @Inject
    CallbackManager callbackManager;

    /**
     * GoogleApiClient.OnConnectionFailedListener
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.d("onConnectionFailed " + connectionResult);
    }

    /**
     * FacebookCallback<LoginResult>
     */
    @Override
    public void onSuccess(LoginResult loginResult) {
        Timber.d("onSuccess loginResult " + loginResult);
        AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
        signInViewModel.signIn(credential);
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }

    /**
     * BaseActivity
     */
    @Override
    public void onBackPressed() {
        viewModel.onClickBack();
    }


    @Override
    protected void injectDependency(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.auth;
    }

    @Override
    protected void bind(AuthBinding binding) {
        binding.setViewModel(viewModel);
        binding.setSignInViewModel(signInViewModel);
        binding.setSignUpViewModel(signUpViewModel);
//        binding.setFindPwdViewModel(findPwdViewModel);
//        binding.setChangePwdViewModel(changePwdViewModel);
//        binding.setPolicyViewModel(policyViewModel);
        binding.signIn.btnGoogleSignIn.setOnClickListener(v -> {
            signInViewModel.onClickGoogleSignIn();
        });
        binding.signIn.btnFacebookSignIn.registerCallback(callbackManager, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("AuthActivity", "onActivityResult requestCode : " + requestCode);
        // GOOGLE AUTH
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            Log.d("AuthActivity", "requestCode == RC_GOOGLE_SIGN_IN");
            GoogleSignInResult result = googleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                Timber.d("result.isSuccess()");
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                signInViewModel.signIn(GoogleAuthProvider.getCredential(account.getIdToken(), null));
            } else {
                Timber.d("!result.isSuccess()");
                // Google Sign In failed, update UI appropriately
            }
        } else {
            // FACEBOOK AUTH
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void showMessage(String message) {
        showToast(message);
    }
}
