package ir.griffinstudio.weather.utils

import android.util.Log
import retrofit2.Call
import retrofit2.awaitResponse
import javax.inject.Inject

class ErrorUtils @Inject constructor() {
    suspend fun <T> handleApiError(value: Call<T>): Pair<T?, Int?> {
        return try {
            val response = value.awaitResponse()
            Log.i("jjj", "handleApiError: ${response.code()}")
            if (response.isSuccessful) {
                Pair(response.body(), response.code())
            } else {
                Pair(null, response.code())
            }

        } catch (_: Exception) {
            Pair(null, null)
        }
    }
}