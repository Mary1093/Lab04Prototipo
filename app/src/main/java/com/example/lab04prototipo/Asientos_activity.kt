package com.example.lab04prototipo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.lab04prototipo.databinding.ActivityAsientosActivityBinding

class Asientos_activity : AppCompatActivity() {

    private lateinit var binding: ActivityAsientosActivityBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asientos_activity)
    }
}