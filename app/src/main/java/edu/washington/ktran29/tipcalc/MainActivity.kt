package edu.washington.ktran29.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.widget.Toast
import android.text.Selection



class MainActivity : AppCompatActivity() {

    private var tipButton : Button? = null
    private var tipText : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tipText = findViewById(R.id.editText)
        tipButton = findViewById(R.id.button)


        tipText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                val tipTextString = tipText?.text.toString()

                if (tipTextString == "$") {
                    tipText?.setText("")
                } else if (tipTextString.isNotEmpty() && !tipTextString.contains("$")) {
                    tipText?.setText("$$tipTextString")

                    val position = tipTextString.length + 1
                    Selection.setSelection(tipText?.text, position)
                }

                tipButton?.isEnabled = tipTextString.isNotEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })

        tipButton?.setOnClickListener { tipButtonClicked() }
    }

    private fun tipButtonClicked() {

        val tip = "%.2f".format(Integer.parseInt(tipText?.text.toString().substring(1)) * 0.15)

        Toast.makeText(this, "$$tip", Toast.LENGTH_SHORT).show()
        tipText?.setText("")
    }
}
