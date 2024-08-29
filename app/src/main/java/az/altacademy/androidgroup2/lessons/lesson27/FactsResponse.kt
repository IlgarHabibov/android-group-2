package az.altacademy.androidgroup2.lessons.lesson27

import com.google.gson.annotations.SerializedName

data class FactsResponse(

    @SerializedName("_id")
    var id: String?,

    var user: String?,
    var text: String?,
    var used: Boolean?,
)
