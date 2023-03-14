package com.ziaad.startup.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ziaad.startup.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var btnHex: Button
    private lateinit var btnOctal: Button
    private lateinit var btnDec: Button
    private lateinit var btnBin: Button
    private lateinit var screenView: TextView
    private lateinit var btnEqual: Button
    private lateinit var btnDel: Button
    private lateinit var btnClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        enableDecimalOperation()

        btnDec.setOnClickListener {
            default()
            enableDecimalOperation()
        }

        btnOctal.setOnClickListener {
            default()
            enableOctalButtonOperation()
        }

        btnHex.setOnClickListener {
            default()
            enableHexaButtonOperation()
        }
        btnBin.setOnClickListener {
            default()
            enableBinaryButtonOperation()
        }


    }

    private fun initialize() {
        screenView = findViewById(R.id.tv_Input)
        btnHex = findViewById(R.id.btnHex)
        btnOctal = findViewById(R.id.btnOctal)
        btnDec = findViewById(R.id.btnDec)
        btnBin = findViewById(R.id.btnBin)
        btnEqual = findViewById(R.id.btnEqual)
        btnDel = findViewById(R.id.btnDel)
        btnClear = findViewById(R.id.btnClear)
    }

    private fun default() {
        screenView.text = ""
        tv_ResultHex.text = "0"
        tv_ResultBinary.text = "0"
        tv_ResultDec.text = "0"
        tv_ResultOctal.text = "0"
    }

    fun onClickNumber(view: View) {
        val numDigit = (view as Button).text.toString()
        val oldNum = screenView.text.toString()
        val newNum = oldNum + numDigit
        screenView.text = newNum
    }

    fun onClick(view: View) {
        when (view) {
            btnClear -> screenView.text = ""
            btnDel -> {
                val num = screenView.text.toString()
                val newNum = num.dropLast(1)
                screenView.text = newNum
            }
            btnEqual -> {
                if (btnDec.isSelected) {
                    inputOfDec()
                } else if (btnHex.isSelected) {
                    inputOfHexa()
                } else if (btnOctal.isSelected) {
                    inputOfOctal()
                } else if (btnBin.isSelected) {
                    inputOfBinary()
                }
            }
        }
    }

    private fun enableDecimalOperation() {
        disSelectedButtons(btnHex, btnOctal, btnBin)
        enableButton(btnHex, btnOctal, btnBin)
        btnDec.isSelected = true
        btnDec.isEnabled = false
        disableAlpha()
        enableAllNumber()
        inputOfDec()

    }

    private fun enableBinaryButtonOperation() {
        disSelectedButtons(btnHex, btnOctal, btnDec)
        enableButton(btnHex, btnOctal, btnDec)
        btnBin.isEnabled = false
        btnBin.isSelected = true
        disableAlpha()
        enableBinaryNumber()
        inputOfBinary()
    }

    private fun enableHexaButtonOperation() {
        enableButton(btnBin, btnOctal, btnDec)
        disSelectedButtons(btnBin, btnDec, btnOctal)
        btnHex.isEnabled = false
        btnHex.isSelected = true
        enableAlpha()
        enableAllNumber()
        inputOfHexa()
    }

    private fun enableOctalButtonOperation() {
        disSelectedButtons(btnDec, btnBin, btnHex)
        enableButton(btnHex, btnBin, btnDec)
        btnOctal.isEnabled = false
        btnOctal.isSelected = true
        disableAlpha()
        enableAllNumber()
        inputOfOctal()
    }

    private fun disableAlpha() {
        disableButton(btnA, btnB, btnC, btnD, btnE, btnF)
    }

    private fun enableAlpha() {
        enableButton(btnA, btnB, btnC, btnD, btnE, btnF)
    }

    private fun enableAllNumber() {
        enableButton(btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9)
        if (btnOctal.isSelected) {
            btnNum8.isEnabled = false
            btnNum9.isEnabled = false
        }
    }

    private fun enableBinaryNumber() {
        disableButton(btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9)
    }


    private fun inputOfDec() {
        if (screenView.text.isNotEmpty()) {
            val num = screenView.text.toString()

            val dec = num.toLong()

            val hex = dec.toULong().toString(16)
            val octal = dec.toULong().toString(8)
            val bin = dec.toULong().toString(2)

            showResult(dec.toString(), hex, octal, bin)
        }
    }

    private fun inputOfHexa() {
        if (screenView.text.isNotEmpty()) {
            val num = screenView.text.toString()

            val dec: Long = Integer.parseInt(num, 16).toLong()
            val hex = dec.toULong().toString(16)
            val bin = dec.toULong().toString(2)
            val octal = dec.toULong().toString(8)

            showResult(dec.toString(), hex, octal, bin)
        }
    }


    private fun inputOfOctal() {
        if (screenView.text.isNotEmpty()) {
            val num = screenView.text.toString()

            val dec = Integer.parseInt(num, 8).toLong()
            val hex = dec.toULong().toString(16)
            val bin = dec.toULong().toString(2)
            val octal = dec.toULong().toString(8)

            showResult(dec.toString(), hex, octal, bin)
        }
    }

    private fun inputOfBinary() {
        if (screenView.text.isNotEmpty()) {
            val num = screenView.text.toString()

            val dec = Integer.parseInt(num, 2).toLong()
            val hex = dec.toULong().toString(16)
            val octal = dec.toULong().toString(8)
            val bin = dec.toULong().toString(2)

            showResult(dec.toString(), hex, octal, bin)
        }
    }


    private fun showResult(dec: String, hex: String, octal: String, bin: String) {
        tv_ResultBinary.text = bin
        tv_ResultDec.text = dec
        tv_ResultOctal.text = octal
        tv_ResultHex.text = hex
    }

    private fun disSelectedButtons(vararg button: Button) {
        button.forEach { it.isSelected = false }
    }

    private fun disableButton(vararg button: Button) {
        button.forEach { it.isEnabled = false }
    }

    private fun enableButton(vararg button: Button) {
        button.forEach { it.isEnabled = true }
    }

}