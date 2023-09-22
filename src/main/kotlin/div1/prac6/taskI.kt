package div1.prac6

import java.io.*
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, r, c) = reader.readLine().trim().split(' ').map {it.toInt()}

    val pupils = reader.readLines().map { it.trim().toInt() }.sorted()

    writer.println(binSearch(pupils, n, r, c))

    writer.close()
}

private fun binSearch(pupils: List<Int>, n: Int, R: Int, c: Int) : Int {
    var l = 0
    var r = pupils.max()!!

    while(l < r) {
        val m = (l + r)/2
        if(countOfBrigades(pupils, c, m) >= R) {
            r = m
        } else {
            l = m + 1
        }
    }

    return l
}


private fun countOfBrigades(pupils: List<Int>, brigadeSize: Int, maxDeltaInBrigade: Int): Long {
    var count = 0L

    var index = 0
    while(index < pupils.size) {
        if(index + brigadeSize - 1 < pupils.size && pupils[index + brigadeSize - 1] - pupils[index] <= maxDeltaInBrigade) {
            count++
            index += brigadeSize
        } else {
            index++
        }
    }

    return count
}