package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val list = reader.readLine().trim().split(' ').map { it.toLong() }

    var twoMin: Pair<Long?, Long?> = null to null // -3 -2
    var twoMax: Pair<Long?, Long?> = null to null  // 4 3


    for(num in list) {

        if(num < 0) {
            if(num < (twoMin.first ?: (num + 1))) {
                twoMin = num to twoMin.first
            } else if(num < (twoMin.second ?: (num + 1))) {
                twoMin = twoMin.first to num
            }
        } else {
            if(num > (twoMax.first ?: (num - 1))) {
                twoMax = num to twoMax.first
            } else if(num > (twoMax.second ?: (num - 1))) {
                twoMax = twoMax.first to num
            }
        }



    }

    val nums = (twoMin.toList().filterNotNull() + twoMax.toList().filterNotNull()).sorted()

    var maxProd = nums[0] * nums[1]
    var indexes = 0 to 1

    for(i in nums.indices) {
        for(j in i+1 until nums.size) {
            val prod = nums[i] * nums[j]
            if(prod > maxProd) {
                maxProd = prod
                indexes = i to j
            }
        }
    }


    println("${nums[indexes.first]} ${nums[indexes.second]}")


    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

