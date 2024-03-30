package com.example.quizapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quizapp.activity.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test class for MainActivity.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    /**
     * Set up method executed before each test case.
     */
    @Before
    fun setUp() {
        // Launch MainActivity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    /**
     * Tear down method executed after each test case.
     */
    @After
    fun tearDown() {
        // Close MainActivity
        activityScenario.close()
    }

    /**
     * Test method to verify sending text to QuestionsActivity.
     */
    @Test
    fun testSendTextToQuestionsActivity() {
        // Username to be sent to QuestionsActivity
        val userName = "Bedirhan"

        // Find EditText and enter text
        Espresso.onView(ViewMatchers.withId(R.id.name_edit_text))
            .perform(ViewActions.typeText(userName), ViewActions.closeSoftKeyboard())

        // Click the start button
        Espresso.onView(ViewMatchers.withId(R.id.btn_start)).perform(ViewActions.click())

        // Check if QuizQuestionsActivity is launched
        activityScenario.onActivity { activity ->
            assert(activity.isFinishing) { "QuizQuestionsActivity should be launched." }
        }
    }
}