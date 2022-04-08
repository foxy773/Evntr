package com.android.example.evntrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {

    lateinit var fragmentContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        fragmentContainer = findViewById(R.id.fragmentContainerView)


        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<StartupFragment>(R.id.fragmentContainerView)
        }


    }
}