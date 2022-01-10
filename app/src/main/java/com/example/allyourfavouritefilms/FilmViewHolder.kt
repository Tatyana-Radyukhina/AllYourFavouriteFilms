package com.example.allyourfavouritefilms

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.film_item.view.*

//ViewHolder предоставляет визуальную часть элемента списка — то, как он будет отрисовываться.
//Этот класс является обёрткой над View, а View является элементом списка.
//Для всех элементов списка создаётся один XML-файл разметки.
// Именно ViewHolder позволяет обратиться к экземпляру View каждого элемента списка.
// Например, в классе ViewHolder можно  привязать к отдельным переменным те или иные View, из которых состоит разметка этого элемента.
// Также можно написать функции для анимирования этого элемента списка или обработать события пользовательского ввода.

//В конструктор класс передается layout, который мы создали(film_item.xml)
class FilmViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
    //Привязываем View из layout к переменным
    private val title = itemView.title
    private val poster = itemView.poster
    private val description = itemView.description

    //В этом методе кладем данные из Film в наши View
    fun bind(film: Film) {
        //Устанавливаем заголовок
        title.text = film.title
        //Устанавливаем постер
        poster.setImageResource(film.poster)
        //Устанавливаем описание
        description.text = film.description
    }
}