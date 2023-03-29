package com.mr.touristguide.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mr.touristguide.core.data.remote.ImagesApi
import com.mr.touristguide.core.data.remote.UnsplashImage

class SearchPagingSource(
    private val imagesApi: ImagesApi,
    private val query: String,
    private val perPage: Int,
    private val maxImages: Int
) : PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: 1
        return try {
            val response = imagesApi.searchImages(term = query, perPage = perPage)
            val endOfPaginationReached = if (maxImages > 0) {
                (currentPage * perPage >= maxImages) || response.results.isEmpty()
            } else {
                response.results.isEmpty();
            }
            if (response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }

}