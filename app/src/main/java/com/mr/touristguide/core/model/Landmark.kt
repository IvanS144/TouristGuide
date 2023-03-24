package com.mr.touristguide.core.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp

data class Landmark(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val mainDescription: String,
    val latitude: Double,
    val longitude: Double,
    var isFavorite: Boolean = false,
    val sections: List<Section>
){
    fun getDescription(): AnnotatedString{
        var string = buildAnnotatedString { append("") }
        for(section in sections){
            string = string.plus(
                AnnotatedString(
                    text = section.title,
                    spanStyle = SpanStyle(fontSize = 22.sp)
                )
            )
            string = buildAnnotatedString { append(string); append("\n") }
            if(section.text!=null){
                string = string.plus(
                    AnnotatedString(
                        text = section.text,
                        spanStyle = SpanStyle(fontSize = 18.sp)
                    )
                )
            }
            if(section.sections!=null && section.sections.isNotEmpty()){
                string = buildAnnotatedString { append(string); append("\n\n") }
                for(subsection in section.sections){
                    string = string.plus(
                        AnnotatedString(
                            text = subsection.title,
                            spanStyle = SpanStyle(fontSize = 20.sp)
                        )
                    )
                    string = buildAnnotatedString { append(string); append("\n") }
                    if(section.text!=null){
                        string = string.plus(
                            AnnotatedString(
                                text = section.text,
                                spanStyle = SpanStyle(fontSize = 18.sp)
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
}
