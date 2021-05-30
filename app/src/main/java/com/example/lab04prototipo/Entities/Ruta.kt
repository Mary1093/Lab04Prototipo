package com.example.lab04prototipo.Entities

import java.io.Serializable

class Ruta (var idRuta: String,
            var origen: String,
            var destino: String,
            var duracionHoras: Int,
            var duracionMinutos: Int
            ) : Serializable {}