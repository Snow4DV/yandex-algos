package div2B.prac1

import java.lang.Math.pow
import java.util.*
import kotlin.math.*

fun distBetween(x1: Int, y1: Int, x2: Int, y2: Int) : Double {
    return sqrt((x1 - x2).toDouble().pow(2) + (y1 - y2).toDouble().pow(2))
}
fun main() {

    val scanner = Scanner(System.`in`)

    val d = scanner.nextInt()
    val x = scanner.nextInt()
    val y = scanner.nextInt()


    val laysInside = (x >= 0 && y >= 0 && y <= (-x + d))

    if(laysInside) {
        println(0)
    } else {
        val dists = mapOf(
            "1" to distBetween(x,y, 0, 0),
            "2" to distBetween(x,y, d, 0),
            "3" to distBetween(x,y, 0, d)
        )
        print(dists.minBy { it.value }!!.key)
    }
}