package com.example.lab04prototipo.Entities

import java.io.Serializable

class Avion (var idAvion: String,
             var ruta : Ruta,
             var tipoAvion : TipoAvion
             ) : Serializable {}