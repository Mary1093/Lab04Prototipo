package com.example.lab04prototipo.Data

import com.example.lab04prototipo.Entities.*
import java.io.Serializable

object DataBaseDummy : Serializable{
    var usuarios = inicializarUsuarios()
    var tiposAvion = inicializarTipoAvion()
    var rutas = inicializarRutas()
    var formasPago = inicializarFormaPago()
    var horarios = inicializarHorarios()
    var aviones = inicializarAviones()
    var vuelos = inicializarVuelos()
    var tiquetes = inicializarTiquetes()
    var reservas = inicializarReservas()

    private fun inicializarUsuarios() : ArrayList<Usuario> {
        var usuarios = ArrayList<Usuario>()
        usuarios.add(Usuario("user1","123","Maria","Lopez","marilopez2410","24/10/1993","Heredia","4445","5555555", 1))
        usuarios.add(Usuario("user2","123","Maria","Lopez","marilopez2410","24/10/1993","Heredia","4445","5555555", 0))
        return usuarios
    }

    private fun inicializarTipoAvion(): ArrayList<TipoAvion> {
        var tiposAvion = ArrayList<TipoAvion>()
        tiposAvion.add(TipoAvion("A01","1999","747","BOEING",4,6,24))
        tiposAvion.add(TipoAvion("A02","2001","747","BOEING",4,6,24))
        return tiposAvion
    }

    private fun inicializarRutas(): ArrayList<Ruta> {
        var rutas = ArrayList<Ruta>()
        rutas.add(Ruta("R01","COSTA RICA","GUATEMALA",1,0))
        rutas.add(Ruta("R02","GUATEMALA","COSTA RICA",1,0))
        return rutas
    }

    private fun inicializarFormaPago(): ArrayList<FormaPago> {
        var formasPago = ArrayList<FormaPago>()
        formasPago.add(FormaPago(1,"CONTADO"))
        formasPago.add(FormaPago(2,"TASA 0"))
        return formasPago
    }

    private fun inicializarHorarios(): ArrayList<Horario> {
        var horarios = ArrayList<Horario>()
        horarios.add(Horario("Hor01","LUNES",5,0,6,0,530000,15, rutas[0]))
        horarios.add(Horario("Hor02","JUEVES",5,0,6,0,530000,15, rutas[0]))
        return horarios
    }

    private fun inicializarAviones(): ArrayList<Avion> {
        var aviones = ArrayList<Avion>()
        aviones.add(Avion("Av01", rutas[0], tiposAvion[0]))
        return aviones
    }

    private fun inicializarVuelos(): ArrayList<Vuelos> {
        var vuelos = ArrayList<Vuelos>()
        vuelos.add(Vuelos("V01",1, aviones[0], aviones[0], horarios[0]))
        return vuelos
    }

    private fun inicializarTiquetes(): ArrayList<Tiquete> {
        var tiquetes = ArrayList<Tiquete>()
        tiquetes.add(Tiquete("V01", vuelos[0], usuarios[0],"C2"))
        return tiquetes
    }

    private fun inicializarReservas(): ArrayList<Reserva> {
        var reservas = ArrayList<Reserva>()
        reservas.add(Reserva(1,150000,1, formasPago[0], usuarios[0],vuelos[0],"Pagado"))
        return reservas
    }

}