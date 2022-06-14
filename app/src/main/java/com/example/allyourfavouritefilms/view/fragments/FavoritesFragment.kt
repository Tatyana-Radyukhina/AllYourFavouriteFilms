package com.example.allyourfavouritefilms.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allyourfavouritefilms.view.RVadapter.FilmListRecyclerAdapter
import com.example.allyourfavouritefilms.view.MainActivity
import com.example.allyourfavouritefilms.view.RVadapter.TopSpacingItemDecoration
import com.example.allyourfavouritefilms.databinding.FragmentFavoritesBinding
import com.example.allyourfavouritefilms.domain.Film
import com.example.allyourfavouritefilms.utils.AnimationHelper

class FavoritesFragment: Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding.favoritesFragmentRoot, requireActivity(), 1)

        //Получаем список при транзакции фрагмента
        val favoritesList: List<Film> = emptyList()

        binding.favoritesRecycler
            .apply {
                filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(film)
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
        filmsAdapter.addItems(favoritesList)
    }
}