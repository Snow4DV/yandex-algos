package div2B.prac5

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, q) = reader.readLine().trim().split(' ').map { it.toInt() }

    val prefixSums = Array(n+1) {0L}

    reader.readLine().trim().split(' ').map {it.toLong()}.forEachIndexed {index, i -> prefixSums[index+1] = prefixSums[index] + i }



    repeat(q){
        val (start, end) = reader.readLine().split(' ').map { it.toInt() - 1 }
        writer.println(prefixSums[end + 1] - prefixSums[start])
    }


    writer.close()
}

