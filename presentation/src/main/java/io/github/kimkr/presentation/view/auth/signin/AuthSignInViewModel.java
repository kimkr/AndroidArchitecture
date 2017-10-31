package io.github.kimkr.presentation.view.auth.signin;

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
public class AuthSignInViewModel extends BaseObservable {

    public final InputViewModel idViewModel;
    public final InputViewModel pwdViewModel;
    private final InputValidator inputValidator;
    private final UserRepository userRepository;
    private final WeakReference<AuthActivity> authActivityWeakReference;
    private final String invalidIdMsg;
    private final String invalidPwdMsg;

    @Inject
    public AuthSignInViewModel(InputValidator inputValidator,
                               UserRepository userRepository,
                               WeakReference<AuthActivity> authActivityWeakReference,
                               @Named("auth_sign_up_id_invalid") String invalidIdMsg,
                               @Named("auth_sign_up_pwd_invalid") String invalidPwdMsg) {
        this.inputValidator = inputValidator;
        this.userRepository = userRepository;
        this.authActivityWeakReference = authActivityWeakReference;
        this.invalidIdMsg = invalidIdMsg;
        this.invalidPwdMsg = invalidPwdMsg;
        idViewModel = new InputViewModel(true, false);
        pwdViewModel = new InputViewModel(true, true);
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
        String id = idViewModel.getInput();
        String pwd = pwdViewModel.getInput();
        if (!inputValidator.isValidEmail(id)) {
            authActivityWeakReference.get().showToast(invalidIdMsg);
            return;
        }
        if (!inputValidator.isValidPwd(pwd)) {
            authActivityWeakReference.get().showToast(invalidPwdMsg);
            return;
        }
        userRepository.signIn(id.trim(), pwd.trim())
                .subscribe(user -> {
                    Timber.d("sign in success");
                }, e -> authActivityWeakReference.get().showToast(e.getMessage()));
    }
}
