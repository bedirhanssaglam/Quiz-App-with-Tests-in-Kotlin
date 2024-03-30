package com.example.quizapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizapp.activity.QuestionsActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test class for QuestionsActivity.
 */
@RunWith(AndroidJUnit4::class)
class QuestionsActivityTest {
    private lateinit var activityScenario: ActivityScenario<QuestionsActivity>

    /**
     * Set up method executed before each test case.
     */
    @Before
    fun setUp() {
        // Launch QuestionsActivity
        activityScenario = ActivityScenario.launch(QuestionsActivity::class.java)
    }

    /**
     * Tear down method executed after each test case.
     */
    @After
    fun tearDown() {
        // Close QuestionsActivity
        activityScenario.close()
    }

    /**
     * Test method to verify submitting answers and navigation to ResultActivity.
     */
    @Test
    fun testSubmitAnswer() {
        // Iterate through all questions
        repeat(9) {
            // Select a random option
            val randomOptionIndex: Int = (1.rangeTo(4)).random() // 1'den 4'e kadar rastgele bir sayı seç
            val optionId: Int = when (randomOptionIndex) {
                1 -> R.id.optionOne
                2 -> R.id.optionTwo
                3 -> R.id.optionThree
                else -> R.id.optionFour
            }
            // Click on the option
            onView(withId(optionId)).perform(click())

            // Now we need to click on the button twice to move on to the next question.
            onView(withId(R.id.submitButton)).perform(click())
            onView(withId(R.id.submitButton)).perform(click())

            // Check if the next question is displayed on the screen
            onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
        }

        // When we reach the 10th question
        val optionId: Int = when ((1.rangeTo(4)).random()) {
            1 -> R.id.optionOne
            2 -> R.id.optionTwo
            3 -> R.id.optionThree
            else -> R.id.optionFour
        }
        // Click on the option
        onView(withId(optionId)).perform(click())

        // Now, after clicking the Submit button, it should navigate to ResultActivity.
        onView(withId(R.id.submitButton)).perform(click())
        onView(withId(R.id.submitButton)).perform(click())

        // We should be directed to the ResultActivity, which is the result screen.
        activityScenario.onActivity { activity: QuestionsActivity ->
            assert(activity.isFinishing) { "ResultActivity should be launched." }
        }
    }
}