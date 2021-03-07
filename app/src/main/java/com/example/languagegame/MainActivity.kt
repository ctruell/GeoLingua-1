package com.example.languagegame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //button handlers
        playButton.setOnClickListener { view ->
            handleButtonClick(view)
        }
        langButton.setOnClickListener { view ->
            handleButtonClick(view)
        }
    }

    fun handleButtonClick(view: View) {
        with(view as Button) {
            //go to game/quiz
            if (view.text == "Play") {
                val intentMain = Intent(this@MainActivity,
                    QuizFragment::class.java
                )
                this@MainActivity.startActivity(intentMain)
                //Log.i("Content ", " Main layout ")
            }
            //go to languages activity
            if (view.text == "Languages") {
                val intentMain = Intent(this@MainActivity,
                    LanguageFragment::class.java
                )
                this@MainActivity.startActivity(intentMain)
            }
        }
    }
}