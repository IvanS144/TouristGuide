package com.mr.touristguide.core.presentation.data

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.mr.touristguide.core.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class ImagesViewModel @Inject constructor(
    imageRepository: ImageRepository
) : ViewModel() {
    val getAllImages = imageRepository.getImages()
}