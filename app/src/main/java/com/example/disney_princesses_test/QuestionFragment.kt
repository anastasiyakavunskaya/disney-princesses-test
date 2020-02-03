package com.example.disney_princesses_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.disney_princesses_test.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    data class Question(
        val text: String,
        val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "Где ты бы хотела жить?",
            answers = listOf("В волшебном лесу", "В поселении индейцев", "В сказачном замке", "Мне нравятся восточные интерьеры", "В готическом замке","На дне морском")),
        Question(text = "На кого из звезд ты хотела бы быть похожа?",
            answers = listOf("Ким Кардашьян", "Лили Коллинз", "Тейлор Свифт", "Эмма Стоун","Зендая","Эмма Уотсон")),
        Question(text = "Кто мог бы стать твоим помощником и верным другом?",
            answers = listOf("7 верных друзей-парней", "Прожорливый енот", "Волшебные говорящие предметы", "Обезьянка","Мыши","Остроумный краб и рыбка")),
        Question(text = "Какой он твой принц?",
            answers = listOf("С благородными корнями", "Хочу, чтоб он полюбил меня настоящую", "Брутальный снаружи, но милый и добрый внутри", "Отчаянный искатель приключений","Главное,чтоб он хорошо целовался","Не знаю точно, это должна быть химия")),
        Question(text = "В чем твоя сила?",
            answers = listOf("Отлично веду домашнее хозяйство", "Я храбрая и сильная", "Я круто танцую", "Могу читать книги днями и ночами","Могу всё, но до 12 ночи","Могу не дышать под водой больше 2-х минут"))
       /* Question(text = "В твоем гардеробе всегда есть",
            answers = listOf("Аксессуары для волос и красная помада в косметичке", "Вещи в этническом стиле", "Считаю, что крутые туфли «делают» весь образ","Кроп-топ и массивные украшения","Простые базовые вещи и идеальное платье для свидания", "Юбка в пайетках и бюстье")),
        Question(text = "Твое идеальное свидание",
            answers = listOf("Милое домашнее свидание", "Поход в дикий лес", "Ужин при свечах","Что-то экстремальное","Прогулка по парку" "На берегу моря")),
        Question(text = "Кто или что портит тебе жизнь?",
            answers = listOf("Люди, которые звонят в дверь и предлагают всякие пылесосы, картошку, яблоки", "Большие города. Люблю природу", "Тупые качки-ухажеры","Правила и границы дозволенного. Я слишком любопытна","Королевские традиции "Завистливые родственники"))*/
    )
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var answersIndexArray = arrayListOf(0,0,0,0,0,0)
    private var questionIndex = 0
    private val numQuestions = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater, R.layout.fragment_question, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->

            val checkedId = binding.questionRadioGroup.checkedRadioButtonId

            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                    R.id.fifthAnswerRadioButton -> answerIndex = 4
                    R.id.sixthAnswerRadioButton -> answerIndex = 5
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.

                questionIndex++
                answersIndexArray[currentQuestion.answers.indexOf(answers[answerIndex])]++

                // Advance to the next question
                if (questionIndex < numQuestions) {
                    currentQuestion = questions[questionIndex]
                    setQuestion()
                    binding.invalidateAll()
                } else {
                    //countResult(answersIndexArray)
                    view.findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToResultFragment(countResult(answersIndexArray)))
                }
            }
        }
        return binding.root
    }


    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        //(activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }

    private fun countResult(answers: ArrayList<Int>):Int{
        var maxIndex = answers.indexOf(answers.max())
        /*when(maxIndex){
            0 -> return "Белоснежка"
            1 -> return "Покахонтас"
            2 -> return "Белль"
            3 -> return "Жасмин"
            4 -> return "Спящая Красавица"
            5 -> return "Ариэль"
        }*/
        return maxIndex
    }
}