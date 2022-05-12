package com.omolleapaza.peruapps.data.mapper


import com.omolleapaza.peruapps.data.extension.mapTo
import com.omolleapaza.peruapps.data.source.local.db.entity.PostDeletedEntity
import com.omolleapaza.peruapps.data.source.local.db.entity.PostEntity
import com.omolleapaza.peruapps.data.source.remote.response.BasePost
import com.omolleapaza.peruapps.domain.model.Post
import com.omolleapaza.peruapps.domain.model.PostDeleted
import java.util.*


class ListPostMapperImpl : ListPostMapper {

    override suspend fun mapPostResponseToModel(requests: BasePost): com.omolleapaza.peruapps.domain.model.BasePost {
        return com.omolleapaza.peruapps.domain.model.BasePost(
            listPost = requests.hits.map {

                Post(
                    author = it.author,
                    created_at = it.created_at,
                    story_id = (it.objectID?.toInt() ?: it.story_id ?: Date().time.toInt()),
                    story_url = it.story_url ?: it.url ?: "https://google.com",
                    story_title = it.story_title ?: it.title ?: "Sin titulo",
                    visible = true
                )
            },
            totalPages = requests.nbPages,
            actualPage = requests.page
        )

    }

    override suspend fun mapPostResponseToEntity(requests: BasePost): List<PostEntity> {
        return requests.hits.map {
            PostEntity(
                id = (it.objectID?.toInt() ?: it.story_id ?: Date().time).toString(),
                storyUrl = it.story_url ?: it.url ?: "https://google.com",
                storyTitle = it.story_title ?: it.title ?: "Sin titulo",
                createdAt = it.created_at,
                author = it.author,
                commentText = it.comment_text ?: "Sin comentarios"
            )
        }

    }

    override suspend fun mapPostDeletedToEntity(requests: PostDeleted): PostDeletedEntity {
        return requests.mapTo { PostDeletedEntity(id = it.id.toString(), createdAt = it.createdAt) }
    }

    override suspend fun mapEntityPostToModel(request: List<PostEntity>): com.omolleapaza.peruapps.domain.model.BasePost {
        return com.omolleapaza.peruapps.domain.model.BasePost(
            listPost = request.map {
                Post(
                    author = it.author,
                    created_at = it.createdAt,
                    story_id = it.id.toInt(),
                    story_title = it.storyTitle,
                    story_url = it.storyUrl,
                    visible = it.visible
                )
            },
            totalPages = 1,
            actualPage = 1
        )
    }


}