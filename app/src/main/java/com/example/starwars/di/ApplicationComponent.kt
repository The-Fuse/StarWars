package com.example.starwars.di

import android.content.Context
import com.example.starwars.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, NetworkModule::class, InterfaceModule::class])
interface ApplicationComponent {

    fun injectHome(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}