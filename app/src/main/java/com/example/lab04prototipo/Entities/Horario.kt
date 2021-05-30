package com.example.lab04prototipo.Entities

import java.io.Serializable

class Horario (var idHorario: String,
               var diaSemana: String,
               var horaSalida: Int,
               var minutosSalida: Int,
               var horaLlegada: Int,
               var minutosLlegada: Int,
               var precio: Int,
               var descuento: Int,
               var ruta: Ruta
               ) : Serializable {}