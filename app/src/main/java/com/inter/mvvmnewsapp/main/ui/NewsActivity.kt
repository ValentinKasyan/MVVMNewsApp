package com.inter.mvvmnewsapp.main.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inter.mvvmnewsapp.R
import com.inter.mvvmnewsapp.databinding.ActivityNewsBinding
import com.inter.mvvmnewsapp.main.db.ArticleDatabase
import com.inter.mvvmnewsapp.main.repository.NewsRepository
import com.inter.mvvmnewsapp.main.ui.fragment.BreakingNewsFragment
import com.inter.mvvmnewsapp.main.ui.fragment.SavedNewsFragment
import com.inter.mvvmnewsapp.main.ui.fragment.SearchNewsFragment

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DebugLog", "onCreate start ")
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository=NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory=NewsViewModelProviderFactory(newsRepository)
        viewModel=ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        val breakingNewsFragment = BreakingNewsFragment()
        val savedNewsFragment = SavedNewsFragment()
        val searchNewsFragment = SearchNewsFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, breakingNewsFragment)
            commit()
        }

        fun setFragment(selectedFragment: Fragment) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, selectedFragment)
                addToBackStack(null)
                commit()
            }
        }

        binding.bottomNavigationMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.breakingNewsFragment -> setFragment(breakingNewsFragment)
                R.id.searchNewsFragment -> setFragment(searchNewsFragment)
                R.id.savedNewsFragment -> setFragment(savedNewsFragment)
            }
            true
        }
    }
}