package com.gamzecoskun.duocards.ui.puzzle

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gamzecoskun.duocards.data.WordRepository
import com.gamzecoskun.duocards.data.model.Word
import com.gamzecoskun.duocards.databinding.FragmentPuzzleBinding

class PuzzleFragment : Fragment() {

    private lateinit var binding: FragmentPuzzleBinding
    private lateinit var wordRepository: WordRepository
    private lateinit var currentWord: Word
    private var shuffledWord: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPuzzleBinding.inflate(inflater, container, false)
        wordRepository = WordRepository(requireContext()) // DI ile inject edebilirsin
        setupGame()
        return binding.root
    }

    private fun setupGame() {
        // Kelimeleri repository'den alıyoruz
        val words = wordRepository.getWords()
        currentWord = words.random()

        // Kelimenin harflerini karıştırıyoruz
        shuffledWord = currentWord.englishWord.toCharArray().apply { shuffle() }.concatToString()

        // Karışık kelimeyi ekrana yerleştiriyoruz
        binding.shuffledWordTextView.text = shuffledWord

        // "Check Answer" butonuna tıklama işlemi
        binding.checkButton.setOnClickListener {
            val userAnswer = binding.answerEditText.text.toString().trim()
            checkAnswer(userAnswer)
        }
    }

    private fun checkAnswer(answer: String) {
        if (answer.equals(currentWord.englishWord, ignoreCase = true)) {
            binding.resultTextView.apply {
                text = "Correct!"
                setTextColor(Color.GREEN)
            }

            binding.root.postDelayed({
                binding.resultTextView.text=""
                binding.answerEditText.text.clear()

                setupGame()
            },2000)
        } else {
            binding.resultTextView.apply {
                text = "Wrong! The correct word was: ${currentWord.englishWord}"
                setTextColor(Color.RED)
            }
        }
    }
}
