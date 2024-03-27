package `in`.bitcode.retrofitdemo

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.net.HttpURLConnection
import java.net.URL

interface UsersService {

    @GET("api/users/{user_id}")
    suspend fun fetchUsers(
        @Path("user_id") id : Int
    ) : UserModel

    companion object {
        fun getInstance() : UsersService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val usersService = retrofit.create(UsersService::class.java)
            return usersService
        }
    }
}

/*
class Proxy2 : UsersService {
    override suspend fun fetchUsers(): UserModel {
        val url = URL("https://reqres.in/api/users/2")
        val httpUrlConnection = url.openConnection() as HttpURLConnection
        httpUrlConnection.connect()

        httpUrlConnection.inputStream
        extract data from is
                convert response to json
        extract data from json and convert to jsava objects
        retrurn the java object
    }
}*/
