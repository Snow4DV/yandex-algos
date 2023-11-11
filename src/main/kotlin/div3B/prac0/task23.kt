package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.PriorityQueue

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    val dp = IntArray(n + 1)
    val dpAction = IntArray(n + 1) {-1} // 0 is *2, 1 is *3, 2 is +1
    dp[0] = Int.MAX_VALUE
    dp[1] = 0

    for(i in 2 until dp.size) {
        var bestOpcode = 2 // add 1 to (i-1)
        var leftNumOfOp = i - 1
        var bestOpcodeOpCount = dp[leftNumOfOp]
        if(i % 2 == 0 && dp[i/2] < bestOpcodeOpCount) {
            bestOpcode = 0
            leftNumOfOp = i/2
            bestOpcodeOpCount = dp[leftNumOfOp]
        }
        if(i % 3 == 0 && dp[i/3] < bestOpcodeOpCount) {
            bestOpcode = 1
            leftNumOfOp = i/3
            bestOpcodeOpCount = dp[leftNumOfOp]
        }

        dp[i] = dp[leftNumOfOp] + 1
        dpAction[i] = bestOpcode
    }

    writer.println(dp[n])


    val seq = mutableListOf<Int>()
    var curI = n
    while(dpAction[curI] != -1) {
        seq.add(0, curI)
        curI = when(dpAction[curI]) {
            2 -> curI - 1
            1 -> curI / 3
            0 -> curI / 2
            else -> throw IllegalStateException()
        }
    }
    seq.add(0, 1)

    writer.println(seq.joinToString(" "))

    writer.close()
}
