package div1.prac7

import java.io.*
import kotlin.collections.HashSet


fun main() { // Не закончена
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    //<Start, End, Type>
    val events = mutableListOf<Triple<Int, Int, Int>>() // types: -1 = buyer entered; 1 = buyer cant listen to ad anymore

    var counter = 1
    repeat(n) {
        val (start, end) = reader.readLine().trim().split(' ').map {it.toInt()}
        if(end - start >= 5) {
            events.add(Triple(start, end-5, -counter))
            events.add(Triple(start, end-5, counter))
            counter++
        }
    }
    events.sortWith(compareBy({ if (it.third < 0) it.first else it.second }, {it.third}))


    val buyers = HashSet<Int>()

    var maxBuyersCount = 0
    var maxBuyersFirstShow = 1
    var maxBuyersSecondShow = 6


    for(i in 0 until events.size) {
        val firstShow = events[i]

        if(firstShow.third < 0) {
            buyers.add(-firstShow.third)
        } else {
            buyers.remove(firstShow.third)
            continue // так мы рассматриваем только входы
        }

        var newBuyers = 0

        var maxNewBuyers = 0
        var maxNewBuyersShowTime = firstShow.first + 5

        for(j in i+1 until events.size) {
            var event = events[j]
            if(event.third < 0) {
                newBuyers++
            } else if(event.third > 0 && !buyers.contains(event.third)) {
                newBuyers--
                continue // так мы рассматриваем только входы
            }
            if(event.second >= firstShow.first + 5 && newBuyers > maxNewBuyers) { // время с момента прошлого показа прошло
                maxNewBuyers = newBuyers
                maxNewBuyersShowTime = if (event.first < firstShow.first + 5) firstShow.first + 5 else event.first
            }
        }

        if(buyers.size + maxNewBuyers > maxBuyersCount) {
            maxBuyersCount = buyers.size + maxNewBuyers
            maxBuyersFirstShow = firstShow.first
            maxBuyersSecondShow = maxNewBuyersShowTime
        }


    }

    writer.println("$maxBuyersCount $maxBuyersFirstShow $maxBuyersSecondShow")

    writer.close()
}
