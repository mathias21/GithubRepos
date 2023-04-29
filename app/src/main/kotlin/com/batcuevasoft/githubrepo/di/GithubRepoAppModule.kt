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
import com.batcuevasoft.githubrepo.data.remote.CommonInterceptor
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasource
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubRepoRemoteDatasourceImpl
import com.batcuevasoft.githubrepo.BuildConfig
import com.batcuevasoft.githubrepo.data.remote.githubRepo.GithubApi
import com.google.firebase.analytics.FirebaseAnalytics
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
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

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(getNetworkLoggingInterceptor())
            .addInterceptor(CommonInterceptor())
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshiBuilder = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

    private fun getNetworkLoggingInterceptor() = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    @Singleton
    @Provides
    internal fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(context)
    }

    private const val DATABASE_NAME = "githubrepo_database_name"
    private const val API_BASE_URL = "https://api.github.com"
}
