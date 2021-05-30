package com.example.lab04prototipo.Data

import com.example.lab04prototipo.Entities.Reserva

class DataAccessVuelo (private var DB: DataBaseDummy) {

    fun listarReservas() : ArrayList<Reserva> {
        return DB.reservas
    }

    fun ingresarReserva(reserva: Reserva){
        DB.reservas.add(reserva)
    }

    fun pagarReserva(numeroReserva: Int) : Reserva{
        var index = DB.reservas.indexOfFirst { r -> r.numero == numeroReserva}
        return DB.reservas[index]
    }

}