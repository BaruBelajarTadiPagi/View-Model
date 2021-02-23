package com.adityabrian.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    /** KITA UBAH KARENA KITA MENGGUNAKAN CLAS DENGAN VIEW MODEL*/
    // pertama tampuung variabel global
//    private var scoreA = 0
//    private var scoreB = 0

    /** di MainActivity kita menambahkan */
    private lateinit var counterViewModel: ScoreCounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lalu kita akan init counterViewModel terlebih dahulu
        /** si ViewModelProvider ini untuk ngecreate si view model agar
         *  tetap berjalan walaupun layarnya tetap ber ubah-ubah*/
        counterViewModel = ViewModelProvider(this)
            .get(ScoreCounterViewModel::class.java)

        // kita akan menampilkan ScoreA
        initView()

        btn_scorePlusTeamA.setOnClickListener(this)
        btn_scoreMinusTeamA.setOnClickListener(this)
        btn_scorePlusTeamB.setOnClickListener(this)
        btn_scoreMinusTeamB.setOnClickListener(this)
        btn_reset.setOnClickListener(this)

    }

    private fun initView() {
            /** kita akan mendapatkan data getScore dari LiveData()
             *  nah fungsinya kita akan menggunakan LiveData agar setiap perubahan
             *  di live data akan bisa ditampilkan kedalam UI */
        counterViewModel.getScoreA()?.observe(this, Observer {
            if(it != null){
                tvScoreCounterA.text = it.toString()
            }
        })
        counterViewModel.getScoreB()?.observe(this, Observer {
            if(it != null){
                tvScoreCounterB.text = it.toString()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_scorePlusTeamA -> {
                // kita menggunakan fungsi daro counter View Model
                counterViewModel.addScoreA()
            }
            R.id.btn_scoreMinusTeamA -> {
                counterViewModel.minScoreA()
            }
            R.id.btn_scorePlusTeamB -> {
                counterViewModel.addScoreB()
            }
            R.id.btn_scoreMinusTeamB -> {
                counterViewModel.minScoreB()
            }
            R.id.btn_reset -> {
                counterViewModel.resetScore()
            }
        }
    }
}
//    KARENA KITA AKAN MEMBUAT VIEW MDOEL JADI TIDAK MEMBUTUHKAN INI
//    private fun addScoreA(){
//        scoreA += 1
//        tvScoreCounterA.text = scoreA.toString()
//    }
//    private fun addScoreB(){
//        scoreB += 1
//        tvScoreCounterB.text = scoreB.toString()
//    }
//    private fun minScoreA(){
//        scoreA -= 1
//        tvScoreCounterA.text = scoreA.toString()
//    }
//    private fun minScoreB(){
//        scoreB -= 1
//        tvScoreCounterB.text = scoreB.toString()
//    }
//    private fun resetScore(){
//        scoreA = 0
//        scoreB = 0
//        tvScoreCounterA.text = scoreA.toString()
//        tvScoreCounterB.text = scoreB.toString()
//    }
