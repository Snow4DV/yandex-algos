package div1.prac7

import java.io.*
import java.util.*
import kotlin.math.abs


fun main() { // решение в два прохода
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    var allDayOffices = 0

    val events = mutableListOf<Pair<Int, Int>>() // types: -1 = buyer entered; 1 = buyer left

    var counter = 1



    repeat(n) {
        val (startH, startM, endH, endM) = reader.readLine().trim().split(' ').map {it.toInt()}
        val start = startH*60 + startM
        val end = endH*60+endM
        if(start != end) {
            events.add(start to -(counter))
            events.add(end to (counter))
        } else {
            allDayOffices++
        }

        counter++
    }
    if(allDayOffices == n) {
        writer.println(1440)
        writer.close()
        return
    }
    events.sortWith(compareBy({it.first}, {it.second}))


    val openedOffices = HashSet<Int>()

    for(event in events) { // first pass
        if(event.second < 0) { // office opened
            openedOffices.add(abs(event.second))
        } else if(event.second > 0) { // office closed
                openedOffices.remove(event.second) // will remove only if there's such office
        }
    }

    var sumMinutes = 0

    for(i in 0 until events.size) { // second pass
        val event = events[i]
        if(event.second < 0) { // office opened
            openedOffices.add(abs(event.second))
        } else if(event.second > 0) { // office closed
            openedOffices.remove(event.second)
        }


        if(openedOffices.size + allDayOffices == n) {
            val startMin = event.first
            val endMin = events[if (i+1 >= events.size) 0 else i+1].first

            if(endMin >= startMin) {
                sumMinutes += endMin - startMin
            } else {
                sumMinutes += (24*60)-startMin + endMin
            }
        }
    }

    writer.println(sumMinutes)




    writer.close()
}
