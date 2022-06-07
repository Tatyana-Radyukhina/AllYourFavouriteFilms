package com.example.allyourfavouritefilms

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allyourfavouritefilms.databinding.FilmItemBinding
import com.example.allyourfavouritefilms.databinding.FragmentWatchlaterBinding

/*import kotlinx.android.synthetic.main.film_item.view.**/

//ViewHolder предоставляет визуальную часть элемента списка — то, как он будет отрисовываться.
//Этот класс является обёрткой над View, а View является элементом списка.
//Для всех элементов списка создаётся один XML-файл разметки.
// Именно ViewHolder позволяет обратиться к экземпляру View каждого элемента списка.
// Например, в классе ViewHolder можно  привязать к отдельным переменным те или иные View, из которых состоит разметка этого элемента.
// Также можно написать функции для анимирования этого элемента списка или обработать события пользовательского ввода.

//В конструктор класс передается layout, который мы создали(film_item.xml)

class FilmViewHolder(var binding: FilmItemBinding, private val itemView: View): RecyclerView.ViewHolder(itemView)


{
    //Привязываем View из layout к переменным
    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description

    //В этом методе кладем данные из Film в наши View
    fun bind(film: Film) {
        //Устанавливаем заголовок
        title.text = film.title
        //Устанавливаем постер
        //Указываем контейнер, в которм будет "жить" наша картинка
        Glide.with(itemView)
            //Загружаем сам ресурс
            .load(film.poster)
            //Центруем изображение
            .centerCrop()
            //Указываем ImageView, куда будем загружать изображение
            .into(poster)
        //Устанавливаем описание
        description.text = film.description
    }

}