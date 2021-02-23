package com.adityabrian.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ScoreCounterViewModel : ViewModel(){

    /** kita menampung datanya dengan live data
     *
     *  MutableLiveData() dan ada LiveData()
     *  perbedaannya
     *  MutableLiveData() = kita bisa menambah data didalamnya
     *                      dan bisa juga membacanya
     *  LiveData() =  hanya untuk membaca saja dan tak bisa menambah value */
    private val scoreA : MutableLiveData<Int>? = MutableLiveData()
    private val scoreB : MutableLiveData<Int>? = MutableLiveData()

    fun addScoreA(){
        /** membeacanya objek result = getScoreA null(kosong)
         *  yaitu value yang kosong. ditambahkan satu
         *
         *  lalu scoreA kosong tadi di tambahkan dengan objek result yang sudah dibuat
         *  ramuannya*/
        val result = getScoreA()?.value?.plus(1)
        scoreA?.value = result
    }
    fun addScoreB(){
        val result = getScoreB()?.value?.plus(1)
        scoreB?.value = result
    }
    fun minScoreA(){
        val result = getScoreA()?.value?.minus(1)
            /** karena kita tidak mau score kita menjadi minus*/
        if(result!! < 0){
            scoreA?.value = 0
        }else{
            scoreA?.value = result
        }
    }
    fun minScoreB(){
        val result = getScoreB()?.value?.minus(1)
        if(result!! < 0){
            scoreB?.value = 0
        }else{
            scoreB?.value = result
        }
    }
    fun resetScore(){
        scoreA?.value = 0
        scoreB?.value = 0
    }
    fun getScoreA() :LiveData<Int>?{
        if(scoreA?.value == null){
            /** memiliki 2 cara */
            //MainThread/UI Thread
            scoreA?.value = 0
            // Background Thread
            /*** saat kita menggunakan retrofit atau menggunakan interface untuk callbacknya
                * kita bisa menggunakan postValue()*/
            scoreA?.postValue(0)
        }
        return scoreA
    }
    fun getScoreB() :LiveData<Int>?{
        if(scoreB?.value == null){
            /** memiliki 2 cara */
            //MainThread/UI Thread
            scoreB?.value = 0
            // Background Thread
            /*** saat kita menggunakan retrofit atau menggunakan interface untuk callbacknya
             * kita bisa menggunakan postValue()*/
            scoreB?.postValue(0)
        }
        return scoreB
    }
}