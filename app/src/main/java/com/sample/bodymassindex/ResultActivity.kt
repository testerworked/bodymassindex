package com.sample.indexbodychecker

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.sample.bodymassindex.R

class ResultActivity : AppCompatActivity() {

    private lateinit var viewModel: MyObserver

    private val tvBmi by lazy {
        findViewById<TextView>(R.id.tvBmi)
    }
    private val tvRecommendations by lazy {
        findViewById<TextView>(R.id.tvRecommendations)
    }
    private val ivBmiCategory by lazy {
        findViewById<ImageView>(R.id.ivBmiCategory)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        viewModel = ViewModelProvider(this).get(MyObserver::class.java)

        val height = intent.getDoubleExtra("height", 0.0)
        val weight = intent.getDoubleExtra("weight", 0.0)
        viewModel.calculateBMI(height, weight)

        viewModel.bmi.observe(this, { bmi ->
            tvBmi.text = "Ваш индекс массы тела: $bmi"
        })

        viewModel.bmiCategory.observe(this, { category ->
            ivBmiCategory.setImageResource(viewModel.getImageResource(category))
        })

        viewModel.recommendations.observe(this, { recommendations ->
            tvRecommendations.text = recommendations
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}