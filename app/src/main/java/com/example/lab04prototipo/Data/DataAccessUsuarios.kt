package com.example.lab04prototipo.Data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.lab04prototipo.Entities.Usuario

class DataAccessUsuarios (private var DB: DataBaseDummy) {

    fun listarUsuarios() : ArrayList<Usuario> {
        return DB.usuarios
    }

    fun ingresarUsuario(usuario: Usuario){
        DB.usuarios.add(usuario)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminarUsuario(id: String){
        DB.usuarios.removeIf { u -> u.idUsuario == id }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun actualizarUsuario(usuario: Usuario) {
        var index = DB.usuarios.indexOfFirst { u -> u.idUsuario == usuario.idUsuario}
        DB.usuarios.removeIf { u -> u.idUsuario == usuario.idUsuario }
        DB.usuarios.add(index, usuario)
    }

}