package io.github.kimkr.presentation.view.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;

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

/**
 * Created by kkr on 2017. 10. 31..
 */

public class AuthActivity extends BaseActivity<AuthBinding> {

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
    }

    @Override
    public void onBackPressed() {
        viewModel.onClickBack();
    }

    public void showMessage(String message) {
        showToast(message);
    }
}
