package div1.prac7

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashSet


fun main() { // бин поиск, не закончена (не работает для случаев с -1)
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val (n, m) = reader.readLine().trim().split(' ').map { it.toInt() }

    val events = mutableListOf<List<Int>>()

    var counter = 1
    repeat(m) {
        val (leaveCity, leaveTime, destCity, destTime) = reader.readLine().trim().split(' ')

        val (leaveH, leaveM) = leaveTime.split(':').map { it.toInt() }
        val (destH, destM) = destTime.split(':').map { it.toInt() }

        val leaveMinSum = leaveH * 60 + leaveM
        val destMinSum = destH*60 + destM

        events.add(listOf(destMinSum, destCity.toInt() - 1, -counter, leaveMinSum)) // сначала приезд
        events.add(listOf(leaveMinSum, leaveCity.toInt() - 1, counter, destMinSum)) // потом уезд


        counter++
    }

    events.sortWith(compareBy({it[0]}, {it[2]}))

    writer.println(binSearchL(events, n))
    writer.close()
}

private fun binSearchL(events: List<List<Int>>, nCitiesCount: Int): Int {
    var l = 0
    var r = Integer.MAX_VALUE

    var foundGood = false

    while(l < r) {
        val m = (l+r)/2
        if(checkIfBusesCountIsEnough(events, nCitiesCount, m)) {
            r = m
            foundGood = true
        } else {
            l = m + 1
        }
    }

    return if(foundGood) l else -1
}


private fun checkIfBusesCountIsEnough(events: List<List<Int>>, nCitiesCount: Int, busesCount: Int): Boolean {
    var sharedBusesBalance = busesCount // баланс автобусов города

    var eachCityBusesCount = Array(nCitiesCount) { 0 }

    for(event in events) {
        if(event[2] < 0 && event[0] >= event[3]) { // приезд
            eachCityBusesCount[event[1]]++
        } else if(event[2] > 0) { // уезд
            eachCityBusesCount[event[1]]--

            if(eachCityBusesCount[event[1]] < 0 && sharedBusesBalance > 0) { // в городе закончились автобусы - списываем их с баланса города
                sharedBusesBalance--
                eachCityBusesCount[event[1]] ++
            } else if(eachCityBusesCount[event[1]] < 0  ) { // у города кончились автобусы
                return false
            }
        }
    }

    for(event in events) {
        if(event[2] < 0) { // приезд
            eachCityBusesCount[event[1]]++
        } else if(event[2] > 0) { // уезд
            eachCityBusesCount[event[1]]--

            if(eachCityBusesCount[event[1]] < 0 && sharedBusesBalance > 0) { // в городе закончились автобусы - списываем их с баланса города
                sharedBusesBalance--
                eachCityBusesCount[event[1]] ++
            } else if(eachCityBusesCount[event[1]] < 0  ) { // у города кончились автобусы
                return false
            }
        }
    }

    return true
}
