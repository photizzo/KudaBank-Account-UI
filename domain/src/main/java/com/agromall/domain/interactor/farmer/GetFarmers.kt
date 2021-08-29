package com.agromall.domain.interactor.farmer

import com.agromall.domain.executor.PostExecutionThread
import com.agromall.domain.model.farmer.Farmer
import com.agromall.domain.model.user.User
import com.agromall.domain.repository.UserRepository
import com.agromall.domain.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for log in user
 */
class GetFarmers @Inject constructor(
    val repository: UserRepository,
    postExecutionThread: PostExecutionThread
) : FlowUseCase<List<Farmer>, GetFarmers.Params>(postExecutionThread) {

    override suspend fun buildFlowUseCase(params: Params?): Flow<List<Farmer>> {
        if (params == null) throw IllegalArgumentException("Params cannot be null")
        return repository.getFarmers(params)
    }

    data class Params(
        val agent: User
    )
}