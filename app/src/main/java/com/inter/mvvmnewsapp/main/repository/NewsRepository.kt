package com.inter.mvvmnewsapp.main.repository

import com.inter.mvvmnewsapp.main.api.RetrofitInstance
import com.inter.mvvmnewsapp.main.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
}