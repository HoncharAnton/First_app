package com.gonchar.project.firstapp.calc.calc

import android.util.Log
import com.gonchar.project.firstapp.utils.Constants.Companion.COMMA
import com.gonchar.project.firstapp.utils.Constants.Companion.MINUS
import com.gonchar.project.firstapp.utils.Constants.Companion.RANGE_CORRECTOR_NEGATIVE
import com.gonchar.project.firstapp.utils.Constants.Companion.TAG


/**
 * Method checks possibility to correctly add math symbol to expression and adds if
 * expression will have correct math structure
 * @param exp exp user expression for check
 * @param sing math symbol which need add to expression
 * @return DEFAULT_ERROR_CODE (-1) if math symbol can`t be adds to the expression
 * or POSITIVE_RESULT value if symbol was add to expression
 */
fun symbolCheck(exp: StringBuilder, sing: String): Boolean {

    if (sing != COMMA && preSymbolCheck(sing, exp) ||
        sing == COMMA && preCommaCheck(exp)
    ) {
        Log.d(TAG, "symbolCheck return true")
        exp.append(sing)
        return true
    }
    return false
}

/**
 * Method checks previous comma position between the last expression index to the last
 * expression math symbol. Returns boolean value, if comma wasn`t fount in this range
 * returns true.
 * @param exp user expression for check last comma position
 * @return true if comma can be added to expression
 */
private fun preCommaCheck(exp: StringBuilder): Boolean {
    if (exp.isNotEmpty() && exp.last().isDigit()) {
        for (i in exp.length downTo 1) {
            if (exp[exp.lastIndex].isDigit() && !exp[i + RANGE_CORRECTOR_NEGATIVE].isDigit()
                && exp[i + RANGE_CORRECTOR_NEGATIVE].toString() != COMMA
            ) {
                Log.d(TAG, "preCommaCheck return true")
                return true
            } else if (exp[i + RANGE_CORRECTOR_NEGATIVE].toString() == COMMA) {
                return false
            }
        }
        return true
    }
    return false
}

/**
 * method checks previous symbol and returns boolean value ( to make decided for added
 * math symbol or not) it also take into account the negative value, double minus
 * @param sing math symbol which need add to expression
 * @param exp user expression for check previous symbol
 * @return true if sing can be added to expression
 */
private fun preSymbolCheck(sing: String, exp: StringBuilder): Boolean {

    if (exp.isNotEmpty()) {
        for (i in exp.length downTo 1) {
            return (exp[i + RANGE_CORRECTOR_NEGATIVE].isDigit()
                    || exp.length >= 2 && !exp[exp.lastIndex].isDigit()
                    && exp[exp.lastIndex - 1].isDigit() && sing == MINUS)
        }
    }
    return sing == MINUS
}

/**
 * lastValCheck checks expression is it completed
 * @param exp expression for check
 * @return true if expression is completed
 */
fun lustValCheck(exp: StringBuilder): Boolean {
    if (exp.isNotEmpty() && exp.last().isDigit()){
        return true
    }
     return false
}