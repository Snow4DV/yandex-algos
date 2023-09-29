package div1.prac7

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.collections.HashSet


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

   val n = reader.readLine().trim().toInt()




    repeat(n) {
        val input = reader.readLine().trim().split(' ').map { it.toInt() }

        val k = input[0]
        val events = mutableListOf<Triple<Int, Int, Int>>()

        var counter = 1
        for(i in 1 until input.size step 2) {
            events.add(Triple(input[i], -counter, input[i+1])) //started working
            events.add(Triple(input[i+1], counter, input[i] + 1)) //stopped working
            counter++
        }

        events.sortWith(compareBy({it.first}, {it.second}))

        var curSecGuys = HashSet<Int>()
        var failFlag = false
        val notUselessSecGuys = HashSet<Int>()
        var prevEventTime = -1

        for(i in 0 until events.size) {
            val event = events[i]
            if(event.first != 0 && curSecGuys.size == 0) {
                failFlag = true
                break
            }

            if(curSecGuys.size == 1 && prevEventTime != event.first) {
                notUselessSecGuys.add(curSecGuys.first())
            }

            if(event.second < 0) {
                curSecGuys.add(-event.second)
            } else {
                curSecGuys.remove(event.second)
            }

            prevEventTime = event.first
        }

        if(events.first().first > 0 || events.last().first < 10000) {
            failFlag = true
        }

        if(failFlag || notUselessSecGuys.size < k) {
            writer.println("Wrong Answer")
        } else {
            writer.println("Accepted")
        }
    }


    writer.close()
}
