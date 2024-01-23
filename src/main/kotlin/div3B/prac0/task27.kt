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

    val arr = Array(n) { reader.readLine().trim().split(' ').map { it.toInt() }.toTypedArray() }

    val dp = Array(n + 1) { Array(m + 1) {-1} }
    val prev = Array(n + 1) { Array(m + 1) {' '} }


    
    dp[1][1] = arr[0][0]
    for(i in 1 .. n) {
        for(j in 1 .. m) {
            if(i == 1 && j == 1) continue
            val val1 = dp[i - 1][j]
            val val2 = dp[i][j - 1]
            dp[i][j] = max(val1, val2) + arr[i - 1][j - 1]
            prev[i][j] = when {
                val1 > val2 -> 'D'
                else -> 'R'
            }
        }
    }

    writer.println(dp.last().last())

    val actions = mutableListOf<Char>()

    var curI = n
    var curJ = m

    while(true) {
        val pr = prev[curI][curJ]
        if(pr != ' ') actions.add(0, pr)
        when(pr) {
            'D' -> curI--
            'R' -> curJ--
            else -> break
        }
    }

    writer.println(actions.joinToString(" "))


    writer.close()
}



private fun <T> Array<T>.g(index: Int) = this[index]!!
