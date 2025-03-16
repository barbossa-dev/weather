package ir.griffinstudio.weather.data.model.remote

import com.google.gson.annotations.SerializedName


data class ApiGetWeatherResponse(
    @SerializedName("weather")
    val weather: ArrayList<ApiGetWeatherResponseWeathers>,
    @SerializedName("main")
    val main: ApiGetWeatherResponseMain,
    @SerializedName("wind")
    val wind:ApiGetWeatherResponseWind
)
data class ApiGetWeatherResponseWind(
    @SerializedName("speed")
    val speed: Float
)

data class ApiGetWeatherResponseMain(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("humidity")
    val humidity: Int
)

data class ApiGetWeatherResponseWeathers(
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String
)