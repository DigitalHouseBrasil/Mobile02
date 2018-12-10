package br.com.digitalhouse.digitaltestes.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtil {
    public static boolean validateEmail(String email) {
        if (email.isEmpty() || !isValidEmailAddress(email)) {
            return false;
        }
        return true;
    }

    public static boolean validatePassword(String password) {
        if (password.isEmpty() || password.length() < 6) {
            return false;
        }

        return true;
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
