package uk.co.sooce.rickandmorty.base.data

import retrofit2.Response
import timber.log.Timber
import uk.co.sooce.rickandmorty.base.data.model.Resource

open class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>) : Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} : ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.e("RemoteDataSource: $message")
        return Resource.error("Network call has failed for a following reason: $message")
    }
}