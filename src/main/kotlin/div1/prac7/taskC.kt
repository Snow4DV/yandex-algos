package div1.prac7

import java.io.*
import java.util.*
import kotlin.math.abs


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, d) = reader.readLine().trim().split(' ').map { it.toInt() }

    val events = mutableListOf<Pair<Int, Int>>() // types: student=0.....10000, speakingRangeEnd=-1

    reader.readLine().trim().split(' ').forEachIndexed { index, it ->
        val x = it.toInt()
        events.add(x to index)
        events.add(x+d to -1)
    }

    events.sortWith(compareBy({it.first}, {it.second * -1}))

    var curSpeakingCount = 0

    var maxSpeakingStudentsCount = -1

    var studentsVars = Array(n) {-1}


    for(event in events) {
        when(event.second) {
            -1 -> curSpeakingCount--
            else -> {
                curSpeakingCount++
                if(curSpeakingCount > maxSpeakingStudentsCount) {
                    maxSpeakingStudentsCount = curSpeakingCount
                }
            }
        }
    }

    writer.println(maxSpeakingStudentsCount)


    var counter = 0

    for(event in events) {
        if(event.second != -1) {
            val i1 = (counter++) % maxSpeakingStudentsCount + 1
            studentsVars[event.second] = i1
        }
    }

    writer.print(studentsVars[0])

    for(i in 1 until studentsVars.size) {
        writer.print(' ')
        writer.print(studentsVars[i])
    }

    writer.close()
}
