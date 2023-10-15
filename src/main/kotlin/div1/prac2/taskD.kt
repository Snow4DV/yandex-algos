package div1.prac2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val list = reader.readLine().trim().split(' ').map { it.toInt() }

    var counter = 0

    for(i in 1 until list.size - 1) {
        if(list[i] > list[i-1] && list[i] > list[i+1]) counter++
    }

    writer.println(counter)

    writer.close()
}

private fun BufferedReader.getNum() = this.readLine().trim().toInt()

