package com.example.ucp_2

import android.app.Application
import com.example.ucp_2.dependenciesinjection.ContainerApp

class TokoApp : Application() {

    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //Membuat instance ContainerApp
        containerApp = ContainerApp(this)
        //instance adalah object uang dibuat dari class
    }
}