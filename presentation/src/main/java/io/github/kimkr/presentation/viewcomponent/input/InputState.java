package io.github.kimkr.presentation.viewcomponent.input;

/**
 * Created by kkr on 2017. 8. 25..
 */

public enum InputState {

    FOCUS_OFF_EMPTY(0), FOCUS_OFF_VALID(1), FOCUS_OFF_INVALID(2),
    FOCUS_ON_EMPTY(3), FOCUS_ON_VALID(4), FOCUS_ON_INVALID(5);

    private int code;

    InputState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public boolean hasFocus() {
        return code >= FOCUS_ON_EMPTY.code;
    }

    public InputState flipFocus() {
        int numOfStates = InputState.values().length;
        int nextStateCode = (code + (numOfStates / 2)) % numOfStates;
        return from(nextStateCode);
    }

    public static InputState from(int code) {
        for (InputState space : InputState.values()) {
            if (space.getCode() == code) {
                return space;
            }
        }
        return FOCUS_ON_EMPTY;
    }

    public static InputState getState(boolean hasFocus, boolean isEmpty, boolean isValid) {
        int code = 0;
        code += hasFocus ? 3 : 0;
        code += isEmpty ? 0 : 1;
        code += isValid ? 0 : 1;
        return from(code);
    }
}
