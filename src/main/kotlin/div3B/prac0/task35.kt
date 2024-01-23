package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val n = reader.readLine().trim().toInt()

    val mergeList = Array<MutableList<Int>>(n) { mutableListOf() }


    repeat(n) {
        val mergeLine = reader.readLine().trim().split(" ")
        for(i in mergeLine.indices) {
            if(mergeLine[i] == "1") {
                mergeList[it].add(i)
            }
        }
    }

    val writer = PrintWriter(System.out)

    val visited = IntArray(n)
    val previous = IntArray(n)

    for(v in 0 until n) {
        val cycleStart = if(visited[v] == 0) dfs4(mergeList, visited, previous, v, -1) else -1
        if(cycleStart != -1) {
            writer.println("YES")
            val res = mutableListOf(cycleStart)
            var current = cycleStart
            while (previous[current] != cycleStart) {
                res.add(previous[current])
                current = previous[current]
            }
            writer.println(res.size)
            writer.println(res.map { it + 1}.joinToString(" "))
            writer.close()
            return
        }
    }

    writer.println("NO")
    writer.close()


}


private fun dfs4(mergeList: Array<MutableList<Int>>, visited: IntArray, prev: IntArray, current: Int, prevDfs: Int): Int {
    visited[current] = 1

    for(adj in mergeList[current]) {
        if(adj == prevDfs) continue
        if(visited[adj] == 0) {
            prev[adj] = current
            val dfsRes = dfs4(mergeList, visited, prev, adj, current)
            if(dfsRes != -1) return dfsRes
        } else if(visited[adj] == 1) {
            prev[adj] = current
            return adj
        }
    }
    visited[current] = 2
    return -1
}
