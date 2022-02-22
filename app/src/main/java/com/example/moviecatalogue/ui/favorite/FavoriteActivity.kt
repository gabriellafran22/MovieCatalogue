package com.example.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding
import com.example.moviecatalogue.ui.favorite.fragment.FavoriteMovieFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding
    private val favoriteMovieFragment = FavoriteMovieFragment()
    private val favoriteTvFragment = FavoriteMovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        setTabLayout()
    }

    private fun setTabLayout() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        sectionPagerAdapter.setFragment(favoriteMovieFragment, favoriteTvFragment)

        val viewPager: ViewPager2 = activityFavoriteBinding.viewPager
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = activityFavoriteBinding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.menu_movies,
            R.string.menu_tv_shows
        )
    }
}