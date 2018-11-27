package br.com.digitalhouse.digitaltestes.util;

import org.junit.Test;

import static br.com.digitalhouse.digitaltestes.util.AppUtil.validateEmail;
import static org.junit.Assert.assertTrue;

public class AppUtilTest {

    @Test
    public void validateEmailIsCorrect() {
        assertTrue(validateEmail("tairo@digital.com"));
    }

    @Test
    public void validateEmailIsWrong() {

    }

    @Test
    public void validatePasswordCorrect() {

    }

    @Test
    public void validatePasswordWrong() {

    }

    @Test
    public void isValidEmailAddress() {

    }
}