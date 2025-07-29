package com.example.amphibians.data

import kotlinx.serialization.Serializable

@Serializable
data class Amphibians(
    val description: String,
    val img_src: String,
    val name: String,
    val type: String
)