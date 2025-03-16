package ir.griffinstudio.weather.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import ir.griffinstudio.weather.data.api.ApiInterface
import ir.griffinstudio.weather.data.model.remote.ApiGetWeatherResponse
import ir.griffinstudio.weather.utils.ErrorUtils
import javax.inject.Inject

@ViewModelScoped
class MainRepository @Inject constructor() {
    @Inject
    lateinit var api: ApiInterface

    @Inject
    lateinit var errorUtils: ErrorUtils
    suspend fun getWeatherState(city: String): Pair<ApiGetWeatherResponse?, Int?> {
        return errorUtils.handleApiError(api.getWeatherState(city = city))
    }
}