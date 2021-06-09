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
import com.example.lab04prototipo.Entities.Reserva

import com.example.lab04prototipo.activities.Reserva_activity

class RecyclerView_AdapterMisReservas(private var items: ArrayList<Reserva>): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var itemsList: ArrayList<Reserva>? = null

    lateinit var mcontext: Context

    class PersonHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.itemsList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val personListView = LayoutInflater.from(parent.context).inflate(R.layout.template_mis_reservas, parent, false)
        val sch = PersonHolder(personListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemsList?.get(position)

        holder.itemView.findViewById<TextView>(R.id.id_reserva)?.text = item?.numero.toString()
        holder.itemView.findViewById<TextView>(R.id.monto_reserva)?.text = item?.monto.toString()
        holder.itemView.findViewById<TextView>(R.id.formas_pago)?.text = item?.cantAsientos.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(mcontext, Reserva_activity::class.java)
            intent.putExtra("passselectedcountry", itemsList?.get(position))
            mcontext.startActivity(intent)
            Log.d("Selected:", itemsList?.get(position)?.numero.toString())
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemsList = items
                } else {
                    val resultList = ArrayList<Reserva>()
                    for (row in items) {
                        if (row.numero.toString().toLowerCase().contains(charSearch.toLowerCase())) {
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
                itemsList = results?.values as ArrayList<Reserva>
                notifyDataSetChanged()
            }

        }
    }
}