package `in`.bitcode.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import `in`.bitcode.retrofitdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val usersService = UsersService.getInstance()
        Log.e("tag", "Class which implements UsersService: ${usersService::class.java.name}")


        activityMainBinding.btnFetchUserData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val userModel = usersService.fetchUsers(activityMainBinding.edtUserId.text.toString().toInt())

                withContext(Dispatchers.Main) {
                    activityMainBinding.txtEmail.text = userModel.data.email
                    activityMainBinding.txtUsername.text = "${userModel.data.firstName} ${userModel.data.lastName}"
                    Glide.with(this@MainActivity)
                        .load(userModel.data.avatar)
                        .into(activityMainBinding.imgUser)
                }
            }
        }

       /* val us = Proxy2()
        us.fetchUsers()*/



    }
}