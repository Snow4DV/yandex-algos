package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.LinkedList
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().trim().toInt()

    val mergeList = Array<MutableList<Int>>(n) { mutableListOf() }


    repeat(n) {lineIndex ->
        val line = reader.readLine().trim().split(" ").map { it.toInt() }
        line.forEachIndexed {index, it -> if(it == 1) mergeList[index].add(lineIndex)}
    }

    val (begin, end) = reader.readLine().trim().split(" ").map { it.toInt() }
    val pathLengths = IntArray(n) {-1} // -1=unvisited; -2=destination
    val prev = IntArray(n) {-2}
    prev[begin - 1] = -1

    pathLengths[begin - 1] = 0
    pathLengths[end - 1] = -2


    val queue = LinkedList<Int>()
    queue.addLast(begin - 1)

    while (queue.size > 0) {
        val top = queue.removeFirst()
        var flag = false
        for(child in mergeList[top]) {
            if(pathLengths[child] == -1) {
                pathLengths[child] = pathLengths[top] + 1
                prev[child] = top
                queue.addLast(child)
            } else if(pathLengths[child] == -2) {
                pathLengths[child] = pathLengths[top] + 1
                flag = true
                break
            }
        }
        if(flag) break
    }
    val res = pathLengths[end - 1]


    println(if(res >= 0) res else -1)

}
