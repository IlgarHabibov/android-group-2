package az.altacademy.androidgroup2.lessons.weather

sealed class ApiState<out T> {
    data class Success<T>(val data: T?): ApiState<T>()
    data class Error(val error: ErrorModel?): ApiState<Nothing>()
}