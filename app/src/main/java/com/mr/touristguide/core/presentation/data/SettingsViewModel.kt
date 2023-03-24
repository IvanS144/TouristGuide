package com.mr.touristguide.core.presentation.data

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.touristguide.R
import com.mr.touristguide.core.data.preferences.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
private val preferencesRepository: PreferencesRepository,
private val app: Application
) : ViewModel(){
    var maxImagesState by mutableStateOf(10)
    private set

    var newsCachingState by mutableStateOf(true)
    private set

    var languageText by mutableStateOf("")
    private set

    val languagesList = listOf(Language(name="English", tag="en"), Language(name="Srpski", tag="sr"))

    init{
        viewModelScope.launch {
            maxImagesState = preferencesRepository.getMaxImages()
            newsCachingState = preferencesRepository.getNewsCachingEnabled()
//            val currentLanguage = app.applicationContext.getString(R.string.locale)
//            val locale = app.applicationContext.resources.configuration.locales.get(0).language
            val locale = preferencesRepository.getLocale()
            languageText = if(locale=="en"){
                "English"
            }
            else{
                "Srpski"
            }
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

    fun setLanguage(language: Language){
        languageText = language.name
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setLocale(language.tag)
        }
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(language.tag)
        )
    }
}
