package com.example.lab04prototipo.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.lab04prototipo.AsientosActivity
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.FormaPago
import com.example.lab04prototipo.Entities.Reserva
import com.example.lab04prototipo.Entities.Usuario
import com.example.lab04prototipo.Entities.Vuelos
import com.example.lab04prototipo.MainActivity
import com.example.lab04prototipo.R


class Reserva_activity: MainActivity() {

    private var DB = DataBaseDummy

    private var usuario_reserva:TextView? = null
    private var cantidad_asientos: EditText? = null
    private var monto_pagar: TextView? = null
    private var forma_pago: EditText? = null
    private var reservar_btn: Button? = null
    lateinit var reserva:Reserva
    override fun initActivity(){
        usuario_reserva = findViewById(R.id.usuario_reserva)
        cantidad_asientos = findViewById(R.id.cantidad_asientos)
        monto_pagar = findViewById(R.id.monto_pagar)
        forma_pago = findViewById(R.id.forma_pago)
        reservar_btn = findViewById(R.id.reservar)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservar_vuelo)
        initActivity()

       var user: Usuario? = intent.extras?.get("usuario") as Usuario?
        usuario_reserva?.text = user?.idUsuario

        var vuelito: Vuelos? = intent.extras?.get("idVuelo") as Vuelos?
        monto_pagar?.text = vuelito?.horario?.precio.toString()


        val pago = FormaPago(1, "CONTADO")
        val us = Usuario("user1","123","Maria","Lopez","marilopez2410","24/10/1993","Heredia","4445","5555555", 1)

        reservar_btn?.setOnClickListener(){
            var vusuario_reserva = usuario_reserva?.text
           // var vcantidad_asientos = cantidad_asientos?.text as Int
            var vcantidad_asientos = cantidad_asientos?.text.toString()?.toInt()
            var vmonto_pagar= monto_pagar?.text
            var vforma_pago= forma_pago?.text
            var numreserva = 2
            var numvuelo = usuario_reserva?.text


            if (vuelito != null) {
               // DB.reservas.add(Reserva(numreserva,vuelito.horario.precio,vcantidad_asientos, pago,us, vuelito,"no pagado"))
                 reserva = Reserva(numreserva,vuelito.horario.precio,vcantidad_asientos, pago,us, vuelito,"no pagado")
            }



            val intent = Intent(applicationContext, AsientosActivity::class.java)
            intent.putExtra("cantidadAsientos", vcantidad_asientos)
            intent.putExtra("idVuelo",vuelito)
            intent.putExtra("formaPago",vforma_pago)
            intent.putExtra("reserva",reserva)

            startActivity(intent)
            finish()

         }//fin del metodo

    }
}
