package com.example.lab04prototipo

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab04prototipo.Entities.TipoAvion
import com.example.lab04prototipo.Entities.Vuelos

class RecyclerView_AdapterVuelos(private var items: ArrayList<Vuelos>): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var itemsList: ArrayList<Vuelos>? = null

    lateinit var mcontext: Context

    class PersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val personListView = LayoutInflater.from(parent.context).inflate(R.layout.template_vuelos, parent, false)
        val sch = PersonHolder(personListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)
        holder.itemView.findViewById<TextView>(R.id.id_vuelo)?.text = item?.codigo
        holder.itemView.findViewById<TextView>(R.id.tipo_vuelo)?.text = item?.tipoVuelo.toString()
        holder.itemView.findViewById<TextView>(R.id.horario)?.text = item?.horario.toString()
        //holder.itemView.findViewById<ImageView>(R.id.ivFoto).setImageResource(item?.foto!!)

        holder.itemView.setOnClickListener {
            val intent = Intent(mcontext, MainActivity::class.java)
            intent.putExtra("passselectedcountry", itemsList?.get(position))
            mcontext.startActivity(intent)
            Log.d("Selected:", itemsList?.get(position)?.codigo.toString())
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Vuelos>()
                    for (row in items) {
                        if (row.codigo.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    itemsList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemsList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsList = results?.values as ArrayList<Vuelos>
                notifyDataSetChanged()
            }

        }
    }
}