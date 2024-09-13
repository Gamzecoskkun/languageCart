package com.gamzecoskun.duocards.ui.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gamzecoskun.duocards.data.WordRepository
import com.gamzecoskun.duocards.data.model.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***
 * Created on 10.09.2024
 *@author Gamze Coşkun
 */

@HiltViewModel
class WordViewModel @Inject constructor(private val wordRepository: WordRepository) : ViewModel() {

    private val _wordList = MutableLiveData<List<Word>>(emptyList())
    val wordList: LiveData<List<Word>> get() = _wordList

    init {
        loardWords()
    }

    private fun loardWords() {
        _wordList.value = wordRepository.getWords()
    }

    fun shuffleWords() {
        _wordList.value = wordRepository.shuffleWords()
    }

    fun removeWord(word: Word){
        val updatedList=_wordList.value?.toMutableList() ?: mutableListOf()
        updatedList.remove(word)
        _wordList.value=updatedList
        wordRepository.removeWordFromList(word)
    }

    fun unlearnWord(word:Word){
        wordRepository.removeWordFromPreferences(word)
        val updatedList=_wordList.value?.toMutableList() ?: mutableListOf()
        if (!updatedList.contains(word)){
            updatedList.add(word)
            _wordList.value=updatedList
        }
    }

}