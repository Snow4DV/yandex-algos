package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

fun main() {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)



    val (nLines, mColumns, kQueries) = reader.readLine().trim().split(' ').map { it.toInt() }

    val matrixRsq = mutableListOf<LongArray>()
    matrixRsq.add(LongArray(mColumns + 1))


    repeat(nLines) {
        val line = reader.readLine().trim().split(' ').map {it.toInt()}
        val lineRsq = LongArray(line.size + 1)

        val onlyCurrentLineRsq = LongArray(line.size + 1)
        for(i in line.indices) {
            onlyCurrentLineRsq[i+1] = onlyCurrentLineRsq[i] + line[i]
            lineRsq[i+1] = matrixRsq.last()[i + 1] + onlyCurrentLineRsq[i] + line[i]
        }
        matrixRsq.add(lineRsq)
    }


    repeat(kQueries) {

        val (x1, y1, x2, y2) = reader.readLine().trim().split(' ').map { it.toInt() - 1 }

        val exclSum1 = matrixRsq[x1][y2 + 1]
        val exclSum2 = matrixRsq[x2+1][y1]

        val addSum3 = matrixRsq[x1][y1]

        val biggerSum = matrixRsq[x2 + 1][y2 + 1]

        val resSum = biggerSum - exclSum1 - exclSum2 + addSum3

        writer.println(resSum)
    }


    writer.close()
}
// got 7754, need -727

private fun BufferedReader.getNum() = this.readLine().trim().toInt()