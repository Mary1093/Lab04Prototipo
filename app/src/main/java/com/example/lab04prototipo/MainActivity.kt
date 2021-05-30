package com.example.lab04prototipo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
       // setContentView(R.layout.activity_crear_usuario)
        setContentView(R.layout.activity_change_password)
    }
}