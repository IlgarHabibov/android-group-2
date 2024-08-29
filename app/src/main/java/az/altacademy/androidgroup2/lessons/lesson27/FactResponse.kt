package az.altacademy.androidgroup2.lessons.lesson27

import com.google.gson.annotations.SerializedName

data class FactResponse(

    @SerializedName("_id")
    var id: String?,
    var text: String?,
    var used: Boolean?,
    var user: User?
)

data class User(
    @SerializedName("_id")
    var id: String?,

    var photo: String?,
    var name: Name?
)

data class Name(
    var first: String?,
    var last: String?
)
