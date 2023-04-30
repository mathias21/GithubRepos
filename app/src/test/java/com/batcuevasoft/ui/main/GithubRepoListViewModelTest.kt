package com.batcuevasoft.ui.main

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.batcuevasoft.MainDispatcherRule
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepository
import com.batcuevasoft.githubrepo.ui.main.GithubRepoListViewModel
import com.batcuevasoft.ui.GithubRepoInstrumentation
import com.batcuevasoft.ui.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GithubRepoListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var sut: GithubRepoListViewModel
    private lateinit var githubRepoRepository: FakeGithubRepoRepository
    private lateinit var mutableStateFlow: MutableStateFlow<List<GithubRepo>>
    private val testDispatcherProvider = TestDispatcherProvider()

    @Before
    fun setup() {
        githubRepoRepository = FakeGithubRepoRepository()
        mutableStateFlow = MutableStateFlow(emptyList())
        sut = GithubRepoListViewModel(
            testDispatcherProvider,
            githubRepoRepository
        )
    }

    @Test
    fun `when repository emits no content, VM emits data loaded with empty content`() = runTest {
        sut.githubRepoDetailsState.test {
            val loadingItem = awaitItem()
            assert(loadingItem is GithubRepoListViewModel.GithubRepoListState.Loading)
            githubRepoRepository.emitList(listOf())
            val dataLoadedItem = awaitItem()
            assertThat((dataLoadedItem as GithubRepoListViewModel.GithubRepoListState.DataLoaded).githubRepoList).isEmpty()
        }
    }

    @Test
    fun `when repository emits a list of GithubRepos, VM emits data loaded with the correct content and sorted by stars`() = runTest {
        sut.githubRepoDetailsState.test {
            val loadingItem = awaitItem()
            assert(loadingItem is GithubRepoListViewModel.GithubRepoListState.Loading)
            githubRepoRepository.emitList(
                listOf(
                    GithubRepoInstrumentation.getGithubRepo(id = 1, starCount = 5),
                    GithubRepoInstrumentation.getGithubRepo(id = 2, starCount = 9)
                )
            )
            val dataLoadedItem = awaitItem()
            with((dataLoadedItem as GithubRepoListViewModel.GithubRepoListState.DataLoaded).githubRepoList) {
                assertThat(first().id).isEqualTo(2L)
                assertThat(get(1).id).isEqualTo(1L)
            }
        }
    }
}

class FakeGithubRepoRepository : GithubRepoRepository {
    private val _githubRepoListFlow = MutableStateFlow<List<GithubRepo>?>(null)
    override val githubRepoListFlow: Flow<List<GithubRepo>> = _githubRepoListFlow
        .filterNotNull()

    override fun getGithubRepoFlowById(repoId: Long): Flow<GithubRepo?> {
        return flowOf()
    }

    suspend fun emitList(repoList: List<GithubRepo>) {
        _githubRepoListFlow.emit(repoList)
    }
}