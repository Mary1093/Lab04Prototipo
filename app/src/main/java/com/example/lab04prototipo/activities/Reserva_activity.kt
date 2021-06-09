package com.example.lab04prototipo.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.Usuario
import com.example.lab04prototipo.MainActivity
import com.example.lab04prototipo.R

class Reserva_activity: MainActivity() {

    private var DB = DataBaseDummy

    private var usuario_reserva:TextView? = null
    private var cantidad_asientos: EditText? = null
    private var monto_pagar: TextView? = null
    private var forma_pago: EditText? = null

    override fun initActivity(){
        usuario_reserva = findViewById(R.id.usuario_reserva)
        cantidad_asientos = findViewById(R.id.cantidad_asientos)
        monto_pagar = findViewById(R.id.monto_pagar)
        forma_pago = findViewById(R.id.forma_pago)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_reservar_vuelo)
        initActivity()


    }//fin del metodo


}//fin de la clase