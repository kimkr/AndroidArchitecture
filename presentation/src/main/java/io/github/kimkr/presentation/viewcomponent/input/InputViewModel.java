package io.github.kimkr.presentation.viewcomponent.input;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.EditText;

import io.github.kimkr.presentation.BR;

/**
 * Created by kkr on 2017. 8. 25..
 */

public class InputViewModel extends BaseObservable {

    public final ObservableField<InputState> state;
    public final ObservableBoolean essential;
    public final ObservableBoolean encrypt;
    private String input;

    public InputViewModel() {
        this(false, false);
    }

    public InputViewModel(boolean essential, boolean encrypt) {
        this.state = new ObservableField<>(InputState.FOCUS_OFF_EMPTY);
        this.essential = new ObservableBoolean(essential);
        this.encrypt = new ObservableBoolean(encrypt);
    }

    @Bindable
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
        notifyPropertyChanged(BR.input);
    }

    public void updateValidity(boolean empty, boolean valid) {
        final boolean hasFocus = state.get().hasFocus();
        state.set(InputState.getState(hasFocus, empty, valid));
    }

    public void updateFocusState(boolean currentFocus) {
        InputState prevState = state.get();
        if (prevState.hasFocus() ^ currentFocus) {
            state.set(prevState.flipFocus());
        }
    }

    public void clearInput(EditText editText) {
        editText.setText("");
        editText.requestFocus();
    }
}
