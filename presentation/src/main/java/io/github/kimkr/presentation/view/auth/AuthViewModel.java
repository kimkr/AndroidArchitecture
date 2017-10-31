package io.github.kimkr.presentation.view.auth;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.github.kimkr.data.injection.ActivityScope;

/**
 * Created by kkr on 2017. 10. 31..
 */

@ActivityScope
public class AuthViewModel extends BaseObservable {

    public final ObservableField<ViewMode> viewMode = new ObservableField<>(ViewMode.SIGNIN);

    @Inject
    WeakReference<AuthActivity> activityWeakReference;

    @Inject
    public AuthViewModel() {
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode.set(viewMode);
    }

    public void onClickBack() {
        switch (viewMode.get()) {
            case SIGNIN:
                activityWeakReference.get().finish();
                break;
            case SIGNUP:
            case FINDPWD:
            case CHANGEPWD:
                viewMode.set(ViewMode.SIGNIN);
                break;
            case POLICY:
                viewMode.set(ViewMode.SIGNUP);
                break;
        }
    }
}
