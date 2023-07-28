package com.curso.android.tpandroid

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun mainActivityMatchCheck() {
        Espresso.onView(
            ViewMatchers.withId(R.id.primerTxt)
        ).perform(
            ViewActions.typeText("Texto")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.segundoTxt)
        ).perform(
            ViewActions.typeText("Texto"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.btnComparar)
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.textView4)
        ).check(
            matches(
                ViewMatchers.withText(R.string.txt_result_ok)
            )
        )
    }

    @Test
    fun mainActivityNoMatchCheck() {
        Espresso.onView(
            ViewMatchers.withId(R.id.primerTxt)
        ).perform(
            ViewActions.typeText("TextoA")
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.segundoTxt)
        ).perform(
            ViewActions.typeText("TextoB"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.btnComparar)
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.textView4)
        ).check(
            matches(
                ViewMatchers.withText(R.string.txt_result_fail)
            )
        )
    }
}
