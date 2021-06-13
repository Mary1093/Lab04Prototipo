package com.example.lab04prototipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.FormaPago
import com.example.lab04prototipo.Entities.Reserva
import com.example.lab04prototipo.Entities.Usuario
import com.example.lab04prototipo.Entities.Vuelos
import com.example.lab04prototipo.helper.MisReservas

class Factura : AppCompatActivity() {

    private var DB = DataBaseDummy

    private var vueloFactura: TextView? = null
    private var ruta: TextView? = null
    private var Precio_factura: TextView? = null
    private var CantidadAsientos: TextView? = null
    private var formaP: TextView? = null
    private var pagar_btn: Button? = null

    fun initActivity(){
        vueloFactura = findViewById(R.id.vueloFactura)
        ruta = findViewById(R.id.ruta)
        Precio_factura = findViewById(R.id.Precio_factura)
        CantidadAsientos = findViewById(R.id.CantidadAsientos)
        formaP = findViewById(R.id.formaP)
        pagar_btn = findViewById(R.id.pagar_btn)
    }
    fun message(text: String?){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factura)
        initActivity()

       /* var reserva: Reserva? = intent.extras?.get("reserva") as Reserva?
       // var cant = intent.extras?.get("reserva").toString()?.toInt()*/

        val numberList = intent.getSerializableExtra( "asientosSeleccionados" )

        val formaPa : String = intent.extras?.get("formaPago").toString()

        var vuelito: Vuelos? = intent.extras?.get("idVuelo") as Vuelos?

        vueloFactura?.text = "Salida: "+vuelito?.horario?.diaSemana+" "+"Hora: "+vuelito?.horario?.horaSalida+":"+
                vuelito?.horario?.minutosSalida+"0"+" / "+"Llegada: "+vuelito?.horario?.horaLlegada+":"+
                vuelito?.horario?.minutosLlegada+"0"
        ruta?.text = "Origen: "+vuelito?.horario?.ruta?.origen+" / "+"Destino: "+vuelito?.horario?.ruta?.destino
        Precio_factura?.text = "Costo: $"+vuelito?.horario?.precio.toString()+" con un %"+vuelito?.horario?.descuento +" incluído"
        CantidadAsientos?.text ="Asientos: "+numberList.toString()
        formaP?.text  = "Forma de Pago: "+ formaPa

        val pago = FormaPago(1, "Credito")

        val us = Usuario("user1","123","Maria","Lopez","marilopez2410","24/10/1993","Heredia","4445","5555555", 1)


        var vcantidad_asientos = numberList.toString()?.toInt()

        var numreserva = 2


        pagar_btn?.setOnClickListener(){
            if (vuelito != null) {
                DB.reservas.add(Reserva(numreserva,vuelito.horario.precio,2, pago,us,vuelito ,"Pago"))
            }

            val intent = Intent(applicationContext, MainNavigationDrawer::class.java)
           // intent.putExtra("reserva",reserva)
            message("Pago exitoso!")

            startActivity(intent)
            finish()
        }

    }
}