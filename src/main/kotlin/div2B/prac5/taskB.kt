package div2B.prac5

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()
    val array = reader.readLine().trim().split(' ').map { it.toInt() }

    val prefixSum = Array(n+1) {0L}

    for(i in 1 until prefixSum.size) {
        prefixSum[i] = prefixSum[i-1] + array[i-1]
    }


    var minPrefixSumOnLeft = prefixSum[0] // 0
    var maxSum = Long.MIN_VALUE

    for(right in 1 until prefixSum.size) {
        if(prefixSum[right] - minPrefixSumOnLeft > maxSum) {
            maxSum = prefixSum[right] - minPrefixSumOnLeft
        }

        minPrefixSumOnLeft = min(minPrefixSumOnLeft, prefixSum[right])
    }

    println(maxSum)

    writer.close()
}


