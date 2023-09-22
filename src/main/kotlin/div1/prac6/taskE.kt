package div1.prac6

import java.io.*
import kotlin.math.max
import kotlin.math.round

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (a, b, c) = reader.readLines().map { it.trim().toLong() }

    writer.println(binSearch(a, b, c))

    writer.close()
}

private fun binSearch(twos: Long, threes: Long, fours: Long): Long {
    var l = 0L
    var r = (twos + threes + fours)

    while(l < r){
        val m = (l + r)/2
        if(check(twos,threes,fours,m)) {
            r = m
        } else {
            l = m + 1
        }
    }

    return l
}


private fun check(twos: Long, threes: Long, fours: Long, fives: Long): Boolean {
    if(twos+threes+fours == 0L) return fives > 0
    return round((twos*2 + threes*3 + fours*4 + fives*5).toDouble()/(twos+threes+fours+fives)) >= 4
}