package com.codegalaxy.testingpracticeimpl.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.codegalaxy.testingpracticeimpl.model.entity.Comment
import com.codegalaxy.testingpracticeimpl.model.entity.User
import com.codegalaxy.testingpracticeimpl.model.repository.IUserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: UserViewModel
    private lateinit var repository: IUserRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
    }

    @Test
    fun `fetchRemoteUsers success updates local storage`() = runTest {
        // Given
        val remoteUsers = listOf(
            User(1,
                "Leanne Graham",
                "Bret",
                "Sincere@april.biz",
                "1-770-736-8031 x56442",
                "hildegard.org"
            )
        )
        coEvery { repository.getRemoteUsers() } returns remoteUsers

        // When
        viewModel = UserViewModel(repository)
        viewModel.fetchRemoteUsers()

        advanceUntilIdle()

        // Then
        coVerify {
            repository.getRemoteUsers()
            repository.saveUsers(remoteUsers)
        }
    }

    @Test
    fun `fetchRemoteUsers error updates error state`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { repository.getRemoteUsers() } throws Exception(errorMessage)

        // When
        viewModel = UserViewModel(repository)
        viewModel.fetchRemoteUsers()


        advanceUntilIdle()

        // Then
        assert(viewModel.error.value == errorMessage)
        assert(viewModel.loading.value == false)
    }

    @Test
    fun `fetchComments success updates comments state`() = runTest {
        // Given
        val userId = 1
        val comments = listOf(
            Comment(
                1,
                1,
                "id labore ex et quam laborum",
                "Eliseo@gardner.biz",
                "laudantium enim quasi est quidem magnam voluptate ipsam eos" +
                        "tempora quo necessitatibus " +
                        "dolor quam autem quasi" +
                        "reiciendis et nam sapiente accusantium"
            )
        )
        coEvery { repository.getComments(userId) } returns comments

        // When
        viewModel = UserViewModel(repository)
        viewModel.fetchComments(userId)

        advanceUntilIdle()

        // Then
        assert(viewModel.comments.value == comments)
        assert(viewModel.loading.value == false)
    }

    @Test
    fun `fetchComments error updates error state`() = runTest {

        // Given
        val userId = 1
        val errorMessage = "Failed to fetch comments"
        coEvery { repository.getComments(userId) } throws Exception(errorMessage)

        // When
        viewModel = UserViewModel(repository)
        viewModel.fetchComments(userId)

        advanceUntilIdle()

        // Then
        assert(viewModel.error.value == errorMessage)
        assert(viewModel.loading.value == false)
    }

    @Test
    fun `setError updates error state`() = runTest {
        // Given
        val errorMessage = "Test error"

        // When
        viewModel = UserViewModel(repository)
        viewModel.setError(errorMessage)

        advanceUntilIdle()

        // Then
        assert(viewModel.error.value == errorMessage)
    }

    @Test
    fun `local users are observed from repository`() = runTest {

        // Given
        val localUsers = MutableLiveData<List<User>>()
        coEvery { repository.getLocalUsers() } returns localUsers

        // When
        viewModel = UserViewModel(repository)

        advanceUntilIdle()

        // Then
        assert(viewModel.localUsers == repository.getLocalUsers())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}