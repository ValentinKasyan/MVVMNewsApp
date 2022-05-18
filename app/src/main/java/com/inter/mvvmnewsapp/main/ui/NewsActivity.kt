package com.inter.mvvmnewsapp.main.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
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
        val viewModelProviderFactory=NewsViewModelProviderFactory(application,newsRepository)
        viewModel=ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)as NavHostFragment
        binding.bottomNavigationMenu.setupWithNavController(navHostFragment.findNavController())

    }
}