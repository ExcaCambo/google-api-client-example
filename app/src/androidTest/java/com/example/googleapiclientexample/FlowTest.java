package com.example.googleapiclientexample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class FlowTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);

    static final String ACCOUNT_NAME = "my@email";

    @Before
    public void setup() {
        PrefUtils.setUseGamesApi(activityRule.getActivity(), false);
    }

    @Test
    public void signInAndSignOut() {
        onView(withId(R.id.fab)).perform(click());
        onData(anything()).inAdapterView(withId(android.R.id.list)).atPosition(0).perform(click());
        pressBack();
        onView(withId(R.id.text))
                .check(matches(
                        withText(activityRule.getActivity().getString(R.string.game_api_connected_with, ACCOUNT_NAME))));
        onView(withId(R.id.fab)).perform(click());
        onData(anything()).inAdapterView(withId(android.R.id.list)).atPosition(0).perform(click());
        pressBack();
        onView(withId(R.id.text)).check(matches(withText(R.string.game_api_not_connected)));
    }
}