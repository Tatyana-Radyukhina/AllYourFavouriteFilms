package com.example.allyourfavouritefilms.view.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allyourfavouritefilms.view.RVadapter.FilmListRecyclerAdapter
import com.example.allyourfavouritefilms.view.MainActivity
import com.example.allyourfavouritefilms.R
import com.example.allyourfavouritefilms.view.RVadapter.TopSpacingItemDecoration
import com.example.allyourfavouritefilms.databinding.FragmentHomeBinding
import com.example.allyourfavouritefilms.domain.Film
import com.example.allyourfavouritefilms.utils.AnimationHelper
import com.example.allyourfavouritefilms.viewModel.HomeFragmentViewModel
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    private var filmsDataBase = listOf<Film>()
        //Используем backing field
        set(value) {
            //Если придет такое же значение, то мы выходим из метода
            if (field == value) return
            //Если пришло другое значение, то кладем его в переменную
            field = value
            //Обновляем RV адаптер
            filmsAdapter.addItems(field)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )

        initSearchView()

        //находим наш RV
        initRecycler()
        //Кладем нашу БД в RV
        viewModel.filmsListLiveData.observe(viewLifecycleOwner, Observer<List<Film>> {
            filmsDataBase = it
        })

    }

    private fun initSearchView() {
        //Подключаем слушателя изменений введенного текста в поиска
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Этот метод отрабатывает при нажатии кнопки "поиск" на софт клавиатуре
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            //Этот метод отрабатывает на каждое изменения текста
            override fun onQueryTextChange(newText: String?): Boolean {
                //Если ввод пуст то вставляем в адаптер всю БД
                if (newText!!.isEmpty()) {
                    filmsAdapter.addItems(filmsDataBase)
                    return true
                }

                //Фильтруем список на поиск подходящих сочетаний
                val result = filmsDataBase.filter {
                    //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                //Добавляем в адаптер
                filmsAdapter.addItems(result)
                return true
            }
        })

    }


        private fun initRecycler() {
            //находим наш RV
            binding.mainRecycler.apply {

                filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(film)
                        }
                    })
                //Присваиваем адаптер
                adapter = filmsAdapter
                //Присвои layoutmanager
                layoutManager = LinearLayoutManager(requireContext())
                //Применяем декоратор для отступов
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        }
//Кладем нашу БД в RV
        /*filmsAdapter.addItems(filmsDataBase)*/

}



