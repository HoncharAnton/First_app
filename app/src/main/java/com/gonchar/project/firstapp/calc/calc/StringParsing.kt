package com.gonchar.project.firstapp.calc.calc

import com.gonchar.project.firstapp.utils.Constants.Companion.DEFAULT_ERROR_CODE
import com.gonchar.project.firstapp.utils.Constants.Companion.EMPTY_STRING
import java.lang.StringBuilder
import kotlin.system.exitProcess

class StringParsing {
    private val parsLines: ArrayList<ArrayList<Char>> = ArrayList()

    /**
     * array list with all math operators which used in parsString method
     */
    private val mathOperators = charArrayOf('-', '+', '/', '*', '^', '(', ')')


    /**
     * this method create string with one element of the expression and put it in array list
     * @param line character array list with all symbols if the user value
     * @return array list with string where string is one element of the user expression
     */
    fun parsString(line: StringBuilder): ArrayList<String> {
        val expression: ArrayList<String> = ArrayList()
        var element = EMPTY_STRING
        var a = 0
        while (a < line.length) {
            for (operator: Char in mathOperators) {
                try {
                    if (line[a] == operator && a != 0 || a == 0 && line[a] == '(') {
                        expression.add(element)
                        element = EMPTY_STRING
                        element += line[a]
                        if (line[a] != ')' && a != line.lastIndex) {
                            expression.add(element)
                            a++
                        }
                        element = EMPTY_STRING
                    }
                } catch (e: IndexOutOfBoundsException) {
                    exitProcess(DEFAULT_ERROR_CODE)
                }
            }
            element += line[a]
            if (a == line.lastIndex) {
                expression.add(element)
            }
            a++
        }
        return expression
    }

    /**
     * this method make parsing argument string & put variable &value in Map.
     * (variable - key (String); value - value (Double))
     * @return map with all arguments.
     */
    fun parsArgument(): Map<String, Double> {
        val expressionMap: MutableMap<String, Double> = HashMap()
        var part = EMPTY_STRING
        var key = EMPTY_STRING
        if (parsLines.size > 1) {
            for (b in 1 until parsLines.size) {
                for (c in 0 until parsLines[b].size) {
                    if (parsLines[b][c] != '=') {
                        part += (parsLines[b][c]).toString()
                    } else if (parsLines[b][c] == '=') {
                        key = part
                        part = EMPTY_STRING
                    }
                }
                try {
                    checkOperatorOfTheKey(key)
                    expressionMap[key] = part.toDouble()
                } catch (e: NumberFormatException) {
                }
            }
        }
        return expressionMap
    }

    /**
     * this method performs the control function, here check write of the variable,
     * if first symbol of variable is digit or math operator - this method stop program working!
     * @param key - it is variable which symbol will by checked
     */
    private fun checkOperatorOfTheKey(key: String) {
        val charKey = key.toCharArray()
        if (!Character.isLetter(charKey[0]) && !Character.isDigit(charKey[0])) {
            exitProcess(DEFAULT_ERROR_CODE)
        } else if (Character.isDigit(charKey[0])) {
            exitProcess(DEFAULT_ERROR_CODE)
        }
    }
}