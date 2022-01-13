package com.example.allyourfavouritefilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val film:Film? = intent.extras?.getParcelable("film") as? Film
        if (film is Film) {
            //Устанавливаем заголовок
            details_toolbar.title = film.title
//Устанавливаем картинку
            details_poster.setImageResource(film.poster)
//Устанавливаем описание
            details_description.text = film.description
        }
    }
}
