package com.gamzecoskun.duocards.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.common.viewBinding
import com.gamzecoskun.duocards.data.model.Word
import com.gamzecoskun.duocards.databinding.FragmentDetailBinding
import com.gamzecoskun.duocards.ui.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private lateinit var selectedWord: Word
    private val viewModel: DetailViewModel by viewModels()
    private val wordViewModel:WordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedWord = DetailFragmentArgs.fromBundle(requireArguments()).selectedWord

        binding.englishWordText.text = selectedWord.englishWord
        binding.turkishWordText.text = selectedWord.turkishWord
        Glide.with(binding.imageView.context)
            .load(selectedWord.imageResId)
            .into(binding.imageView)


        binding.learnedButton.setOnClickListener {
            saveWordAsLearned(selectedWord)
            viewModel.addWordToLearned(selectedWord)
            wordViewModel.removeWord(selectedWord)

//            findNavController().navigate(R.id.action_detailFragment_to_learnedWordsFragment)
            Toast.makeText(requireContext(),"added to learned list",Toast.LENGTH_SHORT).show()
        }

    }

    private fun saveWordAsLearned(word: Word) {
        val sharedPreferences =
            requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(word.englishWord, true)
        editor.apply()

        Toast.makeText(requireContext(), "${word.englishWord} learned", Toast.LENGTH_SHORT).show()
    }
}