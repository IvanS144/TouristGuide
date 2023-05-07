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
        val nextPage = params.key ?: 1
        return try {
            val response = imagesApi.searchImages(term = query, perPage = perPage, page = nextPage)
            val endOfPaginationReached = if (maxImages > 0) {
                if(nextPage * perPage >= maxImages)
                    true
                else response.results.isEmpty()
            } else {
                response.results.isEmpty();
            }
            if (response.results.isNotEmpty()) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (endOfPaginationReached) null else nextPage + 1
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