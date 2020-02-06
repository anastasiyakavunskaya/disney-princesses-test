package com.example.disney_princesses_test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.disney_princesses_test.databinding.FragmentQuestionBinding


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class QuestionFragment : Fragment() {

    data class Question(
        val text: String,
        val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "Где ты бы хотела жить?",
            answers = listOf("В волшебном лесу", "В поселении индейцев", "В готическом замке", "Мне нравятся восточные интерьеры", "В сказачном замке","На дне морском")),
        Question(text = "На кого из звезд ты хотела бы быть похожа?",
            answers = listOf("Лили Коллинз","Зендая", "Эмма Уотсон","Ким Кардашьян","Тейлор Свифт", "Эмма Стоун")),
        Question(text = "Кто мог бы стать твоим помощником и верным другом?",
            answers = listOf("7 верных друзей-парней", "Прожорливый енот", "Волшебные говорящие предметы", "Обезьянка","Мыши","Остроумный краб и рыбка")),
        Question(text = "Какой он твой принц?",
            answers = listOf("Главное,чтоб он хорошо целовался", "Хочу, чтоб он полюбил меня настоящую", "Брутальный снаружи, но милый и добрый внутри", "Отчаянный искатель приключений","С благородными корнями","Не знаю точно, это должна быть химия")),
        Question(text = "В чем твоя сила?",
            answers = listOf("Отлично веду домашнее хозяйство", "Я храбрая и сильная","Могу читать книги днями и ночами", "Я круто танцую", "Могу всё, но до 12 ночи", "Могу не дышать под водой больше 2-х минут")),
        Question(text = "В твоем гардеробе всегда есть",
            answers = listOf("Аксессуары для волос и красная помада в косметичке", "Вещи в этническом стиле","Простые базовые вещи и идеальное платье для свидания","Кроп-топ и массивные украшения", "Считаю, что крутые туфли «делают» весь образ", "Юбка в пайетках и бюстье")),
        Question(text = "Твое идеальное свидание",
            answers = listOf("Милое домашнее свидание", "Поход в дикий лес", "Ужин при свечах","Что-то экстремальное","Прогулка по парку", "На берегу моря")),
        Question(text = "Кто или что портит тебе жизнь?",
            answers = listOf("Люди, которые звонят в дверь и предлагают всякие пылесосы, картошку, яблоки", "Большие города. Люблю природу", "Тупые качки-ухажеры", "Королевские традиции ", "Завистливые родственники","Правила и границы дозволенного. Я слишком любопытна"))
    )
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var answersIndexArray = arrayListOf(0,0,0,0,0,0)
    private var questionIndex = 0
    private val numQuestions = 8

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater, R.layout.fragment_question, container, false)

        if(savedInstanceState!= null) {
            questionIndex = savedInstanceState.getInt("key_question")
            answersIndexArray = savedInstanceState.getIntegerArrayList("key_answersInd")
            setQuestion(savedInstanceState.getStringArrayList("key_answers"))
        }else setQuestion(null)

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
                    setQuestion(null)
                    binding.invalidateAll()
                } else {
                    Log.i("question","answers: $answersIndexArray")
                    view.findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToResultFragment(countResult(answersIndexArray)))
                    questionIndex = 0
                    answersIndexArray = arrayListOf(0,0,0,0,0,0)
                }
            }
        }
        return binding.root
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("key_question", questionIndex)
        outState.putIntegerArrayList("key_answersInd", answersIndexArray)
        outState.putStringArrayList("key_answers", ArrayList(answers))
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion(listOfAnswers: ArrayList<String>?) {

        currentQuestion = questions[questionIndex]
        (activity as AppCompatActivity).supportActionBar!!.title = "Вопрос " + (questionIndex+1)+ " из " + numQuestions

        if(listOfAnswers!=null) answers = listOfAnswers.toMutableList()
        else{
            answers = currentQuestion.answers.toMutableList()
            answers.shuffle()
        }
    }

    private fun countResult(answers: ArrayList<Int>) = answers.indexOf(answers.max())

}