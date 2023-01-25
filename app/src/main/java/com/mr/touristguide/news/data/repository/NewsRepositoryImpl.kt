package com.mr.touristguide.news.data.repository

import com.mr.touristguide.news.data.remote.NewsApi
import com.mr.touristguide.news.data.remote.NewsDto
import com.mr.touristguide.news.domain.repository.NewsRepository
import com.mr.touristguide.util.Resource
import java.util.UUID
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getNews(): Resource<NewsDto> {
        return try{
            Resource.Success(processHeadlines(api.getNews()))
        }
        catch(e: Exception){
            e.printStackTrace()
            Resource.Error("An error occurred")
        }
    }

    private fun processHeadlines(news: NewsDto): NewsDto{
        news.articles.forEach{headline -> headline.id=UUID.randomUUID().toString()}
        return news
    }
}