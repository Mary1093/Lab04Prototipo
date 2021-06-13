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
import com.example.lab04prototipo.Entities.Reserva
import com.example.lab04prototipo.Entities.Vuelos
import com.example.lab04prototipo.MainActivity
import com.example.lab04prototipo.R
import com.example.lab04prototipo.RecyclerView_AdapterMisReservas
import com.example.lab04prototipo.RecyclerView_AdapterVuelos
import com.example.lab04prototipo.activities.Registro_activity
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

class MisReservas : AppCompatActivity(){
    private var DB = DataBaseDummy
    lateinit var lista: RecyclerView
    lateinit var listaReservas: Reserva
    lateinit var adaptador: RecyclerView_AdapterMisReservas
    var archived = ArrayList<Reserva>()
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
        getListOfReservas()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition
                position = viewHolder.adapterPosition
                Collections.swap(DB.reservas, fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }
          override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition
                var quien: String = ""

                if(direction == ItemTouchHelper.RIGHT){
                    listaReservas = Reserva(DB.reservas[position].numero, DB.reservas[position].monto, DB.reservas[position].cantAsientos, DB.reservas[position].formaPago, DB.reservas[position].usuario, DB.reservas[position].vuelos,DB.reservas[position].estado)
                    //DB.deletePerson(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, listaReservas.numero.toString() + "Se eliminaría...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        DB.reservas.add(position, listaReservas)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterMisReservas(DB.reservas)
                    lista.adapter = adaptador
                }else{
                    listaReservas = Reserva(DB.reservas[position].numero, DB.reservas[position].monto, DB.reservas[position].cantAsientos, DB.reservas[position].formaPago, DB.reservas[position].usuario, DB.reservas[position].vuelos,DB.reservas[position].estado)
                    archived.add(listaReservas)

                    //personas.deletePerson(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, listaReservas.numero.toString() + "Se editaría...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        archived.removeAt(archived.lastIndexOf(listaReservas))
                        DB.reservas.add(position, listaReservas)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_AdapterMisReservas(DB.reservas)
                    lista.adapter = adaptador
                    //getListOfPersons()
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                RecyclerViewSwipeDecorator.Builder(this@MisReservas, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        // .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@ListaVuelos, R.color.red))
                        // .addSwipeLeftActionIcon(R.drawable.delete)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(this@MisReservas, R.color.green))
                        .addSwipeRightActionIcon(R.drawable.edit2)
                        .create()
                        .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lista)

    }//end of oncreate

    fun getListOfReservas(){
        val vue = ArrayList<Reserva>()
        for(a in DB.reservas){
            vue.add(a)
        }
        adaptador = RecyclerView_AdapterMisReservas(vue)
        lista.adapter =adaptador
    }
}