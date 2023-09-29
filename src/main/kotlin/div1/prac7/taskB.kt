package div1.prac7

import java.io.*
import java.util.*
import kotlin.math.abs


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, m) = reader.readLine().trim().split(' ').map { it.toInt() }

    val events = mutableListOf<Pair<Int, Int>>() // types: -1 seg started; 0 point; 1 seg ended

    repeat(n) {
        val (a, b) = reader.readLine().trim().split(' ').map { it.toInt() }
        if(a <= b) {
            events.add(a to -1)
            events.add(b to Integer.MAX_VALUE)
        } else {
            events.add(b to -1)
            events.add(a to Integer.MAX_VALUE)
        }
    }

    var pointsSegmentsCount = Array(m) {0}

    reader.readLine().trim().split(' ').map{it.toInt()}.forEachIndexed { index, it -> events.add(it to index) }

    events.sortWith { o1 , o2 ->
        val comp1 = o1.first.compareTo(o2.first)
        if (comp1 != 0) comp1 else o1.second.compareTo(o2.second)
    }

    var curOpenSegmentsCount = 0


    for(event in events) {
        when(event.second) {
            -1 -> curOpenSegmentsCount++
            Integer.MAX_VALUE -> curOpenSegmentsCount--
            else -> {
                pointsSegmentsCount[event.second] = curOpenSegmentsCount
            }
        }
    }

    writer.print(pointsSegmentsCount[0])

    for(i in 1 until pointsSegmentsCount.size) {
        writer.print(' ')
        writer.print(pointsSegmentsCount[i])
    }

    writer.close()
}
