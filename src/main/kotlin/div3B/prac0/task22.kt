package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, k) = reader.readLine().trim().split(' ').map { it.toInt() }

    val dp = IntArray(n)
    dp[0] = 1

    for(i in 1 until dp.size) {
        val dropCount = if(i - k >= 0) (i - k) else 0
        dp[i] = dp.asSequence().take(i).drop(dropCount).sum()
    }

    writer.println(dp.last())

    writer.close()
}
