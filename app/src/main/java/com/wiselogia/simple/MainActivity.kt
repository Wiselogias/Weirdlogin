package com.wiselogia.simple

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import com.wiselogia.simple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var login: String = ""
    private var password: String = ""
    private lateinit var media: MediaPlayer

    private var isNight = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        isNight = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        media = MediaPlayer.create(applicationContext, R.raw.bassboost)
        setContentView(binding.root)

        binding.themeButton.setOnClickListener {
            sugma()
        }
        binding.loginEdit.addTextChangedListener {
            login = it.toString()
        }
        binding.passwordEdit.addTextChangedListener {
            password = it.toString()
        }

        binding.passwordEdit.setOnEnterListener()
        binding.loginEdit.setOnEnterListener()

        binding.themeButton.setImageResource(
            if (isNight) R.drawable.ic_outline_nights_stay_24
            else R.drawable.ic_outline_wb_sunny_24
        )
        binding.themeButton.setColorFilter(
            if (isNight) R.color.white
            else R.color.black
        )
        binding.loginButton.setOnClickListener {
            if (login.isEmpty()) {
                binding.loginEdit.error = getString(R.string.no_login)
            } else if (password.isEmpty()) {
                binding.passwordEdit.error = getString(R.string.no_password)
            } else if (login != "maybebaby@haram.ru" && password != "antiharamsoftware") {
                binding.passwordEdit.error = getString(R.string.no)
            } else
                media.start()
        }
    }

    private fun View.setOnEnterListener() {
        this.setOnKeyListener { _, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
                if (login.isEmpty()) {
                    binding.loginEdit.error = getString(R.string.no_login)
                } else if (password.isEmpty()) {
                    binding.passwordEdit.error = getString(R.string.no_password)
                } else if (login != "maybebaby@haram.ru" && password != "antiharamsoftware") {
                    binding.passwordEdit.error = getString(R.string.no)
                } else
                    media.start()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun sugma() {
        isNight = !isNight
        AppCompatDelegate.setDefaultNightMode(
            if (isNight) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    override fun onSaveInstanceState(
        outState: Bundle
    ) {
        super.onSaveInstanceState(outState)
        outState.putString("login", login)
        outState.putString("password", password)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        login = savedInstanceState.getString("login").toString()
        password = savedInstanceState.getString("password").toString()
    }
}



