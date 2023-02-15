package com.example.androidstudiomaktab

import EventManager
import Observers
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val eventManager = EventManager("0")
        val listener1 = Observers("ehsan")
        val listener2 = Observers("behnam")
        val listener3 = Observers("surush")
        eventManager.subscribe("0",listener1)
        eventManager.subscribe("0",listener2)
        eventManager.subscribe("0",listener3)
        eventManager.notify("0")

    }
}