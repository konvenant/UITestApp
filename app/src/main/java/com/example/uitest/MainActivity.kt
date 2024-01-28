package com.example.uitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.uitest.fragments.GameFragment
import com.example.uitest.fragments.ProfileFragment
import com.example.uitest.fragments.ReferAndEarnFragment
import com.example.uitest.fragments.RewardFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var fragmentManager: FragmentManager
    lateinit var bottomNavigationView: LinearLayout
    lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    fragmentManager = supportFragmentManager

        val homeFragment = GameFragment()

        currentFragment = homeFragment
        fragmentManager.beginTransaction()
            .replace(R.id.navHostFragment, homeFragment)
            .commit()

        bottomNavigationView = findViewById(R.id.bottomNav)

        bottomNavigationView.children.forEachIndexed { index, view ->
           view.setOnClickListener {
               when(index) {
                   0 -> switchFragment(GameFragment(),index)
                   1 -> switchFragment(RewardFragment(),index)
                   2 -> switchFragment(GameFragment(),index)
                   3 -> switchFragment(ReferAndEarnFragment(),index)
                   4 -> switchFragment(ProfileFragment(),index)
               }
           }
        }


    }

   private fun switchFragment(fragment: Fragment, selectedIndex: Int){
       if (fragment != currentFragment){
           fragmentManager.beginTransaction()
               .replace(R.id.navHostFragment,fragment)
               .commit()
           currentFragment = fragment

           updateConvexShapePosition(selectedIndex)
           updateIconForSelectedItem(selectedIndex)
       }
   }

    private fun updateConvexShapePosition(selectedIndex: Int) {
      val convexShape = findViewById<View>(R.id.convexShape)
        val item = findViewById<LinearLayout>(R.id.bottomNav)
        val itemWidth = item.width.toFloat() / item.childCount

        val translationX = (itemWidth * selectedIndex) + (itemWidth/2) - (convexShape.width/2)
        convexShape.translationX = translationX
    }

    private fun updateIconForSelectedItem(selectedIndex: Int){

       val homeIcon = findViewById<ImageView>(R.id.home_img)
       val  starIcon = findViewById<ImageView>(R.id.stars)
        val gameIcon = findViewById<ImageView>(R.id.game_img)
        val leaderIcon = findViewById<ImageView>(R.id.leaderboard_img)
        val profileIcon = findViewById<ImageView>(R.id.user_img)


        val selectedSizeFactor = 1.2f
        val unselectedSizeFactor = 1.0f

        homeIcon.setImageResource(if (selectedIndex == 0) R.drawable.group_43948 else R.drawable.unselected_home)
        starIcon.setImageResource(if (selectedIndex == 1) R.drawable.group_439482 else R.drawable.stars_solid)
        gameIcon.setImageResource(if (selectedIndex == 2) R.drawable.game else R.drawable.game)
        leaderIcon.setImageResource(if (selectedIndex == 3) R.drawable.selected_leaderboard else R.drawable.unselected_leadeboard)
        profileIcon.setImageResource(if (selectedIndex == 4) R.drawable.group_43980 else R.drawable.user)

        homeIcon.scaleX = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor
        homeIcon.scaleY = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor

        starIcon.scaleX = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor
        starIcon.scaleY = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor

        gameIcon.scaleX = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor
        gameIcon.scaleY = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor

        leaderIcon.scaleX = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor
        leaderIcon.scaleY = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor


        profileIcon.scaleX = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor
        profileIcon.scaleY = if (selectedIndex == 0) selectedSizeFactor else unselectedSizeFactor
    }

}