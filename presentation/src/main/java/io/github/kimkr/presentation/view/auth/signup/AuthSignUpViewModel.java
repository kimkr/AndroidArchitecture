package io.github.kimkr.presentation.view.auth.signup;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.lang.ref.WeakReference;

import javax.inject.Inject;
import javax.inject.Named;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.domain.repository.UserRepository;
import io.github.kimkr.presentation.view.auth.AuthActivity;
import io.github.kimkr.presentation.viewcomponent.input.InputValidator;
import io.github.kimkr.presentation.viewcomponent.input.InputViewModel;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by kkr on 2017. 10. 31..
 */

@ActivityScope
public class AuthSignUpViewModel extends BaseObservable {

    @Inject
    @Named("auth_id_view_model")
    public InputViewModel idViewModel;
    @Inject
    @Named("auth_pwd_view_model")
    public InputViewModel pwdViewModel;
    @Inject
    @Named("auth_repwd_view_model")
    public InputViewModel rePwdViewModel;
    @Inject
    InputValidator inputValidator;
    @Inject
    UserRepository userRepository;
    @Inject
    WeakReference<AuthActivity> authActivityWeakReference;
    @Inject
    @Named("auth_sign_up_id_invalid")
    String invalidIdMsg;
    @Inject
    @Named("auth_sign_up_pwd_invalid")
    String invalidPwdMsg;
    @Inject
    @Named("auth_sign_up_repwd_invalid")
    String invalidRePwdMsg;

    @Inject
    public AuthSignUpViewModel() {
    }

    @Bindable
    public Function<String, Boolean> getIdChecker() {
        return (input) -> inputValidator.isValidEmail(input);
    }

    @Bindable
    public Function<String, Boolean> getPwdChecker() {
        return (input) -> inputValidator.isValidPwd(input);
    }

    @Bindable
    public Function<String, Boolean> getRePwdChecker() {
        return (repwd) -> {
            String pwd = pwdViewModel.getInput();
            return isValidRePwd(pwd, repwd);
        };
    }

    public void onClickSignUp() {
        String id = idViewModel.getInput();
        String pwd = pwdViewModel.getInput();
        String repwd = rePwdViewModel.getInput();
        if (!inputValidator.isValidEmail(id)) {
            authActivityWeakReference.get().showToast(invalidIdMsg);
            return;
        }
        if (!inputValidator.isValidPwd(pwd)) {
            authActivityWeakReference.get().showToast(invalidPwdMsg);
            return;
        }
        if (!isValidRePwd(pwd, repwd)) {
            authActivityWeakReference.get().showToast(invalidRePwdMsg);
            return;
        }
        userRepository.signUp(id.trim(), pwd.trim())
                .subscribe(user -> {
                    Timber.d("sign up success");
                }, e -> authActivityWeakReference.get().showToast(e.toString()));
    }

    private boolean isValidRePwd(String pwd, String repwd) {
        if (pwd == null || pwd.isEmpty()) {
            return false;
        }
        if (repwd == null || repwd.isEmpty()) {
            return false;
        }
        return pwd.equals(repwd);
    }
}
