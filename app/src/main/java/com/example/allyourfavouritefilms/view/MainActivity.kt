package com.example.allyourfavouritefilms.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.allyourfavouritefilms.R
import com.example.allyourfavouritefilms.databinding.ActivityMainBinding
import com.example.allyourfavouritefilms.domain.Film
import com.example.allyourfavouritefilms.view.fragments.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Инициализируем объект
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Передаем его в метод
        setContentView(binding.root)
        /*supportFragmentManager.beginTransaction().replace(binding.root.id, HomeFragment()).commit()*/

        initNavigation()

        //Запускаем фрагмент при старте
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    fun launchDetailsFragment(film: Film) {
        //Создаем "посылку"
        val bundle = Bundle()
        //Кладем наш фильм в "посылку"
        bundle.putParcelable("film", film)
        //Кладем фрагмент с деталями в перменную
        val fragment = DetailsFragment()
        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        //Запускаем фрагмент
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initNavigation() {

        binding.bottomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> { val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    //В первом параметре, если фрагмент не найден и метод вернул null, то с помощью
                    //элвиса мы вызываем создание нового фрагмента
                    changeFragment( fragment?: HomeFragment(), tag)
                    true
                }
                R.id.favorites -> {

                    val tag = "favorites"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: FavoritesFragment(), tag)
                    true
                }
                R.id.watch_later -> {
                    val tag = "watch_later"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: WatchLaterFragment(), tag)
                    true
                }
                R.id.selections -> {
                    val tag = "selections"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment( fragment?: SelectionsFragment(), tag)
                    true
                }

                else -> false
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0){
        AlertDialog.Builder(this)
            .setTitle("Вы хотите выйти?")
            .setIcon(R.drawable.ic_baseline_photo_library_24)
            .setPositiveButton("Да") { _, _ ->
                finish()
            }
            .setNegativeButton("Нет") { _, _ ->
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_placeholder, HomeFragment())
                    .addToBackStack(null)
                    .commit()}
            .setNeutralButton("Не знаю") { _, _ ->
                Toast.makeText(this, "Решайся", Toast.LENGTH_SHORT).show()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_placeholder, HomeFragment())
                    .addToBackStack(null)
                    .commit()}

            .show()}
        else return
    }

    //Ищем фрагмент по тэгу, если он есть то возвращаем его, если нет - то null
    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

}


