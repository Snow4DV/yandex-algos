package div2B.prac6

import java.io.*
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)
    val (n, k) = reader.readLine().trim().split(' ').map { it.toInt() }

    val points = reader.readLine().trim().split(' ').map { it.toInt() }.sorted()

     val res = binSearchL(points, k)
    writer.println(res)
    writer.close()
}

private fun binSearchL(points: List<Int>, k: Int): Int {
    var l = 0
    var r = abs((points.first().toLong() - points.last().toLong()).toInt()) / k

    while(l < r) {
        val m = (l+r)/2

        if(checkIfCovered(points, k, m)) {
            r = m
        } else {
            l = m + 1
        }
    }

    return l
}

private fun checkIfCovered(points: List<Int>, k: Int, length: Int) : Boolean {
    var lastUncoveredPointIndex = 0

    for(n in 1..k) {

        if(lastUncoveredPointIndex >= points.size) break

        val startX = points[lastUncoveredPointIndex]
        val endX = startX + length

        while(lastUncoveredPointIndex < points.size && points[lastUncoveredPointIndex] <= endX) {
            lastUncoveredPointIndex++
        }


    }

    return lastUncoveredPointIndex == points.size
}
