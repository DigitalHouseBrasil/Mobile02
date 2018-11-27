package br.com.digitalhouse.digitaltestes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public TestRule activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testIfLoginIsCorrect() {
        onView(withId(R.id.edittext_user_login)).perform(replaceText("tairo@digitalhouse.com"));
        onView(withId(R.id.edittext_password_login)).perform(replaceText("123456"));
        onView(withId(R.id.btn_goto_login)).perform(scrollTo()).perform(click());
        onView(withText("Bem vindo!"));
    }

}