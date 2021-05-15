package com.gonchar.project.firstapp.calc.calc

import java.util.*

class CalcCore {
    /**
     * This method compares the operand with a mathematical symbol and performs the appropriate actions.
     * @param polishNotation it is array list with expression in polish notation format
     * @return total result of the math operations
     */
    fun calc(polishNotation: ArrayList<String>): String {
        while (polishNotation.size != 1) {
            for (a in polishNotation.indices) {
                if (polishNotation[a] == "*") {
                    arrayCorrector(
                        polishNotation[a - 2].toDouble() * polishNotation[a - 1].toDouble(),
                        a,
                        polishNotation
                    )
                    break
                } else if (polishNotation[a] == "/") {
                    arrayCorrector(
                        polishNotation[a - 2].toDouble() / polishNotation[a - 1].toDouble(),
                        a,
                        polishNotation
                    )
                    break
                } else if (polishNotation[a] == "+") {
                    arrayCorrector(
                        polishNotation[a - 2].toDouble() + polishNotation[a - 1].toDouble(),
                        a,
                        polishNotation
                    )
                    break
                } else if (polishNotation[a] == "-") {
                    arrayCorrector(
                        polishNotation[a - 2].toDouble() - polishNotation[a - 1].toDouble(),
                        a,
                        polishNotation
                    )
                    break
                } else if (polishNotation[a] == "^") {
                    arrayCorrector(
                        Math.pow(
                            polishNotation[a - 2].toDouble(),
                            polishNotation[a - 1].toDouble()
                        ), a, polishNotation
                    )
                    break
                } else if (polishNotation[a] == "sin" || polishNotation[a] == "-sin") {
                    if (polishNotation[a] == "sin") arrayCorrectorForFunction(
                        Math.sin(
                            polishNotation[a - 1].toDouble()
                        ), a, polishNotation
                    ) else arrayCorrectorForFunction(
                        Math.sin(
                            polishNotation[a - 1].toDouble()
                        ) * -1, a, polishNotation
                    )
                    break
                } else if (polishNotation[a] == "cos" || polishNotation[a] == "-cos") {
                    if (polishNotation[a] == "cos") arrayCorrectorForFunction(
                        Math.cos(
                            polishNotation[a - 1].toDouble()
                        ), a, polishNotation
                    ) else arrayCorrectorForFunction(
                        Math.cos(
                            polishNotation[a - 1].toDouble()
                        ) * -1, a, polishNotation
                    )
                    break
                } else if (polishNotation[a] == "tan" || polishNotation[a] == "-tan") {
                    if (polishNotation[a] == "tan") arrayCorrectorForFunction(
                        Math.tan(
                            polishNotation[a - 1].toDouble()
                        ), a, polishNotation
                    ) else arrayCorrectorForFunction(
                        Math.tan(
                            polishNotation[a - 1].toDouble()
                        ) * -1, a, polishNotation
                    )
                    break
                } else if (polishNotation[a] == "atan" || polishNotation[a] == "-atan") {
                    if (polishNotation[a] == "atan") arrayCorrectorForFunction(
                        Math.atan(
                            polishNotation[a - 1].toDouble()
                        ), a, polishNotation
                    ) else arrayCorrectorForFunction(
                        Math.atan(
                            polishNotation[a - 1].toDouble()
                        ) * -1, a, polishNotation
                    )
                    break
                } else if (polishNotation[a] == "log10" || polishNotation[a] == "-log10") {
                    if (polishNotation[a] == "log10") arrayCorrectorForFunction(
                        Math.log10(
                            polishNotation[a - 1].toDouble()
                        ), a, polishNotation
                    ) else arrayCorrectorForFunction(
                        Math.log10(
                            polishNotation[a - 1].toDouble()
                        ) * -1, a, polishNotation
                    )
                    break
                } else if (polishNotation[a] == "log2" || polishNotation[a] == "-log2") {
                    if (polishNotation[a] == "log2") arrayCorrectorForFunction(
                        Math.log10(
                            polishNotation[a - 1].toDouble()
                        ) / Math.log(2.0), a, polishNotation
                    ) else arrayCorrectorForFunction(
                        Math.log10(
                            polishNotation[a - 1].toDouble()
                        ) / Math.log(2.0) * -1, a, polishNotation
                    )
                    break
                } else if (polishNotation[a] == "sqrt" || polishNotation[a] == "-sqrt") {
                    if (polishNotation[a] == "sqrt") arrayCorrectorForFunction(
                        Math.sqrt(
                            polishNotation[a - 1].toDouble()
                        ), a, polishNotation
                    ) else arrayCorrectorForFunction(
                        Math.sqrt(
                            polishNotation[a - 1].toDouble()
                        ) * -1, a, polishNotation
                    )
                    break
                }
            }
        }
        return polishNotation.last()
    }

    /**
     * This is a repetitive part of the actions to perform a calculation.
     * @param result it is result of calculation some operation
     * @param a it is iterator
     * @param polishNotation it is array list with expression in polish notation format
     */
    private fun arrayCorrectorForFunction(
        result: Double,
        a: Int,
        polishNotation: ArrayList<String>
    ) {
        polishNotation[a] = result.toString()
        polishNotation.removeAt(a - 1)
    }

    /**
     * This is a repetitive part of the actions to perform a calculation.
     * @param result it is result of calculation some operation
     * @param a it is iterator
     * @param polishNotation it is array list with expression in polish notation format
     */
    private fun arrayCorrector(result: Double, a: Int, polishNotation: ArrayList<String>) {
        polishNotation[a] = result.toString()
        polishNotation.removeAt(a - 1)
        polishNotation.removeAt(a - 2)
    }
}