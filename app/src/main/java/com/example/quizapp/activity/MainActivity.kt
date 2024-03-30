package com.example.quizapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quizapp.R
import com.example.quizapp.constants.AppConstants
import com.example.quizapp.databinding.ActivityMainBinding

/**
 * [MainActivity] class for the Quiz App.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    /** Function to initialize the MainActivity */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** Inflate the layout using view binding */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
    }

    /** Function to initialize UI components and set up listeners */
    private fun initializeUI() {
        // Get references to UI components
        val startButton: Button = binding.btnStart
        val nameEditText: EditText = binding.nameEditText

        // Set click listener for the start button
        startButton.setOnClickListener {
            // Retrieve the username from the EditText
            val userName: String = nameEditText.text.toString().trim()
            // Check if the username is not empty. If not empty, start the quiz activity. If empty, show a toast message prompting to enter a name
            if (userName.isNotEmpty()) startQuiz(userName) else Toast.makeText(this, R.string.enter_name_warning, Toast.LENGTH_SHORT).show()
        }
    }

    /**  Function to start the quiz activity */
    private fun startQuiz(userName: String) {
        // Create an Intent to start the quiz activity
        val intent = Intent(this, QuestionsActivity::class.java)
        // Pass the username as an extra to the QuizActivity
        intent.putExtra(AppConstants.USER_NAME, userName)
        // Start the QuizActivity
        startActivity(intent)
        // Finish the current activity
        finish()
    }
}