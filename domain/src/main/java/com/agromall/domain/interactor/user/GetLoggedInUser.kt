package com.agromall.domain.interactor.user

import com.agromall.domain.executor.PostExecutionThread
import com.agromall.domain.model.user.User
import com.agromall.domain.repository.UserRepository
import com.agromall.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting log in user
 */
class GetLoggedInUser @Inject constructor(
    val repository: UserRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<User, Nothing>(postExecutionThread) {
    override suspend fun buildFlowUseCase(params: Nothing?): Flow<User> {
        return repository.getLoggedInUser()
    }
}