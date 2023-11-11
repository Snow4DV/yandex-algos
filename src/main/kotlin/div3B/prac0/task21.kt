package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    val dp = IntArray(n)

    // база
    dp[0] = 2 // len 1
    if(1 in dp.indices) dp[1] = 4 // len 2
    if(2 in dp.indices) dp[2] = 7 // len 3 (excl 111)

    for(i in 3 until dp.size) {
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
    }

    writer.println(dp.last())
    writer.close()
}
