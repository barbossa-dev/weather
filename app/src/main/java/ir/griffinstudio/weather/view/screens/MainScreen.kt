package ir.griffinstudio.weather.view.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.griffinstudio.weather.R
import ir.griffinstudio.weather.view.ui.theme.WeatherTheme
import ir.griffinstudio.weather.viewmodel.MainViewModel

@Preview(showBackground = true)
@Composable
fun ViewMain(viewModel: MainViewModel = hiltViewModel()) {
    val inputLocation = remember {
        mutableStateOf("")
    }
    val showData = viewModel.showWeatherStateData.collectAsState()
    val weatherData = viewModel.weatherViewData.collectAsState()
    WeatherTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
                    .fillMaxWidth()
                    .animateContentSize(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(5.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null
                    )
                    TextField(
                        value = inputLocation.value,
                        onValueChange = {
                            inputLocation.value = it
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        )
                    )
                    IconButton(
                        onClick = {
                            if (inputLocation.value.isNotEmpty()) {
                                viewModel.getWeather(inputLocation.value)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }

                }
                if (weatherData.value != null && showData.value) {
                    Image(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        painter = painterResource(weatherData.value?.image ?: R.drawable.ic_clear),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally),
                        text = buildAnnotatedString {
                            append(weatherData.value?.temp.toString())
                            pushStyle(
                                SpanStyle(
                                    baselineShift = BaselineShift.Superscript,
                                    fontSize = 13.sp
                                )
                            )
                            append("C")
                        },
                        fontSize = 20.sp
                    )
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally),
                        text = weatherData.value?.description.toString()
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .size(30.dp),
                                painter = painterResource(R.drawable.ic_humid),
                                contentDescription = null
                            )
                            Text(
                                text = "${weatherData.value?.humidity}%"
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .size(30.dp),
                                painter = painterResource(R.drawable.ic_windy),
                                contentDescription = null
                            )
                            Text(
                                text = "${weatherData.value?.windSpeed} km/h"
                            )
                        }
                    }
                }else if (showData.value){
                    Image(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        painter = painterResource(R.drawable.ic_err),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}