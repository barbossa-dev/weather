package ir.griffinstudio.weather.data.api

import ir.griffinstudio.weather.BuildConfig
import ir.griffinstudio.weather.data.model.remote.ApiGetWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/data/2.5/weather")
    fun getWeatherState(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Call<ApiGetWeatherResponse>
}