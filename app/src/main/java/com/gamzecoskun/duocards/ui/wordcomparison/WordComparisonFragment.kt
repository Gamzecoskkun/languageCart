package com.gamzecoskun.duocards.ui.wordcomparison

import android.graphics.Color
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.gamzecoskun.duocards.data.WordRepository
import com.gamzecoskun.duocards.databinding.FragmentWordComparisonBinding

class WordComparisonFragment : Fragment() {

    private lateinit var binding: FragmentWordComparisonBinding
    private lateinit var wordRepository: WordRepository
    private val wordPairs = mutableMapOf<String, String>()
    private var currentPage = 0
    private val pageSize = 8

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordComparisonBinding.inflate(inflater, container, false)
        wordRepository = WordRepository(requireContext())
        setupGame(currentPage) // İlk sayfayı yüklüyoruz

        // Swipe yenileme işlemi
        binding.swipeRefreshLayout.setOnRefreshListener {
            currentPage++
            setupGame(currentPage) // Yeni sayfayı yüklüyoruz
        }

        return binding.root
    }

    private fun setupGame(page: Int) {
        val words = wordRepository.getWords()
        wordPairs.clear()

        // Sayfa başı veri seçimi
        val startIndex = page * pageSize
        val endIndex = minOf(startIndex + pageSize, words.size)

        // Eğer startIndex mevcut veri boyutunu geçerse, kullanıcıya bir mesaj gösterin
        if (startIndex >= words.size) {
            binding.resultTextView.text = "Daha fazla kelime yok."
            binding.resultTextView.setTextColor(Color.RED)
            return
        }

        val currentWords = words.subList(startIndex, endIndex)

        // Kelimeleri eşleştiriyoruz
        for (word in currentWords) {
            wordPairs[word.turkishWord] = word.englishWord
        }

        // Listeleri hazırlıyoruz
        val turkishWords = wordPairs.keys.shuffled().toList()
        val englishWords = wordPairs.values.shuffled().toList()

        val turkishAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_multiple_choice,
            turkishWords
        )
        val englishAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_multiple_choice,
            englishWords
        )

        binding.turkishWordsListView.choiceMode = android.widget.ListView.CHOICE_MODE_MULTIPLE
        binding.englishWordsListView.choiceMode = android.widget.ListView.CHOICE_MODE_MULTIPLE

        binding.turkishWordsListView.adapter = turkishAdapter
        binding.englishWordsListView.adapter = englishAdapter

        // Yenileme işlemi tamamlandığında swipe gösterimini durdur
        binding.swipeRefreshLayout.isRefreshing = false

        binding.checkButton.setOnClickListener {
            checkMatches()
        }
    }

    private fun checkMatches() {
        val turkishListView = binding.turkishWordsListView
        val englishListView = binding.englishWordsListView

        val selectedTurkishPositions: SparseBooleanArray = turkishListView.checkedItemPositions
        val selectedEnglishPositions: SparseBooleanArray = englishListView.checkedItemPositions

        val selectedTurkishWords = mutableListOf<String>()
        val selectedEnglishWords = mutableListOf<String>()

        // Seçilen Türkçe kelimeleri al
        for (i in 0 until selectedTurkishPositions.size()) {
            if (selectedTurkishPositions.valueAt(i)) {
                selectedTurkishWords.add(
                    turkishListView.getItemAtPosition(
                        selectedTurkishPositions.keyAt(i)
                    ) as String
                )
            }
        }

        // Seçilen İngilizce kelimeleri al
        for (i in 0 until selectedEnglishPositions.size()) {
            if (selectedEnglishPositions.valueAt(i)) {
                selectedEnglishWords.add(
                    englishListView.getItemAtPosition(
                        selectedEnglishPositions.keyAt(i)
                    ) as String
                )
            }
        }

        // Sonuçları kontrol et
        var resultText = ""
        var isCorrect = true

        if (selectedTurkishWords.size == selectedEnglishWords.size) {
            for (i in selectedTurkishWords.indices) {
                val turkishWord = selectedTurkishWords[i]
                val correctEnglishWord = wordPairs[turkishWord]

                // Doğru eşleşmeyi kontrol et
                if (selectedEnglishWords.contains(correctEnglishWord)) {
                    resultText += "$turkishWord -> $correctEnglishWord: Doğru eşleşme!\n"
                } else {
                    resultText += "$turkishWord -> Yanlış eşleşme!\n"
                    isCorrect = false
                }
            }
        } else {
            resultText = "Lütfen her iki listeden aynı sayıda kelime seçiniz!"
            isCorrect = false
        }

        // Sonuçları göster
        binding.resultTextView.apply {
            text = if (isCorrect) {
                "Tebrikler! Tüm eşleşmeler doğru!\n$resultText"
            } else {
                "Bazı eşleşmeler yanlış:\n$resultText"
            }
            setTextColor(if (isCorrect) Color.GREEN else Color.RED)
        }
    }
}
