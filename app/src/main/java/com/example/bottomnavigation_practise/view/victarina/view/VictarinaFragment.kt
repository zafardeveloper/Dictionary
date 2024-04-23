package com.example.bottomnavigation_practise.view.victarina.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.alif.core.common.clazz
import com.alif.core.view.extention.findViewById
import com.example.bottomnavigation_practise.R
import com.example.bottomnavigation_practise.core.view.BaseNewsVMFragment
import com.example.bottomnavigation_practise.view.victarina.vm.DictionaryFragmentViewModel
import com.example.bottomnavigation_practise.view.victarina.vm.DictionaryResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class VictarinaFragment : BaseNewsVMFragment<DictionaryResult, DictionaryFragmentViewModel>(
    R.layout.fragment_dictionary,
    clazz()
) {

    private var wordTextView: TextView by Delegates.notNull()
    private var answersLinearLayout: LinearLayout by Delegates.notNull()

    override fun initView() {
        wordTextView = findViewById(R.id.wordTextView)
        answersLinearLayout = findViewById(R.id.answersLinearLayout)
        val refreshButton = findViewById<ImageView>(R.id.refreshButton)
        refreshButton.setOnClickListener {
            // Заменяем текущий фрагмент на новый
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_view, VictarinaFragment())
            transaction.commit()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        wordTextView = view!!.findViewById(R.id.wordTextView)
        answersLinearLayout = view.findViewById(R.id.answersLinearLayout)
        return view
    }

    override fun onInitVMObservers(): DictionaryFragmentViewModel.() -> Unit = {
        resultLiveData.observe { result ->
            when (result) {
                is DictionaryResult.VictorinaWordsModel -> {
                    wordTextView.text = result.word
                    result.answers.forEach { answer ->
                        val answerButton = LayoutInflater.from(requireContext()).inflate(R.layout.item_button, answersLinearLayout, false) as Button
                        answerButton.text = answer
                        answerButton.setOnClickListener {
                            CoroutineScope(Dispatchers.Main).launch {
                                val isCorrect = viewModel.checkAnswer(result.id, answer)
                                handleAnswerButtonClick(answerButton, isCorrect)
                            }
                        }
                        answersLinearLayout.addView(answerButton)
                    }
                }

                is DictionaryResult.VictorinaCheckModel -> {
                    val message = if (result.isAnswerTrue) "Правильно!" else "Неправильно!"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        loadRandomVictorina()
    }

    private fun handleAnswerButtonClick(button: Button, isCorrect: Boolean) {
        val colorResId = if (isCorrect) Color.GREEN else Color.RED
        button.setBackgroundColor(colorResId)
    }

}