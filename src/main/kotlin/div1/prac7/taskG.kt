package div1.prac7

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.math.min


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

   val (m, n) = reader.readLine().trim().split(' ').map { it.toInt() }

    val dudes = mutableListOf<Triple<Int, Int, Int>>()

    repeat(n) {
        val (t, z, i) = reader.readLine().trim().split(' ').map { it.toInt() }
        dudes.add(Triple(t, z, i))
    }

    val (time, eachCount) = binSearchL(dudes, m)
    writer.println(time)


    writer.print(eachCount[0])
    for(i in 1 until eachCount.size) {
        writer.print(' ')
        writer.print(eachCount[i])
    }

    writer.close()
}
//returns time
private fun binSearchL(dudes: List<Triple<Int, Int, Int>>, balloonCountM: Int): Pair<Int, Array<Long>> {
    var l = 0
    var r = Integer.MAX_VALUE
    var lastSuccessfulBArray: Array<Long>? = null



    while(l < r) {
        val m = (l+r)/2
        val checkHowManyBalloonsForTime = checkHowManyBalloonsForTime(m, dudes, balloonCountM)
        if(checkHowManyBalloonsForTime.first >= balloonCountM) {
            r = m
            lastSuccessfulBArray = checkHowManyBalloonsForTime.second
        } else {
            l = m + 1
        }
    }

    return l to lastSuccessfulBArray!!
}

fun checkHowManyBalloonsForTime(time: Int, dudes: List<Triple<Int, Int, Int>>, reqBalloons: Int): Pair<Long, Array<Long>> {

    var sumBalloons = 0L
    val bArray = Array(dudes.size) {0L}


    for(i in dudes.indices) {
        if(sumBalloons >= reqBalloons) {
            bArray[i] = 0
            continue
        }
        val dude = dudes[i]
        var curDudeTime = time.toLong()
        var balloonsCount = 0L

        val howManyTimesWillHaveABreak = curDudeTime / (dude.first.toLong() * dude.second + dude.third)
        balloonsCount += howManyTimesWillHaveABreak * dude.second
        curDudeTime -= howManyTimesWillHaveABreak * dude.third + howManyTimesWillHaveABreak * dude.second * dude.first

        val lastBalloonCount = if(curDudeTime / dude.first <= dude.second) {
            curDudeTime / dude.first.toLong()
        } else {
            dude.second.toLong()
        }
        balloonsCount += lastBalloonCount
        curDudeTime -= dude.first*lastBalloonCount

        if(reqBalloons - (sumBalloons + balloonsCount) < 0) {
            balloonsCount = reqBalloons - sumBalloons // do not do more than needed
        }

        sumBalloons+=balloonsCount

        bArray[i] = balloonsCount
    }

    return sumBalloons to bArray
}

/*
2437
15 20 28 170 55 22 40 55 50 138 46 33 38 18 48 24 31 31 97 41
 */

//8 10 64
// expected: 170 on 2437
// got: 240 on 2437



/*
1000 20
59 1 100
82 2 81
72 2 27
8 10 64
37 9 67
99 9 94
52 4 33
43 9 8
36 6 77
12 7 41
45 6 52
60 4 57
52 5 60
78 1 56
44 6 42
87 6 72
64 5 73
71 4 27
10 4 61
50 10 91



2437
15 20 28 240 55 22 40 55 50 138 46 33 38 18 48 24 31 31 68 0
 */