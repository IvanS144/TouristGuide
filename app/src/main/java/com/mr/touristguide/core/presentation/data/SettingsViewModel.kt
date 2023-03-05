package com.mr.touristguide.core.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.touristguide.core.data.preferences.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
private val preferencesRepository: PreferencesRepository
) : ViewModel(){
    var maxImagesState by mutableStateOf(10)
    private set

    var newsCachingState by mutableStateOf(true)
    init{
        viewModelScope.launch {
            maxImagesState = preferencesRepository.getMaxImages()
            newsCachingState = preferencesRepository.getNewsCachingEnabled()
        }
    }
    fun setMaxImages(maxImages: Int){
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setMaxImages(maxImages)
        }
        maxImagesState=maxImages
    }

    fun setNewsCaching(newsCaching: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setNewsCachingEnabled(newsCaching)
        }
        newsCachingState = newsCaching
    }
}
