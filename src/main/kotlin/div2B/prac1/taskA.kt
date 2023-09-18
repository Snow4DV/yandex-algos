package div2B.prac1

import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)

    val (exitCode, interactorCode, checkerCode) = listOf(scanner.nextInt(), scanner.nextInt(), scanner.nextInt())

    val res = when (interactorCode) {
        0 -> if (exitCode != 0) 3 else checkerCode
        1 -> checkerCode
        4 -> if (exitCode != 0) 3 else 4
        6 -> 0
        7 -> 1
        else -> interactorCode
    }
    print(res)
}