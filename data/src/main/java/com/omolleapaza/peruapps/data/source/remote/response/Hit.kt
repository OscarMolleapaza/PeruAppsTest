package com.omolleapaza.peruapps.data.source.remote.response


data class Hit(

    val author: String,
    val comment_text: String?,
    val created_at: String,
    val created_at_i: Int,
    val story_id: Int?,
    val story_title: String?,
    val story_url: String?,
    val url:String?,
    val title:String?,
    val objectID:String?
)