package com.example.allyourfavouritefilms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment: Fragment() {
    private lateinit var film: Film

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    /*var favoritesList: List<Film> = listOf()*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesFragment()

        //Получаем список при транзакции фрагмента
        var favoritesList: List<Film> = listOf()

        favorites_recycler
            .apply {
                filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
                    override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchFavoritesFragment(film)
                    }
                })
                //Присваиваем адаптер
                adapter = filmsAdapter

                //Присвоим layoutmanager
                layoutManager = LinearLayoutManager(requireContext())

                //Применяем декоратор для отступов
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }

        //Кладем нашу БД в RV
        if (film.isInFavorites) {
            filmsAdapter.addItems(favoritesList)
            }
        }

        fun favoritesFragment(){
            val film:Film = arguments?.getParcelable<Film>("film") as Film

        //Устанавливаем заголовок
        details_toolbar.title = film.title
        //Устанавливаем картинку
        details_poster.setImageResource(film.poster)
        //Устанавливаем описание
        details_description.text = film.description
    }


}