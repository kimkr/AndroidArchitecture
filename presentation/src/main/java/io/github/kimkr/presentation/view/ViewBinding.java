package io.github.kimkr.presentation.view;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by kkr on 2017. 10. 26..
 */

public class ViewBinding {

    @BindingAdapter(value = {"binding:selected"}, requireAll = false)
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }
}
