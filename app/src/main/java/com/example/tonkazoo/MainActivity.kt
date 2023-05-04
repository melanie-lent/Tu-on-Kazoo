package com.example.tonkazoo

import android.app.Activity
import android.content.ClipData.newIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.w3c.dom.Text
import java.lang.Exception
import android.widget.Toast.makeText as makeText1

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var startQuizButton: Button // alt enter to auto add a built-in object
    private lateinit var grammarButton: Button

    //        val provider: ViewModelProvider = ViewModelProviders.of(this)
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        startQuizButton = findViewById(R.id.start_button)
        grammarButton = findViewById(R.id.grammar_lesson_button)

        startQuizButton.setOnClickListener {
            // start quizActivity
            // navigate to quiz activity
            val intent = QuizActivity.newIntent(this@MainActivity)
            startActivity(intent)

        }

//        // TODO: 2/8/2022 grammar lesson
        grammarButton.setOnClickListener {
            // start grammar lesson activity (it just displays text)
            // navigate to grammar lesson activity
            val intent = GrammarActivity.newIntent(this@MainActivity)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

//    override fun onSaveInstanceState(savedInstanceState: Bundle) {
//        super.onSaveInstanceState(savedInstanceState)
//        Log.i(TAG, "onSaveInstanceState")
//        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
//    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}