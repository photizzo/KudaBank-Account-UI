package com.agromall.data.implementation

import com.agromall.domain.interactor.farmer.GetFarmers
import com.agromall.domain.interactor.user.LoginUser
import com.agromall.domain.model.farmer.Farmer
import com.agromall.domain.model.user.User
import com.agromall.domain.repository.UserRepository
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UserDataRepository @Inject constructor(
): UserRepository {
    override fun loginUser(param: LoginUser.Params): Flow<User> {
        throw IllegalArgumentException()
    }

    override fun getLoggedInUser(): Flow<User> {
        throw IllegalArgumentException()
    }

    override fun getFarmers(param: GetFarmers.Params): Flow<List<Farmer>> {
        throw IllegalArgumentException()
    }
}