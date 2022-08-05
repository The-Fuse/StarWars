package com.example.starwars.di

import android.content.Context
import com.example.starwars.ui.characterDetails.CharacterDetailsFragment
import com.example.starwars.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, NetworkModule::class, InterfaceModule::class])
interface ApplicationComponent {

    fun injectHome(fragment: HomeFragment)

    fun injectFilmDetails(fragment: CharacterDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}