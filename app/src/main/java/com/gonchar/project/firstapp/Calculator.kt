package com.gonchar.project.firstapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gonchar.project.firstapp.calc.calc.CalcCore
import com.gonchar.project.firstapp.calc.calc.ExpressionCorrector
import com.gonchar.project.firstapp.calc.calc.PolishNotation
import com.gonchar.project.firstapp.calc.calc.StringParsing
import com.gonchar.project.firstapp.databinding.ActivityCalculatorBinding
import com.gonchar.project.firstapp.utils.Constants.Companion.DEFAULT_ERROR_CODE
import com.gonchar.project.firstapp.utils.Constants.Companion.TAG
import com.gonchar.project.firstapp.utils.Utils

class Calculator : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCalculatorBinding
    private var expression = StringBuilder()
    private val ec = ExpressionCorrector()
    private val calculate = CalcCore()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        initListener()
        setContentView(binding.root)

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

        when (v!!.id) {
            R.id.btnOne -> expression.append(getString(R.string.calc_btn_one))
            R.id.btnTwo -> expression.append(getString(R.string.calc_btn_two))
            R.id.btnThree -> expression.append(getString(R.string.calc_btn_three))
            R.id.btnFour -> expression.append(getString(R.string.calc_btn_four))
            R.id.BtnFive -> expression.append(getString(R.string.calc_btn_five))
            R.id.btnSix -> expression.append(getString(R.string.calc_btn_six))
            R.id.btnSeven -> expression.append(getString(R.string.calc_btn_seven))
            R.id.btnEight -> expression.append(getString(R.string.calc_btn_eight))
            R.id.btnNine -> expression.append(getString(R.string.calc_btn_nine))
            R.id.btnZero -> expression.append(getString(R.string.calc_btn_zero))
            R.id.btnDivide -> {
                symbolAppend(getString(R.string.calc_btn_divide), getString(R.string.wrong_math_sequence))
            }
            R.id.btnMultiply -> {
                symbolAppend(getString(R.string.calc_btn_multiply), getString(R.string.wrong_math_sequence))
            }
            R.id.btnMinus -> {
                symbolAppend(getString(R.string.calc_btn_minus), getString(R.string.wrong_math_sequence))
            }
            R.id.btnPlus -> {
                symbolAppend(getString(R.string.calc_btn_plus), getString(R.string.wrong_math_sequence))
            }
            R.id.btnComma -> {
                symbolAppend(getString(R.string.calc_def_comma), getString(R.string.wrong_math_sequence))
            }
            R.id.btnC -> expression.clear()
            R.id.btnResult -> {
                val rExp = StringParsing().parsString(expression);
                expression.clear()
                expression.append(calculate.calc(PolishNotation().makeSequence(rExp)))
            }
        }
        binding.tvResult.text = expression.toString()
    }

    /**
     * Method checks possibility to adds symbol to expression, if it impossible - shows
     * error message
     * @param symbol - symbol for added
     * @param eMessage - error message
     */
    private fun symbolAppend(symbol: String, eMessage: String) {
        if (ec.symbolEditor(expression, symbol) == DEFAULT_ERROR_CODE) {
            Utils().showErrMessage(eMessage, this)
        }
    }
}
