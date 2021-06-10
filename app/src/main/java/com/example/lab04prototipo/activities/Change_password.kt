package com.example.lab04prototipo.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.lab04prototipo.Data.DataAccessLogin
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.Usuario
import com.example.lab04prototipo.MainActivity
import com.example.lab04prototipo.R

class Change_password : MainActivity() {

    var CORRECTO : String = "SUCCESS"
    var INCORRCTO1 : String = "CKECK PASSWORD!"
    var INCORRECTO2 : String = "USE ANOTHER USERNAME!"
    var INCORRECTO3 : String = "IMCOMPLETE DATA!"
    private var DB = DataBaseDummy
    private var accesLogin = DataAccessLogin(DB)

    private var username_change: EditText? = null
    private var password_change: EditText? = null
    private var password_change_confirm: EditText? = null
    private var form_btn: Button? = null

    override fun initActivity(){
        username_change = findViewById(R.id.username_change)
        password_change = findViewById(R.id.password_change)
        password_change_confirm = findViewById(R.id.password_change_confirm)
        form_btn = findViewById(R.id.btn_change)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        initActivity()

        form_btn?.setOnClickListener(){

            var error: String = check(username_change?.text.toString(), password_change?.text.toString(), password_change?.text.toString())
            if(error.isEmpty()){
                message(CORRECTO)
                accesLogin.setPassword(username_change.toString(),password_change.toString())
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("model", DB)
                startActivity(intent)
                finish()
            }else{
                message(error)
            }
        }

    }//fin del metodo

    fun validateNotull(value: String): Boolean{
        return value.isEmpty()
    }

    fun validateConfirmatePassword(password1: String, password2: String): Boolean{
        return password1.equals(password2)
    }

   /* fun validateUsername(user: String):Boolean{
        return accesLogin.verificarUsuario(user) ==null
    }*/

    fun check(user: String, passActual: String, pass1:String): String{
        var error : String =""

        if(validateNotull(pass1)  || validateNotull(user) || validateNotull(passActual)){
            error = INCORRECTO3
        }
      /*  if(!validateConfirmatePassword(pass1,pass2)){
            error +=" "+ INCORRECTO2
        }*/
      /*  if(validateUsername(user)){
            error+=" "+ INCORRCTO1
        }*/

        return error
    }

}