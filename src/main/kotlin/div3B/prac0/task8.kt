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


    val k = reader.getNum()

    var minX = Integer.MAX_VALUE
    var maxX = -1
    var minY = Integer.MAX_VALUE
    var maxY = -1


    repeat(k) {
        val (x,y) = reader.readLine().trim().split(' ').map { it.toInt() }
        minX = min(x, minX)
        maxX = max(x, maxX)
        minY = min(y, minY)
        maxY = max(y, maxY)
    }

    writer.println("$minX $minY $maxX $maxY")

    writer.close()
}


private fun BufferedReader.getNum() = this.readLine().trim().toInt()