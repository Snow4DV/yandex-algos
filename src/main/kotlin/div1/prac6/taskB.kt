package div1.prac6

import java.io.*
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, k) = reader.readLine().trim().split(' ').map { it.toInt() }
    val nArray = reader.readLine().trim().split(' ').map { it.toInt() }
    val kArray = reader.readLine().trim().split(' ').map { it.toInt() }

    kArray.forEach {
        val firstGE = binSearchL(nArray, it)
        val firstLess = if (firstGE > 0) (firstGE - 1) else if(firstGE == 0) -1 else nArray.indices.last

        if(firstLess == -1) {
            writer.println(nArray[firstGE])
        } else if(firstGE == -1) {
            writer.println(nArray[firstLess])
        } else {
            val firstLessVal = nArray[firstLess]
            val firstGEVal = nArray[firstGE]

            if(abs(firstLessVal - it) <= abs(firstGEVal - it)) {
                writer.println(firstLessVal)
            } else {
                writer.println(firstGEVal)
            }
        }
    }

    writer.close()
}

private fun binSearchL(array: List<Int>, query: Int): Int {
    var l = 0
    var r= array.indices.last

    while(l < r) {
        val m = (l+r)/2
        if(array[m] >= query) {
            r = m
        } else {
            l = m + 1
        }
    }

    return if (array[l] >= query) l else -1
}