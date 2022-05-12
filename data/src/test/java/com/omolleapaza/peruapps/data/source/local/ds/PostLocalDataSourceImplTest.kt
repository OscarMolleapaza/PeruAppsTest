package com.omolleapaza.peruapps.data.source.local.ds

import android.database.sqlite.SQLiteException
import com.omolleapaza.peruapps.data.MockupsData
import com.omolleapaza.peruapps.data.source.local.db.dao.HistoryPostDeletedDao
import com.omolleapaza.peruapps.data.source.local.db.dao.PostDao
import com.omolleapaza.peruapps.domain.utils.Failure
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test



@ExperimentalCoroutinesApi
internal class PostLocalDataSourceImplTest {


    private lateinit var postLocalRepo:PostLocalDataSource
    @MockK(relaxed = true)
    private lateinit var postDao: PostDao
    @MockK(relaxed = true)
    private lateinit var historyDao: HistoryPostDeletedDao

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        postLocalRepo = PostLocalDataSourceImpl(postDao,historyDao)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(postDao, historyDao)
        clearStaticMockk()
    }

    @Test
    fun `get all post , should return Exception`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.getAllPost()
        } throws  SQLiteException("No se pudo eliminar")
        //WHEN
        val data=postLocalRepo.getAllPost()
        //THEN
        assertTrue(data.isError)
        coVerify(exactly = 1){
            postDao.getAllPost()
        }

        data.either({
            assertTrue(Failure.None == it)
        },{
            assertTrue(false)
        })
    }

    @Test
    fun `get all post , should return Success`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.getAllPost()
        } returns MockupsData.listPostEntity
        //WHEN
        val data=postLocalRepo.getAllPost()
        //THEN
        assertTrue(data.isSuccess)
        coVerify(exactly = 1){
            postDao.getAllPost()
        }

        data.either({
            assertTrue(false)

        },{
            assertTrue(it.isNotEmpty())
            assertTrue(it.size == MockupsData.listPostEntity.size )
        })
    }


    @Test
    fun `save List Post, should return Exception`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.insertAll(MockupsData.listPostEntity)
        } throws  SQLiteException("Error al guardar")
        //WHEN
        val data=postLocalRepo.saveListPosts(MockupsData.listPostEntity)
        //THEN
        assertTrue(data.isError)
        coVerify(exactly = 1){
            postDao.insertAll(MockupsData.listPostEntity)
        }

        data.either({
            assertTrue(Failure.None == it)
        },{
            assertTrue(false)
        })
    }

    @Test
    fun `save list post , should return Success`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.insertAll(MockupsData.listPostEntity)
        } returns Unit
        //WHEN
        val data=postLocalRepo.saveListPosts(MockupsData.listPostEntity)
        //THEN
        assertTrue(data.isSuccess)
        coVerify(exactly = 1){
            postDao.insertAll(MockupsData.listPostEntity)
        }

        data.either({
            assertTrue(false)

        },{
            assertTrue(true)
        })
    }

    @Test
    fun `delete Post, should return Exception`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.deleteItem(MockupsData.listPostEntity[0])
        } throws  SQLiteException("Error al borrar")
        //WHEN
        val data=postLocalRepo.deletePost(MockupsData.listPostEntity[0])
        //THEN
        assertTrue(data.isError)
        coVerify(exactly = 1){
            postDao.deleteItem(MockupsData.listPostEntity[0])
        }

        data.either({
            assertTrue(Failure.None == it)
        },{
            assertTrue(false)
        })
    }

    @Test
    fun `delete post , should return Success`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.deleteItem(MockupsData.listPostEntity[0])
        } returns Unit
        //WHEN
        val data=postLocalRepo.deletePost(MockupsData.listPostEntity[0])
        //THEN
        assertTrue(data.isSuccess)
        coVerify(exactly = 1){
            postDao.deleteItem(MockupsData.listPostEntity[0])
        }

        data.either({
            assertTrue(false)

        },{
            assertTrue(true)
        })
    }

    @Test
    fun `delete all Post, should return Exception`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.deleteAll()
        } throws  SQLiteException("Error al borrar todo")
        //WHEN
        val data=postLocalRepo.deleteAllPost()
        //THEN
        assertTrue(data.isError)
        coVerify(exactly = 1){
            postDao.deleteAll()
        }

        data.either({
            assertTrue(Failure.None == it)
        },{
            assertTrue(false)
        })
    }

    @Test
    fun `delete all post , should return Success`() = runBlockingTest{
        //GIVEN
        coEvery {
            postDao.deleteAll()
        } returns Unit
        //WHEN
        val data=postLocalRepo.deleteAllPost()
        //THEN
        assertTrue(data.isSuccess)
        coVerify(exactly = 1){
            postDao.deleteAll()
        }

        data.either({
            assertTrue(false)

        },{
            assertTrue(true)
        })
    }


    @Test
    fun `save post delete , should return Exception`() = runBlockingTest{
        //GIVEN
        coEvery {
            historyDao.insert(MockupsData.listPostDeletedEntity[0])
        } throws  SQLiteException("Error al borrar")
        //WHEN
        val data=postLocalRepo.savePostDeleted(MockupsData.listPostDeletedEntity[0])
        //THEN
        assertTrue(data.isError)
        coVerify(exactly = 1){
            historyDao.insert(MockupsData.listPostDeletedEntity[0])
        }

        data.either({
            assertTrue(Failure.None == it)
        },{
            assertTrue(false)
        })
    }

    @Test
    fun `save post delete  , should return Success`() = runBlockingTest{
        //GIVEN
        coEvery {
            historyDao.insert(MockupsData.listPostDeletedEntity[0])
        } returns Unit
        //WHEN
        val data=postLocalRepo.savePostDeleted(MockupsData.listPostDeletedEntity[0])
        //THEN
        assertTrue(data.isSuccess)
        coVerify(exactly = 1){
            historyDao.insert(MockupsData.listPostDeletedEntity[0])
        }

        data.either({
            assertTrue(false)

        },{
            assertTrue(true)
        })
    }

    @Test
    fun `is Post Deleted , should return Exception`() = runBlockingTest{
        //GIVEN
        coEvery {
            historyDao.isPostDeleted(MockupsData.listPostDeletedEntity[0].id)
        } throws  SQLiteException("Error al borrar")
        //WHEN
        val data=postLocalRepo.isPostDeleted(MockupsData.listPostDeletedEntity[0].id)
        //THEN
        assertTrue(!data)
        coVerify(exactly = 1){
            historyDao.isPostDeleted(MockupsData.listPostDeletedEntity[0].id)
        }

    }

    @Test
    fun `is Post Deleted   , should return Success`() = runBlockingTest{
        //GIVEN
        coEvery {
            historyDao.isPostDeleted(MockupsData.listPostDeletedEntity[0].id)
        } returns true
        //WHEN
        val data=postLocalRepo.isPostDeleted(MockupsData.listPostDeletedEntity[0].id)
        //THEN
        assertTrue(data)
        coVerify(exactly = 1){
            historyDao.isPostDeleted(MockupsData.listPostDeletedEntity[0].id)
        }


    }

}