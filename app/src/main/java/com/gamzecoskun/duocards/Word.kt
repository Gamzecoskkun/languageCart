package com.gamzecoskun.duocards

import java.io.Serializable
import java.util.concurrent.atomic.AtomicInteger


data class Word(
    val uuid:Int,
    val englishWord: String,
    val turkishWord: String,
    val imageResId: Int,
    var isLearned: Boolean = false,
) : Serializable
