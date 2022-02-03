package com.example.musico

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.musico.databinding.FragmentPlayerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PlayerFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playPauseBtnSheet.setOnTouchListener(View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                return@OnTouchListener true
            }
            Toast.makeText(context, "play", Toast.LENGTH_SHORT).show()
            return@OnTouchListener true
        })
    }
}


