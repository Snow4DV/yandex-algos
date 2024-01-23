package div3B.prac0

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = reader.readLine().trim().split(" ").map { it.toInt() }

    val mergeList = Array<MutableList<Int>>(n+1) { mutableListOf() }

    repeat(m) {
        val (v1, v2) = reader.readLine().trim().split(" ").map { it.toInt() }
        mergeList[v1].add(v2)
    }



    val writer = PrintWriter(System.out)

    val visited = IntArray(n + 1)
    val result: MutableList<Int> = mutableListOf()

    for(v in 1..n) {
        if(visited[v] == 0 && !dfs4(mergeList, visited, result, v)) {
            writer.println("-1")
            writer.close()
        }
    }

    writer.println(result.reversed().joinToString(" "))
    writer.close()


}


private fun dfs4(mergeList: Array<MutableList<Int>>, visited: IntArray, result: MutableList<Int>, current: Int): Boolean {
    visited[current] = 1
    for(adj in mergeList[current]) {
        if(visited[adj] == 0) {
            if(!dfs4(mergeList, visited, result, adj)) return false
        } else if(visited[adj] == 1) {
            return false
        }
    }
    visited[current] = 2
    result.add(current)
    return true
}
