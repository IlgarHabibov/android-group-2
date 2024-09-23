package az.altacademy.androidgroup2.lessons.weather

data class ErrorModel(
    var code: Int?,
    var message: String?,
)

data class BaseError(
    val error: ErrorModel
)
