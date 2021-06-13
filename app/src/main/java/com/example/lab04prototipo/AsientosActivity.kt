package com.example.lab04prototipo

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.lab04prototipo.Entities.Reserva
import com.example.lab04prototipo.Entities.Vuelos
import com.example.lab04prototipo.databinding.ActivityAsientosActivityBinding

class AsientosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsientosActivityBinding
    private var cant: TextView? = null
    val asientos = mutableListOf<String>()


    fun message(text: String?){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_asientos_activity)
        binding = ActivityAsientosActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarAsientos(8,8);

        var vuelito: Vuelos? = intent.extras?.get("idVuelo") as Vuelos?
        val formaP : String = intent.extras?.get("formaPago").toString()

        var btn_pagar: Button = binding.pagar

       // var reserva: Reserva? = intent.extras?.get("reserva") as Reserva?

        btn_pagar.setOnClickListener(){

            if(asientos.isEmpty()){
                message("Debe seleccionar los asientos!!!")
            }else {

                val intent = Intent(applicationContext, Factura::class.java)
                intent.putExtra("asientosSeleccionados", ArrayList(asientos))
                intent.putExtra("idVuelo", vuelito)
                intent.putExtra("formaPago", formaP)
                //intent.putExtra("reserva",reserva)
                startActivity(intent)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun cargarAsientos(filas: Int, columnas: Int) {

        var cant : TextView = binding.cantidadDispo
        var bundle = intent.extras
        var cantidadA : String = bundle!!.getInt("cantidadAsientos").toString()

        cant.text = "Disponibles a elegir: "+ cantidadA
        for (i in 1..filas) {
            val layout: LinearLayout = LinearLayout(applicationContext)
            layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layout.setHorizontalGravity(Gravity.CENTER)
            layout.orientation = LinearLayout.HORIZONTAL
            for (j in 1..columnas) {
                val btn: Button = Button(applicationContext)
                btn.layoutParams = LinearLayout.LayoutParams(110, 110)
                btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#585EF4"))
                //btn.setText(i.toString() + j.toString())
                btn.tag = i.toString() + j.toString()
                layout.addView(btn)
                btn.setOnClickListener {
                    if (btn.backgroundTintList!!.defaultColor == -15348162) {
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#585EF4"))

                        asientos.remove(btn.tag.toString())
                    } else if(asientos.count() < cantidadA.toInt()) {
                        asientos.add(btn.tag.toString());
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#15CE3E"))
                    }
                }
            }
            binding.layoutAsi.addView(layout)
        }
    }
}