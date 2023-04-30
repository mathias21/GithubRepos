package com.batcuevasoft.core.githubRepo

import app.cash.turbine.test
import app.cash.turbine.testIn
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.batcuevasoft.MainDispatcherRule
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepository
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepositoryImpl
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoEntity
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoLocalDatasource
import com.batcuevasoft.githubrepo.data.remote.NetworkResponse
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasource
import com.batcuevasoft.ui.GithubRepoInstrumentation
import com.batcuevasoft.ui.GithubRepoInstrumentation.getGithubRepoEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepoRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var sut: GithubRepoRepository
    private val githubRepoRemoteDatasource: GithubRepoRemoteDatasource = mock()
    private val githubRepoLocalDatasource: GithubRepoLocalDatasource = mock()
    private lateinit var localFlow: MutableStateFlow<List<GithubRepoEntity>>

    @Before
    fun setup() {
        localFlow = MutableStateFlow(emptyList())
        whenever(githubRepoLocalDatasource.flow).thenReturn(localFlow)
        sut = GithubRepoRepositoryImpl(
            githubRepoLocalDatasource,
            githubRepoRemoteDatasource
        )
    }

    @Test
    fun `when starting the flow, repository fetches from remote`() = runTest {
        whenever(githubRepoLocalDatasource.flow).thenReturn(flowOf(emptyList()))
        whenever(githubRepoRemoteDatasource.fetchRepositories()).thenReturn(NetworkResponse.Success(emptyList()))

        sut.githubRepoListFlow.testIn(backgroundScope)
        verify(githubRepoRemoteDatasource, times(1)).fetchRepositories()
    }

    @Test
    fun `when starting the flow, repository fetches from remote and inserts the items received in local`() = runTest {
        whenever(githubRepoLocalDatasource.flow).thenReturn(flowOf(emptyList()))
        whenever(githubRepoLocalDatasource.insertGithubRepoEntity(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(
            getGithubRepoEntity()
        )
        whenever(githubRepoRemoteDatasource.fetchRepositories()).thenReturn(
            NetworkResponse.Success(
                listOf(
                    GithubRepoInstrumentation.getGithubRepoRemoteEntity(),
                    GithubRepoInstrumentation.getGithubRepoRemoteEntity()
                )
            )
        )

        sut.githubRepoListFlow.test {
            verify(githubRepoRemoteDatasource, times(1)).fetchRepositories()
            verify(githubRepoLocalDatasource, times(2)).insertGithubRepoEntity(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when starting the flow, repository maps local entities to models`() = runBlocking {
        val entity = getGithubRepoEntity()
        whenever(githubRepoRemoteDatasource.fetchRepositories()).thenReturn(NetworkResponse.Success(listOf()))

        sut.githubRepoListFlow.test {
            val firstItem = awaitItem()
            assertThat(firstItem.size).isEqualTo(0)
            localFlow.emit(listOf(entity))
            val newItem = awaitItem()
            assertThat(newItem.size).isEqualTo(1)
            assertThat(newItem.first()).isEqualTo(entity.toGithubRepo())
        }
    }
}