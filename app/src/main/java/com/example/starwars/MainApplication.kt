package com.example.starwars

import android.app.Application
import com.example.starwars.di.ApplicationComponent
import com.example.starwars.di.DaggerApplicationComponent

class MainApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}