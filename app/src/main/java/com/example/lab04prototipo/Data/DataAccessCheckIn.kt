package com.example.lab04prototipo.Data

import com.example.lab04prototipo.Entities.Tiquete

class DataAccessCheckIn (private var DB: DataBaseDummy) {

    fun listarTiquetes() : ArrayList<Tiquete> {
        return DB.tiquetes
    }

    fun ingresarTiquetes(tiquetes: ArrayList<Tiquete>){
        tiquetes.forEach { tiquete -> DB.tiquetes.add(tiquete) }
    }

}