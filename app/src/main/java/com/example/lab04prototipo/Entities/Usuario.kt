package com.example.lab04prototipo.Entities

import java.io.Serializable

class Usuario (var idUsuario: String,
               var password: String,
               var nombre: String,
               var apellidos: String,
               var correo: String,
               var fechaNacimiento: String,
               var direcccion: String,
               var telefonoOficina: String,
               var celular: String,
               var rol: Int
               ) : Serializable {}