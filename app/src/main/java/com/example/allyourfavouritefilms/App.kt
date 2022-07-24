package com.example.allyourfavouritefilms

import android.app.Application
import com.example.allyourfavouritefilms.di.AppComponent
import com.example.allyourfavouritefilms.di.modules.DatabaseModule
import com.example.allyourfavouritefilms.di.modules.DomainModule
import com.example.allyourfavouritefilms.di.modules.RemoteModule


class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()

    }
        companion object {
            lateinit var instance: App
                private set
        }

}


