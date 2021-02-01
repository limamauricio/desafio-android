package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.data.remote.UserRemoteDataSource
import com.picpay.desafio.android.utils.performGetOperation
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserDao
) {

    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.addAllUsers(it) }
    )
}