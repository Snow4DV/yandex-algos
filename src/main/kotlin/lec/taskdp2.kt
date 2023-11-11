package lec

import java.io.*
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) { // diy stack rec fact

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val prices = reader.readLine().trim().split(' ').map { it.toInt() }

    val dp = IntArray(prices.size + 1)
    val dpPrev = IntArray(prices.size + 1)

    dp[0] = 0
    dpPrev[0] = -1

    for(i in 1 until dp.size) {
        val choice1 = dp.getOrNull(i - 1) ?: Int.MIN_VALUE
        val choice2 = dp.getOrNull(i-2) ?: Int.MIN_VALUE
        val maxPrevPrice = if(choice1 > choice2) {
            dpPrev[i] = i - 1
            choice1
        } else {
            dpPrev[i] = i - 2
            choice2
        }
        dp[i] = prices[i-1] + maxPrevPrice
    }
    writer.println(dp.last())

    var curI = dp.indices.last

    writer.println("Steps: ")
    while(dpPrev[curI] != -1) {
        writer.println(dpPrev[curI])
        curI = dpPrev[curI]
    }

    writer.close()
}
