package az.altacademy.androidgroup2.lessons.lesson28_2

data class CreatePostResponse(
    var id: Int,
    var title: String,
    var body: String,
    var userId: Int
)
