package com.example.lab04prototipo.Entities

import java.io.Serializable

class Tiquete (var idTiquete: String,
               var vuelo: Vuelos,
               var usuario: Usuario,
               var asiento: String
               ): Serializable {}