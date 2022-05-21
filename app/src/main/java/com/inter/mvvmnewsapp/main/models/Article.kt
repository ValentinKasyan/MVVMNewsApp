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
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable {

    //java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.hashCode()'
    // on a null object reference
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Article
        if (id != other.id) return false
        if (author != other.author) return false
        if (content != other.content) return false
        if (description != other.description) return false
        if (publishedAt != other.publishedAt) return false
        if (title != other.title) return false
        if (url != other.url) return false
        if (urlToImage != other.urlToImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        val const = 31
        result = const * result + title.hashCode()
        if (!author.isNullOrEmpty()) {
            result = const * result + author.hashCode()
        }
        if (!content.isNullOrEmpty()) {
            result = const * result + content.hashCode()
        }
        if (!description.isNullOrEmpty()) {
            result = const * result + description.hashCode()
        }
        if (!publishedAt.isNullOrEmpty()) {
            result = const * result + publishedAt.hashCode()
        }
        if (!title.isNullOrEmpty()) {
            result = const * result + title.hashCode()
        }
        if (!url.isNullOrEmpty()) {
            result = const * result + url.hashCode()
        }
        if (!urlToImage.isNullOrEmpty()) {
            result = const * result + urlToImage.hashCode()
        }
        return result
    }
}