package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (start, end) = reader.readLine().trim().split(' ').map {it.toInt()}

    val flag = when(reader.readLine().trim()) {
        "heat" -> end >= start
        "freeze" -> end <= start
        "fan" -> false
        "auto" -> true
        else -> throw IllegalArgumentException()
    }

    println(if (flag) end else start)
}