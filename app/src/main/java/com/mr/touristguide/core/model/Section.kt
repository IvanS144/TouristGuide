package com.mr.touristguide.core.model

data class Section(
    val title: String,
    val text: String?,
    val sections: List<Section>?
)
