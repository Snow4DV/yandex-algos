package lec

import java.io.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)


    writer.close()
}

const val EPSILON = 0.00001


fun dist(cyclistsX: List<Int>, cyclistsV: List<Int>, time: Double): Double { // complexity: O(n)
    var minPos = cyclistsX[0].toDouble()
    var maxPos = cyclistsV[0].toDouble()

    cyclistsX.indices.forEach {
        minPos = min(minPos, cyclistsX[it] + cyclistsV[it] * time)
        maxPos = max(maxPos, cyclistsX[it] + cyclistsV[it] * time)
    }

    return maxPos - minPos
}

fun checkAscending(cyclistsX: List<Int>, cyclistsV: List<Int>, time: Double, epsilon: Double = EPSILON): Boolean {
    return dist(cyclistsX, cyclistsV, time + epsilon) - dist(cyclistsX, cyclistsV, time) >= 0
}

fun binSearchCyclists(cyclistsX: List<Int>, cyclistsV: List<Int>) {
    var l = 0.0
    var r = 10000000000.0

    while(r - l > EPSILON) {
        val m = (l + r) / 2

        if(checkAscending(cyclistsX, cyclistsV, m)) {
            r = m
        } else {
            l = m
        }
    }
}