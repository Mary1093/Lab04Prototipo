package com.example.lab04prototipo.helper

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab04prototipo.Data.DataAccessLogin
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.TipoAvion
import com.example.lab04prototipo.R

class CrudTipoAviones : AppCompatActivity(){
    private var DB = DataBaseDummy

    lateinit var lista: RecyclerView
    lateinit var tipoAvion: TipoAvion
    var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_tipo_avion)

        val searchIcon = findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)

        val cancelIcon = findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)

        val textView = findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

        lista = findViewById(R.id.lista)
        lista.layoutManager = LinearLayoutManager(lista.context)
        lista.setHasFixedSize(true)


    }
    fun getListOfTipoAvion(){
        val tipoA = ArrayList<TipoAvion>()
        for(a in DB.tiposAvion){
            tipoA.add(a)
        }

    }
}