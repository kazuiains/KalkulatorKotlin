package com.zerosoft.kalkulatorkotlin

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_kalkulator.*
import net.objecthunter.exp4j.ExpressionBuilder

class kalkulator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        //fungsi lainnya
        btnmore.setOnClickListener {
            if (btntambah.text == "+"){
                btntambah.text = "("
                btnkurang.text = ")"
                btnkali.text = "^"
                btnbagi.text = "%"
            }else{
            btntambah.text = "+"
            btnkurang.text = "-"
            btnkali.text = "*"
            btnbagi.text = "/"
        }
        }

        //fungsi tombol
        btn0.setOnClickListener{perintah("0",true)}
        btn1.setOnClickListener{perintah("1",true)}
        btn2.setOnClickListener{perintah("2",true)}
        btn3.setOnClickListener{perintah("3",true)}
        btn4.setOnClickListener{perintah("4",true)}
        btn5.setOnClickListener{perintah("5",true)}
        btn6.setOnClickListener{perintah("6",true)}
        btn7.setOnClickListener{perintah("7",true)}
        btn8.setOnClickListener{perintah("8",true)}
        btn9.setOnClickListener{perintah("9",true)}
        btntitik.setOnClickListener{perintah(".",true)}

        //fungsi matematika
        btntambah.setOnClickListener{
            if (btntambah.text== "+")
                perintah("+",false)
            else
                perintah("(",false)
        }
        btnkurang.setOnClickListener{
            if (btnkurang.text== "-")
                perintah("-",false)
            else
                perintah(")",false)
        }
        btnkali.setOnClickListener{
            if (btnkali.text== "*")
                perintah("*",false)
            else
                perintah("^",false)
        }
        btnbagi.setOnClickListener{
            if (btnbagi.text== "/")
                perintah("/",false)
            else
                perintah("% ",false)
        }

        //fungsi hapus semua (reset)
        btnhpssemua.setOnClickListener {
            inputan.text=""
            hasil.text=""
        }

        //fungsi hapus (backspace)
        btnhapus.setOnClickListener {
            val string = inputan.text.toString()
            if (string.isNotEmpty()){
                inputan.text=string.substring(0,string.length-1)
            }
            hasil.text=""
        }

        //fungsi samadengan (=)
        btnsmdg.setOnClickListener{
            try{
                val tampilanperhitungan = ExpressionBuilder(inputan.text.toString()).build()
                val tampilanhasilnya = tampilanperhitungan.evaluate()
                val panjangtampil = tampilanhasilnya.toLong()
                if (tampilanhasilnya == panjangtampil.toDouble())
                    hasil.text=panjangtampil.toString()
                else
                    hasil.text=tampilanhasilnya.toString()

            }catch (e:Exception){
            Log.d("Exception","message : " + e.message)
            }
        }
    }
    fun perintah( string: String, hapus : Boolean){
        if(hasil.text.isNotEmpty()){
        inputan.text=""

        }
        if(hapus){
        hasil.text=""
        inputan.append(string)
        }else{
            inputan.append(hasil.text)
            inputan.append(string)
            hasil.text=""
        }
    }

}
