package com.inter.mvvmnewsapp.main.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.inter.mvvmnewsapp.main.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase :RoomDatabase(){

    abstract fun getArticleDao():ArticleDao

    companion object{
        @Volatile
        private var instance:ArticleDatabase?=null
        private val LOCK=Any()

        //when initialize object
        //если instance null ,длокируем синхронизацию и не даем доступ к потоку затем
        // проверяем еще раз если instance null вызываем метод createDatabase ()
        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?:createDatabase(context).also{
                instance=it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}