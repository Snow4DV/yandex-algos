package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.getNum()
    val list = reader.readLine().trim().split(' ').map { it.toInt() }
    val x = reader.getNum()

    var closest = list[0]

    for(i in 1 until list.size) {
        val num = list[i]
        if(abs(num - x) < abs(closest - x)) {
            closest = list[i]
        }
    }

    writer.println(closest)

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

