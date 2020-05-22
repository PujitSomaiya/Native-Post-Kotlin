package com.tatvasoft.nativepostkotlin.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tatvasoft.nativepostkotlin.R
import com.tatvasoft.nativepostkotlin.ui.post.view.PostActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, PostActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}
