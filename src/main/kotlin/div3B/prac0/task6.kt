package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashSet
import kotlin.concurrent.fixedRateTimer
import kotlin.math.max
import kotlin.math.min

fun main() { // сортировка событий

    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val events = mutableListOf<Triple<Int, Int, Int>>() // sectorNo to type (-1 ->start ; 1-> end) to index

    val sectorsCount = reader.getNum()
    val partitionsCount = reader.getNum()

    var curUntouchedIndex = -1
    val curOpenIndexes = TreeSet<Int>(Comparator.reverseOrder())

    var counter = 0
    repeat(partitionsCount) {
        val (a, b) = reader.readLine().trim().split(' ').map {it.toInt()}
        events.add(Triple(a, -1, counter))
        events.add(Triple(b, 1, counter))
        counter++
    }



    events.sortWith(compareBy({it.first}, {it.second}, {if (it.second == -1) it.third else (it.third * -1)}))

    var goodOsCounter = 0
    events.forEach {event ->
        when(event.second) {
            -1 -> {
                if(curOpenIndexes.size == 0 || curOpenIndexes.first() < event.third) {
                    curUntouchedIndex = event.third
                }
                curOpenIndexes.add(event.third)
            }
            1 -> {
                if(curUntouchedIndex == event.third && curOpenIndexes.first() == event.third) {
                    goodOsCounter++
                }
                if(curUntouchedIndex == event.third) curUntouchedIndex = -1
                curOpenIndexes.remove(event.third)
            }
        }
    }

    writer.println(goodOsCounter)

    writer.close()
}



private fun BufferedReader.getNum() = this.readLine().trim().toInt()