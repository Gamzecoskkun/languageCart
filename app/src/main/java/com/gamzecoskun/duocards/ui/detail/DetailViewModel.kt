package com.gamzecoskun.duocards.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzecoskun.duocards.data.WordRepository
import com.gamzecoskun.duocards.data.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***
 * Created on 12.09.2024
 *@author Gamze Coşkun
 */

@HiltViewModel
class DetailViewModel @Inject constructor(private val wordRepository: WordRepository) :ViewModel() {

    private var _learnedWordList = MutableLiveData<List<Word>>()
    val learnedWordList: LiveData<List<Word>> get() = _learnedWordList

    fun addWordToLearned(word: Word) {
        wordRepository.addWordToLearned(word)
        _learnedWordList.value = wordRepository.getLearnedWordsFromSharedPreferences()
    }
}