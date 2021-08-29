package com.agromall.data.repository.user

import com.agromall.domain.model.user.User
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining methods for the caching of [Farm]. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface UsersCache {
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