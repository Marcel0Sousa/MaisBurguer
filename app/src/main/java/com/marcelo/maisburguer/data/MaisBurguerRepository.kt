package com.marcelo.maisburguer.data

import com.google.gson.Gson
import com.marcelo.maisburguer.api.MaisBurguerService

class MaisBurguerRepository(
    private val service: MaisBurguerService
) {

    suspend fun postUser(userRequest: UserRequest): UserCreateResponse  {
        val response = service.postUser(userRequest)

        try {
            if (!response.isSuccessful) {

                val errorData = response.errorBody()?.string()?.let { json ->
                    if (response.code() == 401) {
                        Gson().fromJson(json, UserCreateResponse.ErrorAuth::class.java)
                    } else {
                        Gson().fromJson(json, UserCreateResponse.Error::class.java)
                    }
                }
                return errorData ?: UserCreateResponse.Error("internal server error")
            }

            val successData = response.body()?.string()?.let { json ->
                Gson().fromJson(json, UserCreateResponse.Success::class.java)
            }
            return successData ?: UserCreateResponse.Error("unexpected response success")

        } catch (e: Exception) {

            return UserCreateResponse.Error(e.message ?: "unexpected exception")

        }
    }
}