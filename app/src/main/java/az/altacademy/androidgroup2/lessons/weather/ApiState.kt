package az.altacademy.androidgroup2.lessons.weather

sealed class ApiState {
    data class Success(val data: CurrentWeatherResponse?): ApiState()
    data class Loading(val isLoading: Boolean): ApiState()
    data class Error(val errorCode: Int?, val errorMessage: String?): ApiState()
}