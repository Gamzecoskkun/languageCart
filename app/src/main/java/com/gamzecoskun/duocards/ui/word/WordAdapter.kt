package com.gamzecoskun.duocards.ui.word

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.data.model.Word
import com.gamzecoskun.duocards.databinding.ItemwordBinding

/***
 * Created on 9.09.2024
 *@author Gamze Coşkun
 */

class WordAdapter(
    private val onClick: (Word) -> Unit
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val wordList= mutableListOf<Word>()

    inner class WordViewHolder(private val binding: ItemwordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Word) {
            binding.englishWordText.text = word.englishWord
            binding.turkishWordText.text = word.turkishWord

            Glide.with(binding.imageView.context)
                .load(word.imageResId)
                .placeholder(R.drawable.ic_word)
                .error(R.drawable.ic_learned)
                .into(binding.imageView)

            binding.root.setOnClickListener {
                onClick(word)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = ItemwordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    override fun getItemCount(): Int = wordList.size

    fun updateList(newWordList: List<Word>){
        wordList.clear()
        wordList.addAll(newWordList)
        notifyDataSetChanged()
    }
}





