package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()
    val prices = Array(n) { reader.readLine().trim().toInt() }

    val inf = 300 * 1000 * 100

    val dp = Array(n + 1) { i -> Array(n + 1) {if(i == 0) inf else 0} }
    val prevDp = Array(n + 1) { Array(n + 1) {0} }
    dp[0][0] = 0

    for(day in 1 until n + 1) {
        for(coupons in 0 until n + 1) {
            // если б он потратил купон вчера
            val try1 = dp[day - 1].getOrNull(coupons + 1) ?: inf
            // если б он купил за деньги с получением купона
            val try2 = if ((coupons - 1) in dp[day - 1].indices && prices[day - 1] >  100) (dp[day-1][coupons - 1] + prices[day - 1]) else inf
            // если б он купил за деньги без получения купона
            val try3 = if (coupons in dp[day - 1].indices && prices[day - 1] <= 100) (dp[day-1][coupons] + prices[day - 1]) else inf
            val minMoneySpent = sequenceOf(try1, try2, try3).min()

            dp[day][coupons] = minMoneySpent
            // флаг, был ли потрачен купон
            prevDp[day][coupons] = when {
                minMoneySpent == try2 -> 2
                minMoneySpent == try3 -> 3
                else -> 1
            }
        }
    }

    val (maxCoupons, minMoney) = dp.last().mapIndexed { index, it -> index to it }.minWith(compareBy({it.second}, {it.first * -1}))

    writer.println(minMoney)

    val days = mutableListOf<Int>()

    var lastI = n
    var lastJ = maxCoupons

    var counterOfSpentCoupons = 0

    while(lastI != 0) {
        val (prevI, prevJ) = when(prevDp[lastI][lastJ]) {
            1 -> {
                counterOfSpentCoupons++
                days.add(lastI)
                (lastI - 1) to (lastJ + 1) // потратил купон
            }
            2 -> (lastI - 1) to (lastJ - 1) // получил купон
            3 -> (lastI - 1) to (lastJ) // просто потратил деньги
            else -> (lastI - 1) to 0
        }
        lastI = prevI
        lastJ = prevJ
    }

    writer.println("$maxCoupons $counterOfSpentCoupons")

    writer.println(days.sorted().joinToString(System.lineSeparator()))


    writer.close()
}



