package div2B.prac4

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*

fun main(args: Array<String>) {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val writer = PrintWriter(System.out)

    val map = LinkedHashMap<String, Int>()

    var line: String?

    var votesSum = 0

    while((reader.readLine().also { line = it }) != null) {
        val lastSpaceIndex = line!!.indexOfLast { it == ' ' }

        val pName = line!!.substring(0, lastSpaceIndex)
        val votesCount = line!!.substring(lastSpaceIndex + 1).toInt()
        map[pName] = votesCount
        votesSum += votesCount
    }


    var izbirChast = votesSum / 450.0

    var leftVotes = 450

    map.forEach { leftVotes -= (it.value/izbirChast).toInt() }



    val tMap = map.entries.sortedWith { o1, o2 ->
        val comp1 = ((o2.value / izbirChast) % 1) - ((o1.value / izbirChast) % 1)
        val comp1int = if(comp1 > 0) {
            1
        } else if(comp1 < 0) {
            -1
        } else {
            0
        }
        if (comp1int != 0) comp1int else o2.value - o1.value
    }.map {
        if (leftVotes > 0) {
            leftVotes--
            it.key to (it.value/izbirChast).toInt() + 1
        } else {
            it.key to (it.value/izbirChast).toInt()
        }
    }.toMap()

    map.keys.forEach {
        writer.println("$it ${tMap[it]}")
    }
    writer.close()
}

