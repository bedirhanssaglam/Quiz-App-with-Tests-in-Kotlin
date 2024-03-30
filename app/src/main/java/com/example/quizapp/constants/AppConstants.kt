package com.example.quizapp.constants

/**
 * A class that contains constant values used throughout the application.
 */
class AppConstants {
    companion object {
        // Keys for passing data between activities
        const val USER_NAME: String = "user_name"
        const val TOTAL_QUESTIONS: String = "total_questions"
        const val CORRECT_ANSWERS: String = "correct_answers"

        // Button labels
        const val FINISH: String = "FINISH"
        const val SUBMIT: String = "SUBMIT"
        const val GO_TO_NEXT_QUESTION: String = "GO TO NEXT QUESTION"

        // Default value for page extras
        const val DEFAULT_PAGE_EXTRA_VALUE: Int = 0
    }
}