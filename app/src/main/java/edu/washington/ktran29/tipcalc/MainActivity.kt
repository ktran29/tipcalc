package edu.washington.ktran29.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.widget.Toast

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
                tipButton?.isEnabled = tipText?.text.toString().isNotEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        tipButton?.setOnClickListener { tipButtonClicked() }
    }

    private fun tipButtonClicked() {
        Toast.makeText(this, tipText?.text.toString(), Toast.LENGTH_SHORT).show()
        tipText?.setText("")
    }
}
