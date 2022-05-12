package com.omolleapaza.peruapps.data.source.local.ds

import android.database.sqlite.SQLiteException
import com.omolleapaza.peruapps.data.source.local.db.dao.HistoryPostDeletedDao
import com.omolleapaza.peruapps.data.source.local.db.dao.PostDao
import com.omolleapaza.peruapps.data.source.local.db.entity.PostDeletedEntity
import com.omolleapaza.peruapps.data.source.local.db.entity.PostEntity
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure


class PostLocalDataSourceImpl(
    private val postDao: PostDao,
    private val historyPostDeletedDao: HistoryPostDeletedDao
): PostLocalDataSource {


    override suspend fun getAllPost(): Either<Failure, List<PostEntity>> {
        return try {
            Either.Success(postDao.getAllPost())
        }catch (exception: SQLiteException){
            Either.Error(Failure.None)
        }
    }

    override suspend fun saveListPosts(response: List<PostEntity>): Either<Failure, Unit> {
            return try {
            Either.Success(postDao.insertAll(response))
        }catch (exception: SQLiteException){
            Either.Error(Failure.None)
        }
    }

    override suspend fun deletePost(response: PostEntity): Either<Failure, Unit> {
            return try {
            Either.Success(postDao.deleteItem(response))
        }catch (exception: SQLiteException){
            Either.Error(Failure.None)
        }
    }

    override suspend fun deleteAllPost(): Either<Failure, Unit> {
            return try {
            Either.Success(postDao.deleteAll())
        }catch (exception: SQLiteException){
            Either.Error(Failure.None)
        }
    }

    override suspend fun savePostDeleted(response: PostDeletedEntity): Either<Failure, Unit> {
            return try {
            Either.Success(historyPostDeletedDao.insert(response))
        }catch (exception: SQLiteException){
            Either.Error(Failure.None)
        }
    }

    override suspend fun isPostDeleted(idPost: String): Boolean {
            return historyPostDeletedDao.isPostDeleted(idPost)
    }

    override suspend fun removeAllDataOld() {
        historyPostDeletedDao.removeOldData()
    }
}