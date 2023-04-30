package com.batcuevasoft.githubrepo.ui.main

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepo
import com.batcuevasoft.githubrepo.core.githubRepo.GithubRepoRepository
import com.batcuevasoft.githubrepo.core.util.DispatcherProvider
import com.batcuevasoft.githubrepo.di.GithubRepoAppModule
import com.batcuevasoft.githubrepo.ui.MainActivity
import com.batcuevasoft.githubrepo.ui.theme.GithubRepoTheme
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import com.batcuevasoft.githubrepo.R

@UninstallModules(GithubRepoAppModule.BindsModule::class)
@HiltAndroidTest
class GithubRepoListTest {

    @get:Rule(order = 1)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // We disconnect repository layer to be able to inject our own logic and recreate scenarios
    @BindValue
    @JvmField
    val githubRepoRepository: GithubRepoRepository = mock()

    @BindValue
    @JvmField
    val dispatcherProvider: DispatcherProvider = object : DispatcherProvider {
        override val io: CoroutineDispatcher = Dispatchers.Main
        override val default: CoroutineDispatcher = Dispatchers.Main
        override val unconfined: CoroutineDispatcher = Dispatchers.Main
        override val main: CoroutineDispatcher = Dispatchers.Main
    }

    private val mutableStateFlow = MutableStateFlow<List<GithubRepo>>(emptyList())

    @Test
    fun whenClickingRepoWeTriggerOnRepoClickWithCorrectParam() {
        var isRepoClickId = -1L
        whenever(githubRepoRepository.githubRepoListFlow).thenReturn(mutableStateFlow)

        composeTestRule.activity.setContent {
            GithubRepoTheme {
                GithubRepoListScreen(
                    onBackPressed = {},
                    onRepoClick = {
                        isRepoClickId = it
                    }
                )
            }
        }

        val titleLabel = composeTestRule.activity.getString(R.string.repo_list_title)
        val repoId = 123L
        val repoName = "MyLovelyRepo"

        mutableStateFlow.value = listOf(
            GithubRepoInstrumentation.getGithubRepo(id = repoId, name = repoName),
            GithubRepoInstrumentation.getGithubRepo()
        )

        composeTestRule.onNodeWithText(titleLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(repoName).performClick()
        assert(isRepoClickId == repoId)
    }

    @Test
    fun whenNoRepositoriesWeShowNoReposFoundContent() {
        whenever(githubRepoRepository.githubRepoListFlow).thenReturn(mutableStateFlow)

        composeTestRule.activity.setContent {
            GithubRepoTheme {
                GithubRepoListScreen(
                    onBackPressed = {},
                    onRepoClick = {
                    }
                )
            }
        }

        val titleLabel = composeTestRule.activity.getString(R.string.repo_list_title)
        val noReposFound = composeTestRule.activity.getString(R.string.empty_repo_list_title)

        composeTestRule.onNodeWithText(titleLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(noReposFound).assertIsDisplayed()
    }
}