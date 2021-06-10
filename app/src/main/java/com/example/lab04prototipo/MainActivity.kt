package com.example.lab04prototipo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lab04prototipo.Data.DataAccessAdministrador
import com.example.lab04prototipo.Data.DataAccessLogin
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.activities.Change_password
import com.example.lab04prototipo.activities.Registro_activity

open class MainActivity : AppCompatActivity() {

    private var DB = DataBaseDummy
    private var accesLogin = DataAccessLogin(DB)

    private var username_txt: EditText? = null
    private var password_txt: EditText? = null
    private var login_btn: Button? = null

    private var _forgot_password_txt: TextView? = null
    private var _register_txt: TextView? = null

    open fun initActivity(){

        username_txt = findViewById(R.id.username_txtFld)
        password_txt = findViewById(R.id.password_txtFld)
        login_btn = findViewById(R.id.login_btn)

        _forgot_password_txt = findViewById(R.id.forgot_password_txt)
        _register_txt = findViewById(R.id.register_txt)

        _forgot_password_txt!!.setOnClickListener(View.OnClickListener { view: View? -> changeUserPassword() })
        _register_txt!!.setOnClickListener(View.OnClickListener { view: View? -> registerUser() })
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
    fun redirectActivityTo(destinationClass: Class<*>?): Intent {
        return Intent(applicationContext, destinationClass)
    }
    private fun registerUser() {
        intent = redirectActivityTo(Registro_activity::class.java)
        intent.putExtra("editable", false)
        startActivity(intent)
    }

    private fun changeUserPassword() {
        intent = redirectActivityTo(Change_password::class.java)
        intent.putExtra("editable", true)
        startActivity(intent)
    }
    fun resetTextErrors(editTextList: Array<EditText?>) {
        for (txt in editTextList) txt!!.setError(null)
    }

}