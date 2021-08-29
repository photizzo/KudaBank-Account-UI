package com.agromall.domain.interactor.user

import com.agromall.domain.executor.PostExecutionThread
import com.agromall.domain.model.user.User
import com.agromall.domain.repository.UserRepository
import com.agromall.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for log in user
 */
class LoginUser @Inject constructor(
    val repository: UserRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<User, LoginUser.Params>(postExecutionThread) {

    override suspend fun buildFlowUseCase(params: Params?): Flow<User> {
        if (params == null) throw IllegalArgumentException("Params cannot be null")
        return repository.loginUser(params)
    }

    data class Params(
        val email: String,
        val password: String
    )
}