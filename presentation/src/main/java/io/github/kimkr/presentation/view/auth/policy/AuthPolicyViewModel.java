package io.github.kimkr.presentation.view.auth.policy;

import android.databinding.BaseObservable;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;

/**
 * Created by kkr on 2017. 10. 31..
 */

@ActivityScope
public class AuthPolicyViewModel extends BaseObservable{

    @Inject
    public AuthPolicyViewModel() {
    }
}
