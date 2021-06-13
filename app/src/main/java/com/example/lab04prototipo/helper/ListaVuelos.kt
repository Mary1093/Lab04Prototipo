package com.example.lab04prototipo.helper

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.TipoAvion
import com.example.lab04prototipo.Entities.Usuario
import com.example.lab04prototipo.Entities.Vuelos
import com.example.lab04prototipo.R
import com.example.lab04prototipo.RecyclerView_Adapter
import com.example.lab04prototipo.RecyclerView_AdapterVuelos
import com.example.lab04prototipo.activities.Reserva_activity
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

class ListaVuelos: AppCompatActivity() {

    private var DB = DataBaseDummy
    lateinit var lista: RecyclerView
    lateinit var listaVuelos: Vuelos
    lateinit var adaptador: RecyclerView_AdapterVuelos
    var archived = ArrayList<Vuelos>()
    var position : Int = 0


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_vuelos)

        val searchIcon = findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)

        val cancelIcon = findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)

        val textView = findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

        lista = findViewById(R.id.lista)
        lista.layoutManager = LinearLayoutManager(lista.context)
        lista.setHasFixedSize(true)

        findViewById<SearchView>(R.id.vuelos_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adaptador.filter.filter(newText)
                return false
            }

        })
        getListOfTipoAvion()



    }//end of oncreate
    fun getListOfTipoAvion(){
        val vue = ArrayList<Vuelos>()
        for(a in DB.vuelos){
            vue.add(a)
        }
        adaptador = RecyclerView_AdapterVuelos(vue)
        lista.adapter =adaptador
    }
}