package com.example.musico

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.utils.widget.MotionButton
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musico.adapter.AllSongsAdapter
import com.example.musico.adapter.HistoryAdapter
import com.example.musico.adapter.RandomPickerAdapter
import com.example.musico.databinding.ActivityMainBinding
import com.example.musico.placeholder.AllSongsPlaceHolder
import com.example.musico.placeholder.HistoryItemPlaceHolder
import com.example.musico.placeholder.RandomPickerPlaceHolder
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val context = this
    private var isHiding = false
    private val viewModel: MViewModel by viewModels()


    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestRunTimePermission()

        binding.playedSongAlbum.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                return@setOnTouchListener true
            }
            showMusicPlayer()
            return@setOnTouchListener true
        }
        viewModel.context = this
        viewModel.main()

        AllSongsPlaceHolder.Music.addAll(viewModel.songs)
        RandomPickerPlaceHolder.Music.addAll(viewModel.randomSongs)
        HistoryItemPlaceHolder.Music.addAll(viewModel.randomSongs)
        binding.randomList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.historyList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.allMusicList.layoutManager = LinearLayoutManager(this)

        binding.randomList.adapter = RandomPickerAdapter(RandomPickerPlaceHolder.Music, this)

        binding.historyList.adapter = HistoryAdapter(HistoryItemPlaceHolder.Music, this)
        binding.allMusicList.adapter = AllSongsAdapter(AllSongsPlaceHolder.Music, this)

        binding.scroller.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            when {
                scrollY > oldScrollY -> scrollDownHandler()
                scrollY < oldScrollY -> scrollUpHandler()
            }


        }


    }

    private fun showMusicPlayer() {
        val bottomSheetPlayer = PlayerFragment()
        bottomSheetPlayer.show(supportFragmentManager, bottomSheetPlayer.tag)
    }

    private fun scrollUpHandler() {
        if (isHiding) {
            val v = binding.musicController
            val animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 300f, 0f)
            animator.start()
            isHiding = false
        }

    }

    private fun scrollDownHandler() {
        if (!isHiding) {
            val v = binding.musicController
            val animator = ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 0f, 300f)
            animator.start()
            isHiding = true
        }

    }

    private fun requestRunTimePermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                13
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 13) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    13
                )
        }
    }
}