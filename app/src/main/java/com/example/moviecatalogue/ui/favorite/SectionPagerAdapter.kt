package com.example.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviecatalogue.ui.favorite.fragment.FavoriteMovieFragment
import com.example.moviecatalogue.ui.favorite.fragment.FavoriteTvFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private var favoriteMovieFragment: FavoriteMovieFragment? = null
    private var favoriteTvFragment: FavoriteTvFragment? = null


    override fun getItemCount(): Int = 2

    fun setFragment(favoriteMovieFragment: FavoriteMovieFragment, favoriteTvFragment: FavoriteTvFragment) {
        this.favoriteMovieFragment = favoriteMovieFragment
        this.favoriteTvFragment = favoriteTvFragment
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = favoriteMovieFragment
            1 -> fragment = favoriteTvFragment
        }

        return fragment as Fragment
    }

}