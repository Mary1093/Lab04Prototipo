package com.example.lab04prototipo.Data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.lab04prototipo.Entities.*

class DataAccessAdministrador (private var DB: DataBaseDummy) {

    // region Tipo Avion

    fun listarTiposAvion() : ArrayList<TipoAvion> {
        return DB.tiposAvion
    }

    fun ingresarTipoAvion(tipoAvion: TipoAvion){
        DB.tiposAvion.add(tipoAvion)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminarTipoAvion(idTipoAvion: String){
        DB.tiposAvion.removeIf { tipo -> tipo.idTipoAvion == idTipoAvion }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun actualizarTipoAvion(tipoAvion: TipoAvion) {
        var index = DB.tiposAvion.indexOfFirst { tipo -> tipo.idTipoAvion == tipoAvion.idTipoAvion }
        DB.tiposAvion.removeIf { tipo -> tipo.idTipoAvion == tipoAvion.idTipoAvion }
        DB.tiposAvion.add(index, tipoAvion)
    }

    // endregion

    // region Rutas

    fun listarRutas() : ArrayList<Ruta> {
        return DB.rutas
    }

    fun ingresarRuta(ruta: Ruta){
        DB.rutas.add(ruta)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminarRuta(idRuta: String){
        DB.rutas.removeIf { ruta -> ruta.idRuta == idRuta }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun actualizarRuta(ruta: Ruta) {
        var index = DB.rutas.indexOfFirst { r -> r.idRuta == ruta.idRuta }
        DB.rutas.removeIf { r -> r.idRuta == ruta.idRuta }
        DB.rutas.add(index, ruta)
    }

    // endregion

    // region Horarios

    fun listarHorarios() : ArrayList<Horario> {
        return DB.horarios
    }

    fun ingresarHorario(horario: Horario){
        DB.horarios.add(horario)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminarHorario(idHorario: String){
        DB.horarios.removeIf { h -> h.idHorario == idHorario }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun actualizarHorario(horario: Horario) {
        var index = DB.horarios.indexOfFirst { h -> h.idHorario == h.idHorario}
        DB.horarios.removeIf { h -> h.idHorario == horario.idHorario }
        DB.horarios.add(index, horario)
    }

    // endregion

    // region Aviones

    fun listarAviones() : ArrayList<Avion> {
        return DB.aviones
    }

    fun ingresarAviones(avion: Avion){
        DB.aviones.add(avion)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminarAvion(idAvion: String){
        DB.aviones.removeIf { a -> a.idAvion == idAvion }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun actualizarAvion(avion: Avion) {
        var index = DB.aviones.indexOfFirst { a -> a.idAvion == avion.idAvion}
        DB.aviones.removeIf { a -> a.idAvion == avion.idAvion }
        DB.aviones.add(index, avion)
    }

    // endregion

    // region Vuelos

    fun listarVuelos() : ArrayList<Vuelos> {
        return DB.vuelos
    }

    fun ingresarVuelo(vuelo: Vuelos){
        DB.vuelos.add(vuelo)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminarVuelo(codigoVuelo: String){
        DB.vuelos.removeIf { v -> v.codigo == codigoVuelo }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun actualizarVuelo(vuelo: Vuelos) {
        var index = DB.vuelos.indexOfFirst { v -> v.codigo == vuelo.codigo}
        DB.vuelos.removeIf { v -> v.codigo == vuelo.codigo }
        DB.vuelos.add(index, vuelo)
    }

    // endregion
}