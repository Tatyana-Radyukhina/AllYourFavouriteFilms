package com.example.allyourfavouritefilms.view.RVadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allyourfavouritefilms.data.ApiConstants
import com.example.allyourfavouritefilms.databinding.FilmItemBinding
import com.example.allyourfavouritefilms.domain.Film


//СОЗДАЁМ АДАПТЕР РЕСАЙКЛЕРА

//в параметр передаем слушатель, чтобы мы потом могли обрабатывать нажатия из класса Activity
class FilmListRecyclerAdapter(private val clickListener: OnItemClickListener):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class FilmViewHolder(val binding: FilmItemBinding): RecyclerView.ViewHolder(binding.root)


    {
        //Привязываем View из layout к переменным
        private val title = binding.title
        private val poster = binding.poster
        private val description = binding.description
        //Вот здесь мы находим в верстке наш прогресс бар для рейтинга
        /*private val ratingDonut = binding.rating_donut*/
        private val ratingDonut = binding.ratingDonut

        //В этом методе кладем данные из Film в наши View
        fun bind(film: Film) {
            //Устанавливаем заголовок
            title.text = film.title
            //Устанавливаем постер
            //Указываем контейнер, в которм будет "жить" наша картинка
            Glide.with(itemView)
                //Загружаем сам ресурс
                .load(ApiConstants.IMAGES_URL + "w342" + film.poster)
                //Центруем изображение
                .centerCrop()
                //Указываем ImageView, куда будем загружать изображение
                .into(poster)
            //Устанавливаем описание
            description.text = film.description
            //Устанавливаем рэйтинг
            ratingDonut.setProgress((film.rating * 10).toInt())
        }

    }

    //Здесь у нас хранится список элементов для RV
    private val items = mutableListOf<Film>()

    //Этот метод нужно переопределить на возврат количества элементов в списке RV
    override fun getItemCount() = items.size


    //В этом методе мы привязываем наш ViewHolder и передаем туда "надутую" верстку нашего фильма
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }


    //В этом методе будет привязка полей из объекта Film к View из film_item.xml
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        //Проверяем, какой у нас ViewHolder
        when (holder) {
            is FilmViewHolder -> {
                //Вызываем метод bind(), который мы создали, и передаем туда объект
                //из нашей базы данных с указанием позиции
                holder.bind(items[position])
                //Обрабатываем нажатие на весь элемент целиком(можно сделать на отдельный элемент
                //например, картинку) и вызываем метод нашего листенера, который мы получаем из
                //конструктора адаптера
                holder.binding.itemContainer.setOnClickListener {
                    clickListener.click(items[position])
                }
            }
            }
        }

        //Метод для добавления объектов в наш список
        fun addItems(list: List<Film>) {
            //Сначала очищаем(если не реализовать DiffUtils)
            items.clear()
            //Добавляем
            items.addAll(list)
            //Уведомляем RV, что пришел новый список, и ему нужно заново все "привязывать"
            notifyDataSetChanged()
        }


        //Интерфейс для обработки кликов
    interface OnItemClickListener {
        fun click(film: Film)
    }
    }

