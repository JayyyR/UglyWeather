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

fun degreeTypeToCode(degreeType: DegreeType): Int? {
    return when (degreeType) {
        DegreeType.FAHRENHEIT -> 0
        DegreeType.CELSIUS -> 1
        DegreeType.WEISHAUS -> 2
        else -> null
    }
}

fun degreeNameToDegreeType(name: String): DegreeType? {
    return when (name.toLowerCase()) {
        "fahrenheit" -> DegreeType.FAHRENHEIT
        "celsius" -> DegreeType.CELSIUS
        "weishaus" -> DegreeType.WEISHAUS
        else -> null
    }
}