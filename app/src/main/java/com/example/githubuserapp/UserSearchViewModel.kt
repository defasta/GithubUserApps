package com.example.githubuserapp

import android.util.Log
import com.loopj.android.http.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class UserSearchViewModel : ViewModel(){

    val listUser = MutableLiveData<ArrayList<User>>()

    fun setUser(username: String?){
        val listItems = ArrayList<User>()
        val url = "https://api.github.com/search/users?q=$username"
        val client = AsyncHttpClient()
        client.addHeader("Authentication", "token 0ca5410ce58178c183a0501ba2015389dcb0507b")
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("items")

                    for (i in 0 until list.length()) {
                        val userList = list.getJSONObject(i)
                        var user = User()
                        user.id = userList.getInt("id")
                        user.username = userList.getString("login")
                        listItems.add(user)
                    }
                    listUser.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }

        })
    }

    internal fun getUser() : LiveData<ArrayList<User>> {
        return listUser
    }
}