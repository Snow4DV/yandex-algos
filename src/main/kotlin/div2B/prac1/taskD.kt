package div2B.prac1

import java.util.*
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.roundToInt

fun main() {

    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()

    val pupils = mutableListOf<Int>()

    repeat(n) {
        pupils.add(scanner.nextInt())
    }

    if(n % 2 == 0) {
        print(pupils[n/2])
    } else {
        print(pupils[n/2])
    }
    // проверяем, может ли быть при таком дне такой месяц - функция проверки нужна




}