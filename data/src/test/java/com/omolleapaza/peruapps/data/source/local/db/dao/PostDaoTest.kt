package com.omolleapaza.peruapps.data.source.local.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.omolleapaza.peruapps.data.MockupsData
import com.omolleapaza.peruapps.data.source.local.db.PeruAppsDataBase
import com.omolleapaza.peruapps.data.utils.TestRobolectric
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotSame
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.*

@ExperimentalCoroutinesApi
class PostDaoTest: TestRobolectric()  {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: PeruAppsDataBase
    private lateinit var postDao: PostDao


    @Before
    fun setUp() = runBlockingTest {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room
            .inMemoryDatabaseBuilder(context, PeruAppsDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        postDao = database.getPostDao()
    }

    @After
    fun destroy() {
        database.close()
    }

    @Test
    fun `obtain post, should return empty`() = runBlockingTest {
        val posts = postDao.getAllPost()
        Assert.assertTrue(posts.isEmpty())
    }
    @Test
    fun `insert all post, should return data and equals data`()= runBlockingTest {
        postDao.insertAll(MockupsData.listPostEntity)
        val posts = postDao.getAllPost()
        assertEquals(MockupsData.listPostEntity.size, posts.size)
    }
    @Test
    fun `insert all post and obtain data, should return data not equals`()= runBlockingTest  {
        postDao.insertAll(MockupsData.listPostEntity)
        val posts = postDao.getAllPost()
        assertNotSame(posts.size, 0)
    }
    @Test
    fun `insert all post and delete one post, should return all data - 1 `() = runBlockingTest {
        postDao.insertAll(MockupsData.listPostEntity)
        val posts = postDao.getAllPost()
        assertEquals(MockupsData.listPostEntity.size, posts.size)
        postDao.deleteItem(MockupsData.listPostEntity[1])

        val postsLater = postDao.getAllPost()
        assertEquals(MockupsData.listPostEntity.size-1, postsLater.size)
    }
    @Test
    fun `insert all post an clear data, should return 0`() = runBlockingTest{
        postDao.insertAll(MockupsData.listPostEntity)
        val posts = postDao.getAllPost()
        assertEquals(MockupsData.listPostEntity.size, posts.size)
        postDao.deleteAll()
        val postsLater = postDao.getAllPost()
        assertEquals(0, postsLater.size)
    }


}