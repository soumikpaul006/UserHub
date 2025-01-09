package com.codegalaxy.testingpracticeimpl.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codegalaxy.testingpracticeimpl.model.entity.Comment
import com.codegalaxy.testingpracticeimpl.model.entity.User
import com.codegalaxy.testingpracticeimpl.model.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: IUserRepository
) : ViewModel() {


    val localUsers: LiveData<List<User>> = repository.getLocalUsers()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    fun fetchRemoteUsers() {
        viewModelScope.launch {
            try {
                _loading.value = true
                val remoteUsers = repository.getRemoteUsers()
                repository.saveUsers(remoteUsers)

            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun setError(message: String) {
        _error.value = message
    }



    fun fetchComments(userId: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val comments = repository.getComments(userId)
                _comments.value = comments
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
}