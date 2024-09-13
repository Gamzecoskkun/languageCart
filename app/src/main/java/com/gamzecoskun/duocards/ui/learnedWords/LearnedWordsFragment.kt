package com.gamzecoskun.duocards.ui.learnedWords

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.common.viewBinding
import com.gamzecoskun.duocards.data.model.Word
import com.gamzecoskun.duocards.databinding.FragmentLearnedWordsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnedWordsFragment : Fragment(R.layout.fragment_learned_words) {

    private val binding by viewBinding(FragmentLearnedWordsBinding::bind)
    private val viewModel: LearnedViewModel by viewModels()
    private lateinit var learnedWordsAdapter: LearnedWordsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeLearnedWords()
        viewModel.getLearnedWords()
    }

    private fun setupAdapter() {
        learnedWordsAdapter = LearnedWordsAdapter(emptyList()) { word ->
            showWordDialog(word)
        }
        binding.learnedRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = learnedWordsAdapter
        }
    }

    private fun observeLearnedWords() {
        viewModel.learnedWordList.observe(viewLifecycleOwner) { wordList ->
            learnedWordsAdapter.updateWords(wordList)
        }
    }

    private fun showWordDialog(word: Word) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_word, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialogView.findViewById<TextView>(R.id.tvWord).text = word.englishWord
        dialogView.findViewById<Button>(R.id.btnUnlearned).setOnClickListener {
            unlearnWord(word)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun unlearnWord(word: Word) {

        viewModel.unlearnWord(word)
        Toast.makeText(
            requireContext(),
            "${word.englishWord} marked as unlearned!",
            Toast.LENGTH_SHORT
        ).show()
    }
}