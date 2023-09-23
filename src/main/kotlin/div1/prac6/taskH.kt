package div1.prac6

import java.io.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, k) = reader.readLine().trim().split(' ').map {it.toInt()}

    val wires = reader.readLines().map { it.trim().toInt() }

    writer.println(binSearchR(wires, k))
    writer.close()
}

fun binSearchR(wires: List<Int>, k: Int): Long {
    var l = 0L
    var r = 1000000000L

    while(l < r) {
        val m = (l + r + 1) / 2
        if(maxCountOfWiresWithLen(wires, m) >= k) {
            l = m
        } else {
            r = m - 1
        }
    }

    return l
}


private fun maxCountOfWiresWithLen(wires: List<Int>, length: Long): Long {
    if(length == 0L) return Long.MAX_VALUE
    var count = 0L
    wires.forEach {
        count += it / length
    }
    return count
}