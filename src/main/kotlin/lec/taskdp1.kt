package lec

import java.io.*
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) { // diy stack rec fact

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().toInt()

    val dp = IntArray(n+1)

    dp[0] = 1

    for(i in 1 until dp.size) {
        dp[i] = (dp.getOrNull(i-1) ?: 0) + (dp.getOrNull(i-2) ?: 0) + (dp.getOrNull(i-3) ?: 0)
    }
    writer.println(dp[n])

    writer.close()
}
