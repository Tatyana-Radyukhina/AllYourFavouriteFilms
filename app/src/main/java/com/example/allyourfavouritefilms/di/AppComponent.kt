package com.example.allyourfavouritefilms.di

import com.example.allyourfavouritefilms.di.modules.DatabaseModule
import com.example.allyourfavouritefilms.di.modules.DomainModule
import com.example.allyourfavouritefilms.di.modules.RemoteModule
import com.example.allyourfavouritefilms.viewModel.HomeFragmentViewModel
import com.example.allyourfavouritefilms.viewModel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(

    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {

    fun inject(homeFragmentViewModel: HomeFragmentViewModel)

    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}