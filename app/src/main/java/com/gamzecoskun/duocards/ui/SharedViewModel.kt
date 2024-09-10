package com.gamzecoskun.duocards.ui

import androidx.lifecycle.ViewModel
import com.gamzecoskun.duocards.R
import com.gamzecoskun.duocards.Word
import java.util.concurrent.atomic.AtomicInteger

/***
 * Created on 10.09.2024
 *@author Gamze Coşkun
 */
class SharedViewModel:ViewModel() {

    private val uuid=AtomicInteger(0)
    val wordList = mutableListOf<Word>()




    fun getCurrentWordList():List<Word>{ //sheredviewmodelda her şeyi çekiyor
        return wordList

    }

    fun addCurrentList(list: List<Word>){// listenin içini dolduruyor
        wordList.addAll(list)
    }

    fun shuffleList(){
        wordList.shuffle()
    }

     fun getRandomWords(): List<Word> {
        return listOf(
            Word(uuid.getAndIncrement(),"Cat", "Kedi", R.drawable.ic_cat, false),
            Word(uuid.getAndIncrement(),"Dog", "Köpek", R.drawable.dog, false),
            Word(uuid.getAndIncrement(),"House", "Ev", R.drawable.house, false),
            Word(uuid.getAndIncrement(),"Car", "Araba", R.drawable.car, false),
            Word(uuid.getAndIncrement(),"Book", "Kitap", R.drawable.book, false),
            Word(uuid.getAndIncrement(),"Tree","Ağaç", R.drawable.tree,false),
            Word(uuid.getAndIncrement(),"Water","Su", R.drawable.water,false),
            Word(uuid.getAndIncrement(),"Sun","Güneş", R.drawable.sun,false),
            Word(uuid.getAndIncrement(),"Moon","Ay", R.drawable.moon,false),
            Word(uuid.getAndIncrement(),"Star","Yıldız", R.drawable.star,false),
            Word(uuid.getAndIncrement(),"Food","Yemek", R.drawable.food,false),
            Word(uuid.getAndIncrement(),"Friend","Arkadaş", R.drawable.friends,false),
            Word(uuid.getAndIncrement(),"Love","Sevgi", R.drawable.love,false),
            Word(uuid.getAndIncrement(),"Happy","Mutlu", R.drawable.happy,false),
            Word(uuid.getAndIncrement(),"Sad","Üzgün", R.drawable.sad,false),
            Word(uuid.getAndIncrement(),"Fast","Hızlı", R.drawable.fast,false),
            Word(uuid.getAndIncrement(),"Slow","Yavaş", R.drawable.slow,false),
            Word(uuid.getAndIncrement(),"New","Yeni", R.drawable.nev,false),
            Word(uuid.getAndIncrement(),"Young","Genç", R.drawable.young,false),
            Word(uuid.getAndIncrement(),"Child","Çocuk", R.drawable.children,false),
            Word(uuid.getAndIncrement(),"Woman","Kadın", R.drawable.women,false),
            Word(uuid.getAndIncrement(),"Man","Adam", R.drawable.man,false),
            Word(uuid.getAndIncrement(),"Bird","Kuş", R.drawable.bird,false),
            Word(uuid.getAndIncrement(),"Fish","Balık", R.drawable.fish,false),
            Word(uuid.getAndIncrement(),"Sky","Gökyüzü", R.drawable.sky,false),
            Word(uuid.getAndIncrement(),"Flower","Çiçek", R.drawable.sunflower,false),
            Word(uuid.getAndIncrement(),"Mountain","Dağ", R.drawable.mountain,false),
            Word(uuid.getAndIncrement(),"River","Nehir", R.drawable.river,false),
            Word(uuid.getAndIncrement(),"Sea","Deniz", R.drawable.sea,false),
            Word(uuid.getAndIncrement(),"Chair","Sandalye", R.drawable.chair,false),
            Word(uuid.getAndIncrement(),"Table","Masa", R.drawable.table,false),
            Word(uuid.getAndIncrement(),"Window","Pencere", R.drawable.window,false),
            Word(uuid.getAndIncrement(),"Door","Kapı", R.drawable.door,false),
            Word(uuid.getAndIncrement(),"Computer","Bilgisayar", R.drawable.computer,false),
            Word(uuid.getAndIncrement(),"Phone","Telefon", R.drawable.smartphone,false),
            Word(uuid.getAndIncrement(),"Bottle","Şişe", R.drawable.bottle,false),
            Word(uuid.getAndIncrement(),"Glass","Bardak", R.drawable.glass,false),
            Word(uuid.getAndIncrement(),"Knife","Bıçak", R.drawable.knife,false),
            )
    }
}