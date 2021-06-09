package com.example.lab04prototipo.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.lab04prototipo.Data.ConstantesGlobales
import com.example.lab04prototipo.Data.DataAccessLogin
import com.example.lab04prototipo.Data.DataBaseDummy
import com.example.lab04prototipo.Entities.Usuario
import com.example.lab04prototipo.MainActivity
import com.example.lab04prototipo.R

class Registro_activity : MainActivity(){

    var CORRECTO : String = "SUCCESS"
    var INCORRCTO1 : String = "CKECK PASSWORD!"
    var INCORRECTO2 : String = "USE ANOTHER USERNAME!"
    var INCORRECTO3 : String = "IMCOMPLETE DATA!"

    private var DB = DataBaseDummy
    private var accesLogin = DataAccessLogin(DB)

    private var _editable = false
    private var userName_txtFld: EditText? = null
    private var password_txtFld: EditText? = null
    private var nombre: EditText? = null
    private var apellidos: EditText? = null
    private var correo: EditText? = null
    private var fechNac: EditText? = null
    private var direccion: EditText? = null
    private var telOficina: EditText? = null
    private var celular: EditText? = null
    private var form_btn: Button? = null

     override fun initActivity(){
         userName_txtFld = findViewById(R.id.userName_txtFld)
         password_txtFld = findViewById(R.id.password_txtFld)
         _editable = false
         nombre = findViewById(R.id.nombre)
         apellidos = findViewById(R.id.apellidos)
         correo = findViewById(R.id.correo)
         fechNac = findViewById(R.id.fechNac)
         direccion = findViewById(R.id.direccion)
         telOficina = findViewById(R.id.telOficina)
         celular = findViewById(R.id.celular)
         form_btn = findViewById(R.id.form_btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)
        initActivity()
        form_btn?.setOnClickListener(){
            var vuserName_txtFld = userName_txtFld?.text
            var vpassword_txtFld = password_txtFld?.text
            var vnombre= nombre?.text
            var vapellidos= apellidos?.text
            var vcorreo = correo?.text
            var vfechNac= fechNac?.text
            var vdireccion= direccion?.text
            var vtelOficina = telOficina?.text
            var vcelular= celular?.text

            var error: String = check(vuserName_txtFld.toString(),vpassword_txtFld.toString())
            if(error.isEmpty()){
                message(CORRECTO)
                DB.usuarios.add(Usuario(vuserName_txtFld.toString(),vpassword_txtFld.toString(),vnombre.toString(),vapellidos.toString(),
                        vcorreo.toString(), vfechNac.toString(), vdireccion.toString(),vtelOficina.toString(),vcelular.toString(),1))


                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("model", DB)
                startActivity(intent)
                finish()
            }else{
                message(error)
            }
        }

    }//fin del metodo

    fun check(user: String, pass1: String): String{
        var error : String =""
        if(pass1.isEmpty()  || user.isEmpty()){
            return INCORRECTO3
        }
        /*if(!pass1.equals(pass2)){
            error+=" "+ INCORRCTO1
        }*/
       /* if (if (modelo.search(user) != null) true else false) {
            error+=" "+ INCORRECTO2
        }*/
        return error
    }

}//end class