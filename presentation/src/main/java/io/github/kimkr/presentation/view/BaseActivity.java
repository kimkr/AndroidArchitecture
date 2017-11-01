package io.github.kimkr.presentation.view;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by kkr on 2017. 10. 21..
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity
        implements LifecycleOwner {

    protected T binding;
    protected final int NO_LAYOUT = -1;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return this.lifecycleRegistry;
    }

    protected abstract void injectDependency(@Nullable Bundle savedInstanceState);

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void bind(T binding);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectDependency(savedInstanceState);
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        if (getLayout() > NO_LAYOUT) {
            binding = DataBindingUtil.setContentView(this, getLayout());
        }
        if (getLayout() > NO_LAYOUT) {
            bind(binding);
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }
}
