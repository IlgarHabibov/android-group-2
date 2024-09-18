package az.altacademy.androidgroup2.lessons.weather

sealed class ApiResult<out T> {
    data class Success<T>(val data: T?): ApiResult<T>()
    data class Error(val error: ErrorModel?): ApiResult<Nothing>()
}