package com.example.allyourfavouritefilms

import android.app.Application
import com.example.allyourfavouritefilms.DI.AppComponent
import com.example.allyourfavouritefilms.DI.DaggerAppComponent
import com.example.allyourfavouritefilms.DI.modules.DatabaseModule
import com.example.allyourfavouritefilms.DI.modules.DomainModule
import com.example.allyourfavouritefilms.DI.modules.RemoteModule

import com.example.allyourfavouritefilms.data.ApiConstants
import com.example.allyourfavouritefilms.data.MainRepository
import com.example.allyourfavouritefilms.data.TmdbApi
import com.example.allyourfavouritefilms.domain.Interactor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {
    /*lateinit var repo: MainRepository
    lateinit var interactor: Interactor
    lateinit var retrofitService: TmdbApi

    override fun onCreate() {
        super.onCreate()
        //Инициализируем экземпляр App, через который будем получать доступ к остальным переменным
        instance = this
        //Инициализируем репозиторий
        repo = MainRepository()

        //Создаём кастомный клиент
        val okHttpClient = OkHttpClient.Builder()
            //Настраиваем таймауты для медленного интернета
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            //Добавляем логгер
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()

        //Создаем Ретрофит
        val retrofit = Retrofit.Builder()
            //Указываем базовый URL из констант
            .baseUrl(ApiConstants.BASE_URL)
            //Добавляем конвертер
            .addConverterFactory(GsonConverterFactory.create())
            //Добавляем кастомный клиент
            .client(okHttpClient)
            .build()
//Создаем сам сервис с методами для запросов
        retrofitService = retrofit.create(TmdbApi::class.java)
//Инициализируем интерактор
        interactor = Interactor(repo, retrofitService)

    }


    companion object {
        //Здесь статически хранится ссылка на экземпляр App
        lateinit var instance: App
            //Приватный сеттер, чтобы нельзя было в эту переменную присвоить что-либо другое
            private set

    }*/
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


