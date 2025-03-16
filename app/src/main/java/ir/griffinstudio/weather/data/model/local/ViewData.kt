package ir.griffinstudio.weather.data.model.local

data class ViewData(
    val image: Int,
    val temp: Float,
    val humidity: Int,
    val windSpeed:Float,
    val description: String,
    val city: String
)
