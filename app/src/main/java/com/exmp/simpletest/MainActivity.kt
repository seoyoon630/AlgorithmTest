package com.exmp.simpletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        confirm.setOnClickListener {
            number.text?.let {
                val resultText = StringBuilder()
                var number = it.toString()
                var dotIndex = number.indexOf(".")
                if (dotIndex == -1) {
                    number += ".0"
                    dotIndex = number.indexOf(".")
                }

                val intNum = number.substring(0, dotIndex)
                val prefix = DecimalFormat("#,##0").format(intNum.toInt())

                resultText.append(prefix)

                resultText.append(".")
                val decimalNum = number.substring(dotIndex + 1, number.length) + "00"
                resultText.append(decimalNum.substring(0, 2))
                result.text = resultText
            }
        }
    }
}
