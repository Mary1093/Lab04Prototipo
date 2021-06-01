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
import com.example.lab04prototipo.Data.DataAccessLogin
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.TipoAvion
import com.example.lab04prototipo.R
import com.example.lab04prototipo.RecyclerView_Adapter
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.*
import kotlin.collections.ArrayList

class CrudTipoAviones : AppCompatActivity(){
    private var DB = DataBaseDummy

    lateinit var lista: RecyclerView
    lateinit var tipoAvion: TipoAvion
    lateinit var adaptador: RecyclerView_Adapter
    var archived = ArrayList<TipoAvion>()
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

        findViewById<SearchView>(R.id.tipoA_search).setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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

                Collections.swap(DB.tiposAvion, fromPosition, toPosition)

                lista.adapter?.notifyItemMoved(fromPosition, toPosition)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                position = viewHolder.adapterPosition
                var quien: String = ""

                if(direction == ItemTouchHelper.LEFT){
                    tipoAvion = TipoAvion(DB.tiposAvion[position].idTipoAvion, DB.tiposAvion[position].annio, DB.tiposAvion[position].modelo, DB.tiposAvion[position].marca, DB.tiposAvion[position].numFilas, DB.tiposAvion[position].numAsientos, DB.tiposAvion[position].cantidadPasajeros)
                    //DB.deletePerson(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, tipoAvion.idTipoAvion + "Se eliminaría...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        DB.tiposAvion.add(position, tipoAvion)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_Adapter(DB.tiposAvion)
                    lista.adapter = adaptador
                }else{
                    tipoAvion = TipoAvion(DB.tiposAvion[position].idTipoAvion, DB.tiposAvion[position].annio, DB.tiposAvion[position].modelo, DB.tiposAvion[position].marca, DB.tiposAvion[position].numFilas, DB.tiposAvion[position].numAsientos, DB.tiposAvion[position].cantidadPasajeros)
                    archived.add(tipoAvion)

                    //personas.deletePerson(position)
                    lista.adapter?.notifyItemRemoved(position)

                    Snackbar.make(lista, tipoAvion.idTipoAvion + "Se editaría...", Snackbar.LENGTH_LONG).setAction("Undo") {
                        archived.removeAt(archived.lastIndexOf(tipoAvion))
                        DB.tiposAvion.add(position, tipoAvion)
                        lista.adapter?.notifyItemInserted(position)
                    }.show()
                    adaptador = RecyclerView_Adapter(DB.tiposAvion)
                    lista.adapter = adaptador
                    //getListOfPersons()
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

             RecyclerViewSwipeDecorator.Builder(this@CrudTipoAviones, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@CrudTipoAviones, R.color.red))
                        .addSwipeLeftActionIcon(R.drawable.delete)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(this@CrudTipoAviones, R.color.green))
                        .addSwipeRightActionIcon(R.drawable.edit)
                        .create()
                        .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

        }



        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(lista)

    }//end of oncreate
    fun getListOfTipoAvion(){
        val tipoA = ArrayList<TipoAvion>()
        for(a in DB.tiposAvion){
            tipoA.add(a)
        }
        adaptador = RecyclerView_Adapter(tipoA)
        lista.adapter =adaptador
    }
}