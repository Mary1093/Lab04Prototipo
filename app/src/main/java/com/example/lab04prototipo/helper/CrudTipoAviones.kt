package com.example.lab04prototipo.helper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab04prototipo.Entities.TipoAvion
import com.example.lab04prototipo.R

class CrudTipoAviones : AppCompatActivity(){

    lateinit var tiposA: TipoAvion
    lateinit var lista: RecyclerView
    var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_tipo_avion)


    }
}