package com.gamzecoskun.duocards.ui.learnedWords

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
class LearnedViewModel @Inject constructor(private val wordRepository: WordRepository) :
    ViewModel() {

    private val _learnedWordList = MutableLiveData<List<Word>>()
    val learnedWordList: LiveData<List<Word>> get() = _learnedWordList

    init {
        getLearnedWords()
    }

    fun getLearnedWords() {
        _learnedWordList.value = wordRepository.getLearnedWordsFromSharedPreferences()
    }

    fun unlearnWord(word:Word){
        wordRepository.removeWordFromLearned(word)
        getLearnedWords()
    }
}