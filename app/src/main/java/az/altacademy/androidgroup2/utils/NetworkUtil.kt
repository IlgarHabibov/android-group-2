package az.altacademy.androidgroup2.utils

import android.accounts.NetworkErrorException
import android.net.ParseException
import android.net.http.NetworkException
import az.altacademy.androidgroup2.lessons.weather.ApiResult
import az.altacademy.androidgroup2.lessons.weather.ErrorModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Response

suspend fun <T> apiCall(call: () -> Response<T>): ApiResult<T> {
    return try {
        val result = withContext(Dispatchers.IO){
            call.invoke()
        }
        if (result.isSuccessful){
            ApiResult.Success(result.body())
        }else{
            val gson = Gson()
            val jsonObject = JSONObject(result.errorBody()?.charStream()?.readText())
            val error = gson.fromJson(jsonObject.toString(), ErrorModel::class.java)
            ApiResult.Error(error)
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
    catch (e: Exception){
        e.printStackTrace()
        ApiResult.Error(
            ErrorModel(errorCode = 505, errorTitle = "Sistem Xetasi", "Zehmet olmasa bir qeder sonra yoxlayin")
        )
    }
}