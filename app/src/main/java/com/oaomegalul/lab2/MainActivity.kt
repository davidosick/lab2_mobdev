package com.oaomegalul.lab2

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var method = 0;
        val number_field_1 = findViewById<EditText>(R.id.number_field_1)
        val number_field_2 = findViewById<EditText>(R.id.number_field_2)
        val button_calculate = findViewById<Button>(R.id.addButton)
        val clearButton = findViewById<ImageButton>(R.id.clearButton)
        val label_text = findViewById<TextView>(R.id.textView)

        clearButton.setOnClickListener {
            number_field_1.text.clear()
            number_field_2.text.clear()
            label_text.setText(R.string.numbersAreClear)
        }

        button_calculate.setOnClickListener {
            val n1 = (number_field_1.text.toString()).toFloat()
            val n2 = (number_field_2.text.toString()).toFloat()
            val res: Float
            var errorOccurred = false

            when (method) {
                1 -> res = n1 - n2
                2 -> res = if (n2 != 0f) {
                    n1 / n2
                } else {
                    label_text.setText(R.string.zeroDivizion)
                    errorOccurred = true
                    0f
                }
                3 -> res = n1 * n2
                else -> res = n1 + n2
            }

            if(!errorOccurred) {
                label_text.setText(res.toString())
            }
        }

        val spinner: Spinner = findViewById(R.id.calcMethod)
        ArrayAdapter.createFromResource(
            this,
            R.array.calcMethodArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                method = position;
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                method = 0;
            }
        }

    }
}
