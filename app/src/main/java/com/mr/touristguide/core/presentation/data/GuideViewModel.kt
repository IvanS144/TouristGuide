package com.mr.touristguide.core.presentation.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.touristguide.core.data.preferences.PreferencesRepository
import com.mr.touristguide.core.domain.repository.CityRepository
import com.mr.touristguide.core.domain.repository.CountryRepository
import com.mr.touristguide.core.domain.repository.LandmarkRepository
import com.mr.touristguide.core.model.Country
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val landmarkRepository: LandmarkRepository,
    private val countryRepository: CountryRepository,
    ) : ViewModel(){
//    private val api: CitiesApi = Retrofit.Builder()
//        .baseUrl("https://63cacd0ff36cbbdfc76091ca.mockapi.io")
//        .addConverterFactory(MoshiConverterFactory.create())
//        .build()
//        .create()
//    private val repository = CityRepositoryImpl(api)
    var citiesState by mutableStateOf(CitiesState())
    private set
    var landmarksState by mutableStateOf(LandmarksState())
    private set
    var country : MutableState<Country?> = mutableStateOf(null)

    var favoriteLandmarks : MutableState<List<Landmark>?> = mutableStateOf(null)
    private set

    init{
        loadCountry()
        loadCities()
        loadLandmarks()
    }

    fun loadCities(){
        viewModelScope.launch {
            citiesState = citiesState.copy(
                isLoading = true,
                isError = false
            )
            citiesState = when(val result = cityRepository.getCities()){
                is Resource.Success -> {
                    citiesState.copy(
                        cities = result.data,
                        isLoading = false,
                        isError = false
                    )
                }
                is Resource.Error ->{
                    citiesState.copy(
                        cities = result.data,
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun loadLandmarks(){
        viewModelScope.launch {
            landmarksState = landmarksState.copy(
                isLoading = true,
                isError = false
            )
            landmarksState = when(val result = landmarkRepository.getLandmarks()){
                is Resource.Success -> {
                    landmarksState.copy(
                        landmarks = result.data,
                        isLoading = false,
                        isError = false
                    )
                }
                is Resource.Error ->{
                    landmarksState.copy(
                        landmarks = result.data,
                        isLoading = false,
                        isError = true
                    )
                }
            }
        }
    }

    fun loadCountry(){
        viewModelScope.launch {
            country.value = countryRepository.getCountry()
        }
    }

    fun loadFavoriteLandmarks(){
        viewModelScope.launch {
            favoriteLandmarks.value = landmarkRepository.getFavoriteLandmarks()
        }
    }

    fun addToFavoriteLandmarks(id: Int){
        viewModelScope.launch {
            landmarkRepository.addToFavoriteLandmarks(id)
            favoriteLandmarks.value = landmarkRepository.getFavoriteLandmarks()
        }
    }

    fun removeFromFavoriteLandmarks(id: Int){
        viewModelScope.launch {
            landmarkRepository.removeFromFavoriteLandmarks(id)
            favoriteLandmarks.value = landmarkRepository.getFavoriteLandmarks()
        }
    }
}