package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()
    val peopleSpecs = Array<Triple<Int, Int, Int>?>(n) {null}

    repeat(n) {
        val (a, b, c) = reader.readLine().trim().split(' ').map { it.toInt() }
        peopleSpecs[it] = Triple(a, b, c)
    }

    val dp = IntArray(n)
    dp[0] = peopleSpecs.g(0).first
    if(n >= 2) {
        dp[1] = min(
            peopleSpecs.g(0).first + peopleSpecs.g(1).first,
            peopleSpecs.g(0).second
        )
    }
    if(n >= 3) {
        dp[2] = sequenceOf(
            peopleSpecs.g(0).first + peopleSpecs.g(1).first + peopleSpecs.g(2).first,
            peopleSpecs.g(0).first + peopleSpecs.g(1).second,
            peopleSpecs.g(0).second + peopleSpecs.g(2).first,
            peopleSpecs.g(0).third
        ).min()
    }

    for(i in 3 until dp.size) {
        dp[i] = listOf(
            dp[i - 1] + peopleSpecs.g(i).first,
            dp[i - 2] + peopleSpecs.g(i-1).second,
            dp[i - 3] + peopleSpecs.g(i-2).third,
        ).min()
    }

    writer.println(dp.last())

    writer.close()
}

private fun <T> Array<T>.g(index: Int) = this[index]!!
