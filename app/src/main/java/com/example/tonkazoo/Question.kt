package com.example.tonkazoo

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int,  val questionType: String, val answer: String, val possibleAnswers: List<String>) {
}