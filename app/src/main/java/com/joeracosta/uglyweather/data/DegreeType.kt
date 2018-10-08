package com.joeracosta.uglyweather.data

enum class DegreeType(val code: Int) {
    FAHRENHEIT(0),
    CELSIUS(1),
    WEISHAUS(2)
}

fun codeToDegreeType(code: Int) : DegreeType? {
    return when (code) {
        0 -> DegreeType.FAHRENHEIT
        1 -> DegreeType.CELSIUS
        2 -> DegreeType.WEISHAUS
        else -> null
    }
}