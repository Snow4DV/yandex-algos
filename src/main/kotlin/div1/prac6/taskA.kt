package div1.prac6

import java.io.*
import java.math.BigInteger
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, k) = reader.readLine().trim().split(' ').map { it.toInt() }
    val nArray = reader.readLine().trim().split(' ').map { it.toInt() }
    val kArray = reader.readLine().trim().split(' ').map { it.toInt() }

    kArray.forEach {
        writer.println(if (binSearch(nArray, it) != -1) "YES" else "NO")
    }

    writer.close()
}

private fun binSearch(array: List<Int>, query: Int): Int {
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

    return if(array[l] == query) l else -1
}

