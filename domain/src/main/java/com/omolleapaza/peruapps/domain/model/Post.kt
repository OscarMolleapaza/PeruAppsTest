package com.omolleapaza.peruapps.domain.model


data class Post(

    val author: String,
    val created_at: String,
    val story_id: Int,

    val story_title: String,
    val story_url: String,
    var visible :Boolean = true,
)
