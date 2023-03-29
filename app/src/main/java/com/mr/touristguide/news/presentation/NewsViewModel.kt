package com.mr.touristguide.news.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.touristguide.news.data.remote.HeadlineDto
import com.mr.touristguide.news.domain.repository.NewsRepository
import com.mr.touristguide.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    var state by mutableStateOf(NewsState())
        private set

    init {
        loadNews()
    }

    fun loadNews() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                isError = false
            )
            state = when (val result = repository.getNews()) {
                is Resource.Success -> {
                    state.copy(
                        news = result.data,
                        isLoading = false,
                        isError = false
                    )
                }
                is Resource.Error -> {
                    state.copy(
                        news = result.data,
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun getArticle(id: Int): HeadlineDto? {
        return state.news?.articles?.first { article -> article.id == id }
    }
}