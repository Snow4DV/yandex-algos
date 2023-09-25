package lec

import java.io.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)


    val n = reader.readLine().trim().toInt()
    val people = reader.readLine().trim().split(' ').map {it.toInt()}

    val m = reader.readLine().trim().toInt()
    val boss = reader.readLine().trim().split(' ').map { it.toInt() }


    val events = ArrayList<Pair<Int, Int>>(n*2 + m)

    for(i in people.indices step 2) {
        events.add(people[i] to -1) // entry
        events.add(people[i + 1] to 1) // exit
    }

    for(bossEntry in boss) {
        events.add(bossEntry to 0) // boss is checking online
    }

    events.sortedWith {p1, p2 ->
        val comp1 = p1.first.compareTo(p2.first)
        if(comp1 != 0) comp1 else p1.second.compareTo(p2.second)
    }

    var onlineCounts = ArrayList<Int>(m)

    var online = 0


    for(event in events) {
        when(event.second) {
            -1 -> online++ // entry
            1 -> online-- // exit
            0 -> onlineCounts.add(online) // boss is checking online
        }
    }

    onlineCounts.forEach(writer::println)

    writer.close()
}



