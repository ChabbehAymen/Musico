package com.example.musico

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.musico.placeholder.AllSongsPlaceHolder
import com.example.musico.placeholder.HistoryItemPlaceHolder
import com.example.musico.placeholder.RandomPickerPlaceHolder


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private val viewModel: MViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            onDestroy()
        }, 3000)
    }
}