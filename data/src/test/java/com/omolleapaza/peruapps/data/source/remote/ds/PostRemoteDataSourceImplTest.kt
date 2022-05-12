package com.omolleapaza.peruapps.data.source.remote.ds

import com.omolleapaza.peruapps.data.source.remote.service.RestService
import com.omolleapaza.peruapps.data.utils.ConnectionUtils
import com.omolleapaza.peruapps.domain.utils.Failure
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.SocketTimeoutException

internal class PostRemoteDataSourceImplTest {

    private lateinit var postLocalRepo: PostRemoteDataSource

    @MockK(relaxed = true)
    private lateinit var restApi: RestService
    @MockK(relaxed = true)
    private lateinit var connectionUtils: ConnectionUtils

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        postLocalRepo = PostRemoteDataSourceImpl(restApi,connectionUtils)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(restApi, connectionUtils)
        clearStaticMockk()
    }

    @Test
    fun `get all post , should return Exception`() = runBlockingTest{
        //GIVEN
        coEvery {
            restApi.getPost(0)
        } throws  SocketTimeoutException()
        //WHEN
        val data=postLocalRepo.getPost(0)
        //THEN
        assertTrue(data.isError)

        data.either({
            assertTrue(Failure.NoNetworkDetected == it)
        },{
            assertTrue(false)
        })
    }


}