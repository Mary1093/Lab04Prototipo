package com.example.lab04prototipo.helper

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
import com.example.lab04prototipo.Entities.Vuelos
import com.example.lab04prototipo.R
import com.example.lab04prototipo.RecyclerView_Adapter
import com.example.lab04prototipo.RecyclerView_AdapterVuelos
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

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(DB.vuelos, fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition
                var quien: String = ""

                if(direction == ItemTouchHelper.RIGHT){
                    listaVuelos = Vuelos(DB.vuelos[position].codigo, DB.vuelos[position].tipoVuelo, DB.vuelos[position].avionIda, DB.vuelos[position].avionVuelta, DB.vuelos[position].horario)
                    //DB.deletePerson(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, listaVuelos.codigo + "Se eliminaría...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        DB.vuelos.add(position, listaVuelos)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterVuelos(DB.vuelos)
                    lista.adapter = adaptador
                }else{
                    listaVuelos = Vuelos(DB.vuelos[position].codigo, DB.vuelos[position].tipoVuelo, DB.vuelos[position].avionIda, DB.vuelos[position].avionVuelta, DB.vuelos[position].horario)
                    archived.add(listaVuelos)

                    //personas.deletePerson(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, listaVuelos.codigo + "Se editaría...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        archived.removeAt(archived.lastIndexOf(listaVuelos))
                        DB.vuelos.add(position, listaVuelos)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterVuelos(DB.vuelos)
                    lista.adapter = adaptador
                    //getListOfPersons()
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@ListaVuelos, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                       // .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@ListaVuelos, R.color.red))
                       // .addSwipeLeftActionIcon(R.drawable.delete)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(this@ListaVuelos, R.color.green))
                        .addSwipeRightActionIcon(R.drawable.edit2)
                        .create()
                        .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lista)

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