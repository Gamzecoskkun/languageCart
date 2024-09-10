package com.gamzecoskun.duocards.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.Word
import com.gamzecoskun.duocards.common.viewBinding
import com.gamzecoskun.duocards.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private lateinit var selectedWord: Word

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Argümentlerden seçilen veriyi alırız
        selectedWord = DetailFragmentArgs.fromBundle(requireArguments()).selectedWord

        //Kelime bilgilerini ekranda gösteririz
        binding.englishWordText.text=selectedWord.englishWord
        binding.turkishWordText.text=selectedWord.turkishWord
        binding.imageView.setImageResource(selectedWord.imageResId)


        //Learned buttonuna tıklanırsa kelimeyi kaydet
        binding.learnedButton.setOnClickListener{
            saveWordAsLearned(selectedWord)
        }

    }

    private fun saveWordAsLearned(word: Word){
        val sharedPreferences=requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.putBoolean(word.englishWord,true)
        editor.apply()

        Toast.makeText(requireContext(), "${word.englishWord} learned", Toast.LENGTH_SHORT).show()
    }
}