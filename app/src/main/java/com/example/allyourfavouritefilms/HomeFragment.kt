package com.example.allyourfavouritefilms

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private val filmsDataBase = listOf(
        Film(
            "The day after tomorrow",
            R.drawable.thedayaftertomorrow,
            "Jack Hall, paleoclimatologist, must make a daring trek from Washington, D.C. to New York City to reach his son, trapped in the cross-hairs of a sudden international storm which plunges the planet into a new Ice Age."
        ),
        Film(
            "Marley & Me",
            R.drawable.marly,
            "A family learns important life lessons from their adorable, but naughty and neurotic dog."
        ),
        Film(
            "What Happens in Vegas",
            R.drawable.vegas,
            "A man and a woman are compelled, for legal reasons, to live life as a couple for a limited period of time. At stake is a large amount of money."
        ),
        Film(
            "The Hangover",
            R.drawable.vegas_2,
            "Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding."
        ),
        Film(
            "Venom: Let There Be Carnage",
            R.drawable.venom,
            "Eddie Brock attempts to reignite his career by interviewing serial killer Cletus Kasady, who becomes the host of the symbiote Carnage and escapes prison after a failed execution."
        ),
        Film(
            "In the Heart of the Sea",
            R.drawable.img,
            "A recounting of a New England whaling ship's sinking by a giant whale in 1820, an experience that later inspired the great novel Moby-Dick."
        ),
        Film(
            "The Devil Wears Prada",
            R.drawable.devilwearsprada,
            "A smart but sensible new graduate lands a job as an assistant to Miranda Priestly, the demanding editor-in-chief of a high fashion magazine."
        ),
        Film(
            "The Amityville Horror",
            R.drawable.amitivillie,
            "Newlyweds are terrorized by demonic forces after moving into a large house that was the site of a grisly mass murder a year before."
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //находим наш RV
        main_recycler.apply {

            filmsAdapter = FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener{
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
//Кладем нашу БД в RV
        filmsAdapter.addItems(filmsDataBase)
    }
}



