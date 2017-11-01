package io.github.kimkr.presentation.viewcomponent.input;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.functions.Function;

/**
 * Created by kkr on 2017. 8. 25..
 */

public class InputViewBinding {

    @BindingAdapter(value = {"binding:state", "binding:defaultBackground", "binding:errorBackground",
            "binding:validBackground", "binding:essential"}, requireAll = true)
    public static void setBackground(EditText editText, InputState inputState, Drawable defaultBackground,
                                     Drawable errorBackground, Drawable validBackground, boolean essential) {
        switch (inputState) {
            case FOCUS_ON_EMPTY:
            case FOCUS_ON_INVALID:
            case FOCUS_OFF_EMPTY:
                editText.setBackground(defaultBackground);
                break;
            case FOCUS_ON_VALID:
            case FOCUS_OFF_VALID:
                editText.setBackground(validBackground);
                break;
            case FOCUS_OFF_INVALID:
                editText.setBackground(errorBackground);
                break;
        }
    }

    @BindingAdapter(value = {"binding:state", "binding:invalidInputError"}, requireAll = true)
    public static void setErrorMessage(TextView textView, InputState inputState,
                                       String invalidInputError) {
        switch (inputState) {
            case FOCUS_ON_EMPTY:
            case FOCUS_ON_VALID:
            case FOCUS_ON_INVALID:
            case FOCUS_OFF_EMPTY:
            case FOCUS_OFF_VALID:
                textView.setText("");
                break;
            case FOCUS_OFF_INVALID:
                textView.setText(invalidInputError);
        }
    }

    @BindingAdapter(value = {"binding:checker", "binding:viewModel"}, requireAll = true)
    public static void setChecker(EditText editText, Function<String, Boolean> checker,
                                  InputViewModel viewModel) {
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            viewModel.updateFocusState(hasFocus);
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    viewModel.setInput(s.toString());
                    boolean isValid = checker.apply(s.toString());
                    viewModel.updateValidity(s.toString().isEmpty(), isValid);
                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @BindingAdapter(value = {"app:encrypt"}, requireAll = false)
    public static void setEncrypted(EditText editText, boolean encrypt) {
        if (encrypt) {
            editText.setTransformationMethod(new PasswordTransformationMethod() {
                @Override
                public CharSequence getTransformation(CharSequence source, View view) {
                    return new EncryptedCharSequence(source);
                }
            });
        }
    }

    private static class EncryptedCharSequence implements CharSequence {

        private final CharSequence source;

        public EncryptedCharSequence(CharSequence source) {
            this.source = source;
        }

        @Override
        public int length() {
            return source.length();
        }

        @Override
        public char charAt(int i) {
            return '*';
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return source.subSequence(start, end);
        }
    }

}
