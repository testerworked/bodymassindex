package com.sample.indexbodychecker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.sample.bodymassindex.R

class MyObserver : ViewModel() {
    private val checkBmi = MutableLiveData<Double>()
    private val checkBmiCategory = MutableLiveData<String>()
    private val checkRecommend = MutableLiveData<String>()

    val bmi: MutableLiveData<Double> get() = checkBmi
    val bmiCategory: MutableLiveData<String> get() = checkBmiCategory
    val recommendations: MutableLiveData<String> get() = checkRecommend

    fun calculateBMI(height: Double, weight: Double) {
        val bmiValue = weight / (height / 100 * height / 100)
        checkBmi.value = bmiValue
        checkBmiCategory.value = getBMICategory(bmiValue)
        checkRecommend.value = getRecommendations(getBMICategory(bmiValue))
    }

    private fun getBMICategory(bmi: Double): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal"
            bmi in 25.0..29.9 -> "Overweight"
            else -> "Obese"
        }
    }

    private fun getRecommendations(category: String): String {
        return when (category) {
            "Underweight" -> "У вас недостаточный вес. Подумайте о том, чтобы увеличить потребление калорий и проконсультироваться с диетологом"
            "Normal" -> "У вас нормальный вес. Придерживайтесь сбалансированной диеты и регулярно занимайтесь спортом"
            "Overweight" -> "У вас избыточный вес. Сократите потребление калорий и увеличьте физическую активность"
            else -> "У вас ожирение. Значительно сократите потребление калорий и регулярно занимайтесь физическими упражнениями. Проконсультируйтесь с врачом"
        }
    }

    fun getImageResource(category: String): Int {
        return when (category) {
            "Underweight" -> R.drawable.underweight
            "Normal" -> R.drawable.normal
            "Overweight" -> R.drawable.overweight
            else -> R.drawable.obese
        }
    }
}