package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()
    val gv = reader.readLine().trim().split(' ').map { it.toInt() }.sorted()

    val dp = IntArray(gv.size + 1)
    dp[0] = Int.MAX_VALUE shr 3
    dp[1] = Int.MAX_VALUE shr 3
    dp[2] = abs(gv[0] - gv[1])
    if(3 in dp.indices) dp[3] = sequenceOf(abs(gv[0] - gv[1]) + abs(gv[1] - gv[2]),
        abs(gv[0] - gv[2]) + abs(gv[0] - gv[1]),
        abs(gv[0] - gv[2]) + abs(gv[2] - gv[1])).min()
    if(4 in dp.indices) dp[4] = abs(gv[0] - gv[1]) + abs(gv[2] - gv[3])
    for(i in 5..dp.indices.last) {
        val var1 = dp[i-3] + abs(gv[i-2 - 1] - gv[i-1 - 1]) + abs(gv[i-1 - 1] - gv[i - 1])
        val var2 = dp[i-2] + abs(gv[i-1 - 1] - gv[i - 1])
        dp[i] = min(var1,var2)
    }
    

    writer.println(dp.last())

    writer.close()
}

private fun <T> Array<T>.g(index: Int) = this[index]!!
