package com.inter.mvvmnewsapp.main.ui

import android.app.DownloadManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inter.mvvmnewsapp.main.models.Article
import com.inter.mvvmnewsapp.main.models.NewsResponse
import com.inter.mvvmnewsapp.main.repository.NewsRepository
import com.inter.mvvmnewsapp.main.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    // TODO: implement logic
    var breakingNewsPage = 1
    var breakingNewsResponse:NewsResponse?=null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse:NewsResponse?=null

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery:String)=viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response=newsRepository.getsSearchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                //pagination
                breakingNewsPage++
                //first page loading from api
                if(breakingNewsResponse==null){
                    breakingNewsResponse=resultResponse
                }else{
                    //other pages loading
                    val oldArticle=breakingNewsResponse?.articles
                    val newArticle=resultResponse.articles
                    oldArticle?.addAll(newArticle)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                //pagination
                searchNewsPage++
                //first page loading from api
                if(searchNewsResponse==null){
                    searchNewsResponse=resultResponse
                }else{
                    //other pages loading
                    val oldArticle=searchNewsResponse?.articles
                    val newArticle=resultResponse.articles
                    oldArticle?.addAll(newArticle)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews()=newsRepository.getSavedNews()

    fun  deleteArticle(article: Article)=viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

}