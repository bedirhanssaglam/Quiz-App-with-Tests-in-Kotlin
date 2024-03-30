package com.example.quizapp.model

import androidx.annotation.StringRes

/**
 * Data class representing a quiz question.
 *
 * @property id Unique identifier for the question.
 * @property flagDrawableId Resource ID for the flag image associated with the question.
 * @property optionOneResId Resource ID for the first option text.
 * @property optionTwoResId Resource ID for the second option text.
 * @property optionThreeResId Resource ID for the third option text.
 * @property optionFourResId Resource ID for the fourth option text.
 * @property correctAnswerIndex Index of the correct answer (1, 2, 3, or 4).
 */
data class Question(
    val id: Int,
    val flagDrawableId: Int,
    @StringRes val optionOneResId: Int,
    @StringRes val optionTwoResId: Int,
    @StringRes val optionThreeResId: Int,
    @StringRes val optionFourResId: Int,
    val correctAnswerIndex: Int
)
