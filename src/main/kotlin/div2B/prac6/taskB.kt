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
    reader.readLine() // skip n
    val array = reader.readLine().trim().split(' ').map { it.toInt() }
    reader.readLine() // skip m
    val queries = reader.readLine().trim().split(' ').map { it.toInt() }

    queries.forEach { query ->
        val first = binSearchL(array) { it >= query }
        var last = binSearchL(array) {it > query}

        if(array[last] <= query) {
            last++
        }


        if(array[first] != query) {
            writer.println("0 0")
        } else {
            writer.println("${first+ 1} ${last}")
        }

    }

    writer.close()
}

private fun binSearchL(array: List<Int>, check: (Int) -> Boolean): Int {
    var l = array.indices.first
    var r = array.indices.last

    while(l < r) {
        val m = (l+r)/2
        if(check(array[m])) {
            r = m
        } else {
            l = m + 1
        }
    }

    return l

}


