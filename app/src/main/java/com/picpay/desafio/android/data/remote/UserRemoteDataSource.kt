package com.picpay.desafio.android.data.remote

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val service: PicPayService
): BaseDataSource() {

    suspend fun getUsers() = getResult { service.getUsers() }
}