package com.inter.mvvmnewsapp.main.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "article"
)
data class Article(
    //add id for DB
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    //add nullable
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
):Serializable