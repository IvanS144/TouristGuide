package com.mr.touristguide.core.data.mappers

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.mr.touristguide.core.data.remote.LandmarkDto
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.core.model.Section
import com.mr.touristguide.ui.theme.h1
import com.mr.touristguide.ui.theme.h2
import com.mr.touristguide.ui.theme.textSpanNormal

//fun mapToCities(cityDtos: List<CityDto>): List<City> {
//    return cityDtos.map { cityDto ->
//        City(
//            id = cityDto.id,
//            name = cityDto.name,
//            shortDescription = cityDto.shortDescription,
//            mainDescription = cityDto.mainDescription,
//            latitude = cityDto.latitude,
//            longitude = cityDto.longitude,
//            searchTerm = cityDto.searchTerm,
//            videoUrl = cityDto.videoUrl,
//            sections = cityDto.sections,
//            flagUrl = cityDto.flagUrl
//        )
//    }.toList()
//}

//fun mapToLandmarks(landmarkDtos: List<LandmarkDto>): List<Landmark> {
//    return landmarkDtos.map { landmarkDto ->
//        Landmark(
//            id = landmarkDto.id,
//            name = landmarkDto.name,
//            shortDescription = landmarkDto.shortDescription,
//            mainDescription = landmarkDto.mainDescription,
//            latitude = landmarkDto.latitude,
//            longitude = landmarkDto.longitude,
//            sections = landmarkDto.sections
//        )
//    }
//}

fun sectionsToAnnotatedString(sections: List<Section>): AnnotatedString {
    var string = buildAnnotatedString { append("") }
    for (section in sections) {
        string = string.plus(
            AnnotatedString(
                text = section.title,
                spanStyle = h1
            )
        )
        if (section.text != null) {
            string = buildAnnotatedString { append(string); append("\n") }
            string = string.plus(
                AnnotatedString(
                    text = section.text,
                    spanStyle = textSpanNormal
                )
            )
        }
        if (section.sections != null && section.sections.isNotEmpty()) {
            string = buildAnnotatedString { append(string); append("\n\n") }
            for (subsection in section.sections) {
                string = string.plus(
                    AnnotatedString(
                        text = subsection.title,
                        spanStyle = h2
                    )
                )
                if (subsection.text != null) {
                    string = buildAnnotatedString { append(string); append("\n") }
                    string = string.plus(
                        AnnotatedString(
                            text = subsection.text,
                            spanStyle = textSpanNormal
                        )
                    )
                }
                string = string.plus(
                    AnnotatedString(
                        text = "\n\n"
                    )
                )
            }
        }
        string = string.plus(
            AnnotatedString(
                text = "\n\n"
            )
        )
    }
    return string
}