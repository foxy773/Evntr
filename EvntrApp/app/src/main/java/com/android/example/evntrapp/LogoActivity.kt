package com.android.example.evntrapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.findNavController
import java.util.*
import kotlin.concurrent.schedule


class LogoActivity : AppCompatActivity() {

    lateinit var fragmentContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        
        fragmentContainer = findViewById(R.id.fragmentContainerView1)

        Timer().schedule(1500) {

            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<StartupFragment>(R.id.fragmentContainerView1)

            }
        }




      /*  supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent (this@LogoActivity,MainActivity2::class.java)
            startActivity(intent)
        }, 3000)

       */

    }
}