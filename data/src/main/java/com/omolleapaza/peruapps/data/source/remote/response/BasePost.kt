package com.omolleapaza.peruapps.data.source.remote.response


data class BasePost(

    val hits: List<Hit>,
    val hitsPerPage: Int,
    val nbHits: Int,
    val nbPages: Int,
    val page: Int,
    val processingTimeMS: Int,
)