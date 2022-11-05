package com.example.injob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.injob.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val mBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)
        }
    }
}