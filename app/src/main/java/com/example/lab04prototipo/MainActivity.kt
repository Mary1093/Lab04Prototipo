package com.example.lab04prototipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lab04prototipo.Data.DataAccessAdministrador
import com.example.lab04prototipo.Data.DataAccessLogin
import com.example.lab04prototipo.Data.DataBaseDummy

open class MainActivity : AppCompatActivity() {

    private var DB = DataBaseDummy
    private var accesLogin = DataAccessLogin(DB)

    private var username_txt: EditText? = null
    private var password_txt: EditText? = null
    private var login_btn: Button? = null

    fun initActivity(){

        username_txt = findViewById(R.id.username_txtFld)
        password_txt = findViewById(R.id.password_txtFld)
        login_btn = findViewById(R.id.login_btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActivity()
        login_btn?.setOnClickListener(){
            login()
        }

    }
    fun message(text: String?){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }
    fun login(){

        var usu = accesLogin.verificarUsuario(username_txt?.text.toString(), password_txt?.text.toString())
        if(usu !== null){
            val intent = Intent(applicationContext, MainNavigationDrawer::class.java)
            intent.putExtra("base", DB)
            intent.putExtra("usuario", usu)
            startActivity(intent);
            finish()
        }else{
            message("INCORRECTO")
        }
    }

}