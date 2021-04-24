package com.example.foodunitconverter

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.foodunitconverter.databinding.ActivityMainBinding
import java.lang.String.format
import java.util.Collections.list
import kotlin.math.ceil
import kotlin.math.round
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.convertButton.setOnClickListener { convertFoodUnit() }
        binding.amountToConvertEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }

    private fun convertFoodUnit() {
        val stringInTextField = binding.amountToConvertEditText.text.toString()
        val amount = stringInTextField.toDoubleOrNull()
        if (amount == null) {
            return
        }
        val convert = binding.convertOption.checkedRadioButtonId


        var conversion = when(convert) {
            R.id.option_kg1_to_gm -> amount * 1000
            R.id.option_gm1_to_kg -> amount / 1000
            R.id.option_gm1_to_milligrams -> amount * 1000
            else -> amount /1000
        }
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            conversion = round(conversion * 1000) /1000
        }

        binding.convertResult.text = conversion.toString()

    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}