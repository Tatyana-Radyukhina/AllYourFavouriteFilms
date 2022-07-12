package com.example.allyourfavouritefilms.DI

import com.example.allyourfavouritefilms.DI.modules.DatabaseModule
import com.example.allyourfavouritefilms.DI.modules.DomainModule
import com.example.allyourfavouritefilms.DI.modules.RemoteModule
import com.example.allyourfavouritefilms.viewModel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}