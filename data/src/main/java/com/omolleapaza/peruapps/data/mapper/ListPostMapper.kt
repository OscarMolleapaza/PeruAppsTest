package com.omolleapaza.peruapps.data.mapper

import com.omolleapaza.peruapps.data.source.local.db.entity.PostDeletedEntity
import com.omolleapaza.peruapps.data.source.local.db.entity.PostEntity
import com.omolleapaza.peruapps.domain.model.BasePost
import com.omolleapaza.peruapps.domain.model.PostDeleted


interface ListPostMapper {

    suspend fun mapPostResponseToModel(
            requests: com.omolleapaza.peruapps.data.source.remote.response.BasePost
    ): BasePost

    suspend fun mapPostResponseToEntity(
        requests: com.omolleapaza.peruapps.data.source.remote.response.BasePost
    ): List<PostEntity>

    suspend fun mapPostDeletedToEntity(
        requests: PostDeleted
    ): PostDeletedEntity

    suspend fun mapEntityPostToModel(
            request: List<PostEntity>
    ): BasePost




}