package com.inter.mvvmnewsapp.main.models

import com.inter.mvvmnewsapp.main.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)