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

    val events = reader.readLines().map { line ->
        line.trim().split(' ')
            .mapIndexed { index, numStr -> numStr.toInt() to if (index == 0) -1 else 1 } // -1 is start; 1 is end
    }.flatten().sortedWith { o1, o2 ->
        val comp1 = o1.first.compareTo(o2.first)
        if (comp1 != 0) comp1 else o2.second.compareTo(o2.second)
    }

    var count = 0
    var prevEventX = -1

    var lenSum = 0

    for (event in events) {
        if (count >= 1) {
            lenSum += event.first - prevEventX
        }

        when (event.second) {
            -1 -> count++
            1 -> count--
        }



        prevEventX = event.first

    }

    writer.println(lenSum)

    writer.close()
}
