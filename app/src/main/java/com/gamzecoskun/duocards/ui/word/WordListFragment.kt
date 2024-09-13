package com.gamzecoskun.duocards.ui.word

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.common.viewBinding
import com.gamzecoskun.duocards.data.model.Word
import com.gamzecoskun.duocards.databinding.FragmentWordListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordListFragment : Fragment(R.layout.fragment_word_list) {

    private lateinit var wordList: MutableList<Word>
    private val binding by viewBinding(FragmentWordListBinding::bind)
    private lateinit var wordAdapter: WordAdapter
    private val viewModel: WordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wordAdapter = WordAdapter { word ->
            findNavController().navigate(
                WordListFragmentDirections.actionWordListFragmentToWordDetailFragment(word)
            )
        }

        binding.recyclerView.apply {
            adapter = wordAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        binding.swipe.setOnRefreshListener {
            viewModel.shuffleWords()
        }
        observeWordList()
    }

    private fun observeWordList() {
        viewModel.wordList.observe(viewLifecycleOwner) { wordList ->
            wordAdapter.updateList(wordList)
            binding.swipe.isRefreshing=false
        }
    }

    fun removeWordFromList(word: Word){
        viewModel.removeWord(word)
    }

    fun addWord(word: Word){
        wordList.add(word)
        wordAdapter.notifyDataSetChanged()
    }
}
