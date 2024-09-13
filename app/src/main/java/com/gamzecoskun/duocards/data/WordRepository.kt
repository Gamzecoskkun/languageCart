package com.gamzecoskun.duocards.data

import android.content.Context
import android.content.SharedPreferences
import com.gamzecoskun.duocards.data.model.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/***
 * Created on 12.09.2024
 *@author Gamze Coşkun
 */
class WordRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val wordList = mutableListOf<Word>()
    private val learnedWordList = mutableListOf<Word>()

    init {
        saveJsonDataToSharedPrefsIfFirstTime(context)
        loadWordsFromSharedPreferences()
    }

    fun getLearnedWordsFromSharedPreferences():List<Word>{
        val json=sharedPreferences.getString("learned_words_list",null)
        return if (json!=null){
            val type=object :TypeToken<List<Word>>() {}.type
            gson.fromJson(json,type)
        }else{
            emptyList()
        }
    }

    private fun saveLearnedWordsToSharedPreferences(learnedWordList: List<Word>){
        val editor=sharedPreferences.edit()
        val json=gson.toJson(learnedWordList)
        editor.putString("learned_words_list",json)
        editor.apply()
    }

    fun addWordToLearned(word: Word) {
        val learnedWordsFromPrefs = getLearnedWordsFromSharedPreferences().toMutableList()
        learnedWordsFromPrefs.add(word)
        saveLearnedWordsToSharedPreferences(learnedWordsFromPrefs)
    }

    fun removeWordFromLearned(word: Word) {
        val learnedWordsFromPrefs = getLearnedWordsFromSharedPreferences().toMutableList()
        learnedWordsFromPrefs.remove(word)
        saveLearnedWordsToSharedPreferences(learnedWordsFromPrefs)
        addWordList(word)
    }

    private fun loadWordsFromSharedPreferences() {
        val wordsFromPrefs = getWordsFromSharedPreferences()
        wordList.clear()
        wordList.addAll(wordsFromPrefs)

    }

    private fun isFirstRun(): Boolean {
        return sharedPreferences.getBoolean("is_first_run", true)
    }

    private fun setFirstRunCompleted() {
        sharedPreferences.edit().putBoolean("is_first_run", false).apply()
    }

    private fun saveWordsToSharedPreferences(wordList: List<Word>) {
        val json = gson.toJson(wordList)
        sharedPreferences.edit().putString("words_list", json).apply()
    }

    private fun getWordsFromSharedPreferences(): List<Word> {
        val json = sharedPreferences.getString("words_list", null)
        val type = object : TypeToken<List<Word>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    private fun saveJsonDataToSharedPrefsIfFirstTime(context: Context) {
        if (isFirstRun()) {
            val json = context.assets.open("WordList.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Word>>() {}.type
            val wordList: List<Word> = gson.fromJson(json, type)
            saveWordsToSharedPreferences(wordList)
            setFirstRunCompleted()
        }
    }

    private fun addWordList(word: Word) {
        this.wordList.add(word)
        saveWordsToSharedPreferences(wordList)
    }

    fun getWords(): List<Word> {
        return wordList
    }

    fun shuffleWords(): List<Word> {
        return wordList.shuffled()
    }

    fun removeWordFromList(word: Word) {
        wordList.remove(word)
        saveWordsToSharedPreferences(wordList)
    }
    fun removeWordFromPreferences(word: Word){
        learnedWordList.remove(word)
        saveLearnedWordsToSharedPreferences(learnedWordList)

        if (!wordList.contains(word)){
            wordList.add(word)
            saveWordsToSharedPreferences(wordList)
        }
    }
}