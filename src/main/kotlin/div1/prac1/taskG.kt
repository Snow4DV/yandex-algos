package div1.prac1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.ceil
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, k, m) = reader.readLine().trim().split(' ').map { it.toInt() }

    val detailsForWorkpiece = k / m
    val detailsWeightForWorkpiece = detailsForWorkpiece * m

    if(detailsForWorkpiece == 0) {
        println(0)
        return
    }

    var leftOver = n
    var parts = 0

    while(leftOver >= k) {
        val workpieces = leftOver / k
        leftOver -= detailsWeightForWorkpiece * workpieces
        parts += detailsForWorkpiece * workpieces
    }

    println(parts)




    writer.close()
}

