package com.ziaad.startup.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ziaad.startup.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var btnHex: Button
    lateinit var btnOctal: Button
    lateinit var btnDec: Button
    lateinit var btnBin: Button
    lateinit var tvInput: TextView
    lateinit var btnEqual: Button
    lateinit var btnDel: Button
    lateinit var btnClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intinalize()
        decimal()

        btnDec.setOnClickListener {
            default()
            decimal()
        }

        btnOctal.setOnClickListener {
            default()
            octal()
        }

        btnHex.setOnClickListener {
            default()
            hexa()
        }
        btnBin.setOnClickListener {
            default()
            binary()
        }


    }

    fun intinalize() {
        tvInput = findViewById(R.id.tv_Input)
        btnHex = findViewById(R.id.btnHex)
        btnOctal = findViewById(R.id.btnOctal)
        btnDec = findViewById(R.id.btnDec)
        btnBin = findViewById(R.id.btnBin)
        btnEqual = findViewById(R.id.btnEqual)
        btnDel = findViewById(R.id.btnDel)
        btnClear = findViewById(R.id.btnClear)
    }

    fun default() {
        tvInput.text = ""
        tv_ResultHex.text = "0"
        tv_ResultBinary.text = "0"
        tv_ResultDec.text = "0"
        tv_ResultOctal.text = "0"
    }

    fun onClickNumber(view: View) {
        var numDigit = (view as Button).text.toString()
        var oldNum = tvInput.text.toString()
        var newNum = oldNum + numDigit
        tvInput.text = newNum
    }

    fun onClick(view: View) {
        when (view) {
            btnClear -> tvInput.text = ""
            btnDel -> {
                var num = tvInput.text.toString()
                var newNum = num.dropLast(1)
                tvInput.text = newNum
            }
            btnEqual -> {
                if (btnDec.isSelected) {
                    decInput()
                } else if (btnHex.isSelected) {
                    hexInput()
                } else if (btnOctal.isSelected) {
                    octalInput()
                } else if (btnBin.isSelected) {
                    binaryInput()
                }
            }
        }
    }

    fun decimal() {
        disSelectedButtons(btnHex, btnOctal, btnBin)
        enableButton(btnHex, btnOctal, btnBin)
        btnDec.isSelected = true
        btnDec.isEnabled = false
        disableAlpha()
        enableNum()
        decInput()

    }

    fun binary() {
        disSelectedButtons(btnHex, btnOctal, btnDec)
        enableButton(btnHex, btnOctal, btnDec)
        btnBin.isEnabled = false
        btnBin.isSelected = true
        disableAlpha()
        disbleNum()
        binaryInput()
    }

    fun hexa() {
        enableButton(btnBin, btnOctal, btnDec)
        disSelectedButtons(btnBin, btnDec, btnOctal)
        btnHex.isEnabled = false
        btnHex.isSelected = true
        enableAlpha()
        enableNum()
        hexInput()
    }

    fun octal() {
        disSelectedButtons(btnDec, btnBin, btnHex)
        enableButton(btnHex, btnBin, btnDec)
        btnOctal.isEnabled = false
        btnOctal.isSelected = true
        disableAlpha()
        enableNum()
        octalInput()
    }

    fun disableAlpha() {
        disableButton(btnA, btnB, btnC, btnD, btnE, btnF)
    }

    fun enableAlpha() {
        enableButton(btnA, btnB, btnC, btnD, btnE, btnF)
    }

    fun enableNum() {
        enableButton(btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9)
        if (btnOctal.isSelected) {
            btnNum8.isEnabled = false
            btnNum9.isEnabled = false
        }
    }

    fun disbleNum() {
        disableButton(btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9)
    }


    fun decInput() {
        if (tvInput.text.isNotEmpty()) {
            var num = tvInput.text.toString()

            var dec = num.toLong()

            var hex = dec.toULong().toString(16)
            var octal = dec.toULong().toString(8)
            var bin = dec.toULong().toString(2)

            showInResult(dec.toString(), hex, octal, bin)
        }
    }

    fun hexInput() {
        if (tvInput.text.isNotEmpty()) {
            var num = tvInput.text.toString()

            var dec: Long = Integer.parseInt(num, 16).toLong()
            var hex = dec.toULong().toString(16)
            var bin = dec.toULong().toString(2)
            var octal = dec.toULong().toString(8)

            showInResult(dec.toString(), hex, octal, bin)
        }
    }


    fun octalInput() {
        if (tvInput.text.isNotEmpty()) {
            var num = tvInput.text.toString()

            var dec = Integer.parseInt(num, 8).toLong()
            var hex = dec.toULong().toString(16)
            var bin = dec.toULong().toString(2)
            var octal = dec.toULong().toString(8)

            showInResult(dec.toString(), hex, octal, bin)
        }
    }

    fun binaryInput() {
        if (tvInput.text.isNotEmpty()) {
            var num = tvInput.text.toString()

            var dec = Integer.parseInt(num, 2).toLong()
            var hex = dec.toULong().toString(16)
            var octal = dec.toULong().toString(8)
            var bin = dec.toULong().toString(2)

            showInResult(dec.toString(), hex, octal, bin)
        }
    }


    fun showInResult(dec: String, hex: String, octal: String, bin: String) {
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