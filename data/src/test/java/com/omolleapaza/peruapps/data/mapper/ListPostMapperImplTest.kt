package com.omolleapaza.peruapps.data.mapper

import com.omolleapaza.peruapps.data.MockupsData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExperimentalCoroutinesApi
internal class ListPostMapperImplTest {
    private val listPostMapperImpl: ListPostMapper  = ListPostMapperImpl()


    @Test
    fun mapEntityPostToModel() = runBlockingTest{
        val listData =  listPostMapperImpl.mapPostResponseToModel(
            MockupsData.basePost
        )
        assertTrue(listData.actualPage == MockupsData.basePost.page)
        assertTrue(listData.listPost.size == MockupsData.basePost.hits.size)
    }

    @Test
    fun mapPostResponseToModel()  = runBlockingTest{

        val listData = listPostMapperImpl.mapEntityPostToModel(
            MockupsData.listPostEntity
        )
        assertTrue(listData.listPost.size == MockupsData.listPostEntity.size)

    }

    @Test
    fun mapEntityPostToModelEmpty() = runBlockingTest{
        val data = MockupsData.basePost.copy(
            hits = listOf()
        )
        val listData =  listPostMapperImpl.mapPostResponseToModel(
            data
        )
        assertTrue(listData.listPost.isEmpty())
    }

    @Test
    fun mapPostResponseToEmpty()  = runBlockingTest{

        val listData = listPostMapperImpl.mapEntityPostToModel(
            listOf()
        )
        assertTrue(listData.listPost.isEmpty())

    }
}