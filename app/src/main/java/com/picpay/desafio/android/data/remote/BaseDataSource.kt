package com.picpay.desafio.android.data.remote

import android.util.Log
import com.picpay.desafio.android.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {

        try {

            val response = call()
            if (response.isSuccessful){
                val body = response.body()
                if(body != null)
                    return Resource.success(body)
            }

            return error(" ${response.code()} ${response.message()}")

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.d("ERROR:", message)
        return Resource.error("Falha ao buscar dados no servidor. Tente novamente em alguns segundos.")
    }
}