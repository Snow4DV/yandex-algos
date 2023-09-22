package div1.prac6

import java.io.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, x, y) = reader.readLine().trim().split(' ').map { it.toInt() }

    writer.println(binSearch(n, x, y))
    writer.close()
}

private fun binSearch(n: Int, x: Int, y: Int): Long {
    var l = 0L
    var r = min(x, y).toLong() * n

    while(l < r) {
        val m = (l+r)/2
        if(amountOfPagesPrintedForSeconds(x, y, m) >= n) {
            r = m
        } else {
            l = m + 1
        }
    }

    return l
}


private fun amountOfPagesPrintedForSeconds(x: Int, y: Int, seconds: Long): Long {
    val fast = min(x, y)
    val slow = max(x, y)

    if(seconds < fast) return 0

    return (seconds - fast)/fast + (seconds-fast)/slow + 1 // printing first page on the fast printer
}