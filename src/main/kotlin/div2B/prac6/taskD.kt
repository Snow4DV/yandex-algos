package div2B.prac6

import java.io.*
import java.math.BigInteger
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val coefficients = reader.readLine().trim().split(' ').map { it.toLong() }
    val (dPerfA: Long, dSkipK: Long, fPerfB: Long, fSkipM: Long, treesX: Long) = coefficients

    val days = binSearchL(treesX, dPerfA, dSkipK, fPerfB, fSkipM)

    writer.println(days)
    //1 2 1000000000 1000000000 1000000000000000000
    writer.close()
}


private fun binSearchL(treesX: Long, dPerfA: Long, dSkipK: Long, fPerfB: Long, fSkipM: Long): Long {
    var l = 0L
    var r = treesX * 2 / min(dPerfA, fPerfB) + 1
    while(l < r) {
        val m = (l+r)/2
        val allTreesDigged = model(treesX, dPerfA, dSkipK, fPerfB, fSkipM, m)
        if(allTreesDigged) {
            r = m
        } else {
            l = m + 1L
        }
    }

    return l

}


fun Long.toBigInteger() = BigInteger.valueOf(this)

private fun model(treesX: Long, dPerfA: Long, dSkipK: Long, fPerfB: Long, fSkipM: Long, days: Long): Boolean { // returns amount of trees left
    val trees = treesX.toBigInteger() - dPerfA.toBigInteger()*(days - days/dSkipK).toBigInteger() - fPerfB.toBigInteger()*(days - days/fSkipM).toBigInteger()

    return trees <= 0L.toBigInteger()
}


