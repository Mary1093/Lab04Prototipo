package com.example.lab04prototipo.Data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.lab04prototipo.Entities.Persona
import java.util.ArrayList

class ModelDummy  private constructor(){

    private val personaList: MutableList<Persona?>?
    private var currentUser: Persona? = null

    init {
        personaList = ArrayList()
        initPersonasList()
    }

    private fun initPersonasList(){
        personaList!!.add(Persona("mari","123","Maria","Lopez","marilopez2410","24/10/1993","Heredia","4445","5555555",ConstantesGlobales.ADMIN_ROLE))
        personaList!!.add(Persona("diego","666","Diego","Brenes","dieguito@gmail.com","22/09/1999","San Jose","55555","888888", ConstantesGlobales.USER_ROLE))
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun checkCredentials(pUser: Persona?): Boolean? {
        return personaList!!.stream()
            .anyMatch { user: Persona? -> user!!.get_idUsuario()== pUser!!.get_idUsuario() && user!!.get_password() == pUser!!.get_password() }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getPersona(pPersona: Persona?): Persona?{
        return personaList!!.stream()
            .filter{user: Persona? -> user!!.get_idUsuario()== pPersona!!.get_idUsuario() && user!!.get_password() == pPersona!!.get_password()}
            .findFirst().get()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun checkUserInfo(pFullName: String?, pUsername: String?): Boolean? {
        return personaList!!.stream()
            .anyMatch { user: Persona? -> user!!.get_FullName()== pFullName && user!!.get_idUsuario()  == pUsername }
    }

    fun get_PersonaList() : MutableList<Persona?>?{
        return personaList
    }

    fun get_CurrentUser():Persona?{
        return currentUser
    }

    fun setCurrentUser(current: Persona?){
        this.currentUser = current
    }

    companion object {
        private var _uniqueInstance: ModelDummy? = null
        fun getInstance(): ModelDummy? {
            if (_uniqueInstance == null) _uniqueInstance = ModelDummy()
            return _uniqueInstance
        }
    }
}