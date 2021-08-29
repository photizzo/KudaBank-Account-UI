package com.agromall.domain.repository

import com.agromall.domain.interactor.farmer.GetFarmers
import com.agromall.domain.interactor.user.LoginUser
import com.agromall.domain.model.farmer.Farmer
import com.agromall.domain.model.user.User
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface UserRepository {
    /**
     * Login a user
     */
    fun loginUser(param: LoginUser.Params): Flow<User>

    /**
     * Get Logged in user
     */
    fun getLoggedInUser(): Flow<User>

    /**
     * Get farmers for an agent
     */
    fun getFarmers(param: GetFarmers.Params): Flow<List<Farmer>>
}