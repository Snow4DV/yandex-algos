package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().trim().toInt() // stations
    val m = reader.readLine().trim().toInt() // lines

    val stationToLines = Array<MutableSet<Int>>(n) { mutableSetOf() } // station to lines it belongs to
    val linesMergeList = Array<MutableSet<Int>> (m) { mutableSetOf() }

    val lengths = IntArray(m) {-1} // shortest length of path to get to each line

    repeat(m) {line ->
        val stations = reader.readLine().trim().split(" ").map { it.toInt() - 1}
        for(i in 1 until stations.size) {
            val station = stations[i]
            stationToLines[station].add(line)
        }
    }

    for(station in stationToLines.indices) {
        val lines = stationToLines[station].toList()
        for(i in lines.indices) {
            for(j in i + 1 until lines.size) {
                linesMergeList[lines[i]].add(lines[j])
                linesMergeList[lines[j]].add(lines[i])
            }
        }
    }

    val (begin, end) = reader.readLine().trim().split(" ").map { it.toInt() - 1 }
    val linesOfBegin = stationToLines[begin]
    val linesOfEnd = stationToLines[end]

    linesOfBegin.forEach {
        lengths[it] = 0
    }

    val queue = LinkedList<Int>()
    linesOfBegin.forEach { queue.addLast(it) } // trying to get from each line begin station belongs to

    while(queue.size > 0) { // dfs
        val top = queue.removeFirst()
        val curLen = lengths[top]
        for(neigh in linesMergeList[top]) {
            if(lengths[neigh] == -1) {
                lengths[neigh] = curLen + 1
                queue.addLast(neigh)
            }
        }
    }

    var minNum: Int? = null
    for(line in linesOfEnd) {
        if(lengths[line] == -1) continue
        if(minNum == null) {
            minNum = lengths[line]
        } else {
            minNum = min(minNum, lengths[line])
        }
    }

    println(minNum ?: -1)
}

