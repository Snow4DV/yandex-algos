package div1.prac6

import java.io.*
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (w, h, n) = reader.readLine().trim().split(' ').map { it.toInt() }

    writer.println(binSearch(w, h, n))

    writer.close()
}

private fun binSearch(w: Int, h: Int, n: Int): Long {
    var l = 1L
    var r = max(w, h) * n.toLong()

    while(l < r) {
        val m = (l + r) / 2
         if(diplomasFitting(m, m, w, h, n)) {
             r = m
         } else {
             l = m + 1
         }
    }

    return l
}


private fun diplomasFitting(boardW: Long, boardH: Long, w: Int, h: Int, n: Int): Boolean {
    val lineCount = boardW / w
    val columnCount = boardH / h
    return lineCount * columnCount >= n
}