package com.github.mrbean355.witnesswords.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.mrbean355.witnesswords.MainActivity
import com.github.mrbean355.witnesswords.R
import com.github.mrbean355.witnesswords.loadWords
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            loadWords()
            startActivity(MainActivity(this@SplashActivity))
            finish()
        }
    }
}