package div2B.prac7

import java.io.*
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = PrintWriter(System.out)

    val m = reader.readLine().trim().toInt()

    var lines = mutableListOf<String>()

    var line: String?

    while(reader.readLine().trim().also { line = it } != "0 0") {
        lines.add(line!!)
    }


    val segments = lines.map {readLine ->
        readLine.trim().split(Pattern.compile("""\s+""")).map { it.toInt() }.zipWithNext()[0]
    }.sortedBy { it.first }




    val chosen = findMinLines(segments, m)

    if(chosen.size != 0) {
        writer.println(chosen.size)
        chosen.forEach {
            writer.println("${it.first} ${it.second}")
        }
    } else {
        writer.println("No solution")
    }

    writer.close()
}


fun findMinLines(segments: List<Pair<Int, Int>>, reqX: Int): List<Pair<Int, Int>> {
    var farthestIncludingZeroIndex = -1

    for(i in segments.indices) {
        if(segments[i].second > 0 && (farthestIncludingZeroIndex == -1 || segments[i].second > segments[farthestIncludingZeroIndex].second) && segments[i].first <= 0) {
            farthestIncludingZeroIndex = i
        }
    }

    if(farthestIncludingZeroIndex == -1) return listOf()

    var curX = segments[farthestIncludingZeroIndex].second
    var chosen = mutableListOf(segments[farthestIncludingZeroIndex])

    var longestFittingIndex = farthestIncludingZeroIndex + 1

    if(longestFittingIndex >= segments.size || segments[longestFittingIndex].first > curX) return if(curX >= reqX) chosen else listOf()



    for(i in longestFittingIndex + 1 until segments.size) {
        if(segments[i].first <= curX && segments[i].second > segments[longestFittingIndex].second) {
            longestFittingIndex = i
        } else if(segments[i].first > curX && segments[longestFittingIndex].first <= curX && curX < reqX) { // reached first non fitt
            curX = segments[longestFittingIndex].second
            chosen.add(segments[longestFittingIndex]) // PROBLEM: current one is not considered
            longestFittingIndex = i
        }
    }

    if(longestFittingIndex == segments.size - 1 && segments[longestFittingIndex].first <= curX && segments[longestFittingIndex].second >= curX) {
        curX = segments[longestFittingIndex].second
        chosen.add(segments[longestFittingIndex])
    }

    return if(curX >= reqX) chosen else listOf()
}
