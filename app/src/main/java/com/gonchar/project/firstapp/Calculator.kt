package com.gonchar.project.firstapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.gonchar.project.firstapp.calc.calc.*
import com.gonchar.project.firstapp.databinding.ActivityCalculatorBinding
import com.gonchar.project.firstapp.utils.Constants.Companion.EMPTY_STRING
import com.gonchar.project.firstapp.utils.Constants.Companion.TAG
import com.gonchar.project.firstapp.utils.showErrMessage

class Calculator : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCalculatorBinding
    private var expression = StringBuilder()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        initListener()
        setContentView(binding.root)
        title = getString(R.string.calc_tb_title)
    }

    /**
     * this method set clickListener to element which needed be clickable
     */
    private fun initListener() {
        binding.btnOne.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)
        binding.btnThree.setOnClickListener(this)
        binding.btnFour.setOnClickListener(this)
        binding.BtnFive.setOnClickListener(this)
        binding.btnSix.setOnClickListener(this)
        binding.btnSeven.setOnClickListener(this)
        binding.btnEight.setOnClickListener(this)
        binding.btnNine.setOnClickListener(this)
        binding.btnZero.setOnClickListener(this)
        binding.btnC.setOnClickListener(this)
        binding.btnDivide.setOnClickListener(this)
        binding.btnMultiply.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
        binding.btnPlus.setOnClickListener(this)
        binding.btnResult.setOnClickListener(this)
        binding.btnComma.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when {
            v!!.id == R.id.btnC -> {
                expression.clear()
                displayResult(EMPTY_STRING)
            }
            v.id == R.id.btnResult -> {
                if (lustValCheck(expression)){
                    resultAppend()
                    displayResult(expression.toString())
                }else{
                    showErrMessage(getString(R.string.wrong_math_sequence), this)
                }
            }
            else -> {
                symbolAppend((v as Button).text.toString())
            }
        }
    }

    /**
     * resultAppend method makes the current expression empty and append value after calculations.
     */
    private fun resultAppend() {
        val tempExpression = StringParser().makeTempString(expression)
        expression.clear()
        expression.append(CalcCore().calc(PolishNotation().makeSequence(tempExpression)))
    }

    /**
     * displayResult method set to the textView field the total expression value after
     * any buttons click.
     * @param res total expression value
     */
    private fun displayResult(res: String) {
        binding.tvResult.text = res
    }

    /**
     * symbolAppend method add symbol to the expression or calls the method which showing error message,
     * also calls the method for update displayed expression.
     * @param symbol - symbol for added
     */
    private fun symbolAppend(symbol: String) {
        if(symbol.isDigitsOnly()){
            Log.d(TAG, "symbolAppend isDigitsOnly fun")
            expression.append(symbol)
        }else{
            if (!symbolCheck(expression, symbol)) {
                showErrMessage(getString(R.string.wrong_math_sequence), this)
            }
        }
        displayResult(expression.toString())
    }
}
