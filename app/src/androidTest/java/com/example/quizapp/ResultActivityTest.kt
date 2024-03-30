package com.example.quizapp

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizapp.activity.ResultActivity
import com.example.quizapp.constants.AppConstants
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test class for ResultActivity.
 */
@RunWith(AndroidJUnit4::class)
class ResultActivityTest {

    private lateinit var activityScenario: ActivityScenario<ResultActivity>

    /**
     * Set up method executed before each test case.
     */
    @Before
    fun setUp() {
        // Create an intent with necessary extras
        val intent: Intent = Intent(ApplicationProvider.getApplicationContext(), ResultActivity::class.java).apply {
            putExtra(AppConstants.USER_NAME, "John")
            putExtra(AppConstants.CORRECT_ANSWERS, 7)
            putExtra(AppConstants.TOTAL_QUESTIONS, 10)
        }
        // Launch ResultActivity with the intent
        activityScenario = ActivityScenario.launch(intent)
    }

    /**
     * Tear down method executed after each test case.
     */
    @After
    fun tearDown() {
        // Close ResultActivity
        activityScenario.close()
    }

    /**
     * Test method to verify behavior of finishButton.
     */
    @Test
    fun testFinishButton() {
        // Click on the finishButton
        onView(withId(R.id.finishButton)).perform(click())

        // MainActivity should be launched after clicking finishButton
        activityScenario.onActivity { activity ->
            assert(activity.isFinishing) { "MainActivity should be launched." }
        }
    }
}