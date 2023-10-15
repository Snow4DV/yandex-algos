package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val list = reader.readLine().trim().split(' ').map { it.toLong() }


    var threeMaxNeg: Triple<Long?, Long?, Long?> = Triple(null, null, null) // -4 -3 -2
    var threeMin: Triple<Long?, Long?, Long?> = Triple(null, null, null) // -999 -312 -122
    var threeMax: Triple<Long?, Long?, Long?> = Triple(null, null, null)  // 4 3 2

    fun addNumToTwoMaNeg(num: Long) {
        if(num >= 0) throw IllegalArgumentException()
        if(num > (threeMaxNeg.first ?: (num - 1))) {
            threeMaxNeg = Triple(num, threeMaxNeg.first, threeMaxNeg.second)
        } else if(num > (threeMaxNeg.second ?: (num - 1))) {
            threeMaxNeg = Triple(threeMaxNeg.first, num, threeMaxNeg.second)
        } else if(num > (threeMaxNeg.third ?: (num - 1))) {
            threeMaxNeg = Triple(threeMaxNeg.first, threeMaxNeg.second, num)
        }
    }

    for(num in list) {
        if(num < 0) {
            if(num < (threeMin.first ?: (num + 1))) {
                threeMin.third?.let { addNumToTwoMaNeg(it) }
                threeMin = Triple(num, threeMin.first, threeMin.second)
            } else if(num < (threeMin.second ?: (num + 1))) {
                threeMin.third?.let { addNumToTwoMaNeg(it) }
                threeMin = Triple(threeMin.first, num, threeMin.second)
            } else if(num < (threeMin.third ?: (num + 1))) {
                threeMin.third?.let { addNumToTwoMaNeg(it) }
                threeMin = Triple(threeMin.first, threeMin.second, num)
            } else {
                addNumToTwoMaNeg(num)
            }
        } else {
            if(num > (threeMax.first ?: (num - 1))) {
                threeMax = Triple(num, threeMax.first, threeMax.second)
            } else if(num > (threeMax.second ?: (num - 1))) {
                threeMax = Triple(threeMax.first, num, threeMax.second)
            } else if(num > (threeMax.third ?: (num - 1))) {
                threeMax = Triple(threeMax.first, threeMax.second, num)
            }
        }
    }

    val nums = (threeMin.toList().filterNotNull() + threeMax.toList().filterNotNull() + threeMaxNeg.toList().filterNotNull()).sorted()

    var maxProd = nums[0] * nums[1] * nums[2]
    var indexes = Triple(0, 1, 2)

    for(i in nums.indices) {
        for(j in nums.indices) {
            for(k in nums.indices) {
                if(setOf(i, j, k).size < 3) continue
                val prod = nums[i] * nums[j] * nums[k]
                if(prod > maxProd) {
                    maxProd = prod
                    indexes = Triple(i, j, k)
                }
            }
        }
    }


    println("${nums[indexes.third]} ${nums[indexes.second]} ${nums[indexes.first]}")


    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

