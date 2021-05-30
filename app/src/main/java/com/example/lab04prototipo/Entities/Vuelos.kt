package com.example.lab04prototipo.Entities

import java.io.Serializable

class Vuelos (var codigo: String,
              var tipoVuelo: Int,
              var avionIda: Avion,
              var avionVuelta: Avion,
              var horario: Horario
              ) : Serializable {}