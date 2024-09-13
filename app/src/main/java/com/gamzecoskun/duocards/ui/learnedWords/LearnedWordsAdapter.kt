package com.gamzecoskun.duocards.ui.learnedWords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamzecoskun.duocards.data.model.Word
import com.gamzecoskun.duocards.databinding.ItemwordBinding

/***
 * Created on 11.09.2024
 *@author Gamze Coşkun
 */

class LearnedWordsAdapter(
    private var wordList: List<Word>,
    private val onWordClick:(Word)->Unit
) : RecyclerView.Adapter<LearnedWordsAdapter.LearnedWordsViewHolder>() {

    class LearnedWordsViewHolder(private val binding: ItemwordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word,onWordClick: (Word) -> Unit) {
            binding.englishWordText.text = word.englishWord
            binding.turkishWordText.text = word.turkishWord

            Glide.with(binding.imageView.context)
                .load(word.imageResId)
                .into(binding.imageView)

            binding.root.setOnClickListener{
                onWordClick(word)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LearnedWordsViewHolder {
        val binding = ItemwordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LearnedWordsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LearnedWordsViewHolder,
        position: Int
    ) {
        val word = wordList[position]
        holder.bind(word,onWordClick)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun updateWords(newWords: List<Word>) {
        wordList = newWords
        notifyDataSetChanged()
    }
}