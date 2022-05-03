package com.inter.mvvmnewsapp.main.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inter.mvvmnewsapp.main.models.Article

@Dao
interface ArticleDao {
    //OnConflictStrategy describes what will happen
    // if such an article is already in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    //returns all available articles
    @Query("SELECT*FROM article")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}