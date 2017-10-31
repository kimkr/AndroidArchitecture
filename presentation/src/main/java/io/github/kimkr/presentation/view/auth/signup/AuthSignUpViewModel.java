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

    public final InputViewModel idViewModel;
    public final InputViewModel pwdViewModel;
    public final InputViewModel rePwdViewModel;
    private final InputValidator inputValidator;
    private final UserRepository userRepository;
    private final WeakReference<AuthActivity> authActivityWeakReference;
    private final String invalidIdMsg;
    private final String invalidPwdMsg;
    private final String invalidRePwdMsg;

    @Inject
    public AuthSignUpViewModel(InputValidator inputValidator,
                               UserRepository userRepository,
                               WeakReference<AuthActivity> authActivityWeakReference,
                               @Named("auth_sign_up_id_invalid") String invalidIdMsg,
                               @Named("auth_sign_up_pwd_invalid") String invalidPwdMsg,
                               @Named("auth_sign_up_repwd_invalid") String invalidRePwdMsg) {
        this.inputValidator = inputValidator;
        this.userRepository = userRepository;
        this.authActivityWeakReference = authActivityWeakReference;
        this.invalidIdMsg = invalidIdMsg;
        this.invalidPwdMsg = invalidPwdMsg;
        this.invalidRePwdMsg = invalidRePwdMsg;
        idViewModel = new InputViewModel(true, false);
        pwdViewModel = new InputViewModel(true, true);
        rePwdViewModel = new InputViewModel(true, true);
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
                }, e -> authActivityWeakReference.get().showToast(e.getMessage()));
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
