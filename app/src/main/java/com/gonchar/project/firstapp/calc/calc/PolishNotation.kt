package com.gonchar.project.firstapp.calc.calc

import com.gonchar.project.firstapp.utils.Constants.Companion.DEFAULT_ERROR_CODE
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class PolishNotation {
    /**
     * this is array with name of mathematical functions. used for compare user expression
     */
    private var function = arrayOf(
        "sin", "-sin", "cos", "-cos", "tan", "-tan", "atan", "-atan", "log10", "-log10",
        "log2", "-log2", "sqrt", "-sqrt"
    )

    /**
     * array list with expression in polish notation format
     */
    private val polishNotation: ArrayList<String> = ArrayList()

    /**
     * this is stack
     */
    private val stack: Stack<String> = Stack()

    /**
     * This method make sequence.This method sorts the elements of the expression by value
     * (if the element is a letter or a number, it adds it to PolishNotation, if a math operator
     * adds it to the stack first and at a certain moment adds it to the PolishNotation)
     * @param expression it is a user string after parsing
     */
    fun makeSequence(expression: ArrayList<String>): ArrayList<String> {
        var b = 0
        while (b < expression.size) {
            b = checkForFunction(expression[b], b)
            val charOperand = expression[b].toCharArray()
            for (a in charOperand.indices) {
                if (Character.isLetter(charOperand[a]) || Character.isDigit(
                        charOperand[a]
                    )
                ) {
                    addValue(expression[b], charOperand[a])
                    break
                } else if (expression[b] == ")") {
                    while (!stack.peek().equals("(")) {
                        polishNotation.add(stack.pop())
                    }
                    stack.pop()
                } else if (charOperand.size == 1 && !Character.isLetter(charOperand[a]) && !Character.isDigit(
                        charOperand[a]
                    )
                ) addMathOperators(expression[b])
            }
            if (b == expression.size - 1 && !stack.empty()) {
                while (!stack.empty()) {
                    polishNotation.add(stack.pop())
                }
            }
            b++
        }
        nPrint(expression)
        return polishNotation
    }

    /**
     * this method check & add value or letters in array list PolishNotation
     * (add only operand, not operator)
     * @param operand this is operand of the user value
     * @param symbol this is symbol from operand of the user value
     */
    private fun addValue(operand: String, symbol: Char) {
        if (Character.isLetter(symbol)) {
            if (StringParser().parsArgument()[operand] != null) {
                polishNotation.add(StringParser().parsArgument()[operand].toString())
            } else {
                exitProcess(DEFAULT_ERROR_CODE)
            }
        }
        if (Character.isDigit(symbol)) polishNotation.add(operand)
    }

    /**
     * This method add math operator in stack & at certain moment adds it to the PolishNotation
     * @param operator it is math operators
     */
    private fun addMathOperators(operator: String) {
        if (stack.empty()) {
            stack.push(operator)
        } else {
            if (operator == "(") {
                stack.push(operator)
            } else if (makePriority(stack.peek()) < makePriority(operator)) {
                stack.push(operator)
            } else if (makePriority(stack.peek()) >= makePriority(operator)) {
                polishNotation.add(stack.pop())
                while (!stack.empty()) {
                    if (makePriority(stack.peek()) >= makePriority(operator)) polishNotation.add(
                        stack.pop()
                    ) else break
                }
                stack.push(operator)
            }
        }
    }

    /**
     * this method determines the priority of performing mathematical operations.
     * ( -1 - low priority; 4 high priority)
     * @param operator it is math operator
     * @return operator priority value ( -1 - low priority; 4 high priority)
     */
    private fun makePriority(operator: String): Int {
        if (operator == "(") return 0
        if (operator == "+" || operator == "-") return 1
        if (operator == "*" || operator == "/") return 2
        if (operator == "^" || operator == "sqrt") return 3
        if (operator == "sin" || operator == "-sin" || operator == "cos" || operator == "-cos" || operator == "tan" || operator == "-tan" || operator == "atan" || operator == "log10" || operator == "log2") return 4
        return if (operator == ")") -1 else -2
    }

    /**
     * This method displays the contents of array list "expression" and array list "PolishNotation" in the console
     * @param expression array list with user string after parsing
     */
    private fun nPrint(expression: ArrayList<String>) {
        for (operand in expression) {
            print(operand)
        }
        for (notation in polishNotation) {
            print(notation)
        }
    }

    /**
     * this method check operator (if operator is math function)
     * if there is true - operator tut in stack & iterator make+1
     * @param operator it is segment of user value after parsing
     * @param b it is iterator
     * @return iterator+1 used in makeSequence method
     */
    private fun checkForFunction(operator: String, b: Int): Int {
        var iterator = b
        for (one in function) {
            if (operator == one) {
                stack.push(operator)
                iterator++
            }
        }
        return iterator
    }
}