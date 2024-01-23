package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, m) = reader.readLine().trim().split(' ').map { it.toInt() }

    val dp = Array(n) { Array(m) {0} }

    dp[0][0] = 1

    for(i in 0 until n) {
        for(j in 0 until m) {
            if(i == 0 && j == 0) continue

            dp[i][j] = sequenceOf(
                if((i - 1) in dp.indices && (j-2) in dp[0].indices) dp[i-1][j-2] else null,
                if((i - 2) in dp.indices && (j-1) in dp[0].indices) dp[i-2][j-1] else null
            ).filterNotNull().sum()
        }
    }

    writer.println(dp.last().last())

    writer.close()
}



private fun <T> Array<T>.g(index: Int) = this[index]!!
