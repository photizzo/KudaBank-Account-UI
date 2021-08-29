package com.agromall.data.repository.user

import com.agromall.domain.interactor.user.LoginUser
import com.agromall.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRemote {
    /**
     * Login a user
     */
    fun loginUser(param: LoginUser.Params): Flow<User>
}