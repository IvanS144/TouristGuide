package com.mr.touristguide.news.data.repository

import androidx.room.withTransaction
import com.mr.touristguide.news.data.database.ArticlesDatabase
import com.mr.touristguide.news.data.remote.NewsApi
import com.mr.touristguide.news.data.remote.NewsDto
import com.mr.touristguide.news.domain.repository.NewsRepository
import com.mr.touristguide.util.Resource
import com.mr.touristguide.util.networkBoundResource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val database: ArticlesDatabase
) : NewsRepository {
    private val articleDao = database.articleDao()
    override suspend fun getNews(): Resource<NewsDto> {
        var news: NewsDto = NewsDto()
        val articles = networkBoundResource(
            query ={articleDao.getAllAsFlow()},
            fetch = {news = api.getNews()
                news.articles.map { headlineDto -> headlineDto.toArticle() } },
            saveFetchResult = {articles ->
                database.withTransaction {
                    articleDao.clearData()
                    articleDao.insert(articles)
                }
            }
        )
        articles.first().data?.let { news.articles = it.map { article -> article.toArticleDto() } }
        return Resource.Success(news)
//        return try {
//            val news = api.getNews()
//            val articles = news.articles.map { headlineDto -> headlineDto.toArticle() }
//            database.withTransaction {
//                articleDao.clearData()
//                articleDao.insert(articles)
//            }
//            news.articles = articleDao.findAll().map { article -> article.toArticleDto() }
//            Resource.Success(news)
//        } catch(e: Exception){
//            val news = NewsDto()
//            news.articles = articleDao.findAll().map { article -> article.toArticleDto() }
//            Resource.Success(news)
//        }
    }

//    private fun processHeadlines(news: NewsDto): NewsDto{
//        news.articles.forEach{headline -> headline.id=UUID.randomUUID().toString()}
//        return news
//    }
}