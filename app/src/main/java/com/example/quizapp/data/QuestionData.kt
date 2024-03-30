package com.example.quizapp.data

import com.example.quizapp.R
import com.example.quizapp.model.Question

/**
 * A singleton object responsible for providing the list of quiz questions.
 */
object QuestionData {

    /**
     * Function to retrieve the list of quiz questions.
     *
     * @return ArrayList<Question> containing the quiz questions.
     */
    fun getQuestions(): ArrayList<Question> {
        return arrayListOf(
            Question(1, R.drawable.ic_flag_of_argentina, R.string.option_argentina, R.string.option_australia, R.string.option_turkey, R.string.option_austria, 1),
            Question(2, R.drawable.ic_flag_of_australia, R.string.option_angola, R.string.option_austria, R.string.option_australia, R.string.option_armenia, 3),
            Question(3, R.drawable.ic_flag_of_brazil, R.string.option_belarus, R.string.option_belize, R.string.option_brunei, R.string.option_brazil, 4),
            Question(4, R.drawable.ic_flag_of_belgium, R.string.option_bahamas, R.string.option_belgium, R.string.option_barbados, R.string.option_belize, 2),
            Question(5, R.drawable.ic_flag_of_fiji, R.string.option_gabon, R.string.option_france, R.string.option_fiji, R.string.option_finland, 3),
            Question(6, R.drawable.ic_flag_of_germany, R.string.option_germany, R.string.option_georgia, R.string.option_greece, R.string.option_none_of_these, 1),
            Question(7, R.drawable.ic_flag_of_denmark, R.string.option_dominica, R.string.option_egypt, R.string.option_denmark, R.string.option_ethiopia, 3),
            Question(8, R.drawable.ic_flag_of_india, R.string.option_ireland, R.string.option_iran, R.string.option_hungary, R.string.option_india, 4),
            Question(9, R.drawable.ic_flag_of_new_zealand, R.string.option_australia, R.string.option_new_zealand, R.string.option_tuvalu, R.string.option_united_states_of_america, 2),
            Question(10, R.drawable.ic_flag_of_kuwait, R.string.option_kuwait, R.string.option_jordan, R.string.option_sudan, R.string.option_palestine, 1)
        )
    }
}