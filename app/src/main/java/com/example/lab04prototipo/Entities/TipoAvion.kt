package com.example.lab04prototipo.Entities

import java.io.Serializable

class TipoAvion (var idTipoAvion: String,
                 var annio: String,
                 var modelo: String,
                 var marca: String,
                 var numFilas: Int,
                 var numAsientos: Int,
                 var cantidadPasajeros: Int
                 ) : Serializable {}