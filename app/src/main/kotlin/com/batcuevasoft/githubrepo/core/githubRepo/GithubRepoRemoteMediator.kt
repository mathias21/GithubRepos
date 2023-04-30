package com.batcuevasoft.githubrepo.core.githubRepo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoEntity

import com.batcuevasoft.githubrepo.data.local.remoteKeys.RemoteKeyEntity
import com.batcuevasoft.githubrepo.data.local.remoteKeys.RemoteKeyLocalDatasource
import com.batcuevasoft.githubrepo.data.remote.NetworkResponse
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasource
import timber.log.Timber

@OptIn(ExperimentalPagingApi::class)
class GithubRemoteMediator(
    private val query: String,
    private val githubRepoRemoteDatasource: GithubRepoRemoteDatasource,
    private val remoteKeyLocalDatasource: RemoteKeyLocalDatasource
) : RemoteMediator<Int, GithubRepoEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, GithubRepoEntity>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)

                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)

                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }


        val networkResponse = githubRepoRemoteDatasource.fetchRepositories(query, state.config.pageSize, page)
        return when(networkResponse) {
            is NetworkResponse.Success -> {
                val repos = networkResponse.data.items
                val endOfPaginationReached = repos.isEmpty()

                remoteKeyLocalDatasource.updateKeysAndRepos(
                    repos.map { it.toGithubRepo() },
                    loadType == LoadType.REFRESH,
                    if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                    if (endOfPaginationReached) null else page + 1
                )
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }

            is NetworkResponse.Error -> {
                Timber.e(networkResponse.exception)
                MediatorResult.Error(networkResponse.exception)
            }
        }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, GithubRepoEntity>): RemoteKeyEntity? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                remoteKeyLocalDatasource.getRemoteKeysByGithubRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, GithubRepoEntity>): RemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                remoteKeyLocalDatasource.getRemoteKeysByGithubRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, GithubRepoEntity>
    ): RemoteKeyEntity? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                remoteKeyLocalDatasource.getRemoteKeysByGithubRepoId(repoId)
            }
        }
    }

    companion object {
        const val GITHUB_STARTING_PAGE_INDEX = 1
    }
}