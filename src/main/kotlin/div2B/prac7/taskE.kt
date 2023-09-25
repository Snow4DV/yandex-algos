package div2B.prac7

import java.io.*
import java.util.*
import kotlin.math.abs


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val n = reader.readLine().trim().toInt()

    // События: -1: начало пол. прям., 1: конец пол. прям.
    val events = mutableListOf<Pair<Double, Int>>() // [X (rads), Type+Index]


    var counter = 1

    var innerR = Double.MIN_VALUE
    var outerR = Double.MAX_VALUE

    reader.readLines().forEach {
        val (r1, r2, angle1, angle2) = it.trim().split(' ').map {it.toDouble()}
        events.add(angle1 to -counter)
        events.add(angle2 to counter++)
        if(r1 > innerR) innerR = r1
        if(r2 < outerR) outerR = r2
    }

    if(innerR > outerR) {
        writer.println(0.0)
        writer.close()
        return
    }

    events.sortWith {o1, o2 ->
        val comp1 = o1.first.compareTo(o2.first)
        if (comp1 != 0) comp1 else o1.second.compareTo(o2.second)
    }

    val openedPolarRects = HashSet<Int>()

    for(event in events) {
        when(event.second < 0) {
            true -> openedPolarRects.add(abs(event.second))
            false -> openedPolarRects.remove(abs(event.second))
        }
    }

    var resS = 0.0

    var curOpenRectsCount = openedPolarRects.size

    for(i in events.indices) {
        val event = events[i]
        when(event.second < 0) { // by type
            true -> {
                curOpenRectsCount++

                if(curOpenRectsCount == n) { // everything intersects!
                    val startAngle = event.first
                    val endAngle = if (i+1 < events.size) events[i+1].first else events[0].first

                    var sumAngle = if(startAngle <= endAngle) {
                        endAngle - startAngle
                    } else {
                        2 * Math.PI - startAngle + endAngle
                    }


                    val polarRectS = Math.PI * (outerR * outerR - innerR * innerR) * (sumAngle / (2*Math.PI))

                    resS += polarRectS
                }
            }
            false -> curOpenRectsCount--
        }


    }

    writer.println(resS)
    writer.close()
}
