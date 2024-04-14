package com.alif.newsapplication.view.dictionary.view

import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.alif.core.common.clazz
import com.alif.core.view.extention.findViewById
import com.alif.newsapplication.R
import com.alif.newsapplication.core.view.BaseNewsVMFragment
import com.example.bottomnavigation_practise.view.dictionary.vm.DictionaryFragmentViewModel
import com.example.bottomnavigation_practise.view.dictionary.vm.DictionaryResult
import kotlin.properties.Delegates

class DictionaryFragment : BaseNewsVMFragment<DictionaryResult, DictionaryFragmentViewModel>(
    R.layout.fragment_dictionary,
    clazz()
) {

    private var wordTextView: TextView by Delegates.notNull()
    private var answersLinearLayout: LinearLayout by Delegates.notNull()

    override fun initView() {
        wordTextView = findViewById(R.id.wordTextView)
        answersLinearLayout = findViewById(R.id.answersLinearLayout)
    }

    override fun onInitVMObservers(): DictionaryFragmentViewModel.() -> Unit = {
        resultLiveData.observe { result ->
            when (result) {
                is DictionaryResult.VictorinaWordsModel -> {
                    wordTextView.text = result.word
                    result.answers.forEach { answer ->
                        answersLinearLayout.addView(
                            TextView(context).apply {
                                textSize = 40f
                                text = answer
                                setOnClickListener {
                                    viewModel.checkAnswer(result.id, answer)
                                }
                            }
                        )
                    }
                }

                is DictionaryResult.VictorinaCheckModel -> {
                    Toast.makeText(requireContext(), "${result.isAnswerTrue}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        loadRandomVictorina()
    }
}