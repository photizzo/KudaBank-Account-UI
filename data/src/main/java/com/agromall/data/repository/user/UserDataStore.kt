package com.agromall.data.repository.user

import com.agromall.domain.interactor.user.LoginUser
import com.agromall.domain.model.farmer.Farmer
import com.agromall.domain.model.user.User
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining methods for the data operations related to [Farmer].
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface UserDataStore {
    /**
     * Login a user
     */
    fun loginUser(param: LoginUser.Params): Flow<User>

    /**
     * Savings a user
     */
    suspend fun saveUser(param: User)

    /**
     * Get logged in user
     */
    fun getLoggedInUser(): Flow<User>

    /**
     * Delete User
     */
    suspend fun deleteUser(param: User)
}