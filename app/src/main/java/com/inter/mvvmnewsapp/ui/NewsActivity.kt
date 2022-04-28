package com.inter.mvvmnewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.inter.mvvmnewsapp.R
import com.inter.mvvmnewsapp.databinding.ActivityNewsBinding
import com.inter.mvvmnewsapp.ui.fragment.BreakingNewsFragment
import com.inter.mvvmnewsapp.ui.fragment.SavedNewsFragment
import com.inter.mvvmnewsapp.ui.fragment.SearchNewsFragment

class NewsActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DebugLog", "onCreate start ")
        super.onCreate(savedInstanceState)
        binding= ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val breakingNewsFragment=BreakingNewsFragment()
        val savedNewsFragment=SavedNewsFragment()
        val searchNewsFragment=SearchNewsFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,breakingNewsFragment)
            commit()
        }

        fun setFragment(selectedFragment:Fragment){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment,selectedFragment)
                addToBackStack(null)
                commit()
            }
        }

        binding.bottomNavigationMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.breakingNewsFragment ->setFragment(breakingNewsFragment)
                R.id.searchNewsFragment -> setFragment(searchNewsFragment)
                R.id.savedNewsFragment ->setFragment(savedNewsFragment)
            }
            true
        }




    }
}