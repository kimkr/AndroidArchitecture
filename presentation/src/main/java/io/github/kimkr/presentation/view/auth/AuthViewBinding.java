package io.github.kimkr.presentation.view.auth;

import android.content.Context;
import android.databinding.BindingConversion;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;
import io.github.kimkr.presentation.R;

/**
 * Created by kkr on 2017. 10. 26..
 */

@ActivityScope
public class AuthViewBinding {

    private static Context context;

    @Inject
    public AuthViewBinding(Context context) {
        this.context = context;
    }

    @BindingConversion
    public static String convertViewModeToString(ViewMode viewMode) {
        if (viewMode == null) {
            return "";
        }
        switch (viewMode) {
            case SIGNIN:
                return context.getString(R.string.auth_sign_in_title);
            case SIGNUP:
                return context.getString(R.string.auth_sign_up_title);
            case FINDPWD:
                return context.getString(R.string.auth_find_pwd_title);
            case CHANGEPWD:
                return context.getString(R.string.auth_change_pwd_title);
            case POLICY:
                return context.getString(R.string.auth_policy_title);
            default:
                return "";
        }
    }
}
