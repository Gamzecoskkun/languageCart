package com.gamzecoskun.duocards.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.common.viewBinding
import com.gamzecoskun.duocards.databinding.FragmentWordListBinding

class WordListFragment : Fragment(R.layout.fragment_word_list) {
    private val binding by viewBinding(FragmentWordListBinding::bind)
    private lateinit var wordAdapter: WordAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        wordList.addAll(sharedViewModel.getRandomWords())
        val list = sharedViewModel.getRandomWords()
        sharedViewModel.addCurrentList(list)


        wordAdapter = WordAdapter { word ->
            findNavController().navigate(
                WordListFragmentDirections.actionWordListFragmentToWordDetailFragment(word)
            )
        }
        wordAdapter.updateList(list)


        binding.recyclerView.apply {
            adapter = wordAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        binding.swipe.setOnRefreshListener {
            sharedViewModel.shuffleList()
            wordAdapter.updateList(sharedViewModel.getCurrentWordList())
            binding.swipe.isRefreshing = false
        }
    }
}
