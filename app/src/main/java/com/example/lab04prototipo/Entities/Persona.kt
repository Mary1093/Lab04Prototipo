package com.example.lab04prototipo.Entities

import java.io.Serializable
import java.util.*

class Persona : Serializable {

    private var idUsuario: String?
    private var password: String?
    private var nombre: String?
    private var apellidos: String?
    private var correo: String?
    private var fechaNacimieto: String?
    private var direccion: String
    private var telefonoOficina: String?
    private var celular: String?
    private var rol: Int

    constructor(){
        idUsuario = ""
        password = ""
        nombre = ""
        apellidos = ""
        correo = ""
        fechaNacimieto = ""
        direccion = ""
        telefonoOficina = ""
        celular = ""
        rol = 0
    }
    constructor(idUsuario: String?, password: String?, nombre: String?, apellidos: String?, correo: String?, fechaNacimieto: String?,
                direccion: String, telefonoOficina: String?, celular: String?, rol: Int){

        this.idUsuario = idUsuario
        this.password = password
        this.nombre = nombre
        this.apellidos = apellidos
        this.correo = correo
        this.fechaNacimieto = fechaNacimieto
        this.direccion = direccion
        this.telefonoOficina = telefonoOficina
        this.celular = celular
        this.rol = rol
    }
    fun get_FullName(): String?{
        return "$nombre $apellidos"
    }
    fun get_idUsuario(): String?{
        return idUsuario
    }
    fun get_password(): String?{
        return password
    }
    fun get_Nombre(): String?{
        return nombre
    }
    fun get_Apellidos(): String?{
        return apellidos
    }
    fun get_Correo(): String?{
        return correo
    }
    fun get_FechaNaci(): String?{
        return fechaNacimieto
    }
    fun get_Direccion(): String?{
        return direccion
    }
    fun get_Celular(): String?{
        return celular
    }
    fun get_Rol(): Int?{
        return rol
    }
    override fun toString(): String{
        return "Person{" +
                "Nombre='" + nombre + '\'' +
                ", Apellidos='" + apellidos + '\'' +
                ", Correo='" + correo + '\'' +
                ", Celular='" + celular + '\'' +
                ", Fecha Nacimiento='" + fechaNacimieto + '\'' +
                ", Direccion='" + direccion + '\'' +
                ", Telefono=" + telefonoOficina +
                ", rol='" + rol + '\'' +
                ", id Usuario='" + idUsuario + '\'' +
                ", password=" + password +
                '}'
    }
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val person = o as Persona
        return password == person.password && idUsuario == person.idUsuario && rol == person.rol && telefonoOficina == person.telefonoOficina
                && direccion == person.direccion && fechaNacimieto == person.fechaNacimieto && celular == person.celular && correo == person.correo && apellidos == person.apellidos && nombre == person.nombre
    }

    override fun hashCode(): Int {
        return Objects.hash(
            idUsuario,
            password,
            nombre,
            apellidos,
            correo,
            fechaNacimieto,
            direccion,
            telefonoOficina,
            celular,
            rol
        )
    }
}