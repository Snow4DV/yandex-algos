package div1.prac7

import java.io.*
import java.util.*
import kotlin.math.abs


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, m) = reader.readLine().trim().split(' ').map { it.toInt() }

    val events = mutableListOf<Pair<Int, Int>>() // <X, Type>
    // Типы события: -1: препод начал наблюдать, 1: препод перестал наблюдать

    repeat(m) {
        val (start, end) = reader.readLine().trim().split(' ').map { it.toInt() }
        events.add(start to -1)
        events.add(end to 1)
    }

    events.sortWith { o1, o2 ->
        val comp1 = o1.first.compareTo(o2.first)
        if (comp1 != 0) comp1 else o1.second.compareTo(o2.second)
    }

    var spectatingProfs = 0
    var startedSpectating = -1

    var cheatingStudents = n

    for(event in events) {
        when(event.second) {
            -1 -> {
                if(spectatingProfs == 0) startedSpectating = event.first
                spectatingProfs++
            }
            1 -> {
                spectatingProfs--
                if(spectatingProfs == 0) {
                    cheatingStudents -= event.first - startedSpectating + 1
                }
            }
        }
    }

    println(cheatingStudents)

    writer.close()
}
