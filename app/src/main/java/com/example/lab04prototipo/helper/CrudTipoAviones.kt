package com.example.lab04prototipo.helper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CrudTipoAviones : AppCompatActivity(){

    lateinit var lista: RecyclerView
    var position : Int = 0

    fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

    }
}