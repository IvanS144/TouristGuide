package com.mr.touristguide.news.data.remote

import com.mr.touristguide.news.data.database.ArticleEntity
import java.util.UUID

data class HeadlineDto(
    var id: Int?,
    val source: SourceDto,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
){
    fun toArticle(): ArticleEntity{
        return ArticleEntity(
            id = id,
            sourceName = source.name,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt,
            content = content
        )
    }
}
