package com.agromall.presentation.viewmodel


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agromall.domain.interactor.user.GetLoggedInUser
import com.agromall.domain.interactor.user.LoginUser
import com.agromall.domain.model.user.User
import com.agromall.presentation.state.UIState
import com.agromall.presentation.util.handleError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * [UsersViewModel] handle all interactions with the UI layer
 */
open class UsersViewModel @ViewModelInject constructor(
    private val loginUser: LoginUser,
    private val getLoggedInUser: GetLoggedInUser
) : ViewModel() {

    //mutable livedata should be private to a single class
    private val _loginUserLiveData: MutableLiveData<UIState<User>> = MutableLiveData()
    private val _getLoggedInLiveData: MutableLiveData<UIState<User>> = MutableLiveData()

    //exposing MutableLivedata to corresponding Livedata objects
    val loginUserLiveData: LiveData<UIState<User>>
        get() = _loginUserLiveData
    val getLoggedInUserLiveData: LiveData<UIState<User>>
        get() = _getLoggedInLiveData

    /**
     * Login a user
     */
    fun loginUser(param: LoginUser.Params) {
        _loginUserLiveData.postValue(UIState.Loading)
        viewModelScope.launch {
            loginUser.execute(param)
                .onEach { data ->
                    // success response
                    _loginUserLiveData.postValue(UIState.Success(data))
                }.handleError { error ->
                    // error response
                    _loginUserLiveData.postValue(UIState.Failed(error))
                }.collect()
        }
    }

    /**
     * Get logged in user
     */
    fun getLoggedInUser() {
        viewModelScope.launch {
            getLoggedInUser.execute()
                .onEach { data ->
                    // success response
                    _getLoggedInLiveData.postValue(UIState.Success(data))
                }.handleError { error ->
                    // error response
                    _getLoggedInLiveData.postValue(UIState.Failed(error))
                }.collect()
        }
    }
}