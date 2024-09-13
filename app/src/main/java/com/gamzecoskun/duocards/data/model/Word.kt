package com.gamzecoskun.duocards.data.model

import java.io.Serializable


data class Word(
    val englishWord: String,
    val turkishWord: String,
    val imageResId: String,
    var isLearned: Boolean = false,
) : Serializable
