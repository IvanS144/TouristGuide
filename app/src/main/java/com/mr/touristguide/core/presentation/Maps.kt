package com.mr.touristguide.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.mr.touristguide.core.model.City
import com.mr.touristguide.core.model.Landmark

@Composable
fun CitiesMap(modifier: Modifier = Modifier, cities: List<City>?, onMarkerClick: (Int) -> Unit) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(44.04338, 17.78456), 17f)
    }
    if(cities!=null) {
        Column(modifier = modifier) {
            GoogleMap(
                cameraPositionState = cameraPositionState,
                modifier = Modifier.weight(1f),
                uiSettings = MapUiSettings(compassEnabled = true)
            ) {
                CityMarkers(cities, onMarkerClick)
            }
        }
    }

}

@Composable
fun CityMarkers(cities: List<City>, onMarkerClick: (Int) -> Unit) {
    for(city in cities) {
        Marker(
            state = rememberMarkerState(position = LatLng(city.latitude, city.longitude)),
            title = "Marker1",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
            onClick = {onMarkerClick(city.id);  false}
        )
    }
}

@Composable
fun LandmarksMap(modifier: Modifier = Modifier, landmarks: List<Landmark>?, onMarkerClick: (Int) -> Unit) {

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(44.04338, 17.78456), 17f)
    }
    if(landmarks!=null) {
        Column(modifier = modifier) {
            GoogleMap(
                cameraPositionState = cameraPositionState,
                modifier = Modifier.weight(1f),
                uiSettings = MapUiSettings(compassEnabled = true)
            ) {
                LandmarkMarkers(landmarks, onMarkerClick)
            }
        }
    }

}

@Composable
fun LandmarkMarkers(landmarks: List<Landmark>, onMarkerClick: (Int) -> Unit) {
    for(city in landmarks) {
        Marker(
            state = rememberMarkerState(position = LatLng(city.latitude, city.longitude)),
            title = "Marker1",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
            onClick = {onMarkerClick(city.id);  false}
        )
    }
}