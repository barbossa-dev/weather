package ir.griffinstudio.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.griffinstudio.weather.R
import ir.griffinstudio.weather.data.model.local.ViewData
import ir.griffinstudio.weather.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var repository: MainRepository

    private val _weatherViewData = MutableStateFlow<ViewData?>(null)
    val weatherViewData: StateFlow<ViewData?> = _weatherViewData
    private val _showWeatherStateData = MutableStateFlow<Boolean>(false)
    val showWeatherStateData: StateFlow<Boolean> = _showWeatherStateData
    fun getWeather(city: String) {
        viewModelScope.launch {
            val result = repository.getWeatherState(city = city)
            if (result.second == 200) {
                _weatherViewData.value = ViewData(
                    city = city,
                    description = result.first?.weather?.get(0)?.description ?: "",
                    humidity = result.first?.main?.humidity ?: 0,
                    temp = result.first?.main?.temp ?: 0.0f,
                    windSpeed = result.first?.wind?.speed ?: 0.0f,
                    image = when (result.first?.weather?.get(0)?.main) {
                        "Clear" -> R.drawable.ic_clear
                        "Clouds" -> R.drawable.ic_cloud
                        "Rain" -> R.drawable.ic_rain
                        "Snow" -> R.drawable.ic_snow
                        "Haze" -> R.drawable.ic_mist
                        else -> R.drawable.ic_clear
                    }
                )
            } else {
                _weatherViewData.value = null
            }
            _showWeatherStateData.value = true
        }
    }
}