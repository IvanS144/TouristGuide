package com.mr.touristguide.core.presentation.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mr.touristguide.core.data.preferences.PreferencesRepository
import com.mr.touristguide.core.data.remote.UnsplashImage
import com.mr.touristguide.core.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedImages = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchedImages = _searchedImages

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun search(query: String = searchQuery.value) {
        viewModelScope.launch(Dispatchers.IO) {
            val maxImages = preferencesRepository.getMaxImages()
            imageRepository.searchImages(term = query, pageSize = 10, maxImages = maxImages)
                .cachedIn(viewModelScope).collect {
                _searchedImages.value = it
            }
        }
    }

}