package com.mr.touristguide.news.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mr.touristguide.news.data.remote.HeadlineDto
import com.mr.touristguide.news.data.remote.SourceDto

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "source_name")
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    @ColumnInfo(name = "url_to_image")
    val urlToImage: String?,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    val content: String?
) {
    fun toArticleDto(): HeadlineDto {
        return HeadlineDto(
            id = id,
            source = SourceDto(id = null, name = sourceName),
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
