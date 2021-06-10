package com.example.lab04prototipo.Data

import com.example.lab04prototipo.Entities.Usuario

class DataAccessLogin (private var DB: DataBaseDummy) {

    fun verificarUsuario(id: String, password: String): Usuario? {
        var usuario : Usuario? = null
        var index = DB.usuarios.indexOfFirst { u -> u.idUsuario == id}
        if(index != -1){
            usuario = DB.usuarios[index]
            if(usuario.password == password) {
              return usuario
            }
        }
        return usuario
    }
    fun setPassword(user: String, password: String){
        for (obj : Usuario in DB.usuarios) {
            if(obj.nombre.equals(user)){
                obj.password= password
            }
        }
    }
    
}