package com.example.quizapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.constants.AppConstants
import com.example.quizapp.data.QuestionData
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding
import com.example.quizapp.extensions.isZero
import com.example.quizapp.model.Question

/**
 * [QuestionsActivity] class for the Quiz App.
 */
class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizQuestionsBinding

    private var currentPosition: Int = 1
    private lateinit var questionList: ArrayList<Question>
    private var selectedOptionPosition: Int = 0
    private var correctAnswers: Int = 0
    private lateinit var userName: String

    /** Function to initialize the QuizActivity */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the username passed from MainActivity
        userName = intent.getStringExtra(AppConstants.USER_NAME) ?: ""

        // Set up click listeners for options and submit button
        with(binding) {
            optionOne.setOnClickListener(this@QuestionsActivity)
            optionTwo.setOnClickListener(this@QuestionsActivity)
            optionThree.setOnClickListener(this@QuestionsActivity)
            optionFour.setOnClickListener(this@QuestionsActivity)
            submitButton.setOnClickListener(this@QuestionsActivity)
        }

        // Retrieve the list of questions
        questionList = QuestionData.getQuestions()

        // Set the first question
        setQuestion()
    }

    /** Function to set the current question and update UI */
    private fun setQuestion() {
        val question: Question = questionList[currentPosition - 1]

        with(binding) {
            // Update progress bar and progress text
            progressBar.progress = currentPosition
            progressText.text = "$currentPosition / ${progressBar.max}"

            // Set question image and options
            image.setImageResource(question.flagDrawableId)
            optionOne.setText(question.optionOneResId)
            optionTwo.setText(question.optionTwoResId)
            optionThree.setText(question.optionThreeResId)
            optionFour.setText(question.optionFourResId)

            // Reset option backgrounds
            resetOptions()
        }

        // Update submit button text based on current question position
        binding.submitButton.text =
            if (currentPosition == questionList.size) AppConstants.FINISH else AppConstants.SUBMIT
    }

    /** Function to reset option backgrounds */
    private fun resetOptions() {
        val options: List<TextView> =
            listOf(binding.optionOne, binding.optionTwo, binding.optionThree, binding.optionFour)

        // Reset backgrounds for all options
        options.forEach { option ->
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

        // Disable submit button
        binding.submitButton.isEnabled = false
        binding.submitButton.isClickable = false
    }

    /** Function to handle option selection */
    private fun selectOption(view: TextView, selectedOption: Int) {
        resetOptions()
        selectedOptionPosition = selectedOption

        // Enable submit button and highlight selected option
        binding.submitButton.isEnabled = true
        binding.submitButton.isClickable = true
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.optionOne -> selectOption(binding.optionOne, 1)
            R.id.optionTwo -> selectOption(binding.optionTwo, 2)
            R.id.optionThree -> selectOption(binding.optionThree, 3)
            R.id.optionFour -> selectOption(binding.optionFour, 4)
            R.id.submitButton -> submitAnswer()
        }
    }

    /** Function to handle answer submission */
    private fun submitAnswer() {
        if (selectedOptionPosition.isZero()) {
            currentPosition++
            if (currentPosition <= questionList.size) {
                setQuestion()
            } else {
                navigateToResultActivity()
            }
        } else {
            checkAnswer()
            setCorrectAnswerView()
            if (currentPosition == questionList.size) {
                binding.submitButton.text = AppConstants.SUBMIT
            } else {
                binding.submitButton.text =  AppConstants.GO_TO_NEXT_QUESTION
            }
            selectedOptionPosition = 0
        }
    }

    /** Function to check if the selected answer is correct */
    private fun checkAnswer() {
        val question: Question = questionList[currentPosition - 1]
        if (question.correctAnswerIndex == selectedOptionPosition) {
            correctAnswers++
        }
    }

    /** Function to highlight correct and incorrect answers */
    private fun setCorrectAnswerView() {
        val question: Question = questionList[currentPosition - 1]
        val correctOption: TextView? = when (question.correctAnswerIndex) {
            1 -> binding.optionOne
            2 -> binding.optionTwo
            3 -> binding.optionThree
            4 -> binding.optionFour
            else -> null
        }
        correctOption?.background = ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)

        // Highlight the selected option if it's incorrect
        if (selectedOptionPosition != question.correctAnswerIndex) {
            val wrongOption: TextView? = when (selectedOptionPosition) {
                1 -> binding.optionOne
                2 -> binding.optionTwo
                3 -> binding.optionThree
                4 -> binding.optionFour
                else -> null
            }
            wrongOption?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
        }
    }

    /** Function to navigate to the result activity */
    private fun navigateToResultActivity() {
        val intent: Intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(AppConstants.USER_NAME, userName)
            putExtra(AppConstants.CORRECT_ANSWERS, correctAnswers)
            putExtra(AppConstants.TOTAL_QUESTIONS, questionList.size)
        }
        startActivity(intent)
        finish()
    }
}
