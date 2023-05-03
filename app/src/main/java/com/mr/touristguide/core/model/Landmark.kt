package com.mr.touristguide.core.model

import com.mr.touristguide.core.data.remote.UnsplashImage

data class Landmark(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val mainDescription: String,
    val latitude: Double,
    val longitude: Double,
    var isFavorite: Boolean = false,
    val sections: List<Section>,
    val searchTerm: String?,
    var image: UnsplashImage?,
    val photoId: String
) {
//    fun getDescription(): AnnotatedString {
//        var string = buildAnnotatedString { append("") }
//        for (section in sections) {
//            string = string.plus(
//                AnnotatedString(
//                    text = section.title,
//                    spanStyle = h1
//                )
//            )
//            if (section.text != null) {
//                string = buildAnnotatedString { append(string); append("\n") }
//                string = string.plus(
//                    AnnotatedString(
//                        text = section.text,
//                        spanStyle = textSpanNormal
//                    )
//                )
//            }
//            if (section.sections != null && section.sections.isNotEmpty()) {
//                string = buildAnnotatedString { append(string); append("\n\n") }
//                for (subsection in section.sections) {
//                    string = string.plus(
//                        AnnotatedString(
//                            text = subsection.title,
//                            spanStyle = h2
//                        )
//                    )
//                    if (subsection.text != null) {
//                        string = buildAnnotatedString { append(string); append("\n") }
//                        string = string.plus(
//                            AnnotatedString(
//                                text = subsection.text,
//                                spanStyle = textSpanNormal
//                            )
//                        )
//                    }
//                    string = string.plus(
//                        AnnotatedString(
//                            text = "\n\n"
//                        )
//                    )
//                }
//            }
//            string = string.plus(
//                AnnotatedString(
//                    text = "\n\n"
//                )
//            )
//        }
//        return string
//    }
}
