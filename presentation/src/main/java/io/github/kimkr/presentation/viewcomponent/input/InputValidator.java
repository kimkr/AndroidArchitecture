package io.github.kimkr.presentation.viewcomponent.input;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by kkr on 19/12/2016.
 */
@Singleton
public class InputValidator {
    private final String PASSWORD_REGX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=\\S+$).{8,}$";
    private Pattern passwordPattern;

    @Inject
    public InputValidator() {
        passwordPattern = Pattern.compile(PASSWORD_REGX);
    }

    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPwd(String pwd) {
        Matcher matcher = passwordPattern.matcher(pwd);
        return matcher.matches();
    }

    public boolean isValidPhone(String phoneNumber) {
        return Patterns.PHONE.matcher(phoneNumber).matches();
    }

    public String getPhoneNumber(String countryCode, String phoneNumberInput) {
        if (phoneNumberInput.startsWith("0")) {
            return countryCode + phoneNumberInput.substring(1);
        }
        return countryCode + phoneNumberInput;
    }
}
