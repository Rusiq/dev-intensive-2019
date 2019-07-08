package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.format
import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        if (fullName?.trim().isNullOrEmpty())
            return null to null

        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName:String?):String?{

        if (firstName?.trim().isNullOrEmpty() && lastName?.trim().isNullOrEmpty())
            return null

        val firstInitial = firstName?.trim()?.getOrNull(0)?.toUpperCase()
        val secondInitial = lastName?.trim()?.getOrNull(0)?.toUpperCase()

        return "${firstInitial?:""}${secondInitial?:""}"
    }

    fun transliteration(source: String, divider: String = " "): String {
        source.trim()
        var result = ""

        for (currentSymbol in source) {
            result += if (currentSymbol.toString() == " ") divider else translateToEng(currentSymbol)
        }

        return result
    }

    private fun translateToEng(currentSymbol: Char): String {
        val rusToEng = hashMapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )

        return when {
            currentSymbol.isLowerCase() -> rusToEng.getOrElse(currentSymbol.toString()) { currentSymbol.toString() }
            currentSymbol.isUpperCase() -> {
                val eng = rusToEng.getOrElse(currentSymbol.toString().toLowerCase()) { currentSymbol.toString() }
                    .toUpperCase()
                return if (eng.length > 1)
                    eng.toCharArray()[0] + eng.toCharArray()[1].toLowerCase().toString()
                else
                    eng
            }
            else -> ""
        }
    }

    fun getDifferNumberDate(currentDate: Date, date: Date, pattern: String) : Int {
        return currentDate.format(pattern).toInt() - date.format(pattern).toInt()
    }
}