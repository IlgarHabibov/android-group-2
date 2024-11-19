package az.altacademy.androidgroup2.note.ui.core

sealed class CoreUIState<out T> {
    data class Success<T>(val data: T): CoreUIState<T>()
    data class Loading(val isLoading: Boolean): CoreUIState<Nothing>()
    data class Error(val errorCode: Int?, val errorMessage: String?): CoreUIState<Nothing>()
}