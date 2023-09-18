package div2B.prac1

import java.util.*
import kotlin.math.abs
import kotlin.math.min

fun main() {

    val scanner = Scanner(System.`in`)

    val (day, month, year) = listOf(scanner.nextInt(), scanner.nextInt(), scanner.nextInt())
    // [1-31] [1-12] [2023]
    // [1-12] [1-31] [2023]

    print(if (isPossible(day, month,year) && isPossible(month, day, year) && day != month) 0 else 1)

    // проверяем, может ли быть при таком дне такой месяц - функция проверки нужна


}

fun isPossible(day: Int, month: Int, year: Int) : Boolean {
    var daysInMonth = 0
    if(month == 2) {
        daysInMonth = if (year % 4 == 0) 29 else 28
    } else if(month <= 7 && month % 2 != 0 || month % 2 == 0) {
        daysInMonth = 31
    } else {
        daysInMonth = 30
    }

    return day <= daysInMonth && month <= 12
}

