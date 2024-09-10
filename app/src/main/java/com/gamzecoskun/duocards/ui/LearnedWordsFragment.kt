package com.gamzecoskun.duocards.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.common.viewBinding
import com.gamzecoskun.duocards.databinding.FragmentLearnedWordsBinding

class LearnedWordsFragment : Fragment(R.layout.fragment_learned_words) {
    private val binding by viewBinding(FragmentLearnedWordsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}