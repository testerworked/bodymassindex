package com.sample.bodymassindex

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sample.indexbodychecker.ResultActivity

class MainActivity : AppCompatActivity() {

    private val calcB by lazy {
        findViewById<Button>(R.id.calcB)
    }
    private val heightET by lazy {
        findViewById<EditText>(R.id.heightET)
    }
    private val weightET by lazy {
        findViewById<EditText>(R.id.weightET)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        calcB.setOnClickListener {
            val height = heightET.text.toString().toDoubleOrNull()
            val weight = weightET.text.toString().toDoubleOrNull()

            if (height != null && weight != null) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("height", height)
                intent.putExtra("weight", weight)
                startActivity(intent)
            } else {

            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        toastActivityState("ON_START")
    }

    override fun onResume() {
        super.onResume()
        toastActivityState("ON_RESUME")
    }

    override fun onPostResume() {
        super.onPostResume()
        toastActivityState("ON_POST_RESUME")
    }

    override fun onPause() {
        super.onPause()
        toastActivityState("ON_PAUSE")
    }

    private fun toastActivityState(message: String){
        Toast.makeText(this, "${lifecycle.currentState}, $message", Toast.LENGTH_LONG).show()
    }
}