package div2B.prac7

import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    val events = reader.readLines().map {line ->
        val list = line.trim().split(' ').map { it.toInt() }
        listOf(list[0] to 1, list[1] + list[0] to -1)
    }.flatten().sortedWith { o1, o2 ->
        val comp1 = o1.first.compareTo(o2.first)
        if (comp1 != 0) comp1 else o1.second.compareTo(o2.second)
    }

    var count = 0

    var maxCount = 0

    for(event in events) {
        when(event.second) {
            1 -> count++
            -1 -> count--
        }

        maxCount = max(count, maxCount)
    }

    writer.println(maxCount)

    writer.close()
}
