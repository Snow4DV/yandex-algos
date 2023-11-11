package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

fun main() { // сортировка событий

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val linesRsq = mutableListOf<LongArray>()

    val (nLines, mColumns, kQueries) = reader.readLine().trim().split(' ').map { it.toInt() }

    repeat(nLines) {
        val line = reader.readLine().trim().split(' ').map {it.toInt()}
        val lineRsq = LongArray(line.size + 1)
        for(i in line.indices) {
            lineRsq[i+1] = lineRsq[i] + line[i]
        }
        linesRsq.add(lineRsq)
    }

    repeat(kQueries) {
        var sum = 0L
        val (x1, y1, x2, y2) = reader.readLine().trim().split(' ').map { it.toInt() - 1 }
        for (line in x1..x2) {
            val lineSum = linesRsq[line][y2 + 1] - linesRsq[line][y1]
            sum += lineSum
        }
        writer.println(sum)
    }


    writer.close()
}


private fun BufferedReader.getNum() = this.readLine().trim().toInt()