package com.example.stopwatchapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvTime: TextView
    private lateinit var btnStart: Button
    private lateinit var btnPause: Button
    private lateinit var btnReset: Button

    private var milliseconds = 0L
    private var running = false

    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {
            if (running) {
                milliseconds += 10
                updateTime()
                handler.postDelayed(this, 10)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTime = findViewById(R.id.tvTime)
        btnStart = findViewById(R.id.btnStart)
        btnPause = findViewById(R.id.btnPause)
        btnReset = findViewById(R.id.btnReset)

        btnStart.setOnClickListener {
            if (!running) {
                running = true
                handler.post(runnable)
            }
        }

        btnPause.setOnClickListener {
            running = false
        }

        btnReset.setOnClickListener {
            running = false
            milliseconds = 0
            updateTime()
        }
    }

    private fun updateTime() {
        val minutes = (milliseconds / 60000)
        val seconds = (milliseconds % 60000) / 1000
        val millis = (milliseconds % 1000)

        val time = String.format("%02d:%02d:%03d", minutes, seconds, millis)
        tvTime.text = time
    }
}