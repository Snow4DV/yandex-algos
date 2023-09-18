package div2B.prac4

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val writer = PrintWriter(System.out)

    val n = reader.readLine().toInt()

    val map = HashMap<Long, Long>()

    repeat(n) {
        val (color, num) = reader.readLine().split(" ").map { it.toLong() }
        map[color] = (map[color] ?: 0L) + num
    }

    map.keys.sorted().forEach {
        writer.print("$it ${map[it]}\n")
    }
    writer.close()
}

