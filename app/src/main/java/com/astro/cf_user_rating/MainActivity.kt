package com.astro.cf_user_rating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var button:Button
    lateinit var editText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.buttonGetRating)
        editText = findViewById(R.id.editTextHandle)



    }

    fun btnClicked(view: View) {

        var intent:Intent = Intent(this,ChartActivity::class.java)
        startActivity(intent)

    }
}