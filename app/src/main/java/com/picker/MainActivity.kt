package com.picker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.picker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var vBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vBinding.root)


    }
}