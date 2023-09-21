package div2B.prac6

import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    reader.readLine() // skip first line

    val arr = reader.readLine().trim().split(' ').map { it.toInt() }.sorted()

    var first = true

    repeat (times = reader.readLine().toInt()) {// queries
        val (min, max) = reader.readLine().trim().split(' ').map { it.toInt() }
        var firstIndexMin = binarySearch(arr) { it >= min }
        var firstIndexGreaterThanMax = binarySearch(arr) { it > max }
        if(arr[firstIndexGreaterThanMax] <= max) firstIndexGreaterThanMax++
        if(arr[firstIndexMin] < min) firstIndexMin = -1
        if(!first) {
            writer.print(' ')
        } else {
            first = false
        }
        if(firstIndexMin != -1) writer.print(firstIndexGreaterThanMax - firstIndexMin)
        else writer.print(0)
    }

    writer.close()
}

private fun binarySearch(arr: List<Int>, check: (Int) -> Boolean): Int {
    var l = arr.indices.first
    var r = arr.indices.last

    while(l < r) {
        val m = (l+r)/2
        if(check(arr[m])) {
            r = m
        } else {
            l = m + 1
        }
    }

    return l
}

