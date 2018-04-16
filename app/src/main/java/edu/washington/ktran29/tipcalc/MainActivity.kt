package edu.washington.ktran29.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.widget.Toast
import android.text.Selection
import android.text.InputFilter
import android.widget.Spinner
import android.util.Log


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private var tipButton : Button? = null
    private var tipText : EditText? = null
    private var tipSpinner : Spinner? = null
    private var containsDecimal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipText = findViewById(R.id.editText)
        tipButton = findViewById(R.id.button)
        tipSpinner = findViewById(R.id.spinner)


        tipText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                var tipTextString = tipText?.text.toString()

                if (tipTextString == "$") {
                    tipText?.setText("")
                } else if (tipTextString.isNotEmpty() && !tipTextString.contains("$")) {
                    tipText?.setText("$$tipTextString")

                    tipTextString = tipText?.text.toString()

                    val position = tipTextString.length
                    Selection.setSelection(tipText?.text, position)
                }

                if (tipTextString.contains(".") && !containsDecimal) {
                    tipText?.filters = arrayOf(InputFilter.LengthFilter(tipTextString.length + 2))
                    containsDecimal = true
                } else if (!tipTextString.contains(".")){
                    tipText?.filters = arrayOf()
                    containsDecimal = false
                }

                tipButton?.isEnabled = tipTextString.isNotEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })

        tipButton?.setOnClickListener { tipButtonClicked() }
    }

    private fun tipButtonClicked() {
        val tipStringPercentage = tipSpinner?.selectedItem.toString()
        val tipPercentage = tipStringPercentage.substring(0, tipStringPercentage.length - 1).toDouble()

        val tip = "%.2f".format(tipText?.text.toString().substring(1).toDouble() * tipPercentage / 100)

        Toast.makeText(this, "$$tip", Toast.LENGTH_SHORT).show()
        tipText?.setText("")
    }
}
