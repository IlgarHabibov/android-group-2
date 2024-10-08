package az.altacademy.androidgroup2.utils

import az.altacademy.androidgroup2.lessons.weather.ApiState
import az.altacademy.androidgroup2.lessons.weather.BaseError
import az.altacademy.androidgroup2.lessons.weather.ErrorModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

suspend fun <T> apiCall(call: suspend () -> Response<T>): ApiState<T> {
    return try {
        val result = withContext(Dispatchers.IO) {
            call.invoke()
        }
        if (result.isSuccessful) {
            ApiState.Success(result.body())
        } else {
            val gson = Gson()
            val jsonObject = JSONObject(result.errorBody()?.charStream()?.readText())
            val error = gson.fromJson(jsonObject.toString(), BaseError::class.java)
            ApiState.Error(error.error)
        }
    }


//    catch (e: NetworkErrorException){
//        e.printStackTrace()
//        ApiResult.Error(
//            ErrorModel(errorCode = 505, errorTitle = "Internet Xetasi", "Zehmet olmasa internet sebekesini yoxlayin")
//        )
//    }catch (e: ParseException){
//        ApiResult.Error(
//            ErrorModel(errorCode = 505, errorTitle = "Sistem Xetasi", "Zehmet olmasa bir qeder sonra yoxlayin")
//        )
//    }
    catch (e: Exception) {
        e.printStackTrace()
        ApiState.Error(
            ErrorModel(code = 505, message = "Zehmet olmasa bir qeder sonra yoxlayin")
        )
    }
}

suspend fun <T> apiCallWithFlow(call: suspend () -> Response<T>) = flow<ApiState<T>> {
    try {
        val result = withContext(Dispatchers.IO) {
            call.invoke()
        }
        if (result.isSuccessful) {
            emit(ApiState.Success(result.body()))
        } else {
            val gson = Gson()
            val jsonObject = JSONObject(result.errorBody()?.charStream()?.readText())
            val error = gson.fromJson(jsonObject.toString(), BaseError::class.java)

            emit(ApiState.Error(error.error))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        emit(
            ApiState.Error(
                ErrorModel(code = 505, message = "Zehmet olmasa bir qeder sonra yoxlayin")
            )
        )
    }
}