package com.example.pelmorexassessment.ui.gallery

data class GalleryItemDisplayModel(
    val type: ComponentType,
    val description: String? = null,
    val path: Int? = null,
)


enum class ComponentType {
    IMAGE_ITEM,
}
