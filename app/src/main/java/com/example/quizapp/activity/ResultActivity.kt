package com.example.quizapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.quizapp.R
import com.example.quizapp.constants.AppConstants
import com.example.quizapp.databinding.ActivityResultBinding

/**
 * [ResultActivity] to display the result of the quiz.
 */
class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using view binding
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get references to UI elements
        val nameTextView: TextView = binding.nameText
        val scoreTextView: TextView = binding.scoreText
        val finishButton: Button = binding.finishButton

        // Get the username passed from the QuizActivity
        nameTextView.text = intent.getStringExtra(AppConstants.USER_NAME)

        // Get total question count and correct answer count from QuizActivity
        val totalQuestionCount: Int = intent.getIntExtra(AppConstants.TOTAL_QUESTIONS, AppConstants.DEFAULT_PAGE_EXTRA_VALUE)
        val correctAnswerCount: Int = intent.getIntExtra(AppConstants.CORRECT_ANSWERS, AppConstants.DEFAULT_PAGE_EXTRA_VALUE)

        // Display the score
        scoreTextView.text = getString(R.string.score_text_template, correctAnswerCount, totalQuestionCount)

        // Set click listener for finish button to return to MainActivity
        finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}