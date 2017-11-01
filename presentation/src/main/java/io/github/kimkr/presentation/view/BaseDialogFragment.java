package io.github.kimkr.presentation.view;

import android.app.DialogFragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by kkr on 2017. 10. 29..
 */

public abstract class BaseDialogFragment<T extends ViewDataBinding> extends DialogFragment
        implements LifecycleRegistryOwner {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    protected T binding;

    protected abstract void injectDependency(Bundle bundle);

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void bind(T binding);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        injectDependency(getArguments());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        bind(binding);
        return binding.getRoot();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
