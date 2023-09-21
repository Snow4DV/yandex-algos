package div2B.prac6

import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val coefficients = reader.readLine().trim().split(' ').map { it.toInt() }
    val (a, b, c, d) = coefficients


    val x = binSearchL(coefficients)

    writer.println(x)

    writer.close()
}


private fun binSearchL(coefficients: List<Int>): Double {
    var l = -100000.0
    var r = 100000.0

    var isAscending = f(l, coefficients) <= f(r, coefficients)

    while(r - l > 0.00000000001) {
        val m = (l+r)/2
        val res = f(m, coefficients)
        if(res >= 0 && isAscending || res <= 0 && !isAscending) {
            r = m
        } else {
            l = m
        }
    }

    return l

}

private fun f(x: Double, coefficients: List<Int>): Double {
    val (a, b, c, d) = coefficients
    return a * x * x * x + b * x * x + c * x + d
}


