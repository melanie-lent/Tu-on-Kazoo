package com.example.tonkazoo

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import java.lang.Exception

private const val TAG = "QuizActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0

/* Starting activity. You can navigate to lessons, grammar, and high scores from here. */
class QuizActivity : AppCompatActivity() {
    private lateinit var answer_1_button: Button
    private lateinit var answer_2_button: Button
    private lateinit var answer_3_button: Button
    private lateinit var answer_4_button: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView

    private lateinit var multiCounter: TextView
    private lateinit var scoreCounter: TextView

    private var multiplier: Int = 1
    private var score: Int = 0

    private fun updateAnsButtonText() {
        answer_1_button.text = quizViewModel.firstAnsText()
        answer_2_button.text = quizViewModel.secondAnsText()
        answer_3_button.text = quizViewModel.thirdAnsText()
        answer_4_button.text = quizViewModel.fourthAnsText()
    }

    private fun updateMultiText() {
        if (quizViewModel.isCheater)
            multiplier = 1
        multiCounter.text = "Multiplier: ${multiplier}"
    }

    private fun updateScoreText() {
        scoreCounter.text = "Score: ${score}"
    }


    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    private fun checkAnswer(userAnswer: String) {
        val correctAnswer: String = quizViewModel.currentQuestionAnswer
//        }
        Log.d(TAG, "PICKED ANSWER " + userAnswer + "\nCORRECT ANSWER " + correctAnswer)
        val messageResId = when {
            userAnswer == correctAnswer && !quizViewModel.isCheater -> {
                R.string.answer_toast_correct
            }
            userAnswer == correctAnswer && quizViewModel.isCheater -> {
                R.string.answer_toast_correct_cheater
            }
            userAnswer != correctAnswer && quizViewModel.isCheater -> {
                R.string.answer_toast_incorrect_cheater
            }
            else -> {
                R.string.answer_toast_incorrect
            }
        }
        if (messageResId == R.string.answer_toast_correct) {
            multiplier += 1
            score += (100 * multiplier)
        }
        if (messageResId == R.string.answer_toast_incorrect || quizViewModel.isCheater) {
            multiplier = 1
        }
        updateQuestion()
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_quiz)

        getIntent();

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        answer_1_button = findViewById(R.id.answer_1_button)
        answer_2_button = findViewById(R.id.answer_2_button)
        answer_3_button = findViewById(R.id.answer_3_button)
        answer_4_button = findViewById(R.id.answer_4_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)

        multiCounter = findViewById(R.id.multiplier_counter)
        scoreCounter = findViewById(R.id.score_counter)

        updateScoreText()
        updateMultiText()
        updateAnsButtonText()

        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

        answer_1_button.setOnClickListener {
            val button1Text: String = answer_1_button.getText().toString()
            checkAnswer(button1Text)
        }

        answer_2_button.setOnClickListener {
            val button2Text: String = answer_2_button.getText().toString()
            checkAnswer(button2Text)
        }

        answer_3_button.setOnClickListener {
            val button3Text: String = answer_3_button.getText().toString()
            checkAnswer(button3Text)
        }

        answer_4_button.setOnClickListener {
            val button4Text: String = answer_4_button.getText().toString()
            checkAnswer(button4Text)
        }

        cheatButton.setOnClickListener {
            // start cheatactivity
            // navigate to cheat activity
            val answer = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@QuizActivity, answer)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
            updateMultiText()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        quizViewModel.isCheater = false
        if ((quizViewModel.getIdx() + 1) >= quizViewModel.getQuestionBankSize()) {
            if (score >= 6500) {
                Toast.makeText(this, "You finished the quiz! You got a perfect $score!", Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                Toast.makeText(this, "You finished the quiz! Your score is $score", Toast.LENGTH_SHORT)
                    .show()
            }

            finishActivity()
        }
        quizViewModel.moveToNext()
        Log.d(TAG, "Updating question text", Exception())
        val questionTextResId = quizViewModel.currentQuestionText
//        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
        updateAnsButtonText()
        updateScoreText()
        updateMultiText()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    private fun finishActivity() {
        setResult(Activity.RESULT_OK);
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            val intent = Intent(packageContext, QuizActivity::class.java)
            return intent
        }
    }
}