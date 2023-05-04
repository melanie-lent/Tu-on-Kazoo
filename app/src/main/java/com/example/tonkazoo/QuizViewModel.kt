package com.example.tonkazoo

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
    var currentIndex = 0
    var isCheater = false

    private val possibleQuestions = listOf(
        Question(R.string.choose_sentence_s_q1, "choose_sentence_s", "Estoy en la escuela en este momento.", listOf("Estoy en la escuela en este momento.", "Voy a trabajar.", "Hola, buenos días.", "No puedo encontrar el baño.")),
        Question(R.string.choose_sentence_s_q2, "choose_sentence_s", "El lápiz está encima de la silla.", listOf("El lápiz está encima de la silla.", "El lápiz está debajo de la silla.", "El lápiz está arriba de la silla.", "El lápiz está dentro de la silla.")),
        Question(R.string.choose_sentence_s_q3, "choose_sentence_s", "Soy analfabeta.", listOf("Soy analfabeta.", "Estoy felíz.", "Ese es mi primo.", "Puedo leer.")),
        Question(R.string.choose_sentence_s_q4, "choose_sentence_s", "El hombre es bajito.", listOf("Esa casa es alta.", "Hay un gato afuera.", "Soy de Francia.", "El hombre es bajito.")),
        Question(R.string.choose_sentence_e_q1, "choose_sentence_e", "The pencil is behind the book.", listOf("The pencil is behind the book.", "The pencil is between the book and the eraser.", "I lost my pencil.", "My pencil is still in my car.")),
        Question(R.string.choose_sentence_e_q2, "choose_sentence_e", "Do you have a car?", listOf("Do you have a car?", "Do you have any carrots?", "Do they have a car?", "Can you drive a car?")),
        Question(R.string.choose_sentence_e_q3, "choose_sentence_e", "I am from France.", listOf("I'm French.", "I am from France.", "He speaks French.", "This is from france.")),
        Question(R.string.choose_sentence_e_q4, "choose_sentence_e", "See you tomorrow.", listOf("I'm leaving.", "See you tomorrow.", "I'm coming tomorrow.", "I'm lost.")),
        Question(R.string.fill_blank_q1, "fill_blank", "gusta", listOf("gusta", "techar", "necesito", "tengo")),
        Question(R.string.fill_blank_q2, "fill_blank", "verde", listOf("pizarra", "verde", "pero", "canoso")),
        Question(R.string.fill_blank_q3, "fill_blank", "Me llamo", listOf("Me llamo", "Yo tengo", "Nací el", "Entre")),
        Question(R.string.fill_blank_q4, "fill_blank", "Estoy", listOf("Estár", "Soy", "Somos", "Estoy")),
        Question(R.string.define_s_q1, "define_s", "To be (state, location)", listOf("To be (state, location)", "To have", "My name is...", "I was born in...")),
        Question(R.string.define_s_q2, "define_s", "We", listOf("To have", "We", "Parents", "You (pl. f.)")),
        Question(R.string.define_s_q3, "define_s", "My name is...", listOf("They are...", "You are at...", "My name is...", "I was born on...")),
        Question(R.string.define_s_q4, "define_s", "You (pl. inf.)", listOf("You (pl. f.)", "We", "Parents", "Notebook")),
        Question(R.string.define_e_q1, "define_e", "Padres", listOf("Padres", "Borrador", "Papel", "Cumpleaños")),
        Question(R.string.define_e_q2, "define_e", "Me llamo...", listOf("Me llamo...", "Vosotros", "Yo tengo...", "Bolígrafo")),
        Question(R.string.define_e_q3, "define_e", "Ustedes", listOf("Ustedes", "Vosotros", "Tú", "Estoy")),
        Question(R.string.define_e_q4, "define_e", "Papel", listOf("Puerte", "Pizarra", "Techo", "define e q4 a4"))
    )

    private fun generateQuestionList():List<Question> {
        var questions = mutableListOf<Question>()
        var chooseFrom = possibleQuestions.toMutableList()
        for (i in 0..10) {
            var addedQuestionIdx = (0..(chooseFrom.size-1)).random()
            questions.add(chooseFrom[addedQuestionIdx])
            chooseFrom.removeAt(addedQuestionIdx)
        }
        return questions
    }

    private val questionBank = generateQuestionList()

    val currentQuestionAnswer: String
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun firstAnsText(): String {
        val ans = questionBank[currentIndex].possibleAnswers[0]
        Log.d(TAG, ans)
        return ans
    }

    fun secondAnsText(): String {
        val ans = questionBank[currentIndex].possibleAnswers[1]
        Log.d(TAG, ans)
        return ans
    }

    fun thirdAnsText(): String {
        val ans = questionBank[currentIndex].possibleAnswers[2]
        Log.d(TAG, ans)
        return ans
    }

    fun fourthAnsText(): String {
        val ans = questionBank[currentIndex].possibleAnswers[3]
        Log.d(TAG, ans)
        return ans
    }

    fun moveToNext() {
        currentIndex = currentIndex + 1
    }

    fun getIdx(): Int {
        return currentIndex
    }

    fun getQuestionBankSize(): Int {
        return questionBank.size
    }



}