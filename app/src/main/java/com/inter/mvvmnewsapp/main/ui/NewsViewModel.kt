package com.inter.mvvmnewsapp.main.ui

import androidx.lifecycle.ViewModel
import com.inter.mvvmnewsapp.main.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
}