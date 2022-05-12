package com.omolleapaza.peruapps.domain.model


data class BasePost(
    val listPost: List<Post>,
    val totalPages: Int,
    val actualPage: Int
)