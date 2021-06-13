package com.example.lab04prototipo.Entities

import java.io.Serializable

class Reserva (var numero: Int,
               var monto: Int,
               var cantAsientos: Int,
               var formaPago: FormaPago,
               var usuario: Usuario,
               var vuelos: Vuelos,
               var estado: String
               ): Serializable {}