package az.altacademy.androidgroup2.note.data.repository

interface AuthRepository {
    suspend fun registerUser(email: String, password: String)
    suspend fun loginUser(email: String, password: String)
    suspend fun logOut()
}