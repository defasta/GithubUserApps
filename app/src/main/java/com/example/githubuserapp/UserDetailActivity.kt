package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class UserDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
    }
}
