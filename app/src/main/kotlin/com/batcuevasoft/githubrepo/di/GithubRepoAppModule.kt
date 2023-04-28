package com.batcuevasoft.githubrepo.di

import android.content.Context
import androidx.room.Room
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepository
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepositoryImpl
import com.batcuevasoft.githubrepo.core.util.DispatcherProvider
import com.batcuevasoft.githubrepo.core.util.DispatcherProviderImp
import com.batcuevasoft.githubrepo.data.local.GithubRepoDatabase
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoLocalDatasource
import com.batcuevasoft.githubrepo.data.local.githubRepo.GithubRepoLocalDatasourceImpl
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasource
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasourceImpl
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
object GithubRepoAppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindsModule {

        @Binds
        fun bindGithubRepoRepository(implementation: GithubRepoRepositoryImpl): GithubRepoRepository

        @Binds
        fun bindGithubRepoLocalDatasource(implementation: GithubRepoLocalDatasourceImpl): GithubRepoLocalDatasource

        @Binds
        fun bindGithubRepoRemoteDatasource(implementation: GithubRepoRemoteDatasourceImpl): GithubRepoRemoteDatasource

        @Binds
        fun bindDispatcherProvider(implementation: DispatcherProviderImp): DispatcherProvider
    }

    @Provides
    @Singleton
    internal fun provideGithubRepoDatabase(@ApplicationContext context: Context): GithubRepoDatabase = Room.databaseBuilder(
        context,
        GithubRepoDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    internal fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    private const val DATABASE_NAME = "githubrepo_database_name"
}
