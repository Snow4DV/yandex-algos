package lec

import java.io.*
import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) { // diy stack rec fact

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val nums = reader.readLine().trim().split(' ').map { it.toInt() }

    val dp = IntArray(nums.size)
    val dpPrev = IntArray(nums.size)

    dp[0] = 1
    dpPrev[0] = -1

    var maxLength = -1
    var lastIndex = -1

    for(i in 1 until dp.size) { // за квадрат
        var maxPrevLength = -2
        var maxPrevIndex = -2
        for(j in 0 until i) {
            if(dp[j] > maxPrevLength && nums[j] < nums[i]) {
                maxPrevLength = dp[j]
                maxPrevIndex = j
            }
        }
        dpPrev[i] = maxPrevIndex
        dp[i] = maxPrevLength + 1

        if(dp[i] > maxLength) {
            maxLength = dp[i]
            lastIndex = i
        }
    }
    writer.println(dp[lastIndex])

    var curI = lastIndex

    var resultArr = mutableListOf<Int>()
    while(dpPrev[curI] != -1) {
        resultArr.add(0, dpPrev[curI])
        curI = dpPrev[curI]
    }

    writer.println("Arr: ${(resultArr + listOf(lastIndex)).joinToString(" ")}")

    writer.close()
}
